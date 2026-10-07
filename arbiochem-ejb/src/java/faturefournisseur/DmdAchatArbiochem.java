package faturefournisseur;

import historique.MapUtilisateur;

import java.sql.Connection;

public class DmdAchatArbiochem extends DmdAchat{

    @Override
    public String[] getRoleValidation() {
        return null;
    }

    @Override
    public Object validerObject(MapUtilisateur u, Connection c) throws Exception {
        return super.validerObject(u, c);
    }

    public DmdAchatArbiochem() throws Exception {
        super();
    }
}
