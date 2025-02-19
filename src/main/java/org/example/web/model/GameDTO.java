package org.example.web.model;

public class GameDTO {
    private String id;
    private BoardDTO board;
    private int currentPlayer;
    private Integer winner;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BoardDTO getBoard() {
        return board;
    }

    public void setBoard(BoardDTO board) {
        this.board = board;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Integer getWinner() {return winner;}

    public void setWinner(Integer winner) {this.winner = winner;}
}
