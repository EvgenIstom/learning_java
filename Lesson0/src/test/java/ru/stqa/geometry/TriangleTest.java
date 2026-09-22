package ru.stqa.geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTest {
    @Test
    void triangleIsValid() {
        Assertions.assertTrue(new Triangle(3, 4, 5).isValidTriangle());
    }

    @Test
    void perimeterIsCorrect() {
        Assertions.assertEquals(12, new Triangle(3, 4, 5).perimeter());
    }

    @Test
    void areaIsCorrect() {
        Assertions.assertEquals(5.95, new Triangle(3, 4, 5.3).area(), 0.01);
    }
}
