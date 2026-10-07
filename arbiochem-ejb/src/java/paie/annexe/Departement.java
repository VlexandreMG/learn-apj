package paie.annexe;

import bean.TypeObjet;

import java.sql.Connection;

public class Departement extends TypeObjet {
    String idSociete;

    public String getIdSociete() {
        return idSociete;
    }

    public void setIdSociete(String idSociete) {
        this.idSociete = idSociete;
    }

    public Departement() {
        this.setNomTable("DEPARTEMENT");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("DEP","getseq_departement");
        this.setId(makePK(c));
    }

}
