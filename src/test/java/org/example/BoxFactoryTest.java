package org.example;

import static org.junit.Assert.assertTrue;

import org.example.juego.Boxes.Box;
import org.junit.Test;
import org.example.juego.BoxFactory;

public class BoxFactoryTest {
    @Test
    public void createABomb()
    {
        Box box = BoxFactory.createBox(BoxFactory.Type.BOMB, 10);
        assertTrue(box.isABomb());
    }
    @Test
    public void bombsNumberIs10(){
        Box box = BoxFactory.createBox(BoxFactory.Type.BOMB, 10);
        assertTrue(box.getNumber() == 10);
        //agregar excepcion si el numero es diferente de 10
    }

    @Test
    public void bombsFlagisFalse(){
        Box box = BoxFactory.createBox(BoxFactory.Type.BOMB, 10);
        assertTrue(box.flag == false);
    }

    @Test
    public void createANonNullBox()
    {
        Box box = BoxFactory.createBox(BoxFactory.Type.BOX, 0);
        assertTrue(box != null);
    }

    @Test
    public void boxNumerIs0()
    {
        Box box = BoxFactory.createBox(BoxFactory.Type.BOX, 0);
        assertTrue(box.getNumber() == 0);
    }

    @Test
    public void boxFlagisFalse(){
        Box box = BoxFactory.createBox(BoxFactory.Type.BOX, 0);
        assertTrue(box.flag == false);
    }

    @Test
    public void createANonNullClickedBox()
    {
        Box box = BoxFactory.createBox(BoxFactory.Type.CLICK, 0);
        assertTrue(box != null);
    }

    @Test
    public void clickedBoxNumberIs0()
    {
        Box box = BoxFactory.createBox(BoxFactory.Type.CLICK, 0);
        assertTrue(box.getNumber() == 0);
    }
}
