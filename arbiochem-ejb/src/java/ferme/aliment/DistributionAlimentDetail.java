package ferme.aliment;

import java.sql.Connection;
import bean.ClassFille;

/**
 *
 * @author Safidy
 */
public class DistributionAlimentDetail extends ClassFille {
    private String id;
    private String idMere,idBatiment,idParquet,idAliment,idLotStock;
    private double qte,rationParTete;

    public DistributionAlimentDetail()throws Exception {
        super.setNomTable("distributionAlimentDetail");
        this.setNomClasseMere("ferme.aliment.DistributionAliment");
        this.setLiaisonMere("idMere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("DSTAF", "GETSEQDISTADETAIL");
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
    public String getNomClasseMere() {
        return "ferme.aliment.DistributionAliment";
    }

    @Override
    public String getLiaisonMere() {
        return "idMere";
    }

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

    public String getIdBatiment() {
        return idBatiment;
    }

    public void setIdBatiment(String idBatiment) {
        this.idBatiment = idBatiment;
    }

    public String getIdParquet() {
        return idParquet;
    }

    public void setIdParquet(String idParquet) {
        this.idParquet = idParquet;
    }

    public String getIdAliment() {
        return idAliment;
    }

    public void setIdAliment(String idAliment) {
        this.idAliment = idAliment;
    }

    public String getIdLotStock() {
        return idLotStock;
    }

    public void setIdLotStock(String idLotStock) {
        this.idLotStock = idLotStock;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public double getRationParTete() {
        return rationParTete;
    }

    public void setRationParTete(double rationParTete) {
        this.rationParTete = rationParTete;
    }
}
