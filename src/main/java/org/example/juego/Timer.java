package org.example.juego;

import javafx.animation.AnimationTimer;

public class Timer extends AnimationTimer {
    private long prevTime = 0;
    private int time = 0;
    @Override
    public void handle(long now) {
        long dt =now - prevTime;
        if (dt >1e9){
            time++;
            prevTime = now;
            System.out.println(time);
        }
    }
}
