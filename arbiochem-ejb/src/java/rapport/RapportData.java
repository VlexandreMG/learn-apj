package rapport;

import bean.CGenUtil;
import fabrication.Of;
import fabrication.OfFilleCpl;
import stat.EtatStockProduitFini;
import stock.MvtStockFilleTheorique;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Vector;


public class RapportData {
    private Of of;

    public Of getOf() {
        return of;
    }

    public void setOf(Of of) {
        this.of = of;
    }

    public OfFilleCpl[] getFillesOf(Connection c)throws Exception{
        OfFilleCpl ofFilleCpl = new OfFilleCpl();
        ofFilleCpl.setNomTable("OfFilleLibStock");
        ofFilleCpl.setIdMere(this.getOf().getId());
        OfFilleCpl[] ofFilleCpls = (OfFilleCpl[]) CGenUtil.rechercher(ofFilleCpl, null, null, c, "");
        return ofFilleCpls;
    }

    public static String genererEntete(){
        String entete = "<html>\n" +
                "<head>\n" +
                "  <style>\n" +
                "    table {\n" +
                "      border-collapse: collapse;\n" +
                "      width: 100%;\n" +
                "      max-width: 800px;\n" +
                "      font-family: Tahoma, sans-serif;\n" +
                "      font-size: 9px;\n" +
                "    }\n" +
                "\n" +
                "    th {\n" +
                "      background-color: #004080;\n" +
                "      color: white;\n" +
                "      padding: 6px;\n" +
                "      border: 1px solid #004080;\n" +
                "    }\n" +
                "\n" +
                "    td {\n" +
                "      padding: 6px;\n" +
                "      border: 1px solid #ddd;\n" +
                "      text-align: center;\n" +
                "    }\n" +
                "\n" +
                "    tbody tr:nth-child(even) {\n" +
                "      background-color: #f2f8ff;\n" +
                "    }\n" +
                "\n" +
                "    a {\n" +
                "      text-decoration: none;\n" +
                "    }\n" +
                "\n" +
                "    table.total-table {\n" +
                "      width: 25%;\n" +
                "      margin-top: 10px;\n" +
                "      border: 1px solid #ccc;\n" +
                "      background-color: #ffffff;\n" +
                "    }\n" +
                "\n" +
                "    table.total-table td {\n" +
                "      background-color: #ffffff;\n" +
                "      text-align: left;\n" +
                "      padding: 6px;\n" +
                "      border: 1px solid #ccc;\n" +
                "      font-size: 9px;\n" +
                "    }\n" +
                "\n" +
                "    table.total-table td:last-child {\n" +
                "      font-weight: bold;\n" +
                "      color: #000;\n" +
                "      text-align: right;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>";
        return entete;
    }

    public String genererBasPage(){
        String basPage = "</body></html>";

        return basPage;
    }

    public String ficheOf(Connection c)throws Exception{
        String fiche = "<h1>Ordre de fabrication</h1>";

        String[] libAffiche = {"ID", "Lanc&eacute; par", "Cible", "Remarque", "D&eacute;signation", "Date de besoin", "Date", "&Eacute;tat"};
        String[] attributs = {"id", "lancePar", "cible", "remarque", "libelle", "besoin", "daty", "etatLib"};
        fiche += Utilitaire.ficheTable(this.getOf(), libAffiche, attributs);

        return fiche;
    }

    public String ficheOfFille(OfFilleCpl ofFilleCpl)throws Exception{
        String fiche = "<h1>Ordre de fabrication fille</h1>";

        String[] libAffiche = {"ID", "Libell&eacute; de l'ordre de fabrication", "Remarque", "idMere", "Quantit&eacute;", "Ingr&eacute;dients"};
        String[] attributs = {"id", "libelle", "remarque", "idMere", "qte", "libelleexacte"};
        fiche += Utilitaire.ficheTable(ofFilleCpl, libAffiche, attributs);

        return fiche;
    }

    public String detailsOf(Connection c) throws Exception{

        String[] libEntete = {"id", "idIngredients", "idunite", "libelleexacte","purevient","qte","qteFabrique","qteReste","pv","montantRevient","montantentree","montantsortie","tauxRevient"};
        String[] libEnteteAffiche = {"id", "Composants", "Unit&eacute;","D&eacute;signation","PU de revient","Qt&eacute; Ordre","Qt&eacute; Fabriqu&eacute;","Qt&eacute; restante","Prix de vente","Montant Th&eacute;orique","Valeur Fabriqu&eacute;e","D&eacute;pense de Fabrication","Taux de revient ( en %)"};

        OfFilleCpl[] ofFilleCpls = this.getFillesOf(c);

        String table = "<h2>D&eacute;tails: </h2>";

        table += Utilitaire.tableHeader(libEnteteAffiche);
        if (ofFilleCpls.length == 0) {
            table += "<tr><td colspan='" + libEnteteAffiche.length + "'>Aucune donn&eacute;e disponible .</td></tr>";
        }
        for (int i = 0; i < ofFilleCpls.length; i++) {
            table += "<tr>";
            table += Utilitaire.toHtmlRow(ofFilleCpls[i], libEntete);
            table += "</tr>";
        }

        table += Utilitaire.tableFooter();
        return table;
    }

