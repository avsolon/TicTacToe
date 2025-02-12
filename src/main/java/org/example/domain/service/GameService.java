package org.example.domain.service;

import org.example.domain.model.Game;

public interface GameService {
    void saveGame(Game game);
    Game getGame(String gameId);
    int[] getNextMove(Game game); // Метод получения следующего хода алгоритмом Минимакс
    boolean isBoardFull(Game game); // Метод валидации игрового поля
    boolean isWinner(Game game, int player);
//    boolean isGameOver(Game game); // Метод проверки окончания игры
}
