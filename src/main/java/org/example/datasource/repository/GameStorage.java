package org.example.datasource.repository;

import org.example.datasource.model.GameEntity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameStorage {
    private final Map<String, GameEntity> games = new ConcurrentHashMap<>();

    public void saveGame(GameEntity game) {
        games.put(game.getGameId(), game);
    }

    public GameEntity getGame(String gameId) {
        return games.get(gameId);
    }
}
