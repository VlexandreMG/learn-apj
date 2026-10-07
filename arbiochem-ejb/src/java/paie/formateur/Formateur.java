package paie.formateur;

import bean.ClassMAPTable;

import java.sql.Connection;

public class Formateur extends ClassMAPTable {
    private String id;
    private String libelle;
    private String email;
    private String telephone;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }



    public Formateur() throws Exception {
        this.setNomTable("FORMATEUR");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("FOR","GET_SEQ_FORMATEUR");
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
        String[] motCles={"id","libelle"};
        return motCles;
    }
    @Override
    public String[] getValMotCles() {
	 String[] valMotCles={"id","libelle"};
        return valMotCles;
    }
}

