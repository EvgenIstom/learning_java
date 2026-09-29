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
}
