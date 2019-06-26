package Sorting_Algorithms;

public class BubbleSort {
    public int[] bubbleSort(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
        return arr;
    }

    public void veryLongComputation() throws InterruptedException {
        Thread.sleep(5000);
    }

    public double timeOfSorting(int[] arr){
        long tStart = System.currentTimeMillis();
        BubbleSort b = new BubbleSort();
        b.bubbleSort(arr);
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        return elapsedSeconds;
    }
}
