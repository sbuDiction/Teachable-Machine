package net.hack.doa;

import net.hack.model.Player;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface PlayerDoa {
    @SqlUpdate("insert into players (id, first_name, last_name, email) values (:id, :firstName, :lastName, :email)")
    boolean insertPlayer(@BindBean Player player);

    @SqlQuery("select * from players order by id asc")
    List<Player> selectAllPlayers();

    @SqlQuery("select * from players where id=?")
    Player selectPlayer(int id);

    @SqlUpdate("delete from players where id=?")
    boolean deletePlayer(int id);
}
