package net.hack.doa;

import net.hack.mappers.PlayerMapper;
import net.hack.mappers.PoseMapper;
import net.hack.model.Pose;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

public class PoseDoaImpl implements PoseDoa{
    private Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/hack", "codex", "codex123");

    public PoseDoaImpl() {
        try {
            jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost:5432/hack, codex, codex123");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new PoseMapper());
    }

    public PoseDoaImpl(Jdbi jdbi){
        this.jdbi = jdbi;
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new PoseMapper());
    }

    @Override
    public boolean insertPose(Pose pose) {
        return jdbi.withExtension(PoseDoa.class, doa -> doa.insertPose(pose));
    }

    @Override
    public List<Pose> selectAllPoses() {
        return jdbi.withExtension(PoseDoa.class, doa -> doa.selectAllPoses());
    }

    @Override
    public Pose selectPose(int id) {
        return jdbi.withExtension(PoseDoa.class, doa -> doa.selectPose(id));
    }

    @Override
    public Pose selectPose(String name) {
        return jdbi.withExtension(PoseDoa.class, doa -> doa.selectPose(name));
    }

    @Override
    public boolean deletePose(int id) {
        return jdbi.withExtension(PoseDoa.class, doa -> doa.deletePose(id));
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
