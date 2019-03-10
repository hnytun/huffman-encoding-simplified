/**
 * Henrik Nytun (hny003@student.uib.no) Studentnr 235187 
 * Kristoffer Edvardsen(ked005@student.uib.no) 
 * 
 * 
 * 
 */


package huffmanEncoding;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdOut;

public class Encoder {

	public static void main(String[] args)  { 
		  ArrayListST<String,Integer> st = new ArrayListST<String,Integer>();
		  ArrayList<String> documentText = new ArrayList<String>();
		  
		  //this is the file you want to encode
		  In infile = new In("/home/henrik/workspace/huffmanEncoding/src/huffmanEncoding/leipzig1M.txt");
		  while (!infile.isEmpty()) {
			  
		    String key = infile.readString(); 
		    
		    documentText.add(key);
		    
		    Integer i = st.get(key); 
		    if (i!=null){st.put(key,i+1);} 
		    else {st.put(key,1);}
		  }
		 // System.out.println("file entered into symboltable");
		  
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
		  
		 // System.out.println("file made into polypairs with frequency");
		  
		 // then we sort the list
		  //NlogN linearithmic runtime(mergesort)
		  
		  //the compare from PolyPair is changed to make the order descending(most frequent at top)
		  //giving shorter ascii-combos to the frequent words
		  Collections.sort(polypairs); 
		  
		  //System.out.println("polypairs sorted");
		 
		  
		  //first, we make an array of ascii character-combos to give to our words
		  
		 ArrayList<String> allAsciiCombinations = new ArrayList<String>();
		 
		 //possible ascii's with 1 char
		 for(int i = 33;i<127;i++) //our desired asciis
		 {
			 char ascii = (char)i;
			 allAsciiCombinations.add(Character.toString(ascii)); 
		 }
		 
		 //possible ascii's with 2 char combinations, make total combos 94*94+94 = 8930
		 
		 for(int i = 33;i<127;i++)
		 {
			 for(int j = 33;j<127;j++)
			 {
				 String firstAscii = Character.toString((char)i);
				 String secondAscii = Character.toString((char)j);
				 String bothAsciis = (firstAscii+secondAscii);
				 allAsciiCombinations.add(bothAsciis);				 
			 }
		 }
		 
		 //if there is more than 94*94+94 = 8930 words, we will need 3 ascii-combos in our list aswell
		 //sadly, this means that we always add the 3 combos, even though we dont use all of them
		 //i have no intention to fix this since it does not matter that much
		 int uniquewords = polypairs.size();
		 if(uniquewords>8930)
		 {
			 for(int a = 33;a<127;a++)
			 {
				// System.out.print(a + " ");
				 for(int b = 33;b<127;b++)
				 {
					// System.out.print(b + " ");
					 for(int c = 33;c<127;c++)
					 {
						 
						 //System.out.print(c + " ");
						 String firstAscii = Character.toString((char)a);
						 String secondAscii = Character.toString((char)b);
						 String thirdAscii = Character.toString((char)c);
						 String allAsciis = (firstAscii+secondAscii+thirdAscii);
						 allAsciiCombinations.add(allAsciis);	
						// System.out.println();
					 }
				 }
			 }
			 
			 
		 }
		 
		 //Finished making combos
		 
		 //then we make a map where we have the conversions(word and ascii-representation)
		 Map<String, String> conversionMap = new HashMap<String, String>();
		 
		 
		 //put "translation" in the value for every key (which only occur once in the polypair-list
		 //common keys get the smallest ascii value (single character)
		 int asciiIndex = 0;
		 for(PolyPair f:polypairs)
		 {
			 conversionMap.put(f.getFst().toString(), allAsciiCombinations.get(asciiIndex));
			 asciiIndex++;	 
				 
		 }
		//System.out.println("Conversionmap made of polypairs");	 
		 
		 
		 Iterator<String> documentIterator = documentText.iterator();
		 
		 int encodedWords = 0;
		// System.out.println("Starting encoding");
		 StringBuilder strbuild = new StringBuilder();
		 while(documentIterator.hasNext())
		 {
			 
			 String key = documentIterator.next();
			 String asciivalue = conversionMap.get(key);
			 strbuild.append(asciivalue + " ");
			 
			// linebreak++;
			 //if(linebreak%100 == 0)
				 //encodedOutput += "\n";
			 encodedWords++;
			 if((encodedWords%10000) == 0)
				 System.out.println(encodedWords);
		 }
		 
		 
		 try {
	            //this is the file you want to encode to
	            BufferedWriter out = new BufferedWriter(new FileWriter("/home/henrik/workspace/huffmanEncoding/src/huffmanEncoding/encoded.txt"));
	            out.write(strbuild.toString());
	            out.close();
	        }
	        catch (IOException e)
	        {
	            System.out.println("Exception ");       
	        }
		 
		
		 
  
		  
		}//End of main

}