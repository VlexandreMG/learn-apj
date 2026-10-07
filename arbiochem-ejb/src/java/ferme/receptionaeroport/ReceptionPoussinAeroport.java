package ferme.receptionaeroport;

import java.sql.Connection;
import bean.ClassMere;
import java.sql.Date;
import bean.ClassMAPTable;
/**
 *
 * @author Safidy
 */
public class ReceptionPoussinAeroport extends ClassMere {
    private String id,idLot,remarque, heureDepart, heureArrive;
    private Date daty;
    private double qteRecus;
    private int nbrCartonMale,nbrCartonFemelle,etat;
    public ReceptionPoussinAeroport()throws Exception {
        super.setNomTable("receptionPoussinAeroport");
        this.setNomClasseFille("ferme.receptionaeroport.ReceptionPoussinAeroportDetail");
        this.setLiaisonFille("idMere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("RPA", "GETSEQRECEPTIONPOUSSINAEROPORT");
        this.setId(this.makePK(c));
    }
    @Override
    public String getTuppleID() {
        return id;
    }

    public int getNbrCartonMale() {
        return nbrCartonMale;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setNbrCartonMale(int nbrCartonMale) {
        this.nbrCartonMale = nbrCartonMale;
    }

    public int getNbrCartonFemelle() {
        return nbrCartonFemelle;
    }

    public void setNbrCartonFemelle(int nbrCartonFemelle) {
        this.nbrCartonFemelle = nbrCartonFemelle;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }

    @Override
    public String getNomClasseFille() {
        return "ferme.receptionaeroport.ReceptionPoussinAeroportDetail";
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

    public String getIdLot() {
        return idLot;
    }

    public void setIdLot(String idLot) {
        this.idLot = idLot;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public double getQteRecus() {
        return qteRecus;
    }

    public void setQteRecus(double qteRecus) {
        this.qteRecus = qteRecus;
    }

    public String getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    public String getHeureArrive() {
        return heureArrive;
    }

    public void setHeureArrive(String heureArrive) {
        this.heureArrive = heureArrive;
    }

    @Override
    public ClassMAPTable createObject(String u, Connection c) throws Exception {
        this.checkQteFille();
        return super.createObject(u, c);
    }

    public void checkQteFille()throws Exception {
        ReceptionPoussinAeroportDetail[] fille = (ReceptionPoussinAeroportDetail[]) this.getFille();
        double qteFille = 0;
        for (ReceptionPoussinAeroportDetail f : fille) {
            qteFille += f.getQte();
        }
        if (this.getQteRecus() != qteFille)
            throw new Exception("La quantit\u00e9 totale en d\u00e9tails : (" + qteFille + ") est diff\u00e9rente de la quantit\u00e9 re\u00e7ue : (" + this.getQteRecus() + ")");
    }
}