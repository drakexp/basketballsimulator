import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Main{
   static String genericTeamNames[] = {"Atlanta Hawks", "Boston Celtics", "Brooklyn Nets", "Charlotte Hornets", "Chicago Bulls", "Cleveland Cavaliers",
      "Dallas Mavericks", "Denver Nuggets", "Detroit Pistons", "Golden State Warriors", "Houston Rockets", "Indiana Pacers", "LA Clippers",
      "Los Angeles Lakers", "Memphis Grizzlies", "Miami Heat", "Milwaukee Bucks", "Minnesota Timberwolves", "New Orleans Pelicans", 
      "New York Knicks", "Oklahoma City Thunder", "Orlando Magic", "Philadelphia 76ers", "Phoenix Suns", "Portland Trailblazers", "Sacramento Kings",
      "San Antonio Spurs", "Toronto Raptors", "Utah Jazz", "Washington Wizards"};
   static Hashtable<String, Team> teams = new Hashtable<String, Team>();
   static NameRandomizer nameRandomizer;
   public static void main(String[] args){

      createGenericTeams();
      randomizePlayers();
   }
   
   public static void createGenericTeams(){
      for(int i = 0; i < genericTeamNames.length; i++) {
         Team team = new Team(genericTeamNames[i]);
         teams.put(genericTeamNames[i], team);
      }
//      Set<String> keys = teams.keySet();
//      for(String key: keys){
//         System.out.println("Value of "+key+" is: "+teams.get(key).getTeam());
//     }
   }
   
   public static void randomizePlayers(){
      Set<String> keys = teams.keySet();
      nameRandomizer = new NameRandomizer();
      for(String key: keys){
         while(!teams.get(key).fullTeam()) {;
            Player player = new Player(nameRandomizer.getRandomName(), teams.get(key));
            teams.get(key).insertPlayer(player);
         }
      }
      for(String key: keys){
         ArrayList<Player> players = teams.get(key).allPlayers();
         Iterator<Player> foreach = players.iterator();
         while(foreach.hasNext()){
//            System.out.println(foreach.next().getPlayerInfo());
            System.out.println(foreach.next().getPlayerStats());
         }
      }
   }
}