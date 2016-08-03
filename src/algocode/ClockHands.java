package algocode;

import java.io.*;

public class ClockHands {

	static int[] output;

	static double[] overlap = { 0, 105.27, 210.55, 316.22, 421.49, 527.16, 632.44, 738.11, 843.38, 949.05,
			1054.33, 1200, 1305.27, 1410.55, 1516.22, 1621.49, 1727.16, 1832.44, 1938.11, 2043.38, 2149.05,
			2254.33, 2400 };

	int startNumber;

	int endNumber;

	public static void main(String[] args) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			int t = Integer.parseInt(in.readLine());
			output = new int[t];
			int i = 0;
			in.readLine();
			while (t > 0) {
				String start = in.readLine();
				String end = in.readLine();
				if (t > 1) {
				in.readLine();
				}
				ClockHands ch = new ClockHands();
				ch.initialize(start, end);
				output[i] = ch.spyHands();
				i++;
				t--;
			}

			for (int o : output) {
				System.out.println(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void initialize(String strt, String end) {
		String[] split = strt.split(":");
		startNumber = Integer.parseInt(split[0] + split[1]);
		String[] endTime = end.split(":");
		endNumber = Integer.parseInt(endTime[0] + endTime[1]);
	}

	int spyHands() {
		int count = 0;
		for (int i = 0; i < overlap.length; i++) {
			if (overlap[i] > startNumber && overlap[i] <= endNumber) {
				count++;
			}
		}
		return count;
	}
}


/*
 * 
8

00:00
03:00

00:00
00:10

06:00
12:00

11:59
12:01

00:00
12:00

06:27
06:31

18:27
18:32

00:00
23:59
*/
