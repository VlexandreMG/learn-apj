package faturefournisseur;

import bean.CGenUtil;
import utilitaire.UtilDB;
import vente.BonDeCommande;

import java.sql.Connection;
import java.util.*;

public class DmdAchatSuiviCommande extends DmdAchatFille{

    private String produit;
    private String produitDesignation;
    private String idDmdAchat;
    private double quantiteDemande;
    private int quantiteDejaCommande;
    private int resteACommander;

    public DmdAchatSuiviCommande() throws Exception
    {
        this.setNomTable("DMDACHATSUIVICOMMANDE");
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public String getProduitDesignation() {
        return produitDesignation;
    }

    public void setProduitDesignation(String produitDesignation) {
        this.produitDesignation = produitDesignation;
    }

    public String getIdDmdAchat() {
        return idDmdAchat;
    }

    public void setIdDmdAchat(String idDmdAchat) {
        this.idDmdAchat = idDmdAchat;
    }

    public double getQuantiteDemande() {
        return quantiteDemande;
    }

    public void setQuantiteDemande(double quantiteDemande) {
        this.quantiteDemande = quantiteDemande;
    }

    public int getQuantiteDejaCommande() {
        return quantiteDejaCommande;
    }

    public void setQuantiteDejaCommande(int quantiteDejaCommande) {
        this.quantiteDejaCommande = quantiteDejaCommande;
    }

    public int getResteACommander() {
        return resteACommander;
    }

    public void setResteACommander(int resteACommander) {
        this.resteACommander = resteACommander;
    }

    public static As_BonDeCommande_Fille[] updateResterCommandeQuantite(As_BonDeCommande_Fille[] filles, String idDmdAchat) throws Exception
    {
        Connection c = null;

       try {

           c = new UtilDB().GetConn();

           DmdAchatSuiviCommande d = new DmdAchatSuiviCommande();
           d.setNomTable("DMDACHATSUIVICOMMANDE");
           d.setIdDmdAchat(idDmdAchat);

           DmdAchatSuiviCommande[] ds = (DmdAchatSuiviCommande[]) CGenUtil.rechercher(d, null, null, c, "");

           if (ds == null || ds.length == 0)
           {
               throw new Exception("DMD Achat Suivi Commande est null");
           }

           Map<String, Integer> resteMap = new HashMap<>();

           for (DmdAchatSuiviCommande s : ds)
           {
               resteMap.put(s.getProduit(), s.getResteACommander());
           }

           List<As_BonDeCommande_Fille> result = new ArrayList<>();

           for (As_BonDeCommande_Fille f : filles)
           {
               int reste = resteMap.get(f.getProduit());

               if (reste > 0)
               {
                   f.setQuantite(reste);
                   result.add(f);
               }
           }

           return result.toArray(new As_BonDeCommande_Fille[0]);
       } catch (Exception e) {
           throw new Exception("La quantit\u00e9 peut pas &ecirc;tre inf&eacute;rieur ou \u00e9gale &agrave; 0", e);
       }
       finally {
           if (c != null)
           {
               c.close();
           }
       }


    }
}
