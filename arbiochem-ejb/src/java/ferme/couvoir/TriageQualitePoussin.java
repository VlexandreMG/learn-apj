package ferme.couvoir;

import bean.ClassMere;
import java.sql.Connection;
import java.sql.Date;

public class TriageQualitePoussin extends ClassMere {
    private String id;
    private String idlot;
    private Date daty;
    private int qtepoussinacontroler;
    private String ideclosoir;
    private String idincubateur;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdlot() {
        return idlot;
    }

    public void setIdlot(String idlot) {
        this.idlot = idlot;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public int getQtepoussinacontroler() {
        return qtepoussinacontroler;
    }

    public void setQtepoussinacontroler(int qtepoussinacontroler) {
        this.qtepoussinacontroler = qtepoussinacontroler;
    }

    public String getIdeclosoir() {
        return ideclosoir;
    }

    public void setIdeclosoir(String ideclosoir) {
        this.ideclosoir = ideclosoir;
    }

    public String getIdincubateur() {
        return idincubateur;
    }

    public void setIdincubateur(String idincubateur) {
        this.idincubateur = idincubateur;
    }



    public TriageQualitePoussin() throws Exception {
        this.setNomTable("TRIAGEQUALITEPOUSSIN");
        this.setNomClasseFille("ferme.couvoir.TriageQualitePoussinDetail");
        this.setLiaisonFille("idmere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("TRP","getseqtriageQualitePoussin");
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

