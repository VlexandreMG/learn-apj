package paramCompta;

import bean.AdminGen;
import bean.CGenUtil;
import bean.TypeObjet;
import org.apache.poi.ss.formula.functions.T;

import java.sql.Connection;

public class ParamGlobal extends TypeObjet{
    static TypeObjet[] paramGlobal;

    public ParamGlobal() {
        this.setNomTable("paramConstanteSocobis");
    }

    public static TypeObjet[] getParamGlobal(Connection c) throws Exception {
        if(paramGlobal==null) {
            paramGlobal = reload(c);
        }
        return paramGlobal;
    }

    public static TypeObjet[] reload(Connection c) throws Exception {
        ParamGlobal critere = new ParamGlobal();
        paramGlobal = (TypeObjet[]) CGenUtil.rechercher(critere,null, null, c,"");
        return paramGlobal;
    }

    public TypeObjet[] filtrer (String id, String val, String desce) throws Exception{
        String[] col = new String[]{"id","val","desce"};
        String[] valeur = new String[]{id,val,desce};
        paramGlobal = (TypeObjet[]) AdminGen.find(getParamGlobal(null),col,valeur);
        return paramGlobal;
    }

    public static TypeObjet getById (String id) throws Exception{
        String[] col = new String[]{"id"};
        String[] valeur = new String[]{id};

        TypeObjet [] retour = (TypeObjet[]) AdminGen.find(getParamGlobal(null),col,valeur);
        if(retour!=null)return retour[0];
        return null;
    }
}
