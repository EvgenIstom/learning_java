package ru.stqa.geometry;

public class Triangle {
    double side1;
    double side2;
    double side3;

    public Triangle(double side1, double side2, double side3) {
        if (
            side1 < 0 ||
            side2 < 0 ||
            side3 < 0

        ) {
            throw new IllegalArgumentException("Длина сторон треугольника не может быть меньше нуля");
        }
        else if (
                (side1 + side2 <= side3) ||
                (side1 + side3 <= side2) ||
                (side2 + side3 <= side1)
        ) {
            throw new IllegalArgumentException("Неравенство треугольника не соблюдено");
        }
        else {
            this.side1 = side1;
            this.side2 = side2;
            this.side3 = side3;
        }
    }

    public double perimeter() {
        {return this.side1 + this.side2 + this.side3;}
    }

    public double area() {
        double halfPerimeter = this.perimeter()/2;
        return Math.sqrt(halfPerimeter * (halfPerimeter - this.side1) * (halfPerimeter - this.side2) * (halfPerimeter - this.side3));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Triangle)) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(this.side1, triangle.side1) == 0
                && Double.compare(this.side2, triangle.side2) == 0
                && Double.compare(this.side3, triangle.side3) == 0)
                ||
                (Double.compare(this.side1, triangle.side1) == 0
                && Double.compare(this.side2, triangle.side3) == 0
                && Double.compare(this.side3, triangle.side2) == 0)
                ||
                (Double.compare(this.side1, triangle.side2) == 0
                && Double.compare(this.side2, triangle.side1) == 0
                && Double.compare(this.side3, triangle.side3) == 0)
                ||
                (Double.compare(this.side1, triangle.side2) == 0
                && Double.compare(this.side2, triangle.side3) == 0
                && Double.compare(this.side3, triangle.side1) == 0)
                ||
                (Double.compare(this.side1, triangle.side3) == 0
                && Double.compare(this.side2, triangle.side1) == 0
                && Double.compare(this.side3, triangle.side2) == 0)
                ||
                (Double.compare(this.side1, triangle.side3) == 0
                && Double.compare(this.side2, triangle.side2) == 0
                && Double.compare(this.side3, triangle.side1) == 0)
                ;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    public static void main(String[] args) {
        System.out.println("Периметр = " + new Triangle(3, 4, 6).perimeter());
        System.out.println("Площадь = " + new Triangle(3, 4, 6).area());
    }

}
