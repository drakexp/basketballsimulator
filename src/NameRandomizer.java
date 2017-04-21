import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class NameRandomizer {
   private static ArrayList<String> names = new ArrayList<String>();
   private Random randomGenerator;
   
   public NameRandomizer() {
      String filedir = System.getProperty("user.dir") + "\\names.txt";
      System.out.println(filedir);
      readFile(filedir);
//      Iterator<String> foreach = names.iterator();
//      while(foreach.hasNext()){
//         System.out.println(foreach.next());
//      }
   }
   
   public String getRandomName() {
      randomGenerator = new Random();
      int firstname = randomGenerator.nextInt(names.size());
      int lastname = randomGenerator.nextInt(names.size());
      return names.get(firstname) + " " + names.get(lastname);
   }
   
   private void readFile(String file) {
      String line;
      try {
         FileReader fileReader = new FileReader(file);
         BufferedReader bufferedReader = new BufferedReader(fileReader);
         while((line = bufferedReader.readLine()) != null) {
            names.add(line);
         }
         bufferedReader.close();
      } catch (FileNotFoundException ex) {
         JOptionPane.showMessageDialog(null, "Unable to open file " + file);
      } catch (IOException ex) {
         JOptionPane.showMessageDialog(null, "Error reading file " + file);
      }
   }
}