package conv;

public class tester {
	// m = (X.Y) in base B ---> (U.VW) in base R
	// Input: X[], Y[], B, R output: U[], V[], W[];
	class properties { // for simplicity of returning purposes.
		//
		// private short[] X,Y,U,V,W;
		// private short B,R;
		// public properties(short[] X, short[] Y, short B, short R){
		// this.X = X;
		// this.Y = Y;
		// this.B = B;
		// this.R = R;
		// }
		// public short[] getX() {
		// return X;
		// }
		// public void setX(short[] x) {
		// X = x;
		// }
		// public short[] getY() {
		// return Y;
		// }
		// public void setY(short[] y) {
		// Y = y;
		// }
		// public short[] getU() {
		// return U;
		// }
		// public void setU(short[] u) {
		// U = u;
		// }
		// public short[] getV() {
		// return V;
		// }
		// public void setV(short[] v) {
		// V = v;
		// }
		// public short[] getW() {
		// return W;
		// }
		// public void setW(short[] w) {
		// W = w;
		// }
		// public short getB() {
		// return B;
		// }
		// public void setB(short b) {
		// B = b;
		// }
		// public short getR() {
		// return R;
		// }
		// public void setR(short r) {
		// R = r;
		// }
		//
		//
	}

	public static short[] add(short[] num1, short[] num2, short base) {
		// for addition, there's a possibility that the leftmost digit would
		// result in an extra digit.
		// therefore maxLength of the result should be the maximum of the two +
		// 1
		int maxLength = Math.max(num1.length, num2.length) + 1;
		short[] result = new short[maxLength];
		int carry = 0;

		for (int i = 0; i < result.length; i++) {
			result[i] = (short) ((num1[i] + num2[i] + carry) / base);
			carry = (short) ((num1[i] + num2[i] + carry) % base);
			if (i == (result.length - 1) && carry == 1) { // if the leftmost
															// digit has carry
															// after addition,
															// give it to the
															// one more digit of
															// the result on the
															// left.
				result[i + 1] = 1;
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
			if(num1[i] < 0){
				num1[i] = num1[i] + 10
			}
			if(num1[i] < num2[i]){
				borrow = 1;
				num1[i] += 10;
				num1[i+1] -= 1;
			}
		}
		return result;
	}

	public static short[] multiply(short[] num1, short[] num2, short base) {
		// maximum length for multiplication is the number of digits multiplied.
		int maxLength = (num1.length * num2.length);
		short result[] = new short[maxLength];

		return result;

	}

	public static short[] divide(short[] num1, short[] num2, short base, short R) {
		// maximum length for division is the length of the number being divided
		// which, in this case, is num1.
		int maxLength = num1.length;
		short result[] = new short[maxLength];

		return result;
	}

	public static void main(String[] args) {
		class Number {
			short base;
			short[] Int, NonRep, Rep;

			// =+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
			// your method for converting belongs here...
			// =+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
			public Number convert(Number A, short base) {
				Number B = new Number();
				B.base = base;
				B.Int = A.NonRep;
				B.NonRep = A.Int;
				B.Rep = A.NonRep;
				// my code above is just to make sure it compiles and runs

				return B;
			}

			// =+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

			public void printShortArray(short[] S) {
				for (int i = S.length - 1; i >= 0; i--) {
					System.out.print(S[i]);
				}
			}

			public void printNumber(Number N) {
				System.out.print("(");
				N.printShortArray(N.Int);
				System.out.print(".");
				N.printShortArray(N.NonRep);
				System.out.print("{");
				N.printShortArray(N.Rep);
				System.out.print("})_");
				System.out.println(N.base);
			}
		}
		;
		Number N1 = new Number();
		N1.base = 10;
		N1.Int = new short[2];
		N1.NonRep = new short[3];
		N1.Int[1] = 1;
		N1.Int[0] = 9;
		N1.NonRep[2] = 2;
		N1.NonRep[1] = 4;
		N1.NonRep[0] = 7;
		N1.Rep = new short[0];
		N1.printNumber(N1);

		Number N2 = new Number();
		short R = 2;
		N2 = N1.convert(N1, R);
		N2.printNumber(N2);
	}
}