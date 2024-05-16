package application;
import java.io.IOException;
import structures.*;
import java.util.*;

public class Tester2 {

	public static void main(String[] args) throws IOException, NoMoreTokensException {
			ArrayList<String> a = SimpleSearch.query("cat", new Indexer("text1.txt"));
			ArrayList<String> as = new ArrayList<String>();
			as.add("cat");
			as.add("bird");
			as.add("fish");
			System.out.println("Test 1 --> " + a.equals(as));
			
			ArrayList<String> b = SimpleSearch.query("big red dog", new Indexer("text1.txt"));
			ArrayList<String> bs = new ArrayList<String>();
			bs.add("dog");
			System.out.println("Test 2 --> " + b.equals(bs));
			
			ArrayList<String> c = SimpleSearch.query("big cat", new Indexer("text1.txt"));
			ArrayList<String> cs = new ArrayList<String>();
			cs.add("dog");
			cs.add("cat");
			cs.add("bird");
			cs.add("fish");
			System.out.println("Test 3 --> " + c.equals(cs));
			
			ArrayList<String> d = SimpleSearch.query("Big, friendly CAT!", new Indexer("text1.txt"));
			ArrayList<String> ds = new ArrayList<String>();
			ds.add("dog");
			ds.add("bird");
			ds.add("cat");
			ds.add("fish");
			System.out.println("Test 4 --> " + d.equals(ds));

	}

}
