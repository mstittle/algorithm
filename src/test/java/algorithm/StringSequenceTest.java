package algorithm;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import algorithm.StringSequence;

public class StringSequenceTest {
	static String[] TestCases = { 
			"0", "1", "01", "10", "101", "010", "011", "100", "0120", "010110111011110", "010110111011111" };

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
	public void testFindLongestSequenceOfOnesBoundedByZeros() {

		for (String testCase : TestCases) {
			int len = StringSequence.findLongestSequenceOfOnesBoundedByZeros(testCase);
			System.out.printf("TestCase:%s len:%d\n", testCase, len);
			assertEquals(getActualLength(testCase), len);
		}
	}

}
