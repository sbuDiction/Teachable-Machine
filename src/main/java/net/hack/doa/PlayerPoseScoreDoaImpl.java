package net.hack.doa;

import net.hack.mappers.PlayerPoseScoreMapper;
import net.hack.mappers.PoseMapper;
import net.hack.model.PlayerPoseScore;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

public class PlayerPoseScoreDoaImpl implements PlayerPoseScoreDoa{
    private Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/hack", "codex", "codex123");

    public PlayerPoseScoreDoaImpl() {
        try {
            jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost:5432/hack, codex, codex123");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new PlayerPoseScoreMapper());
    }

    public PlayerPoseScoreDoaImpl(Jdbi jdbi){
        this.jdbi = jdbi;
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin());
        jdbi.registerRowMapper(new PlayerPoseScoreMapper());
    }

    @Override
    public boolean insertPlayerPoseScore(PlayerPoseScore playerPoseScore) {
        return jdbi.withExtension(PlayerPoseScoreDoa.class, doa -> doa.insertPlayerPoseScore(playerPoseScore));
    }

    @Override
    public List<PlayerPoseScore> selectAllPlayerPoseScores() {
        return jdbi.withExtension(PlayerPoseScoreDoa.class, doa -> doa.selectAllPlayerPoseScores());
    }

    @Override
    public PlayerPoseScore selectPlayerPoseScore(int id) {
        return jdbi.withExtension(PlayerPoseScoreDoa.class, doa -> doa.selectPlayerPoseScore(id));
    }

    @Override
    public boolean updatePlayerPoseScore(PlayerPoseScore playerPoseScore) {
        return jdbi.withExtension(PlayerPoseScoreDoa.class, doa -> doa.updatePlayerPoseScore(playerPoseScore));
    }

    @Override
    public boolean deletePlayerPoseScore(int id) {
        return jdbi.withExtension(PlayerPoseScoreDoa.class, doa -> doa.deletePlayerPoseScore(id));
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
