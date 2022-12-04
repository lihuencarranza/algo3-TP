package org.example;

import static org.junit.Assert.*;

import org.junit.Test;

import org.example.juego.Board;

public class BoardTest {
    @Test
    public void creatingABoardLevel1l()
    {
        Board board = new Board();

        assertNotNull(board);

        int bombs = 0;
        int empty = 0;
        int number = 0;

        for (int i = 0; i < 10; i++){
            for(int j= 0; j < 10; j++){
                if (board.matrix[i][j].bomb)
                    bombs++;
                else if (board.matrix[i][j].number == 0)
                    empty++;
                else if (board.matrix[i][j].number > 0)
                    number++;
            }
        }

        assertEquals(bombs, 10);

        int total_of_boxes = bombs + empty + number;

        assertEquals(total_of_boxes, 100);

    }
}
