package paie.onBoarding;

import bean.TypeObjet;

import java.sql.Connection;

public class TypeDocumentCheckList extends TypeObjet {
    public TypeDocumentCheckList() throws Exception {
        this.setNomTable("TYPEDOCUMENTCHECKLIST");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("TPD","getSeqTypeDocCheckList");
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
        String[] motCles={"id", "val", "desce"};
        return motCles;
    }

    @Override
    public String[] getValMotCles() {
        String[] motCles={"val", "desce"};
        return motCles;
    }
}

