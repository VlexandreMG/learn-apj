package rapprochement;

public class ReleverDetailCpl extends ReleverDetail {
    private String idCaisse, compte;
    private int mouvement;

    public ReleverDetailCpl() {
        super();
        setNomTable("ReleverDetailCpl");
    }

    public int getMouvement() {
        return mouvement;
    }

    public void setMouvement(int mouvement) {
        this.mouvement = mouvement;
    }

    public String getIdCaisse() {
        return idCaisse;
    }

    public void setIdCaisse(String idCaisse) {
        this.idCaisse = idCaisse;
    }

    public String getCompte() {
        return compte;
    }

    public void setCompte(String compte) {
        this.compte = compte;
    }
}
