package Tests;

import Another_solution.Solution;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertNotNull;


public class AnotherSolutionTest {

    Solution sol;


    @Before
    public void setUp() throws Exception {
        sol = new Solution();

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldMyClassBeInitialized() {
        assertNotNull(sol);
    }

    @Test
    public void checkOutput() {
        float[] arr = {2, 5, 8, 7};
        int[] res = new int[2];
        res[0] = 0; res[1] = 3;
        sol.solution(arr,9);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenNegative() {
        float[] arr = {2, 5, 8, 2};
        sol.solution(arr,1);    }


    @Test(timeout = 7000)
    public void shouldNotRumLongerThanTimeout() throws Exception {
        sol.veryLongComputation();
    }

    @Test(expected = NullPointerException.class)
    public void whenExceptionThrown_thenExpectationSatisfied() {
        Assert.assertNull("Null exception", sol.solution(null, 3));    }

}