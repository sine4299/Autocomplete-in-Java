import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ArrayList;

public class WordItem implements Comparable {
	private String word;
	private int count;
	private ArrayList<Integer> atLines;
	
	public WordItem(String word, int c) {
		this.word = word;
		this.count = c;
	}
		
	@Override
	 public int compareTo(Object other) {
      WordItem w = (WordItem)other;
      return w.getWord().compareTo(this.word);
      }
	
	public String getWord() {
		return this.word;
	}
	
	public int getCount() {
		return this.count;
	}
	
	public void setCount(int count){
      this.count = count;
   }

	@Override
	public boolean equals(Object obj) {
      WordItem w = (WordItem)obj;
      if(w.getWord().equals(this.word)){
        return true;
      }
      return false;
    }
	
	@Override
	public String toString() {
		String ret = "";
		ret += word + ":" + this.count;
		return ret;
	}
}
