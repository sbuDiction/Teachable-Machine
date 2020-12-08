package net.hack.doa;

import net.hack.mappers.PlayerMapper;
import net.hack.model.Player;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.List;

public class PlayerDoaImpl implements PlayerDoa{
    private Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/hack", "thaabit", "1234");

    public PlayerDoaImpl() {
/*        try {
            jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost:5432/banking, thaabit, 1234");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new PlayerMapper());
    }

    public PlayerDoaImpl(Jdbi jdbi){
        this.jdbi = jdbi;
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new PlayerMapper());
    }

    @Override
    public boolean insertPlayer(Player player) {
        return jdbi.withExtension(PlayerDoa.class, doa -> doa.insertPlayer(player));
    }

    @Override
    public List<Player> selectAllPlayers() {
        return jdbi.withExtension(PlayerDoa.class, doa -> doa.selectAllPlayers());
    }

    @Override
    public Player selectPlayer(int id) {
        return jdbi.withExtension(PlayerDoa.class, doa -> doa.selectPlayer(id));
    }

    @Override
    public boolean deletePlayer(int id) {
        return jdbi.withExtension(PlayerDoa.class, doa -> doa.deletePlayer(id));
    }
}
