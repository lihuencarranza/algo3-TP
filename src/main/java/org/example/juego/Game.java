package org.example.juego;


import org.example.juego.Boxes.Box;

import java.util.Random;
import java.util.Scanner;

public class Game{

    public boolean gameOver = false;
    public Board board;

    public Box usingBotModeBoxClicked(){
        board = new Board();
        Random random = new Random();


        int col = (int) random.nextInt(10);
        int row = (int) random.nextInt(10);

        gameOver = board.clickBox(row, col);

        return board.matrix[row][col];

    }

    public void level1(Menu menu){
        board = new Board();

        while(!gameOver) {
            try{
                menu.printCommand();
            }catch(NullPointerException e){
                System.out.println("The Menu didn't work");
            }

            board.printBoard();
            Scanner sc = new Scanner(System.in);
            int command = sc.nextInt();
            System.out.println("Introduce the column: ");
            int row = sc.nextInt();
            board.printBoard();
            System.out.println("Introduce the row: ");
            int col = sc.nextInt();

            switch (command) {
                case 1 -> gameOver = board.clickBox(col, row);
                case 2 -> board.setFlagBox(col, row);
            }
        }

        System.out.println("You lost!");

    }

    private void setCoordenates(int row, int col) {
        // TODO Auto-generated method stub

    }

    public Game(){


    }


}
