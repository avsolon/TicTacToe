package org.example.datasource.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class BoardEntity {
    private int[] field;

    public BoardEntity() {
        this.field = new int[9]; // Плоский массив 3x3
    }

    public int[][] getField() {
        int[][] board = new int[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(field, i * 3, board[i], 0, 3);
        }
        return board;
    }

    public void setField(int[][] field) {
        for (int i = 0; i < 3; i++) {
            System.arraycopy(field[i], 0, this.field, i * 3, 3);
        }
    }
}


//public class BoardEntity {
//    private int[][] field;
//
//    public BoardEntity() {
//        this.field = new int[3][3];
//    }
//
//    public int[][] getField() {
//        return field;
//    }
//
//    public void setField(int[][] field) {
//        this.field = field;
//    }
//
//}





