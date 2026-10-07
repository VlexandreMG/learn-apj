package magasin;

import bean.TypeObjet;
import java.sql.Connection;

public class MagasinCompte extends TypeObjet {
    String nomCompte;

    public String getNomCompte() {
        return nomCompte;
    }

    public void setNomCompte(String nomCompte) {
        this.nomCompte = nomCompte;
    }

    public MagasinCompte() throws Exception {
        this.setNomTable("MAGASIN_COMPTE");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("MC","GETSEQMAGASINCOMPTE");
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

