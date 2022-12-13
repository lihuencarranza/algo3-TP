package org.example.juego;

import org.example.juego.Boxes.Box;
import org.example.juego.Boxes.ClickedBox;
import org.example.juego.Boxes.BombBox;
import org.example.juego.Boxes.FlagBox;

public class BoxFactory {
    public static enum Type { BOMB, BOX, CLICK, FLAG };
    private static final BombBox bombBox = new BombBox(10);
    private static final Box[] box = new Box[11];
    private static final ClickedBox[] clickedBox = new ClickedBox[11];
    private static final FlagBox[] flagBox = new FlagBox[11];

    public static Box createBox(int number){
        if (box[number] == null)
            box[number] = new Box(number);
        return box[number];
    }

    public static BombBox createBomb(){
        return bombBox;
    }

    public static ClickedBox createClickedBox(int number){
        if (clickedBox[number] == null)
            clickedBox[number] = new ClickedBox(number);
        return clickedBox[number];
    }

    public static FlagBox createFlagBox(int number){
        if (flagBox[number] == null) {
            flagBox[number] = new FlagBox(number);
        }
        return flagBox[number];
    }


}
