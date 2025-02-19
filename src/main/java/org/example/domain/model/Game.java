package org.example.domain.model;

import java.util.UUID;

public class Game {
    private String id; // UUID игры
    private Board board;
    private int currentPlayer; // 1 - крестик, 2 - нолик
    private Integer winner;

    public Game() {
        this.id = UUID.randomUUID().toString();
        this.board = new Board();
        this.currentPlayer = 1; // начинает игрок 1 - крестики
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

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setBoard(Board domain) {
        this.board = domain;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getWinner() {
        return winner;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
    }
}
