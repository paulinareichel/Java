package Sorting_Algorithms;

public class GFG {
    public static int[] cycleSort(int arr[], int n) {

        int writes = 0;
        for (int cycle_start = 0; cycle_start <= n - 2; cycle_start++) {

            int item = arr[cycle_start];

            int pos = cycle_start;
            for (int i = cycle_start + 1; i < n; i++)
                if (arr[i] < item)
                    pos++;

            if (pos == cycle_start)
                continue;

            while (item == arr[pos])
                pos += 1;

            if (pos != cycle_start) {
                int temp = item;
                item = arr[pos];
                arr[pos] = temp;
                writes++;
            }

            while (pos != cycle_start) {
                pos = cycle_start;

                for (int i = cycle_start + 1; i < n; i++)
                    if (arr[i] < item)
                        pos += 1;

                while (item == arr[pos])
                    pos += 1;

                if (item != arr[pos]) {
                    int temp = item;
                    item = arr[pos];
                    arr[pos] = temp;
                    writes++;
                }
            }
        }
        return arr;
    }
    public void veryLongComputation() throws InterruptedException {
        Thread.sleep(5000);
    }

    public double timeOfSorting(int[] arr){
        long tStart = System.currentTimeMillis();
        GFG b = new GFG();
        b.cycleSort(arr, arr.length);
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        return elapsedSeconds;
    }
}