package paie.competence;

import bean.TypeObjet;

import java.sql.Connection;

public class SousFamillePro extends TypeObjet {
    private String idFamille;
    public SousFamillePro() throws Exception {
        this.setNomTable("sous_famille_pro");
    }
    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("SFP","getSeqSousFamillePro");
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
    public String getIdFamille() {
        return idFamille;
    }

    public void setIdFamille(String idFamille) {
        this.idFamille = idFamille;
    }
    @Override
    public String[] getMotCles() {
        String[] motCles={"id","val"};
        return motCles;
    }@Override
    public String[] getValMotCles() {
        String[] motCles={"id","val"};
        return motCles;
    }
}
