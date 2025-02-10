package org.example.datasource.model;

public class BoardEntity {
    private int[][] field;

    public BoardEntity() {
        this.field = new int[3][3];
    }

    public int[][] getfield() {
        return field;
    }

    public void setfield(int[][] field) {
        this.field = field;
    }
}
