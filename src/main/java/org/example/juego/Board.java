package org.example.juego;

import java.util.Random;

import org.example.juego.Boxes.Box;

public class Board {
    public Box[][] matrix;
    private int mines = 10;
    private int rows = 10;
    private int columns = 10;


    public Board() {
        this.matrix = new Box[rows][columns];

        createTable();
    }

    private int randomNumberRows() {

        Random random = new Random();
        int upperbound = rows;

        return random.nextInt(upperbound);
    }

    private int randomNumberCols() {

        Random random = new Random();
        int upperbound = columns;

        return (int) random.nextInt(upperbound);
    }

    private void createTable(){
        int rCol = randomNumberRows();
        int rRow = randomNumberCols();

        int i = 0;
        while (i < mines) {
            if (matrix[rCol][rRow] == null || !matrix[rCol][rRow].bomb){
                matrix[rCol][rRow] = BoxFactory.createBox(BoxFactory.Type.BOMB, 10);
                i++;
                createNumberBoxes(rCol, rRow);
            }

            rCol = randomNumberRows();
            rRow = randomNumberCols();
        }

        createEmptyBoxes();

    }



    private void createNumberBoxes(int colBomb, int rowBomb){
        int colStart = colBomb - 1;
        int colEnd = colBomb + 1;
        int rowStart = rowBomb - 1;
        int rowEnd = rowBomb + 1;

        if (colStart < 0) {
            colStart = 0;
        }
        if (colEnd > rows-1) {
            colEnd = rows-1;
        }
        if (rowStart < 0) {
            rowStart = 0;
        }
        if (rowEnd > columns-1) {
            rowEnd = columns-1;
        }


        for (int i = colStart; i <= colEnd; i++) {
            for (int j = rowStart; j <= rowEnd; j++) {
                if (matrix[i][j] == null) {
                    matrix[i][j] = BoxFactory.createBox(BoxFactory.Type.BOX, 1);
                } else if (!matrix[i][j].bomb) {
                    matrix[i][j] = BoxFactory.createBox(BoxFactory.Type.BOX, matrix[i][j].number + 1);
                }
            }
        }

    }



    private void createEmptyBoxes(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == null) {
                    matrix[i][j] = BoxFactory.createBox(BoxFactory.Type.BOX, 0);
                }
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j].printBox();
            }
            System.out.println();
        }
    }

    public boolean clickBox(int col, int row) {
        if (matrix[col][row].bomb) {
            matrix[col][row].visible = true;
            return true;
        } else {
            matrix[col][row] = BoxFactory.createBox(BoxFactory.Type.CLICK, matrix[col][row].number);
            clickNeighbours(col, row);
        }
        return false;
    }

    public void clickNeighbours(int col, int row){
        int colStart = col - 1;
        int colEnd = col + 1;
        int rowStart = row - 1;
        int rowEnd = row + 1;

        if (colStart < 0) {
            colStart = 0;
        }
        if (colEnd > rows-1) {
            colEnd = rows-1;
        }
        if (rowStart < 0) {
            rowStart = 0;
        }
        if (rowEnd > columns-1) {
            rowEnd = columns-1;
        }

        for (int i = colStart; i <= colEnd; i++) {
            for (int j = rowStart; j <= rowEnd; j++) {
                if (matrix[i][j].number == 0 && !matrix[i][j].visible) {
                    matrix[i][j] = BoxFactory.createBox(BoxFactory.Type.CLICK, matrix[i][j].number);
                    clickNeighbours(i, j);
                } else if (matrix[i][j].number != 10 && !matrix[i][j].visible) {
                    matrix[i][j] = BoxFactory.createBox(BoxFactory.Type.CLICK, matrix[i][j].number);
                }
            }
        }

    }

    public void setFlagBox(int col, int row) {
        if (!matrix[col][row].visible) {
            matrix[col][row].setFlag();
        }
    }

    public Box getBox(int row, int col) {
        return matrix[row][col];
    }
}
