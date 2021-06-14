import java.io.*;
import java.util.*;

public class DynamicRangeMinimum2 {

	public static void main(String[] args) throws Exception {
		int n = ni(), q = ni();
		length = n;
		treeMin = new int[2 * length];
		Arrays.fill(treeMin, Integer.MAX_VALUE);

		int[] numbers = new int[n];
		for (int i = 0; i < n; i++) {
			int value = ni();
			numbers[i] = value;
			set(i, value);
		}

		StringBuilder outBuffer = new StringBuilder();
		for (int i = 0; i < q; i++) {
			switch (ni()) {
			case 2:
				outBuffer.append((get(ni() - 1, ni())) + "\n");
				break;
			case 1:
				set(ni() - 1, ni());
				break;
			}
		}

		System.out.println(outBuffer);
	}

	static int length;
	static int[] treeMin;

	static void set(int i, int x) {
		i += length;
		treeMin[i] = x;
		for (i = i >> 1; i > 0; i >>= 1) {
			treeMin[i] = Math.min(treeMin[i << 1], treeMin[(i << 1) + 1]);
		}
	}

	// l <= i < r
	static int get(int l, int r) {
		int result = Integer.MAX_VALUE;
		for (l += length, r += length; l < r; l >>= 1, r >>= 1) {
			if ((l & 1) != 0) {
				result = Math.min(result, treeMin[l]);
				l++;
			}
			if ((r & 1) != 0) {
				r--;
				result = Math.min(result, treeMin[r]);
			}
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