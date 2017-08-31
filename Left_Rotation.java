import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static int[] arrayLeftRotation(int[] a, int n, int k) {      
        int[] arr = new int[n];

        // using for loops solution

        // copy the end of the array
        int i, j;
        for (i = 0, j = n-k; j < n; ++i, ++j){
            arr[j] = a[i];
        }

        // copy the beginning of the array
        for (j = 0; j < n - k; ++j, ++i ){
            arr[j] = a[i];
        }

        return arr;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
      
        int[] output = new int[n];
        output = arrayLeftRotation(a, n, k);
        for(int i = 0; i < n; i++)
            System.out.print(output[i] + " ");
      
        System.out.println();
      
    }
}
