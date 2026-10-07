package vente;

import bean.ClassMAPTable;
import chatbot.AiColDesc;
import chatbot.AiTabDesc;
import chatbot.ClassIA;

import java.sql.Connection;
import java.sql.Date;

@AiTabDesc("La structure de ma table de vente est comme ceci: ")
public class StatistiqueCA extends ClassMAPTable implements ClassIA {
    @AiColDesc("Voici le chiffre d'affaire du jour")
    private int ca_Du_Jour;
    @AiColDesc("Voici le chiffre de la semaine")
    private int meilleur_ca_Semaine;
    @AiColDesc("Voici le chiffre d'affaire du mois")
    private int meilleur_ca_Mois;
    @AiColDesc("Voici le chiffre d'affaire du mois de janvier")
    private int ca_janvier;

    @AiColDesc("Voici le chiffre d'affaire du mois de fevrier")
    private int ca_fevrier;
    @AiColDesc("Voici le chiffre d'affaire du mois de mars")
    private int ca_mars;
    @AiColDesc("Voici le chiffre d'affaire du mois de avril")
    private int ca_avril;
    @AiColDesc("Voici le chiffre d'affaire du mois de mai")
    private int ca_mai;
    @AiColDesc("Voici le chiffre d'affaire du mois de juin")
    private int ca_juin;
    @AiColDesc("Voici le chiffre d'affaire du mois de juillet")
    private int ca_juillet;
    @AiColDesc("Voici le chiffre d'affaire du mois de Aout")
    private int ca_aout;
    @AiColDesc("Voici le chiffre d'affaire du mois de Septembre")
    private int ca_septembre;
    @AiColDesc("Voici le chiffre d'affaire du mois de Octobre")
    private int ca_octobre;
    @AiColDesc("Voici le chiffre d'affaire du mois de Novembre")
    private int ca_novembre;
    @AiColDesc("Voici le chiffre d'affaire du mois de Decembre")
    private int ca_decembre;

    public int getCa_Du_Jour() {
        return ca_Du_Jour;
    }

    public void setCa_Du_Jour(int ca_Du_Jour) {
        this.ca_Du_Jour = ca_Du_Jour;
    }

    public int getCa_janvier() {
        return ca_janvier;
    }

    public void setCa_janvier(int ca_janvier) {
        this.ca_janvier = ca_janvier;
    }

    public int getCa_fevrier() {
        return ca_fevrier;
    }

    public void setCa_fevrier(int ca_fevrier) {
        this.ca_fevrier = ca_fevrier;
    }

    public int getCa_mars() {
        return ca_mars;
    }

    public void setCa_mars(int ca_mars) {
        this.ca_mars = ca_mars;
    }

    public int getCa_avril() {
        return ca_avril;
    }

    public void setCa_avril(int ca_avril) {
        this.ca_avril = ca_avril;
    }

    public int getCa_mai() {
        return ca_mai;
    }

    public void setCa_mai(int ca_mai) {
        this.ca_mai = ca_mai;
    }

    public int getCa_juin() {
        return ca_juin;
    }

    public void setCa_juin(int ca_juin) {
        this.ca_juin = ca_juin;
    }

    public int getCa_juillet() {
        return ca_juillet;
    }

    public void setCa_juillet(int ca_juillet) {
        this.ca_juillet = ca_juillet;
    }

    public int getCa_aout() {
        return ca_aout;
    }

    public void setCa_aout(int ca_aout) {
        this.ca_aout = ca_aout;
    }

    public int getCa_septembre() {
        return ca_septembre;
    }

    public void setCa_septembre(int ca_septembre) {
        this.ca_septembre = ca_septembre;
    }

    public int getCa_octobre() {
        return ca_octobre;
    }

    public void setCa_octobre(int ca_octobre) {
        this.ca_octobre = ca_octobre;
    }

    public int getCa_novembre() {
        return ca_novembre;
    }

    public void setCa_novembre(int ca_novembre) {
        this.ca_novembre = ca_novembre;
    }

    public int getCa_decembre() {
        return ca_decembre;
    }

    public void setCa_decembre(int ca_decembre) {
        this.ca_decembre = ca_decembre;
    }

    public int getca_Du_Jour() {
        return ca_Du_Jour;
    }

    public void setca_Du_Jour(int ca_Du_Jour) {
        this.ca_Du_Jour = ca_Du_Jour;
    }

    public int getMeilleur_ca_Semaine() {
        return meilleur_ca_Semaine;
    }

    public void setMeilleur_ca_Semaine(int meilleur_ca_Semaine) {
        this.meilleur_ca_Semaine = meilleur_ca_Semaine;
    }

    public int getMeilleur_ca_Mois() {
        return meilleur_ca_Mois;
    }

    public void setMeilleur_ca_Mois(int meilleur_ca_Mois) {
        this.meilleur_ca_Mois = meilleur_ca_Mois;
    }

    @Override
    public String getNomTableIA() {
        return "V_STATISTIQUE_CA";
    }

    @Override
    public String getUrlListe() {
        return "/socobis/pages/module.jsp?but=vente/vente-liste.jsp";
    }

    @Override
    public String getUrlAnalyse() {
        return "/socobis/pages/module.jsp?but=vente/vente-analyse.jsp";
    }

    @Override
    public String getUrlSaisie() {
        return "/socobis/pages/module.jsp?but=vente/vente-saisie.jsp";
    }

    @Override
    public ClassIA getClassListe() {
        return this;
    }

    @Override
    public ClassIA getClassAnalyse() {
        return this;
    }

    @Override
    public ClassIA getClassSaisie() {
        return this;
    }

    @Override
    public String getTuppleID() {
        return "";
    }

    @Override
    public String getAttributIDName() {
        return "";
    }
}

