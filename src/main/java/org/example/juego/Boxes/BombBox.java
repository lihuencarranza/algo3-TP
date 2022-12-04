package org.example.juego.Boxes;

public class BombBox extends Box{
    public BombBox() {
        super(10);
        this.bomb = true;
    }

    public static boolean clickBombBox() {
        System.out.println("BOOM!");
        return true;
    }
}
