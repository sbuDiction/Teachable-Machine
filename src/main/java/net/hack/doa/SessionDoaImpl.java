package net.hack.doa;

import net.hack.mappers.RoutineMapper;
import net.hack.mappers.SessionMapper;
import net.hack.model.Session;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

public class SessionDoaImpl implements SessionDoa{


    private Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/hack", "codex", "coder123");

    public SessionDoaImpl() {
        try {
            jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost:5432/hack, codex, coder123");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

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
