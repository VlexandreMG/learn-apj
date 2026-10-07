package paie.cantine;

import bean.CGenUtil;
import paie.log.LogPersonnel;
import utilitaire.UtilDB;
import utilitaire.Utilitaire;

import java.sql.Connection;

public class PointageCantineCpl extends PointageCantine{

    private String nomPersonnel;
    private String matricule;
    private String departementLib;
    private String etatLib;
    private String moisLib;

    public PointageCantineCpl() throws Exception
    {
        this.setNomTable("POINTAGECANTINE_CPL");
    }
    public String getMoisLib() {
        return moisLib;
    }

    public void setMoisLib(String moisLib) {
        this.moisLib = moisLib;
    }
    public String getNomPersonnel() {
        return nomPersonnel;
    }

    public void setNomPersonnel(String nomPersonnel) {
        this.nomPersonnel = nomPersonnel;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getDepartementLib() {
        return departementLib;
    }

    public void setDepartementLib(String departementLib) {
        this.departementLib = departementLib;
    }

    public String getEtatLib() {
        return etatLib;
    }

    public void setEtatLib(String etatLib) {
        this.etatLib = etatLib;
    }

    public PointageCantineCpl[] genererPointage(String iddepartement) throws Exception{
        PointageCantineCpl[] pointages = null;
        Connection c = null;

        try {
            c = (new UtilDB()).GetConn();
            LogPersonnel emp = new LogPersonnel();
            emp.setNomTable("LOG_PERSONNEL_V2");
            String where = " and iddepartement = '" + iddepartement + "' ORDER BY TO_NUMBER(REGEXP_SUBSTR(matricule, '^[0-9]+')) ASC";
            LogPersonnel[] listEmp = (LogPersonnel[]) CGenUtil.rechercher(emp, (String[]) null, (String[]) null, where);
            if (listEmp != null && listEmp.length > 0) {
                pointages = new PointageCantineCpl[listEmp.length];

                for(int i = 0; i < listEmp.length; ++i) {
                    PointageCantineCpl temp = new  PointageCantineCpl();
                    temp.setIdPersonnel(listEmp[i].getId());
                    temp.setNomPersonnel(listEmp[i].getNom() + " " + listEmp[i].getPrenom());
                    temp.setMatricule(listEmp[i].getMatricule());
                    temp.setMois(Utilitaire.getMoisEnCours());
                    temp.setAnnee(Utilitaire.getAneeEnCours());
                    temp.setDaty(Utilitaire.dateDuJourSql());
                    pointages[i] = temp;
                }
            }
        } catch (Exception e) {
            c.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            if (c != null) {
                c.close();
            }

        }

        return pointages;
    }
}
