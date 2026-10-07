package paie.accident;

public class AccidentLib extends Accident{
    public String id_machine_lib;
    public String id_lieu_lib;
    public String id_type_accident_lib;
    public String id_gravite_lib;
    public String id_personnel_lib;
    public String matricule;
    public String etat_lib;

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getId_machine_lib() {
        return id_machine_lib;
    }

    public void setId_machine_lib(String id_machine_lib) {
        this.id_machine_lib = id_machine_lib;
    }

    public String getId_lieu_lib() {
        return id_lieu_lib;
    }

    public void setId_lieu_lib(String id_lieu_lib) {
        this.id_lieu_lib = id_lieu_lib;
    }

    public String getId_type_accident_lib() {
        return id_type_accident_lib;
    }

    public void setId_type_accident_lib(String id_type_accident_lib) {
        this.id_type_accident_lib = id_type_accident_lib;
    }

    public String getId_gravite_lib() {
        return id_gravite_lib;
    }

    public void setId_gravite_lib(String id_gravite_lib) {
        this.id_gravite_lib = id_gravite_lib;
    }

    public String getId_personnel_lib() {
        return id_personnel_lib;
    }

    public void setId_personnel_lib(String id_personnel_lib) {
        this.id_personnel_lib = id_personnel_lib;
    }

    public String getEtat_lib() {
        return etat_lib;
    }

    public void setEtat_lib(String etat_lib) {
        this.etat_lib = etat_lib;
    }

    public AccidentLib () throws Exception{
        this.setNomTable("v_accident_lib");
    }
}
