import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class WordProcessor {

   LinkedList words = new LinkedList();
   ArrayList<WordItem> test = new ArrayList<WordItem>();

   public ArrayList<String> fileRead(String name) throws IOException {
      ArrayList<String> lines = new ArrayList<String>();	
      FileReader fileReader = new FileReader(name);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String aline = null;
      while ((aline = bufferedReader.readLine()) != null) {
         aline = aline.trim();
         if(aline.length() > 0)
            lines.add(aline);
      }
      fileReader.close();
      return lines;
   }
	
   public ArrayList<WordItem> extractLine(String aline) {
      boolean inWord = false;
      String freak = "";
      int i = 0;
      int wordLength = 0;
      int start = 0;
      String word = "";
      while(i < aline.length()){
         char c = aline.charAt(i);
         if(Character.isLetter(c)){
            if(!inWord){
               start = i;
               inWord = true;
            }
            wordLength++;
         }
         else if(c >= '0' && c <= '9'){
            freak += "" + Character.getNumericValue(c);
         }
         else if(inWord){
            String w = aline.substring(start,(wordLength) + start);
            word = w.toLowerCase();
            inWord = false;
            wordLength = 0;
         }
         i++;
      }
      WordItem newWord = new WordItem(word, Integer.parseInt(freak));
      test.add(newWord); 
      return test; 
   }
	
   public ArrayList<WordItem> extractAll(String fileName) throws IOException {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String aline = null;
      while ((aline = bufferedReader.readLine()) != null) {
         aline = aline.trim();
         if(aline.length() > 0);
         {
            extractLine(aline);
         }
      }
      fileReader.close();
      return test;
   }
}
	

