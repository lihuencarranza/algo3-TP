package org.example.juego;

import org.example.juego.Boxes.Box;
import org.example.juego.Boxes.ClickedBox;
import org.example.juego.Boxes.BombBox;

public class BoxFactory {
    public static enum Type { BOMB, BOX, CLICK };
    private static final BombBox bombBox = new BombBox();
    private static org.example.juego.Boxes.Box[] Box = new Box[11];
    private static final Box[] clickedBox = new ClickedBox[11];

    public static Box createBox(Type type, int number) {

        switch (type) {
            case BOMB:
                return bombBox;
            case BOX:
                if (Box[number] == null)
                    Box[number] = new Box(number);
                return Box[number];
            case CLICK:
                if (clickedBox[number] == null)
                    clickedBox[number] = new ClickedBox(number);
                return clickedBox[number];
        }

        return null;
    }
}
