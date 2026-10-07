
package fabrication.equipe;

import bean.TypeObjet;
import java.sql.Connection;

public class Equipe extends TypeObjet{

    public Equipe() {
        this.setNomTable("EQUIPE");
    }
    
    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("EQP", "getseq_equipe");
        this.setId(makePK(c));
    }
}
