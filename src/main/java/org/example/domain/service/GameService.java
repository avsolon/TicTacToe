package org.example.domain.service;

import org.example.domain.model.Game;
import org.example.web.model.GameDTO;

public interface GameService {
    void saveGame(Game game);
    Game getGame(String gameId);
    int[] getNextMove(Game game);
    boolean isValidMove(Game game, GameDTO gameDTO); //
    boolean isBoardFull(Game game);
    boolean isWinner(Game game, int player);
    boolean isGameOver(Game game);
}
