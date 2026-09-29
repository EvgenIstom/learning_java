package ru.stqa.geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTest {
    @Test
    void perimeterIsCorrect() {
        Assertions.assertEquals(10, new Triangle(4, 2, 4).perimeter());
    }

    @Test
    void areaIsCorrect() {
        Assertions.assertEquals(2.9, new Triangle(3, 4, 2).area(), 0.01);
    }

    @Test
    void cannotCreateTriangleWithNegativeSide() {
        try {
            new Triangle(-3, 2, 3);
            Assertions.fail();
        }
        catch (IllegalArgumentException exception) {
            //ok
        }
    }

    @Test
    void cannotCreateInvalidTriangle() {
        try {
            new Triangle(3, 20, 4);
            Assertions.fail();
        }
        catch (IllegalArgumentException exception) {
            //ok
        }
    }

    @Test
    void trianglesAreEqual() {
        var t1 = new Triangle(2, 4, 5);
        var t2 = new Triangle(2, 4, 5);
        Assertions.assertTrue(t1.equals(t2));
    }

    @Test
    void trianglesWithNotOrderedSidesAreEqual() {
        var t1 = new Triangle(2, 4, 5);
        var t2 = new Triangle(4, 2, 5);
        Assertions.assertTrue(t1.equals(t2));
    }
}
