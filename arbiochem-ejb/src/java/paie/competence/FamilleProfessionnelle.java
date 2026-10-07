package paie.competence;

import bean.TypeObjet;

import java.sql.Connection;

public class FamilleProfessionnelle extends TypeObjet {
    private String id,val,desce;
    public FamilleProfessionnelle() throws Exception {
        this.setNomTable("famille_professionnelle");
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getDesce() {
        return desce;
    }

    public void setDesce(String desce) {
        this.desce = desce;
    }
    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("FP","getSeqFamilleProfessionnelle");
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
    public String[] getMotCles() {
        String[] motCles={"id","val"};
        return motCles;
    }
    @Override
    public String[] getValMotCles() {
        String[] motCles={"id","val"};
        return motCles;
    }
}