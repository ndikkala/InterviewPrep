package algocode;

import java.io.*;
import java.util.*;

public class FruitFarm {
	/*
	 * Find the number of trees at palindromic indices of a given interval in
	 * the following order of priority 1. Maximum number of trees 2. Minimize
	 * length of the farm chosen and should be atmost L 3. Leftmost farm
	 */
	static ArrayList<String> output = new ArrayList<String>();

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(in.readLine());
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				System.out));
		for (int i = 0; i < t; i++) {
			String[] input = in.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			int l = Integer.parseInt(input[2]);
			processInput(a, b, l);
		}
		for (int i = 0; i < output.size(); i++) {
			out.write(output.get(i));
			out.write("\n");
		}

		out.flush();
		out.close();

	}

	private static void processInput(int a, int b, int l) {
		// Instead of traversing all the numbers in the given interval to find
		// palindromes, we can generate the palindromes in the given interval.
		// Also, we don't have to generate all palindromes in the given interval
		// to look for the farm
		// Observation
		// 1. If the number of digits increases when 9 resets to 0 while
		// generating palindromes, the length of the part forming the farm is
		// the least at that point, provided that point is within the range and
		// length is less than L
		// 2. If the number of digits does not change when 9 resets to 0 and if
		// the number of digits is even, the length is lesser at the point where
		// 9 resets to 0
		// 3. If the number of digits does not change when 9 resets to 0 and if
		// the number of digits is odd, the length is greater at the point where
		// 9 resets to 0

		// If max length is 1, add the first palindrome in the input and return
		if (l == 1) {
			if (checkIfPalindrome(a)) {
				output.add(a + " " + a);
				return;
			} else {
				int p = getNextPalindrome(a);
				if (p < b) {
					output.add(p + " " + p);
				} else {
					output.add("Barren Land.");
				}

				return;
			}

		}

		// Number of digits in start and end are different, look for the point
		// where 9 resets to 0 and no. of digits increases, else pick the first
		// palindrom from left
		if (Integer.toString(a).length() != Integer.toString(b).length()) {
			int lenOfa = Integer.toString(a).length();
			String pt = "";
			for (int k = 0; k < lenOfa; k++) {
				pt += "9";
			}

			int point = Integer.parseInt(pt);
			int compare = getNextPalindrome(point);
			int next = point;
			if (compare > b || (compare - point) > l) {
				if (!checkIfPalindrome(a)) {
					point = getNextPalindrome(a);
				} else {
					point = a;
				}
				next = point;
			}

			if (point > b) {
				if (!checkIfPalindrome(a)) {
					point = getNextPalindrome(a);
					if (point > b) {
						output.add("Barren Land.");
						return;
					}
				} else {
					point = a;
				}

			}

			int length = 1;
			//Take care of single digits from the start because if interval has single digits, they account for most palindromes 
			if (lenOfa == 1) {
				point = a;
				next = point;
				if (length == l) {
					output.add(point + " " + next);
					return;
				}
				while (next < 10 && length < l) {
					int temp = next;
					next++;
					if(next==b && (!(checkIfPalindrome(next))))
					{
						next =  temp;
						output.add(point + " " + next);
						return;
					}
					length = length + 1;

				}

			}

			//Check if any more palindromes can be added if length is stil less than max length and end point is still within interval
			while (next < b && length < l) {
				int temp = next;
				next = getNextPalindrome(next);
				int diff = next - point;
				if (diff > l || next > b) {
					next = temp;
					break;
				}
				length = diff;
			}
			if(next>=b && (next-point)<l){
				//get prev pals of point
				while (point > a && length < l) {
					int temp = point;
					point = getPrevPalindrome(point);
					int diff = next - point;
					if (diff > l || point < a) {
						point = temp;
						break;
					}
					length = diff;
				}
				
			}
			output.add(point + " " + next);
			
			
		}

		// All single digit scenario
		else if (a > 0 && b < 10) {
			int length = 1;
			int end = a;
			while (length < l && end < b) {
				int temp = end;
				end = end + 1;
				int diff2 = end - a;
				if (end > b || diff2 > l) {
					end = temp;
					break;
				}
				length = diff2;
			}
			output.add(a + " " + end);
		}
		// All are Double Digits
		else if (a > 9 && b < 100) {
			// start is multiple of 11
			if (a % 11 == 0) {
				int length = 1;
				int end = a;
				while (length < l && end < b) {
					int temp = end;
					end = end + 11;
					int diff3 = end - a;
					if (end > b || diff3 > l) {
						end = temp;
						break;
					}
					length = diff3;
				}
				output.add(a + " " + end);
			}
			// start is not a multiple of 11
			else {
				int remainder = a % 11;
				int diff = 11 - remainder;
				int nextMultiple = a + diff;
				if (nextMultiple > b) {
					output.add("Barren Land.");
					return;
				}
				int length = 1;
				int end = nextMultiple;
				while (length < l && end < b) {
					int temp = end;
					end = end + 11;
					int diff1 = end - nextMultiple;
					length = diff1;
					if (end > b || length > l) {
						end = temp;
						break;
					}

				}
				output.add(nextMultiple + " " + end);
			}

		}
		// All are triple digits
		else if (a > 99 && b < 1000) {
			// No. of digits is odd so the least length is at the point where
			// there is no reset of 9 to 0, example., 101, 111, 121 have a
			// difference of 10 everytime
			// whereas 191 and 202 have a difference of 11. Since we want to
			// minimize the length, we are getting same number of palindromes
			// for lesser length at the pt
			// where 9 does not reset to 0.
			int length = 1;
			int pal;
			if (!(checkIfPalindrome(a)))
				pal = getNextPalindrome(a);
			else
				pal = a;
			if (pal > b) {
				output.add("Barren Land.");
				return;
			}
			int end = pal;
			while (length < l && end < b) {
				//For 3-digit numbers difference between pallindromes is 10 for the points where 9 is not reset to 0
				if (l >= 10) {
					int temp = end;
					end = getNextPalindrome(end);
					if(end-temp == 11)
					{
						pal=end;
					}
					int diff = end - pal;
					if (diff > l || end > b) {
						end = temp;
						break;
					}
					length = diff;

				} else {
					end = pal;
					break;
				}

			}
			output.add(pal + " " + end);

		}

	}

	private static boolean checkIfPalindrome(int num) {
		String number = Integer.toString(num);
		String reverse = reverse(number);
		if (number.equals(reverse))
			return true;
		else
			return false;

	}

	private static String reverse(String input) {
		char[] chars = input.toCharArray();
		Stack<Character> s = new Stack<Character>();
		String reverse = "";
		for (char c : chars) {
			s.push(c);
		}
		while (!s.empty()) {
			reverse = reverse + s.pop();
		}
		return reverse;
	}

	private static int getNextPalindrome(int num) {
		// This method finds the next palindrome of num, when num itself is not
		// a palindrome.
		String number = Integer.toString(num);
		int length = number.length();
		boolean isLengthEven = false;
		int half = length / 2;
		if (length % 2 == 0)
			isLengthEven = true;
		if (isLengthEven) {
			// Number of digits is even, so number should be exact mirror image
			// of n/2 digits
			String leftHalf = number.substring(0, half);
			String rightHalf = number.substring(half, length);
			int leftPtr = half - 1;
			int rightPtr = 0;
			while ((leftPtr >= 0) && (rightPtr <= half)) {
				if (Character.getNumericValue(rightHalf.charAt(rightPtr)) < Character
						.getNumericValue(leftHalf.charAt(leftPtr))) {
					// First digit of right half is less than last digit of left
					// half, simply put the mirror image to get next pal
					return Integer.parseInt(leftHalf + reverse(leftHalf));

				} else if (Character
						.getNumericValue(rightHalf.charAt(rightPtr)) > Character
						.getNumericValue(leftHalf.charAt(leftPtr))) {
					// First digit of right half is greater than last digit of
					// left half, increment the rightmost digit of lefthalf and
					// concatenate its mirror
					int rightMost = Character.getNumericValue(leftHalf
							.charAt(leftPtr));
					rightMost++;
					leftHalf = leftHalf.substring(0, leftPtr)
							+ Integer.toString(rightMost);
					return Integer.parseInt(leftHalf + reverse(leftHalf));
				} else {
					// First digit of right half is equal to last digit of left
					// half, move the pointers

					leftPtr--;
					rightPtr++;
					char[] nines = number.toCharArray();
					boolean allNines = true;
					for (int m = 0; m < nines.length; m++) {
						if (nines[m] != '9')
							allNines = false;
					}
					if (leftPtr < 0 && allNines) {
						// digit reset to 0 and increase size
						int newlength = number.length() + 1;
						int newhalf = newlength / 2;
						boolean isNewLengthEven = false;
						if (newlength % 2 == 0)
							isNewLengthEven = true;
						String newLeft = "1";
						for (int k = 0; k < (newhalf - 1); k++) {
							newLeft += "0";
						}
						String newRight = reverse(newLeft);
						if (isNewLengthEven) {
							return Integer.parseInt(newLeft + newRight);
						} else {
							return Integer.parseInt(newLeft + "0" + newRight);
						}
					} else if (leftPtr < 0 && !allNines && number.length() == 2) {
						return nextDoublePal(Integer.parseInt(number));
					}
				}
			}
		} else {
			// Number of digits is odd, so right half will be mirror image of
			// left half for n/2 elements with middle element any digit
			String leftHalf = number.substring(0, half); // 2
			String rightHalf = number.substring(half + 1, length);// 2
			String middle = number.substring(half, half + 1);// 9
			int leftPtr = half - 1;// 0
			int rightPtr = 0;
			while ((leftPtr >= 0) && (rightPtr <= half)) {
				if (Character.getNumericValue(rightHalf.charAt(rightPtr)) < Character
						.getNumericValue(leftHalf.charAt(leftPtr))) {
					// First digit of right half is less than last digit of left
					// half, simply put the mirror image to get next pal
					return Integer.parseInt(leftHalf + middle
							+ reverse(leftHalf));

				} else if (Character
						.getNumericValue(rightHalf.charAt(rightPtr)) > Character
						.getNumericValue(leftHalf.charAt(leftPtr))) {
					// First digit of right half is greater than last digit of
					// left half, increment the rightmost digit of lefthalf and
					// concatenate its mirror
					int rightMost = Character.getNumericValue(leftHalf
							.charAt(leftPtr));
					rightMost++;
					leftHalf = leftHalf.substring(0, leftPtr)
							+ Integer.toString(rightMost);
					return Integer.parseInt(leftHalf + "0" + reverse(leftHalf));
				} else {
					// First digit of right half is equal to last digit of left
					// half, move the pointers
					leftPtr--;// -1
					rightPtr++;// 1
					char[] nines = number.toCharArray();
					boolean allNines = true;
					for (int m = 0; m < nines.length; m++) {
						if (nines[m] != '9')
							allNines = false;
					}
					if (leftPtr < 0 && allNines) {
						// digit reset to 0 and increase size
						int newlength = number.length() + 1;
						int newhalf = newlength / 2;
						boolean isNewLengthEven = false;
						if (newlength % 2 == 0)
							isNewLengthEven = true;
						String newLeft = "1";
						for (int k = 0; k < (newhalf - 1); k++) {
							newLeft += "0";
						}
						String newRight = reverse(newLeft);
						if (isNewLengthEven) {
							return Integer.parseInt(newLeft + newRight);
						} else {
							return Integer.parseInt(newLeft + "0" + newRight);
						}
					} else if (leftPtr < 0 && !allNines && number.length() == 2) {
						return nextDoublePal(Integer.parseInt(number));
					} else if (leftPtr < 0 && !allNines && number.length() == 3) {
						int mid = Integer.parseInt(middle);
						if (mid == 9) {
							mid = 0;
							int leftDigit = Character.getNumericValue(leftHalf
									.charAt(leftPtr + 1)) + 1;
							int rightDigit = Character
									.getNumericValue(rightHalf
											.charAt(rightPtr - 1)) + 1;
							char[] leftChars = leftHalf.toCharArray();
							char[] rightChars = rightHalf.toCharArray();
							leftChars[leftPtr + 1] = Integer
									.toString(leftDigit).charAt(0);
							rightChars[rightPtr - 1] = Integer.toString(
									rightDigit).charAt(0);
							leftHalf = new String(leftChars);
							rightHalf = new String(rightChars);

						} else
							mid++;
						return Integer.parseInt(leftHalf + mid + rightHalf);
					}
				}
			}
		}

		return num;
	}

	private static int getPrevPalindrome(int num){
		String number =  Integer.toString(num);
		int len = number.length();
		if(len == 2){
			return (num-11);
		}
		if(len == 3){
			int half = len/2;
			String mid = number.substring(half, half+1);
			if(mid.equals("0"))
				return (num-21);
			return (num-10);
		}
		return num;
	}
	private static int nextDoublePal(int num) {
		if (num % 11 == 0) {
			return num + 11;
		} else {
			int remainder = num % 11;
			int diff = 11 - remainder;
			int nextMultiple = num + diff;
			return nextMultiple;
		}
	}
}
