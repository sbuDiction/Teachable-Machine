package net.hack.routing;

import net.hack.api.HackApi;

import static spark.Spark.*;


public class AppRouting {

    private final HackApi hackApi = new HackApi();


    public AppRouting(){
        get("/", hackApi.createRoutine());
    }
}
