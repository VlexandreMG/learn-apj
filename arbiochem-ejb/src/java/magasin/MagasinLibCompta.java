package magasin;

public class MagasinLibCompta extends Magasin{
    String compte;
    String idMagasincompte;

    public String getCompte() {
        return compte;
    }

    public void setCompte(String compte) {
        this.compte = compte;
    }

    public String getIdMagasincompte() {
        return idMagasincompte;
    }

    public void setIdMagasincompte(String idMagasincompte) {
        this.idMagasincompte = idMagasincompte;
    }

    public MagasinLibCompta() {
        this.setNomTable("MAGASINLIBCOMPTA");
    }
}
