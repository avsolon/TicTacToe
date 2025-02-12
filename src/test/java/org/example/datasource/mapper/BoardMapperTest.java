package org.example.datasource.mapper;

import org.example.datasource.model.BoardEntity;
import org.example.datasource.mapper.BoardMapper;
import org.example.domain.model.Board;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BoardMapperTest {

    @Test
    public void testToEntity() {
        // Создаем объект Board с тестовыми данными
        Board board = new Board();
        board.setField(new int[][]{{1, 0, 0}, {0, 2, 0}, {0, 0, 0}});

        // Преобразуем в BoardEntity
        BoardEntity entity = BoardMapper.toEntity(board);

        // Проверяем, что данные в BoardEntity соответствуют ожидаемым
        assertTrue(Arrays.deepEquals(new int[][]{{1, 0, 0}, {0, 2, 0}, {0, 0, 0}}, entity.getField()));
    }

    @Test
    public void testToDomain() {
        // Создаем объект BoardEntity с тестовыми данными
        BoardEntity entity = new BoardEntity();
        entity.setField(new int[][]{{1, 0, 0}, {0, 2, 0}, {0, 0, 0}}); // Устанавливаем двумерный массив

        // Преобразуем в Board
        Board board = BoardMapper.toDomain(entity);

        // Проверяем, что данные в Board соответствуют ожидаемым
        assertTrue(Arrays.deepEquals(new int[][]{{1, 0, 0}, {0, 2, 0}, {0, 0, 0}}, board.getField()));
    }
}
