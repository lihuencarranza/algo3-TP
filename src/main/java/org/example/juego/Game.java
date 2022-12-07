package org.example.juego;


import org.example.juego.Boxes.Box;

import java.util.Random;
import java.util.Scanner;

public class Game{

    public boolean gameOver = false;
    public Board board;
    public final int flags = 10;

    public Box usingBotModeBoxClicked(){
        board = new Board();
        Random random = new Random();


        int col = (int) random.nextInt(10);
        int row = (int) random.nextInt(10);

        gameOver = board.clickBox(row, col);

        return board.matrix[row][col];

    }

    public Game(){
        board = new Board();
    }

    private void setCoordenates(int row, int col) {
        // TODO Auto-generated method stub

    }




}
