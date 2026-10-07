package paie.onBoarding;

import bean.CGenUtil;
import bean.ClassMere;

import java.sql.Connection;
import java.sql.Date;

public class CheckListMere extends ClassMere {
    private String id;
    private Date daty;
    private String idpersonnel;
    private int typecheckliste;

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

    public String getIdpersonnel() {
        return idpersonnel;
    }

    public void setIdpersonnel(String idpersonnel) {
        this.idpersonnel = idpersonnel;
    }

    public int getTypecheckliste() {
        return typecheckliste;
    }

    public void setTypecheckliste(int typecheckliste) {
        this.typecheckliste = typecheckliste;
    }



    public CheckListMere() throws Exception {
        this.setNomTable("CHECKLIST_MERE");
        this.setNomClasseFille("paie.onBoarding.CheckListFille");
        this.setLiaisonFille("idchecklistmere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CHL","getSeqCheckListMere");
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

    public CheckListFille[] getDataCheckListByType(int type) throws Exception {
        CheckListFille checkListFille = new CheckListFille();
        if (type == 1){
            checkListFille.setNomTable("Data_check_list_sortie");
        } else{
            checkListFille.setNomTable("Data_check_list_entree");
        }
        CheckListFille[] checkListFilles = (CheckListFille[]) CGenUtil.rechercher(checkListFille, null, null, " ");
        if (checkListFilles != null && checkListFilles.length > 0){
            for (int i = 0; i < checkListFilles.length; i++) {
                checkListFilles[i].setId(null);
            }
        }
        return checkListFilles;
    }
}

