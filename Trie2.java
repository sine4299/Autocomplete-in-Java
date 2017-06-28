import java.util.*;
import java.io.IOException;

public class Trie2 {
   private class TrieNode {
      Map<Character, TrieNode> children = new TreeMap<>();//TreeMap is java build-in structure, 
      boolean aword;						//Basically it acts like a Hashtable or Hashmap, establishing a mapping between Key and Value
   	                                    //Unlike hash table, keys in TreeMap are sorted!
   }
	
   private TrieNode root;
   public static ArrayList<String> result = new ArrayList<String>();
   public ArrayList<String> getResult(){
      return this.result;
   }
    
   public Trie2() {
      this.root = new TrieNode();
   }

   public void insertString(String s) {
      insertString(root, s);
   }
	
   private void insertString(TrieNode root, String s) {
      TrieNode cur = root;
      for (char ch : s.toCharArray()) {
         TrieNode next = cur.children.get(ch);
         if (next == null)
            cur.children.put(ch, next = new TrieNode());
         cur = next;
      }
      cur.aword = true;
   }
    
   private void print(TrieNode node, String s, String pre) {
      if (node.aword) {
         result.add(pre + s);
      }
      for (Character ch : node.children.keySet()) {
         print(node.children.get(ch), s + ch, pre);
      }
   }
   public void findWord(String s) {
      findWord(root, s, s);
   }
	
   private void findWord(TrieNode node, String s, String pre) {
      if(s != null) {
         String rest = s.substring(1); //rest is a substring of s, by excluding the first character in s
         char ch = s.charAt(0);        //ch is the first letter of s
         TrieNode child = node.children.get(ch);	//return the child that ch associated with. 
         if(s.length() == 1 && child != null) //if s contains only one letter, and current node has a child associated with that letter, we find the prefix in Trie!
            print(child, rest, pre);	                 //base case
         else
            findWord(child, rest, pre);    //recursive, In this way, we follow the path of the trie from root down towards leaf
      }
   }
   
   public ArrayList<String> search(ArrayList<WordItem> ara){
      ArrayList<WordItem> pop = new ArrayList<WordItem>();
      ArrayList<String> rest = new ArrayList<String>();
      for(String target : result){
         int high = ara.size() - 1;
         int low = 0;
         int mid = 0;
         while (low <= high){
            mid = (low +high) / 2;
            WordItem temp = ara.get(mid);
            String word = temp.getWord();
            if (word.compareTo(target) > 0){
               high = mid - 1;
            }
            else if (word.compareTo(target) < 0){
               low = mid + 1;
            }
            else
               pop.add(ara.get(mid));
         }
      }
      quickSort(pop);
      for(int i = 0; i < 10; i++)
         rest.add(pop.get(i).getWord());
      return rest;
   }
   
   public void quickSort( ArrayList<WordItem> array ) {
		quickSort( array, 0, array.size() - 1 );
	}
	
	public void quickSort( ArrayList<WordItem> array, int low, int high )
	{
		if ( low < high ) {
			int pivotIndex = partition(array, low, high);
			quickSort( array, low, pivotIndex - 1 );
			quickSort( array, pivotIndex + 1, high );
		}
	}
   
	int partition( ArrayList<WordItem> array, int low, int high )
	{
      WordItem temp = array.get(low);
      Integer pivot = temp.getCount();
		int left = low, right = high  + 1;
		while( left < right ) {
			while( left < right && array.get(--right).getCount() >= pivot) {
			}
			if( left < right ) {
				array.add(left, array.get(right));
			}
			while( (left < right) && array.get(++left).getCount() <= pivot) {
			}
			if( left < right ) {
				array.add(right, array.get(left));
			}
		}
		array.add(right, temp);
		return right;
	}
   public static void main(String[] args) throws Exception{
   
   } 
}
