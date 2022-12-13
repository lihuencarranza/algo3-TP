package org.example;

import org.example.juego.Boxes.BombBox;
import org.example.juego.Boxes.Box;
import org.example.juego.Boxes.ClickedBox;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoxTest {
    @Test
    public void createABomb()
    {
        Box box = new BombBox(10);
        assertTrue(box.isABomb());
    }

    @Test
    public void bombsNumberIs10(){
        Box box = new BombBox(10);
        assertEquals(10, box.getNumber());
    }

    @Test
    public void bombsFlagisFalse(){
        Box box = new BombBox(10);
        assertFalse(box.flag);
    }

    @Test
    public void createANonNullBox()
    {
        Box box = new Box(0);
        assertNotNull(box);
    }

    @Test
    public void boxNumerIs0()
    {
        Box box = new Box(0);
        assertEquals(0, box.getNumber());
    }

    @Test
    public void boxFlagisFalse(){
        Box box = new Box(0);
        assertFalse(box.flag);
    }

    @Test
    public void createANonNullClickedBox()
    {
        ClickedBox box = new ClickedBox(0);
        assertNotNull(box);
    }

    @Test
    public void clickedBoxNumberIs0()
    {
        ClickedBox box = new ClickedBox(0);
        assertEquals(0, box.getNumber());
    }

    @Test
    public void clickedBoxFlagsFalse(){
        ClickedBox box = new ClickedBox(0);
        assertFalse(box.flag);
    }

    @Test
    public void setFlagInA0Box(){
        Box box = new Box(0);
        box.setFlag();
        assertTrue(box.flag);
    }

    @Test
    public void setFlagInABomb(){
        Box box = new BombBox(10);
        box.setFlag();
        assertTrue(box.flag);
    }

    @Test
    public void cantClickAFlag(){
        Box box = new Box(0);
        box.setFlag();
        assertFalse(box.isClickable());
    }

    @Test
    public void cantClickAClickedBox(){
        Box box = new ClickedBox(0);
        assertFalse(box.isClickable());
    }
}
