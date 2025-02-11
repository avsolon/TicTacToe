package org.example.datasource.repository;

import org.example.datasource.model.GameEntity;

public interface GameRepository {
    void save(GameEntity game);
    GameEntity findById(String gameId);
}
