package org.example.datasource.repository;

import org.example.datasource.model.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {
    GameEntity findByGameId(String gameId); // Метод для поиска по gameId
}


//    void save(GameEntity game);

