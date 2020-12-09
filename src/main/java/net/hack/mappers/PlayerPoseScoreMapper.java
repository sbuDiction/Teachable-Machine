package net.hack.mappers;

import net.hack.model.PlayerPoseScore;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerPoseScoreMapper implements RowMapper<PlayerPoseScore> {
    @Override
    public PlayerPoseScore map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new PlayerPoseScore(rs.getInt("id"), rs.getInt("player_id"), rs.getInt("pose_id"), rs.getInt("score"));
    }
}
