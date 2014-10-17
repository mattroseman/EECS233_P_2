package project_2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class HuffmanList { //constructs the linkedlist of HuffmanNodes from the byte[] from ByteCounter

	private LinkedList<HuffmanNode> list;
	private ByteCounter bCounter;
	private byte[] elements; //temporary array used in construction
	
	public HuffmanList(byte[] byteArr){
		bCounter = new ByteCounter(byteArr);
		bCounter.setOrder("countInc");
		list = new LinkedList<HuffmanNode>();
		elements = bCounter.getElements();
		for(int i=0; i<elements.length; i++){ //assigns every byte to its proper node
			list.add(new HuffmanNode(elements[i], bCounter.getCount(elements[i]))); //creates a huffman node with they byte elements[i] and the count of that byte
		}
	}
	
	public HuffmanList(String filePath){
		bCounter = new ByteCounter(filePath);
		bCounter.setOrder("countInc");
		list = new LinkedList<HuffmanNode>();
		elements = bCounter.getElements();
		for(int i=0; i<elements.length; i++){
			list.add(new HuffmanNode(elements[i], bCounter.getCount(elements[i])));
		}
	}
	
	public HuffmanList(byte[] byteArr, int[] countArr){ //need to make sure to order the list countInc after I make the list from this method
		for(int c: countArr){ //sees if any of the counts are negative
			if(c < 0)
				throw new IllegalArgumentException("contains negative counts");
		}
		
		if(countArr.length != byteArr.length)
			throw new IllegalArgumentException("Varying Length Exception");
		
		list = new LinkedList<HuffmanNode>();
		
		for(int i=0; i<byteArr.length; i++){
			for(int j=i+1; j<byteArr.length; j++){ //checks for duplicates
				if(byteArr[i] == byteArr[j])
					throw new IllegalArgumentException("Duplicate Byte Exception");
			}
			list.add(new HuffmanNode(byteArr[i], countArr[i]));
		}
	}
	
	public void orderListCountInc(){
		Object[] temp = list.toArray();
		Arrays.sort(temp);
		list.clear();
		for(Object h: temp){
			list.add((HuffmanNode)h);
		}
	}
	
	public void add(HuffmanNode n){
		list.add(n);
		orderListCountInc();
	}
	
	public int size(){
		return list.size();
	}
	
	public Iterator iterator(){
		return list.iterator();
	}
}
