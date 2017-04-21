import java.util.ArrayList;


public class Team {
   private String teamName;
   private int currentSalary = 0;
   private int maxSalary = 94143000;
   private final int maxPlayers = 15;
   private final int minPlayers = 13;
   private static ArrayList<Player> players = new ArrayList<Player>();
   
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
      if(players.size() == maxPlayers)
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