package net.hack.doa;

import net.hack.model.Routine;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface RoutineDoa {
    @SqlUpdate("insert into routines (id, name, list_pose_ids) values(:id, :name, :listOfPoseIds)")
    boolean insertRoutine(@BindBean Routine routine);

    @SqlQuery("select * from routines")
    List<Routine> selectAllRoutines();

    @SqlQuery("select * from routines where id=?")
    Routine selectRoutineById(int id);

    @SqlQuery("select * from routines where name=?")
    Routine selectRoutineByName(String name);

    @SqlUpdate("update routines set list_pose_ids=:listOfPoseIds where id=:id")
    boolean updateListOfPoseIds(@BindBean Routine routine);

    @SqlUpdate("delete from routines where id=?")
    boolean deleteRoutine(int id);


}
