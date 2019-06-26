package Tests;

import Sorting_Algorithms.GFG;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class GFGTest {
    GFG b;
    int[] arr;
    Random generator;


    @Before
    public void setUp() {
        b = new GFG();
        int[] arr = new int[7];
        generator = new Random();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void shouldMyClassBeInitialized() {
        assertNotNull(b);
    }

    @Test(expected = NullPointerException.class)
    public void whenExceptionThrown_thenExpectationSatisfied() {
        Assert.assertNull("Null exception", b.cycleSort(arr, arr.length));    }

    @Test
    public void checkOutput() {
        int[] arr = {1, 7, 2, 1, 11, 11, 0};
        int[] res = {0, 1, 1, 2, 7, 11, 11};
        assertArrayEquals(b.cycleSort(arr, arr.length), res);
    }

    @Test
    public void checkWrongOutput() {
        int[] arr = {1, 7, 0, 1, 11, 11, 2};
        int[] res = {0, 1, 2, 7, 11, 11};
        assertNotEquals(b.cycleSort(arr, arr.length), res);
    }

    @Test
    public void checkOutputSingle() {
        int[] brr = {1};
        int[] res = {1};
        assertArrayEquals(b.cycleSort(brr, brr.length), res);
    }

    @Test(timeout = 6000)
    public void shouldNotRumLongerThanTimeout() throws Exception {
        b.veryLongComputation();
    }

    @Test
    public void compareOptymisticTimes() {
        int tmp[] = new int[1550];
        for (int i = 0; i < tmp.length; i++) tmp[i] = generator.nextInt();
        b.cycleSort(tmp, tmp.length);
        assertThat("Time is above 0.001s ", b.timeOfSorting(tmp), is(not(b.timeOfSorting(tmp)<0.0001)));
    }
    @Test
    public void comparePesymisticTimes() {
        int tmp[] = new int[1550];
        for (int i = 0; i < tmp.length; i++) tmp[i] = generator.nextInt();
        b.cycleSort(tmp, tmp.length);
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

