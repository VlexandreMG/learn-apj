package onBoarding;
import java.sql.Date;
public class EmployeOnBoardingSessionLibPDF extends EmployeOnBoardingSessionLib{
    String nom,prenom,posteOccupe,typecontrat;
    Date dateEntree;
    int dureePeriodeEssaie;

     public EmployeOnBoardingSessionLibPDF() throws Exception {
        this.setNomTable("EMPLOYEEONBOARDINGSESSIONS_PDF");
    }

     public String getNom() {
         return nom;
     }

     public void setNom(String nom) {
         this.nom = nom;
     }

     public String getPrenom() {
         return prenom;
     }

     public void setPrenom(String prenom) {
         this.prenom = prenom;
     }

     public String getPosteOccupe() {
         return posteOccupe;
     }

     public void setPosteOccupe(String posteOccupe) {
         this.posteOccupe = posteOccupe;
     }

     public String getTypecontrat() {
         return typecontrat;
     }

     public void setTypecontrat(String typecontrat) {
         this.typecontrat = typecontrat;
     }

     public Date getDateEntree() {
         return dateEntree;
     }

     public void setDateEntree(Date dateEntree) {
         this.dateEntree = dateEntree;
     }

     public int getDureePeriodeEssaie() {
         return dureePeriodeEssaie;
     }

     public void setDureePeriodeEssaie(int dureePeriodeEssaie) {
         this.dureePeriodeEssaie = dureePeriodeEssaie;
     }
    
}
