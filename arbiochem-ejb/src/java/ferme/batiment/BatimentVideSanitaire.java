package ferme.batiment;

import bean.ClassMere;
import java.sql.Connection;
import java.sql.Date;

public class BatimentVideSanitaire extends ClassMere {
    private String id;
    private String idbatiment;
    private String idresponsable;
    private Date daty;
    private String heuredebut;
    private String heurefin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdbatiment() {
        return idbatiment;
    }

    public void setIdbatiment(String idbatiment) {
        if(this.getMode().compareToIgnoreCase("modif") == 0) {
            if (idbatiment == null || idbatiment.isEmpty()) {
                throw new IllegalArgumentException("Veuillez specifier le b\u00E2timent");
            }
        }
        this.idbatiment = idbatiment;
    }

    public String getIdresponsable() {
        return idresponsable;
    }

    public void setIdresponsable(String idresponsable) {
        this.idresponsable = idresponsable;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public String getHeuredebut() {
        return heuredebut;
    }

    public void setHeuredebut(String heuredebut) {
        if(this.getMode().compareToIgnoreCase("modif") == 0) {
            if (heuredebut == null || heuredebut.isEmpty()) {
                throw new IllegalArgumentException("Veuillez specifier l'heure de d\u00E9but");
            }
        }
        this.heuredebut = heuredebut;
    }

    public String getHeurefin() {
        return heurefin;
    }

    public void setHeurefin(String heurefin) {
        if(this.getMode().compareToIgnoreCase("modif") == 0) {
            if (heurefin == null || heurefin.isEmpty()) {
                throw new IllegalArgumentException("Veuillez specifier l'heure de fin");
            }
        }
        this.heurefin = heurefin;
    }



    public BatimentVideSanitaire() throws Exception {
        this.setNomTable("BATIMENTVIDESANITAIRE");
        this.setNomClasseFille("ferme.batiment.BatimentVideSanitaireDetail");
        this.setLiaisonFille("idmere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("BVS","GETSEQBATIMENTVIDESANITAIRE");
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

