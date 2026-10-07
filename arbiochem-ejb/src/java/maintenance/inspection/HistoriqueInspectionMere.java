package maintenance.inspection;
import bean.ClassMere;
import java.sql.Date;
import java.sql.Connection;

public class HistoriqueInspectionMere extends ClassMere{
    private String id,heure,idInspecteur,idElement,remarque;
    private Date daty;
    private int etat;
    public  HistoriqueInspectionMere()throws Exception {
        this.setNomTable("HistoriqueInspectionMere");
        this.setNomClasseFille("maintenance.inspection.HistoriqueInspectionFille");
        this.setLiaisonFille("idMere");
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
    public void construirePK(Connection c) throws Exception {
        this.preparePk("HIM", "getSeqHistoriqueInspectionMere");
        this.setId(makePK(c));
    }

    @Override
    public String getLiaisonFille() {
        return "idMere";
    }

    public String getNomClasseFille() {
        return "maintenance.inspection.HistoriqueInspectionFille";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getIdInspecteur() {
        return idInspecteur;
    }

    public void setIdInspecteur(String idInspecteur) {
        this.idInspecteur = idInspecteur;
    }

    public String getIdElement() {
        return idElement;
    }

    public void setIdElement(String idElement) {
        this.idElement = idElement;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
}
