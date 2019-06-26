package Matrix;

import java.util.ArrayList;
import java.util.Iterator;

public class Matrix<T> {
    int row;
    int column;
    T value;
    public ArrayList<T> matrix = new ArrayList<T>();
    String message = "Matrixs dimensions are not equal";

    public Matrix(){}
    public Matrix(int row, int column) {
        this.row = row;
        this.column = column;
        this.matrix = matrix;
        this.value = value;
    }

    public ArrayList<T> createMatrix(Matrix m, T value, T second_value) {
        for (int i = 0; i < m.row * m.column; i++) {
            if (i % 2 == 0)
                matrix.add(i, value);
            else matrix.add(i, second_value);
        }

        return matrix;
    }


    public ArrayList<T> addMatrix(Matrix first_matrix, Matrix second_matrix) throws MyException {
        ArrayList<T> new_matrix = new ArrayList<T>();
        try {
            if (first_matrix.row != second_matrix.row || first_matrix.column != second_matrix.column)
                throw new MyException();
        } catch (MyException e) {
            System.out.println(e);
        }
        if (!first_matrix.matrix.isEmpty() & !second_matrix.matrix.isEmpty()) {
            for (int i = 0; i < first_matrix.matrix.size(); i++) {
                if (first_matrix.matrix.get(i) instanceof Integer)
                    new_matrix.add((T) (Integer) ((Integer) first_matrix.matrix.get(i) + (Integer) second_matrix.matrix.get(i)));
                else
                    new_matrix.add((T) (String) ((String) first_matrix.matrix.get(i) + (String) second_matrix.matrix.get(i)));
            }
        } else throw new NullPointerException();

        return new_matrix;
    }

    public void print(ArrayList<T> m, int column) {
        System.out.println("");
        int j = 0;
        Iterator<T> itr = m.iterator();

        while (itr.hasNext()) {
            T element = itr.next();
            if (j == column) {
                System.out.println();
                j = 0;
            }
            System.out.print(element + " ");
            j++;
        }
        System.out.println("");

    }

    public void veryLongComputation() throws InterruptedException {
        Thread.sleep(5000);
    }
}

