package application;
import structures.*;

public class Tester {
	public static void main(String[] args) {
		///////TOKENIZER//////// test cases from pdf
		System.out.println("----------TOKENIZER TEST----------");
		Tokenizer tokenizer = new Tokenizer(": ", "fish: The cat REALLY tried to eat my fish");
		print(tokenizer);
		System.out.println("------");
		
		tokenizer = new Tokenizer(",! ", "Find cats, dogs, and Fish!");
		print(tokenizer);
		
		
		///////HASH TABLE////////
		System.out.println("----------HASH TABLE TEST----------");
		//nodes
		KeyValue node1 = new KeyValue("my");
		node1.addValue("dog");
		node1.addValue("cat");
		node1.addValue("bird");
		node1.addValue("fish");
		
		KeyValue node2 = new KeyValue("big");
		node2.addValue("dog");
		node2.addValue("monkey");
		
		KeyValue node3 = new KeyValue("friendly");
		node3.addValue("dog");
		node3.addValue("bird");
		
		KeyValue node4 = new KeyValue("cat");
		node4.addValue("cat");
		node4.addValue("bird");
		node4.addValue("fish");
		
		KeyValue node5 = new KeyValue("pet");
		node5.addValue("lobster");
		node5.addValue("shrimp");
		node5.addValue("squid");
		
		//duplicate key nodes
		KeyValue dnode1 = new KeyValue("my");
		dnode1.addValue("horse");
		dnode1.addValue("dog"); //d
		dnode1.addValue("pig");
		
		KeyValue dnode2 = new KeyValue("big");
		dnode2.addValue("monkey"); //d
		dnode2.addValue("sheep");
		dnode2.addValue("parrot");
		
		KeyValue dnode3 = new KeyValue("friendly");
		dnode3.addValue("canary");
		dnode3.addValue("bird"); //d
		dnode3.addValue("turkey");
		
		KeyValue dnode4 = new KeyValue("cat");
		dnode4.addValue("lizard");
		dnode4.addValue("snake");
		dnode4.addValue("fish"); //d
		
		
		KeyValue dnode5 = new KeyValue("pet");
		dnode5.addValue("lobster"); //d
		dnode5.addValue("crab");
		dnode5.addValue("shrimp"); //d
		
		//hash table
		HashTable table = new HashTable();
		table.insertKeyValue(0, node1);
		table.insertKeyValue(1, node2);
		table.insertKeyValue(2, node3);
		table.insertKeyValue(3, node4);
		table.insertKeyValue(4, node5);
		System.out.println(table);
	
		//rehash by this point, add duplicates
		table.insertKeyValue(3, dnode4);
		table.insertKeyValue(10, dnode5);
		table.insertKeyValue(19, dnode1);
		table.insertKeyValue(24, dnode3);
		table.insertKeyValue(26, dnode2);
		System.out.println(table);
		
		//start chaining
		table.insertKeyValue(3, dnode5);
		table.insertKeyValue(24, dnode1);
		System.out.println(table);
		
		//look up test, all of them should return except last one
		System.out.println(table.lookUpKey(3, "pet"));
		System.out.println(table.lookUpKey(26, "big"));
		System.out.println(table.lookUpKey(24, "friendly"));
		System.out.println(table.lookUpKey(10, "cat"));
	}
	
	private static void print(Tokenizer tokenizer) {
		while(tokenizer.hasToken())
			try {
				System.out.println(tokenizer.nextToken());
			} catch (NoMoreTokensException e) {
				System.out.println("Caught NoMoreTokensException");
			}
	}

}
