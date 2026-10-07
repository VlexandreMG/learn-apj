package produits;

import bean.CGenUtil;
import bean.ClassMere;
import java.sql.Connection;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class RecetteMereLib extends RecetteMere {
    String idProduitLib;

    public String getIdProduitLib() {
        return idProduitLib;
    }

    public void setIdProduitLib(String idProduitLib) {
        this.idProduitLib = idProduitLib;
    }

    public RecetteMereLib() throws Exception {
        this.setNomTable("RECETTE_MERE_LIB");
    }

    public String compare(RecetteMereLib[] recetteMereLibs, RecetteLib[] liste) throws Exception {
        String idCorrect = "";
        // Compter les produits de la liste
        Map<String, Integer> compteurListe = new HashMap<>();
        for (RecetteLib r : liste) {

            String idProduit = r.getIdingredients();
            compteurListe.put(
                    idProduit,
                    compteurListe.getOrDefault(idProduit, 0) + 1
            );
        }

        for (int i = 0; i < recetteMereLibs.length; i++) {
            RecetteFille recetteFille = new RecetteFille();
            recetteFille.setIdMere(recetteMereLibs[i].getId());

            RecetteFille[] recetteFilles = (RecetteFille[]) CGenUtil.rechercher(
                    recetteFille, null, null, " "
            );

            // Même nombre d'éléments
            if (recetteFilles.length != liste.length) {
                continue;
            }


            // Compter les ingrédients de la recette
            Map<String, Integer> compteurRecette = new HashMap<>();
            for (RecetteFille rf : recetteFilles) {
                String idIngredient = rf.getIdIngredient();
                compteurRecette.put(
                        idIngredient,
                        compteurRecette.getOrDefault(idIngredient, 0) + 1
                );
            }

            boolean resultat = compteurRecette.equals(compteurListe);

            if (resultat) {
                idCorrect = recetteMereLibs[i].getId();
                break;
            }
        }
        return idCorrect;
    }
}

