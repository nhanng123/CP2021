import java.io.*;
import java.util.*;

public class StaticRangeSum {

	public static void main(String[] args) throws Exception {
		int n = ni(), q = ni();
		long[] accumulateSum = new long[n + 1];
		for (int i = 1; i <= n; i++) {
			accumulateSum[i] = accumulateSum[i - 1] + ni();
		}
		StringBuilder outBuffer = new StringBuilder();
		for (int i = 0; i < q; i++) {
			outBuffer.append((-accumulateSum[ni() - 1] + accumulateSum[ni()]) + "\n");
		}
		System.out.println(outBuffer);
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