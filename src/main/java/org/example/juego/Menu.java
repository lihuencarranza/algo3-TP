package org.example.juego;

public class Menu {

    public Menu(){
        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║         Welcome to Minesweeper            ║");
        System.out.println("╠═══════════════════════════════════════════╣");
        System.out.println("║                 1. Play                   ║");
        System.out.println("║                 2. Exit                   ║");
        System.out.println("╚═══════════════════════════════════════════╝");
    }

    public void printCommand(){
        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║                 Choose                    ║");
        System.out.println("╠═══════════════════════════════════════════╣");
        System.out.println("║               1. Click box                ║");
        System.out.println("║               2. Set flag                 ║");
        System.out.println("╚═══════════════════════════════════════════╝");
    }
}
