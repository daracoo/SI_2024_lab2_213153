package mk.ukim.finki.si;

import org.junit.jupiter.api.Test;


import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {
    @Test
    void everyBranchTest(){
        RuntimeException ex;
        //1 null
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 80));
        assertTrue(ex.getMessage().contains("allItems list can't be null!"));
        //2 null name
        List<Item> items = Collections.singletonList(new Item(null, "123445", 80, 0));
        assertTrue(SILab2.checkCart(items, 80));
        //3 no null name + sum less than or equal to payment
        List<Item> items1 = Collections.singletonList(new Item("item1", "1234", 100, 0));
        assertTrue(SILab2.checkCart(items1, 100));
        //4 valid barcode and no discount
        List<Item> items2 = Collections.singletonList(new Item("item2", "12345", 100, 0));
        assertTrue(SILab2.checkCart(items2, 1000));
        //5 valid barcode and discount
        List<Item> items3 = Collections.singletonList(new Item("item3", "12345", 100, 10));
        assertTrue(SILab2.checkCart(items3, 1000));
        //6 invalid barcode
        List<Item> items4 = Collections.singletonList(new Item("item4", "q1245", 100, 0));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> SILab2.checkCart(items4, 1000));
        assertEquals("Invalid character in item barcode!", exception.getMessage());
        //7 sum greater than payment
        List<Item> items5 = Collections.singletonList(new Item("item5", "12345", 900, 0));
        assertFalse(SILab2.checkCart(items5, 800));
        //8 price > 300, discount > 0, barcode starting with '0'
        List<Item> items6 = Collections.singletonList(new Item("item6", "01234", 600, 10));
        assertTrue(SILab2.checkCart(items6, 7000));
        //no barcode
        List<Item> items7 = Collections.singletonList(new Item("item5", null, 100, 0));
        RuntimeException exception2 = assertThrows(RuntimeException.class, () -> SILab2.checkCart(items7, 1000));
        assertEquals("No barcode!", exception2.getMessage());
    }

    @Test
    void secondTestConditions(){
        // T T T
        List<Item> items = Collections.singletonList(new Item("item1", "01234", 600, 10));
        assertTrue(SILab2.checkCart(items, 7000));
        //T T F
        List<Item> items2 = Collections.singletonList(new Item("item2", "1234", 600, 10));
        assertTrue(SILab2.checkCart(items2, 7000));
        //T F T
        List<Item> items3 = Collections.singletonList(new Item("item3", "01234", 600, 0));
        assertTrue(SILab2.checkCart(items3, 7000));
        //T F F
        List<Item> items4 = Collections.singletonList(new Item("item4", "1234", 600, 0));
        assertTrue(SILab2.checkCart(items4, 7000));
        //F T T
        List<Item> items5 = Collections.singletonList(new Item("item5", "01234", 200, 10));
        assertTrue(SILab2.checkCart(items5, 7000));
        //F T F
        List<Item> items6 = Collections.singletonList(new Item("item6", "1234", 200, 10));
        assertTrue(SILab2.checkCart(items6, 7000));
        //F F T
        List<Item> items7 = Collections.singletonList(new Item("item7", "01234", 200, 0));
        assertTrue(SILab2.checkCart(items7, 7000));
        //F F F
        List<Item> items8 = Collections.singletonList(new Item("item8", "1234", 200, 0));
        assertTrue(SILab2.checkCart(items8, 7000));
    }
}