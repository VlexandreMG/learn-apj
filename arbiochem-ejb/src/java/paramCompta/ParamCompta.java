package paramCompta;

import bean.ClassMAPTable;
import bean.TypeObjet;
import historique.MapUtilisateur;
import historique.Objet;

import java.sql.Connection;

public class ParamCompta extends TypeObjet {
    @Override
    public ClassMAPTable createObject(MapUtilisateur u, Connection c) throws Exception {
        ClassMAPTable retour = super.createObject(u, c);
        ParamComptaStatique.reload(c);
        return retour;
    }

    @Override
    public int updateToTableWithHisto(String refUser, Connection c) throws Exception {
        int retour = super.updateToTableWithHisto(refUser);
        ParamComptaStatique.reload(c);
        return retour;
    }
}
