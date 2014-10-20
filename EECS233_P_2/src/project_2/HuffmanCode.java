package project_2;

import java.util.Iterator;
import java.util.LinkedList;

public class HuffmanCode { //creates the tree from the linked list 	

	private HuffmanList list;
	private Iterator<HuffmanNode> it;
	
	public HuffmanCode(byte[] byteArr){
		list = new HuffmanList(byteArr);
		it= list.iterator();
		buildTree();
	}
	
	public HuffmanCode(String filePath){
		list = new HuffmanList(filePath);
		it = list.iterator();
		buildTree();
	}
	
	public HuffmanCode(byte[] byteArr, int[] countArr){
		list = new HuffmanList(byteArr, countArr);
		it = list.iterator();
		buildTree();
	}
	
	private HuffmanNode buildTree(){
		HuffmanNode temp1 = null;
		HuffmanNode temp2;
		while(list.size() > 1){ //then there is only one element left, it is the root
			temp1 = it.next();
			it.remove(); //remove that element
			temp2 = it.next(); //temp2 equals second element, now first
			it.remove(); //removes that element
			temp1.left = new HuffmanNode(temp1.b, temp1.count, temp1.left, temp1.right);
			temp1.right = new HuffmanNode(temp2.b, temp2.count, temp2.left, temp2.right);
			temp1.count = temp1.count + temp2.count;
			list.add(temp1);
			it = list.iterator();
		}
		return temp1;
	}
	
	public boolean[] code(Byte b){ //returns the binary encoding for byte b
		HuffmanNode trav = list.get(0);
		LinkedList<Boolean> encoding = new LinkedList<Boolean>();
		while(trav.hasChildren()){ //runs until you reach a leaf node
			for(int j=0; trav.hasChildren() && j<trav.left.getAllChildren().size(); j++){ //checks to see if b is in trav'r left children
				if(trav.left.getAllChildren().get(j) == b){ 
					trav = trav.left; //moves to the left child
					encoding.add(false); //adds a false to the encoding						
					break; //we know the node is in trav's left subtree, and we move to that subtree and start over
				}
			}
			for(int k=0; trav.hasChildren() && k<trav.left.getAllChildren().size(); k++){ //checks to see if b is in trav's right children
				if(trav.right.getAllChildren().get(k) == b){
					trav = trav.right;
					encoding.add(true);
					break; //we know that the children are in trav's right subtree so we start the process over
				}
			}
		}
		return toPrimitiveBooleanArray(encoding);
	}
	
	private boolean[] toPrimitiveBooleanArray(LinkedList<Boolean> bool){ //converts Boolean[] to boolean[]
		boolean[] b = new boolean[bool.size()];
		for(int i=0; i<bool.size(); i++){
			b[i] = bool.get(i);
		}
		return b;
	}
	
	public String codeString(byte b){ //returns binary encoding but as a string with ones and zeros
		String stringEncoding = "";
		boolean[] encoding  = code(b);
		for(boolean bool: encoding){
			if(bool == true)
				stringEncoding = stringEncoding.concat("1");
			if(bool == false)
				stringEncoding = stringEncoding.concat("0");
		}
		return stringEncoding;
	}
	
	public String toString(){
		String returnString = "";
		Byte temp;
		for(int i=0; i<list.get(0).getAllChildren().size(); i++){
			temp = list.get(0).getAllChildren().get(i);
			returnString = returnString.concat(temp.intValue() + " : " + code(temp) + "/n");
		}
		return returnString;
	}
}
