package net.hack.mappers;

import net.hack.model.Player;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PlayerMapper implements RowMapper<Player> {
    @Override
    public Player map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Player(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"));
    }
}
