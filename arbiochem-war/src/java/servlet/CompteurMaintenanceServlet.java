package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import com.google.gson.Gson;
import maintenance.configuration.CompteurMaintenance;

@WebServlet("/CompteurMaintenanceServlet")
public class CompteurMaintenanceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String idLigneParam    = request.getParameter("idLigne");
            String idCategorieParam = request.getParameter("idCategorie");

            if (idLigneParam == null || idCategorieParam == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"error\": \"Paramètres manquants : Ligne et Catégorie sont requis\"}");
                return;
            }
            CompteurMaintenance compteur = new CompteurMaintenance();
            compteur.setIdLigne(idLigneParam);
            compteur.setIdCategorie(idCategorieParam);
            CompteurMaintenance result = compteur.getLastCompteur();
            if (result == null) {
                result = compteur;
            }
            Gson gson = new Gson();
            out.print(gson.toJson(result));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            String errMsg = e.getMessage() != null ? e.getMessage().replace("\"", "'") : "Erreur inconnue";
            out.print("{\"error\": \"Erreur serveur : " + errMsg + "\"}");
        } finally {
            out.flush();
        }
    }
}