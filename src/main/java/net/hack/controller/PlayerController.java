package net.hack.controller;

import net.hack.model.Player;
import net.hack.model.Routine;
import net.hack.services.PlayerService;
import net.hack.services.RoutineService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static  spark.Spark.*;

public class PlayerController {
    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public PlayerController(PlayerService playerService, RoutineService routineService){
        get("/player/:id", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int playerId = Integer.parseInt(request.params("id"));
            List<Routine> routineList = RoutineService.getInstance().selectAllRoutines();
            Player player = PlayerService.getInstance().selectPlayer(playerId);

            model.put("routineList", routineList);
            model.put("playerName", player.getFirstName() + " " + player.getLastName());
            model.put("playerId", player.getId());
            return render(model, "index.hbs");
        }));

        get("/player/:id/create/routine", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int playerId = Integer.parseInt(request.params("id"));

            return render(model, "addRoutineForm.hbs");
        }));
    }
}

