package algocode;

import java.util.*;

public class MergeSortCode
{
	public static void main(String[] args)
	{
		Integer[] a = {23, 45, 25, 90, 10, 345, 100, 234, 1000, 67};
		Comparable<Integer>[] temporary = mergeSort(a);
		System.out.println(Arrays.toString(temporary));
	}

	public static Comparable<Integer>[] mergeSort(Comparable<Integer>[] a)
	{
		@SuppressWarnings("unchecked")
		Comparable<Integer>[] tmp = new Comparable[a.length];
		mergeSort(a, tmp,  0,  a.length - 1);
		return tmp;
	}


	private static void mergeSort(Comparable<Integer> [ ] a, Comparable<Integer> [ ] tmp, int left, int right)
	{
		if( left < right )
		{
			//System.out.println("Step 1: left "+left+ " right " + right );
			int center = (left + right) / 2;
			//System.out.println("Step 2: left "+left+ " right " + right + " center " + center );
			mergeSort(a, tmp, left, center);
			mergeSort(a, tmp, center + 1, right);
			//System.out.println("Step 3: Merge is abt to be called" );
			merge(a, tmp, left, center + 1, right);
		}
	}


    private static void merge(Comparable<Integer>[ ] a, Comparable<Integer>[ ] tmp, int left, int right, int rightEnd )
    {
        int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;

        while(left <= leftEnd && right <= rightEnd)
            if(a[left].compareTo((Integer) a[right]) <= 0)
                tmp[k++] = a[left++];
            else
                tmp[k++] = a[right++];

        while(left <= leftEnd)    // Copy rest of first half
            tmp[k++] = a[left++];

        while(right <= rightEnd)  // Copy rest of right half
            tmp[k++] = a[right++];

        // Copy tmp back
        for(int i = 0; i < num; i++, rightEnd--)
            a[rightEnd] = tmp[rightEnd];
        //System.out.println("Inside Merge" + Arrays.toString(a));
    }
 }