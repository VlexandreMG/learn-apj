package paie.avance;

import bean.ClassMAPTable;
import bean.ClassMere;
import caisse.MvtCaisse;
import facture.tr.Traite;
import java.sql.Connection;
import java.sql.Date;

public class PaiementAvance2 extends ClassMere {
    private String id,designation,idCaisse,idVenteDetail,idVirement,idOp,idOrigine, idtraite,idTiers;
    protected String idDevise;
    private double debit,credit,taux;
    private Date daty, datycomptabilisation;
    private String idPrevision;
    private String compte;
    private String idModePaiement;
    private int etatversement;
    private Traite traite;
    private String idmvtcaissemere;
    private String reference;
    private String idTypePaiement;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getIdCaisse() {
        return idCaisse;
    }

    public void setIdCaisse(String idCaisse) {
        this.idCaisse = idCaisse;
    }

    public String getIdVenteDetail() {
        return idVenteDetail;
    }

    public void setIdVenteDetail(String idVenteDetail) {
        this.idVenteDetail = idVenteDetail;
    }

    public String getIdVirement() {
        return idVirement;
    }

    public void setIdVirement(String idVirement) {
        this.idVirement = idVirement;
    }

    public String getIdOp() {
        return idOp;
    }

    public void setIdOp(String idOp) {
        this.idOp = idOp;
    }

    public String getIdOrigine() {
        return idOrigine;
    }

    public void setIdOrigine(String idOrigine) {
        this.idOrigine = idOrigine;
    }

    public String getIdtraite() {
        return idtraite;
    }

    public void setIdtraite(String idtraite) {
        this.idtraite = idtraite;
    }

    public String getIdTiers() {
        return idTiers;
    }

    public void setIdTiers(String idTiers) {
        this.idTiers = idTiers;
    }

    public String getIdDevise() {
        return idDevise;
    }

