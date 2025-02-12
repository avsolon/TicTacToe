package org.example.datasource.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // Используйте Long или UUID в зависимости от ваших предпочтений

    private String gameId; // UUID игры
    @Embedded
    private BoardEntity board;
    private int currentPlayer;

    public GameEntity() {
        this.gameId = UUID.randomUUID().toString();
        this.board = new BoardEntity();
        this.currentPlayer = 1;
    }

    public String getGameId() {
        return gameId;
    }

    public BoardEntity getBoard() {
        return board;
    }

    public void setBoard(BoardEntity board) {
        this.board = board;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
//public class GameEntity {
//    private final String id;
//    private BoardEntity board;
//    private int currentPlayer;
//
//    public GameEntity() {
//        this.id = UUID.randomUUID().toString();
//        this.board = new BoardEntity();
//        this.currentPlayer = 1;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public BoardEntity getBoard() {
//        return board;
//    }
//
//    public void setBoard(BoardEntity board) { // Добавьте этот метод
//        this.board = board;
//    }
//
//    public int getCurrentPlayer() {
//        return currentPlayer;
//    }
//
//    public void setCurrentPlayer(int currentPlayer) {
//        this.currentPlayer = currentPlayer;
//    }
//}
