/**
 * Henrik Nytun (hny003@student.uib.no) Studentnr 235187 
 * Kristoffer Edvardsen(ked005@student.uib.no) 
 * 
 * 
 * 
 */


package huffmanEncoding;

import java.util.Iterator;
import java.util.ArrayList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
public class ArrayListST<Key extends Comparable<Key>, Value> {
  
private int N = 0;     // number of key-value pairs         
private ArrayList<Key> keys = new ArrayList<Key>();
private ArrayList<Value> values = new ArrayList<Value>();

public boolean isEmpty() {return N == 0;}
public int     size()    {return N;}
public Iterator<Key> keysIterator() {return keys.iterator();}

public int rank(Key key){ // NB keys are sorted and unique
  int lo = 0, hi = N-1;
  while (lo <= hi) { // inv: all keys to the left of lo are smaller than key,
                      // and all keys to the right of hi are larger than key
    int mid = (lo+hi)/2;
    int cmp = key.compareTo(keys.get(mid));
    if (cmp == 0) return mid; // key found at position mid
    if (cmp < 0) {hi = mid-1;}
    else         {lo = mid+1;}
  }
  return lo; // hi+1 = lo <= N and key not present, but "should be" at lo
}
public void put(Key key, Value v) { 
  int maybe = rank(key); // key is or "should be" at position maybe
  if (maybe < N && key.equals(keys.get(maybe))) {values.set(maybe,v);}
  else {keys.add(maybe,key); values.add(maybe,v); N++;}
}
public Value get(Key key){
  if (isEmpty()) return null;
  int maybe = rank(key); // key is or "should be" at position maybe
  if (maybe < N && key.equals(keys.get(maybe))) {return values.get(maybe);}
  else {return null;}
}

}//End of ArrayListST, based on Algorithms, 4th Edition, Alg. 3.2
