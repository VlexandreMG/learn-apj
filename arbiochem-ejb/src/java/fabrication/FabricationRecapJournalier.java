package fabrication;

import bean.ClassMAPTable;

import java.sql.Date;

public class FabricationRecapJournalier extends ClassMAPTable {
    Date daty;
    int nombre;
    double qte, qtefabrique, qtereste;


    public FabricationRecapJournalier(){
        this.setNomTable("OFFILLESTOCK_RECAP_JOUR");
    }


    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public double getQtefabrique() {
        return qtefabrique;
    }

    public void setQtefabrique(double qtefabrique) {
        this.qtefabrique = qtefabrique;
    }

    public double getQtereste() {
        return qtereste;
    }

    public void setQtereste(double qtereste) {
        this.qtereste = qtereste;
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
