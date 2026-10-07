package paie.accident;

public class ArretTravailLib extends ArretTravail {
    private String nomPersonnel, id_Personnel, matricule, dateAccident;

    public ArretTravailLib() throws Exception {
        this.setNomTable("V_ARRETTRAVAIL_LIB");
    }

    public String getNomPersonnel() {
        return nomPersonnel;
    }

    public void setNomPersonnel(String nomPersonnel) {
        this.nomPersonnel = nomPersonnel;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getDateAccident() {
        return dateAccident;
    }

    public void setDateAccident(String dateAccident) {
        this.dateAccident = dateAccident;
    }

    public String getId_Personnel() {
        return id_Personnel;
    }

    public void setId_Personnel(String id_Personnel) {
        this.id_Personnel = id_Personnel;
    }
}
