package paramCompta;

import bean.AdminGen;
import bean.CGenUtil;
import bean.ClassMAPTable;
import bean.TypeObjet;
import org.jfree.data.xy.Vector;

import java.sql.Connection;

public class ParamComptaStatique {
    static TypeObjet[] paramCompta;

    public static TypeObjet[] getParamCompta(Connection c) throws Exception {
        if(paramCompta==null) {
            paramCompta = reload(c);
        }
        return paramCompta;
    }

    public static TypeObjet[] reload(Connection c) throws Exception {
        TypeObjet critere =new TypeObjet();
        critere.setNomTable("PARAMCOMPTA");
        paramCompta = (TypeObjet[]) CGenUtil.rechercher(critere,null, null, c,"");
        return paramCompta;
    }

    public static void setParamCompta(TypeObjet[] paramCompta) {
        ParamComptaStatique.paramCompta = paramCompta;
    }

    public static TypeObjet[] filtrer (String id, String val, String desce) throws Exception{
        String[] col = new String[]{"id","val","desce"};
        String[] valeur = new String[]{id,val,desce};
        paramCompta = (TypeObjet[]) AdminGen.find(getParamCompta(null),col,valeur);
        return paramCompta;
    }
    public static TypeObjet getById (String id) throws Exception{
        String[] col = new String[]{"id"};
        String[] valeur = new String[]{id};

        TypeObjet [] retour = (TypeObjet[]) AdminGen.find(getParamCompta(null),col,valeur);
        if(retour!=null)return retour[0];
        return null;
    }
}
