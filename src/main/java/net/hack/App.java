package net.hack;

import net.hack.controller.PlayerController;
import net.hack.model.Player;
import net.hack.model.Pose;
import net.hack.model.Routine;
import net.hack.routing.AppRouting;
import net.hack.services.PlayerService;
import net.hack.services.PoseService;
import net.hack.services.RoutineService;

import java.util.ArrayList;

import static spark.Spark.staticFiles;

public class App {


    public static void main(String[] args) {

        staticFiles.location("/client");
        /*new AppRouting();*/
/*
        PoseService.getInstance().insertPose(new Pose(2, "lion"));
        PoseService.getInstance().insertPose(new Pose(3, "standing"));
        PoseService.getInstance().insertPose(new Pose(4, "seated"));*/
        new PlayerController(PlayerService.getInstance(), RoutineService.getInstance());

//       PlayerService.getInstance().insertPlayer(new Player(1,"Thaabit", "Jacobs", "jacobs@gmail.com"));
//       RoutineService.getInstance().insertRoutine(new Routine(1,"sbu",new ArrayList<>()));
    }

}
