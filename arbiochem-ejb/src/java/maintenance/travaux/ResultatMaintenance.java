package maintenance.travaux;
import bean.CGenUtil;
import bean.ClassMAPTable;
import maintenance.planning.Planning;
import maintenance.planning.DemandeTravaux;

import java.sql.Connection;
import java.sql.Date;

public class ResultatMaintenance extends ClassMAPTable {
    private String id;
    private Date daty;
    private String idordretravaux;
    private int idpersonnel;
    private String etatmachine;
    private String designation;
    private String prochaineetape;
    private Date prochainedaty;

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

    public String getIdordretravaux() {
        return idordretravaux;
    }

    public void setIdordretravaux(String idordretravaux) {
        this.idordretravaux = idordretravaux;
    }

    public int getIdpersonnel() {
        return idpersonnel;
    }

    public void setIdpersonnel(int idpersonnel) {
        this.idpersonnel = idpersonnel;
    }

    public String getEtatmachine() {
        return etatmachine;
    }

    public void setEtatmachine(String etatmachine) {
        this.etatmachine = etatmachine;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getProchaineetape() {
        return prochaineetape;
    }

    public void setProchaineetape(String prochaineetape) {
        this.prochaineetape = prochaineetape;
    }

    public Date getProchainedaty() {
        return prochainedaty;
    }

    public void setProchainedaty(Date prochainedaty) {
        this.prochainedaty = prochainedaty;
    }



    public ResultatMaintenance() throws Exception {
        this.setNomTable("RESULTATMAINTENANCE");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("RESM","get_seqresultatmaintenance");
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

    @Override
    public ClassMAPTable createObject(String u, Connection c) throws Exception {
        ClassMAPTable cl = super.createObject(u, c);
        setId(cl.getTuppleID());
        if (getProchaineetape() != null && !getProchaineetape().trim().isEmpty() && getProchainedaty() != null) {
            Planning p = creerPlanning();
            p.createObject(u, c);
        }

        return cl;
    }

    public Planning creerPlanning() throws Exception {
        Planning p = new Planning();
        OrdreTravauxFille otFIlle = this.getOtFille();
        DemandeTravaux demandeTravaux = this.getDemandeTravaux();
        p.setDatedebut(getProchainedaty());
        p.setDatefin(getProchainedaty());
        p.setIdSource(getId());
        p.setRefObjet("Prochaine etape de l'ordre travaux n " + getIdordretravaux());
        p.setEstPeriodique(0);
        if(otFIlle!=null){
            p.setIdMachine(otFIlle.getIdIngredients());
        }if(demandeTravaux!=null){
            p.setIdTypeMaintenance(demandeTravaux.getIdTypeMaintenance());
        }
        p.setFrequence(0);
        p.setDuree("1");
        p.setHeure("10:00");

        return p;
    }
    public OrdreTravauxFille getOtFille()throws Exception{
        OrdreTravauxFille val = null;
        OrdreTravauxFille tmp = new OrdreTravauxFille();
        tmp.setIdMere(this.getIdordretravaux());
        OrdreTravauxFille[] data = (OrdreTravauxFille[]) CGenUtil.rechercher(tmp, null, null, "");
        if(data!=null){
            val = data[0];
        }
        return val;
    }
    public DemandeTravaux getDemandeTravaux()throws Exception{
        DemandeTravaux val=null;
        OrdreTravaux ot = (OrdreTravaux)new OrdreTravaux().getById(this.getIdordretravaux(),"",null);
        DemandeTravaux tmp = new DemandeTravaux();
        tmp.setId(ot.getIdBc());
        DemandeTravaux[] data = (DemandeTravaux[]) CGenUtil.rechercher(tmp, null, null, "");
        if(data!=null){
            val = data[0];
        }
        return val;
    }
}

