package paie.employe;
import bean.ClassMAPTable;
import java.sql.Connection;

public class UtilisateurPersonnel extends ClassMAPTable{
    private String id,idUtilisateur,idPersonnel;
    public UtilisateurPersonnel(){
        super.setNomTable("utilisateur_personnel");
    }
    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("UP", "getsequtilisateur_personnel");
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

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(String idPersonnel) {
        this.idPersonnel = idPersonnel;
    }
}
