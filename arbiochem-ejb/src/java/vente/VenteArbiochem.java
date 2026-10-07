package vente;

import avoir.AvoirFC;
import avoir.AvoirFCFille;
import avoir.AvoirFCLib;
import bean.AdminGen;
import bean.CGenUtil;
import caisse.MvtCaisseCpl;
import client.Client;
import client.ClientArbiochem;
import constante.ConstanteEtat;
import paiement.LiaisonPaiement;
import ristourne.Ristourne;
import ristourne.RistourneDetails;
import utilitaire.UtilDB;
import utilitaire.Utilitaire;
import utils.ConstanteSocobis;
import utils.ConstanteVente;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VenteArbiochem extends Vente{

    @Override
    public String getNomClasseFille() {
        return "vente.VenteDetailsArbiochem";
    }

    public VenteLib[] getVenteParCliAvecReste(Connection c) throws Exception {
        VenteLib[] liste = null;

        liste = (VenteLib[]) CGenUtil.rechercher(new VenteLib(), null, null, c, " and idClient = '"+  this.getIdClient()+"' and montantreste > 0 and etat = 11");
        return liste;
    }

    public double getMontantresteClient(Connection c) throws Exception {
        VenteLib[] liste = getVenteParCliAvecReste(c);
        if(liste == null || liste.length == 0) return 0;

        double somme = AdminGen.calculSommeDouble(liste, "montantreste");
        return somme;
    }

    public ClientArbiochem getClientArbiochem(Connection c) throws Exception{
        ClientArbiochem client = new ClientArbiochem();
        ClientArbiochem[] clients = (ClientArbiochem[]) CGenUtil.rechercher(client,null,null,c, " and id = '"+this.getIdClient()+"'");
        if(clients.length > 0){
            return clients[0];
        }
        throw new Exception("Le client n'existe pas");
    }


    public LiaisonPaiement genererPaiementFactureParAvoirAutom(String u, Connection c,VenteLib vente) throws SQLException, Exception{
        LiaisonPaiement pFacture = null;
        boolean estOuvert = false;
        if(c==null){
            c = new UtilDB().GetConn();
            estOuvert = true;
            c.setAutoCommit(false);
        }
        try {
//            VenteLib vente = (VenteLib) new VenteLib().getById(this.getId(), "", c);
            String idVente = vente.getId();
            AvoirFCLib t = new AvoirFCLib();
            t.setNomTable("AVOIRFCLIB_CPL");
            AvoirFCLib[] details = (AvoirFCLib[]) CGenUtil.rechercher(t,null,null,c," AND idClient='"+vente.getIdClient()+"' and resteapayerar > 0 order by id asc");
            StringBuilder sb = new StringBuilder();
            double montantTotalReste = 0;
            double montantFacture = vente.getMontantreste();

            if(details.length>0){
                for(int i=0;i<details.length;i++){
                    if (montantTotalReste >= montantFacture) {
                        break;
                    }
                        montantTotalReste += details[i].getResteapayerar();
                        if (i > 0) sb.append(";");
                        sb.append(details[i].getId());
                }
            }else {
                return null;
            }
            double montantPaiement = Math.min(montantTotalReste, montantFacture);


            pFacture = new LiaisonPaiement();
            pFacture.setId2(idVente);
            pFacture.setId1(sb.toString());
            pFacture.setDatesaisie(Utilitaire.dateDuJourSql());
            pFacture.setMontant(montantPaiement);
            c.commit();
        } catch (Exception e) {
            if(estOuvert) c.rollback();
            throw e ;
        } finally {
            if(estOuvert) c.close();
        }

        return pFacture;
    }

    public String[] getAvanceClient(Connection c, String awhereBc, VenteLib vente) throws Exception{
        MvtCaisseCpl[] liste = null;

//        VenteLib vente = (VenteLib) new VenteLib().getById(this.getId(), "", c);

        liste = (MvtCaisseCpl[]) CGenUtil.rechercher(new MvtCaisseCpl(), null, null, c, " and idTiers = '"+vente.getIdClient()+"' and soldecredit>0 and etat>=8" + awhereBc);

        List<String> listeId = new ArrayList<String>();
        double montantTotalReste = 0;
        double montantFacture = vente.getMontantreste();

        if(liste!=null && liste.length>0){
            for(int i=0;i<liste.length;i++){
                if (montantTotalReste >= montantFacture) {
                    break;
                }
                listeId.add(liste[i].getId());
                montantTotalReste += liste[i].getSoldecredit();
            }
        }else {
            return null;
        }
        String[] avanceClients = listeId.toArray(new String[0]);
        return avanceClients;
    }

    public Ristourne genererRistourneMontant() throws Exception {
        Ristourne ristourne = new Ristourne();
        ristourne.setEstLiaisonPaiement(1);
        ristourne.setDesignation("Ristourne de la facture "+this.getId());
        ristourne.setIdClient(getIdClient());
        ristourne.setIdOrigine(this.getId());
        ristourne.setDaty(Utilitaire.dateDuJourSql());
        ristourne.setMois(Utilitaire.getMois(getDaty()));
        ristourne.setAnnee(Utilitaire.getAnnee(getDaty()));
        ristourne.setIdVente(getId());
        ristourne.setDateDebutRistourne(null);
        ristourne.setDateFinRistourne(null);

        List<RistourneDetails> ristourneDetails = getRistourneDetailsMontant();

        if (ristourneDetails.isEmpty()) {
            return null;
        }

        ristourne.setFille(ristourneDetails.toArray(new RistourneDetails[]{}));
        return ristourne;
    }

    private List<RistourneDetails> getRistourneDetailsMontant() throws Exception {
        VenteDetailsArbiochem[] listeFille = (VenteDetailsArbiochem[]) this.getFille();
        List<RistourneDetails> ristourneDetails = new ArrayList<>();
        for (VenteDetailsArbiochem venteFille : listeFille) {
            if (venteFille.getRistourneArbiochem() != 0) {

                double prix = venteFille.getPu() * venteFille.getQte();
//                double htApresRemise = prix - ((prix * venteFille.getRemise()) / 100);
                double htApresRemise = prix;

                double montantVoulu = venteFille.getRistourneArbiochem() * venteFille.getQte();

                if (htApresRemise == 0) {
                    continue;
                }

                double tauxEquivalent = (montantVoulu / htApresRemise) * 100;

                RistourneDetails ristourneFille = new RistourneDetails();
                ristourneFille.setIdProduit(venteFille.getIdProduit());
                ristourneFille.setIdOrigine(venteFille.getIdOrigine());
                ristourneFille.setTaux1(tauxEquivalent);
                ristourneFille.setPu(montantVoulu); // informatif seulement, pas relu par validerObject
                ristourneFille.setQte((int) venteFille.getQte());
                ristourneDetails.add(ristourneFille);
            }
        }
        return ristourneDetails;
    }

    // paiement automatique du ristourne montant
    public AvoirFC genererAvoirRistourneMontant(String u, Connection c, Ristourne ristourne) throws Exception {
        VenteDetailsArbiochem[] listeFille = (VenteDetailsArbiochem[]) this.getFille();

        AvoirFC avoir = new AvoirFC();
        avoir.setDesignation(getDesignation());
        avoir.setIdMagasin(getIdMagasin());
        avoir.setRemarque(getRemarque());
        avoir.setIdOrigine(this.getId());
        avoir.setIdVente(this.getId());
        avoir.setIdClient(getIdClient());
        avoir.setIdMotif(ConstanteSocobis.ID_MOTIF_RISTOURNE);
        avoir.setCompte(getCompte());
        avoir.setDaty(Utilitaire.dateDuJourSql());
        avoir.setEtat(11);
        avoir.setIdtypeavoir(ConstanteVente.idtyperistourne);
        avoir.setIdristourne(ristourne.getId());

        List<AvoirFCFille> avoirsFilles = new ArrayList<>();
        double sommeMontantAvoir = 0;

        for (VenteDetailsArbiochem venteFille : listeFille) {
            if (venteFille.getRistourneArbiochem() != 0) {

                double montantFinal = venteFille.getRistourneArbiochem() * venteFille.getQte() ;
                double tva = venteFille.getTva();

                AvoirFCFille fille = Vente.transformerFactureToAvoirFille(venteFille);
                fille.setIdProduit(venteFille.getIdProduit());
                fille.setEtat(11);
                fille.setQte(1);
                fille.setTva(tva);
                fille.setPu(montantFinal);
                fille.setTaux(venteFille.getQte());
                avoirsFilles.add(fille);

                sommeMontantAvoir += (montantFinal * (1+(venteFille.getTva()/100)));
            }
        }

        if (avoirsFilles.isEmpty()) {
            return null;
        }

        avoir.setFille(avoirsFilles.toArray(new AvoirFCFille[0]));
        avoir.createObject(u, c);
        avoir.validerObject(u, c);

        LiaisonPaiement liaisonPaiement = new LiaisonPaiement();
        liaisonPaiement.setId1(avoir.getId());
        liaisonPaiement.setId2(getId());
        liaisonPaiement.setMontant(sommeMontantAvoir);
        liaisonPaiement.setEtat(ConstanteEtat.getEtatValider());
        liaisonPaiement.createObject(u, c);

        return avoir;
    }

    @Override
    public Object validerObject(String u, Connection c) throws Exception {
        double montantresteClient = getMontantresteClient(c);
        double seuil = getClientArbiochem(c).getSeuil();

        if (seuil > 0 && montantresteClient > seuil) {
            throw new Exception(
                    "Le montant des factures non payées (" + String.format("%.2f", montantresteClient) +
                            ") dépasse le seuil maximum autorisé pour ce client (" + String.format("%.2f", seuil) + ")."
            );
        }
        Object object = super.validerObject(u, c);

        VenteLib vente = (VenteLib) new VenteLib().getById(this.getId(), "", c);

        // ristourne montant génère avoir
        Ristourne ristourne = genererRistourneMontant();
        if (ristourne != null) {
            ristourne.createObject(u, c);
            genererAvoirRistourneMontant(u, c, ristourne);
        }

        // avance bc
        String[] ids0 = getAvanceClient(c, " and idbc = '"+ this.getIdOrigine()+"'", vente);
        if (ids0 != null && ids0.length > 0) {
            String[] tabVente0=this.getId().split(";");
            LiaisonPaiement lp0=new LiaisonPaiement();
            lp0.creerPaiementParCaisse1(ids0,tabVente0,u,c);
        }

        // paiement avoir
        LiaisonPaiement paiement = genererPaiementFactureParAvoirAutom(u, c, vente);
        if (paiement != null) paiement.creerPaiementFacture(u,c);

        // avance client
        String[] ids = getAvanceClient(c, " and idbc is null", vente);
        if (ids != null && ids.length > 0) {
            String[] tabVente=this.getId().split(";");
            LiaisonPaiement lp=new LiaisonPaiement();
            lp.creerPaiementParCaisse1(ids,tabVente,u,c);
        }

        return object;
    }
}
