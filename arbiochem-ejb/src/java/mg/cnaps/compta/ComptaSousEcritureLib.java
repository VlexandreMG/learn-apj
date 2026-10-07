package mg.cnaps.compta;

import java.sql.Date;

public class ComptaSousEcritureLib extends ComptaSousEcriture{
    int horsExercice;
    String od;
    String origine;
    String compteInt;
    String contrepartie;

    String compte_auxlib;
    String compteauxiliaire;
    double montant;




    String declaration;
    String journalfolio;

    public int getHorsExercice() {
        return horsExercice;
    }

    public void setHorsExercice(int horsExercice) {
        this.horsExercice = horsExercice;
    }

    public String getOd() {
        return od;
    }

    public void setOd(String od) {
        this.od = od;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public String getCompteInt() {
        return compteInt;
    }

    public void setCompteInt(String compteInt) {
        this.compteInt = compteInt;
    }

    public String getContrepartie() {
        return contrepartie;
    }

    public void setContrepartie(String contrepartie) {
        this.contrepartie = contrepartie;
    }

    public String getCompte_auxlib() {
        return compte_auxlib;
    }

    public void setCompte_auxlib(String compte_auxlib) {
        this.compte_auxlib = compte_auxlib;
    }

    public String getCompteauxiliaire() {
        return compteauxiliaire;
    }

    public void setCompteauxiliaire(String compteauxiliaire) {
        this.compteauxiliaire = compteauxiliaire;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
    }

    public String getJournalfolio() {
        return journalfolio;
    }

    public void setJournalfolio(String journalfolio) {
        this.journalfolio = journalfolio;
    }

    public ComptaSousEcritureLib() throws Exception {
        super();
    }



    
}
