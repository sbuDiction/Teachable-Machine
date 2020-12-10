package net.hack.doa;

import net.hack.mappers.PoseMapper;
import net.hack.mappers.RoutineMapper;
import net.hack.model.Routine;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.List;

public class RoutineDoaImpl implements RoutineDoa{

    private Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/hack", "codex", "codex123");

    public RoutineDoaImpl() {
/*        try {
            jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost:5432/banking, thaabit, 1234");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
        jdbi.registerArrayType(Integer.class, "integer");
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new RoutineMapper());
    }

    public RoutineDoaImpl(Jdbi jdbi){
        this.jdbi = jdbi;
        jdbi.registerArrayType(Integer.class, "integer");
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new RoutineMapper());
    }


    @Override
    public boolean insertRoutine(Routine routine) {
        return jdbi.withExtension(RoutineDoa.class, doa -> doa.insertRoutine(routine));
    }

    @Override
    public List<Routine> selectAllRoutines() {
        return jdbi.withExtension(RoutineDoa.class, doa -> doa.selectAllRoutines());
    }

    @Override
    public Routine selectRoutineById(int id) {
        return jdbi.withExtension(RoutineDoa.class, doa -> doa.selectRoutineById(id));
    }

    @Override
    public Routine selectRoutineByName(String name) {
        return jdbi.withExtension(RoutineDoa.class, doa -> doa.selectRoutineByName(name));
    }

    @Override
    public boolean updateListOfPoseIds(Routine routine) {
        return jdbi.withExtension(RoutineDoa.class, doa -> doa.updateListOfPoseIds(routine));
    }

    @Override
    public boolean deleteRoutine(int id) {
        return jdbi.withExtension(RoutineDoa.class, doa -> doa.deleteRoutine(id));
    }
}