    public void setIdDevise(String idDevise) {
        this.idDevise = idDevise;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public Date getDatycomptabilisation() {
        return datycomptabilisation;
    }

    public void setDatycomptabilisation(Date datycomptabilisation) {
        this.datycomptabilisation = datycomptabilisation;
    }

    public String getIdPrevision() {
        return idPrevision;
    }

    public void setIdPrevision(String idPrevision) {
        this.idPrevision = idPrevision;
    }

    public String getCompte() {
        return compte;
    }

    public void setCompte(String compte) {
        this.compte = compte;
    }

    public String getIdModePaiement() {
        return idModePaiement;
    }

    public void setIdModePaiement(String idModePaiement) {
        this.idModePaiement = idModePaiement;
    }

    public int getEtatversement() {
        return etatversement;
    }

    public void setEtatversement(int etatversement) {
        this.etatversement = etatversement;
    }

    public Traite getTraite() {
        return traite;
    }

    public void setTraite(Traite traite) {
        this.traite = traite;
    }

    public String getIdmvtcaissemere() {
        return idmvtcaissemere;
    }

    public void setIdmvtcaissemere(String idmvtcaissemere) {
        this.idmvtcaissemere = idmvtcaissemere;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getIdTypePaiement() {
        return idTypePaiement;
    }

    public void setIdTypePaiement(String idTypePaiement) {
        this.idTypePaiement = idTypePaiement;
    }

    public PaiementAvance2() throws Exception {
        this.setNomTable("MOUVEMENTCAISSE");
        this.setNomClasseFille("paie.avance.BilletageFille");
        this.setLiaisonFille("idMere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("MTC", "GETSEQMOUVEMENTCAISSE");
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
    public ClassMAPTable validerObject(String u, Connection c) throws Exception{
        // Validation du montant des billets
        validerMontantBillets();

        MvtCaisse mvtCaisse = new MvtCaisse();
        mvtCaisse.setDesignation(this.getDesignation());
        mvtCaisse.setIdCaisse(this.getIdCaisse());
        mvtCaisse.setIdVenteDetail(this.getIdVenteDetail());
        mvtCaisse.setIdVirement(this.getIdVirement());
        mvtCaisse.setIdOp(this.getIdOp());
        mvtCaisse.setIdOrigine(this.getIdOrigine());
        mvtCaisse.setIdtraite(this.getIdtraite());
        mvtCaisse.setIdTiers(this.getIdTiers());
        mvtCaisse.setIdDevise(this.getIdDevise());
        mvtCaisse.setDebit(this.getDebit());
        mvtCaisse.setCredit(this.getCredit());
        mvtCaisse.setTaux(this.getTaux());
        mvtCaisse.setDaty(this.getDaty());
        mvtCaisse.setDatycomptabilisation(this.getDatycomptabilisation());
        mvtCaisse.setIdPrevision(this.getIdPrevision());
        mvtCaisse.setCompte(this.getCompte());
        mvtCaisse.setIdModePaiement(this.getIdModePaiement());
        mvtCaisse.setEtatversement(this.getEtatversement());
        mvtCaisse.setTraite(this.getTraite());
        mvtCaisse.setIdmvtcaissemere(this.getIdmvtcaissemere());
        mvtCaisse.setReference(this.getReference());
        mvtCaisse.validerObject(u,c);
        PaiementAvance2 paiementAvance2 = new PaiementAvance2();
        paiementAvance2.setId(mvtCaisse.getId());
        return paiementAvance2;
    }

    @Override
    public ClassMAPTable createObject(String u, Connection c) throws Exception{
        // Validation du montant des billets
        validerMontantBillets();

        MvtCaisse mvtCaisse = new MvtCaisse();
        mvtCaisse.setDesignation(this.getDesignation());
        mvtCaisse.setIdCaisse(this.getIdCaisse());
        mvtCaisse.setIdVenteDetail(this.getIdVenteDetail());
        mvtCaisse.setIdVirement(this.getIdVirement());
        mvtCaisse.setIdOp(this.getIdOp());
        mvtCaisse.setIdOrigine(this.getIdOrigine());
        mvtCaisse.setIdtraite(this.getIdtraite());
        mvtCaisse.setIdTiers(this.getIdTiers());
        mvtCaisse.setIdDevise(this.getIdDevise());
        mvtCaisse.setDebit(this.getDebit());
        mvtCaisse.setCredit(this.getCredit());
        mvtCaisse.setTaux(this.getTaux());
        mvtCaisse.setDaty(this.getDaty());
        mvtCaisse.setDatycomptabilisation(this.getDatycomptabilisation());
        mvtCaisse.setIdPrevision(this.getIdPrevision());
        mvtCaisse.setCompte(this.getCompte());
        mvtCaisse.setIdModePaiement(this.getIdModePaiement());
        mvtCaisse.setEtatversement(this.getEtatversement());
        mvtCaisse.setTraite(this.getTraite());
        mvtCaisse.setIdmvtcaissemere(this.getIdmvtcaissemere());
        mvtCaisse.setReference(this.getReference());

        mvtCaisse.createObject(u,c);
        PaiementAvance2 paiementAvance2 = new PaiementAvance2();
        paiementAvance2.setId(mvtCaisse.getId());

        for (int i = 0; i < this.getFille().length ; i++) {
            BilletageFille billet = (BilletageFille) this.getFille()[i];
            billet.setIdMere(mvtCaisse.getId());
            billet.createObject(u,c);
        }

        Avance tempAv = new Avance();
        Avance av = tempAv.getAvance(this.getIdOrigine());
        av.setEtat(21); // Etat "Payée"
        av.updateObject(u,c);
        return av;
    }

    /**
     * Valide que la somme des billets correspond au montant à payer
     */
    private void validerMontantBillets() throws Exception {
        if (this.getFille() != null) {
            double sommeBillets = 0;
            for (int i = 0; i < this.getFille().length; i++) {
                BilletageFille billetage = (BilletageFille) this.getFille()[i];
                if (billetage != null && billetage.getNombreBillet() > 0) {
                    sommeBillets += billetage.getBillet() * billetage.getNombreBillet();
                }
            }

            // Vérification avec tolérance pour les erreurs d'arrondi
            if (Math.abs(sommeBillets - this.getDebit()) > 0.01) {
                throw new Exception("ERREUR: Le montant total des billets (" + sommeBillets +
                    " Ar) ne correspond pas au montant à payer (" + this.getDebit() + " Ar)");
            }

            if (sommeBillets == 0) {
                throw new Exception("ERREUR: Aucun billet n'a été saisi");
            }
        }
    }
}
