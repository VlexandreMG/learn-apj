package ferme.triageBatiment;

import bean.ClassMAPTable;

public class TriageLotSexeQualiteLib extends ClassMAPTable {
    String idLot, idQualite, idSexe;
    double qte;

    public TriageLotSexeQualiteLib() {
        this.setNomTable("QTE_DISPO_LOT_SEXE_QUALITELIB");
    }

    public String getIdLot() {
        return idLot;
    }

    public void setIdLot(String idLot) {
        this.idLot = idLot;
    }

    public String getIdQualite() {
        return idQualite;
    }

    public void setIdQualite(String idQualite) {
        this.idQualite = idQualite;
    }

    public String getIdSexe() {
        return idSexe;
    }

    public void setIdSexe(String idSexe) {
        this.idSexe = idSexe;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    @Override
    public String getTuppleID() {
        return idLot;
    }

    @Override
    public String getAttributIDName() {
        return "idLot";
    }
}
