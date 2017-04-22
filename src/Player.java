import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Random;


public class Player {
   DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
   Date nowDate = new Date();
   Calendar cal = Calendar.getInstance();
   private String name;
   private int salary;
   private Team team;
   private String position;
   private Date birthdate;
   private int yrsinLeague;
   
   private int overall;
   private int strength;
   private int speed;
   private Hashtable<String, Integer> height
   = new Hashtable<String, Integer>();
   private int drvlayup;
   private int stdlayup;
   private int drvdunk;
   private int stddunk;
   private int closhot;
   private int midshot;
   private int thrshot;
   private int post;
   private int pass;
   private int iq;
   private int dribbling;
   private int offrebound;
   private int defrebound;
   private int block;
   private int steal;

   public Player(String name, Team team) {
      this.name = name;
      this.team = team;
      String[] positions = {"SG", "PG", "SF", "PF", "C"};
      int randNum = new Random().nextInt(positions.length);
      position = positions[randNum];
      cal.setTime(nowDate);
      int range = cal.get(Calendar.YEAR) - cal.get(Calendar.YEAR) + 22;
      int year = new Random().nextInt(range + 1) + cal.get(Calendar.YEAR) - 40;
      int dayOfYear = new Random().nextInt(365 + 1) + 1;
      Calendar calendar = Calendar.getInstance();
      calendar.set(Calendar.YEAR, year);
      calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);
      birthdate = calendar.getTime();
      int possibleYears = cal.get(Calendar.YEAR) - 18 - year;
      if(possibleYears > 0) {
         double rand = Math.random();
         rand = Math.sqrt(rand);
         rand *= possibleYears;
         int next = (int) rand;
         yrsinLeague = next;
      }
      else 
         yrsinLeague = possibleYears;
      
   }
   
   public String getPlayerName() {
      return name;
   }
   
   public String getPlayerInfo() {
      return name + ", " + team.getTeam() + ", " + position + ", " + yrsinLeague + ", " + dateFormat.format(birthdate) + ", " + salary;
   }
   
   private void randomizeStats() {
      switch(position) {
         case "C":
            int centerHts[] = {6,6,6,6,6,6,7};
            int rnd = new Random().nextInt(centerHts.length);
            int feet = centerHts[rnd];
            height.put("feet", feet);
            if(feet == 7) {
               int centerInches[] = {0,0,0,1,1,2};
               rnd = new Random().nextInt(centerInches.length);
               int inches = centerInches[rnd];
               height.put("inches", inches);
            }
            else {
               int centerInches[] = {9,10,10,10,11,11};
               rnd = new Random().nextInt(centerInches.length);
               int inches = centerInches[rnd];
               height.put("inches", inches);
            }
            strength = new Random().nextInt(40) + 60;
      }
   }
}