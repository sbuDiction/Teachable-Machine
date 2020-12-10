package net.hack.doa;

import net.hack.mappers.PoseMapper;
import net.hack.mappers.RoutineMapper;
import net.hack.model.Routine;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

public class RoutineDoaImpl implements RoutineDoa{

    private Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/hack", "codex", "codex123");

    public RoutineDoaImpl() {
        try {
            jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost:5432/hack, codex, codex123");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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

    static Jdbi getJdbiDatabaseConnection(String defualtJdbcUrl) throws URISyntaxException, SQLException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        String database_url = processBuilder.environment().get("DATABASE_URL");
        if (database_url != null) {

            URI uri = new URI(database_url);
            String[] hostParts = uri.getUserInfo().split(":");
            String username = hostParts[0];
            String password = hostParts[1];
            String host = uri.getHost();

            int port = uri.getPort();

            String path = uri.getPath();
            String url = String.format("jdbc:postgresql://%s:%s%s", host, port, path);

            return Jdbi.create(url, username, password);
        }

        return Jdbi.create(defualtJdbcUrl);

    }
}
