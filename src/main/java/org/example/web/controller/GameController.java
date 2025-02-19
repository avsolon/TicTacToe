package org.example.web.controller;

import org.example.domain.model.Game;
import org.example.domain.service.GameService;
import org.example.web.model.GameDTO;
import org.example.web.mapper.BoardMapper;
import org.example.web.mapper.GameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<GameDTO> createGame() {
        Game game = new Game();
        gameService.saveGame(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(GameMapper.toDTO(game));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> getGame(@PathVariable String id) {
        Game game = gameService.getGame(id);
        if (game == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(GameMapper.toDTO(game));
    }

    @PostMapping("/{id}")
    public ResponseEntity<GameDTO> updateGame(@PathVariable String id, @RequestBody GameDTO gameDTO) {
        Game game = gameService.getGame(id);
        if (game == null) {
            System.out.println("Несуществующая игра");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Логирование входящих данных
        System.out.println("Обновление игры с ID: " + id + " с данными: " + gameDTO);

        try {
            //Проверка на победителя
            if (gameService.isWinner(game, 1)) {
                System.out.println("Победа игрока 1!");
                gameDTO.setWinner(1);
                gameService.saveGame(game);
                return ResponseEntity.ok(gameDTO); // Завершаем игру и возвращаем результат
            } else if (gameService.isWinner(game, 2)) {
                System.out.println("Победа игрока 2!");
                gameDTO.setWinner(2);
                gameService.saveGame(game);
                return ResponseEntity.ok(gameDTO); // Завершаем игру и возвращаем результат
            }

            // Проверка, что текущий игрок соответствует ожидаемому значению
            if (game.getCurrentPlayer() != gameDTO.getCurrentPlayer()) {
                System.out.println("Неверный игрок");
                return ResponseEntity.badRequest().body(null);
            }

            // Проверка на валидность хода
            if (!gameService.isValidMove(game, gameDTO)) {
                System.out.println("Невалидный ход");
                return ResponseEntity.badRequest().body(null);
            }

            // Обновление игрового поля
            game.setBoard(BoardMapper.toDomain(gameDTO.getBoard()));

                // Проверка, заполнено ли поле
                if (gameService.isBoardFull(game)) {
                    System.out.println("Поле заполнено.");
                    return ResponseEntity.ok(gameDTO);
                }

                // Переключение игрока
                game.switchPlayer();

                // Ход компьютера (игрока 2 - нолики)
                if (game.getCurrentPlayer() == 2) {
                    int[] nextMove = gameService.getNextMove(game);
                    if (nextMove[0] != -1 && nextMove[1] != -1) {
                        game.getBoard().setCell(nextMove[0], nextMove[1], 2);
                        System.out.println("Компьютер сделал ход в ячейку: " + nextMove[0] + ", " + nextMove[1]);
                        game.switchPlayer();
                    }
                }

            // Проверка на победителя
            if (gameService.isWinner(game, 1)) {
                System.out.println("Победа игрока 1!");
                gameDTO.setWinner(1);
                gameService.saveGame(game);
                return ResponseEntity.ok(gameDTO);
            } else if (gameService.isWinner(game, 2)) {
                System.out.println("Победа игрока 2!");
                gameDTO.setWinner(2);
                gameService.saveGame(game);
                return ResponseEntity.ok(gameDTO);
            }

                // Логирование состояния игры перед сохранением
                System.out.println("Состояние игры перед сохранением: " + game);

                // Сохранение обновленного состояния
                gameService.saveGame(game);

                // Проверка на GameOver
                if (gameService.isGameOver(game)) {
                    System.out.println("Игра завершена!");
                    return ResponseEntity.ok(gameDTO);
                }

                // Возвращаем обновленный объект GameDTO
                return ResponseEntity.ok(gameDTO);

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при обновлении игры: " + e.getMessage());
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            System.out.println("Неизвестная ошибка: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



}
