package Another_solution;

import Substring.Substring;

public class Solution {
    public int[] solution(float[] arr, float target) {
        int[] res = new int[2];
        res[0]=-1;


        for (int j = 0; j < arr.length; j++)
            for (int i = 1;i < arr.length ;){
                if (arr[i] + arr[j] == target) {
                    res[0] = j;
                    res[1] = i;
                    return res;
                } else i++;}
                if (res[0]==-1) throw new IllegalArgumentException("No solution");
        return res;
    }
    public void veryLongComputation() throws InterruptedException {
        Thread.sleep(5000);
    }
    public static void main(String[] args){
        Solution s =new Solution();
        float[] arr = {2, 5, 8, 7};
        int[] res = new int[2];

        res = s.solution(arr,9);
        System.out.println(res[0] + "  " +res[1]);

    }
}
