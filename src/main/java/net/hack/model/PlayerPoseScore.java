package net.hack.model;

public class PlayerPoseScore {
    private int id;
    private int poseId;
    private int playerId;
    private int score;

    public PlayerPoseScore() {
    }

    public PlayerPoseScore(int id, int pose_id, int player_id, int score) {
        this.id = id;
        this.poseId = pose_id;
        this.playerId = player_id;
        this.score  = score;
    }

    public int getId() {
        return id;
    }

    public PlayerPoseScore setId(int id) {
        this.id = id;

        return this;
    }

    public int getPoseId() {
        return poseId;
    }

    public PlayerPoseScore setPoseId(int pose_id) {
        this.poseId= pose_id;

        return this;
    }

    public int getPlayerId() {
        return playerId;
    }

    public PlayerPoseScore setPlayerId(int player_id) {
        this.playerId = player_id;

        return this;
    }
}
