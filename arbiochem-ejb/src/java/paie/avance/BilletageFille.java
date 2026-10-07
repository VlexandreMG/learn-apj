package paie.avance;

import bean.ClassFille;
import java.sql.Connection;

public class BilletageFille extends ClassFille {
    private String id;
    private String idMere;
    private int billet;
    private int nombreBillet;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMere() {
        return idMere;
    }

    public void setIdMere(String idMere) {
        this.idMere = idMere;
    }

    public int getBillet() {
        return billet;
    }

    public void setBillet(int billet) {
        this.billet = billet;
    }

    public int getNombreBillet() {
        return nombreBillet;
    }

    public void setNombreBillet(int nombreBillet) {
        this.nombreBillet = nombreBillet;
    }


    @Override
    public String getNomClasseMere() {
        return "paie.avance.PaiementAvance2";
    }

    @Override
    public String getLiaisonMere() {
        return "idMere";
    }

    public BilletageFille() throws Exception {
        this.setNomTable("BILLETAGEFILLE");
        this.setNomClasseMere("paie.avance.PaiementAvance2");
        this.setLiaisonMere("idMere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("BF","GETSEQBILLETAGEFILLE");
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

