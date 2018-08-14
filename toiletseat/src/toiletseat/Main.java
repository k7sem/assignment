package toiletseat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * For toilet seat adjustment case
 * 
 * @author Kevin
 * @since 2018/08/14
 *
 */
public class Main {
	
	private String inputString;
	
	private int adjustCountOfLeaveUp, adjustCountOfLeaveDown, adjustCountOfLeaveFind;
	
	private static final char UP = 'U';
	private static final char DOWN = 'D';
	
	public Main() {
		
	}
	
	public void getInput() {
		System.out.println("Please type the sequence of seat status:");
	    InputStreamReader is = new InputStreamReader(System.in);
	    BufferedReader br = new BufferedReader(is);
	    try {
	    	inputString = br.readLine();
	    }
	    catch(IOException e){
	      //e.printStackTrace();
	      System.out.println("Error occurred, please run program again");
	    }    
	}
	
	public void calculating() {
		if (inputString != null && (inputString.length() >=2 && inputString.length() <= 1000)) {
			char[] charArray = inputString.toCharArray();
			char lastChar = 0;
			for (int i = 0; i < charArray.length; i++) {
				
				if (i == 1) {
					if (charArray[i] == DOWN && lastChar == UP) {
						adjustCountOfLeaveUp += 2;
						adjustCountOfLeaveDown += 1;
						adjustCountOfLeaveFind += 1;
					}
					if (charArray[i] == DOWN && lastChar == DOWN) {
						adjustCountOfLeaveUp += 1;
					}
					if (charArray[i] == UP && lastChar == DOWN) {
						adjustCountOfLeaveUp += 1;
						adjustCountOfLeaveDown += 2;
						adjustCountOfLeaveFind += 1;
					}
					if (charArray[i] == UP && lastChar == UP) {
						adjustCountOfLeaveDown += 1;
					}
				}
				if (i > 1) {
					if (charArray[i] == DOWN && lastChar == UP) {
						adjustCountOfLeaveUp += 2;
						
						adjustCountOfLeaveFind += 1;
					}
					if (charArray[i] == DOWN && lastChar == DOWN) {
						// suppose last char already changed to UP
						adjustCountOfLeaveUp += 2;
					}
					if (charArray[i] == UP && lastChar == DOWN) {
						
						adjustCountOfLeaveDown += 2;
						adjustCountOfLeaveFind += 1;
					}
					if (charArray[i] == UP && lastChar == UP) {
						// suppose last char already changed to DOWN
						adjustCountOfLeaveDown += 2;
					}
				}
				lastChar = charArray[i];
			}
			System.out.println(adjustCountOfLeaveUp);
			System.out.println(adjustCountOfLeaveDown);
			System.out.println(adjustCountOfLeaveFind);
			
		} else {
			System.out.println("Only support string length >= 2 and <= 1000");
		}
		
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.getInput();
		main.calculating();
	}

}
