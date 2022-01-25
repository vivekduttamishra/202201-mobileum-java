package in.conceptarchitect.utils.testing;

import static org.junit.Assert.fail;

public class CustomAssert {

	public static void assertContainsSubstring(String mainString, String expectedContent,boolean caseSensetive) {
		if(!caseSensetive) {
			mainString=mainString.toLowerCase();
			expectedContent=expectedContent.toLowerCase();
		}
		
		
	
		if(mainString.indexOf(expectedContent)==-1) {
			fail(String.format("Couldn't find '%s' inside '%s'", expectedContent, mainString));
			
		}
	}
	
	public static void assertContainsSubstring(String mainString, String expectedContent) {
		assertContainsSubstring(mainString, expectedContent,false);
	}
	
	
}
