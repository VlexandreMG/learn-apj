package paie.recrutement;

import bean.ClassMAPTable;

import java.sql.Connection;
import java.sql.Date;

public class Candidat extends ClassMAPTable {
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private Date date_naissance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }



    public Candidat() throws Exception {
        this.setNomTable("CANDIDAT");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CAN","getseq_candidat");
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
        String[] motCles={"id","nom","prenom"};
        return motCles;
    }
    @Override
    public String[] getValMotCles() {
	 String[] valMotCles={"id","nom","prenom"};
        return valMotCles;
    }
}

