package paie.cv;

import bean.ClassMAPTable;

import java.sql.Connection;

public class Diplome extends ClassMAPTable {
    private String id;
    private String libelle;
    private int niveau;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }



    public Diplome() throws Exception {
        this.setNomTable("DIPLOME");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("DP","getSeqDiplome");
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

