package net.hack.doa;

import net.hack.model.Pose;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface PoseDoa {
    @SqlUpdate("insert into poses (id, name) values(:id, :name)")
    boolean insertPose(@BindBean Pose pose);

    @SqlQuery("select * from poses order by id asc")
    List<Pose> selectAllPoses();

    @SqlQuery("select * from poses where id=?")
    Pose selectPose(int id);

    @SqlUpdate("delete from poses where id=?")
    boolean deletePose(int id);
}
