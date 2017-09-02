import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static int lonelyInteger(int[] a) {
        int[] count = new int[101];

        for (int i : a)
            ++count[i];

        for (int i = 0; i < 101; ++i) {
            if (count[i] == 1) return i;
        }
        return a[0];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[] = new int[n];
        for (int a_i = 0; a_i < n; a_i++) {
            a[a_i] = in.nextInt();
        }
        System.out.println(lonelyInteger(a));
    }
}
