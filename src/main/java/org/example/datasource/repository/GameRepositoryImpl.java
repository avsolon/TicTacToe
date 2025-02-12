//package org.example.datasource.repository;
//
//import org.example.datasource.model.GameEntity;
//
//
//public class GameRepositoryImpl implements GameRepository{
//    private final GameStorage gameStorage;
//
//    public GameRepositoryImpl(GameStorage gameStorage) {
//        this.gameStorage = gameStorage;
//    }
//
//    public void save(GameEntity game) {
//        gameStorage.saveGame(game);
//    }
//
//    @Override
//    public GameEntity findByGameId(String gameId) {
//        return gameStorage.getGame(gameId);
//    }
//}
