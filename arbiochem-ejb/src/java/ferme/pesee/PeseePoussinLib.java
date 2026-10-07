package ferme.pesee;
import java.sql.Date;

public class PeseePoussinLib extends PeseePoussin{

    private String idtypepeseelib;
    private String idresponsablelib;
    private String idfermelib;
    private String idbatimentlib;
    private String idlotlib;
    private String idparquetlib;
    private String idsouchelib;
    private String idsexelib;
    private Date dateeclosion;
    private double agejour;
    private double agesemaine;
    private double nombreoiseauxpeses;
    private double poidstotal;
    private double poidsmoyen;
    private double ecarttype;
    private double coefficientvariation;
    private double limitebasseuniformite;
    private double limitehauteuniformite;
    private double nombreoiseauxdansplage;
    private double uniformite;
    private String etatlib;

    public PeseePoussinLib() throws Exception {
        this.setNomTable("PESEEPOUSSIN_LIB");
    }

    public String getIdtypepeseelib() {
        return idtypepeseelib;
    }

    public void setIdtypepeseelib(String idtypepeseelib) {
        this.idtypepeseelib = idtypepeseelib;
    }

    public String getIdresponsablelib() {
        return idresponsablelib;
    }

    public void setIdresponsablelib(String idresponsablelib) {
        this.idresponsablelib = idresponsablelib;
    }

    public String getIdfermelib() {
        return idfermelib;
    }

    public void setIdfermelib(String idfermelib) {
        this.idfermelib = idfermelib;
    }

    public String getIdbatimentlib() {
        return idbatimentlib;
    }

    public void setIdbatimentlib(String idbatimentlib) {
        this.idbatimentlib = idbatimentlib;
    }

    public String getIdlotlib() {
        return idlotlib;
    }

    public void setIdlotlib(String idlotlib) {
        this.idlotlib = idlotlib;
    }

    public String getIdparquetlib() {
        return idparquetlib;
    }

    public void setIdparquetlib(String idparquetlib) {
        this.idparquetlib = idparquetlib;
    }

    public String getIdsouchelib() {
        return idsouchelib;
    }

    public void setIdsouchelib(String idsouchelib) {
        this.idsouchelib = idsouchelib;
    }

    public String getIdsexelib() {
        return idsexelib;
    }

    public void setIdsexelib(String idsexelib) {
        this.idsexelib = idsexelib;
    }

    public Date getDateeclosion() {
        return dateeclosion;
    }

    public void setDateeclosion(Date dateeclosion) {
        this.dateeclosion = dateeclosion;
    }

    public double getAgejour() {
        return agejour;
    }

    public void setAgejour(double agejour) {
        this.agejour = agejour;
    }

    public double getAgesemaine() {
        return agesemaine;
    }

    public void setAgesemaine(double agesemaine) {
        this.agesemaine = agesemaine;
    }

    public double getNombreoiseauxpeses() {
        return nombreoiseauxpeses;
    }

    public void setNombreoiseauxpeses(double nombreoiseauxpeses) {
        this.nombreoiseauxpeses = nombreoiseauxpeses;
    }

    public double getPoidstotal() {
        return poidstotal;
    }

    public void setPoidstotal(double poidstotal) {
        this.poidstotal = poidstotal;
    }

    public double getPoidsmoyen() {
        return poidsmoyen;
    }

    public void setPoidsmoyen(double poidsmoyen) {
        this.poidsmoyen = poidsmoyen;
    }

    public double getEcarttype() {
        return ecarttype;
    }

    public void setEcarttype(double ecarttype) {
        this.ecarttype = ecarttype;
    }

    public double getCoefficientvariation() {
        return coefficientvariation;
    }

    public void setCoefficientvariation(double coefficientvariation) {
        this.coefficientvariation = coefficientvariation;
    }

    public double getLimitebasseuniformite() {
        return limitebasseuniformite;
    }

    public void setLimitebasseuniformite(double limitebasseuniformite) {
        this.limitebasseuniformite = limitebasseuniformite;
    }

    public double getLimitehauteuniformite() {
        return limitehauteuniformite;
    }

    public void setLimitehauteuniformite(double limitehauteuniformite) {
        this.limitehauteuniformite = limitehauteuniformite;
    }

    public double getNombreoiseauxdansplage() {
        return nombreoiseauxdansplage;
    }

    public void setNombreoiseauxdansplage(double nombreoiseauxdansplage) {
        this.nombreoiseauxdansplage = nombreoiseauxdansplage;
    }

    public double getUniformite() {
        return uniformite;
    }

    public void setUniformite(double uniformite) {
        this.uniformite = uniformite;
    }

    public String getEtatlib() {
        return etatlib;
    }

    public void setEtatlib(String etatlib) {
        this.etatlib = etatlib;
    }
}
