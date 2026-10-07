package fabrication;

import bean.CGenUtil;
import utilitaire.UtilDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;

public class HeureSupFabricationCPL extends  HeureSupFabrication{
    String etatlib, idPersonneLib,nomPersonnel;
    double montantTotal, valeurHS, valeurHD, valeurIF, valeurMN, valeurJF, th,total;
    String posteLib;

    public String getEtatlib() {
        return etatlib;
    }

    public void setEtatlib(String etatlib) {
        this.etatlib = etatlib;
    }

    public HeureSupFabricationCPL(){
        this.setNomTable("heureSupFabrication_cpl");
    }

    public String getIdPersonneLib() {
        return idPersonneLib;
    }

    public void setIdPersonneLib(String idPersonneLib) {
        this.idPersonneLib = idPersonneLib;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public double getValeurHS() {
        return valeurHS;
    }

    public void setValeurHS(double valeurHS) {
        this.valeurHS = valeurHS;
    }

    public double getValeurHD() {
        return valeurHD;
    }

    public void setValeurHD(double valeurHD) {
        this.valeurHD = valeurHD;
    }

    public double getValeurIF() {
        return valeurIF;
    }

    public void setValeurIF(double valeurIF) {
        this.valeurIF = valeurIF;
    }

    public double getValeurMN() {
        return valeurMN;
    }

    public void setValeurMN(double valeurMN) {
        this.valeurMN = valeurMN;
    }

    public double getValeurJF() {
        return valeurJF;
    }

    public void setValeurJF(double valeurJF) {
        this.valeurJF = valeurJF;
    }

    public double getTh() {
        return th;
    }

    public void setTh(double th) {
        this.th = th;
    }

    public String getPosteLib() {
        return posteLib;
    }

    public void setPosteLib(String posteLib) {
        this.posteLib = posteLib;
    }

    public java.util.Map<String, Double> getSommeHeuresSupParPeriode(Date dateDebut, Date dateFin, String iddep, Connection c) throws Exception {
        boolean cree = false;
        java.util.Map<String, Double> result = new java.util.LinkedHashMap<>();
        try {
            if(c == null) {
                cree = true;
                c = new UtilDB().GetConn();
            }

            StringBuilder query = new StringBuilder(
                "SELECT " +
                "    SUM(HS) AS TOTAL_HS, " +
                "    SUM(JF) AS TOTAL_JF, " +
                "    SUM(HD) AS TOTAL_HD, " +
                "    SUM(NVL(IF, 0)) AS TOTAL_IF " +
                "FROM HEURESUPFABRICATION_CPL " +
                "WHERE 1=1 "
            );

            if(dateDebut != null) {
                query.append(" AND DATEFABRICATION >= ? ");
            }
            if(dateFin != null) {
                query.append(" AND DATEFABRICATION <= ? ");
            }
            if(iddep != null && !iddep.trim().isEmpty()) {
                query.append(" AND IDDEPARTEMENT = ? ");
            }

            System.out.println("REQUETE HEURES SUP = " + query.toString());

            PreparedStatement pstmt = c.prepareStatement(query.toString());
            int index = 1;
            if(dateDebut != null) {
                pstmt.setDate(index++, dateDebut);
            }
            if(dateFin != null) {
                pstmt.setDate(index++, dateFin);
            }
            if(iddep != null && !iddep.trim().isEmpty()) {
                pstmt.setString(index++, iddep);
            }
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                result.put("HS", rs.getDouble("TOTAL_HS"));
                result.put("JF", rs.getDouble("TOTAL_JF"));
                result.put("HD", rs.getDouble("TOTAL_HD"));
                result.put("IF", rs.getDouble("TOTAL_IF"));
            }

            rs.close();
            pstmt.close();

        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(cree && c != null) {
                c.close();
            }
        }
        return result;
    }

    public String getNomPersonnel() {
        return nomPersonnel;
    }

    public void setNomPersonnel(String nomPersonnel) {
        this.nomPersonnel = nomPersonnel;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public HeureSupFabricationCPL getHeureSupById(String idHs) throws Exception {
        HeureSupFabricationCPL[] hs =  (HeureSupFabricationCPL[]) CGenUtil.rechercher(new HeureSupFabricationCPL(), null, null, null," id = '"+idHs+"'");
        if(hs != null && hs.length > 0) {
            return hs[0];
        }
        return null;
    }
}
