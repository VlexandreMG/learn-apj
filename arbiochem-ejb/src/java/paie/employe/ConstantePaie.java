/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paie.employe;
import bean.TypeObjet;
/**
 *
 * @author rakotondralambokoto
 */
public class ConstantePaie {
//    RUBRIQUE HS
    public static final String rub_jf = "PR0000222";
    public static final String rub_if = "PR0000228";
    public static final String rub_mn = "PR0000221";
    public static final String rub_hd = "PR0000223";
//    SOLDE CONGE FORMATION SYNDICALE
    public static final double solde_par_an_formation_synd = 15;
//    TYPE D'ABSENCE
    public static final String conge = "TYP000004";
    public static final String permission = "TYP000003";
    public static final String evenement_familial = "TYP000006";
    public static final String formation_syndicale = "TYPCOL0005";
    public static final String maternite = "TYP00MAT";

    //Cantine
    public static final String cantine = "PRCTN0001";
    public static final double retenueCantine = 700;

    public static final String cnaps = "CONF0001";
    public static final String ostie = "CONF0002";
    public static final String desce_irsa_bareme = "IRSA";
    public static final String irsa_conf = "CONF0008";
    public static final String abbatement_enfant = "CONF0009";
    public static final String CATEGORIE_TEMPORAIRE = "CP0000011";
    public static final String CATEGORIE_CADRE = "CP000004";
    public static final String TYPE_PAIEMENT_HEURE_SUP = "TYPEHS001";
    public static final String TYPE_PAIEMENT_AVANCE = "TYPEAVC001";

    
    public static final String id_sal_base = "PR000041";
    public static final String id_cnaps = "PR000101";
    public static final String id_irsa = "PR000141";
    public static final String id_net_a_payer = "PR000210A";
    public static final String id_net_a_payer_virtuel = "PRUNI";
    public static final String id_abattement = "PR000181";
    public static final String id_irsa_nature = "PR000201";
    public static final double plafondAvance=1;

    public static final String dep_idlicensiement = "TYP0010";
    public static final String dep_iddemission = "TD002";
    public static final String dep_idfincontrat = "TYP001";
    public static final String dep_iddemissionSansPreavis = "TYP0002";

    public static final String idPreavis = "PR0000232";
    public static final String idLicensiement = "PR0000233";
    public static final String idAllocationconge = "PR000061";
    public static final String idPreavisRetenue = "PR0000234";

    public static final String idAvanceExceptionnelle = "PRU0453";
    public static final String idAvanceSurSalaire = "PRU0447";

    //    TYPE D'ABSENCE
    public static final String idCongeCollectif = "TYPCOL0004";

    public static final String matriculeTemp = "T";
    public static final String idCategoriePaieTemp = "CP0000011";


    public static final double heuremois=173.33;
    
    public static final int plafond_avcance = 30;

    //Type Avancement
    public static final String TYPE_AVANCEMENT_INDICE = "TYPAV0001";
    public static final String TYPE_AVANCEMENT_FONCTION = "TYPAV0004";
    public static final String TYPE_AVANCEMENT_REGION = "TYPAV0005";
    public static final String TYPE_AVANCEMENT_AFFECTATION = "TYPAV0006";
    public static final String TYPE_AVANCEMENT_MODE_PAIEMENT = "TYPAV0007";
    public static final String TYPE_AVANCEMENT_COMPTE = "TYPAV0008";
    public static final String TYPE_AVANCEMENT_CONTRAT = "TYPAV0009";
    public static final String TYPE_AVANCEMENT_UNITE = "TYPAV0010";
    public static final String TYPE_AVANCEMENT_SERVICE_SECTION = "TYPAV0011";

    public static TypeObjet[] getMoisTous(){
        TypeObjet[] mois = new TypeObjet[12];
        mois[0] = new TypeObjet("1","Janvier","");
        mois[1] = new TypeObjet("2","Fevrier","");
        mois[2] = new TypeObjet("3","Mars","");
        mois[3] = new TypeObjet("4","Avril","");
        mois[4] = new TypeObjet("5","Mai","");
        mois[5] = new TypeObjet("6","Juin","");
        mois[6] = new TypeObjet("7","Juillet","");
        mois[7] = new TypeObjet("8","Aout","");
        mois[8] = new TypeObjet("9","Septembre","");
        mois[9] = new TypeObjet("10","Octobre","");
        mois[10] = new TypeObjet("11","Novembre","");
        mois[11] = new TypeObjet("12","Decembre","");
        return mois;
    }
    
}
