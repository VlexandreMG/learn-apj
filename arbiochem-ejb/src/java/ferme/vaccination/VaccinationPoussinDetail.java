package ferme.vaccination;

import java.sql.Connection;
import bean.ClassFille;
import java.sql.Date;

/**
 *
 * @author Safidy
 */
public class VaccinationPoussinDetail extends ClassFille {

    private String id;
    private String idMere, idBatiment, idParquet, idTypeVaccination, idMaladiePoussin, idVaccin, idModeAdministration;
    private double qteDose;
    private String heureDebut, heureFin;
    private Date dateExpiration;

    public VaccinationPoussinDetail() throws Exception {
        super.setNomTable("VACCINATIONPOUSSINDETAIL");
        this.setNomClasseMere("ferme.vaccination.VaccinationPoussin");
        this.setLiaisonMere("idMere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("VACPF", "GETSEQVACCINATIONPOUSSINDETAIL");
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
        return "ferme.vaccination.VaccinationPoussin";
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

    public String getIdTypeVaccination() {
        return idTypeVaccination;
    }

    public void setIdTypeVaccination(String idTypeVaccination) {
        this.idTypeVaccination = idTypeVaccination;
    }

    public String getIdMaladiePoussin() {
        return idMaladiePoussin;
    }

    public void setIdMaladiePoussin(String idMaladiePoussin) {
        this.idMaladiePoussin = idMaladiePoussin;
    }

    public String getIdVaccin() {
        return idVaccin;
    }

    public void setIdVaccin(String idVaccin) {
        this.idVaccin = idVaccin;
    }

    public String getIdModeAdministration() {
        return idModeAdministration;
    }

    public void setIdModeAdministration(String idModeAdministration) {
        this.idModeAdministration = idModeAdministration;
    }

    public double getQteDose() {
        return qteDose;
    }

    public void setQteDose(double qteDose) {
        this.qteDose = qteDose;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

}