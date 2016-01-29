package testing;

import java.util.Arrays;

public class TestCal {

	public static short[] add(short[] num1, short[] num2, short base) {
		// for addition, there's a possibility that the leftmost digit would
		// result in an extra digit.
		// therefore maxLength of the result should be the maximum of the two +
		// 1
		int maxLength = Math.max(num1.length, num2.length) + 1;
		short[] result = new short[maxLength];
		short carry = 0;

		for (int i = 0; i < result.length - 1; i++) {
			result[i] = (short) ((num1[i] + num2[i] + carry) % base);
			carry = (short) ((num1[i] + num2[i] + carry) / base);
			if (i == (result.length - 2) && carry != 0) { // if the leftmost
															// digit has carry
															// after addition,
															// give it to the
															// one more digit of
															// the result on the
															// left.
				result[i + 1] = carry;
			}
		}

		return result;
	}

	public static short[] subtract(short[] num1, short[] num2, short base) {
		// for subtraction, as I'm assuming num1 > num2 during base-conversion,
		// the maximum length will be num1.length only.
		// which also means negative numbers won't appear.
		int maxLength = num1.length;
		short result[] = new short[maxLength];
		int borrow = 0;

		for (int i = 0; i < num1.length; i++) {
			result[i] = (short) (num1[i] - num2[i] - borrow);
			borrow = 0;
			if (result[i] < 0) {
				result[i] += base; // borrow 1 => result[i] += 1 x base
				borrow = 1;
			}
		}
		return result;
	}

	public static short[] multiply(short[] num1, short[] num2, short base) {
		// maximum length for multiplication is the total number of digits of
		// the two.
		// case: num1 x num2
		int maxLength = num1.length + num2.length;
		short result[] = new short[maxLength];
		short zero = 0;
		Arrays.fill(result, zero);

		// num1 x num2 -> num2.length's amount of number to add after
		// multiplication:
		// row = amount of number to add(num2.length); column = num1.length +
		// num2.length(including shifts)
		short temp[][] = new short[num2.length][maxLength];
		
		// Initialize all elements in the array[][] to 0;
		for (int i = 0; i < num2.length; i++) {
			Arrays.fill(temp[i], zero);
		}
		
		// multiplication stage
		for (int i = 0; i < num2.length; i++) {
			int carry = 0;
			for (int j = 0; j < num1.length; j++) {
				temp[i][i + j] = (short) ((num1[i] * num2[j] + carry) % base);
				carry = (short) ((num1[i] * num2[j] + carry) / base);
				if ((j == num1.length - 2) && carry != 0) { // add carry to the
															// left-most digit.
					temp[i][i + j + 1] = (short) carry;
				}
			}
		}

		// addition stage
		for (int i = 0; i < num2.length; i++) {
			// result = result + temp[i] -> cumulative
			result = add(result, add(result, temp[i], base), base);
		}
		return result;

	}

	public static short[] divide(short[] num1, short[] num2, short base, short R) {
		// maximum length for division is the length of the number being divided
		// which, in this case, is num1.
		int maxLength = num1.length;
		short result[] = new short[maxLength];

		return result;
	}

	// **optional** display optimization; get rid of the '0's at the beginning
	// if exists
	public static short[] displayOptimize(short[] num) {

		int numOfZero = 0;
		if (num.length == 1)
			return num;
		else {
			for (int i = num.length - 1; i >= 1; i--) {
				if (num[i] == 0) {
					numOfZero++;
				} else {
					break;
				}
			}
			int resultLength = num.length - numOfZero;
			short[] result = new short[resultLength];
			for (int i = 0; i < resultLength; i++) {
				result[i] = num[i];
			}
			return result;
		}
	}

	public static void printArray(short[] num) {
		for (int i = 0; i < num.length; i++) {
			System.out.print(num[i] + " ");
		}
		System.out.println("\n");
	}

	// for testing and convenience of display
	public static void printArray(short[] num, boolean isConv) {
		for (int i = num.length - 1; i >= 0; i--) {
			System.out.print(num[i] + " ");
		}
		System.out.println("\n");
	}

	public static void main(String[] args) {
		short[] num1 = 
//			{ 10, 11, 15 }; // 15,11,10 -> FBA
			{ 9, 8, 7};		// 7,8,9 in deci
		short[] num2 = 
//			{ 9, 13, 14 }; // 14,13,9 -> ED9
			{ 6, 5, 4};		//4,5,6 in deci
		short base = 10;
		short[] result =
			// add(num1, num2, base);
			// subtract(num1,num2,base);
			multiply(num1, num2, base);
		
		printArray(result, true);

		// short[] num0 = { 3, 4, 0, 0 }; // 0043 -> 43 <=> 3400 -> 34
		// printArray(num0,true);
		// result = displayOptimize(num0);
		// printArray(result,true); // for convenient display of the actual
		// number.
	}
}
