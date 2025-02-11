package org.example.domain.service;

import org.example.domain.model.Game;

public interface GameService {
    int[] getNextMove(Game game); // Метод получения следующего хода алгоритмом Минимакс
    boolean validateBoard(Game game); // Метод валидации игрового поля
    boolean isGameOver(Game game); // Метод проверки окончания игры
}
