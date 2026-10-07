package ferme.transfertpoulet;

import java.sql.Connection;
import bean.ClassMere;
import java.sql.Date;

/**
 *
 * @author Safidy
 */
public class TransfertPoulet extends ClassMere {
    private String id;
    private String remarque,idLot;
    private Date daty;

    public TransfertPoulet()throws Exception {
        super.setNomTable("TRANSFERTPOULET");
        this.setNomClasseFille("ferme.transfertpoulet.TransfertPouletDetail");
        this.setLiaisonFille("idMere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("TRSP", "GETSEQTRANSFERTPOULET");
        this.setId(this.makePK(c));
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
    public String getNomClasseFille() {
        return "ferme.transfertpoulet.TransfertPouletDetail";
    }

    @Override
    public String getLiaisonFille() {
        return "idMere";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public String getIdLot() {
        return idLot;
    }

    public void setIdLot(String idLot) {
        this.idLot = idLot;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }
}