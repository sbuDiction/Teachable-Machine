package net.hack.services;

import net.hack.doa.PoseDoa;
import net.hack.doa.PoseDoaImpl;
import net.hack.model.Pose;

import java.util.List;

public class PoseService {

    private static final PoseService instance = new PoseService();
    private final PoseDoa poseDoa = new PoseDoaImpl();

    public static PoseService getInstance(){
        return instance;
    }

    public boolean insertPose(Pose pose){
        return poseDoa.insertPose(pose);
    }

    public Pose selectPose(int id){
        return poseDoa.selectPose(id);
    }

    public Pose selectPose(String name){
        return poseDoa.selectPose(name);
    }

    public List<Pose> selectAllPoses(){
        return poseDoa.selectAllPoses();
    }

    public boolean deletePose(int id){
        return poseDoa.deletePose(id);
    }
}
