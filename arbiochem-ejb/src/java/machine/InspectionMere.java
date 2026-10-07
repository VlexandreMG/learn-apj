package machine;

import bean.CGenUtil;
import bean.ClassMAPTable;
import bean.ClassMere;
import maintenance.inspection.HistoriqueInspectionFille;
import maintenance.inspection.HistoriqueInspectionMere;
import maintenance.ressources.IngredientMaintenance;
import vente.NumeroFacture;
import vente.VenteDetails;

import java.sql.Connection;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class InspectionMere extends ClassMere {
    private String id;
    private Date daty;
    private String heure;
    private String idInspecteur;
    private String idElement;
    private String remarque;

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



    public InspectionMere() throws Exception {
        this.setNomTable("INSPECTIONMERE");
        this.setNomClasseFille("machine.InspectionFille");
        this.setLiaisonFille("idMere");
    }

    @Override
    public String getLiaisonFille() {
        return "idMere";
    }

    public String getNomClasseFille() {
        return "machine.InspectionFille";
    }

    @Override
    public int updateToTableWithHisto(String u, Connection c) throws Exception {
        IngredientMaintenance element =  (IngredientMaintenance)new IngredientMaintenance().getById(this.getIdElement(),"AS_INGREDIENT_MAINTENANCE",c);
        InspectionMere ins = (InspectionMere)this;
        InspectionFille[] insF = (InspectionFille[])this.getFille(null,c,"");
        HistoriqueInspectionMere hist = new HistoriqueInspectionMere();
        hist.setDaty(ins.getDaty());
        hist.setHeure(ins.getHeure());
        hist.setRemarque(ins.getRemarque());
        hist.setIdInspecteur(ins.getIdInspecteur());
        hist.setIdElement(ins.getIdElement());
        HistoriqueInspectionFille[] hisF = new HistoriqueInspectionFille[insF.length];
        for(int j=0;j<insF.length;j++){
            hisF[j]=new HistoriqueInspectionFille();
            hisF[j].setIdElement(insF[j].getIdElement());
            hisF[j].setEtatInspection(insF[j].getIdEtatInspection());
            hisF[j].setRemarque(insF[j].getRemarque());
        }
        hist.setFille(hisF);
        hist.createObject(u,c);
        return super.updateToTableWithHisto(u, c);
    }
    public boolean checkHistoInspectionExiste(Connection c)throws Exception{
        boolean val =true;
        IngredientMaintenance aim = (IngredientMaintenance)new IngredientMaintenance().getById(this.getIdElement(),null,c);
        InspectionMere im = aim.getInspectionMere();
        if(im==null) val=false;
        return val;
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("ISPM","getSeqInspectionMere");
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
        InspectionMere p = (InspectionMere) super.createObject(u, c);
        if(this.checkHistoInspectionExiste(c)==false) {
            int updateHistoriqueInspection = p.updateToTableWithHisto(u,c);
        }
        return p;
    }
}

