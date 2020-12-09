package net.hack.mappers;

import net.hack.model.Routine;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class RoutineMapper implements RowMapper<Routine> {
    @Override
    public Routine map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Routine(rs.getInt("id"), rs.getString("name"), Arrays.asList((Integer[])rs.getArray("list_pose_ids").getArray()));
    }
}
