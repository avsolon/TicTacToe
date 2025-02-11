package org.example;

import org.example.datasource.repository.GameRepository;
import org.example.datasource.repository.GameRepositoryImpl;
import org.example.datasource.repository.GameStorage;
import org.example.domain.service.GameService;
import org.example.domain.service.GameServiceImpl;

public class Main {
    public static void main(String[] args) {
        GameStorage gameStorage = new GameStorage();
        GameRepository gameRepository = new GameRepositoryImpl(gameStorage);
        GameService gameService = new GameServiceImpl(gameRepository);

        // ...
    }
}