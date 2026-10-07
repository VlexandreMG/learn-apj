package vente;

import bean.CGenUtil;
import bean.ClassMAPTable;
import bean.TypeObjet;
import historique.MapUtilisateur;

import java.sql.Connection;

public class ImprimerVente extends TypeObjet {
    public ImprimerVente() {
        this.setNomTable("imprimerVente");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("IMP", "GETSEQimprimerVente");
        this.setId(makePK(c));
    }


    @Override
    public ClassMAPTable createObject(String u, Connection c) throws Exception {
        MapUtilisateur[] mapUtilisateur = (MapUtilisateur[]) CGenUtil.rechercher(new MapUtilisateur(), null, null, " and REFUSER ='"+u+"' ");
        String awhere = " and val ='"+this.getVal()+"'";
        if(mapUtilisateur[0].getIdrole().equals("dg") == false){
            ImprimerVente[] imprimerVentes = (ImprimerVente[]) CGenUtil.rechercher(new ImprimerVente(), null, null, awhere);
            if (imprimerVentes.length > 0) {
                throw new Exception("Vente d\u00e9j\u00e0 imprim\u00e9e");
            }

//            awhere = awhere + " and desce ='"+this.getDesce()+"'";
        }

        return super.createObject(u, c);
    }

}
