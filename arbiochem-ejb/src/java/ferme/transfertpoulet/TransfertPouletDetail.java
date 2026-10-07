package ferme.transfertpoulet;

import java.sql.Connection;
import bean.ClassFille;

/**
 *
 * @author Safidy
 */
public class TransfertPouletDetail extends ClassFille {
    private String id;
    private String idMere,idSexe,idFermeDepart,idBatimentDepart,idParquetDepart,idFermeArrive,idBatimentArrive,idParquetArrive,idControleur,idChauffeur,idVehicule;
    private double qte;

    public TransfertPouletDetail()throws Exception {
        super.setNomTable("TransfertPouletDetail");
        this.setNomClasseMere("ferme.transfertpoulet.TransfertPoulet");
        this.setLiaisonMere("idMere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("TRSPF", "GETSEQTRANSFERTPOULETDETAIL");
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
        return "ferme.transfertpoulet.TransfertPoulet";
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

    public String getIdSexe() {
        return idSexe;
    }

    public void setIdSexe(String idSexe) {
        this.idSexe = idSexe;
    }

    public String getIdFermeDepart() {
        return idFermeDepart;
    }

    public void setIdFermeDepart(String idFermeDepart) {
        this.idFermeDepart = idFermeDepart;
    }

    public String getIdBatimentDepart() {
        return idBatimentDepart;
    }

    public void setIdBatimentDepart(String idBatimentDepart) {
        this.idBatimentDepart = idBatimentDepart;
    }

    public String getIdParquetDepart() {
        return idParquetDepart;
    }

    public void setIdParquetDepart(String idParquetDepart) {
        this.idParquetDepart = idParquetDepart;
    }

    public String getIdFermeArrive() {
        return idFermeArrive;
    }

    public void setIdFermeArrive(String idFermeArrive) {
        this.idFermeArrive = idFermeArrive;
    }

    public String getIdBatimentArrive() {
        return idBatimentArrive;
    }

    public void setIdBatimentArrive(String idBatimentArrive) {
        this.idBatimentArrive = idBatimentArrive;
    }

    public String getIdParquetArrive() {
        return idParquetArrive;
    }

    public void setIdParquetArrive(String idParquetArrive) {
        this.idParquetArrive = idParquetArrive;
    }

    public String getIdControleur() {
        return idControleur;
    }

    public void setIdControleur(String idControleur) {
        this.idControleur = idControleur;
    }

    public String getIdChauffeur() {
        return idChauffeur;
    }

    public void setIdChauffeur(String idChauffeur) {
        this.idChauffeur = idChauffeur;
    }

    public String getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(String idVehicule) {
        this.idVehicule = idVehicule;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }
}
