package project_2;

import java.util.LinkedList;

public class HuffmanNode  implements Comparable <HuffmanNode>{
	public byte b;
	public int count; //the number of times the node appears in the file
	public boolean[] code = new boolean[8];
	public LinkedList<Byte> allChildren = new LinkedList<Byte>();
	public HuffmanNode next = null; 
	public HuffmanNode left = null;
	public HuffmanNode right = null;
	
	public HuffmanNode(byte nodeData, int nodeCount, HuffmanNode leftSubChild, HuffmanNode rightSubChild){
		if(leftSubChild == null || rightSubChild == null){
			b = nodeData;
			code = findCode(b);
			count = nodeCount;
			allChildren.add(nodeData);
		}	else{
				b = nodeData;
				code = findCode(b);
				left = leftSubChild;
				right = rightSubChild;
				count = count + left.count + right.count;
				allChildren.add(nodeData);
			}
	}
	
	public HuffmanNode(byte nodeData, int nodeCount){
		b = nodeData;
		left = null;
		right = null;
		code = findCode(b);
		count = nodeCount;
		allChildren.add(nodeData);
	}
	
	public static boolean[] findCode(byte b){ //returns boolean[] array representation of the byte
		boolean[] bCode = new boolean[8];
		
		/*bCode[0] = (b & 0x01) != 0; //if the least significant bit is the same as 0x01 which is one in radix 16	
		bCode[1] = (b & 0x02) != 0; //2 radix 16
		bCode[2] = (b & 0x04) != 0; //4 radix 16
		bCode[3] = (b & 0x08) != 0; //8 radix 16
		bCode[4] = (b & 0x10) != 0; //16 radix 16
		bCode[5] = (b & 0x20) != 0; //32 radix 16
		bCode[6] = (b & 0x30) != 0; //64 radix 16
		bCode[7] = (b & 0x40) != 0; //128 radix 16 */
		
		for(int i=0; i<Byte.SIZE; i++)
			bCode[i] = (b & (2^i)) != 0;
		
		return bCode;
	}
	
	public LinkedList<Byte> getAllChildren(){
		if(left == null && right == null)
			return allChildren;
		LinkedList<Byte> temp = left.getAllChildren();
		temp.addAll(right.getAllChildren());
		return temp;
		
	}
	
	public boolean hasChildren(){
		try{
			return left != null && right != null;
		}catch(NullPointerException e){
			return false;
		}
	}

	@Override
	public int compareTo(HuffmanNode h2) {
		if(this.count > h2.count) //compares count first then compares bytes
			return 1;
		else
			if(this.count < h2.count)
				return -1;
				else
					if(this.b > h2.b) //secondary comparison of the bytes
						return 1;
					else
						if(this.b < h2.b)
							return -1;
						else 
							return 0; //if the byte and count are the same
	}

}
