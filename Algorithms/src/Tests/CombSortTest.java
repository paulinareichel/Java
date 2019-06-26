package Tests;

import Sorting_Algorithms.CombSort;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class CombSortTest {
    CombSort b;
    int[] arr;
    Random generator;

    @Before
    public void setUp() {
        b = new CombSort();
        int[] arr = new int[7];
        generator = new Random();
    }

    @After
    public void tearDown() {    }

    @Test
    public void shouldMyClassBeInitialized() {
        assertNotNull(b);
    }

    @Test(expected = NullPointerException.class)
    public void whenExceptionThrown_thenExpectationSatisfied() {
        Assert.assertNull("Null exception", b.sort(arr));    }


    @Test
    public void checkOutput() {
        int [] arr= {1,7,0,1,11,11,2};
        int []res = {0,1,1,2,7,11,11};
        assertArrayEquals(b.sort(arr), res);
    }

    @Test
    public void checkWrongOutput() {
        int [] arr= {1,7,0,1,11,11,2};
        int []res = {0,1,2,7,11,11};
        assertNotEquals(b.sort(arr), res);
    }
    @Test
    public void checkOutputSingle() {
        int [] brr= {1};
        int []res = {1};
        assertArrayEquals(b.sort(brr), res);
    }
    @Test(timeout = 7000)
    public void shouldNotRumLongerThanTimeout() throws Exception {
        b.veryLongComputation();
    }
    @Test
    public void compareOptymisticTimes() {
        int tmp[] = new int[1550];
        for (int i = 0; i < tmp.length; i++) tmp[i] = generator.nextInt();
        b.sort(tmp);
        assertThat("Time is above 0.001s ", b.timeOfSorting(tmp), is(not(b.timeOfSorting(tmp)<0.0001)));
    }

    @Test
    public void comparePesymisticTimes() {
        int tmp[] = new int[1550];
        for (int i = 0; i < tmp.length; i++) tmp[i] = generator.nextInt();
        b.sort(tmp);
        int tab[] = new int[tmp.length];
        int start = 0;
        int end = tmp.length - 1;
        while (start < end) {
            int help = tmp[start];
            tab[start] = tmp[end];
            tab[end] = help;
            start++;
            end--;
        }
        assertThat("Time is lower than 0.0001s ", b.timeOfSorting(tab), is(not(b.timeOfSorting(tab)>0.0001)));
    }

}