package paie.hs;

import bean.ClassEtat;

import java.sql.Connection;
import java.sql.Date;

public class PaiementHeureSup extends ClassEtat {
    private String id,idMvtCaisse,idHs,idFabrication;
    private double montant;
    private Date daty;

    public PaiementHeureSup() {
        this.setNomTable("PAIEMENTHEURESUP");
    }
    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("PHS", "GETSEQPAIEMENTHS");
        this.setId(makePK(c));
    }
    @Override
    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMvtCaisse() {
        return idMvtCaisse;
    }

    public void setIdMvtCaisse(String idMvtCaisse) {
        this.idMvtCaisse = idMvtCaisse;
    }

    public String getIdHs() {
        return idHs;
    }

    public void setIdHs(String idHs) {
        this.idHs = idHs;
    }

    public String getIdFabrication() {
        return idFabrication;
    }

    public void setIdFabrication(String idFabrication) {
        this.idFabrication = idFabrication;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }
}
