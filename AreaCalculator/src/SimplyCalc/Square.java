package Lab1;

public class Square extends Figure implements Print{

    public double side_length;

    public Square (){
        side_length = 0;
    }

    public Square (double side_length){
        this.side_length = side_length;
    }

    @Override
    public double calculateArea(){
        return this.side_length*this.side_length;
    }

    @Override
    public double calculatePerimeter(){
        return 4*this.side_length;
    }

    @Override
    public void print(){
        System.out.println("\n Square: " +
                "\nArea:" + calculateArea() +
                "\nPerimeter:" + calculatePerimeter());
    }
}


