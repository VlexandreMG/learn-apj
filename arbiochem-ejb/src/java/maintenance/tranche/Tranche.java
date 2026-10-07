package maintenance.tranche;

import bean.TypeObjet;
import java.sql.Connection;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Tranche extends TypeObjet {
    private String heuredebut;
    private String heurefin;

    public String getHeuredebut() {
        return heuredebut;
    }

    public void setHeuredebut(String heuredebut) {
        this.heuredebut = heuredebut;
    }

    public String getHeurefin() {
        return heurefin;
    }

    public void setHeurefin(String heurefin) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime hf = LocalTime.parse(heurefin, formatter);
        LocalTime hd = LocalTime.parse(this.heuredebut, formatter);
        if(this.getMode().equals("modif")){
            if(hd.isAfter(hf)){
                throw new Exception("Heure de debut ne doit pas etre anterieur a l'heure de fin");
            }
        }
        this.heurefin = heurefin;
    }



    public Tranche() throws Exception {
        this.setNomTable("TRANCHE");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("TR","get_SEQ_TRANCHE");
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

    @Override
    public String[] getMotCles() {
        return new String[] {"id", "val", "desce", "heuredebut", "heurefin"};
    }
}
