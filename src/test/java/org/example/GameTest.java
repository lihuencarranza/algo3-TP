package org.example;

import static org.junit.Assert.*;

import org.junit.Test;

import org.example.juego.Game;
import org.example.juego.Boxes.Box;

public class GameTest {


    @Test
    public void inicializarGame() {
        Game game = new Game();
        assertNotNull(game);
    }

    @Test
    public void clickearBombEsGameOver() {
        Game game = new Game();
        Box box = game.usingBotModeBoxClicked();

        assertSame(box.bomb, game.gameOver);
    }

    @Test
    public void clickearBoxNormalNoEsGameOver() {
        Game game = new Game();
        Box box = game.usingBotModeBoxClicked();

        assertSame(!box.bomb, !game.gameOver);
    }
}
