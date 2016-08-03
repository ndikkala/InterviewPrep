package algocode;

import java.io.*;

public class PermutationsRecursive {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// Give input without spaces
		String input = in.readLine();
		PermutationsRecursive per = new PermutationsRecursive(input);
		per.permute();
	}

	private boolean[] used;
	private StringBuilder out = new StringBuilder();
	private final String in;

	public PermutationsRecursive(final String str) {
		in = str;
		used = new boolean[in.length()];
	}

	public void permute() {
		if (out.length() == in.length()) {
			System.out.println("out len "+out.length() + " in len "+in.length());
			System.out.println(out);
			return;
		}
		for (int i = 0; i < in.length(); ++i) {
			System.out.println("what is used of "+i+ " : "+ used[i]);
			if (used[i])
				continue;
			System.out.println("appending "+in.charAt(i) + " to out");
			out.append(in.charAt(i));
			System.out.println("setting used of "+i+ " to true ");
			used[i] = true;
			System.out.println("recursive permute call ");
			permute();
			System.out.println("setting used of "+i+ " to false ");
			used[i] = false;
			System.out.println("reducing out size from "+out.length());
			out.setLength(out.length() - 1);
			System.out.println("to "+out.length());
		}
	}
}
