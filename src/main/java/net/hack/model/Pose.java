package net.hack.model;

public class Pose {

    private int id;
    private String name;

    public Pose(){}

    public Pose(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Pose setId(int id) {
        this.id = id;

        return this;
    }

    public String getName() {
        return name;
    }

    public Pose setName(String name) {
        this.name = name;

        return this;
    }
}
