package paie.cv;

import bean.CGenUtil;
import bean.ClassMAPTable;
import poste.FicheCompetenceFPLib;
import poste.TypeCompetencesFP;

import java.sql.Connection;

public class CVCompetence extends ClassMAPTable {
    private String id;
    private String idcv;
    private String competence;
    private int niveau;
    private String idtypecompetencesfp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdcv() {
        return idcv;
    }

    public void setIdcv(String idcv) {
        this.idcv = idcv;
    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public int getNiveau() {
        return niveau;
    }

     public void setNiveau(int niveau) throws Exception {
        if(this.getMode().equals("modif")){
                 if (niveau < 0 || niveau > 4) {
                throw new Exception("Le niveau doit \\u00EAtre compris entre 0 et 4.");
            }
        }
        this.niveau = niveau;
    }



    public CVCompetence() throws Exception {
        this.setNomTable("CV_COMPETENCE");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CVC","getSeqCvCompetence");
        this.setId(makePK(c));
    }

    @Override
    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }

    public String getIdtypecompetencesfp() {
        return idtypecompetencesfp;
    }

    public void setIdtypecompetencesfp(String idtypecompetencesfp) {
        this.idtypecompetencesfp = idtypecompetencesfp;
    }

    public CVCompetence[] getCVCompetenceByFichePoste(String idCv) throws Exception {
        CV cv = new CV();
        cv.setId(idCv);
        CV[] cvs = (CV[]) CGenUtil.rechercher(cv, null, null," ");
        TypeCompetencesFP typeCompetencesFP = new TypeCompetencesFP();
        if (cvs.length > 0) {
            FicheCompetenceFPLib ficheCompetenceFPLib = new FicheCompetenceFPLib();
            ficheCompetenceFPLib.setIdficheposte(cvs[0].getIdFichePoste());
            FicheCompetenceFPLib[] ficheCompetenceFPLibs = (FicheCompetenceFPLib[]) CGenUtil.rechercher(ficheCompetenceFPLib, null, null, " ");
            if (ficheCompetenceFPLibs.length > 0) {
                CVCompetence[] cvCompetences = new CVCompetence[ficheCompetenceFPLibs.length];
                for (int i = 0; i < ficheCompetenceFPLibs.length; i++) {
                    cvCompetences[i] = new CVCompetence();
                    cvCompetences[i].setIdtypecompetencesfp(ficheCompetenceFPLibs[i].getIdtypecompetencesfp());
                    typeCompetencesFP = typeCompetencesFP.getTypeCompetencesFP(ficheCompetenceFPLibs[i].getIdtypecompetencesfp());
                    cvCompetences[i].setCompetence(typeCompetencesFP.getVal());
//                    cvCompetences[i].setNiveau(ficheCompetenceFPLibs[i].getNiveau());
                }
                return cvCompetences;
            }
        }
        return null;
    }
}

