package net.hack.doa;

import net.hack.mappers.PlayerPoseScoreMapper;
import net.hack.mappers.PoseMapper;
import net.hack.model.PlayerPoseScore;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.List;

public class PlayerPoseScoreDoaImpl implements PlayerPoseScoreDoa{
    private Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/hack", "codex", "codex123");

    public PlayerPoseScoreDoaImpl() {
/*        try {
            jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost:5432/banking, thaabit, 1234");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
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
}
