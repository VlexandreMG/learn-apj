package ferme.batiment;

import bean.ClassFille;
import java.sql.Connection;

public class BatimentVideSanitaireDetail extends ClassFille {
    private String id;
    private String idmere;
    private String idproduit;
    private int qte;
    private String remarque;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdmere() {
        return idmere;
    }

    public void setIdmere(String idmere) {
        this.idmere = idmere;
    }

    public String getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(String idproduit) {
        if(this.getMode().compareToIgnoreCase("modif") == 0) {
            if (idproduit == null || idproduit.isEmpty()) {
                throw new IllegalArgumentException("Veuillez fournir au moins un produit");
            }
        }
        this.idproduit = idproduit;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        if(this.getMode().compareToIgnoreCase("modif") == 0) {
            if (qte == 0) {
                throw new IllegalArgumentException("La quantit\u00E9 ne peut pas \u00EAtre nulle");
            }
            if (qte < 0) {
                throw new IllegalArgumentException("La quantit\u00E9 ne peut pas \u00EAtre n\u00E9gative");
            }
        }
        this.qte = qte;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }


    @Override
    public String getNomClasseMere() {
        return "ferme.batiment.BatimentVideSanitaire";
    }

    @Override
    public String getLiaisonMere() {
        return "idmere";
    }

    public BatimentVideSanitaireDetail() throws Exception {
        this.setNomTable("BATIMENTVIDESANITAIREDETAIL");
        this.setNomClasseMere("ferme.batiment.BatimentVideSanitaire");
        this.setLiaisonMere("idmere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("BVSF","GETSEQBATVIDSANITDET");
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

