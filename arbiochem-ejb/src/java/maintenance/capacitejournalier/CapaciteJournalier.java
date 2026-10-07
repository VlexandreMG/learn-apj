package maintenance.capacitejournalier;

import bean.ClassMAPTable;

import java.sql.Connection;

public class CapaciteJournalier extends ClassMAPTable {
    private String id, idLigne;
    private double capacite;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdLigne() {
        return idLigne;
    }

    public void setIdLigne(String idLigne) {
        this.idLigne = idLigne;
    }

    public double getCapacite() {
        return capacite;
    }

    public void setCapacite(double capacite) throws Exception {
        if (this.getMode().equals("modif") && capacite <= 0) {
            throw new Exception("La capacit&eacute; ne peut pas &ecirc;tre inf&eacute;rieur ou &eacute;gal &agrave; 0");
        }
        this.capacite = capacite;
    }

    public CapaciteJournalier(){
        setNomTable("CAPACITEJOURNALIER");
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
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CP", "GETSEQCAPACITEJOURNALIER");
        this.setId(makePK(c));
    }
}
