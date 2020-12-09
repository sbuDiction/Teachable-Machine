package net.hack.services;

import net.hack.doa.RoutineDoa;
import net.hack.doa.RoutineDoaImpl;
import net.hack.model.Routine;

import java.util.List;

public class RoutineService {
    private static final RoutineService instance = new RoutineService();
    private final RoutineDoaImpl routineDoa = new RoutineDoaImpl();

    public static RoutineService getInstance(){
        return instance;
    }

    public boolean insertRoutine(Routine routine) {
        return routineDoa.insertRoutine(routine);
    }

    public List<Routine> selectAllRoutines() {
        return routineDoa.selectAllRoutines();
    }

    public Routine selectRoutineById(int id) {
        return routineDoa.selectRoutineById(id);
    }

    public Routine selectRoutineByName(String name) {
        return routineDoa.selectRoutineByName(name);
    }

    public boolean deleteRoutine(int id) {
        return routineDoa.deleteRoutine(id);
    }

    public boolean updateListOfPoseIds(Routine routine) {
        return routineDoa.updateListOfPoseIds(routine);
    }

    public List<Integer> selectAllPosesIdsForRoutine(int id){
        return selectRoutineById(id).getListOfPoseIds();
    }
}
