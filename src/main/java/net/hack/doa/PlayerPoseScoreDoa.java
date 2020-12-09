package net.hack.doa;

import net.hack.model.PlayerPoseScore;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface PlayerPoseScoreDoa {
    @SqlUpdate("insert into player_pose_scores(id, pose_id, player_id, score) values(:id, :poseId, :playerId, :score)")
    boolean insertPlayerPoseScore(@BindBean PlayerPoseScore playerPoseScore);

    @SqlQuery("select * from player_pose_scores")
    List<PlayerPoseScore> selectAllPlayerPoseScores();

    @SqlQuery("select * from player_pose_scores where id=?")
    PlayerPoseScore selectPlayerPoseScore(int id);

    @SqlUpdate("update player_pose_scores set score=:score where id=:id")
    boolean updatePlayerPoseScore(@BindBean PlayerPoseScore playerPoseScore);

    @SqlUpdate("delete from player_pose_scores where id=?")
    boolean deletePlayerPoseScore(int id);
}
