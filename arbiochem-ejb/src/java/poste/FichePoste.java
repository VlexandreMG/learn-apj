package poste;

import bean.ClassMere;

import java.sql.Connection;
import java.sql.Date;
public class FichePoste extends ClassMere {
    private String id;
    private String iddepartement;
    private String idtypecontrat;
    private String titre;
    private String reference;
    private String description;
    private String missions;
    private String exigenceposte;
    private double salairemin;
    private double salairemax;
    private Date daty;
    private String lieuTravail;
    private String materielDocument;
    private String outils;
    private String conditionTravail;
    private String modeControle;
    private String idNiveauFormation;
    private String idPermis	;
    private String idFonction;
    private String idBu;
    private String autre;
    private String idCategoriePaie;
    private String idQualificationPaie;
    private String idFamilleProfessionel , idMetier , idEmploi , idCodeRome;

    public String getIdFamilleProfessionel() {
        return idFamilleProfessionel;
    }

    public void setIdFamilleProfessionel(String idFamilleProfessionel) {
        this.idFamilleProfessionel = idFamilleProfessionel;
    }

    public String getIdMetier() {
        return idMetier;
    }

    public void setIdMetier(String idMetier) {
        this.idMetier = idMetier;
    }

    public String getIdEmploi() {
        return idEmploi;
    }

    public void setIdEmploi(String idEmploi) {
        this.idEmploi = idEmploi;
    }

    public String getIdCodeRome() {
        return idCodeRome;
    }

    public void setIdCodeRome(String idCodeRome) {
        this.idCodeRome = idCodeRome;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIddepartement() {
        return iddepartement;
    }

    public void setIddepartement(String iddepartement) {
        this.iddepartement = iddepartement;
    }

    public String getIdtypecontrat() {
        return idtypecontrat;
    }

    public void setIdtypecontrat(String idtypecontrat) {
        this.idtypecontrat = idtypecontrat;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMissions() {
        return missions;
    }

    public void setMissions(String missions) {
        this.missions = missions;
    }

    public String getExigenceposte() {
        return exigenceposte;
    }

    public void setExigenceposte(String exigenceposte) {
        this.exigenceposte = exigenceposte;
    }

    public double getSalairemin() {
        return salairemin;
    }

    public void setSalairemin(double salairemin) {
        this.salairemin = salairemin;
    }

    public double getSalairemax() {
        return salairemax;
    }

    public void setSalairemax(double salairemax) {
        this.salairemax = salairemax;
    }


    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public String getIdBu() {
        return idBu;
    }

    public void setIdBu(String idBu) {
        this.idBu = idBu;
    }

    public FichePoste() throws Exception {
        this.setNomTable("FICHE_POSTE");
        this.setNomClasseFille("poste.FichePosteFille");
        this.setLiaisonFille("idficheposte");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("FPST","GET_SEQ_FICHE_POSTE");
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
    public String[] getMotCles() {
        String[] motCles={"id","titre"};
        return motCles;
    }
    @Override
    public String[] getValMotCles() {
	 String[] valMotCles={"id","titre"};
        return valMotCles;
    }

    public String getLieuTravail() {
        return lieuTravail;
    }

    public void setLieuTravail(String lieuTravail) {
        this.lieuTravail = lieuTravail;
    }

    public String getMaterielDocument() {
        return materielDocument;
    }

    public void setMaterielDocument(String materielDocument) {
        this.materielDocument = materielDocument;
    }

    public String getOutils() {
        return outils;
    }

    public void setOutils(String outils) {
        this.outils = outils;
    }

    public String getConditionTravail() {
        return conditionTravail;
    }

    public void setConditionTravail(String conditionTravail) {
        this.conditionTravail = conditionTravail;
    }

    public String getModeControle() {
        return modeControle;
    }

    public void setModeControle(String modeControle) {
        this.modeControle = modeControle;
    }

    public String getIdNiveauFormation() {
        return idNiveauFormation;
    }

    public void setIdNiveauFormation(String idNiveauFormation) {
        this.idNiveauFormation = idNiveauFormation;
    }

    public String getIdPermis() {
        return idPermis;
    }

    public void setIdPermis(String idPermis) {
        this.idPermis = idPermis;
    }

    public String getIdFonction() {
        return idFonction;
    }

    public void setIdFonction(String idFonction) {
        this.idFonction = idFonction;
    }

    public String getAutre() {
        return autre;
    }

    public void setAutre(String autre) {
        this.autre = autre;
    }

    public String getIdCategoriePaie() {
        return idCategoriePaie;
    }

    public void setIdCategoriePaie(String idCategoriePaie) {
        this.idCategoriePaie = idCategoriePaie;
    }

    public String getIdQualificationPaie() {
        return idQualificationPaie;
    }

    public void setIdQualificationPaie(String idQualificationPaie) {
        this.idQualificationPaie = idQualificationPaie;
    }
}

