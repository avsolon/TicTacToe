package org.example.datasource.mapper;

import org.example.datasource.model.BoardEntity;
import org.example.domain.model.Board;

public class BoardMapper {
    public static BoardEntity toEntity(Board board) {
        BoardEntity entity = new BoardEntity();
        entity.setField(board.getField());
        return entity;
    }

    public static Board toDomain(BoardEntity entity) {
        Board board = new Board();
        board.setField(entity.getField());
        return board;
    }
}
