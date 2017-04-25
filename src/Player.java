import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Player {
   DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
   Date nowDate = new Date();
   Calendar cal = Calendar.getInstance();
   Calendar calNow = Calendar.getInstance();
   Random rand = new Random();
   private String name;
   private int salary;
   private Team team;
   private String position;
   private Calendar birthdate;
   private int age;
   private int yrs_league;
   
   // STATS
   private Hashtable<String, Integer> height = new Hashtable<String, Integer>();
   private int growth_potential;
   private int strength = 0;
   private int speed = 0;
   private int drvlayup = 0;
   private int stdlayup = 0;
   private int drvdunk = 0;
   private int stddunk = 0;
   private int closhot = 0;
   private int midshot = 0;
   private int thrshot = 0;
   private int post = 0;
   private int pass = 0;
   private int iq = 0;
   private int dribbling = 0;
   private int offrebound = 0;
   private int defrebound = 0;
   private int block = 0;
   private int steal = 0;
   private int overall;

   public Player(String name, Team team) {
      this.name = name;
      this.team = team;
      String[] positions = {"SG", "PG", "SF", "PF", "C"};
      position = positions[rand.nextInt(positions.length)];
      calNow.setTime(nowDate);
      int range = calNow.get(Calendar.YEAR) - calNow.get(Calendar.YEAR) + 22;
      int year = rand.nextInt(range + 1) + calNow.get(Calendar.YEAR) - 40;
      int dayOfYear = rand.nextInt(365 + 1) + 1;
      cal.set(Calendar.YEAR, year);
      cal.set(Calendar.DAY_OF_YEAR, dayOfYear);
      birthdate = cal;
      
      // IMPORTANT! Remember to update age every day passing      
      age = ageCalculator(birthdate, calNow);
      int possibleYears = calNow.get(Calendar.YEAR) - 18 - year;
      if(possibleYears > 0) {
         double rand1 = Math.random();
         rand1 = Math.sqrt(rand1);
         rand1 *= possibleYears;
         int next = (int) rand1;
         yrs_league = next;
      }
      else 
         yrs_league = possibleYears;
      randomizeStats();
   }
   
   public String getPlayerName() {
      return name;
   }
   
   public String getPlayerInfo() {
      return name + ", " + team.getTeam() + ", " + position + ", " 
            + yrs_league + ", " + dateFormat.format(birthdate.getTime()) 
            + ", " + salary + ", " + age;
   }
   
   public String getPlayerStats() {
      return name + ", " + team.getTeam() + ", " + position + ", "
            + overall +  ", " + strength+", "+speed+", "+drvlayup+", "
            +stdlayup+", "+drvdunk+", "+stddunk+", "+closhot+", "
            +midshot+", "+thrshot+", "+post+", "+pass+", "+iq+", "
            +dribbling+", "+offrebound+", "+defrebound+", "+block+", "+steal;
   
   }
   
   private void randomizeStats() {
      int rnd = rand.nextInt(100+1)+1;
      if(age <= 25) {
         growth_potential = 
               (rnd <= 8) ? rand.nextInt(20+1)                   : 
               (rnd > 8 && rnd <= 20) ? rand.nextInt(19+1) + 21  :
               (rnd > 20 && rnd <= 45) ? rand.nextInt(9+1)+ 41    :
               (rnd > 45 && rnd <= 65) ? rand.nextInt(9+1) + 51   :
               (rnd > 65 && rnd <= 77) ? rand.nextInt(9+1)+ 61    :
               (rnd > 77 && rnd <= 84) ? rand.nextInt(9+1) + 71   :
               (rnd > 84 && rnd <= 90) ? rand.nextInt(9+1) + 81   :
               (rnd > 90 && rnd <= 96) ? rand.nextInt(4+1) + 91   :
                  rand.nextInt(4+1)+95;
      }
      else if (age > 25 && age <= 29) {
         growth_potential = 
               (rnd <= 15) ? rand.nextInt(20+1)                   : 
               (rnd > 15 && rnd <= 30) ? rand.nextInt(19+1) + 21  :
               (rnd > 30 && rnd <= 50) ? rand.nextInt(9+1)+ 41    :
               (rnd > 50 && rnd <= 71) ? rand.nextInt(9+1) + 51   :
               (rnd > 71 && rnd <= 81) ? rand.nextInt(9+1)+ 61    :
               (rnd > 81 && rnd <= 87) ? rand.nextInt(9+1) + 71   :
               (rnd > 87 && rnd <= 92) ? rand.nextInt(9+1) + 81   :
               (rnd > 92 && rnd <= 97) ? rand.nextInt(4+1) + 91   :
                  rand.nextInt(4+1)+95;
      }
      else {
         growth_potential = 
               (rnd <= 30) ? rand.nextInt(20+1)                   : 
               (rnd > 30 && rnd <= 55) ? rand.nextInt(19+1) + 21  :
               (rnd > 55 && rnd <= 65) ? rand.nextInt(9+1)+ 41    :
               (rnd > 65 && rnd <= 80) ? rand.nextInt(9+1) + 51   :
               (rnd > 80 && rnd <= 87) ? rand.nextInt(9+1)+ 61    :
               (rnd > 87 && rnd <= 92) ? rand.nextInt(9+1) + 71   :
               (rnd > 92 && rnd <= 95) ? rand.nextInt(9+1) + 81   :
               (rnd > 95 && rnd <= 98) ? rand.nextInt(4+1) + 91   :
                  rand.nextInt(4+1)+95;
      }
      switch(position) {
         case "C":
            int center_hts[] = {6,6,6,6,6,6,7};
            rnd = rand.nextInt(center_hts.length);
            int feet = center_hts[rnd];
            height.put("feet", feet);
            if(feet == 7) {
               int center_inches[] = {0,0,0,0,0,0,0,1,1,1,1,1,2,2,2,3,3,4};
               rnd = rand.nextInt(center_inches.length);
               height.put("inches", center_inches[rnd]);
            }
            else {
               int center_inches[] = {9,10,10,10,11,11};
               rnd = rand.nextInt(center_inches.length);
               height.put("inches", center_inches[rnd]);
            }
            rnd = rand.nextInt(100+1)+1;
            strength =
                  (rnd <= 75) ? rand.nextInt(19+1) + 40               :
                  (rnd > 75 && rnd <= 87) ? rand.nextInt(19+1) + 60   :
                  (rnd > 87 && rnd <= 95) ? rand.nextInt(9+1) + 80    :
                     rand.nextInt(5+1) + 90;
            rnd = rand.nextInt(100+1)+1;
            speed =
                  (rnd <= 80) ? rand.nextInt(19+1) + 30              :
                  (rnd > 80 && rnd <= 96) ? rand.nextInt(19+1) + 50  :
                     rand.nextInt(9+1) + 70;
            drvlayup = (rand.nextInt(55+1)+30+speed)/2;
            stdlayup = rand.nextInt(65+1)+35;
            drvdunk = (rand.nextInt(55+1)+30+speed+(14+(height.get("inches")+(height.get("feet")*12))))/3;
            stddunk = (rand.nextInt(60+1)+40+(14+(height.get("inches")+(height.get("feet")*12))))/2;
            closhot = rand.nextInt(55+1)+30;
            midshot = rand.nextInt(55+1)+30;
            thrshot = rand.nextInt(45+1)+30;
            post = (rand.nextInt(55+1)+40+strength)/2;
            pass = rand.nextInt(60+1)+10;
            iq = (rand.nextInt(70+1)+20+60+age)/2;
            dribbling = rand.nextInt(60+1)+10;
            offrebound = (rand.nextInt(70+1)+30+(14+(height.get("inches")+(height.get("feet")*12))))/2;
            defrebound = (rand.nextInt(70+1)+30+(14+(height.get("inches")+(height.get("feet")*12))))/2;
            block = (rand.nextInt(70+1)+30+(14+(height.get("inches")+(height.get("feet")*12))))/2;
            steal = (rand.nextInt(60+1)+20+speed)/2;
            overall = (strength+speed+drvlayup+stdlayup+drvdunk+stddunk
                  +closhot+midshot+thrshot+post+pass+iq+dribbling+offrebound+defrebound+block+steal)/17;
            break;
         case "PF":
            int pforward_hts[] = {6,6,6,6,6,6,6,6,7};
            rnd = rand.nextInt(pforward_hts.length);
            feet = pforward_hts[rnd];
            height.put("feet", feet);
            if(feet == 7) {
               int pf_inches[] = {0,0,0,0,0,0,0,0,1,1,1,1,1,2,2,2,3};
               rnd = rand.nextInt(pf_inches.length);
               height.put("inches", pf_inches[rnd]);
            }
            else {
               int pf_inches[] = {7,8,8,9,9,9,10,10,11};
               rnd = rand.nextInt(pf_inches.length);
               height.put("inches", pf_inches[rnd]);
            }
            rnd = rand.nextInt(100+1)+1;
            strength =
                  (rnd <= 77) ? rand.nextInt(19+1) + 40               :
                  (rnd > 77 && rnd <= 89) ? rand.nextInt(19+1) + 60   :
                  (rnd > 89 && rnd <= 97) ? rand.nextInt(9+1) + 80    :
                     rand.nextInt(5+1) + 90;
            rnd = rand.nextInt(100+1)+1;
            speed =
                  (rnd <= 70) ? rand.nextInt(19+1) + 30              :
                  (rnd > 75 && rnd <= 95) ? rand.nextInt(19+1) + 50  :
                     rand.nextInt(9+1) + 70;
            drvlayup = (rand.nextInt(60+1)+30+speed)/2;
            stdlayup = rand.nextInt(60+1)+35;
            drvdunk = (rand.nextInt(60+1)+30+speed+(14+(height.get("inches")+(height.get("feet")*12))))/3;
            stddunk = (rand.nextInt(60+1)+35+(14+(height.get("inches")+(height.get("feet")*12))))/2;
            closhot = rand.nextInt(55+1)+30;
            midshot = rand.nextInt(55+1)+30;
            thrshot = rand.nextInt(50+1)+30;
            post = (rand.nextInt(55+1)+35+strength)/2;
            pass = rand.nextInt(70+1)+10;
            iq = (rand.nextInt(70+1)+20+60+age)/2;
            dribbling = rand.nextInt(65+1)+15;
            offrebound = (rand.nextInt(65+1)+30+(14+(height.get("inches")+(height.get("feet")*12))))/2;
            defrebound = (rand.nextInt(65+1)+30+(14+(height.get("inches")+(height.get("feet")*12))))/2;
            block = (rand.nextInt(65+1)+30+(14+(height.get("inches")+(height.get("feet")*12))))/2;
            steal = (rand.nextInt(65+1)+20+speed)/2;
            overall = (strength+speed+drvlayup+stdlayup+drvdunk+stddunk
                  +closhot+midshot+thrshot+post+pass+iq+dribbling+offrebound+defrebound+block+steal)/17;
            break;
         case "SF":
            height.put("feet", 6);
            int sf_inches[] = {5,6,6,7,7,7,8,8};
            rnd = rand.nextInt(sf_inches.length);
            height.put("inches", sf_inches[rnd]);
            rnd = rand.nextInt(100+1)+1;
            strength =
                  (rnd <= 82) ? rand.nextInt(19+1) + 40               :
                  (rnd > 82 && rnd <= 96) ? rand.nextInt(19+1) + 60   :
                     rand.nextInt(9+1) + 80;
            rnd = rand.nextInt(100+1)+1;
            speed =
                  (rnd <= 65) ? rand.nextInt(19+1) + 40              :
                  (rnd > 65 && rnd <= 97) ? rand.nextInt(19+1) + 60  :
                     rand.nextInt(9+1) + 80;
            drvlayup = (rand.nextInt(65+1)+30+speed)/2;
            stdlayup = rand.nextInt(55+1)+30;
            drvdunk = (rand.nextInt(65+1)+30+speed+(14+(height.get("inches")+(height.get("feet")*12))))/3;
            stddunk = (rand.nextInt(55+1)+30+(14+(height.get("inches")+(height.get("feet")*12))))/2;
            closhot = rand.nextInt(55+1)+40;
            midshot = rand.nextInt(55+1)+40;
            thrshot = rand.nextInt(55+1)+35;
            post = (rand.nextInt(55+1)+30+strength)/2;
            pass = rand.nextInt(60+1)+25;
            iq = (rand.nextInt(70+1)+20+60+age)/2;
            dribbling = rand.nextInt(60+1)+30;
            offrebound = (rand.nextInt(60+1)+30+(14+(height.get("inches")+(height.get("feet")*12))))/2;
            defrebound = (rand.nextInt(60+1)+30+(14+(height.get("inches")+(height.get("feet")*12))))/2;
            block = (rand.nextInt(60+1)+25+(14+(height.get("inches")+(height.get("feet")*12))))/2;
            steal = (rand.nextInt(65+1)+30+speed)/2;
            overall = (strength+speed+drvlayup+stdlayup+drvdunk+stddunk
                  +closhot+midshot+thrshot+post+pass+iq+dribbling+offrebound+defrebound+block+steal)/17;
            break;
         case "SG":
            height.put("feet", 6);
            int sg_inches[] = {1,2,3,3,4,4,4,4,5,5,5,6};
            rnd = rand.nextInt(sg_inches.length);
            height.put("inches", sg_inches[rnd]);
            rnd = rand.nextInt(100+1)+1;
            strength =
                  (rnd <= 80) ? rand.nextInt(19+1) + 30               :
                  (rnd > 80 && rnd <= 95) ? rand.nextInt(19+1) + 50   :
                     rand.nextInt(9+1) + 70;
            rnd = rand.nextInt(100+1)+1;
            speed =
                  (rnd <= 62) ? rand.nextInt(19+1) + 40              :
                  (rnd > 62 && rnd <= 91) ? rand.nextInt(19+1) + 60  :
                  (rnd > 91 && rnd <= 98) ? rand.nextInt(9+1) + 80 :
                     rand.nextInt(5+1) + 90;
            drvlayup = (rand.nextInt(65+1)+35+speed)/2;
            stdlayup = rand.nextInt(60+1)+25;
            drvdunk = (rand.nextInt(65+1)+35+speed+(14+(height.get("inches")+(height.get("feet")*12))))/3;
            stddunk = (rand.nextInt(60+1)+20+(14+(height.get("inches")+(height.get("feet")*12))))/2;
            closhot = rand.nextInt(50+1)+45;
            midshot = rand.nextInt(50+1)+45;
            thrshot = rand.nextInt(55+1)+45;
            post = (rand.nextInt(60+1)+25+strength)/2;
            pass = rand.nextInt(55+1)+30;
            iq = (rand.nextInt(70+1)+20+60+age)/2;
            dribbling = rand.nextInt(55+1)+35;
            offrebound = (rand.nextInt(65+1)+15+(14+(height.get("inches")+(height.get("feet")*12))))/2;
            defrebound = (rand.nextInt(65+1)+20+(14+(height.get("inches")+(height.get("feet")*12))))/2;
            block = (rand.nextInt(70+1)+15+(14+(height.get("inches")+(height.get("feet")*12))))/2;
            steal = (rand.nextInt(65+1)+35+speed)/2;
            overall = (strength+speed+drvlayup+stdlayup+drvdunk+stddunk
                  +closhot+midshot+thrshot+post+pass+iq+dribbling+offrebound+defrebound+block+steal)/17;
            break;
         default: // PG
            int pg_hts[] = {5,6,6,6,6};
            rnd = rand.nextInt(pg_hts.length);
            feet = pg_hts[rnd];
            height.put("feet", feet);
            if(feet == 5) {
               int pg_inches[] = {9,10,10,11,11,11,11,11,11};
               rnd = rand.nextInt(pg_inches.length);
               height.put("inches", pg_inches[rnd]);
            }
            else {
               int pg_inches[] = {1,1,1,2,2,2,2,3,3,4};
               rnd = rand.nextInt(pg_inches.length);
               height.put("inches", pg_inches[rnd]);
            }
            rnd = rand.nextInt(100+1)+1;
            strength =
                  (rnd <= 90) ? rand.nextInt(19+1) + 30               :
                     rand.nextInt(19+1) + 50;
            rnd = rand.nextInt(100+1)+1;
            speed =
                  (rnd <= 55) ? rand.nextInt(9+1) + 50              :
                  (rnd > 55 && rnd <= 87) ? rand.nextInt(19+1) + 60  :
                  (rnd > 87 && rnd <= 95) ? rand.nextInt(9+1) + 80 :
                     rand.nextInt(5+1) + 90;
            drvlayup = (rand.nextInt(65+1)+40+speed)/2;
            stdlayup = rand.nextInt(65+1)+20;
            drvdunk = (rand.nextInt(60+1)+30+speed+(14+(height.get("inches")+(height.get("feet")*12))))/3;
            stddunk = (rand.nextInt(50+1)+10+(14+(height.get("inches")+(height.get("feet")*12))))/2;
            closhot = rand.nextInt(50+1)+45;
            midshot = rand.nextInt(50+1)+45;
            thrshot = rand.nextInt(50+1)+45;
            post = (rand.nextInt(60+1)+15+strength)/2;
            pass = rand.nextInt(55+1)+45;
            iq = (rand.nextInt(70+1)+20+60+age)/2;
            dribbling = rand.nextInt(55+1)+45;
            offrebound = (rand.nextInt(65+1)+10+(14+(height.get("inches")+(height.get("feet")*12))))/2;
            defrebound = (rand.nextInt(65+1)+10+(14+(height.get("inches")+(height.get("feet")*12))))/2;
            block = (rand.nextInt(65+1)+10+(14+(height.get("inches")+(height.get("feet")*12))))/2;
            steal = (rand.nextInt(60+1)+40+speed)/2;
            overall = (strength+speed+drvlayup+stdlayup+drvdunk+stddunk
                  +closhot+midshot+thrshot+post+pass+iq+dribbling+offrebound+defrebound+block+steal)/17;
            break;
      }
   }
   
   private int ageCalculator(Calendar start, Calendar end) {
      int diff_months = end.get(Calendar.MONTH) - start.get(Calendar.MONTH);
      int age_year = end.get(Calendar.YEAR) - start.get(Calendar.YEAR);
      int age_month = end.get(Calendar.DAY_OF_MONTH) - start.get(Calendar.DAY_OF_MONTH);
      return
            (diff_months < 0) ? 
                  age_year-1        : 
            (diff_months == 0) ? 
                  (age_month < 0) ?
                        age_year-1  :
                        age_year    :
                  age_year;
   }
   
}