package project_2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ByteCounter { //runs through the file and creates the byte[] from the text
	
	private byte[] bList;
	private byte[] countDecList = null; //these are lists that are created when the list is ordered for the first time
	private byte[] countIncList = null;
	private int[] count = new int[255]; //the index is the character and the contents are the count
	private int size; //the number of unique characters in the list
	
	public ByteCounter(byte[] byteArray){
		bList = byteArray;
		generateCount();
		generateBList();
	}
	
	public ByteCounter(String fileLocation){
		try{
		Path filePath = Paths.get(fileLocation);
		bList = Files.readAllBytes(filePath);
		generateCount();
		generateBList();
		}catch(IOException e){
			System.out.println("IOException thrown");
		}
	}
	
	private void generateCount(){ //fills in count with the counts of every byte in bList
		for(int i=0; i<bList.length; i++){
				count[((Byte)bList[i]).intValue() + 128]++; //finds the int value [0, 255] of the byte and increases that index in count by 1
		}
	}
	
	private void generateBList(){ //make sure to run after generate Count runs
		bList = new byte[255];
		size = 0;
		for(int i=0; i<count.length; i++){ //this loops removes duplicates because it only includes a byte if the count is not 0. It doesn't matter what the count is
			if(count[i] != 0){ //if the count of a byte is not 0
				bList[size] = ((Integer)(i - 128)).byteValue(); //add that byte value to the bList[i]
				size ++;
			}
		}
	}
	
	public int getCount(byte b){
		return count[((Byte)b).intValue() + 128];
	}
	
	public int[] getCount(byte[] b){
		int[] countArray = new int[b.length];
		for(int i=0; i<b.length; i++){ //for every value in b
			countArray[i] = getCount(b[i]); //find the respective count and put it in the same index for countArray
		}
		return countArray;
	}
	
	public byte[] getElements(){ //returns bList
		byte[] tempArr = new byte[size];
		for(int i=0; i<size; i++){  //moves all of bList up to size over the tempArr
			tempArr[i] = bList[i];
		}
		return tempArr;
	}
	
	public void setOrder(String order){ //changes the order of bList not count
		//bytes that have the same count should be ordered by byte so alway order by bList before reordering by countInc or Dec
		generateBList(); //just recreates bList in its default state of byte order
			if(order.equalsIgnoreCase("countInc"))
				setOrderCountInc();
			if(order.equalsIgnoreCase("countDec"))
				setOrderCountDec();
	}
	
	private void setOrderCountInc(){
		if(countIncList == null){
			int minCount; //the current smallest count
			int minByte; //index of the current smallest byte
			byte temp;
			for(int i=0; i<size; i++){ //runs through bList
				minCount = getCount(bList[i]); //the smallest count defaults to the count at position i
				minByte = i; //starts with the smallest being at index i
				for(int j=i+1; j<size; j++){ //compares the count at i to the rest and sees if any are smaller, if so they become the smallest
					if(getCount(bList[j]) < minCount){ //if this byte has a smaller count then the current min
						minCount = getCount(bList[j]); //it becomes the new min
						minByte = j;
					}
				}
				temp = bList[i];
				bList[i] = bList[minByte];
				bList[minByte] = temp;
			}
			countIncList = bList;
		}
		else
			bList = countIncList;
	}
	
	private void setOrderCountDec(){
		if(countDecList == null){
			int maxCount; //index of the current largest count
			int maxByte; //index of the current largest byte
			byte temp;
			for(int i=0; i<size; i++){ //runs through bList
				maxCount = getCount(bList[i]);
				maxByte = i; 
				for(int j=i+1; j<size; j++){ //compares the bytes left to whatever is after them
					if(getCount(bList[j]) > maxCount){
						maxCount = getCount(bList[j]);
						maxByte = j;
					}
				}
				temp = bList[i];
				bList[i] = bList[maxByte];
				bList[maxByte] = temp;
			}
			countDecList = bList;
		}
		else 
			bList = countDecList;
	}
	
	public String toString(){
		String tempString = "";
		for(int i=0; i<size; i++){
			tempString = tempString.concat((((Byte)bList[i]).intValue()) + ":" + count[((Byte)bList[i]).intValue() + 128] + " ");
		}
		return tempString.substring(0, tempString.length()-1); //gets rid of the extra space at the end
	}
	
	public String toString(String format){
		if(format.equalsIgnoreCase("char") || format.equalsIgnoreCase("character")){
			String tempString = "";
			for(int i=0; i<size; i++){
				tempString = tempString.concat((char)(bList[i]) + ":" + count[((Byte)bList[i]).intValue() + 128] + " "); //converts the byte to char
			}
			return tempString.substring(0, tempString.length()-1);
		}
		else if(format.equalsIgnoreCase("byte"))
			return toString();
		return null;
	}
}

