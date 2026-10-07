package maintenance.ressources;

import bean.ClassMAPTable;
import bean.CGenUtil;
import maintenance.travaux.TravauxCpl;
import mg.cnaps.compta.BilanSection;
import stock.MvtStockFille;

import java.sql.Connection;

public class ConsommableMachine extends ClassMAPTable {
    private String id;
    private String idMachine;
    private String idConsommable;
    private String idTypeMaintenance;
    private double qte;
    private String idUnite;
    private String frequence;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMachine() {
        return idMachine;
    }

    public void setIdMachine(String idMachine) {
        this.idMachine = idMachine;
    }

    public String getIdConsommable() {
        return idConsommable;
    }

    public void setIdConsommable(String idConsommable) {
        this.idConsommable = idConsommable;
    }

    public String getIdTypeMaintenance() {
        return idTypeMaintenance;
    }

    public void setIdTypeMaintenance(String idTypeMaintenance) {
        this.idTypeMaintenance = idTypeMaintenance;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public String getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(String idUnite) {
        this.idUnite = idUnite;
    }

    public String getFrequence() {
        return frequence;
    }

    public void setFrequence(String frequence) {
        this.frequence = frequence;
    }

    public ConsommableMachine() {
        this.setNomTable("CONSOMMABLEMACHINE");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CONSM","GETSEQCONSOMMABLEMACHINE");
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

    public MvtStockFille[] genererMvtStockFille(String idTravaux) throws Exception {
        TravauxCpl travaux = (TravauxCpl) new TravauxCpl().getById(idTravaux,"TRAVAUXCPL",null);
        ConsommableMachineLib cm= new ConsommableMachineLib();
        cm.setIdMachine(travaux.getIdIngredientMaintenance());
        ConsommableMachineLib[] listeConsommables =  (ConsommableMachineLib[])CGenUtil.rechercher(cm, null, null, null, " ");
        MvtStockFille[] val = new MvtStockFille[listeConsommables.length];
        for (int i = 0; i < listeConsommables.length; i++) {
            val[i]=new MvtStockFille();
            val[i].setSortie(listeConsommables[i].getQte());
            val[i].setIdProduit(listeConsommables[i].getIdConsommable());
            val[i].setDesignation(listeConsommables[i].getIdConsommableLib());
        }
        return val;
    }
}

