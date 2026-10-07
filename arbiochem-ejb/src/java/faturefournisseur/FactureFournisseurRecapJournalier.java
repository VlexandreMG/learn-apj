package faturefournisseur;

import bean.ClassMAPTable;

import java.sql.Date;

public class FactureFournisseurRecapJournalier extends ClassMAPTable {
    Date daty;
    double montantttc, montantpaye, montantreste, montantresteacejour;
    int nombre;


    public FactureFournisseurRecapJournalier(){
        this.setNomTable("FACTUREFOURNISSEUR_RECAP_JOUR");
    }



    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public double getMontantttc() {
        return montantttc;
    }

    public void setMontantttc(double montantttc) {
        this.montantttc = montantttc;
    }

    public double getMontantpaye() {
        return montantpaye;
    }

    public void setMontantpaye(double montantpaye) {
        this.montantpaye = montantpaye;
    }

    public double getMontantreste() {
        return montantreste;
    }

    public void setMontantreste(double montantreste) {
        this.montantreste = montantreste;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public double getMontantresteacejour() {
        return montantresteacejour;
    }

    public void setMontantresteacejour(double montantresteacejour) {
        this.montantresteacejour = montantresteacejour;
    }

    @Override
    public String getTuppleID() {
        return "";
    }

    @Override
    public String getAttributIDName() {
        return "";
    }

    public String getRequeteHebdomadaire(Date datyMin, Date datyMax) {
        StringBuilder req = new StringBuilder();

        req.append("SELECT\n");
        req.append("    CAST(SUM(NVL(MONTANTTTC, 0)) AS NUMBER(30,5)) AS MONTANTTTC,\n");
        req.append("    CAST(SUM(NVL(MONTANTPAYE, 0)) AS NUMBER(30,5)) AS MONTANTPAYE,\n");
        req.append("    CAST(SUM(NVL(MONTANTRESTE, 0)) AS NUMBER(30,5)) AS MONTANTRESTE,\n");
        req.append("    CAST(SUM(NVL(NOMBRE, 0)) AS NUMBER(10,0)) AS NOMBRE,\n");
        req.append("    CAST(\n");
        req.append("        MAX(MONTANTRESTEACEJOUR)\n");
        req.append("            KEEP (DENSE_RANK LAST ORDER BY DATY)\n");
        req.append("        AS NUMBER(30,5)\n");
        req.append("    ) AS MONTANTRESTEACEJOUR\n");
        req.append("FROM FACTUREFOURNISSEUR_RECAP_JOUR\n");

        boolean hasDatyMin = datyMin != null && !datyMin.toString().trim().isEmpty();
        boolean hasDatyMax = datyMax != null && !datyMax.toString().trim().isEmpty();

        if (hasDatyMin || hasDatyMax) {
            req.append("WHERE ");

            if (hasDatyMin) {
                req.append("DATY >= DATE '").append(datyMin).append("'");
            }

            if (hasDatyMin && hasDatyMax) {
                req.append(" AND ");
            }

            if (hasDatyMax) {
                // borne exclusive pour inclure toute la journée
                req.append("DATY < DATE '").append(datyMax).append("' + 1");
            }
        }

        return req.toString();
    }
}
