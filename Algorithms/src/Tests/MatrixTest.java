package Tests;

import Matrix.Matrix;
import Matrix.MyException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MatrixTest {
    ArrayList<String> smatrix;
    ArrayList<Integer> imatrix;
    Matrix<String> sm;
    Matrix<String> sn;

    Matrix<Integer> im;
    Matrix<Integer> in;


    @Before
    public void setUp() {
        smatrix = new ArrayList<String>();
        imatrix = new ArrayList<Integer>();
        sn = new Matrix<String>();
        sm = new Matrix<String>();
        im = new Matrix<Integer>();
        in = new Matrix<Integer>();
    }
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldMyClassBeInitialized() {
        assertNotNull(sn);
        assertNotNull(sm);
        assertNotNull(in);
        assertNotNull(im);
        assertNotNull(smatrix);
        assertNotNull(imatrix);
    }

    @Test(timeout = 80000)
    public void shouldNotRumLongerThanTimeout() throws Exception {
        sn.veryLongComputation();
        sm.veryLongComputation();
        in.veryLongComputation();
        im.veryLongComputation();
    }

    @Test(expected = NullPointerException.class)
    public void whenExceptionThrown_thenExpectationSatisfied() {
        Assert.assertNull("Null exception", im.createMatrix(null, 0, 0));    }
    @Test(expected = NullPointerException.class)
    public void whenSecondExceptionThrown_thenExpectationSatisfied() {
        Assert.assertNull("Null exception", in.createMatrix(null,null,null)); }
 @Test
    public void createNewStringMatrix(){
        sm = new Matrix<String>(2,2);
        sm.createMatrix(sm,"a","b");
 }
    @Test
    public void createNewIntegerMatrix(){
        im = new Matrix<Integer>(2,2);
        im.createMatrix(sm,6,15);
    }
    @Test
    public void addStringMatrices() throws MyException
     {
        sm = new Matrix<String>(2,2);
        sm.createMatrix(sm,"a","b");
        sn = new Matrix<String>(2,2);
        sn.createMatrix(sn,"c","d");
        smatrix = sm.addMatrix(sm,sn);
    }

    @Test
    public void addIntegerMatrices() throws MyException {
        im = new Matrix<Integer>(2,2);
        im.createMatrix(im,89,12);
        in = new Matrix<Integer>(2,2);
        in.createMatrix(in,2,6);
        imatrix = im.addMatrix(im,in);
    }
    @Test
    public void testingPrintFunction() throws MyException {
        im = new Matrix<Integer>(2,2);
        im.createMatrix(im,2,6);

        imatrix = im.addMatrix(im,im);
        im.print(imatrix,2);

    }
    @Test
    public void failIfDifferentSizes() throws MyException {
        im = new Matrix<Integer>(2,2);
        im.createMatrix(im,89,12);
        in = new Matrix<Integer>(4,2);
        in.createMatrix(in,2,6);
        imatrix = im.addMatrix(im,in);
    }
    @Test (expected = NullPointerException.class)
    public void oneEmptyMatrix()throws MyException {
        sm = new Matrix<String>(2,2);
        sn = new Matrix<String>(2,2);
        sm.createMatrix(sm,"a","b");
        smatrix = sm.addMatrix(sm,sn);    }
}