/**
 * Henrik Nytun (hny003@student.uib.no) Studentnr 235187 
 * Kristoffer Edvardsen(ked005@student.uib.no) 
 * 
 * 
 * 
 */


package huffmanEncoding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class FrequencyCounter {

	public static void main(String[] args)  { 
		  ArrayListST<String,Integer> st = new ArrayListST<String,Integer>();
		  In infile = new In("/home/henrik/workspace/huffmanEncoding/src/huffmanEncoding/leipzig1M.txt");
		  while (!infile.isEmpty()) {
		    String key = infile.readString(); 
		    Integer i = st.get(key); 
		    if (i!=null){st.put(key,i+1);} 
		    else {st.put(key,1);}
		  }
		  
		  //make iterator to put them as pairs into arrayList to sort them as collection
		  Iterator<String> iter = st.keysIterator();
		 
		  ArrayList<PolyPair> polypairs = new ArrayList();
		  
		  while(iter.hasNext())
		  {
			  String key = iter.next();
			  int value = st.get(key);
			  PolyPair<String, Integer> currentPair = new PolyPair<String,Integer>(key,value);
			  polypairs.add(currentPair);
			  
			  
		  }
		  
		 // then we sort the list
		  //NlogN linearithmic runtime
		  //the compare from PolyPair is changed to make the order ascending
		  Collections.sort(polypairs); 
		  
		  
		  //make iterator of arrayList
		  Iterator arrayListIterator = polypairs.iterator();
		  
		  //print the pairs, which are in descending order, 20 most frequent words
		  int counter = 0;
		  int firstwords = 20;
		  while(arrayListIterator.hasNext())
		  {
			  if(counter < firstwords )
			  {
				  System.out.println(arrayListIterator.next());
				  counter++;
				  
				  
			  }
			  else
				return;  
			  
		  }
		  
		  
		  
		  
		  
		}//End of main
	
	
	
	
	
	
	
}
