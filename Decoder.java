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

public class Decoder {

	public static void main(String[] args)  { 
		  ArrayListST<String,Integer> st = new ArrayListST<String,Integer>();
		  ArrayList<String> documentText = new ArrayList<String>();
		  
		  //put what you encoded to decode it in variable infile, look at this as some kind of pattern for decoding
		  In infile = new In("/home/henrik/workspace/huffmanEncoding/src/huffmanEncoding/leipzig1M.txt");
		  while (!infile.isEmpty()) {
			  
		    String key = infile.readString(); 
		    documentText.add(key);
		    Integer i = st.get(key); 
		    if (i!=null){st.put(key,i+1);} 
		    else {st.put(key,1);}
		  }
		  
		  
		  
		  //make array of our asciiText
		  //fileToDecode = the encoded file we want to decode
		  ArrayList<String> asciiText = new ArrayList<String>();
		  In fileToDecode = new In("/home/henrik/workspace/huffmanEncoding/src/huffmanEncoding/encoded.txt");
		  while (!fileToDecode.isEmpty()) {
			    String asciiKey = fileToDecode.readString(); 
			    asciiText.add(asciiKey);
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
		  //NlogN linearithmic runtime(mergesort)
		  //the compare from PolyPair is changed to make the order ascending
		  Collections.sort(polypairs); 
		  
		  
		  //make iterator of arrayList
		  //Iterator<PolyPair<String, Integer>> arrayListIterator = polypairs.iterator<PolyPair<String, Integer>>();
		  
		  //first, we make an array of ascii character-combinations
		  
		 
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
			 
			 int uniqueWords = polypairs.size();
			 //we might need more than 2 combos, therefore we make 3 if there is more than 8930 unique
			 if(uniqueWords>8930)
			 {
				 for(int a = 33;a<127;a++)
				 {
					 for(int b = 33;b<127;b++)
					 {
						 for(int c = 33;c<127;c++)
						 {
							 String firstAscii = Character.toString((char)a);
							 String secondAscii = Character.toString((char)b);
							 String thirdAscii = Character.toString((char)c);
							 String allAsciis = (firstAscii+secondAscii+thirdAscii);
							 allAsciiCombinations.add(allAsciis);		
						 }
					 }
				 }
				 
				 
			 }
		
		 

		 
		 //then we make a map where we have the conversions
		 Map<String, String> conversionMap = new HashMap<String, String>();
		 
		 //and a map for reversing
		 Map<String, String> reversionMap = new HashMap<String, String>();
		 
		 
		 
		 
			 //put "translation" in the value for every key (which only occur once in the polypair-list
		 	 //note that the smallest(first) ascii-characters are assigned to the most frequent(first)
		 	 //words in the polypair-arrayList
		 
		 int asciiIndex = 0; //the index of our ascii-arrayList
		 for(PolyPair f:polypairs)
		 {
			conversionMap.put(f.getFst().toString(), allAsciiCombinations.get(asciiIndex));
			reversionMap.put(allAsciiCombinations.get(asciiIndex), f.getFst().toString());
			asciiIndex++;
				 
				 
		 }
			 
		 
		 //int linebreak = 0;
		 Iterator<String> documentIterator = asciiText.iterator(); //iterator of encoded document, to decode
		 
		 StringBuilder strbuild = new StringBuilder();
		 while(documentIterator.hasNext())
		 {
			 String asciiValue = documentIterator.next();
			 String decodedWord = reversionMap.get(asciiValue);
			 strbuild.append(decodedWord + " ");
			 //linebreak++;
			 //if(linebreak%100 == 0)
				// decodedOutput += "\n";
		 }
		 
		 
		 try {
	            //our desired decoded output file
	            BufferedWriter out = new BufferedWriter(new FileWriter("/home/henrik/workspace/huffmanEncoding/src/huffmanEncoding/decoded.txt"));
	            out.write(strbuild.toString());
	            out.close();
	        }
	        catch (IOException e)
	        {
	            System.out.println("Exception ");       
	        }
		 
		 
		 
		 //make file with hashmap
		 try {
	            
	            BufferedWriter out = new BufferedWriter(new FileWriter("/home/henrik/workspace/huffmanEncoding/src/huffmanEncoding/hashmapToString.txt"));
	            out.write(reversionMap.toString());
	            out.close();
	        }
	        catch (IOException e)
	        {
	            System.out.println("Exception ");       
	        }
		 
		 
		  
		  
		  
		  
		  
//		  
//		  
		  
		  
		  
		}//End of main
	
	
	
	
	
	
	
}