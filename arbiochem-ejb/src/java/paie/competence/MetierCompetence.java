package paie.competence;

import bean.ClassFille;

import java.sql.Connection;

public class MetierCompetence extends ClassFille {
    private String id;
    private String idmetier;
    private String idcompetence;
    private int niveaurequis;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdmetier() {
        return idmetier;
    }

    public void setIdmetier(String idmetier) {
        this.idmetier = idmetier;
    }

    public String getIdcompetence() {
        return idcompetence;
    }

    public void setIdcompetence(String idcompetence) {
        this.idcompetence = idcompetence;
    }

    public int getNiveaurequis() {
        return niveaurequis;
    }

    public void setNiveaurequis(int niveaurequis) {
        this.niveaurequis = niveaurequis;
    }


    @Override
    public String getNomClasseMere() {
        return "paie.competence.Metier";
    }

    @Override
    public String getLiaisonMere() {
        return "idMetier";
    }

    public MetierCompetence() throws Exception {
        this.setNomTable("METIER_COMPETENCE");
        this.setNomClasseMere("paie.competence.Metier");
        this.setLiaisonMere("idmetier");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("MTC","getSeqMetierCompetence");
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
}

