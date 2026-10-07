package utils;

public class ConstantePaie {
//CODE RUBRIQUE
    public static final String codeRub_HDJF = "PR00223222";
    public static final String codeRub_HDJFRetenue = "PR00223222R";

//    HS
    public static final String idHS30NI = "PR0000130";
    public static final String idHS50NI = "PR0000150";
    public static final String idMN = "PR0000221"; //majoration nuit ( hs nuit )


    
    public static final String id_typeabsenceconge = "TYP000004";
    
    public static final String id_typemat = "TYP00MAT";
    public static final String id_maternite = "PR0000130A";

    

    public static final String id_typeabsencepermission = "TYP000006";
    public static final double permissionParAnnee = 10;
    public static final String id_typeautorisationabsence = "TYP000003";

    public static final String fichierLogo = System.getProperty("jboss.home.dir")+"/standalone/deployments/fer.war/dist/img/logo_FRM.jpg";
    public static final String fichiercahenode = System.getProperty("jboss.home.dir")+"/cache/nodes.txt";
    public static final String cahenodetemp = System.getProperty("jboss.home.dir")+"/cache/nodes_temp.txt";
    public static final String gitdiff = System.getProperty("jboss.home.dir")+"/cache/gitdiff.txt";
    public static final String typeMvtEntree = "entree";
    public static final String journalVente = "8";
    public static final String valRetenueGarantie = "CONF001";
    private static String pont = "SC00001";
    private static String station = "SC00002";
    private static String nidDePoule = "SC00003";
    private static String fissure = "SC00004";
    private static String faiencage = "SC00005";
    private static String affaissement = "SC00006";
    private static String pelade = "SC00007";
    private static String ornierage = "SC00008";
    private static String nettoyage = "SC00009";
    private static String drainage = "SC00010";
    private static String terre = "SC00011";
    private static String goudron = "SC00012";
    private static String gravillon = "SC00013";
    private static String pave = "SC00014";
    private static String beton = "SC00015";
    public static String degradation = "CAT0002";
    private static String nomrestau = "defaut";
    private static String soumissionnaire = "RLE0001";
    private static String idCategInfrastructure = "CAT0001";
    private static String retenuePenalite = "TYR001";
    private static String retenueGarantie = "TYR002";
    public static final String idMvtSorite = "tpmvt2";
    public static final String typemvtentree = "entree";
    public static final String typemvtsortie = "sortie";
    public static final String idMvtSortie = "tpmvt2";
    public static final String idMvtEntree = "tpmvt1";
    public static final String typesortieFIFO = "TYS001";
    public static final String typesortieLIFO = "TYS002";
    public static final String typesortieCUMP = "TYS003";
    public static final String roleUserIndex="RLE00035"; //role paie/employe/infopersonnel-liste.jsp
    public static final String idAriary = "Ar";
    public static final String idtva = "20";
    public static final String defaultMontantDevise = "1";
    public static final int defaultMontantDeviseInt = 1;
    public static final String typeRER = "TYPR001";
    public static final String categorieRI = "CATR001";
    public static final String typeRessourceRER = "TYPR002";
    public static final String idbailleur = "FINAN000005";
    public static final String deviseDefault = "Ar";
    public static final String typemvtNormal = "tmvt0002";
    public static final String typeGarantie  = "TYR002";
    public static final String retenue  = "5";
    public static final String typeRestitution ="TF001";
    public static final String typeSimple ="TF002";
    public static final int nombreJourAnneeImmoJournaliere = 360;
    public static final String typeffStock = "stock";
    public static final String idMvola = "CAIA002";
    public static final String idAirtelMoney = "CAIA003";
    public static final String idOrangeMoney = "CAIA004";
    public static final String idDeviseAr = "Ar";
    public static final String ctgRessourceAmende = "CATR001";
    public static final String typRessourceAmende = "TYPR003";
    public static final String compteAmendeSurcharge = "70630000";
    public static final String compteEcartRessource = "75800000";
    public static final String compteEcheance = "58811000";
    public static final String idPlanBudgRessourcesExterne = "RESFRI";
    public static final String idCpRessourceZZ = "ZZZZZZZZZZ";
    public static final String typRessourceEcart = "TYPP001";
    public static final String ctgRessourceAutre = "CATR003";


