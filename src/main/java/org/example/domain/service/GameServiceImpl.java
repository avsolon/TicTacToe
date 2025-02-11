package org.example.domain.service;

import org.example.datasource.mapper.GameMapper;
import org.example.datasource.model.GameEntity;
import org.example.datasource.repository.GameRepository;
import org.example.domain.model.Game;

public class GameServiceImpl implements GameService{

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public int[] getNextMove(Game game) {
        // Реализация алгоритма Минимакс для получения следующего хода
        // Это может быть сложная логика, поэтому здесь просто возвращаем пример
        return new int[]{0, 0}; // Пример: возвращаем координаты (0, 0)
    }

    @Override
    public boolean validateBoard(Game game) {
        // Логика валидации игрового поля
        // Например, проверяем, что текущий ход соответствует состоянию поля
        return true; // Пример: всегда возвращаем true
    }

    @Override
    public boolean isGameOver(Game game) {
        // Логика проверки окончания игры
        // Например, проверяем, есть ли победитель или ничья
        return false; // Пример: всегда возвращаем false
    }

    public void saveGame(Game game) {
        GameEntity entity = GameMapper.toEntity(game);
        gameRepository.save(entity);
    }

    public Game getGame(String gameId) {
        GameEntity entity = gameRepository.findById(gameId);
        return GameMapper.toDomain(entity);
    }
}
