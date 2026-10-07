package paie.accident;

import bean.ClassMAPTable;

import java.sql.Connection;
import java.sql.Date;

public class ArretTravail extends ClassMAPTable {
    private String id;
    private String id_Accident;
    private Date daty;
    private Date date_Debut_Arret;
    private Date date_Fin_Arret;
    private double nombre_Jour;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_Accident() {
        return id_Accident;
    }

    public void setId_Accident(String id_Accident) {
        this.id_Accident = id_Accident;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public Date getDate_Debut_Arret() {
        return date_Debut_Arret;
    }

    public void setDate_Debut_Arret(Date date_Debut_Arret) {
        this.date_Debut_Arret = date_Debut_Arret;
    }

    public Date getDate_Fin_Arret() {
        return date_Fin_Arret;
    }

    public void setDate_Fin_Arret(Date date_Fin_Arret) {
        this.date_Fin_Arret = date_Fin_Arret;
    }

    public double getNombre_Jour() {
        return nombre_Jour;
    }

    public void setNombre_Jour(double nombre_Jour) {
        this.nombre_Jour = nombre_Jour;
    }



    public ArretTravail() throws Exception {
        this.setNomTable("ARRET_TRAVAIL");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("ART","getSeqArretTravail");
        this.setId(makePK(c));
    }

    @Override
    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }
}

