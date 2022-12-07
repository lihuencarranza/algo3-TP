package org.example.juego;

import org.example.juego.Boxes.Box;
import org.example.juego.Boxes.ClickedBox;
import org.example.juego.Boxes.BombBox;

public class BoxFactory {
    public static enum Type { BOMB, BOX, CLICK, FLAG };
    private static final BombBox bombBox = new BombBox(10);
    private static org.example.juego.Boxes.Box[] box = new Box[11];
    private static final Box[] clickedBox = new ClickedBox[11];
    private static final Box[] flagBox = new Box[11];

    public static Box createBox(Type type, int number) {

        switch (type) {
            case BOMB:
                return bombBox;
            case BOX:
                if (box[number] == null)
                    box[number] = new Box(number);
                return box[number];
            case CLICK:
                if (clickedBox[number] == null)
                    clickedBox[number] = new ClickedBox(number);
                return clickedBox[number];
            case FLAG:
                if (flagBox[number] == null){
                    flagBox[number] = new Box(number);
                    flagBox[number].setFlag();
                }
                return flagBox[number];

        }

        return null;
    }
}
