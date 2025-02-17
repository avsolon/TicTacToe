package org.example.datasource.model;

import java.util.UUID;

public class GameEntity {
    private final String id;
    private BoardEntity board;
    private int currentPlayer;

    public GameEntity() {
        this.id = UUID.randomUUID().toString();
        this.board = new BoardEntity();
        this.currentPlayer = 1;
    }

    public String getId() {
        return id;
    }

    public BoardEntity getBoard() {
        return board;
    }

    public void setBoard(BoardEntity board) { // Добавьте этот метод
        this.board = board;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
