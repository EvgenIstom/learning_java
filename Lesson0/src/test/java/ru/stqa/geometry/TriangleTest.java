package ru.stqa.geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTest {
    @Test
    void triangleIsValid() {
        boolean validation = Triangle.isValidTriangle(5, 4, 3);
        Assertions.assertEquals(true, validation);
    }

    @Test
    void perimeterIsCorrect() {
        double result = Triangle.perimeter(5, 4, 3);
        Assertions.assertEquals(12, result);
    }

    @Test
    void areaIsCorrect() {
        double result = Triangle.area(5, 4, 3.3);
        Assertions.assertEquals(6.58, result, 0.01);
    }
}
