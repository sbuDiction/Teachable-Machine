package net.hack.services;

import net.hack.doa.PlayerDoa;
import net.hack.doa.PlayerDoaImpl;
import net.hack.model.Player;

import java.util.List;

public class PlayerService {

    private static final PlayerService instance = new PlayerService();
    private final PlayerDoa playerDoa = new PlayerDoaImpl();

    public static PlayerService getInstance(){
        return instance;
    }

    public boolean insertPlayer(Player player) {
        return playerDoa.insertPlayer(player);
    }

    public List<Player> selectAllPlayers() {
        return playerDoa.selectAllPlayers();
    }

    public Player selectPlayer(int id) {
        return playerDoa.selectPlayer(id);
    }

    public boolean deletePlayer(int id) {
        return playerDoa.deletePlayer(id);
    }
}
