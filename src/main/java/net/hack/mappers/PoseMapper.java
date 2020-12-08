package net.hack.mappers;

import net.hack.doa.PoseDoa;
import net.hack.model.Pose;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PoseMapper implements RowMapper<Pose> {
    @Override
    public Pose map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Pose(rs.getInt("id"), rs.getString("name"));
    }
}
