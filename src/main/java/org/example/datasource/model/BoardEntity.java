package org.example.datasource.model;

public class BoardEntity {
    private int[][] field;

    public BoardEntity() {
        this.field = new int[3][3];
    }

    public int[][] getField() {
        return field;
    }

    public void setField(int[][] field) {
        this.field = field;
    }

}
