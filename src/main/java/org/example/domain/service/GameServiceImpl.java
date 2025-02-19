package org.example.domain.service;

import org.example.datasource.mapper.BoardMapper;
import org.example.datasource.mapper.GameMapper;
import org.example.datasource.model.GameEntity;
import org.example.datasource.repository.GameRepository;
import org.example.domain.model.Game;
import org.example.web.model.GameDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public int[] getNextMove(Game game) {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[]{-1, -1};

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (game.getBoard().isCellEmpty(row, col)) {
                    // Сделать ход
                    game.getBoard().setCell(row, col, game.getCurrentPlayer());

                    // Оценить ход
                    int score = minimax(game, false);

                    // Отменить ход
                    game.getBoard().setCell(row, col, 0);

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = row;
                        bestMove[1] = col;
                    }
                }
            }
        }
        return bestMove;
    }

    private int minimax(Game game, boolean isMaximizing) {

        if (isWinner(game, 1)) {
            return -1;
        }
        if (isWinner(game, 2)) {
            return 1;
        }
        if (isBoardFull(game)) {
            return 0;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (game.getBoard().isCellEmpty(row, col)) {
                        // Сделать ход
                        game.getBoard().setCell(row, col, 2); // Игрок 2 (максимизирующий)

                        // Оценить ход
                        int score = minimax(game, false);

                        // Отменить ход
                        game.getBoard().setCell(row, col, 0);

                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (game.getBoard().isCellEmpty(row, col)) {
                        // Сделать ход
                        game.getBoard().setCell(row, col, 1); // Игрок 1 (минимизирующий)

                        // Оценить ход
                        int score = minimax(game, true);

                        // Отменить ход
                        game.getBoard().setCell(row, col, 0);

                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }


    @Override
    public boolean isValidMove(Game game, GameDTO gameDTO) {
        int[][] currentField = game.getBoard().getField(); // Текущее состояние
        int[][] newField = gameDTO.getBoard().getField(); // Новое состояние

        boolean hasChanged = false;

        for (int row = 0; row < newField.length; row++) {
            for (int col = 0; col < newField.length; col++) {
                if (currentField[row][col] == 0) {
                    if (newField[row][col] != currentField[row][col] && newField[row][col] == 1) {
                        if (!hasChanged) {
                            hasChanged = true;
                        } else {
                            hasChanged = false;
                        }
                    }
                }
            }
        }
        return hasChanged;
    }

    @Override
    public boolean isBoardFull(Game game) {
        int[][] field = game.getBoard().getField();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (field[row][col] == 0) { // Если есть хотя бы одна пустая ячейка
                    return false;
                }
            }
        }
        return true; // Все ячейки заполнены
    }

    @Override
    public boolean isWinner(Game game, int player) {
        int[][] field = game.getBoard().getField();
        // Проверка строк
        for (int row = 0; row < 3; row++) {
            if (field[row][0] == player && field[row][1] == player && field[row][2] == player) {
                return true;
            }
        }
        // Проверка столбцов
        for (int col = 0; col < 3; col++) {
            if (field[0][col] == player && field[1][col] == player && field[2][col] == player) {
                return true;
            }
        }
        // Проверка диагоналей
        if (field[0][0] == player && field[1][1] == player && field[2][2] == player) {
            return true;
        }
        if (field[0][2] == player && field[1][1] == player && field[2][0] == player) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isGameOver(Game game) {
        return isWinner(game, 1) || isWinner(game, 2) || isBoardFull(game);
    }

    @Override
    @Transactional
    public void saveGame(Game game) {
        GameEntity existingEntity = gameRepository.findByGameId(game.getId());
        if (existingEntity != null) {
            // Обновляем существующую запись
            existingEntity.setBoard(BoardMapper.toEntity(game.getBoard()));
            existingEntity.setCurrentPlayer(game.getCurrentPlayer());
            gameRepository.save(existingEntity);
        } else {
            // Создаем новую запись
            GameEntity entity = GameMapper.toEntity(game);
            System.out.println("Сохраняем игру с ID: " + entity.getGameId());
            gameRepository.save(entity);
        }
    }

    @Override
    public Game getGame(String gameId) {
        GameEntity entity = gameRepository.findByGameId(gameId);
        if (entity == null) {
            return null; // Возвращаем null, если игра не найдена
        }
        return GameMapper.toDomain(entity);
    }
}
