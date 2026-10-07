package rapport;

import java.sql.Connection;

import bean.TypeObjet;

public class MailRapport extends TypeObjet{
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public MailRapport(){
        this.setNomTable("MAILRAPPORT");
    }

    public void construirePK(Connection c) throws Exception {
        this.preparePk("MR", "getseqMailRapport");
        this.setId(makePK(c));
    }
}
