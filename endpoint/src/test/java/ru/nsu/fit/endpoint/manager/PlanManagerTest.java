package ru.nsu.fit.endpoint.manager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import ru.nsu.fit.endpoint.database.IDBService;
import ru.nsu.fit.endpoint.database.data.CustomerPojo;
import ru.nsu.fit.endpoint.database.data.PlanPojo;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class PlanManagerTest {

    private IDBService dbService;
    private PlanManager planManager;

    private PlanPojo createPlanInput;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void before() {
        // create stubs for the test's class
        dbService = mock(IDBService.class);
        Logger logger = mock(Logger.class);

        // create the test's class
        planManager = new PlanManager(dbService, logger);
    }


    //Name
    @Test
    public void testCreatePlanWithShortName() {
        createPlanInput = new PlanPojo();
        createPlanInput.details = "abcdefg";
        createPlanInput.name = "N";
        createPlanInput.fee = 0;

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Name is bigger than 128 or less than 2.");
        planManager.createPlan(createPlanInput);
    }

    @Test
    public void testCreatePlanWithLongName() {
        createPlanInput = new PlanPojo();
        createPlanInput.details = "abcdefg";
        createPlanInput.name = "ThisNameISBiggerThan128ThisNameISBiggerThan128ThisNameISBiggerThan128" +
                "ThisNameISBiggerThan128ThisNameISBiggerThan128ThisNameISBiggerThan128ThisNameISBiggerThan128";
        createPlanInput.fee = 0;

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Name is bigger than 128 or less than 2.");
        planManager.createPlan(createPlanInput);
    }

    @Test
    public void testCreatePlanWithNameWithSpecialCharacter() {
        createPlanInput = new PlanPojo();
        createPlanInput.details = "abcdefg";
        createPlanInput.name = "Name$$$";
        createPlanInput.fee = 0;

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Name contains a special character.");
        planManager.createPlan(createPlanInput);
    }

    @Test
    public void createPlan() throws Exception {
    }

}