package maintenance.etats;

import avoir.AvoirFCLib;
import bean.CGenUtil;
import bean.ClassMAPTable;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class InterDureePanne extends ClassMAPTable {
    private String id,idMachine;
    private Date dateOt;
    private int duree;
    public InterDureePanne() throws Exception {
        this.setNomTable("InterDureePanne");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("IDP","getseq_interdureepanne");
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

    public String getIdMachine() {
        return idMachine;
    }

    public void setIdMachine(String idMachine) {
        this.idMachine = idMachine;
    }

    public Date getDateOt() {
        return dateOt;
    }

    public void setDateOt(Date dateOt) {
        this.dateOt = dateOt;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getLastDuree(Connection c) throws Exception {
        int lastDuree = 0;
        InterDureePanne idp = new InterDureePanne();
        idp.setIdMachine(this.getIdMachine());
        InterDureePanne[] liste = (InterDureePanne[]) CGenUtil.rechercher(idp, null, null, c, " order by dateot desc");
        if (liste.length > 0) {
            Date dateSql = liste[0].getDateOt();
            LocalDate dateBase = dateSql.toLocalDate();
            LocalDate today = LocalDate.now();
            lastDuree = Math.toIntExact(ChronoUnit.DAYS.between(dateBase, today));
        }
        return lastDuree;
    }
}

