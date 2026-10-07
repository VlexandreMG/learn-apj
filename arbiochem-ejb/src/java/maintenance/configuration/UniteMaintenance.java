package maintenance.configuration;

import bean.TypeObjet;

import java.sql.Connection;

public class UniteMaintenance extends TypeObjet {

    int echelle;
    int ouvrable;

    public UniteMaintenance() {
        this.setNomTable("unitemaintenance");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("TM", "getSeqUniteMaintenance");
        this.setId(makePK(c));
    }

    public int getEchelle() {
        return echelle;
    }

    public void setEchelle(int echelle) {
        this.echelle = echelle;
    }

    public int getOuvrable() {
        return ouvrable;
    }

    public void setOuvrable(int ouvrable) {
        this.ouvrable = ouvrable;
    }

    @Override
    public String[] getMotCles() {
        return new String[] {"id", "val", "desce", "echelle", "ouvrable"};
    }
}
