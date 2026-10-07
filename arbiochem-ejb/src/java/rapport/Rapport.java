package rapport;

import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;

import bean.CGenUtil;
import fabrication.Of;
import fabrication.OfFilleCpl;
import utilitaire.UtilDB;

import utilitaire.Utilitaire;
import utils.ConstanteAsync;
public class Rapport {

    private RapportData rapportData;

    public RapportData getRapportData() {
        return rapportData;
    }

    public void setRapportData(RapportData rapportData) {
        this.rapportData = rapportData;
    }

    public void sendMails(Date daty)throws Exception{
        this.setRapportData(new RapportData());

        String aWhere = " and DATY = TO_DATE('"+daty+"', 'yyyy-MM-dd')";
//        String aWhere = " and DATY = TO_DATE('2025-02-18', 'yyyy-MM-dd')";

        Connection c = new UtilDB().GetConn();

        Of of = new Of();
        of.setNomTable("OFABLIB");
        Of[] ofs = (Of[]) CGenUtil.rechercher(of, null, null, aWhere);
        if(ofs.length==0){
            throw new Exception("Ordre de fabrication introuvable");
        }
        of = ofs[0];
        this.getRapportData().setOf(of);

        this.sendMailChefProduction(c);
        this.sendMailDg(c, daty);

        c.close();
    }

    public void sendMailDg(Connection c, Date daty)throws Exception{
        String htmlBody = this.getRapportData().genererEntete();

        htmlBody += this.getRapportData().detailsOf(c);
        htmlBody += this.getRapportData().stockProduitFiniListe(c);

        htmlBody += this.getRapportData().genererBasPage();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        this.sendMail("Rapport Journali\u00E8re du "+formatter.format(daty), htmlBody, ConstanteAsync.roleDg);
    }

    public void sendMailChefProduction(Connection c)throws Exception{

        String htmlBody = this.getRapportData().genererEntete();

        htmlBody += this.getRapportData().ficheOf(c);
        htmlBody += this.getRapportData().detailsOf(c);
        htmlBody += this.getRapportData().rapprochement(this.getRapportData().getOf().getId(), "stockEtDepenseOfFabThe", null);
        htmlBody += this.getRapportData().rapprochementGlobale(c, this.getRapportData().getOf().getId(), "stockEtDepOfFabTheCatGroupe");
        htmlBody += this.getRapportData().stockProduitFiniListe(c);

        htmlBody += this.getRapportData().genererBasPage();

        this.sendMail("CR OF N: "+this.getRapportData().getOf().getId(), htmlBody, ConstanteAsync.roleChefProduction);

        OfFilleCpl[] ofFilleCpls = this.getRapportData().getFillesOf(c);
        System.out.println(ofFilleCpls.length);
        for (int i = 0; i < ofFilleCpls.length; i++) {
            this.sendOfFilleChefProduction(c, ofFilleCpls[i]);
        }
    }

    public void sendOfFilleChefProduction(Connection c, OfFilleCpl ofFilleCpl)throws Exception{
        String htmlBody = this.getRapportData().genererEntete();
        htmlBody += this.getRapportData().ficheOfFille(ofFilleCpl);
        htmlBody += this.getRapportData().rapprochement(ofFilleCpl.getId(), "STOCKETDEPENSEOFFILLEFABTHE", "STOCKETDEPENSEOFFILLEFABTHE");;
        htmlBody += this.getRapportData().rapprochementGlobale(c, ofFilleCpl.getId(), "STOCKETDEPOFFILLETHECATGROUPE");
        htmlBody += this.getRapportData().genererBasPage();

        this.sendMail("CR OFFILLE N: "+ofFilleCpl.getId(), htmlBody, ConstanteAsync.roleChefProduction);
    }

    public void sendMail(String objet, String corpsMail, String role)throws Exception{
        Connection c=null;
        try{
            c=new UtilDB().GetConn();

            MailRapport[] mail=getListeMail(role, c);
            if(mail!=null && mail.length>0){
                Mail retour=new Mail(ConstanteAsync.getMailRapport()[0],ConstanteAsync.getMailRapport()[1],objet,corpsMail);
                retour.setTo(mail[0].getVal());
                if(mail.length>1){
                    String[] cc=new String[mail.length-1];
                    for(int i=1;i<mail.length;i++){
                        cc[i-1]=mail[i].getVal();
                    }
                    retour.setCc(cc);
                }
                retour.send();
            }
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }finally{
            if(c!=null)c.close();
        }
    }

    public static MailRapport[] getListeMail(String role, Connection c)throws Exception{
        try{
            MailRapport mail=new MailRapport();
            return (MailRapport[])CGenUtil.rechercher(mail,null,null,c," and role ='"+role+"'");
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}

//e-mail: noreplyasync@gmail.com
//pwd: Async1234.
//pwdApp: lhcp apld qdgt tjqp

/*
public static final String[] mailRapport={"noreplyasync@gmail.com","lhcp apld qdgt tjqp"};

public static String[] getMailRapport(){
    return mailRapport;
}
*/