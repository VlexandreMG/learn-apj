package paie.competence;

import bean.ClassMAPTable;

import java.sql.Connection;

public class PersonnelCompetence extends ClassMAPTable {
    private String id;
    private String idpersonnel;
    private String idcompetence;
    private int niveau;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdpersonnel() {
        return idpersonnel;
    }

    public void setIdpersonnel(String idpersonnel) {
        this.idpersonnel = idpersonnel;
    }

    public String getIdcompetence() {
        return idcompetence;
    }

    public void setIdcompetence(String idcompetence) {
        this.idcompetence = idcompetence;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }



    public PersonnelCompetence() throws Exception {
        this.setNomTable("PERSONNEL_COMPETENCE");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("PRSC","getSeqPersonnelCompetence");
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

