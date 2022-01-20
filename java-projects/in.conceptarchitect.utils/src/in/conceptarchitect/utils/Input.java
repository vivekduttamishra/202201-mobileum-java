package in.conceptarchitect.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Input {
	 BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
	
	public String readString(String prompt) {
		try {
		System.out.print(prompt);
		return reader.readLine();
		}catch(Exception e) {
			return "";
		}
	}
	
	public int readInt(String prompt) {
		
		try {
			return Integer.parseInt(readString(prompt));
		}catch(Exception ex) {
			return 0;
		}
	}

}
