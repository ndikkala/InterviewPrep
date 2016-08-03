package algocode;

public class Modulus {

	private static int mod(int x)
	{
		
	   int result = x % 3;
	   if (result < 0)
	   {
	       result += 3;
	   }
	   return result;
	}
	
	public static void main(String[] args){
		System.out.println(7%3);
		System.out.println(-7%3);
		System.out.println("mod of 7 " + mod(7));
		System.out.println("mod of -7 " + mod(-7));
	}
}
