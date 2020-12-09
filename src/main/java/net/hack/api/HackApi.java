package net.hack.api;

import net.hack.model.Player;
import net.hack.model.Routine;
import net.hack.services.PlayerService;
import net.hack.services.RoutineService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HackApi {

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public Route createRoutine() {
        return this::createRoutineHandle;
    }

    private Object createRoutineHandle(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();

        int playerId = Integer.parseInt(request.params("id"));
        List<Routine> routineList = RoutineService.getInstance().selectAllRoutines();
        Player player = PlayerService.getInstance().selectPlayer(playerId);

        model.put("routineList", routineList);
        model.put("playerName", player.getFirstName() + " " + player.getLastName());
        return render(model, "index.hbs");
    }
}
