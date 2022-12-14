package org.example;

import static org.junit.Assert.*;

import org.junit.Test;

import org.example.juego.Game;

public class GameTest {


    @Test
    public void initializerGame() {
        Game game = new Game();
        assertNotNull(game);
    }

    @Test
    public void clickBombEsGameOver() {
        Game game = new Game();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (game.hasBomb(i, j))
                    game.clickBox(i, j);
            }
        }

        assertSame(game.getState(), false);
    }

    @Test
    public void clickearBoxNormalNoEsGameOver() {
        Game game = new Game();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (!game.hasBomb(i, j))
                    game.clickBox(i, j);
            }
        }
        assertFalse(game.getState());
    }
}
