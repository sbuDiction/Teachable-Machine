package net.hack.model;

import java.util.ArrayList;
import java.util.List;

public class Routine {
    private int id;
    private String name;
    private List<Integer> listOfPoseIds;

    public Routine(int id, String name, List<Integer> listOfPoseIds) {
        this.id = id;
        this.name = name;
        this.listOfPoseIds = new ArrayList<>(listOfPoseIds);
    }

    public int getId() {
        return id;
    }

    public Routine setId(int id) {
        this.id = id;

        return this;
    }

    public String getName() {
        return name;
    }

    public Routine setName(String name) {
        this.name = name;

        return this;
    }

    public List<Integer> getListOfPoseIds() {
        return listOfPoseIds;
    }

    public Routine setListOfPoseIds(List<Integer> listOfPoseIds) {
        this.listOfPoseIds = listOfPoseIds;

        return this;
    }
}

