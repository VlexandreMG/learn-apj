package ferme.programme;

import bean.CGenUtil;
import bean.ClassFille;
import ferme.utils.ConstanteFerme;
import java.sql.Connection;

public class ProgrammeZootechniqueDetails extends ClassFille {
    private String id;
    private String idmere;
    private String age;
    private String idsexe;
    private double aliment;
    private double poids;
    private double mortalite;
    private double pontehebdomadaire;
    private double pontecumulee;
    private double oeufcumule;
    private double oacpourcentage;
    private double oachh;
    private double poidsoeuf;
    private double tauxeclosion;
    private double poussins;
    private double consoalimentfemelle;
    private double consoalimentmale;
    private double femellebw;
    private double malebw;
    private double ratioproduction;
    private double fertilite;
    private double consommationEau,temperatureMin,temperatureMax,humiditeMin,humiditeMax,dureeEclairage,uniformiteCible,cvMax;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdmere() {
        return idmere;
    }

    public void setIdmere(String idmere) {
        this.idmere = idmere;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIdsexe() {
        return idsexe;
    }

    public void setIdsexe(String idsexe) {
        this.idsexe = idsexe;
    }

    public double getAliment() {
        return aliment;
    }

    public void setAliment(double aliment) {
        this.aliment = aliment;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public double getMortalite() {
        return mortalite;
    }

    public void setMortalite(double mortalite) {
        this.mortalite = mortalite;
    }

    public double getPontehebdomadaire() {
        return pontehebdomadaire;
    }

    public void setPontehebdomadaire(double pontehebdomadaire) {
        this.pontehebdomadaire = pontehebdomadaire;
    }

    public double getPontecumulee() {
        return pontecumulee;
    }

    public void setPontecumulee(double pontecumulee) {
        this.pontecumulee = pontecumulee;
    }

    public double getOeufcumule() {
        return oeufcumule;
    }

    public void setOeufcumule(double oeufcumule) {
        this.oeufcumule = oeufcumule;
    }

    public double getOacpourcentage() {
        return oacpourcentage;
    }

    public void setOacpourcentage(double oacpourcentage) {
        this.oacpourcentage = oacpourcentage;
    }

    public double getOachh() {
        return oachh;
    }

    public void setOachh(double oachh) {
        this.oachh = oachh;
    }

    public double getPoidsoeuf() {
        return poidsoeuf;
    }

    public void setPoidsoeuf(double poidsoeuf) {
        this.poidsoeuf = poidsoeuf;
    }

    public double getTauxeclosion() {
        return tauxeclosion;
    }

    public void setTauxeclosion(double tauxeclosion) {
        this.tauxeclosion = tauxeclosion;
    }

    public double getPoussins() {
        return poussins;
    }

    public void setPoussins(double poussins) {
        this.poussins = poussins;
    }

    public double getConsoalimentfemelle() {
        return consoalimentfemelle;
    }

    public void setConsoalimentfemelle(double consoalimentfemelle) {
        this.consoalimentfemelle = consoalimentfemelle;
    }

    public double getFemellebw() {
        return femellebw;
    }

    public void setFemellebw(double femellebw) {
        this.femellebw = femellebw;
    }

    public double getMalebw() {
        return malebw;
    }

    public void setMalebw(double malebw) {
        this.malebw = malebw;
    }

    public double getRatioproduction() {
        return ratioproduction;
    }

    public void setRatioproduction(double ratioproduction) {
        this.ratioproduction = ratioproduction;
    }

    public double getFertilite() {
        return fertilite;
    }

    public void setFertilite(double fertilite) {
        this.fertilite = fertilite;
    }

    public double getConsoalimentmale() {
        return consoalimentmale;
    }

    public void setConsoalimentmale(double consoalimentmale) {
        this.consoalimentmale = consoalimentmale;
    }

    @Override
    public String getNomClasseMere() {
        return "ferme.programme.ProgrammeZootechnique";
    }

    @Override
    public String getLiaisonMere() {
        return "idmere";
    }

    public ProgrammeZootechniqueDetails() throws Exception {
        this.setNomTable("PROGRAMMEZOOTECHNIQUEDETAILS");
        this.setNomClasseMere("ferme.programme.ProgrammeZootechnique");
        this.setLiaisonMere("idmere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("PZTD","getseq_pzootechniquedetails");
        this.setId(makePK(c));
    }

    @Override
    public void controler(Connection c) throws Exception {
        super.controler(c);
        this.controlerDoublonAgeSexe(c);
    }

    @Override
    public int updateObject(String u, Connection c) throws Exception {
        this.controlerDoublonAgeSexe(c);
        return super.updateObject(u, c);
    }

    public void controlerDoublonAgeSexe(Connection c) throws Exception {
        String idMere = this.getIdmere() == null ? "" : this.getIdmere().trim();
        String ageLigne = this.getAge() == null ? "" : this.getAge().trim();
        String idSexe = this.getIdsexe() == null ? "" : this.getIdsexe().trim();
        if (idMere.isEmpty() || ageLigne.isEmpty()) return;
        String awhere = " AND IDMERE = '" + idMere.replace("'", "''") + "'"
                + " AND TRIM(AGE) = '" + ageLigne.replace("'", "''") + "'"
                + " AND IDSEXE = '" + idSexe.replace("'", "''") + "'";
        if (this.getId() != null && !this.getId().trim().isEmpty()) {
            awhere += " AND ID <> '" + this.getId().trim().replace("'", "''") + "'";
        }
        ProgrammeZootechniqueDetails[] existants = (ProgrammeZootechniqueDetails[]) CGenUtil.rechercher(new ProgrammeZootechniqueDetails(), null, null, c, awhere);
        if (existants.length > 0) {
            throw new Exception(getMessageDoublonAgeSexe(ageLigne, idSexe));
        }
    }

    public static String getMessageDoublonAgeSexe(String age, String idsexe) {
        String sexe = idsexe;
        if (ConstanteFerme.IDSEXEMALE.equals(idsexe)) sexe = "M\u00E2le";
        if (ConstanteFerme.IDSEXEFEMELLE.equals(idsexe)) sexe = "Femelle";
        return "L'\u00E2ge " + age + " pour le sexe " + sexe + " est dupliqu\u00E9";
    }

    @Override
    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }

    public double getConsommationEau() {
        return consommationEau;
    }

    public void setConsommationEau(double consommationEau) {
        this.consommationEau = consommationEau;
    }

    public double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public double getHumiditeMin() {
        return humiditeMin;
    }

    public void setHumiditeMin(double humiditeMin) {
        this.humiditeMin = humiditeMin;
    }

    public double getHumiditeMax() {
        return humiditeMax;
    }

    public void setHumiditeMax(double humiditeMax) {
        this.humiditeMax = humiditeMax;
    }

    public double getDureeEclairage() {
        return dureeEclairage;
    }

    public void setDureeEclairage(double dureeEclairage) {
        this.dureeEclairage = dureeEclairage;
    }

    public double getUniformiteCible() {
        return uniformiteCible;
    }

    public void setUniformiteCible(double uniformiteCible) {
        this.uniformiteCible = uniformiteCible;
    }

    public double getCvMax() {
        return cvMax;
    }

    public void setCvMax(double cvMax) {
        this.cvMax = cvMax;
    }
}

