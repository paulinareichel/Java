package Lab1;


public class Triangle extends Figure implements Print{

    public double basis_length;
    public double fside_length;
    public double sside_length;
    public double height;

    public Triangle (){
        fside_length = 0;
        sside_length = 0;
        basis_length = 0;
        height = 0;
    }


    public Triangle (double height, double basis_length, double fside_length, double sside_length){
        this.fside_length = fside_length;
        this.sside_length = sside_length;
        this.basis_length = basis_length;
        this.height = height;
    }

    @Override
    public double calculateArea(){
        return this.basis_length*this.height/2;
    }

    @Override
    public double calculatePerimeter(){
        return this.basis_length + this.sside_length + this.fside_length;
    }

    @Override
    public void print(){
        System.out.println("\nTriangle " +
                "\nArea:" + calculateArea() +
                "\nPerimeter:" + calculatePerimeter());
    }
}
