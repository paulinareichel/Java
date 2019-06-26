package Sorting_Algorithms;

public class CombSort {


    int getNextGap(int gap) {
        gap = (gap * 10) / 13;
        if (gap < 1)
            return 1;
        return gap;
    }


    public int[] sort(int arr[]) {
        int n = arr.length;
        int gap = n;

        boolean swapped = true;

        while (gap != 1 || swapped == true) {

            gap = getNextGap(gap);

            swapped = false;

            for (int i = 0; i < n - gap; i++) {
                if (arr[i] > arr[i + gap]) {

                    int temp = arr[i];
                    arr[i] = arr[i + gap];
                    arr[i + gap] = temp;

                    swapped = true;
                }

            }
        } return arr;
    }
    public void veryLongComputation() throws InterruptedException {
        Thread.sleep(5000);
    }

    public double timeOfSorting(int[] arr){
        long tStart = System.currentTimeMillis();
        CombSort b = new CombSort();
        b.sort(arr);
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        return elapsedSeconds;
    }
}

