package paie.formateur;

import bean.ClassMAPTable;

import java.sql.Connection;

public class ModulePrestataire extends ClassMAPTable {
    private String id;
    private String idformateur;
    private String module;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdformateur() {
        return idformateur;
    }

    public void setIdformateur(String idformateur) {
        this.idformateur = idformateur;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }



    public ModulePrestataire() throws Exception {
        this.setNomTable("MODULE_PRESTATAIRE");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("MP","getseqmoduleprestataire");
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
}

