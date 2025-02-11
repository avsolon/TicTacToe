package org.example.web.mapper;

import org.example.domain.model.Board;
import org.example.web.model.BoardDTO;

public class BoardMapper {
    public static BoardDTO toDTO(Board board) {
        BoardDTO dto = new BoardDTO();
        dto.setField(board.getField());
        return dto;
    }

    public static Board toDomain(BoardDTO dto) {
        Board board = new Board();
        board.setField(dto.getField());
        return board;
    }
}
