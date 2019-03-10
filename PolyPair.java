/**
 * Henrik Nytun (hny003@student.uib.no) Studentnr 235187 
 * Kristoffer Edvardsen(ked005@student.uib.no) 
 * 
 * 
 * 
 */




package huffmanEncoding;

import edu.princeton.cs.algs4.StdOut;
public class PolyPair <
  T1 extends Comparable<T1>,
  T2 extends Comparable<T2>> 
implements 
  Comparable<PolyPair<T1,T2>> {
  
private T1 fst;
private T2 snd;

public PolyPair(T1 fst, T2 snd){
  this.fst = fst;
  this.snd = snd;
}

public T1 getFst() {return fst;}
public T2 getSnd() {return snd;}

public int compareTo(PolyPair<T1,T2> p){
 
  return p.getSnd().compareTo(this.snd);
}

@Override public String toString(){
  return "(" + this.getFst() + "  _____" + this.getSnd() + ")";
  }

public static void main(String[] args){
    PolyPair<Integer,String> p = new PolyPair<Integer,String>(1,"aa");
    PolyPair<Integer,String> q;
    for (int i=0; i<3; i++) { 
      q = new PolyPair<Integer,String>(i,"a");
      StdOut.print(p + " compared to " + q + " yields ");
      StdOut.println(p.compareTo(q));
    }

  }//End of main
}//End of PolyPair Class
