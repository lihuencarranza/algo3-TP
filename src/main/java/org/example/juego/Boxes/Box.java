package org.example.juego.Boxes;

public class Box {
    public boolean visible = false;
    public boolean flag = false;
    public int number;
    public boolean bomb = false;

    public Box(int number) {
        this.number = number;

    }

    public void setFlag() {
        this.flag = true;
    }

    public void unsetFlag(){
        this.flag = false;
    }

    public int getNumber() {
        return number;
    }

    public boolean isABomb() {
        return bomb;
    }

    public boolean isClickable() {
        return !flag || !visible;
    }
}
