package net.hack.model;

public class Session {
    private int id;
    private int playerPoseScoresId;

    public Session(){}

    public Session(int id, int playerPoseScoresId) {
        this.id = id;
        this.playerPoseScoresId = playerPoseScoresId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerPoseScoresId() {
        return playerPoseScoresId;
    }

    public void setPlayerPoseScoresId(int playerPoseScoresId) {
        this.playerPoseScoresId = playerPoseScoresId;
    }
}

