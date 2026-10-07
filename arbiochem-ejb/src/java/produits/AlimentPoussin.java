package produits;

import bean.CGenUtil;
import utilitaire.UtilDB;
import utilitaire.Utilitaire;

import java.sql.Connection;

public class AlimentPoussin extends Recette{
    @Override
    public void controler(Connection c) throws Exception {}

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("ALMP", "GETSEQALIMPOUSSIN");
        this.setId(makePK(c));
    }


    public AlimentPoussin() {
        super.setNomTable("AlimentPoussin");
    }

    public void modifQte2(String refuser, String[] id, String[] remarque, Connection c) throws Exception {
        int indice = 0;
        try {
            if (c == null) {
                c = (new UtilDB()).GetConn();
                c.setAutoCommit(false);
                indice = 1;
            }
            if (id == null) {
                throw new Exception("Aucune recette selectionee");
            }
            String[] listeIndice = new String[id.length];
            for(int j = 0; j < id.length; ++j) {
                String[] id_indice = Utilitaire.split(id[j], "_");
                id[j] = id_indice[0];
                listeIndice[j] = id_indice[1];
            }

            String tid = Utilitaire.tabToString(id, "'", ",");
            AlimentPoussin[] cmds = (AlimentPoussin[]) CGenUtil.rechercher(new AlimentPoussin(), (String[])null, (String[])null, c, " and ID in (" + tid + ") order by id asc");
            if (cmds.length == 0) {
                throw new Exception("Aliment poussin introuvable");
            }

            for(int i = 0; i < id.length; ++i) {
                int indRemarque = Integer.parseInt(listeIndice[i]);
                double quantite = Double.valueOf(
                        remarque[indRemarque].replace(",", ".")
                );
                cmds[i].setQuantite(quantite);
                cmds[i].updateToTableWithHisto(refuser, c);
            }

            if (indice == 1) {
                c.commit();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            if (c != null) {
                c.rollback();
            }

            throw new Exception(ex.getMessage());
        } finally {
            if (indice == 1 && c != null) {
                c.close();
            }

        }

    }

    public void suppressionMultiple2(String[] id, String user, Connection c) throws Exception {
        int indice = 0;

        try {
            if (c == null) {
                c = (new UtilDB()).GetConn();
                c.setAutoCommit(false);
                indice = 1;
            }

            if (id == null) {
                throw new Exception("Aucune recette ï¿½ supprimer selectione");
            }

            for(int i = 0; i < id.length; ++i) {
                AlimentPoussin tmp = new AlimentPoussin();
                tmp.setId(Utilitaire.split(id[i], "_")[0]);
                tmp.deleteToTableWithHisto(user, c);
            }

            if (indice == 1) {
                c.commit();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            if (c != null) {
                c.rollback();
            }

            throw new Exception(ex.getMessage());
        } finally {
            if (indice == 1) {
                c.close();
            }

        }

    }
}
