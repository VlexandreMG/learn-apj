package servlet.chatbot;

import chatbot.ChatBot;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import utils.ConstanteAsync;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/response-formattor")
public class ResponseBuilderServlet extends HttpServlet {
    private String buildResponse(String map, String value,String sql) {
        return "Analyse les données de cette requete et repond au prompt de l'utilisateur en une seule phrase a partir des données sans parler de la requete ni de la table,si la devise n'existe pas dans les données, n'en met pas, donne juste le chiffre, calcule bien et verifie tes calculs"+
                "le prompt: '"+value+"'"+
                "la requete: '"+sql+"'"+
                "les données d'habitute si on parle de nom de quelque chose ca correspond au quelque chose_lib, par example si il y a une colonne deviselib, c'est le nom du devise, sa designation, les chiffres n'indique rien dans cette colonne"+
                "Formatte les nombre financiers et n'utilise pas de cours ni de symboles de cours du genre euro, dollars,£,$, etc mais formatte les en nombre financiers"+
                "le données: "+map
                +"ta reponse au prompt apres avoir analyser les données: ";
    }
    public String getResponse(String jsonResponse){
        Gson gson = new Gson();

        // Parser l'objet JSON principal
        JsonObject outerJson = gson.fromJson(jsonResponse, JsonObject.class);

        // Extraire le JSON encodé dans "response"
        return outerJson.get("response").getAsString();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String map = (String) req.getAttribute("maptables");
        String val = req.getParameter("prompt");
        String sql = req.getParameter("requeteSql");
        String lien = (String)req.getAttribute("lien");
        String phrase ="";
        String type = req.getParameter("type");
        String aiResponse = null;

        if(!type.equals("saisie")){
            phrase = buildResponse(ChatBot.removeAllDoubleQuotes(map), val,sql);
            try {
                aiResponse = ChatBot.callGenerativeAI(phrase, ConstanteAsync.API_URL);
                System.out.println("val "+aiResponse);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        List<Map<String, String>> history = (List<Map<String, String>>) req.getSession().getAttribute("chatHistory");
        if (history == null) {
            history = new ArrayList<>();
        }

        // Ajouter le message utilisateur
        Map<String, String> userEntry = new HashMap<>();
        userEntry.put("sender", "user");
        userEntry.put("text", val);
        history.add(userEntry);

        // Exemple de réponse bot
        String botMsg = aiResponse;
        Map<String, String> botEntry = new HashMap<>();
        botEntry.put("sender", "bot");
        botEntry.put("text", botMsg);
        history.add(botEntry);

        req.getSession().setAttribute("chatHistory", history);

        ChatBot chat = new ChatBot(aiResponse,lien);
        System.out.println(chat.getUrl());
        System.out.println(chat.getValue());
        Gson json = new Gson();
        String data = json.toJson(chat);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(data);
    }
}
