package org.example.web.controller;

import org.example.domain.model.Game;
import org.example.domain.service.GameService;
import org.example.domain.service.GameServiceImpl;
import org.example.web.model.GameDTO;
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

        // Обновление состояния игры на основе данных из gameDTO
        try {
            // Здесь должна быть логика валидации и обновления игрового поля
            // Например, проверка, что текущий ход соответствует состоянию поля
            if (!gameService.isBoardFull(game)) {
                return ResponseEntity.badRequest().body(null);
            }

            // Обновление игрового поля
            // game.getBoard().setCell(...); // Установить новое состояние на основе gameDTO

            // Переключить игрока
            game.switchPlayer();

            // Сохранить обновленное состояние игры
            gameService.saveGame(game);

            return ResponseEntity.ok(GameMapper.toDTO(game));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
