package net.hack.api;

import spark.Request;
import spark.Response;
import spark.Route;

public class HackApi {

    public Route createRoutine() {
        return this::createRoutineHandle;
    }

    private Object createRoutineHandle(Request request, Response response) {
        return "hello wolrd";
    }
}
