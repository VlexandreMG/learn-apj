package ferme.triageBatiment;

import bean.ClassMAPTable;
import bean.ClassMere;
import ferme.utils.ConstanteFerme;
import bean.CGenUtil;

import java.sql.Connection;
import java.sql.Date;
import utilitaire.UtilDB;

public class TriageBatimentParquet extends ClassMere {
    private String id;
    private Date daty;
    private String idlot;
    private int dispomale;
    private int dispofemelle,etat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public String getIdlot() {
        return idlot;
    }

    public void setIdlot(String idlot) {
        this.idlot = idlot;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getDispomale() {
        return dispomale;
    }

    public void setDispomale(int dispomale) {
        this.dispomale = dispomale;
    }

    public int getDispofemelle() {
        return dispofemelle;
    }

    public void setDispofemelle(int dispofemelle) {
        this.dispofemelle = dispofemelle;
    }



    public TriageBatimentParquet() throws Exception {
        this.setNomTable("TRIAGEBATIMENTPARQUET");
        this.setNomClasseFille("ferme.tirageBatiment.TriageBatimentParquetDetail");
        this.setLiaisonFille("idmere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("TB","getseq_triagebatimentparquet");
        this.setId(makePK(c));
    }

    @Override
    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }

    @Override
    public ClassMAPTable createObject(String u, Connection c) throws Exception {
//        TriageBatimentParquetDetail[] triageBatimentParquetDetails = (TriageBatimentParquetDetail[]) this.getFille();
//        double quantiteMale = this.getDispomale();
//        double quantiteFemelle = this.getDispofemelle();
//
//        double sommeMale = 0;
//        double sommeFemelle = 0;
//
//        for (int i = 0; i < triageBatimentParquetDetails.length; i++) {
//            String idsexe = triageBatimentParquetDetails[i].getIdsexe();
//            double qte = triageBatimentParquetDetails[i].getQte();
//
//            if (ConstanteFerme.IDSEXEMALE.equals(idsexe)) {
//                sommeMale += qte;
//            } else if (ConstanteFerme.IDSEXEFEMELLE.equals(idsexe)) {
//                sommeFemelle += qte;
//            } else {
//                throw new Exception("Sexe non reconnu pour la ligne de d\u00E9tail: " + idsexe);
//            }
//        }
//
//        if (sommeMale != quantiteMale) {
//            throw new Exception("La quantit\u00E9 totale des m\u00E2les tri\\u00E9s (" + sommeMale
//                    + ") ne correspond pas \u00E0 la quantit\u00E9 disponible male (" + quantiteMale + ")");
//        }
//
//        if (sommeFemelle != quantiteFemelle) {
//            throw new Exception("La quantit\u00E9 totale des femelles tri\u00E9es (" + sommeFemelle
//                    + ") ne correspond pas \u00E0 la quantit\u00E9 disponible femelle (" + quantiteFemelle + ")");
//        }

        return super.createObject(u, c);
    }
    public double getDisponnible(String idLot,String idSexe,Connection c)throws Exception{
        boolean canClose=false;
        try{
            if(c==null){
                c=new UtilDB().GetConn();
                canClose=true;
            }
            TriageLotSexeQualite t = new TriageLotSexeQualite();
            t.setIdLot(idLot);
            t.setIdsexe(idSexe);
            t.setIdqualite(ConstanteFerme.qualiteConforme);
            TriageLotSexeQualite[] r = (TriageLotSexeQualite[])CGenUtil.rechercher(t, null, null, c, "");
            if(r.length>0 && r!=null){
                return r[0].getQte();
            }
            return 0;
        } catch(Exception e){
            throw e;
        } finally {
            if(canClose){
                c.close();
            }
        }
    }

    public double getDisponnibleLib(String idLot,String idSexe, String idQualite,Connection c)throws Exception{
        boolean canClose=false;
        try{
            if(c==null){
                c=new UtilDB().GetConn();
                canClose=true;
            }
            TriageLotSexeQualiteLib t = new TriageLotSexeQualiteLib();
            t.setIdLot(idLot);
            t.setIdSexe(idSexe);
            t.setIdQualite(idQualite);
            TriageLotSexeQualiteLib[] r = (TriageLotSexeQualiteLib[])CGenUtil.rechercher(t, null, null, c, "");
            if(r.length>0 && r!=null){
                return r[0].getQte();
            }
            return 0;
        } catch(Exception e){
            throw e;
        } finally {
            if(canClose){
                c.close();
            }
        }
    }
}

