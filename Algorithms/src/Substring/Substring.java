package Substring;

import static java.lang.StrictMath.abs;

public class Substring {
    public int substring(String a, String b) {
        String temp = "";
        int counter = 0;
        boolean contains = false;

        while (counter != -1) {
            if (!b.contains(a) || abs(a.length() - b.length()) < counter) counter = -1;
            else {
                if (contains == true) {
                    return  counter;
                } else {
                    counter ++;
                    temp += a;
                    contains = temp.contains(b);
                }
            }
        }
        return counter;
    }
    public void veryLongComputation() throws InterruptedException {
        Thread.sleep(5000);
    }
}
