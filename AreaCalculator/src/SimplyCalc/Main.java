package Lab1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        boolean running = true;
        Triangle t = new Triangle();
        Square s = new Square();
        Circle c = new Circle();

        while (running) {
            System.out.println("1.Select a figure. " + "\n2.Show informations " + "\n3.Exit " + "\nYour choice: ");
            int choose = scan.nextInt();
            switch (choose) {
                case 1:
                    System.out.println("Select a figure: " + "\n1. Triangle " + "\n2. Square " + "\n3. Circle" + "\nYour choice: ");
                    int figure = scan.nextInt();
                    switch (figure) {
                        case 1:
                            System.out.println("Triangle");
                            System.out.println("Height: ");
                            String height = scan.next();
                            double checked_height = check(height);
                            System.out.println("Base length: ");
                            String basis_length = scan.next();
                            double checked_basis_length = check(basis_length);
                            System.out.println("First side length: ");
                            String fside_length = scan.next();
                            double checked_fside_length = check(fside_length);
                            System.out.println("Second side length: ");
                            String sside_length = scan.next();
                            double checked_sside_length = check(sside_length);
                            boolean checkTriangle = isTriangle(checked_fside_length, checked_sside_length, checked_basis_length);
                            if (checkTriangle == false){
                                System.out.println("Can not create a triangle");
                                break;}
                            t = new Triangle(checked_height, checked_basis_length, checked_fside_length, checked_sside_length);
                            break;
                        case 2:
                            System.out.println("Square");
                            System.out.println("Side length: ");
                            String side_length = scan.next();
                            double checked_side_length = check(side_length);
                            s = new Square(checked_side_length);
                            break;
                        case 3:
                            System.out.println("Circle");
                            System.out.println("Radius length: ");
                            String radius = scan.next();
                            double checked_radius = check(radius);

                            c = new Circle(checked_radius);
                            break;

                        default:
                            System.out.println("default");
                    }

                    break;

                case 2:
                    t.print();
                    s.print();
                    c.print();
                    break;

                case 3:

                    running = false;
                    break;
                default:
                    System.out.println("default");
            }

        }

    }

    public static double check(String a) {
        double d;
        try {
            d = Double.parseDouble(a);
            if (d <= 0)
                throw new IllegalArgumentException("Wrong value");
            else {
            }
        } catch (NumberFormatException e) {
            throw new InputMismatchException(a + " is not a valid integer number");
        }
        return d;
    }

    public static boolean isTriangle(double a, double b, double c) {
        boolean check = true;
        if (a + b < c) check = false;
        return check;
    }

}

