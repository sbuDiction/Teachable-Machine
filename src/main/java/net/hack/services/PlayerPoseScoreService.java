package net.hack.services;

import net.hack.doa.PlayerPoseScoreDoa;
import net.hack.doa.PlayerPoseScoreDoaImpl;
import net.hack.model.PlayerPoseScore;

import java.util.List;

public class PlayerPoseScoreService {
    private static final PlayerPoseScoreService instance = new PlayerPoseScoreService();
    private final PlayerPoseScoreDoa playerPoseScoreDoa = new PlayerPoseScoreDoaImpl();

    public static PlayerPoseScoreService getInstance(){
        return instance;
    }

    public boolean insertPlayerPoseScore(PlayerPoseScore playerPoseScore) {
        return playerPoseScoreDoa.insertPlayerPoseScore(playerPoseScore);
    }

    public List<PlayerPoseScore> selectAllPlayerPoseScores() {
        return playerPoseScoreDoa.selectAllPlayerPoseScores();
    }

    public PlayerPoseScore selectPlayerPoseScore(int id) {
        return playerPoseScoreDoa.selectPlayerPoseScore(id);
    }

    public boolean updatePlayerPoseScore(PlayerPoseScore playerPoseScore) {
        return playerPoseScoreDoa.updatePlayerPoseScore(playerPoseScore);
    }

    public boolean deletePlayerPoseScore(int id) {
        return playerPoseScoreDoa.deletePlayerPoseScore(id);
    }
}