    //a modifier
    public static String imagePath = "../../dossier.war/gallois/report/";
    public static String idEtatHierarchieRH = "ETH0046";
    public static String idEtatHierarchieEmploye = "ETH0047";
    public static String idEtatHierarchieCH = "ETH0048";
    public static String idEtatHierarchieDE = "ETH0049";
    public static String idEtatHierarchieDG = "ETH0050";

    public static String idDemandeConge = "TD1";
    public static String idDemandeAbsence = "TD2";
    public static String idDemandeSortie= "TD3";
    public static String idDemandeRetard = "TD4";
    public static String idDemandeSalaire = "TD5";

    private static String polyligneRN4 = "vkfrBm`~`HgRtV}IoU_d@cm@uf@bUwcHkScd@txBw^hy@kNntAfnClfAyMtrAqTxh@aw@dk@_jBprAc`@pmCw}ChfEi`BrkCy~A~eAqJhvBufCtx@abIbjCcdEpmCoi@|r@sdB{IgiD`rBgg@fnAaeAte@{`AvN{]|i@bDzp@y{@|GimAFsx@~i@vAzv@{a@by@gtBxj@eyAeN}mADwGvw@mmE~tBolA|VifCzIe}C_^woH`aBkiHkuBoIo{Dl[o]ewA_h@oyC}yBk`C}Z{kDp`@}`Bq_AgvAlOyx@wo@ca@yq@kjAbi@maBvoBodAnyAal@vl@cv@hByvB~RghCkQigAl]jDprBel@hTee@m[_bAbLoPrwA_fBoIeq@|QwBzy@_zBhCc~@vd@iq@bNe[nbA_gCnVkrA|iA{_Cb_AmdB~`DddAz]s]dcB}iDfkAohEwNomC|b@iwFdIa`Czt@eNlgAa`Btr@eoCzOeaC~QcxC~kCc~A~f@{lAdmAq{@hoAy~@jAqyAmnAuoG{}CmXek@epA_IwmA|JohCw|@um@oeAkxAhx@clAjlC}xB`aCe`AxAuV`pAfBjz@m_ApLywByj@_eFq]siDmcA}eC}wAqlCfX_vAttByiCff@q_Btz@gpAvcBw^l{Bwt@hb@y_@zx@m{A~}AqXzo@oq@uUswClt@_z@n}Bm}@tt@ykBaEypB{V_vAlq@aeA`RqhBus@sfEns@ooIa`CwkBkl@bPiqCygD}e@iiAqcAt]ywAhW{v@||@AncCumAbp@wu@mKwfA_qAqgAs`@mjArJciBmuCaLabCpEo~ClMsl@rZwe@m[ejBmtAscAgwAocC}iAeg@em@onCee@y`BguB_pE{gDubC_{@c}@pe@icGgeAyhEqq@omF{PokFqdBosA}gC{rAqq@siEuhB{uAqOq_Asp@ssAgd@}uC`_FkaB~~CavDfrDkmDhhIpu@vpFdqB~rD]`dFcx@jm@yF|w@al@Jgp@zX}Pl}A{r@vyDcsAnoCafCfdCsqF|Xc~CraEy|Af~CkhCr_@ckE_^_bChXuhBr@gvCgVqgDv_AclBt_BgeC`]eX~|AolBx\\\\yrBrUoz@tVu`AlmAqwCfvB_qDrZwu@t_BmgAoGi{Arw@`]jeC~RfpAqj@jbBhMp|@i}AhfC}E~}@at@fTagCbmC{{B|z@avAlcAayCe~A{eAq@q`ArsB{b@rbA{U`r@mqBtFylBhl@lv@tmDc_Ah`CokBltAso@xzE~GnhBeu@pvBsLgE@";
    private static String polyligneRN2 = "|gkrBqk`aHyCol@iLeaAgBqb@sx@ob@vAyi@lQue@qBkc@xg@iVFkrBapAydDwg@isBkgBckE}FygBbrCyp@|zBr@eOqiBwuA}lBzGyqAbxAckCyaBkpCr_@m}@f}C{xBnr@uqAudBafAv|AafC{TefCueA{h@mfAw|@hy@gwAx}@qhAjz@aqByz@cpAsuFoeFz^ueIu}Cm_C|`Bg}EnmEsvN|rGu`SyuAz{H|jE_wUwpAgrJmuA}kOqz@sfNtgEwnI{_AueG{G}zK|LkmGA}dE~x@sgLnlAilI}dBouCcnHcpD_gDkvCgyCmqBwsCspByuJktDoeA}iCozPkaD{cDaJghJyk@}kOyqFqdHitDqkD}dAeyIqhB}vEwx@}pEoqBk~D_~Cq_FooFqtDatCaoNizE";
    private static double longueurPortion = 10;
    private static String idTypeLCRecette = "TLC00001";
    private static String idTypeLCDepense = "TLC00002";
    public static String idRoleDirecteur = "directeur";
    public static String idRoleUtilisateur = "utilisateur";
    public static String idRoleAdmin = "admin";
    public static String idRoleAdminFacture = "adminFacture";
    public static String idRoleAdminCompta = "compta";
    public static String idRoleDg = "dg";
    public static String idRoleSaisie = "saisie";
    public static String tableOpFfLc = "OpFfLc";
    private static String mois[] = {"Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Decembre"};
    private static String moisRang[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    private static String objetDepense = "depense";
    private static String objetRecette = "recette";
    private static String objetFacture = "facture";
    private static String objetFactureF = "factureF";
    static private String tableRecetteLC = "recettesLC";
    static private String tableFactureLC = "factureLigneCredit";
    static private String tableFactureFLC = "FactureFournisseurLC";
    static private String tableDepenseLC = "sortieFF";
    private static String idFournAuto = "clt1";
    static private String objFactureProf = "FactureProfFournisseur";
    static private String objetBc = "BC";
    static private String objetCDetailFactProf = "detFactProf";
    static private String objetDetailBC = "detBC";
    static private String objetDetailFactFourn = "detFactF";
    static private String dedFactProfFourn = "DedFACTUREProfFOURNISSEUR";
    static private String DetailLcCree = "0";
    static private String DetailLcValide = "1";
    static private String objetDedBc = "DedBc";
    static private String objetLcDetMvt = "LcDetailMvtCaisse";
    static private String objetLcDetDed = "LcDetailDed";
    static private String objetLcDetOp = "LcDetailop";
    static private String objetOpFf = "OPFACTUREFOURNISSEUR";
    static private String objetDed = "ded";
    static private String objetOp = "ORDONNERPAYEMENT";
    static private String objetLcDetail = "LcDetails";
    static private String tableDetailBC = "detailBC";
    private static String objetMvtCaisse = "mvtCaisse";
    static private String objectDetailFPF = "detailFPF";
    public static String objetFactureFournisseur = "FactureFournisseur";
    static private String opFactureFournisseur = "OPFACTUREFOURNISSEUR";
    static private String objetLigneCredit = "LigneCredit";
    static private String objetLigneCreditRecette = "LigneCreditRecette";
    static private String visaOp = "VISAORDREPAYEMENT";
    static private String visaOr = "VISAOR";
    static public String typeOpFacture = "facture";
    static public String typeOpNormale = "normale";
    static public String tableVisaOp = "VISAORDREPAYEMENT";
    static public String objetFactureClient = "FACTURECLIENT";
    public static String tableOrFcLc = "ORFCLC";
    static public String tableFactureCLC = "FactureClientLC";
    static public String factureClient = "FACTURECLIENT";
    public static final String constanteTiersStock = "TIERS1";
    public static final String constantePeriode = "T";
    public static final String constanteTypeTiers = "TYPETIERS001";
    public static final String constanteCaisse = "CAISSE001";

    public static final String constanteEntreeStock = "MVT000002";
    public static final String constanteSortieStock = "MVT000003";
    public static final String constanteReintegrStock = "MVT000001";
    public static final String conge = "CONGE";
    public static final String absenceConge = "TYP000004";
    public static final String mouvement = "MOUVEMENT";
    public static final String depart = "DEPART";
    public static final String deplacement = "DEPLACEMENT";
    public static final String deces = "DECES";
    public static final String uploadDirLocation = System.getProperty("jboss.home.dir") + "/welcome-content/upload";
    public static final String uploadedDir = "/upload";
    public static final String zoneIntervenationPhoto = "zi";
    public static final String fileExtension = ".jpg";

    public static final String journalAchat = "COM000000";
    public static final String comptaOriginefactureFournisseur = "FFF";
    public static final String comptaOriginefactureClient = "FCC";
    public static final String comptaOriginePaiement = "PAY";
    public static final String origineFF = "FFF";
    public static final String compteClient = "411";
    public static final String compteTVA  = "44";
    public static final String compteBanque = "512";
    public static final String comptaoriginepaie = "PED";
    public static final String idCnapsEmpl = "CONFCNAPS-EMP";
    public static final String idCnaps = "CONFCNAPS";
    public static final String journalRessource = "7RES";
    public static final String journalCaisse = "5CAI";

    public static final String roleStock = "RLE00034";
    public static final String roleCH = "CH";
    public static final String roleDRCF = "DRCF";
    public static final String roleEmp = "employe";
    public static final String roleSE = "SE";
    public static final String roleDG = "dg";

    // CONSTANTE ID ELEMENT DE SALAIRE ASYNC-PAIE
    public static final String idSalaireBasePaie = "PR000041";
    public static final String idIrsaPaie = "PR000141NET";
    public static final String idCnapsPaie = "PR000101";
    public static final String idOstiePaie = "PRU000I710";
    public static final String idPrimePaie = "PR000001";
    public static final String idTotalRetenuesPaie = "PRUSIS015";
    public static final String idTotalGainsPaie = "PRUSIS014";
    public static final String idAllocationPaie = "PR000042";
    public static final String idIndemnitePaie = "PR000021";
    public static final String idNetAPayerArrondis = "PR000210Z";
    public static final String idAvanceExceptionnelle = "PRU0453";
    public static final String idAvanceSurSalaire = "PRU0447";

    public static final String idpreavis = "PR0000232";
    public static final String idlicensiement = "PR0000233";
    
        public static final String dep_idpreavis = "TD002";
    public static final String dep_idlicensiement = "TYP0010";

    public static double heureLegale = 173.33;

    public static String getRoledg() {
        return roleDG;
    }

    public static String getRolese() {
        return roleSE;
    }

    public static final String idRolePersonnel = "RLE00035";
    public static final int rangPersonnel = 5;

    public static double tva = 0.2;

    public static final String modePaiement = "pay1";

    public static final int nbJrAnnee = 360;



    public static String getRetenuePenalite() {
        return retenuePenalite;
    }

    public static String getRolestock() {
        return roleStock;
    }

    public static String getRolech() {
        return roleCH;
    }

    public static String getRoledrcf() {
        return roleDRCF;
    }


    public static String getRoleemp() {
        return roleEmp;
    }

    public static void setRetenuePenalite(String retenuePenalite) {
        ConstantePaie.retenuePenalite = retenuePenalite;
    }

    public static String getFichiercahenode() {
        return fichiercahenode;
    }

    public static String getRetenueGarantie() {
        return retenueGarantie;
    }

    public static void setRetenueGarantie(String retenueGarantie) {
        ConstantePaie.retenueGarantie = retenueGarantie;
    }

    public static String getIdCategInfrastructure() {
        return idCategInfrastructure;
    }

    public static String getSoumissionnaire() {
        return soumissionnaire;
    }

    public static void setSoumissionnaire(String soumissionnaire) {
        ConstantePaie.soumissionnaire = soumissionnaire;
    }

    public static final String financeTypeAnnulation = "3";

    public static String getNomRestau() {
        return nomrestau;
    }

    public static String getPont() {
        return pont;
    }

    public static String getStation() {
        return station;
    }

    public static String getNidDePoule() {
        return nidDePoule;
    }

    public static String getFissure() {
        return fissure;
    }

    public static String getFaiencage() {
        return faiencage;
    }

    public static String getAffaissement() {
        return affaissement;
    }

    public static String getPelade() {
        return pelade;
    }

    public static String getOrnierage() {
        return ornierage;
    }

    public static String getNettoyage() {
        return nettoyage;
    }

    public static String getDrainage() {
        return drainage;
    }

    public static String getTerre() {
        return terre;
    }

    public static String getGoudron() {
        return goudron;
    }

    public static String getGravillon() {
        return gravillon;
    }

    public static String getPave() {
        return pave;
    }

    public static String getBeton() {
        return beton;
    }

    public static String getPolyligneRN4() {
        return polyligneRN4;
    }

    public static String getPolyligneRN2() {
        return polyligneRN2;
    }

    public static double getLongueurPortion() {
        return longueurPortion;
    }

    public static void setLongueurPortion(double longueurPortion) {
        ConstantePaie.longueurPortion = longueurPortion;
    }

    public static String getIdTypeLCDepense() {
        return idTypeLCDepense;
    }

    public static String getDetailLcCree() {
        return DetailLcCree;
    }

    public static String getDetailLcValide() {
        return DetailLcValide;
    }

    public static String getIdTypeLCRecette() {
        return idTypeLCRecette;
    }

    public static String getIdRoleDirecteur() {
        return idRoleDirecteur;
    }

    public static String[] getMois() {
        return mois;
    }

    public static String[] getMoisRang() {
        return moisRang;
    }

    public static String getObjetDepense() {
        return objetDepense;
    }

    public static String getObjetRecette() {
        return objetRecette;
    }

    public static String getObjetFacture() {
        return objetFacture;
    }

    public static String getObjetFactureF() {
        return objetFactureF;
    }

    public static String getTableRecetteLC() {
        return tableRecetteLC;
    }

    public static String getTableFactureLC() {
        return tableFactureLC;
    }

    public static String getTableFactureFLC() {
        return tableFactureFLC;
    }

    public static String getTableDepenseLC() {
        return tableDepenseLC;
    }

    public static String getIdFournAuto() {
        return idFournAuto;
    }

    public static String getObjFactureProf() {
        return objFactureProf;
    }

    public static String getObjetBc() {
        return objetBc;
    }

    public static String getObjetCDetailFactProf() {
        return objetCDetailFactProf;
    }

    public static String getObjetDetailBC() {
        return objetDetailBC;
    }

    public static String getObjetDetailFactFourn() {
        return objetDetailFactFourn;
    }

    public static String getDedFactProfFourn() {
        return dedFactProfFourn;
    }

    public static void setObjetDedBc(String objetDedBce) {
        objetDedBc = objetDedBce;
    }

    public static String getObjetDedBc() {
        return objetDedBc;
    }

    public static void setObjetLcDetMvt(String objetLcDetMvte) {
        objetLcDetMvt = objetLcDetMvte;
    }

    public static String getObjetLcDetMvt() {
        return objetLcDetMvt;
    }

    public static String getObjetLcDetDed() {
        return objetLcDetDed;
    }

    public static String getObjetLcDetOp() {
        return objetLcDetOp;
    }

    public static String getObjetOpFf() {
        return objetOpFf;
    }

    public static String getObjetDed() {
        return objetDed;
    }

    public static String getObjetOp() {
        return objetOp;
    }

    public static String getObjetLcDetail() {
        return objetLcDetail;
    }

    public static String getTableDetailBC() {
        return tableDetailBC;
    }

    public static String getObjetMvtCaisse() {
        return objetMvtCaisse;
    }

    public static String getObjectDetailFPF() {
        return objectDetailFPF;
    }

    public static String getObjetFactureFournisseur() {
        return objetFactureFournisseur;
    }

    public static String getOpFactureFournisseur() {
        return opFactureFournisseur;
    }

    public static String getObjetLigneCredit() {
        return objetLigneCredit;
    }

    public static String getObjetLigneCreditRecette() {
        return objetLigneCreditRecette;
    }

    public static String getVisaOp() {
        return visaOp;
    }

    public static String getVisaOr() {
        return visaOr;
    }

    public static String getGitdiff() {
        return gitdiff;
    }

    public static String getCahenodetemp() {
        return cahenodetemp;
    }

    public static String getIdTypeautorisationabsence() {
        return id_typeautorisationabsence;
    }

}
