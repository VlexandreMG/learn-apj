package paie.onBoarding;

import bean.CGenUtil;
import bean.ClassFille;
import user.UserEJB;

import java.sql.Connection;

public class CheckListFille extends ClassFille {
    private String id;
    private String idchecklistmere;
    private String idtypedocument;
    private String idresponsable;
    private String contenue;
    private int est_coche;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdchecklistmere() {
        return idchecklistmere;
    }

    public void setIdchecklistmere(String idchecklistmere) {
        this.idchecklistmere = idchecklistmere;
    }

    public String getIdtypedocument() {
        return idtypedocument;
    }

    public void setIdtypedocument(String idtypedocument) {
        this.idtypedocument = idtypedocument;
    }

    public String getIdresponsable() {
        return idresponsable;
    }

    public void setIdresponsable(String idresponsable) {
        this.idresponsable = idresponsable;
    }

    public String getContenue() {
        return contenue;
    }

    public void setContenue(String contenue) {
        this.contenue = contenue;
    }

    public int getEst_coche() {
        return est_coche;
    }

    public void setEst_coche(int est_coche) {
        this.est_coche = est_coche;
    }


    @Override
    public String getNomClasseMere() {
        return "paie.onBoarding.CheckListMere";
    }

    @Override
    public String getLiaisonMere() {
        return "idchecklistmere";
    }

    public CheckListFille() throws Exception {
        this.setNomTable("CHECKLIST_FILLE");
        this.setNomClasseMere("paie.onBoarding.CheckListMere");
        this.setLiaisonMere("idchecklistmere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CHLF","getSeqCheckListFille");
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

    public void checkListFille(String[] ids, UserEJB u) throws Exception {
        CheckListFille[] listFilles = new CheckListFille[ids.length];
        for (int i = 0; i < ids.length; i++) {
            System.out.println("idssssss " + i + " : " + ids[i]);
            listFilles[i] = new CheckListFille();
            listFilles[i].setId(ids[i]);
            listFilles[i] = (CheckListFille) CGenUtil.rechercher(listFilles[i], null, null, " ")[0];
            if (listFilles[i].getEst_coche() == 0) {
                listFilles[i].setEst_coche(1);
                u.updateObject(listFilles[i]);
            }
        }
    }
}

