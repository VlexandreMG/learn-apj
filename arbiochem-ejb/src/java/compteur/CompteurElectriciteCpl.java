package compteur;

import bean.CGenUtil;
import utilitaire.UtilDB;

import java.sql.Connection;

public class CompteurElectriciteCpl extends  CompteurElectricite{
    String ligne;

    public String getLigne() {
        return ligne;
    }

    public void setLigne(String ligne) {
        this.ligne = ligne;
    }

    public CompteurElectriciteCpl() throws Exception {
        setNomTable("CompteurElectriciteCpl");
    }

    public CompteurElectriciteCpl getLastCompteurElectriciteCpl(Connection c) throws Exception {
        boolean estOuvert = false;
        try {
            if(c == null){
                c=new UtilDB().GetConn();
                estOuvert = true;
            }
            CompteurElectriciteCpl[] compteurElectriciteCpls = (CompteurElectriciteCpl[]) CGenUtil.rechercher(this, null, null, c,
                    " order by daty,id desc");
            if (compteurElectriciteCpls.length > 0) {
                return compteurElectriciteCpls[0];
            }
            return null;
        }catch(Exception e){
            throw e;
        }finally {
            if(estOuvert){
                c.close();
            }
        }

    }
}
