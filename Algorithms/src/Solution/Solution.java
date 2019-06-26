package Solution;

import java.util.Collections;
import java.util.List;


public class Solution {

    public int solution(List<Integer> list) throws MyException {
        int number_solution = 0;
        boolean success = false;
        int counter = 0;
        Collections.sort(list);
        int temp = list.get(0);

                if (list.size() > 1E5) throw new MyException();
                for (Integer i : list)
                    if ((i >= 1000) || (i <= -1000)) throw  new IllegalArgumentException();

        while (!success) {
            for (Integer i : list) {
                if (list.get(list.size() - 1) <= 0 || list.get(0) > 1) {
                    temp = 1;
                    success = true;
                }
                if (i != temp && temp > 0) {
                    number_solution = temp;
                    success = true;
                } else {
                    if (temp <= 0) temp = i;
                    temp++;
                    counter++;
                    if ((counter) == (list.size())) {
                        number_solution = temp;
                        success = true;
                    }
                }
            }
        }
        return number_solution;
    }

    public void veryLongComputation() throws InterruptedException {
        Thread.sleep(5000);
    }
}
