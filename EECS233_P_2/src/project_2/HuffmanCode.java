package project_2;

import java.util.Iterator;
import java.util.LinkedList;

public class HuffmanCode { //creates the tree from the linked list 	

	private HuffmanList list;
	private HuffmanNode root;
	private int size; //the size of the tree
	private Iterator<HuffmanNode> it;
	
	public HuffmanCode(byte[] byteArr){
		list = new HuffmanList(byteArr);
		it= list.iterator();
		size = list.size();
		buildTree();
	}
	
	public HuffmanCode(String filePath){
		list = new HuffmanList(filePath);
		it = list.iterator();
		size = list.size();
		buildTree();
	}
	
	public HuffmanCode(byte[] byteArr, int[] countArr){
		list = new HuffmanList(byteArr, countArr);
		it = list.iterator();
		size = list.size();
		buildTree();
	}
	
	private void buildTree(){
		HuffmanNode temp1 = null;
		HuffmanNode temp2;
		for(int i=0; i<list.size(); i++){ //then there is only one element left, it is the root
			temp1 = it.next(); //temp1 equals first element in the list
			it.remove(); //remove that element
			temp2 = it.next(); //temp2 equals second element, now first
			it.remove(); //removes that element
			temp1.left = new HuffmanNode(temp1.b, temp1.count);
			temp1.right = new HuffmanNode(temp2.b, temp2.count);
			temp1.count = temp1.count + temp2.count;
			list.add(temp1);
			size++; //we are adding a new node so need to increase the size by one
		}
		root = temp1;
	}
	
	public boolean[] code(Byte b){ //returns the binary encoding for byte b
		HuffmanNode trav = root;
		LinkedList<Boolean> encoding = new LinkedList<Boolean>();
		encoding.add(false);
		
	}
	
	private boolean[] ToPrimitiveBooleanArray(LinkedList<Boolean> bool){ //converts Boolean[] to boolean[]
		boolean[] b = new boolean[bool.size()];
		for(int i=0; i<bool.size(); i++){
			b[i] = bool.get(i);
		}
		return b;
	}
	
	public String codeString(byte b){ //returns binary encoding but as a string with ones and zeros
		return "";
	}
	
	public String toString(){
		return "";
	}
}
