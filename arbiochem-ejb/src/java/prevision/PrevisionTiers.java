package prevision;

import chatbot.AiTabDesc;

public class PrevisionTiers extends PrevisionComplet{
    private String tierslib;

    public PrevisionTiers(){
        this.setNomTable("PREVISION_TIERS");
    }

    public String getTierslib() {
        return tierslib;
    }

    public void setTierslib(String tierslib) {
        this.tierslib = tierslib;
    }
}