    public String rapprochementGlobale(Connection c, String id, String nomTable)throws Exception{

        String aWhere = " and IDMERE='"+id+"'";
        String libEntete[] = {"idcategorieingredient","categorieingredient","montantsortie","pourcentage","montth","ecart"};
        String libEnteteAffiche[] =  {"ID","Ingredient","Montant","% pratique","Montant TH","&Eacute;cart"};

        MvtStockFilleTheorique t = new MvtStockFilleTheorique();
        t.setNomTable(nomTable);
//        t.setNomTable("stockEtDepOfFabTheCatGroupe");
//        t.setNomTable("STOCKETDEPOFFILLETHECATGROUPE");
        MvtStockFilleTheorique[] ts = (MvtStockFilleTheorique[]) CGenUtil.rechercher(t, null, null, c, aWhere);

        String table = "<h2>Rapprochement globale: </h2>";
        table += Utilitaire.tableHeader(libEnteteAffiche);
        if (ts.length == 0) {
            table += "<tr><td colspan='" + libEnteteAffiche.length + "'>Aucune donn&eacute;e disponible .</td></tr>";
        }
        for (int i = 0; i < ts.length; i++) {
            table += "<tr>";
            table += Utilitaire.toHtmlRow(ts[i], libEntete);
            table += "</tr>";
        }

        table += Utilitaire.tableFooter();

        String[] libAffiche = {"Total Montant", "Total Montant TH", "Ecart Montant"};
        String[] attributs = {"montantsortie", "montth", "ecart"};

        table += Utilitaire.sommeTable(ts, libAffiche, attributs);

        return table;
    }

    public String rapprochement(String id, String nomTable, String tableFonction)throws Exception{
        String libEntete[] = {"idProduit","designation","pu","sortie","qteth","Ecartqte","montantsortie","montth","pourcentage","Ecartmontant"};
        String libEnteteAffiche[] =  {"Ingredient","D&eacute;signation","PU","Quantit&eacute;","Quantit&eacute; Th&eacute;orique", "&Eacute;cart Qte","Montant","Montant Th&eacute;orique","% pratique", "&Eacute;cart montant"};
        String libAffiche[] = {"Total Montant Pratique", "Total Montant TH", "Ecart Montant TH"};
        String attribut[] = {"montantsortie", "montth", "Ecartmontant"};

        MvtStockFilleTheorique t = new MvtStockFilleTheorique();
        t.setNomTable(nomTable);
//        t.setNomTable("stockEtDepenseOfFabThe");
//        t.setNomTable("STOCKETDEPENSEOFFILLEFABTHE");
        HashMap<String, Vector> tab = t.getRapprochementParCategorie(id, tableFonction, null);

        String table = "<h2>Rapprochement: </h2>";

        for (String cle : tab.keySet()) {
            Vector<MvtStockFilleTheorique> data = tab.get(cle);
            MvtStockFilleTheorique[] dataArray = new MvtStockFilleTheorique[data.size()];
            data.copyInto(dataArray);

            table += "<h3>"+cle+"</h3>";
            table += Utilitaire.tableHeader(libEnteteAffiche);
            if (dataArray.length == 0) {
                table += "<tr><td colspan='" + libEnteteAffiche.length + "'>Aucune donn&eacute;e disponible .</td></tr>";
            }
            for (int i = 0; i < dataArray.length; i++) {
                table += "<tr>";
                table += Utilitaire.toHtmlRow(dataArray[i], libEntete);
                table += "</tr>";
            }
            table += Utilitaire.tableFooter();
            table += Utilitaire.sommeTable(dataArray, libAffiche, attribut);
        }

        return table;
    }

    public String stockProduitFiniListe(Connection c)throws Exception{

        String libEntete[] = {"id","idProduitLib","idMagasinLib","quantite","puVente", "totalVente"};
        String libEnteteAffiche[] = {"id","Ingr&eacute;dient","Magasin","Quantit&eacute;","Valeur de vente", "Total Vente"};

        EtatStockProduitFini t = new EtatStockProduitFini();
        EtatStockProduitFini[] ts = (EtatStockProduitFini[])CGenUtil.rechercher(t, null, null, c, "");

        String table = "<h1>Valeur en Stock des produits finis: </h1>";

        table += Utilitaire.tableHeader(libEnteteAffiche);
        if (ts.length == 0) {
            table += "<tr><td colspan='" + libEnteteAffiche.length + "'>Aucune donn&eacute;e disponible .</td></tr>";
        }
        for (int i = 0; i < ts.length; i++) {
            table += "<tr>";
            table += Utilitaire.toHtmlRow(ts[i], libEntete);
            table += "</tr>";
        }

        table += Utilitaire.tableFooter();
        return table;
    }
}
