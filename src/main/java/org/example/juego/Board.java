package org.example.juego;

import java.util.List;
import java.util.Random;

import org.example.juego.Boxes.Box;

public class Board {
    public Box[][] matrix;
    private int mines = 10;
    private final int rows = 10;
    private final int columns = 10;

    public int[][] minesList;


    public Board() {
        this.matrix = new Box[rows][columns];

        createTable();
    }

    private int randomNumberRows() {

        Random random = new Random();

        return random.nextInt(rows);
    }

    private int randomNumberCols() {

        Random random = new Random();

        return (int) random.nextInt(columns);
    }

    private void createTable() {

        minesList = new int[mines+1][2];

        int rRow = randomNumberRows();
        int rCol = randomNumberCols();

        int i = 0;
        while (i < mines) {
            if (matrix[rRow][rCol] == null || !matrix[rRow][rCol].bomb) {
                matrix[rRow][rCol] = BoxFactory.createBomb();
                i++;
                createNumberBoxes(rRow, rCol);
                minesList[i][0]=rRow;
                minesList[i][1]=rCol;
            }
            rRow = randomNumberRows();
            rCol = randomNumberCols();

        }
        createEmptyBoxes();
    }



    private void createNumberBoxes(int rowBomb, int colBomb){
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


        for  (int i = rowStart; i <= rowEnd; i++){
            for (int j = colStart; j <= colEnd; j++) {
                if (matrix[i][j] == null) {
                    matrix[i][j] = BoxFactory.createBox(1);
                } else if (!matrix[i][j].bomb) {
                    matrix[i][j] = BoxFactory.createBox(matrix[i][j].number + 1);
                }
            }
        }

    }



    private void createEmptyBoxes(){
        for (int i = 0; i < rows; i++) {
            for  (int j = 0; j < columns; j++){
                if (matrix[i][j] == null) {
                    matrix[i][j] = BoxFactory.createBox(0);
                }
            }
        }
    }


    public boolean clickBox(int row, int col) {
        if (matrix[row][col].bomb) {
            matrix[row][col].visible = true;
            return true;
        } else {
            matrix[row][col] = BoxFactory.createClickedBox(matrix[row][col].number);
            //clickNeighbours( row, col);
        }
        return false;
    }

    public void clickNeighbours(int row, int col){
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

        for (int i = rowStart; i <= rowEnd; i++) {
            for (int j = colStart; j <= colEnd; j++) {
                if (matrix[i][j].number == 0 && !matrix[i][j].visible) {
                    matrix[i][j] = BoxFactory.createClickedBox(matrix[i][j].number);
                    clickNeighbours(i, j);
                } else if (matrix[i][j].number != 10 && !matrix[i][j].visible) {
                    matrix[i][j] = BoxFactory.createClickedBox(matrix[i][j].number);
                }
            }
        }

    }

    public void setFlagBox( int row, int col) {
        if (!matrix[row][col].visible) {
            int i = matrix[row][col].number;
            matrix[row][col] = BoxFactory.createClickedBox(i);
            matrix[row][col].setFlag();
        }
    }

    public void unsetFlag(int row, int col){
        if (matrix[row][col].flag){
            matrix[row][col].unsetFlag();
        }
    }


    public Box getBox(int row, int col) {
        return matrix[row][col];
    }
}
