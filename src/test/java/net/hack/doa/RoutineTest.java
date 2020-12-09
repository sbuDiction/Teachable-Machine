package net.hack.doa;

import net.hack.model.Routine;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RoutineTest {

    private final RoutineDoa routineDoa = new RoutineDoaImpl(Jdbi.create("jdbc:postgresql://localhost:5432/hacktest", "thaabit", "1234"));
    private Routine routine = new Routine(1, "Morning workout", new ArrayList<>());

    @Test
    void shouldReturnTrueWhenInsertingNewRoutine(){
        routineDoa.deleteRoutine(1);
        assertTrue(routineDoa.insertRoutine(routine));
    }

    @Test
    void shouldReturnListOfRoutines(){
        routineDoa.deleteRoutine(1);
        assertEquals(0, routineDoa.selectAllRoutines().size());
    }

    @Test
    void shouldReturnRoutineForValidId(){
        routineDoa.deleteRoutine(1);
        routineDoa.insertRoutine(routine);
        assertEquals(1, routineDoa.selectRoutineById(1).getId());
    }


    @Test
    void shouldReturnRoutineForValidName(){
        routineDoa.deleteRoutine(1);
        routineDoa.insertRoutine(routine);
        assertEquals(1, routineDoa.selectRoutineByName("Morning workout").getId());
    }

    @Test
    void shouldUpdatePoseIdLists(){
        routineDoa.deleteRoutine(1);
        routineDoa.insertRoutine(routine);
        routine.setListOfPoseIds(Arrays.asList(1, 2, 3));
        assertTrue(routineDoa.updateListOfPoseIds(routine));
    }
}
