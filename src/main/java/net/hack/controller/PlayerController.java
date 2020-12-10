package net.hack.controller;

import com.google.gson.Gson;
import net.hack.mappers.GetAxiosData;
import net.hack.model.Player;
import net.hack.model.PlayerPoseScore;
import net.hack.model.Pose;
import net.hack.model.Routine;
import net.hack.services.PlayerPoseScoreService;
import net.hack.services.PlayerService;
import net.hack.services.PoseService;
import net.hack.services.RoutineService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class PlayerController {
    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public PlayerController(PlayerService playerService, RoutineService routineService) {
        get("/player/:id", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

/*            int playerId = Integer.parseInt(request.params("id"));
            List<Routine> routineList = RoutineService.getInstance().selectAllRoutines();
            Player player = PlayerService.getInstance().selectPlayer(playerId);

            model.put("routineList", routineList);
            model.put("playerName", player.getFirstName() + " " + player.getLastName());
            model.put("playerId", player.getId());*/
            return render(model, "index.hbs");
        }));

        get("/player/:id/create/routine", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int playerId = Integer.parseInt(request.params("id"));
            List<Pose> poseList = PoseService.getInstance().selectAllPoses();

            model.put("poseLIst", poseList);
            return render(model, "createRoutineForm.hbs");
        }));

        post("/api/sendUser/", (request, response) -> {
            GetAxiosData data = new Gson().fromJson(request.body(), GetAxiosData.class);
            System.out.printf(data.getName() + " " + data.getAverage());
//            PoseService.getInstance().insertPose(new Pose(3, data.getName()));
            PlayerPoseScoreService.getInstance().insertPlayerPoseScore(
                    new PlayerPoseScore(1,
                            1,
                            PoseService.getInstance()
                                    .selectPose(data
                                            .getName()).getId(), Integer.parseInt(data.getAverage())));

            response.redirect("/");
            return "success";
        });

        get("/stats", (request, response) -> {
            Map<String, Object> map = new HashMap<>();

            List<PlayerPoseScore> playerPoseScoreList = PlayerPoseScoreService.getInstance().selectAllPlayerPoseScores();
            List<Integer> scoreList = new ArrayList<>();

            playerPoseScoreList.forEach(a -> scoreList.add(a.getScore()));
            map.put("data", scoreList);
            return render(map, "stats.hbs");
        });
    }
}


