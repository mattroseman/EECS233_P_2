package project_2;

public class HuffmanNode implements Comparable <HuffmanNode>{
	public byte b ;
	public int count; //the number of times the node appears in the file
	public boolean[] code;
	public HuffmanNode next; //do I need this
	public HuffmanNode left;
	public HuffmanNode right;
	
	public HuffmanNode(byte nodeData, int nodeCount, HuffmanNode leftSubChild, HuffmanNode rightSubChild){
		b = nodeData;
		code = findCode(b);
		left = leftSubChild;
		right = rightSubChild;
		count = count + left.count + right.count;
	}
	
	public HuffmanNode(byte nodeData, int nodeCount){
		b = nodeData;
		code = findCode(b);
		count = nodeCount;
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

	@Override
	public int compareTo(HuffmanNode h2) {
		if(this.count == h2.count)
			return 0;
		else
			if(this.count < h2.count)
				return -1;
			else
				return 1;
	}
}
