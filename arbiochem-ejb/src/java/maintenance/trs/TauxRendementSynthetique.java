package maintenance.trs;

import bean.ClassMAPTable;

import java.sql.Connection;
import java.sql.Date;

public class TauxRendementSynthetique extends ClassMAPTable {
    private String id, idLigne, idLigneLib;
    private double to, co, arpg, tr, cr, arnpg, tf, cf, tpp, cpp, tn, cn, trb, crbO, crb, tu, cu, txF, txP, txQ, trs;
    private Date daty;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdLigne() {
        return idLigne;
    }

    public void setIdLigne(String idLigne) {
        this.idLigne = idLigne;
    }

    public String getIdLigneLib() {
        return idLigneLib;
    }

    public void setIdLigneLib(String idLigneLib) {
        this.idLigneLib = idLigneLib;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getCo() {
        return co;
    }

    public void setCo(double co) {
        this.co = co;
    }

    public double getTr() {
        return tr;
    }

    public void setTr(double tr) {
        this.tr = tr;
    }

    public double getCr() {
        return cr;
    }

    public void setCr(double cr) {
        this.cr = cr;
    }

    public double getTf() {
        return tf;
    }

    public void setTf(double tf) {
        this.tf = tf;
    }

    public double getCf() {
        return cf;
    }

    public void setCf(double cf) {
        this.cf = cf;
    }

    public double getTpp() {
        return tpp;
    }

    public void setTpp(double tpp) {
        this.tpp = tpp;
    }

    public double getCpp() {
        return cpp;
    }

    public void setCpp(double cpp) {
        this.cpp = cpp;
    }

    public double getTn() {
        return tn;
    }

    public void setTn(double tn) {
        this.tn = tn;
    }

    public double getCn() {
        return cn;
    }

    public void setCn(double cn) {
        this.cn = cn;
    }

    public double getTrb() {
        return trb;
    }

    public void setTrb(double trb) {
        this.trb = trb;
    }

    public double getCrbO() {
        return crbO;
    }

    public void setCrbO(double crbO) {
        this.crbO = crbO;
    }

    public double getCrb() {
        return crb;
    }

    public void setCrb(double crb) {
        this.crb = crb;
    }

    public double getTu() {
        return tu;
    }

    public void setTu(double tu) {
        this.tu = tu;
    }

    public double getCu() {
        return cu;
    }

    public void setCu(double cu) {
        this.cu = cu;
    }

    public double getTxF() {
        return txF;
    }

    public void setTxF(double txF) {
        this.txF = txF;
    }

    public double getTxP() {
        return txP;
    }

    public void setTxP(double txP) {
        this.txP = txP;
    }

    public double getTxQ() {
        return txQ;
    }

    public void setTxQ(double txQ) {
        this.txQ = txQ;
    }

    public double getTrs() {
        return trs;
    }

    public void setTrs(double trs) {
        this.trs = trs;
    }

    public Date getDaty() {
        return daty;
    }

    public double getArpg() {
        return arpg;
    }

    public void setArpg(double arpg) {
        this.arpg = arpg;
    }

    public double getArnpg() {
        return arnpg;
    }

    public void setArnpg(double arnpg) {
        this.arnpg = arnpg;
    }
    
    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public TauxRendementSynthetique() {
        super.setNomTable("taux_rendement_synthetique");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("TRS", "GET_SEQ_TRS");
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
