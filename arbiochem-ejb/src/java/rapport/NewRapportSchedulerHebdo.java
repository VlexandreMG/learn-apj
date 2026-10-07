package rapport;

import utilitaire.Utilitaire;
import utils.ConstanteSocobis;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.sql.Date;

@Singleton
@Startup
public class NewRapportSchedulerHebdo {

    @Schedule(
        dayOfWeek = "Mon",
        hour = "9",
        minute = "0",
        second = "0",
        persistent = false
    )
    public void envoyerCRHebdomadaire() {
        try {
            if (!ConstanteSocobis.EST_PROD) {
                return;
            }

            System.out.println(
                    "Début d'envoi automatique du CR hebdomadaire"
            );

            Date dateFin = Utilitaire.dateDuJourSql();
            Date dateDebut = Utilitaire.ajoutJourDate(dateFin,-4);

            CRJournalier crJournalier = new CRJournalier();

            crJournalier.sendPageEmailHebdomadaire(
                    dateDebut,
                    dateFin
            );

            System.out.println(
                    "Envoi du CR hebdomadaire terminé : "
                            + dateDebut
                            + " au "
                            + dateFin
            );

        } catch (Exception e) {
            System.err.println(
                    "Erreur lors de l'envoi du CR hebdomadaire"
            );
            e.printStackTrace();
        }
    }
}
