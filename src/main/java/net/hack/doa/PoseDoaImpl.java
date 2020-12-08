package net.hack.doa;

import net.hack.mappers.PlayerMapper;
import net.hack.mappers.PoseMapper;
import net.hack.model.Pose;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.List;

public class PoseDoaImpl implements PoseDoa{
    private Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/hack", "thaabit", "1234");

    public PoseDoaImpl() {
/*        try {
            jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost:5432/banking, thaabit, 1234");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
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
    public boolean deletePose(int id) {
        return jdbi.withExtension(PoseDoa.class, doa -> doa.deletePose(id));
    }
}
