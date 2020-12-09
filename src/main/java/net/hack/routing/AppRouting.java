package net.hack.routing;

import net.hack.api.HackApi;

import static spark.Spark.*;


public class AppRouting {

    public AppRouting(){
        get("/add/routine", new HackApi().createRoutine());
    }
}
