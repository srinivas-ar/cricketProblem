package com.simpl.assignment;

public class Random {


    /*
     0 dot ball
     1 single
     4 is four
     6 is six
     -1 is out
     */
    private static int[] runs={ 0,1, 2, 3, 4,5,6,-1 };

    // Utility function to find ceiling of r in arr[l..h]
    static int findCeil(int arr[], int r, int l, int h) {
        int mid;
        while (l < h) {
            mid = l + ((h - l) >> 1); // Same as mid = (l+h)/2
            if (r > arr[mid])
                l = mid + 1;
            else
                h = mid;
        }
        return (arr[l] >= r) ? l : -1;
    }

    // The main function that returns a random number
    // from arr[] according to distribution array
    // defined by freq[]. n is size of arrays.
    static int getRandomNumber(int arr[], int freq[], int n) {
        // Create and fill prefix array

        //    p1.frequency=intArrayOf(5, 30, 25, 10, 15, 1, 9, 5)
        //
        //        5,35,60,70,85,86,95,100
        //
        //
        int prefix[] = new int[n], i;
        prefix[0] = freq[0];
        for (i = 1; i < n; ++i)
            prefix[i] = prefix[i - 1] + freq[i];

        // prefix[n-1] is sum of all frequencies.
        // Generate a random number with
        // value from 1 to this sum

        int r = ((int) (Math.random() * (323567)) % prefix[n - 1]) + 1;

        // Find index of ceiling of r in prefix array
        int indexc = findCeil(prefix, r, 0, n - 1);
        return arr[indexc];
    }


    static int getRandomScore(int freq[]){
        return getRandomNumber(runs,freq,runs.length);
    }
}
