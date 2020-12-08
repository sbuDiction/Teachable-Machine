package net.hack.doa;

import net.hack.model.Pose;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PoseDoaTest {

    private final PoseDoa poseDoa = new PoseDoaImpl(Jdbi.create("jdbc:postgresql://localhost:5432/hacktest", "thaabit", "1234"));
    private Pose poseOne = new Pose(1, "Headstand");

    @Test
    void shouldReturnTRueWhenInsertingposeIntoDb(){
        poseDoa.deletePose(1);
        assertTrue(poseDoa.insertPose(poseOne));
    }

    @Test
    void shouldReturnALIstOfPoses(){
        poseDoa.deletePose(1);
        assertEquals(0, poseDoa.selectAllPoses().size());
    }

    @Test
    void shouldReturnPOseForValidId(){
        poseDoa.deletePose(1);
        poseDoa.insertPose(poseOne);
        assertEquals(1, poseDoa.selectPose(1).getId());
    }
}
