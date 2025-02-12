package org.example.domain.model;

import java.util.UUID;

public class Game {
    private String id; // UUID игры
    private Board board;
    private int currentPlayer; // 1 - крестик, 2 - нолик

    public Game() {
        this.id = UUID.randomUUID().toString();
        this.board = new Board();
        this.currentPlayer = 1; // Текущий игрок(начинает игрок 1)
    }

    public String getId() {
        return id;
    }

    public Board getBoard() {
        return board;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setBoard(Board domain) {
        this.board = domain;
    }

    public void setId(String id) {
        this.id = id;
    }
}
