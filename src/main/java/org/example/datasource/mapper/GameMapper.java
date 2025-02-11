package org.example.datasource.mapper;

import org.example.datasource.model.GameEntity;
import org.example.domain.model.Game;

public class GameMapper {
    public static GameEntity toEntity(Game game) {
        GameEntity entity = new GameEntity();
        entity.setCurrentPlayer(game.getCurrentPlayer());
        entity.setBoard(BoardMapper.toEntity(game.getBoard()));
        return entity;
    }

    public static Game toDomain(GameEntity entity) {
        Game game = new Game();
        game.setCurrentPlayer(entity.getCurrentPlayer());
        game.setBoard(BoardMapper.toDomain(entity.getBoard()));
        return game;
    }
}
