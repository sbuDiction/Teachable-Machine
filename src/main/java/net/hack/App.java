package net.hack;

import net.hack.routing.AppRouting;

import static spark.Spark.staticFiles;

public class App {


    public static void main(String[] args) {
        staticFiles.location("/client");
        new AppRouting();
    }
}
