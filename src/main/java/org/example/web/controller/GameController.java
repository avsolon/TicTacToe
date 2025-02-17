package org.example.web.controller;

import org.example.domain.model.Game;
import org.example.domain.service.GameService;
import org.example.domain.service.GameServiceImpl;
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Логирование входящих данных
        System.out.println("Обновление игры с ID: " + id + " с данными: " + gameDTO);

        try {
            // Проверка, что текущий игрок соответствует ожидаемому значению
            if (game.getCurrentPlayer() != gameDTO.getCurrentPlayer()) {
                return ResponseEntity.badRequest().body(null);
            }

            // Обновление игрового поля
            game.setBoard(BoardMapper.toDomain(gameDTO.getBoard())); // Обновляем поле



            // Проверка, заполнено ли поле
            if (gameService.isBoardFull(game)) {
                // Если поле заполнено, можно вернуть статус или выполнить другую логику
                System.out.println("Поле заполнено.");
                return ResponseEntity.ok(GameMapper.toDTO(game));
            }

            // Переключить игрока
            game.switchPlayer();

            // Ход компьютера (игрока 2)
            if (game.getCurrentPlayer() == 2) {
                int[] nextMove = gameService.getNextMove(game);
                if (nextMove[0] != -1 && nextMove[1] != -1) {
                    game.getBoard().setCell(nextMove[0], nextMove[1], 2); // Устанавливаем ход компьютера
                    System.out.println("Компьютер сделал ход в ячейку: " + nextMove[0] + ", " + nextMove[1]);
                    game.switchPlayer();
                }
            }

            // Сохранить обновленное состояние игры
            gameService.saveGame(game);



            if (gameService.isWinner(game, 1)) {
                System.out.println("Победа игрока 1.");
                return ResponseEntity.ok(GameMapper.toDTO(game));

                //return -1; // Игрок 1 выиграл
            }
            if (gameService.isWinner(game, 2)) {
                System.out.println("Победа игрока 2.");
                return ResponseEntity.ok(GameMapper.toDTO(game));
                //return 1; // Игрок 2 выиграл
            }
            if (gameService.isBoardFull(game)) {
                System.out.println("Ничья");
                return ResponseEntity.ok(GameMapper.toDTO(game));
                //return 0; // Ничья
            }


            return ResponseEntity.ok(GameMapper.toDTO(game));


        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при обновлении игры: " + e.getMessage());
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            System.out.println("Неизвестная ошибка: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



}
