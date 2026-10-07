package paie.formation;

import bean.ClassEtat;

public class RecapGlobalFormation extends ClassEtat {
    private double annee;
    private String ref_formation;
    private String type_formation;
    private String type_formationlib;
    private String categorie;
    private String categorielib;
    private String intitule;
    private String semestre;
    private String semestrelib;
    private String interne_externe;
    private String formateur;
    private String formateurlib;
    private String type_cout_id;
    private String type_cout_lib;
    private double montant_cout;
    private double nombre_session;
    private double nb_participant_ouvriers;
    private double nb_participant_cadres;
    private double nb_participant_total;
    private double duree_par_stagiaire;
    private double duree_totale_heure;

    public double getAnnee() {
        return annee;
    }

    public void setAnnee(double annee) {
        this.annee = annee;
    }

    public String getRef_formation() {
        return ref_formation;
    }

    public void setRef_formation(String ref_formation) {
        this.ref_formation = ref_formation;
    }

    public String getType_formation() {
        return type_formation;
    }

    public void setType_formation(String type_formation) {
        this.type_formation = type_formation;
    }

    public String getType_formationlib() {
        return type_formationlib;
    }

    public void setType_formationlib(String type_formationlib) {
        this.type_formationlib = type_formationlib;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCategorielib() {
        return categorielib;
    }

    public void setCategorielib(String categorielib) {
        this.categorielib = categorielib;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getSemestrelib() {
        return semestrelib;
    }

    public void setSemestrelib(String semestrelib) {
        this.semestrelib = semestrelib;
    }

    public String getInterne_externe() {
        return interne_externe;
    }

    public void setInterne_externe(String interne_externe) {
        this.interne_externe = interne_externe;
    }

    public String getFormateur() {
        return formateur;
    }

    public void setFormateur(String formateur) {
        this.formateur = formateur;
    }

    public String getFormateurlib() {
        return formateurlib;
    }

    public void setFormateurlib(String formateurlib) {
        this.formateurlib = formateurlib;
    }

    public String getType_cout_id() {
        return type_cout_id;
    }

    public void setType_cout_id(String type_cout_id) {
        this.type_cout_id = type_cout_id;
    }

    public String getType_cout_lib() {
        return type_cout_lib;
    }

    public void setType_cout_lib(String type_cout_lib) {
        this.type_cout_lib = type_cout_lib;
    }

    public double getMontant_cout() {
        return montant_cout;
    }

    public void setMontant_cout(double montant_cout) {
        this.montant_cout = montant_cout;
    }

    public double getNombre_session() {
        return nombre_session;
    }

    public void setNombre_session(double nombre_session) {
        this.nombre_session = nombre_session;
    }

    public double getNb_participant_ouvriers() {
        return nb_participant_ouvriers;
    }

    public void setNb_participant_ouvriers(double nb_participant_ouvriers) {
        this.nb_participant_ouvriers = nb_participant_ouvriers;
    }

    public double getNb_participant_cadres() {
        return nb_participant_cadres;
    }

    public void setNb_participant_cadres(double nb_participant_cadres) {
        this.nb_participant_cadres = nb_participant_cadres;
    }

    public double getNb_participant_total() {
        return nb_participant_total;
    }

    public void setNb_participant_total(double nb_participant_total) {
        this.nb_participant_total = nb_participant_total;
    }

    public double getDuree_par_stagiaire() {
        return duree_par_stagiaire;
    }

    public void setDuree_par_stagiaire(double duree_par_stagiaire) {
        this.duree_par_stagiaire = duree_par_stagiaire;
    }

    public double getDuree_totale_heure() {
        return duree_totale_heure;
    }

    public void setDuree_totale_heure(double duree_totale_heure) {
        this.duree_totale_heure = duree_totale_heure;
    }



    public RecapGlobalFormation() throws Exception {
        this.setNomTable("V_RECAP_GLOBAL_FORMATION");
    }

   
    @Override
    public String getTuppleID() {
        return "id";
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }
}

