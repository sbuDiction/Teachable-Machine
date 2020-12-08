package net.hack.doa;

import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import net.hack.model.*;

import java.util.List;

public interface SessionDoa {

    @SqlUpdate("insert into sessions (id, player_pose_scores_id) values(:id, :playerPoseScoresId)")
    boolean insertSessions(@BindBean Session session);

    @SqlQuery("select * from sessions")
    List<Session> selectAllSessions();

    @SqlQuery("select * from sessions where id=?")
    Session selectSession(int id);

    @SqlUpdate("delete from sessions where id=?")
    boolean deleteSession(int id);
}
