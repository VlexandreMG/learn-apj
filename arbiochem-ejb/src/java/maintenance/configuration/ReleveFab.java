package maintenance.configuration;

import bean.CGenUtil;
import bean.ClassEtat;
import maintenance.utils.ConstanteMaintenance;
import utilitaire.UtilDB;
import utilitaire.Utilitaire;

import java.sql.Connection;
import java.sql.Date;

public class ReleveFab extends ClassEtat {
    private String id,idFab,idReleve;
    private Date daty;
    public ReleveFab() {
        super.setNomTable("ReleveFab");
    }
    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("RFAB", "getseqrelevefab");
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdFab() {
        return idFab;
    }

    public void setIdFab(String idFab) {
        this.idFab = idFab;
    }

    public String getIdReleve() {
        return idReleve;
    }

    public void setIdReleve(String idReleve) {
        this.idReleve = idReleve;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public void rattacherFabrication(String u, String[] idsFabrication, Connection c) throws Exception {
        boolean canClose = false;
        try {
            if (c == null){
                c = new UtilDB().GetConn();
                canClose = true;
            }
            for (String idFab : idsFabrication) {
                ReleveFab releveFab = new ReleveFab();
                releveFab.setIdReleve(this.getIdReleve());
                releveFab.setDaty(Utilitaire.dateDuJourSql());
                releveFab.setIdFab(idFab);
                releveFab.createObject(u,c);
            }
        } catch (Exception e) {
            if (canClose) c.rollback();
            throw e;
        } finally {
            if (canClose)  c.close();
        }
    }

    @Override
    public void controler(Connection c) throws Exception {
        CompteurMaintenance compteur = (CompteurMaintenance) new CompteurMaintenance().getById(this.getIdReleve(),null,c);
        if (compteur!=null && compteur.getIdCategorie().equalsIgnoreCase(ConstanteMaintenance.CATEGORIE_GASOIL)) {
            ReleveFabCpl relever = new ReleveFabCpl();
            relever.setIdFab(this.getIdFab());
            relever.setIdCategorie(compteur.getIdCategorie());
            ReleveFabCpl[] releveFabCpls = (ReleveFabCpl[]) CGenUtil.rechercher(relever,null,null,c,"");
            if (releveFabCpls.length > 0) {
                throw new Exception("Une Fabrication ne peut être li\u00E9e qu'\u00E0 un seul relev\u00E9 de cat\u00E9gorie Gasoil.");
            }
        }
    }
}
