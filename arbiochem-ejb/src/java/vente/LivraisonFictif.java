package vente;

import bean.ClassMAPTable;
import bean.TypeObjet;

import java.sql.Connection;

public class LivraisonFictif extends TypeObjet {
    public LivraisonFictif() {
        this.setNomTable("livraisonFictif");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("LF", "GETSEQlivraisonFictif");
        this.setId(makePK(c));
    }
}
