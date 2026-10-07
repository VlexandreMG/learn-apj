package utils;

import annexe.ProduitLib;
import caisse.MvtCaisse;
import chatbot.ClassIA;
import fabrication.FabricationFilleCpl2;
import fabrication.OfFilleCpl;
import faturefournisseur.FactureFournisseurDetailsCpl;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import prevision.PrevisionComplet;
import stock.MvtStockFille;
import stock.MvtStockFilleTheorique;
import stock.RapprochementOF;
import vente.*;

public class ConstanteAsync {
    public static Class<? extends ClassIA>[] iaClasses = new Class[]{VenteDetailsLib.class, VenteLib.class, BonDeCommande.class, As_BondeLivraisonClient.class, FactureFournisseurDetailsCpl.class, FabricationFilleCpl2.class, PrevisionComplet.class, ProduitLib.class, OfFilleCpl.class, RapprochementOF.class, MvtStockFille.class, MvtCaisse.class, StatistiqueCA.class};
    public static final String API_KEY = "AIzaSyDimfy1cj3wJZVaRilx_QFeJd-bG6-N2Tg";
    public static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash-lite:generateContent";
    public static final String ADK_URL = "http://localhost:8000";
    public static final String AI_CONTEXT = "C'est une application d'une entreprise de confiserie et biscuiterie, donc tout ce qui est achat, depenses, ventes, bon de commande, fabrication, ordre de fabrication (OF), prevision, etc etc";
    public static final String AI_DEFINITIONS = "";

    public static final String[] mailRapport={"noreplyasync@gmail.com","lhcp apld qdgt tjqp"};

    public static final String roleChefProduction = "cheffab";
    public static final String roleDg = "dg";
    public static final String directionTana = "DIR00001";


    public static String[] getMailRapport(){
        return mailRapport;
    }

    public static final String[] couleurs = {

            "#FF5733", "#33FF57", "#3357FF", "#F1C40F", "#8E44AD", "#1ABC9C", "#E74C3C", "#2ECC71", "#3498DB", "#9B59B6",

            "#34495E", "#16A085", "#27AE60", "#2980B9", "#D35400", "#C0392B", "#BDC3C7", "#7F8C8D", "#FFB6C1", "#00CED1",

            "#FFD700", "#7FFF00", "#DC143C", "#4B0082", "#FF8C00", "#20B2AA", "#FF69B4", "#FF6347", "#40E0D0", "#6A5ACD",

            "#00FA9A", "#CD5C5C", "#9370DB", "#48D1CC", "#F08080", "#E9967A", "#8FBC8F", "#4169E1", "#800000", "#191970",

            "#FFA07A", "#FF4500", "#ADFF2F", "#00BFFF", "#DAA520", "#B22222", "#00FF7F", "#D8BFD8", "#008080", "#BDB76B",

            "#FF00FF", "#6B8E23", "#FF1493", "#8B0000", "#BC8F8F", "#00FFFF", "#696969", "#8B008B", "#FF7F50", "#A0522D",

            "#DB7093", "#556B2F", "#9932CC", "#B0E0E6", "#DDA0DD", "#A52A2A", "#2F4F4F", "#708090", "#FA8072", "#F5DEB3",

            "#DC143C", "#E0FFFF", "#F4A460", "#483D8B", "#6495ED", "#FFDEAD", "#3CB371", "#4682B4", "#C71585", "#F0E68C",

            "#9ACD32", "#D2691E", "#7B68EE", "#ADD8E6", "#BA55D3"

    };
    public static final String CAISSE_DEFAUT = "CAIS001";

    public static final String SGBD = "Oracle 11G";
    public static double usdToMga = 4500;
    public static final String phoneManager = "+261340530020";
    public static final String messageUrl = "http://localhost:3000/send";
    public static String ANT="/usr/bin/ant";
    public static String BUILD_FILE="build.xml";
    public static String PROJECT_DIR="/home/fitia/Documents/GitHub/socobis/";
    public static String JAVA_PATH="/home/fitia/Documents/GitHub/socobis/socobis-ejb/src/java/aiGenerated/";
    public static String JSP_PATH="/home/fitia/Documents/GitHub/socobis/socobis-war/web/pages/aiGenerated/";

    // Code barre
    public static final String PATH_DIR = System.getProperty("jboss.server.base.dir") + "/deployments/dossier.war";
    public static final Code128Bean CODE128_BEAN = new Code128Bean();
    public static final int DPI = 150;

    public static String buildChemin(String id) {
        return PATH_DIR + "/async/codebarre/vente/" + id + ".png";
    }

    static {
        CODE128_BEAN.setModuleWidth(0.4);
        CODE128_BEAN.setBarHeight(15);
        CODE128_BEAN.doQuietZone(true);
    }

    public static final String[] caisseTypeReport={"TCA003","TCA002"};
//    public static final String CAISSE_DEFAUT = "CAI000338";

    //    CAISSES
    public static final String CAISSE_ESPECE = "CAI000280";
    public static final String CAISSE_TPE = "CAI00023";
    public static final String CAISSE_MVOLA = "CAI000238";
    public static final String CAISSE_ORANGE = "CAI000299";
    public static final String CAISSE_AIRTEL = "CAI000239";
    public static final String CAISSE_CHEQUE = "CAI000240";
    public static final String caisseTypeEspece="TCA003";
    public static final String caisseCategMvola="CTC004";
    public static final String caisseCategAirtel="CTC006";
    public static final String caisseCategOrange="CTC005";
    public static final String caisseCategTpe="CTC007";
    public static final String caisseCategCheque="CTC002";

    // MODE DE PAIEMENT
    public static final String PAIEMENT_ESPECE = "0002";
    public static final String PAIEMENT_CARTE = "0003";
    public static final String PAIEMENT_MOBILEMONEY = "0004";
    public static final String PAIEMENT_CHEQUE = "0001";
    public static final String PAIEMENT_MULTIPLE = "0005";

    public static final String COMPTE_REGROUPEMENT = "710011";
    public static final String MAGAGIN = "PNT000084";

    public static final String tel="+261 34 12 345 67";
    public static final String tel1="+261 34 12 345 67";
    public static final String tel2="+261 34 12 345 67";

    public static final String nif="123456789";
    public static final String stat="123456789";

    public static final String mail="async@bici.mg";
    public static final String disponibilite1="Lundi au Vendredi de 08h00 à 18h00";
    public static final String disponibilite2="Samedi de 08h00 à 12h00";
    public static final String lieuMagasin="ASYNC";

}
