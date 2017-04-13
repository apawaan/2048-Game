import java.util.Scanner;
/*
 * Name: Angelynne Pawaan
 * Login: cs8bwadr
 * Date:  March 6th, 2017
 * File:  ReverseRecurse.java
 * Sources of Help: Textbook, tutors, piazza, discussion
 * 
 * ReverseRecurse.java reverses
 * an array that is given by the user
 * It contains initArray, which reads the values by the user,
 * sets them up in an array, then consists
 * of two reverse recursive methods that either
 * directly manipulates the given array
 * or creates a copy of that array
 * and returns its reverse.
 * This program also consists of 
 * the printArray method which prints all elements in the array 
 */
public class ReverseRecurse {
	
	// initArray takes values given by the user and stores them into
	// an array and returns such. 
	public int[] initArray() 
	{
		int size = 0;
		Scanner input = new Scanner(System.in);
		// asking user for size of array
		System.out.println(PSA7Strings.MAX_NUM);

		// if size hasn't been set, and the user quits
		// or puts a noninteger, exit
		while (size == 0)
		{
		 if (input.hasNext() == false || input.hasNextInt() == false)
		 {
		  System.exit(1);
		 }

		 int givenInt = input.nextInt();

		 // check if user puts a positive number for size
		 if (givenInt > 0)
		 {
		  size = givenInt;
		 }

		 if (givenInt <= 0)
		 {
		  System.out.println(PSA7Strings.TOO_SMALL);	  
		 }
		}
		
		// after size has been set
		int[] newArray = new int[size];	
		// asking user for n number of sizes
		System.out.printf(PSA7Strings.ENTER_INTS, size);
		int index = 0;
		// only take size amount of integers for array
		while (index < size)		
		{
	          // if user puts a noninteger number or exits,
		  // save current values to a new sized array
		  // and return that array
	       	  if (input.hasNext() == false || input.hasNextInt() == false)
		  {
		   int[] copyArray = new int[index];			  
		   System.arraycopy(newArray, 0, copyArray, 0, index);
		   return copyArray;		   
		  }
		
	       	  int givenEle = input.nextInt();
		  newArray[index] = givenEle;
		  index++;
		}		
		return newArray;
	}

	// print Array prints every element in given array
       public void printArray(int[] array) 
       {
	        // if given array is null, print empty
		if (array == null)
		{
		  System.out.println(PSA7Strings.EMPTY);
		}
		
		else	
		{
		 for (int index = 0; index < array.length; index++)
		 {		
		  System.out.print(array[index]+" ");
		 }
		}
       }

       // first reverse method takes in an int array, and two low and high
       // integers that are indices for which values to switch
       // in given array. Then recursively calls this method
       // to do the same as low and high get closer
       // to eachother 
       public void reverse(int[] originalArray, int low, int high)
       {
	        // if given array is empty, return
	        if (originalArray == null) return;			
	        int temp = 0;
		int temp2 = 0;
		// base case, if low > high, stop
		if (low < high)
		{
	          temp = originalArray[low];
		  temp2 = originalArray[high];
		  originalArray[low] = temp2;
		  originalArray[high] = temp;
		  // recursive call with higher low value
		  // and lower high value
		  reverse(originalArray, low + 1, high - 1);
		}
       }
	
       // this reverse method takes in an int array and reverses it
       // recursively but switching the first and last elements
       // of the given array. It then makes a new array
       // out of the middle elements and recursively calls that

       public int[] reverse(int[] originalArray)
       {
	      if (originalArray == null) 
	      {
	        return originalArray;
	      }		
	       
	      // switching first and last elements in last array
	      int[] newArray = new int[originalArray.length];
	      int firstEle = originalArray[0];
	      int lastEle = originalArray[originalArray.length - 1];
	      newArray[0] = lastEle;
	      newArray[newArray.length - 1] = firstEle;

	      int[] midArray = new int[originalArray.length - 2];
	      System.arraycopy(originalArray, 1, midArray, 0, originalArray.length - 2);

	      // if middle elements have a value of more than one,
	      // recursively call 
	      if (midArray.length > 1)
	      {
	        midArray = reverse(midArray);
	      }

	      // if recursive call can't apply, add middle array to new array & return
	      if (midArray.length <= 1)
	      {
	       for (int index = 0, index2 = 0; index < newArray.length; index++)
	       {
	        if (newArray[index] == 0)
	        {
		 newArray[index] = midArray[index2];
		 index2++;
	        }
	       }

	      return newArray;
	      }
	      
	      // add middle array to new array, return new Array
	      System.arraycopy(midArray, 0, newArray, 1, newArray.length - 2);

	      return newArray;
       }
}
