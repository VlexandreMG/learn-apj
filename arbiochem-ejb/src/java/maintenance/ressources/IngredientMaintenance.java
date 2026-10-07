package maintenance.ressources;

import bean.ClassMAPTable;
import caisse.MouvementCaisseFille;
import machine.ElementInspection;
import machine.ElementInspectionMachine;
import machine.InspectionFille;
import machine.InspectionMere;
import mg.cnaps.compta.BilanSection;
import mg.cnaps.compta.EtatSortie;
import produits.Ingredients;
import utils.ConstanteAsync;
import maintenance.utils.ConstanteMaintenance;
import utils.ConstanteSocobis;
import utilitaire.UtilDB;

import java.sql.Connection;
import java.sql.Date;
import bean.CGenUtil;

public class IngredientMaintenance extends ClassMAPTable {
    private String id;
    private String libelle;
    private String idEntite;
    private String referenceObjet;
    private String marqueObjet;
    private String modeleObjet;
    private String numeroSerieObjet;
    private String descriptionObjet;
    private String qualiteObjet;
    private String etatObjet;
    private String localisationObjet;
    private String puissanceObjet;
    private String observationObjet;
    private String typeRattachement;
    private String idIngredient,idLigne,idDepartement;
    private double estEngin;
    private Date dateAquisition;
    private double pu;

    public String getIdDepartement() {
        return idDepartement;
    }

    public void setIdDepartement(String idDepartement) {
        this.idDepartement = idDepartement;
    }

    public Date getDateAquisition() {
        return this.dateAquisition;
    }

    public void setDateAquisition(Date dateAquisition) {
        this.dateAquisition = dateAquisition;
    }

    public String getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(String idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getReferenceObjet() {
        return referenceObjet;
    }

    public void setReferenceObjet(String referenceObjet) {
        this.referenceObjet = referenceObjet;
    }

    public String getIdEntite() {
        return idEntite;
    }

    public void setIdEntite(String idEntite) {
        this.idEntite = idEntite;
    }

    public String getMarqueObjet() {
        return marqueObjet;
    }

    public void setMarqueObjet(String marqueObjet) {
        this.marqueObjet = marqueObjet;
    }

    public String getModeleObjet() {
        return modeleObjet;
    }

    public void setModeleObjet(String modeleObjet) {
        this.modeleObjet = modeleObjet;
    }

    public String getNumeroSerieObjet() {
        return numeroSerieObjet;
    }

    public void setNumeroSerieObjet(String numeroSerieObjet) {
        this.numeroSerieObjet = numeroSerieObjet;
    }

    public String getDescriptionObjet() {
        return descriptionObjet;
    }

    public void setDescriptionObjet(String descriptionObjet) {
        this.descriptionObjet = descriptionObjet;
    }

    public String getQualiteObjet() {
        return qualiteObjet;
    }

    public void setQualiteObjet(String qualiteObjet) {
        this.qualiteObjet = qualiteObjet;
    }

    public String getEtatObjet() {
        return etatObjet;
    }

    public void setEtatObjet(String etatObjet) {
        this.etatObjet = etatObjet;
    }

    public String getLocalisationObjet() {
        return localisationObjet;
    }

    public void setLocalisationObjet(String localisationObjet) {
        this.localisationObjet = localisationObjet;
    }

    public String getPuissanceObjet() {
        return puissanceObjet;
    }

    public void setPuissanceObjet(String puissanceObjet) {
        this.puissanceObjet = puissanceObjet;
    }

    public String getObservationObjet() {
        return observationObjet;
    }

    public void setObservationObjet(String observationObjet) {
        this.observationObjet = observationObjet;
    }

    public String getTypeRattachement() {
        return typeRattachement;
    }

    public void setTypeRattachement(String typeRattachement) {
        this.typeRattachement = typeRattachement;
    }

    public double getEstEngin() {
        return estEngin;
    }

    public void setEstEngin(double estEngin) {
        this.estEngin = estEngin;
    }

    public IngredientMaintenance() {
        setNomTable("AS_INGREDIENT_MAINTENANCE");
    }
    public IngredientMaintenance(String nomTable) {
        setNomTable(nomTable);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("INM", "GET_SEQ_ING_MAIN");
        this.setId(makePK(c));
    }

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) {
        this.pu = pu;
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
    public ClassMAPTable createObject(String u , Connection c) throws Exception {
        Ingredients i = createIngredients();
        i = (Ingredients) i.createObject(u, c);
        this.setIdIngredient(i.getId());
        return super.createObject(u, c);
    }

    public Ingredients createIngredients()throws Exception {
        Ingredients i = new Ingredients();
        i.setLibelle(this.getLibelle());
//        i.setIdNature("");
        i.setCategorieIngredient(ConstanteMaintenance.ID_CATEGORIE_MAINTENANCE);
        i.setPu(this.getPu());
        i.setCompte_vente("701110");
        i.setCompte_achat("62004");
        i.setParfums(this.getIdDepartement());
//        i.setCompte_achat(ConstanteSocobis.);
        return i;
    }
    public String[] getMotCles() {
        return new String[]{ "id","idIngredient","libelle"};
    }

    public String[] getValMotCles() {
        String[] motCles={"id","idIngredient","libelle"};
        return motCles;
    }

    public String getIdLigne() {
        return idLigne;
    }

    public void setIdLigne(String idLigne) {
        this.idLigne = idLigne;
    }

    ElementInspection[] getElementsInspection() throws Exception {
        Connection c=null;
        boolean estOuvert=false;
        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                c.setAutoCommit(false);
                estOuvert = true;
            }
            ElementInspectionMachine ei = new ElementInspectionMachine();
            ei.setIdmachine(this.getId());
            ElementInspectionMachine[]  eim = (ElementInspectionMachine[]) CGenUtil.rechercher(ei ,null,null,c," ");
            ElementInspection[] val =  new ElementInspection[eim.length];
            for (int i = 0; i < eim.length; i++) {
                val[i]=(ElementInspection)new ElementInspection().getById(eim[i].getIdelementinspection(), "ElementInspection", c);
            }
            return val;
        } catch (Exception e) {
            if(estOuvert) c.rollback();
            throw e;
        } finally {
            if(estOuvert) c.close();
        }
    }
    public InspectionMere genererInspectionMere() throws Exception {
        InspectionMere insm = new InspectionMere();
        insm.setIdElement(this.getId());
        insm.setRemarque("Inspection de l'element "+this.getId());
        return insm;
    }
    public InspectionMere getInspectionMere() throws Exception {
        InspectionMere val=null;
        Connection c=null;
        boolean estOuvert=false;
        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                c.setAutoCommit(false);
                estOuvert = true;
            }
            InspectionMere insm = new InspectionMere();
            insm.setIdElement(this.getId());
            InspectionMere[]  eim = (InspectionMere[]) CGenUtil.rechercher(insm ,null,null,c," ");
            if(eim.length>0){
                val=eim[0];
            }
        } catch (Exception e) {
            if(estOuvert) c.rollback();
            throw e;
        } finally {
            if(estOuvert) c.close();
        }
        return val;
    }
    public InspectionFille[] getInspectionFille() throws Exception {
        ElementInspection[] ei = this.getElementsInspection();
        InspectionFille[] val =  new InspectionFille[ei.length];
        for (int i = 0; i < ei.length; i++) {
            val[i]=new InspectionFille();
            val[i].setIdElement(ei[i].getId());
        }
        return val;
    }
}
