package net.hack.mappers;

import org.jdbi.v3.core.mapper.RowMapper;
import net.hack.model.*;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionMapper implements RowMapper<Session> {
    @Override
    public Session map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Session(rs.getInt("id"), rs.getInt("player_pose_scores_id"));
    }
}
