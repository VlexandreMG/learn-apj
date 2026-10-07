package rapport;

import bean.CGenUtil;
import bean.ValeurEtiquette;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class UtilitaireSocobis {


    // cle / valeur eo akaikin
    public static Map<String, String[]> transformerEtiquetteToMap(ValeurEtiquette[][] data) throws Exception{
        Map<String, String[]> map = new LinkedHashMap<>();
        try {
            ValeurEtiquette[][] val = data;
            for (int i = 0; i < val.length; i++) {
                String[] tabString = val[i][1].getValeur().split("<BR>");

                for (int k = 0; k < tabString.length; k++) {
                    tabString[k] = tabString[k].replace(" ", "");
                }

                if (!val[i][0].getValeur().trim().isEmpty()) {
                    map.put(val[i][0].getValeur(), tabString);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erreur lors de la transformation");
        }
        return map;
    }

    // ito cle / total (par defaut)
    public static Map<String, String[]> transformerEtiquetteToMapTotal(ValeurEtiquette[][] data) throws Exception{
        Map<String, String[]> map = new LinkedHashMap<>();
        try {
            ValeurEtiquette[][] val = data;
            for (int i = 0; i < val.length; i++) {
                String[] tabString = val[i][val[i].length - 1].getValeur().split("<BR>");

                for (int k = 0; k < tabString.length; k++) {
                    tabString[k] = tabString[k].replace(" ", "");
                }

                if (!val[i][0].getValeur().trim().isEmpty()) {
                    map.put(val[i][0].getValeur(), tabString);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erreur lors de la transformation");
        }
        return map;
    }

    // ra mis cle roa mitovy de ito no ampesaina
    public static Map<String, String[]> transformerEtiquetteToMapTotalMerge(ValeurEtiquette[][] data) throws Exception{
        Map<String, String[]> map = new LinkedHashMap<>();
        try {
            ValeurEtiquette[][] val = data;
            for (int i = 0; i < val.length; i++) {
                String[] tabString = val[i][val[i].length - 1].getValeur().split("<BR>");

                for (int k = 0; k < tabString.length; k++) {
                    tabString[k] = tabString[k].replace(" ", "");
                }

                if (!val[i][0].getValeur().trim().isEmpty()) {

                    String key = val[i][0].getValeur();
                    String[] nouveau = tabString;

                    if (map.containsKey(key)) {

                        System.out.println("cle = " + key);
                        String[] ancien = map.get(key);

                        int taille = Math.max(ancien.length, nouveau.length);

                        System.out.println("taille = " + taille);
                        String[] fusion = new String[taille];

                        for (int l = 0; l < taille; l++) {

                            double vAncien = 0;
                            double vNouveau = 0;

                            if (l < ancien.length) {
                                try { vAncien = parseNombreFrancais(ancien[l]); } catch(Exception e) {}
                            }

                            if (l < nouveau.length) {
                                try { vNouveau = parseNombreFrancais(nouveau[l]); } catch(Exception e) {}
                            }

                            double resultat = vAncien + vNouveau;

                            System.out.println("ancien = " + vAncien);
                            System.out.println("nouveau = " + vNouveau);
                            fusion[l] = String.valueOf(resultat);
                        }

                        map.put(key, fusion);

                    } else {
                        map.put(key, nouveau);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erreur lors de la transformation");
        }
        return map;
    }

    // Chart Nosologie analyse onlyy ---------------------------
    public static Map<String, String[]> transformerEtiquetteToMapLigne(ValeurEtiquette[][] data) throws Exception{
        Map<String, String[]> map = new LinkedHashMap<>();
        try {
            ValeurEtiquette[][] val = data;
            for (int i = 0; i < val.length; i++) {
                String[] tabString = new String[val[i].length - 1];

                for (int j = 1; j < val[i].length; j++) {
//                    String[] tabString = val[i][val[i].length - 1].getValeur().split("<BR>");

                    tabString[j-1] = val[i][j].getValeur();


                    if (!val[i][0].getValeur().trim().isEmpty()) {
                        map.put(val[i][0].getValeur(), tabString);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return map;
    }


    public static double parseNombreFrancais(String valeur) {
        if(valeur == null || valeur.trim().isEmpty())
            return 0;

        valeur = valeur.replace("\u00A0", "").replace(" ", "");

        valeur = valeur.replace(",", ".");

        return Double.parseDouble(valeur);
    }

}
