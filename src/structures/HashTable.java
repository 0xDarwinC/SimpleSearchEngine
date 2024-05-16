package structures;

/**
 * HashTable class to store KeyValue Objects
 * 
 * @name Darwin Christopher
 * @date 10/12/23
 * @period 3
 */
public class HashTable {

	private KeyValue[] table;
	private double loadFactor;
	private int numNodes;

	/**
	 * By default the size of the HashTable is 5
	 * 		with a load factor of 0.8 (80%)
	 * The load factor should be checked AFTER a new item is added.
	 * When the load of the HashTable exceeds the load factor, 
	 * 		the size of the HashTable should use the 6n-1 formula to 
	 * 		expand to another prime number and the
	 * 		load factor should increase by 50%.
	 */
	public HashTable(){
		table = new KeyValue[5];
		loadFactor = 0.8;
		numNodes = 0;
	}

	/**
	 * Given the index and the KeyValue, the KeyValue will be inserted
	 * 		into this HashTable at the correct index.  If the KeyValue
	 * 		already exists in the HashTable, the values of the KeyValue 
	 * 		passed by parameter will be appended to the values list of 
	 * 		the KeyValue object already stored in this HashTable.  If 
	 * 		the insert results in the HashTable exceeding the loadFactor, 
	 * 		the HashTable will expand using the 6n-1 formula, the loadFactor
	 * 		will increase by 50%, and all the data in the HashTable will be 
	 * 		rehashed accordingly. 
	 * 
	 * @param index is the HashTable index as calculated by the Indexer
	 * @param data a KeyValue object
	 * 
	 * POSTCONDITION: The load factor <= loadFactor, numNodes reflects the 
	 * 		number of KeyValue nodes that are in the HashTable
	 * 
	 * 3 points
	 */
	public void insertKeyValue(int index, KeyValue data){
		/* Complete this method */
		if(table[index] == null) {
			table[index] = data;
			numNodes++;
		}
		else {
			KeyValue pointer = table[index];
			boolean flag = false;
			boolean flag2 = false;
			while(pointer.next != null) {
				if(pointer.getKey().equals(data.getKey())) {
					flag = true;
					for(int i = 0; i < data.getValues().size(); i++) {
						for(int j = 0; j < pointer.getValues().size(); j++) {
							if (data.getValues().get(i).equals(pointer.getValues().get(j))) {
								flag2 = true;
							}
						}
						if(!flag2) {
							pointer.addValue(data.getValues().get(i));
						}
					}
				}
				pointer = pointer.next;
			}
			if(flag == false) {
				pointer.next = data;
				numNodes++;
			}
		}
		
		if(numNodes/table.length > loadFactor) {
			rehash();
		}
		
	}

	/**
	 * Given the index and a key return the KeyValue object from this
	 * 		HashTable. If there is no KeyValue object for the key passed
	 * 		by parameter, return null.
	 * @param index the index for key as determined by the Indexer
	 * @param key a String keyword that is being searched for
	 * @return the KeyValue object that holds the key passed by parameter,
	 * 		or null if this key is not stored as part of a KeyValue object
	 * 		in this HashTable.
	 * 
	 * 2 points
	 */
	public KeyValue lookUpKey(int index, String key){
		/*  Complete this method */
		
		KeyValue pointer = table[index];
		if(pointer == null) {
			return null;
		}
		while(pointer.next != null && !pointer.getKey().equals(key)) {
			pointer = pointer.next;
		}
		if(pointer.getKey().equals(key)) {
			return pointer;
		}
		else {
			return null;
		} 
	}
	
	/**
	 * A private helper method to resize the table and rehash all the values
	 * 		in the table.  When called, the HashTable will expand using 
	 *      the 6n-1 formula, the loadFactor will increase by 50% and all the data 
	 *      in the HashTable will be rehashed.
	 *      
	 * 5 points
	 */
	private void rehash() {
		/*  Complete this method */
		int size = table.length;
		int holder = 6*size-1;
		KeyValue[] holder1;
		KeyValue pointer;
		KeyValue holderK;
		
		while(!(isPrime(holder))){
			size++;
			holder = 6*size-1;
		}
		
		loadFactor *= 1.5;
		
		holder1 = new KeyValue[holder];
		for(int i = 0; i<table.length; i++) {
			if(table[i]!= null) {
				pointer = table[i];
				while(pointer != null) {
					holderK = new KeyValue(pointer.getKey());
					for(String e : pointer.getValues()) {
						holderK.addValue(e);
					}
					holder1[Math.abs(holderK.hashCode())%holder1.length] = holderK;
					pointer = pointer.next;
				}
			}
		}
		table = holder1;
	}
	
	private static boolean isPrime(int n) {
		for(int i = 2; i<n; i++) {
			if(n%i == 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @return the current length of the HashTable to be used
	 * 		by the Indexer.
	 */
	public int size() {
		return table.length;
	}

	/**
	 * @return a String representation of this HashTable
	 */
	public String toString() {
		String tableString = new String();
		for(int index = 0; index < table.length; index++) {
			if(table[index] != null) {
				tableString += String.format("[%03d] -> ", index);
				KeyValue ptr = table[index];
				for(; ptr.next != null; ptr = ptr.next) {
					tableString += ptr.toString() + ", ";
				}
				tableString += ptr.toString() + "\n";
			}
		}
		tableString += String.format("\nLoad Factor: %.2f%c NumNodes: %d\n", loadFactor, '%', numNodes);
		return tableString;
	}
}
