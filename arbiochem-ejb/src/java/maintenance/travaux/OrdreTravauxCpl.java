package maintenance.travaux;

public class OrdreTravauxCpl extends OrdreTravaux{
    private String typeMaintenance;
    public OrdreTravauxCpl() throws Exception {
        super.setNomTable("ordretravauxcpl");
    }

    public String getTypeMaintenance() {
        return typeMaintenance;
    }

    public void setTypeMaintenance(String typeMaintenance) {
        this.typeMaintenance = typeMaintenance;
    }
}
