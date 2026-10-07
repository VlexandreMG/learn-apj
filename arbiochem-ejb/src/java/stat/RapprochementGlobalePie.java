package stat;

import bean.TypeObjet;

import java.sql.Date;

public class RapprochementGlobalePie extends TypeObjet {
    private double montant;
    private double pourcentage;
    private Date daty;

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public RapprochementGlobalePie() {
        this.setNomTable("RAPPROCHEMENTGLOBALE_PIE");
    }
}