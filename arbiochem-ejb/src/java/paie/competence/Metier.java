package paie.competence;

import bean.ClassMere;

import java.sql.Connection;

public class Metier extends ClassMere {
    private String id;
    private String val;
    private String desce;
    private String idCodeRome;
    private String metierLib;
    private String idFonction;

    public String getIdFonction() {
        return idFonction;
    }

    public void setIdFonction(String idFonction) {
        this.idFonction = idFonction;
    }

    public String getMetierLib() {
        return metierLib;
    }

    public void setMetierLib(String metierLib) {
        this.metierLib = metierLib;
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

    public String getIdCodeRome() {
        return idCodeRome;
    }

    public void setIdCodeRome(String idCodeRome) {
        this.idCodeRome = idCodeRome;
    }

    public Metier() throws Exception {
        this.setNomTable("METIER");
        this.setNomClasseFille("paie.competence.MetierCompetence");
        this.setLiaisonFille("idmetier");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("MT","getSeqMetier");
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
        String[] motCles={"id","val", "desce"};
        return motCles;
    }
    @Override
    public String[] getValMotCles() {
        String[] valMotCles={"id","val", "desce"};
        return valMotCles;
    }
}

