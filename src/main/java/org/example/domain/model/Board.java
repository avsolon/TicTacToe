package org.example.domain.model;

public class Board {

    private int[][] field;

    public Board() {
        this.field = new int[3][3];
    }

    public int[][] getField() {
        return field;
    }

    public void setCell(int row, int col, int player) {

        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            throw new IllegalArgumentException("Некорректные координаты");
        }
        if (player != 0 && player != 1 && player != 2) {
            throw new IllegalArgumentException("Некорректное значение игрока");
        }
        field[row][col] = player;
    }

    public boolean isCellEmpty(int row, int col) {
        return field[row][col] == 0;
    }

    public void reset() {
        this.field = new int[3][3];
    }

    public void setField(int[][] field) {
        if (field != null && field.length == 3 && field[0].length == 3) {
            this.field = field;
        }
    }
}


//    public Board(int row, int col){
//        this.field = new int[3][3];
//    }
