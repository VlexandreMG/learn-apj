package ferme.production;

public class TriageOeufDetailLib extends TriageOeufDetail{
    String idTypeCollecteLib, idQualiteTriageOeufLib, idDestinationLib;

    public TriageOeufDetailLib() throws Exception {
        this.setNomTable("TRIAGEOEUFDETAIL_LIB");
    }

    public String getIdTypeCollecteLib() {
        return idTypeCollecteLib;
    }

    public void setIdTypeCollecteLib(String idTypeCollecteLib) {
        this.idTypeCollecteLib = idTypeCollecteLib;
    }

    public String getIdQualiteTriageOeufLib() {
        return idQualiteTriageOeufLib;
    }

    public void setIdQualiteTriageOeufLib(String idQualiteTriageOeufLib) {
        this.idQualiteTriageOeufLib = idQualiteTriageOeufLib;
    }

    public String getIdDestinationLib() {
        return idDestinationLib;
    }

    public void setIdDestinationLib(String idDestinationLib) {
        this.idDestinationLib = idDestinationLib;
    }
}
