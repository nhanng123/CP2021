import java.io.*;
import java.util.*;

public class DynamicRangeSum {

	public static void main(String[] args) throws Exception {
		length = ni();
		treeSum = new long[length + 1];
		int q = ni();

		int[] numbers = new int[length + 1];
		for (int i = 1; i <= length; i++) {
			numbers[i] = ni();
			add(i, numbers[i]);
		}

		StringBuilder outBuffer = new StringBuilder();
		for (int i = 0; i < q; i++) {
			switch (ni()) {
			case 1:
				int index = ni();
				int newValue = ni();
				add(index, newValue - numbers[index]);
				numbers[index] = newValue;
				break;
			case 2:
				outBuffer.append((-get(ni() - 1) + get(ni())) + "\n");
				break;
			}

		}
		System.out.println(outBuffer);
	}

	static int length;
	static long[] treeSum;

	static void add(int i, int x) {
		for (; i <= length; i += (i & -i)) {
			treeSum[i] += x;
		}
	}

	static long get(int i) {
		long result = 0;
		for (; i > 0; i -= (i & -i)) {
			result += treeSum[i];
		}
		return result;
	}

	/*
	 * **********************BASIC READER ******************************************
	 * *****************************************************************************
	 *****************************************************************************/

	static InputStream is = System.in;;
	static byte[] inbuf = new byte[1024 << 4];
	static int lenbuf = 0, ptrbuf = 0;

	static int readByte() {
		if (lenbuf == -1)
			throw new InputMismatchException();
		if (ptrbuf >= lenbuf) {
			ptrbuf = 0;
			try {
				lenbuf = is.read(inbuf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (lenbuf <= 0)
				return -1;
		}
		return inbuf[ptrbuf++];
	}

	static boolean isSpaceChar(int c) {
		return !(c >= 33 && c <= 126);
	}

	static int skip() {
		int b;
		while ((b = readByte()) != -1 && isSpaceChar(b))
			;
		return b;
	}

	static int ns() {
		int b = skip();
		int result = 0;
		while (!(isSpaceChar(b))) {
			result |= (1 << (b - 'a'));
			b = readByte();
		}
		return result;
	}

	static int ni() {
		int num = 0, b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}
}