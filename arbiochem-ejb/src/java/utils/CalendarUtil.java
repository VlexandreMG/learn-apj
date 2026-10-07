package utils;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

public class CalendarUtil {

    public static String[] getDebutEtFinDeSemaine(String dateStr) {
        String[] debutEtFinDeSemaine = new String[4];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Conversion de la chaîne vers LocalDate
        LocalDate date = LocalDate.parse(dateStr, formatter);

        // Début et fin de la semaine courante
        LocalDate debutSemaine = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate finSemaine = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        // Dernier jour de la semaine précédente (dimanche avant lundi courant)
        LocalDate dernierJourSemainePrecedente = debutSemaine.minusDays(1);

        // Premier jour de la semaine suivante (lundi après dimanche courant)
        LocalDate premierJourSemaineSuivante = finSemaine.plusDays(1);

        // Remplissage du tableau
        debutEtFinDeSemaine[0] = debutSemaine.format(formatter);
        debutEtFinDeSemaine[1] = finSemaine.format(formatter);
        debutEtFinDeSemaine[2] = dernierJourSemainePrecedente.format(formatter);
        debutEtFinDeSemaine[3] = premierJourSemaineSuivante.format(formatter);

        return debutEtFinDeSemaine;
    }

    public static String[] getDebutEtFinDuMois(String dateStr) {
        String[] debutEtFinDeSemaine = new String[4];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Conversion de la chaîne vers LocalDate
        LocalDate date = LocalDate.parse(dateStr, formatter);

        // Début et fin de la semaine courante
        LocalDate debutSemaine = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate finSemaine = date.with(TemporalAdjusters.lastDayOfMonth());

        // Dernier jour de la semaine précédente (dimanche avant lundi courant)
        LocalDate dernierJourSemainePrecedente = debutSemaine.minusDays(1);

        // Premier jour de la semaine suivante (lundi après dimanche courant)
        LocalDate premierJourSemaineSuivante = finSemaine.plusDays(1);

        // Remplissage du tableau
        debutEtFinDeSemaine[0] = debutSemaine.format(formatter);
        debutEtFinDeSemaine[1] = finSemaine.format(formatter);
        debutEtFinDeSemaine[2] = dernierJourSemainePrecedente.format(formatter);
        debutEtFinDeSemaine[3] = premierJourSemaineSuivante.format(formatter);

        return debutEtFinDeSemaine;
    }

    public static String getMonthName (int numMois){
        String [] mois = new String[]{"Janvier","Fevrier","Mars","Avril","Mais","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Decembre"};
        return mois[numMois-1];
    }

    public static String castDateToFormat(String date,DateTimeFormatter inputFormatter,DateTimeFormatter outputFormatter){
        if (inputFormatter!=null && outputFormatter!=null){
            LocalDate localDate = LocalDate.parse(date,inputFormatter);
            return localDate.format(outputFormatter);
        }
        return date;
    }

    public static String[] getDebutFinAnnee(LocalDate date,String moisDebut,String anneeDebut,String moisFin,String anneeFin) {
        int anneeDebut_int = date.getYear();
        int anneeFin_int = date.getYear();
        LocalDate debut = LocalDate.of(anneeDebut_int, 1, 1);
        LocalDate fin   = LocalDate.of(anneeFin_int, 12, 31);

        if (moisDebut!=null){
            if (anneeDebut!=null){
                anneeDebut_int = Integer.parseInt(anneeDebut);
            }
            debut = LocalDate.of(anneeDebut_int, Integer.parseInt(moisDebut), 1);
        }
        if (moisFin!=null){
            int m = Integer.parseInt(moisFin);
            if (anneeFin!=null){
                anneeFin_int = Integer.parseInt(anneeFin);
            }
            int nbJour = LocalDate.of(anneeFin_int, m, 1).lengthOfMonth();
            fin = LocalDate.of(anneeFin_int, m, nbJour);
        }
        LocalDate debutSuite = debut.minusDays(1);
        LocalDate finSuite = fin.plusDays(1);
        return new String[] { debut.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                fin.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                debutSuite.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                finSuite.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))};
    }

    public static boolean isValidTime(String time) {
        time = formatTimeToHMS(time);
        // Expression régulière pour le format HH:MM:SS
        String regex = "^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$";
        return time != null && time.matches(regex);
    }

    public static String formatTimeToHMS(String time) {
        // Expression régulière pour le format HH:MM:SS
        if (time!=null){
            String [] tab = time.split(":");
            String regex = "^([01]\\d|2[0-3]):[0-5]\\d";
            if (time != null && time.matches(regex)) {
                if (tab.length == 2){
                    time += ":00";
                }
            }
        }
        return time;
    }

    public static int HMSToSecond(String heure){
        if (heure!=null){
            heure = formatTimeToHMS(heure);
            LocalTime time = LocalTime.parse(heure);
            return time.toSecondOfDay();
        }
        return 0;
    }

    public static String secondToHMS(long seconds){
        Duration duration = Duration.ofSeconds(seconds);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long secs = duration.getSeconds() % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }

    public static List<Date> genererDatesParFrequence(Date dateDebut, Date dateFin, String[] jours) {
        List<Date> dates = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Calendar calDebut = Calendar.getInstance();
            Calendar calFin = Calendar.getInstance();
            calDebut.setTime(dateDebut);
            calFin.setTime(dateFin);

            List<Integer> joursCalendar = new ArrayList<>();
            for (String jour : jours) {
                joursCalendar.add(jourVersCalendar(jour.trim()));
            }

            Calendar calCourant = (Calendar) calDebut.clone();
            // Avancer jusqu'au premier jour correspondant
            while (calCourant.before(calFin) || calCourant.equals(calFin)) {
                int jourSemaine = calCourant.get(Calendar.DAY_OF_WEEK);
                if (joursCalendar.contains(jourSemaine)) {
                    dates.add(new java.sql.Date(calCourant.getTimeInMillis()));
                }
                calCourant.add(Calendar.DAY_OF_MONTH, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dates;
    }

    private static int jourVersCalendar(String jour) {
        switch (jour.toLowerCase()) {
            case "lundi": return Calendar.MONDAY;
            case "mardi": return Calendar.TUESDAY;
            case "mercredi": return Calendar.WEDNESDAY;
            case "jeudi": return Calendar.THURSDAY;
            case "vendredi": return Calendar.FRIDAY;
            case "samedi": return Calendar.SATURDAY;
            case "dimanche": return Calendar.SUNDAY;
            default: return -1;
        }
    }
}
