//package org.example.di;
//
//import org.example.datasource.repository.GameRepository;
//import org.example.datasource.repository.GameRepositoryImpl;
//import org.example.datasource.repository.GameStorage;
//import org.example.domain.service.GameService;
//import org.example.domain.service.GameServiceImpl;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AppConfig {
//
//    @Bean
//    public GameStorage gameStorage() {
//        return new GameStorage();
//    }
//
//    @Bean
//    public GameRepository gameRepository(GameStorage gameStorage) {
//        return new GameRepositoryImpl(gameStorage);
//    }
//
//    @Bean
//    public GameService gameService(GameRepository gameRepository) {
//        return new GameServiceImpl(gameRepository);
//    }
//}
