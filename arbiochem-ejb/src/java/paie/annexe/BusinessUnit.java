package paie.annexe;

import bean.TypeObjet;

import java.sql.Connection;

public class BusinessUnit extends TypeObjet {
    String idSociete;

    public String getIdSociete() {
        return idSociete;
    }

    public void setIdSociete(String idSociete) {
        this.idSociete = idSociete;
    }

    public BusinessUnit() {
        this.setNomTable("BUSINESS_UNITE");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("BU","getseq_bu");
        this.setId(makePK(c));
    }

}
