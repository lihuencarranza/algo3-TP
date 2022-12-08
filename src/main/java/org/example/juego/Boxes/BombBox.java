package org.example.juego.Boxes;

public class BombBox extends Box{
    public BombBox(int number) {
        super(number);

        this.bomb = true;
    }

    public boolean clickBombBox() {
        System.out.println("BOOM!");
        super.visible = true;
        return true;
    }
}
