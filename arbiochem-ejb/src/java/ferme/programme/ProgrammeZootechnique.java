package ferme.programme;

import bean.ClassFille;
import bean.ClassMere;
import java.sql.Connection;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class ProgrammeZootechnique extends ClassMere {
    private String id;
    private String code;
    private String idsouche;
    private String version;
    private Date daty;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIdsouche() {
        return idsouche;
    }

    @Override
    public String getLiaisonFille() {
        return "idmere";
    }

    @Override
    public String getNomClasseFille() {
        return "ferme.programme.ProgrammeZootechniqueDetails";
    }

    public void setIdsouche(String idsouche) {
        if(this.getMode().compareToIgnoreCase("modif") == 0) {
            if (idsouche == null || idsouche.isEmpty()) {
                throw new IllegalArgumentException("Veuillez specifier la souche");
            }
        }
        this.idsouche = idsouche;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }



    public ProgrammeZootechnique() throws Exception {
        this.setNomTable("PROGRAMMEZOOTECHNIQUE");
        this.setNomClasseFille("ferme.programme.ProgrammeZootechniqueDetails");
        this.setLiaisonFille("idmere");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("PZT","getseq_programmezootechnique");
        this.setId(makePK(c));
    }

    @Override
    public void controler(Connection c) throws Exception {
        super.controler(c);
        this.controlerDoublonAgeSexe();
    }

    public void controlerDoublonAgeSexe() throws Exception {
        ClassFille[] filles = this.getFille();
        if (filles == null) return;
        Set<String> ageSexeVus = new HashSet<>();
        for (ClassFille f : filles) {
            if (!(f instanceof ProgrammeZootechniqueDetails)) continue;
            ProgrammeZootechniqueDetails detail = (ProgrammeZootechniqueDetails) f;
            String age = detail.getAge() == null ? "" : detail.getAge().trim();
            String idsexe = detail.getIdsexe() == null ? "" : detail.getIdsexe().trim();
            if (age.isEmpty()) continue;
            if (!ageSexeVus.add(age + "|" + idsexe)) {
                throw new Exception(ProgrammeZootechniqueDetails.getMessageDoublonAgeSexe(age, idsexe));
            }
        }
    }

    @Override
    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }

    @Override
    public String[] getMotCles() {
        String[] motCles={"id","code", "version"};
        return motCles;
    }

    @Override
    public String[] getValMotCles() {
        String[] valMotCles={"id","code", "version"};
        return valMotCles;
    }
}

