package machine;

import bean.TypeObjet;
import mg.cnaps.compta.BilanSection;
import bean.CGenUtil;
import mg.cnaps.compta.ecriture.ComptaEcritureFille;
import bean.ClassMAPTable;
import java.sql.Connection;
import utilitaire.UtilDB;

import java.sql.Connection;

public class ElementInspection extends TypeObjet {



    public ElementInspection() throws Exception {
        this.setNomTable("ELEMENTINSPECTION");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("ELI","getSeqelementInspection");
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

    @Override
    public ClassMAPTable createObject(String u,Connection c)throws Exception {
        try{
            checkNomExistant(super.getVal(),c);
        }
        catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        return super.createObject(u, c);
    }
    public void checkNomExistant(String valeur,Connection c) throws Exception {
        boolean verif= false;
        try {
            if (!verif) {
                c = new UtilDB().GetConn();
                verif=true;
            }
            ElementInspection[] liste = (ElementInspection[]) CGenUtil.rechercher(new ElementInspection(), null, null, c, " AND val like'%"+val+"%'");
            if(liste!=null && liste.length>0)throw new Exception("Nom deja existant!");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (c != null && verif)
            c.close();
        }
    }
}

