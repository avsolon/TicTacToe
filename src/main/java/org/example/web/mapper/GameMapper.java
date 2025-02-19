package org.example.web.mapper;

import org.example.domain.model.Game;
import org.example.web.model.GameDTO;

public class GameMapper {
    public static GameDTO toDTO(Game game) {
        GameDTO dto = new GameDTO();
        dto.setId(game.getId());
        dto.setBoard(BoardMapper.toDTO(game.getBoard()));
        dto.setCurrentPlayer(game.getCurrentPlayer());
        dto.setWinner(game.getWinner());
        return dto;
    }

    public static Game toDomain(GameDTO dto) {
        Game game = new Game();
        game.setId(dto.getId());
        game.setBoard(BoardMapper.toDomain(dto.getBoard()));
        game.setCurrentPlayer(dto.getCurrentPlayer());
        game.setWinner(dto.getWinner());
        return game;
    }
}
