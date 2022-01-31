package javaapp17.iotest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Before;
import org.junit.Test;

public class IOTest {
	
	String path="D:\\MyWorks\\Corporate\\202201-mobileum-java\\sample.txt";
	String outputPath="D:\\MyWorks\\Corporate\\202201-mobileum-java\\sample-output.txt";
	File inputFile;
	File outputFile;
	long length;

	@Before
	public void setUp() throws Exception {
		
		inputFile=new File(path);
		assumeTrue(inputFile.exists());		
		length=inputFile.length();
		outputFile=new File(outputPath);
		
		if(outputFile.exists())
			outputFile.delete(); //remove output file if already existing
		
		//each test should create it's output file
		
	}
	
	
	@Test
	public void shouldDetectBasicInfoAboutFile() {
		var file=new File(path);
		assertEquals("sample.txt",file.getName());
		assertEquals("D:\\MyWorks\\Corporate\\202201-mobileum-java",file.getParent());
	}

	@Test
	public void showReturnMetaInformationForExistingFile() {
		var file=new File(path);
		
		assertTrue(file.exists());
		//System.out.println(file.length());
		//System.out.println(new Date(file.lastModified())); //date represented as ms elepased since 1/1/1970
		assertTrue(file.length()>0);
		
	}
	
	@Test
	public void shouldDetectNonExistingFile() {
		assertFalse(outputPath+" shouldn't exit", outputFile.exists());
	}
	
	
	
	@Test
	public void canReadFileByteByBytes() throws IOException {
		var istream= new FileInputStream(path);
		String data="";
		
		int b; //we want to read a byte but take it in int. Why?
		
		while(istream.available()>0) {
			b= istream.read(); //reads one byte as int. returns -1 when we reach end of file
			
			
			data+=(char)b; //use this byte as an ASCII character
			
		}
		istream.close();
		//System.out.println(data);
		assertEquals(length, data.length());
		
	}

	
	@Test
	public void canReadFileUsingReader() throws IOException{
		var reader=new FileReader(inputFile);
		int ch;
		String data="";
		while(true) {
			ch= reader.read();
			if(ch==-1)
				break;
			data+=(char) ch;
		}
		reader.close();
		assertEquals(length, data.length());
	}
	

	@Test
	public void canCopyTheDataByteByByte() throws IOException{
		
		var istream= new FileInputStream(inputFile);
		var ostream=new FileOutputStream(outputFile);
		try {
			
			
			while(istream.available()>0) {
				int data= istream.read();
				ostream.write(data);
			}
			
		}finally {
			istream.close();
			ostream.close();
		}
		
		assertEquals(inputFile.length(), outputFile.length());
		
	}

	@Test
	public void canCopyFileCharByChar() throws IOException {
		var reader=new FileReader(inputFile);
		var writer=new FileWriter(outputFile);
		try {
			while(true) {
				int ch=reader.read();
				if(ch==-1)
					break;
				writer.write(ch);
			}
			
		}finally {
			reader.close();
			writer.close();
		}
		
		assertEquals(inputFile.length(), outputFile.length());
	}


	@Test
	public void canCopyFileLineByLineUsingBufferedReader() throws IOException{
		
		var fileReader=new FileReader(inputFile);
		var fileWriter=new FileWriter(outputFile);
		var input= new BufferedReader(fileReader);
		var output= new PrintWriter(fileWriter);
		int lines=0;
		try {
			
			String str=null;
			while(true) {
				str=input.readLine();
				if(str==null)
					break;
				output.println(str);
				lines++;
			}			
		}finally {
			input.close();
			output.close();
			fileReader.close();
			fileWriter.close();
		}
		System.out.println("total lines read is "+lines);
		assertEquals(inputFile.length(),outputFile.length());
		
	}
	
}
