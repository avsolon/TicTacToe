package org.example.datasource.repository;

import org.example.datasource.model.GameEntity;

public interface GameRepository {
    GameEntity findByGameId(String gameId); // Метод для поиска по gameId
    void save(GameEntity game);

}

