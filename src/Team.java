import java.util.ArrayList;


public class Team {
   private String teamName;
   private int currentSalary = 0;
   private static int maxSalary = 94143000;
   private final static int maxPlayers = 15;
   private final static int minPlayers = 13;
   private ArrayList<Player> players = new ArrayList<Player>();
   
   public Team(String name) {
      teamName = name;
   }
   
   public void insertPlayer(Player p) {
      players.add(p);
   }
   
   public String getTeam() {
      return teamName;
   }
   
   public boolean fullTeam() {
      if(players.size() >= maxPlayers)
         return true;
      else
         return false;
   }
   
   public boolean fullSalaryCap() {
      if(currentSalary == maxSalary)
         return true;
      else
         return false;
   }
   
   public ArrayList<Player> allPlayers() {
      return players;
   }
}