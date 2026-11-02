package ru.stqa.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CollectionTests {

    @Test
    void arrayTests() {
        var array = new String[]{"a", "b", "c"};
        Assertions.assertEquals("a", array[0]);

        array[0] = "d";
        Assertions.assertEquals("d", array[0]);

    }

    @Test
    void listTests() {
        var listTest = new ArrayList<String>(List.of("a", "b", "c"));

        Assertions.assertEquals(3, listTest.size());
        Assertions.assertEquals("a", listTest.get(0));

        listTest.set(0, "d");
        Assertions.assertEquals("d", listTest.get(0));
    }

}
