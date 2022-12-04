package org.example.juego;

import java.util.Scanner;

public class Main {

    public static void main( String[] args )
    {
        Menu menu = new Menu();
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch (option) {
            case 1:
                Game game = new Game();
                game.level1(menu);
                break;
            case 2:
                return;
        }

    }
}
