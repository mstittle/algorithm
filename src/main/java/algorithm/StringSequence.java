package algorithm;

/**
 * Class for computing string sequences lengths
 * 
 * @author mike stittleburg
 *
 */
public class StringSequence {

	/**
	 * 				State machine
	 * 			
	 * 	start --->[Initial] --- 0 --- > [InLeadingZero] --- 1 ----> [InOneSequence] --- 0 ---> [InTraingZero] 
	 *                ^   |					      ^	  |					  ^   ^   |                 ^   |   |
	 *                1---/					      0---/ 				  |   1 --/                 0---/   |
     *                                                                    --------------------------1-------/
	 * @param input
	 * @return longest length of sequence of 1s bounded by 0s on left and right 
	 */
	static int findLongestSequenceOfOnesBoundedByZeros(String input) {
		int n = input.length();
		int i = 0;

		// no sequence can be present in a string less then 3 chars
		if  ( n < 3) {
			System.out.println("input to short < 3");
			return 0;
		}
		
		// set initial state
		ProblemState state  = ProblemState.Initial;
		
		// keep track of counts and max values
		int countOfOnes = 0;
		int maxSeqLen = 0;
		
		while( i < n ) {
			
			char c = input.charAt(i);
			i++;
			
			// discard and invalid strings
			if ( c != '0' && c != '1') {
				System.out.println("invalid characters in input string - only 1 | 0 allowed ");
				return 0;
			}
			
			// state machine
			switch ( state ) {
				case Initial:
					if ( c == '0')
						state = ProblemState.InLeadingZero;
					break;
					
				case InLeadingZero:
					if ( c == '1') {
						state = ProblemState.InOneSequence;
						countOfOnes = 1;
					}
					break;
					
				case InOneSequence:
					if ( c == '1') {
						countOfOnes++;
					}
					else if ( c == '0') {
						state = ProblemState.InTrailingZero;
						if ( maxSeqLen < countOfOnes )
							maxSeqLen = countOfOnes;
					}
					break;
					
				case InTrailingZero:
					if ( c == '1') {
						state = ProblemState.InOneSequence;
						countOfOnes = 1;
					}
					break;
				}
		}
  		return maxSeqLen;
	}
}
