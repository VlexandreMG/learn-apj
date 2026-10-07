package paie;

import bean.TypeObjet;

public class PaieFonction extends TypeObjet {

    private String idGroupeFonction;
    private String gratification;

    public PaieFonction() throws Exception
    {
        this.setNomTable("PAIE_FONCTION");
    }

    public String getGratification() {
        return gratification;
    }

    public void setGratification(String gratification) {
        this.gratification = gratification;
    }

    public String getIdGroupeFonction() {
        return idGroupeFonction;
    }

    public void setIdGroupeFonction(String idGroupeFonction) {
        this.idGroupeFonction = idGroupeFonction;
    }

    @Override
    public String[] getMotCles() {
        String[] motCles={"id","val"};
        return motCles;
    }

    @Override
    public String[] getValMotCles() {
        String[] valMotCles={"val"};
        return valMotCles;
    }
}


