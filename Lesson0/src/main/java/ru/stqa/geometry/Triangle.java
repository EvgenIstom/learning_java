package ru.stqa.geometry;

public class Triangle {
    double side1;
    double side2;
    double side3;

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public double perimeter() {
        return this.side1 + this.side2 + this.side3;
    }

    public double area() {
        double halfPerimeter = this.perimeter()/2;
        return Math.sqrt(halfPerimeter * (halfPerimeter - this.side1) * (halfPerimeter - this.side2) * (halfPerimeter - this.side3));

    }

    public boolean isValidTriangle(){
        return (this.side1 + this.side2 > this.side3) &&
                (this.side1 + this.side3 > this.side2) &&
                (this.side2 + this.side3 > this.side1);
    }

    public static void main(String[] args) {
        System.out.println("Периметр = " + new Triangle(3, 4, 6).perimeter());
        System.out.println("Площадь = " + new Triangle(3, 4, 6).area());
    }

}
