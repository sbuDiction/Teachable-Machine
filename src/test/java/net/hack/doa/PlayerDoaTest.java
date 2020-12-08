package net.hack.doa;

import net.hack.model.Player;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlayerDoaTest {

    private PlayerDoa playerDoa = new PlayerDoaImpl(Jdbi.create("jdbc:postgresql://localhost:5432/hacktest", "thaabit", "1234"));
    Player playerOne = new Player(1, "Thaabit", "Jacobs", "jacobs@gmail.com");


    @Test
    void shouldReturnTrueWhenInsertingUserIntoDb(){
        playerDoa.deletePlayer(1);
        assertTrue(playerDoa.insertPlayer(playerOne));
    }

    @Test
    void shouldReturnListOfPlayers(){
        playerDoa.deletePlayer(1);
        assertEquals(0, playerDoa.selectAllPlayers().size());
    }

    @Test
    void shouldReturnPlayerForValidId(){
        playerDoa.deletePlayer(1);
        playerDoa.insertPlayer(playerOne);
        assertEquals(1, playerDoa.selectPlayer(1).getId());
    }
}
