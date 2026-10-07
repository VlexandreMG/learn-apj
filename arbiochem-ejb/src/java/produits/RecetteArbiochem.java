package produits;

import bean.CGenUtil;
import utilitaire.UtilDB;

import java.sql.Connection;

public class RecetteArbiochem extends Recette{

    public void validerRecettePrincipale(String u, String idRecetteMere) throws Exception {
        Connection c = null;
        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                c.setAutoCommit(false);
            }
            RecetteMere recetteMere = new RecetteMere();
            recetteMere.setId(idRecetteMere);
            RecetteMere[] recetteMeres = (RecetteMere[]) CGenUtil.rechercher(recetteMere, null, null, " ");
            RecetteFille recetteFille = new RecetteFille();
            recetteFille.setIdMere(recetteMeres[0].getId());
            RecetteFille[] recetteFilles = (RecetteFille[]) CGenUtil.rechercher(recetteFille, null, null, " ");
            Recette recette = new Recette();
            recette.setIdproduits(recetteMeres[0].getIdProduit());
            Recette[] recettes = (Recette[]) CGenUtil.rechercher(recette, null, null, " ");
            String[] idRecette = null;
            if (recettes.length > 0) {
                idRecette = new String[recettes.length];
                for (int i = 0; i < recettes.length; i++) {
                    idRecette[i] = recettes[i].getId();
                }
                recette.suppressionMultiple(idRecette, u, c);
            }
            recetteMere = recetteMeres[0];
            for (RecetteFille recetteFille1 : recetteFilles) {
                Recette recette1 = new Recette();
                recette1.setIdproduits(recetteMere.getIdProduit());
                recette1.setIdingredients(recetteFille1.getIdIngredient());
                recette1.setQuantite(recetteFille1.getQte());
                recette1.setUnite(recetteFille1.getIdUnite());
                recette1.createObject(u, c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            if (c != null) {
                c.rollback();
            }
            throw new Exception(ex.getMessage());
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }
}
