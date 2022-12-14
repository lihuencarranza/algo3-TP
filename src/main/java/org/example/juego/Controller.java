package org.example.juego;

public class Controller {
    public Game game;

    public int availableFlags;

    public Controller() {}

    void startGame(){
        game = new Game();
        availableFlags = game.flags;
    }

    public String getGameRules() {

        return """
                Minesweeper is a game where mines are hidden in a grid of squares. Safe squares have numbers telling you how many mines touch the square. You can use the number clues to solve the game by opening all of the safe squares. If you click on a mine you lose the game!
                 You open squares with the left mouse button and put flags on mines with the right mouse button.When you open a square that does not touch any mines, it will be empty and the adjacent squares will automatically open in all directions until reaching squares that contain numbers. A common strategy for starting games is to randomly click until you get a big opening with lots of numbers.
                \s""";
    }
    public void setFlag(int row, int col) {
         game.board.setFlagBox(row, col);
         this.availableFlags--;
    }

    public boolean isClickable(int row, int col){
        return game.board.matrix[row][col].isClickable();
    }

    public void removeFlag(int row, int col){
        game.board.unsetFlag(row, col);
        this.availableFlags++;
    }

    public int getNumber(int row, int col){
        return game.board.matrix[row][col].number;
    }

    public boolean hasBomb(int row, int col){return game.board.matrix[row][col].isABomb();}

    public void click(int row, int col){game.clickBox(row,col); }

    public void endGame(boolean b){
        game.setState(b);
    }


}
