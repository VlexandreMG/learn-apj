package paie.absence;

import bean.ClassEtat;
import bean.ClassMAPTable;
import constante.ConstanteEtat;
import java.sql.Connection;
import java.sql.Date;

public class TypeAbsence extends ClassEtat{
    private String id,val,desce,frequence;
    private double nbJour;
    public TypeAbsence() {
        this.setNomTable("TYPE_ABSENCE");
    }
    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("TPA", "GETSEQ_TYPEABSENCE");
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

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getDesce() {
        return desce;
    }

    public void setDesce(String desce) {
        this.desce = desce;
    }

    public String getFrequence() {
        return frequence;
    }

    public void setFrequence(String frequence) {
        this.frequence = frequence;
    }

    public double getNbJour() {
        return nbJour;
    }

    public void setNbJour(double nbJour) {
        this.nbJour = nbJour;
    }
}
