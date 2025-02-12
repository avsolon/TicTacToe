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

                    // Если текущий ход лучше, запоминаем его
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
        // Проверка на окончание игры
        if (isWinner(game, 1)) {
            return -1; // Игрок 1 (нолики) выиграл
        }
        if (isWinner(game, 2)) {
            return 1; // Игрок 2 (крестики) выиграл
        }
        if (isBoardFull(game)) {
            return 0; // Ничья
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
    public void saveGame(Game game) {
        GameEntity entity = GameMapper.toEntity(game);
        gameRepository.save(entity);
    }

    @Override
    public Game getGame(String gameId) {
        GameEntity entity = gameRepository.findByGameId(gameId);
        return GameMapper.toDomain(entity);
    }
}
