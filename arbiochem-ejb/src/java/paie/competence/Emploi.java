package paie.competence;

import bean.TypeObjet;

import java.sql.Connection;

public class Emploi extends TypeObjet {
    private String idMetier;
    public Emploi(){
        super.setNomTable("EMPLOI");
    }
    public String getIdMetier() {
        return idMetier;
    }

    public void setIdMetier(String idMetier) {
        this.idMetier = idMetier;
    }
    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("EM","getSeqseqemploi");
        this.setId(makePK(c));
    }
    @Override
    public String getTuppleID() {
        return id;
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


    @Override
    public String getAttributIDName() {
        return "id";
    }
}
