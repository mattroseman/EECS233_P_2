package project_2;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;


public class HuffmanCodeFundTest {
	
	/* Other JUnit tests.
     * 
     * Write your own JUnit tests below to check the correctness of your implementation.
     * 
     * When you turn in your draft (and final) we will run our own test suite on your code 
     * and provide you with the feedback.
     * 
     * Your draft code should contain a complete set of methods as specified in the assignment.
     * Any methods not yet implemented should be written as skeleton methods with an empty body. 
     * 
	 * The JUnit tests below help to ensure that your methods compile with our test suite and have 
	 * correctly typed arguments. You can replace them with more meaningful tests to test their
	 * functionalities.
     */
	
	@Test
	public void testByteArrayArgumentConstructorAndCode() {
    	HuffmanCode hc = new HuffmanCode(new byte [] {(byte)'a', (byte)'b', (byte)'a', (byte)'c', (byte)'b', (byte)'d', (byte)'c', (byte)'b'});
    	assertTrue("The constructor make a HuffmanCode using byte array", Arrays.equals(hc.code((byte)'b'), new boolean[]{true, true}));
	}
	
	@Test
	public void testStringArgumentConstructor() throws IOException {
    	HuffmanCode hc = new HuffmanCode("C:\\Users\\Matthew\\Desktop\\text_P_2.txt");
    	String code = hc.codeString((byte)' ');
    	assertEquals("The constructor make a HuffmanCode from a file", "1100", code);
	}
	
	@Test
	public void testByteAndCountArraysConstructor() {
    	HuffmanCode hc = new HuffmanCode(new byte [] {(byte)'a', (byte)'b'}, new int [] {2, 3});
    	assertTrue("The constructor make a HuffmanCode using byte and count arrays", true);
	}
	
	@Test
	public void testCodeMethod() {
    	HuffmanCode hc = new HuffmanCode(new byte [] {(byte)'h', (byte)'e', (byte)'l', (byte)'o', (byte)' ', (byte)'w', (byte)'r', (byte)'d'}, new int [] {1, 1, 3, 2, 1, 1, 1, 1});
    	boolean[] code = hc.code((byte)'h');
    	boolean[] temp = {false, true};
    	assertTrue("This method reurns the code of specific byte", Arrays.equals(temp, code));
	}
	
	@Test
	public void testToStringMethod() {
    	HuffmanCode hc = new HuffmanCode(new byte [] {(byte)'a', (byte)'b'}, new int [] {2, 3});
    	String s = hc.toString();
    	assertTrue("This method returns astring containing the table of the binary encodings of each byte in the Huffman tree",
			true);
	}
}