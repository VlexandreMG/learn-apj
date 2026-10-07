package calculBesoin;

import bean.CGenUtil;
import bean.ClassMAPTable;
import bean.ClassMere;
import utilitaire.UtilDB;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CalculBesoin extends ClassMere {
    private String id;
    private Date daty;
    private String designation;
    private int etat;

    // Getters / Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }


    public ResultatBesoinsParMatiere[] getBesoinsParMatiere(Connection c) throws Exception {
        boolean localConnection = false;

        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                localConnection = true;
            }

            CalculBesoinFille[] filles =
                    (CalculBesoinFille[]) getFille(null, c, "");

            List<ResultatBesoinsParMatiere[]> besoinsParFille = new ArrayList<>();

            for (CalculBesoinFille fille : filles) {
                ResultatBesoins[] besoins =
                        ResultatBesoins.getResultatBesoin(
                                fille.getIdProduit(),
                                fille.getQte(),
                                c
                        );

                besoinsParFille.add(
                        ResultatBesoinsParMatiere.parse(besoins, c)
                );
            }

            return ResultatBesoinsParMatiere.merge(besoinsParFille);

        } finally {
            if (localConnection && c != null) {
                try {
                    c.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Méthode pour construire la PK
    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CB", "getseqCALCULBESOIN");
        this.setId(makePK(c));
    }

    // Constructeur
    public CalculBesoin() throws Exception {
        this.setNomTable("CalculBesoin");
        this.setLiaisonFille("idMere"); // FK vers la table fille
        this.setNomClasseFille("calculBesoin.CalculBesoinFille");
    }

    // Retourne l'ID
    @Override
    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }

    // Exemple de validation avant insertion
    @Override
    public Object validerObject(String u, Connection c) throws Exception {
        // Vérifie s'il existe déjà un CalculBesoin pour cette date et designation
        return super.validerObject(u, c);
    }

    // Récupération par ID
    public CalculBesoin getById(String[] ids, Connection c) throws Exception {
        if (ids == null || ids.length == 0) {
            throw new Exception("Aucun ID spécifié");
        }
        int verif = 0;
        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                verif = 1;
            }
            String awhere = " AND ID='" + ids[0] + "'";
            CalculBesoinFille[] filles = (CalculBesoinFille[]) CGenUtil.rechercher(new CalculBesoinFille(), null, null, null, awhere);
            if (filles == null || filles.length == 0) {
                return null;
            }
            return filles[0].getCalculBesoin(c);
        } finally {
            if (c != null && verif == 1) {
                c.close();
            }
        }
    }
}