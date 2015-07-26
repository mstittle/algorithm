 package algorithm;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import algorithm.StringSequence;

public class StringSequenceTest {
	static String[] ToShorTestCases = { 
			"0", "1", "01", "10", "11"
	};

	static String[] InvalidStringTestCases = { 
			"012", 
			"020"
	};

	static String[] SingleSequenceTestCases = { 
			"010", 
			"0110", 
			"01110", 
			"011110", 
	};

	static String[] NoSequenceTestCases = { 
			"011", 
			"110", 
			"110", 
			"000", 
			"1101", 
	};


	static String[] MultipleSequenceTestCases = { 
			"010110111011110", 
			"010110111011111",
			"010110111011111010110111011110"
	};
	
	String generateRandomString(int len) {
		StringBuilder sb = new StringBuilder(len); 
		for ( int i = 0; i < len; i++) {
		double r = Math.random();
		
		if ( r < 0.5 )
			sb.append("0");
		else
			sb.append("1");
			
		}
		return sb.toString();
	}
	
	/**
	 * use a regexp to validation the subsequence length
	 * 
	 * @param testCase
	 * @return
	 */
	int getActualLength(String testCase) {
		Pattern p = Pattern.compile("0+(1+)0+");
		int maxSeqLen = 0;
		int start = 0;
		Matcher m = p.matcher(testCase);

		// iterate over all matches looking for the longest
		while (m.find(start)) {
			if (m.groupCount() > 0) {
				String group = m.group(1);
				// System.out.println(group.length());

				// back up to the last trailing zero
				start = m.end() - 1;
				if (maxSeqLen < group.length())
					maxSeqLen = group.length();
			}
		}
		return maxSeqLen;
	}
	
	@Test
	public void testRandomString() {

		System.out.println("testRandomString");
		for (int i = 0; i < 100; i++) {
			String testCase = generateRandomString(100);
			int len = StringSequence.findLongestSequenceOfOnesBoundedByZeros(testCase);
			System.out.printf("TestCase:%s len:%d\n", testCase, len);
			assertEquals(getActualLength(testCase), len);
		}
	}
	
	@Test
	public void testShortStrings() {
		System.out.println("testShortStrings");

		for (String testCase : ToShorTestCases) {
			int len = StringSequence.findLongestSequenceOfOnesBoundedByZeros(testCase);
			System.out.printf("TestCase:%s len:%d\n", testCase, len);
			assertEquals(getActualLength(testCase), len);
		}
	}
	
	@Test
	public void testInvalidStrings() {
		System.out.println("testInvalidStrings");

		for (String testCase : InvalidStringTestCases) {
			int len = StringSequence.findLongestSequenceOfOnesBoundedByZeros(testCase);
			System.out.printf("TestCase:%s len:%d\n", testCase, len);
			assertEquals(getActualLength(testCase), len);
		}
	}
	
	@Test
	public void testNoSequenceStrings() {
		System.out.println("testNoSequenceStrings");

		for (String testCase : NoSequenceTestCases) {
			int len = StringSequence.findLongestSequenceOfOnesBoundedByZeros(testCase);
			System.out.printf("TestCase:%s len:%d\n", testCase, len);
			assertEquals(getActualLength(testCase), len);
		}
	}

	@Test
	public void testMultiSequenceStrings() {
		System.out.println("testMultiSequenceStrings");

		for (String testCase : MultipleSequenceTestCases) {
			int len = StringSequence.findLongestSequenceOfOnesBoundedByZeros(testCase);
			System.out.printf("TestCase:%s len:%d\n", testCase, len);
			assertEquals(getActualLength(testCase), len);
		}
	}
}
