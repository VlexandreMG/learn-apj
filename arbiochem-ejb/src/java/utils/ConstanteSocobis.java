package utils;

public class ConstanteSocobis {
    public static final boolean EST_PROD = false;
    public static final String nomEntreprise = "SOCIETE CONFISERIE ET BISCUITERIE";
    public static final String email = "socobis@malagasy.com";
    public static final String telephone = "020 85 242 84";
    public static final String adresse = "Saropody Tanjombato Antananarivo BP 535\n";
    public static final String perteStock = "PARAM001";
    public static final String gainStock = "PARAM002";

    public static final String ID_CAISSE_NON_VALIDE = "CAIS001";

    public static final String CATEGORIE_SERVICE = "CATINGSERVICE";
    public static final String CATEGORIE_MAINDOEUVRE = "CAT002";
    public static final String CATEGORIE_CONSOMMABLE = "CAT0011";
    public static final String CATEGORIE_MAINTENANCE = "CATM0001";
    public static final String CATEGORIE_PRODUIT_FINI = "CAT008";
    public static final String TYPE_MVT_ENTREE = "TPMVST000001";
    public static final String TYPE_MAG_JIRAMA = "TYPMGJIR000041";
    public static final String TYPE_FACTURE = "TPF0002";
    public static final String TYPE_FRAIS_ACCESSOIRE = "TPF0001";
    public static final String TYPE_ACHAT_PS = "PS";
    public static final String ID_CATEGORIESTOCK_DECHETS = "CTGST000003";
    public static final String ID_CATEGORIESTOCK_NORMAL = "CTGST000001";
    public static final String ID_CATEGORIESTOCK_RETOUR = "CTGST000002";
    public static final String ID_PRODUIT_DIVERS = "ING000T0134D";
    public static final String ID_PRODUIT_ELECTRICITE = "IG000385";
    public static final String MAGASIN_DIVERS = "PNT000125";
    public static final String COMPTE_PRODUIT_DIVERS = "608810";

    public static final String TYPE_MVT_SORTIE = "TPMVST000022";

    public static double POURC_MN = 0.3; // 30%
    public static double POURC_JF = 2.0; // 200%
    public static double POURC_HD = 1.4; // 140%
    public static double POURC_HS30NI = 1.3; // 130%
    public static double POURC_HS50NI = 1.5;
    public static double plafondNI = 20;
    public static double maxHS30NI = 8; // au de la de 8h dia tokony ho lasa 150% ny majoration
    public static final String CATEGORIE_MO = "CAT002";

    public static final double EQ_CARTON_PETRIN = 105; // 1 petrin = 105 cartons

    public static final int CHEFFAB_RANG = 6;
    public static final String CHEFFABR_RANG = "cheffab";
    public static final String CONTREMAITRE_RANG = "ctrmaitre";
    public static final String MAGCENTRAL_RANG = "magcentral";
    public static final String TRANSITMA_RANG = "magtransitmp";
    public static final String TRANSITEM_RANG = "magtransiteblg";
    public static final String DG_RANG = "dg";
    public static final String MAG_GAZ_RANG = "mag_gaz";
    public static final String SEC_PROD_BISC_RANG = "sec_prod_bisc";
    public static final String ASSIST_CTRM_RANG = "assist_ctrm";
    public static final String SEC_CONF_RANG = "sec_conf";
    public static final String MAG_PIECE_RANG = "mag_piece";
    public static final String MAG_PF_RANG = "mag_pf";
    public static final String ASSIST_MAG_PF_RANG = "assist_mag_pf";
    public static final String RESP_RDQ_RANG = "resp_rdq";
    public static final String MAGASIN = "PHARM005";
    public static final String Devise = "AR";
    public static final String EntitePiece = "ENT000004";
    public static final String EntiteMachine = "ENT000001";

    public static final String gaz = "IG000363";
    public static final String electricite = "IG000385";
    public static final String gazoil = "IG000362";

    public static final String[] CLIENT_APPRO_VENTE = {"CLI00SOCT0141100245","CLI00SOCT01411009992"};
    public static final String UNITE_PIECE = "UNT00010";
    public static final String MAGASIN_APPRO = "MAGAPPRO1";

    public static final String ID_MOTIF_RISTOURNE = "MOTIFRST1";
    public static final String ID_AVOIR_RISTOURNE = "TYA0001";
    public static final String ID_TYPE_AVOIR = "TYA0002";

    public static final String typeFactureFournisseurFAE = "FAE";
    public static final String typeFactureFournisseurFAR = "FAR";
    public static final int VALIDE_CHEF_DEPARTEMENT = 3;
    public static final int VALIDE_DIRECTEUR_DEPARTEMENT = 4;
    public static final int VALIDE_DIRECTEUR = 5;
    public static final String ROLE_CHEF_DEP_BISC = "chef_dep_bisc";
    public static final String ROLE_CHEF_DEP_CONF = "chef_dep_conf";
    public static final String ROLE_COMPTABLE = "comptable";
    public static final String ROLE_DG = "dg";
    public static final String ROLE_RESPACHAT = "respachat";

    public static final String caisse_commerciale = "CAI1008";
    //Maintenance
    public static final double MAX_GAZ_VALEUR = 4000.00;

    public static final String typeFactureDepenses = "FD";
    public static final String UNITE_UNITE = "UNT00005";
    public static final String fournisseurSocobis = "FRN000146";

    public static final String typeavoiravecstock = "TYAS0001";
    public static final String stockTypeInventaire="TPMVST000023";
    public static final String typeavoirengager = "TYAS0002";
    public static final String comptePerteInvenatre="671400";
    public static final String compteGainInvenatre="771400";
    public static final String idComptaPerteReport="PARAMPERTE1";
    public static final String idComptaGainReport="PARAMGAIN2";
}
