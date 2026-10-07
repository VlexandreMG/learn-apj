package paie.recrutement;

import bean.CGenUtil;
import bean.ClassEtat;
import reporting.mail.Mail;
import reporting.mail.MailRappel;
import reporting.utilitaire.ConstanteCR;
import utilitaire.UtilDB;
import utils.ConstanteAxelle;

import java.sql.Connection;
import java.sql.Date;

public class Candidatures extends ClassEtat {
    private String id;
    private String idcandidat;
    private String idoffreemploie;
    private Date dateapplication;
    private String remarque;
    private String raisonrefus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdcandidat() {
        return idcandidat;
    }

    public void setIdcandidat(String idcandidat) {
        this.idcandidat = idcandidat;
    }

    public String getIdoffreemploie() {
        return idoffreemploie;
    }

    public void setIdoffreemploie(String idoffreemploie) {
        this.idoffreemploie = idoffreemploie;
    }

    public Date getDateapplication() {
        return dateapplication;
    }

    public void setDateapplication(Date dateapplication) {
        this.dateapplication = dateapplication;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public String getRaisonrefus() {
        return raisonrefus;
    }

    public void setRaisonrefus(String raisonrefus) {
        this.raisonrefus = raisonrefus;
    }



    public Candidatures() throws Exception {
        this.setNomTable("CANDIDATURES");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CDT","getseq_candidatures");
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


    public void refuser(String u) throws Exception {
        Connection c = null;
        try {
            if (c == null){
                UtilDB util = new UtilDB();
                c = util.GetConn();
                c.setAutoCommit(false);
            }
            Candidatures candidatures = new Candidatures();
            candidatures.setId(this.getId());
            Candidatures[] candidatures1 = (Candidatures[]) CGenUtil.rechercher(candidatures, null, null, "");
            if (candidatures1.length > 0) {
                candidatures = candidatures1[0];
                if (candidatures.getEtat() > -1){
                    candidatures.setEtat(-1);
                    candidatures.updateToTableWithHisto(u, c);
                    Candidat candidat = new Candidat();
                    candidat.setId(candidatures.getIdcandidat());
                    Candidat[] candidats = (Candidat[]) CGenUtil.rechercher(candidat, null, null, "");
                    if (candidats.length > 0){
                        candidat = candidats[0];
                    } else {
                        throw new Exception("Candidat non trouve!");
                    }

                    MailRappel mailRappel = new MailRappel();
                    mailRappel.setNom("Axelle");
                    mailRappel.setTitre("Suite à votre candidature - Réponse négative");
                    String[] contacts = new String[]{
                            ConstanteAxelle.addresse1,
                            ConstanteAxelle.addresse2,
                    };
                    mailRappel.setContacts(contacts);
                    String[] paragraphes = new String[] {
                            "Nous vous remercions pour l'intéret que vous avez porté à notre entreprise ainsi que pour le temps consacré à votre candidature.",

                            "Après une analyse attentive de votre profil, nous regrettons de vous informer que votre candidature n'a pas été retenue pour ce poste.",

                            "Nous avons recu un grand nombre de candidatures et avons sélectionné des profils correspondant davantage aux critères spécifiques du poste.",

                            "Nous vous encourageons à consulter régulièrement nos futures opportunités et à postuler de nouveau si une offre correspond à votre profil.",

                            "Nous vous souhaitons une bonne continuation et plein succès dans vos projets professionnels."
                    };
                    mailRappel.setParagraphe(paragraphes);
                    String message = mailRappel.genererMailRappel();

                    Mail mail = new Mail(ConstanteCR.getMailRapport()[0], ConstanteCR.getMailRapport()[1], "Refus", message);
                    mail.setTo(candidat.getEmail());
                    mail.send();
                } else {
                    throw new Exception("Candidature deja refuser");
                }
            } else {
                throw new Exception("Candidature introuvable!");
            }
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }
}

