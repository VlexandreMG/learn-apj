package ferme.aliment;

import java.sql.Connection;
import bean.ClassMere;
import java.sql.Date;

/**
 *
 * @author Safidy
 */
public class DistributionAliment extends ClassMere {
    private String id;
    private String idFerme,idLot;
    private Date daty;

    public DistributionAliment()throws Exception {
        super.setNomTable("distributionAliment");
        this.setNomClasseFille("ferme.aliment.DistributionAlimentDetail");
        this.setLiaisonFille("idMere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("DSTA", "GETSEQDISTRIBUTIONALIMENT");
        this.setId(this.makePK(c));
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
    public String getNomClasseFille() {
        return "ferme.aliment.DistributionAlimentDetail";
    }

    @Override
    public String getLiaisonFille() {
        return "idMere";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdFerme() {
        return idFerme;
    }

    public void setIdFerme(String idFerme) {
        this.idFerme = idFerme;
    }

    public String getIdLot() {
        return idLot;
    }

    public void setIdLot(String idLot) {
        this.idLot = idLot;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }
}