package client;

import bean.CGenUtil;
import produits.AlimentPoussin;
import produits.Ingredients;
import utilitaire.UtilDB;
import utilitaire.Utilitaire;
import utils.ConstanteArbiochem;
import vente.VenteArbiochem;
import vente.VenteDetailsArbiochem;

import java.sql.Connection;
import java.util.*;

public class ClientArbiochem extends Client {
    public double seuil;

    public double getSeuil() {
        return seuil;
    }

    public void setSeuil(double seuil) {
        this.seuil = seuil;
    }

    public VenteArbiochem[] getAllVentesClientByDay(int day, Connection c) throws Exception{
        VenteArbiochem vente = new VenteArbiochem();
        VenteArbiochem[] vnts = (VenteArbiochem[]) CGenUtil.rechercher(vente, null, null, c, " and IDCLIENT = '"+this.getId()+"' and DATY >= SYSDATE - "+ day );
        if (vnts == null || vnts.length == 0) {
            return new VenteArbiochem[0];
        }
        VenteArbiochem[] avecFille = new VenteArbiochem[vnts.length];
        for (int i = 0; i < avecFille.length; i++) {
            VenteArbiochem temp = new VenteArbiochem();
            temp.setId(vnts[i].getId());
            VenteArbiochem vnt = (VenteArbiochem) CGenUtil.getMereAvecFille(temp,c,"");
            avecFille[i] = vnt;
        }
        return avecFille;
    }

    public Ingredients[] achetePoussin(VenteDetailsArbiochem[] vd, Connection c) throws Exception{
        Set<String> setPrd = new HashSet<>();
        for (int j = 0; j < vd.length; j++) {
            setPrd.add(vd[j].getIdProduit());
        }
        String[] tabPrd = setPrd.toArray(new String[0]);
        String listPrd = Utilitaire.tabToString(tabPrd,"'",",");

        String listTypePoussin =  Utilitaire.tabToString(ConstanteArbiochem.type_prd_poussin,"'",",");

        Ingredients[] listIng = (Ingredients[]) CGenUtil.rechercher(new Ingredients(), null, null, c , " and id in ("+listPrd+") and TYPEPRODUIT in ("+listTypePoussin+")");
        return listIng;
    }

    public VenteDetailsArbiochem[] sommerParProduit(VenteArbiochem[] vente) throws Exception {
        Map<String, Double> map = new HashMap<>();
        for (VenteArbiochem v : vente) {
            VenteDetailsArbiochem[] vd = (VenteDetailsArbiochem[]) v.getFille();

            for (VenteDetailsArbiochem detail : vd) {
                String idProduit = detail.getIdProduit();
                double qte = detail.getQte();

                map.put(idProduit, map.getOrDefault(idProduit, 0.0) + qte);
            }
        }
        VenteDetailsArbiochem[] resultat = new VenteDetailsArbiochem[map.size()];

        int i = 0;
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            VenteDetailsArbiochem detail = new VenteDetailsArbiochem();
            detail.setIdProduit(entry.getKey());
            detail.setQte(entry.getValue());
            resultat[i++] = detail;
        }

        return resultat;
    }

    public AlimentPoussin[] getAliment(VenteDetailsArbiochem[] vd, Connection connection)throws Exception {
        String listTypePoussin = Utilitaire.tabToString(vd, "idProduit", "'", ",");
        AlimentPoussin poussin = new AlimentPoussin();
        poussin.setNomTable("AlimentPoussin_LIBCOMPLET");
        AlimentPoussin[] alim = (AlimentPoussin[]) CGenUtil.rechercher(poussin, null, null, " and IDPRODUITS in (" + listTypePoussin + ")");
        if (alim != null && alim.length > 0) {
            for (int i = 0; i < alim.length; i++) {
                for (VenteDetailsArbiochem vnt : vd) {
                    if (alim[i].getIdproduits().equals(vnt.getIdProduit())) {
                        alim[i].setQuantite(alim[i].getQuantite() * vnt.getQte());
                    }
                }
            }
            Map<String, AlimentPoussin> map = new LinkedHashMap<>();
            for (AlimentPoussin a : alim) {
                String idIngredient = a.getIdingredients();

                if (map.containsKey(idIngredient)) {
                    AlimentPoussin existant = map.get(idIngredient);
                    existant.setQuantite(
                            existant.getQuantite() + a.getQuantite()
                    );
                } else {
                    map.put(idIngredient, a);
                }
            }

            return map.values().toArray(new AlimentPoussin[0]);
        }
        return new AlimentPoussin[0];
    }

    public String message()throws Exception {
        boolean isOpen = false;
        Connection con = null;
        try {
            if (con == null) {
                con = new UtilDB().GetConn();
                isOpen = true;
            }

            VenteArbiochem[] venteArbiochems = getAllVentesClientByDay(45, con);
            if (venteArbiochems.length == 0) {
                return "";
            }
            VenteDetailsArbiochem[] venteDetailsArbiochems = sommerParProduit(venteArbiochems);
            Ingredients[] achetePoussin = achetePoussin(venteDetailsArbiochems, con);
            if (achetePoussin.length == 0) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            int compte = 0;
            AlimentPoussin[] alimentPoussins = getAliment(venteDetailsArbiochems, con);
            for (AlimentPoussin a : alimentPoussins) {
                boolean trouve = false;
                for (VenteDetailsArbiochem vnt : venteDetailsArbiochems) {

                    if (a.getIdingredients().equals(vnt.getIdProduit())) {
                        trouve = true;
                        if (vnt.getQte() < a.getQuantite()) {
                            compte++;
                            double qte = a.getQuantite() - vnt.getQte();
                            sb.append(compte)
                                    .append("- ")
                                    .append(a.getLibIngredients())
                                    .append(" ")
                                    .append(qte)
                                    .append(" ")
                                    .append(a.getIdUnite())
                                    .append("\n");
                        }
                        break;
                    }
                }
                if (!trouve) {
                    compte++;
                    sb.append(compte)
                            .append("- ")
                            .append(a.getLibIngredients())
                            .append(" ")
                            .append(a.getQuantite())
                            .append(" ")
                            .append(a.getIdUnite())
                            .append("\n");
                }
            }

            if (compte == 0) {
                return "";
            }

            sb.insert(0, "Ce client doit encore acheter :\n");

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (isOpen && con != null) {
                con.close();
            }
        }
        return null;
    }


}
