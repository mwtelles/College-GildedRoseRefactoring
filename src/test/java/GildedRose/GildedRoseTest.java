package GildedRose;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

}