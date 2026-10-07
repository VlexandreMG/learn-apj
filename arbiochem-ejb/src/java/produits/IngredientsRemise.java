package produits;

import bean.CGenUtil;
import client.Client;
import utilitaire.UtilDB;

import java.sql.Connection;
import java.sql.Date;

public class IngredientsRemise extends Ingredients {


    public IngredientsRemise() throws Exception
    {
        this.setNomTable("ST_INGREDIENTSVENTEPOS_REM");
    }

    private double remise;
    private double pvRemise;
    private String idCategorieClient;
    private String idPoint;
    private String idRemiseFille;
    private String idRemise;
    private String remiseNom;
    private Date datyRemise;
    private Date dateDebutRemise;
    private Date dateFinRemise;

    public double getRemise() {
        return remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
    }

    public double getPvRemise() {
        return pvRemise;
    }

    public void setPvRemise(double pvRemise) {
        this.pvRemise = pvRemise;
    }

    public String getIdCategorieClient() {
        return idCategorieClient;
    }

    public void setIdCategorieClient(String idCategorieClient) {
        this.idCategorieClient = idCategorieClient;
    }

    public String getIdPoint() {
        return idPoint;
    }

    public void setIdPoint(String idPoint) {
        this.idPoint = idPoint;
    }

    public String getIdRemiseFille() {
        return idRemiseFille;
    }

    public void setIdRemiseFille(String idRemiseFille) {
        this.idRemiseFille = idRemiseFille;
    }

    public String getIdRemise() {
        return idRemise;
    }

    public void setIdRemise(String idRemise) {
        this.idRemise = idRemise;
    }

    public Date getDatyRemise() {
        return datyRemise;
    }

    public void setDatyRemise(Date datyRemise) {
        this.datyRemise = datyRemise;
    }

    public String getRemiseNom() {
        return remiseNom;
    }

    public void setRemiseNom(String remiseNom) {
        this.remiseNom = remiseNom;
    }

    public Date getDateDebutRemise() {
        return dateDebutRemise;
    }

    public void setDateDebutRemise(Date dateDebutRemise) {
        this.dateDebutRemise = dateDebutRemise;
    }

    public Date getDateFinRemise() {
        return dateFinRemise;
    }

    public void setDateFinRemise(Date dateFinRemise) {
        this.dateFinRemise = dateFinRemise;
    }

    public static IngredientsRemise getIngredientRemise(String idClient, String idPoint, String idProduit) throws Exception
    {

        Connection c = null;

        try{

            c = new UtilDB().GetConn();

            Client client = new Client();
            client.setId(idClient);

            Client[] clients = (Client[]) CGenUtil.rechercher(client, null, null, c, "");

            if (clients == null)
            {
                throw new Exception("Pas de client");
            }


            IngredientsRemise ingredientsRemise = new IngredientsRemise();
            ingredientsRemise.setId(idProduit);

            String aWhere = "  AND REMISE <> 0";
            if (idPoint != null)
            {
                aWhere += " AND (idPoint = '" + idPoint + "' OR idPoint IS NULL)";
            }

            if (idClient != null)
            {
                aWhere += " AND (idCategorieClient = '" + clients[0].getIdTypeClient().trim() + "' OR idCategorieClient IS NULL)";
            }

            System.out.println("THIS IS IDTYPECLIENT = " + clients[0].getIdTypeClient().trim());

            aWhere += " AND CURRENT_DATE BETWEEN dateDebutRemise AND dateFinRemise";

            aWhere += " ORDER BY DATYREMISE DESC";

            IngredientsRemise[] ingredientsRemisesAvecRemise = (IngredientsRemise[]) CGenUtil.rechercher(ingredientsRemise, null, null, c, aWhere);

            System.out.println("DID A CHECK");

            if (ingredientsRemisesAvecRemise == null || ingredientsRemisesAvecRemise.length == 0)
            {
                ingredientsRemise.setNomTable("ST_INGREDIENTSVENTEPOS");
                IngredientsRemise[] ingredientsRemisesAvecRemiseNew = (IngredientsRemise[]) CGenUtil.rechercher(ingredientsRemise, null, null, c, "");

                if (ingredientsRemisesAvecRemiseNew == null)
                {
                    throw new Exception("N'est pas de produit");
                }

                System.out.println("GIVING HERE");
                return ingredientsRemisesAvecRemiseNew[0];

            }

            System.out.println("NOT NULL");
            return ingredientsRemisesAvecRemise[0];

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {

            if (c != null) {
                c.close();
            }

        }


    }
}
