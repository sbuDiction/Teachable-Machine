package net.hack.doa;

import net.hack.mappers.RoutineMapper;
import net.hack.mappers.SessionMapper;
import net.hack.model.Session;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.List;

public class SessionDoaImpl implements SessionDoa{


    private Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/hack", "thaabit", "1234");

    public SessionDoaImpl() {
/*        try {
            jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost:5432/banking, thaabit, 1234");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/

        jdbi.registerArrayType(Integer.class, "integer");
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new SessionMapper());
    }

    public SessionDoaImpl(Jdbi jdbi){
        this.jdbi = jdbi;
        jdbi.registerArrayType(Integer.class, "integer");
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new SessionMapper());
    }

    @Override
    public boolean insertSessions(Session session) {
        return jdbi.withExtension(SessionDoa.class, doa -> doa.insertSessions(session));
    }

    @Override
    public List<Session> selectAllSessions() {
        return jdbi.withExtension(SessionDoa.class, doa -> doa.selectAllSessions());
    }

    @Override
    public Session selectSession(int id) {
        return jdbi.withExtension(SessionDoa.class, doa -> doa.selectSession(id));
    }

    @Override
    public boolean deleteSession(int id) {
        return jdbi.withExtension(SessionDoa.class, doa -> doa.deleteSession(id));
    }
}
