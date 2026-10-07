package paie.competence;

import bean.ClassMAPTable;

public class CodeRome extends ClassMAPTable {
    private String id,idSousFamille,code;
    public CodeRome() {
        super.setNomTable("code_rome");
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdSousFamille() {
        return idSousFamille;
    }

    public void setIdSousFamille(String idSousFamille) {
        this.idSousFamille = idSousFamille;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getTuppleID() {
        return id;
    }
    @Override
    public String[] getMotCles() {
        String[] motCles={"id","code"};
        return motCles;
    }

    @Override
    public String[] getValMotCles() {
        String[] motCles={"id","code"};
        return motCles;
    }


    @Override
    public String getAttributIDName() {
        return "id";
    }
}
