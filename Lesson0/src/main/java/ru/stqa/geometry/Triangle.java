package ru.stqa.geometry;

public class Triangle {
    public static double perimeter(double side1, double side2, double side3) {
        if (!isValidTriangle(side1, side2, side3)) {
            throw new RuntimeException("Треугольник невалидный. Периметр не будет посчитан");
        }

        return side1 + side2 +side3;
    }

    public static double area(double side1, double side2, double side3) {
        if (!isValidTriangle(side1, side2, side3)) {
            throw new RuntimeException("Треугольник невалидный. Площадь не будет посчитана");
        }

        double halfPerimeter = perimeter(side1, side2, side3)/2;
        return Math.sqrt(halfPerimeter * (halfPerimeter - side1) * (halfPerimeter - side2) * (halfPerimeter - side3));

    }

    public static boolean isValidTriangle(double side1, double side2, double side3){
        return (side1 + side2 > side3) &&
                (side1 + side3 > side2) &&
                (side2 + side3 > side1);
    }

    public static void main(String[] args) {
        double side1 = 10;
        double side2 = 5.5;
        double side3 = 13.5;


        System.out.println("Периметр = " + perimeter(side1, side2, side3));
        System.out.println("Площадь = " + area(side1, side2, side3));
    }

}
