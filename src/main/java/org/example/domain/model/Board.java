package org.example.domain.model;

public class Board {

    private int[][] field;

//    public Board(int row, int col){
//        this.field = new int[3][3];
//    }

    public Board() {
        this.field = new int[3][3];
    }

    public int[][] getField() {
        return field;
    }

    public void setCell(int row, int col, int player) {
        field[row][col] = player;
    }

    public boolean isCellEmpty(int row, int col) {
        return field[row][col] == 0;
    }

    public void reset() {
        this.field = new int[3][3];
    }

    public void setField(int[][] field) {
    }
}
