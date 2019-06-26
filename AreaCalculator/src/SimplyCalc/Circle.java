package Lab1;

public class Circle extends Figure implements Print{

    public double radius;

    public Circle (){
        radius = 0;
    }

    public Circle (double radius){
        this.radius = radius;
    }

    @Override
    public double calculateArea(){
        return this.radius*this.radius*Math.PI;
    }
    public double calculatePerimeter(){
        return 2*Math.PI*this.radius;
    }
    public void print(){
        System.out.println("\n Circle" +
                "\nArea:" + calculateArea() +
                "\nPerimeter:" + calculatePerimeter());
    }
}

