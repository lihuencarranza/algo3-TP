package org.example;

import org.example.juego.Boxes.Box;
import org.junit.Test;
import org.example.juego.BoxFactory;

import static org.junit.Assert.*;

public class BoxFactoryTest {
    @Test
    public void createABomb()
    {
        Box box = BoxFactory.createBomb();
        assertTrue(box.isABomb());
    }
    @Test
    public void bombsNumberIs10(){
        Box box = BoxFactory.createBomb();
        assertEquals(10, box.getNumber());
    }

    @Test
    public void bombsFlagisFalse(){
        Box box = BoxFactory.createBomb();
        assertFalse(box.flag);
    }

    @Test
    public void createANonNullBox()
    {
        Box box = BoxFactory.createBox(0);
        assertNotNull(box);
    }

    @Test
    public void boxNumerIs0()
    {
        Box box = BoxFactory.createBox( 0);
        assertEquals(0, box.getNumber());
    }

    @Test
    public void boxFlagisFalse(){
        Box box = BoxFactory.createBox(0);
        assertFalse(box.flag);
    }

    @Test
    public void createANonNullClickedBox()
    {
        Box box = BoxFactory.createClickedBox(0);
        assertNotNull(box);
    }

    @Test
    public void clickedBoxNumberIs0()
    {
        Box box = BoxFactory.createClickedBox(0);
        assertEquals(0, box.getNumber());
    }
}
