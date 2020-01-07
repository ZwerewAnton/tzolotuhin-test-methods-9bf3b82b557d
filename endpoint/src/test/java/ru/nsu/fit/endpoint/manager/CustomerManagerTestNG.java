package ru.nsu.fit.endpoint.manager;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import ru.nsu.fit.endpoint.database.IDBService;
import ru.nsu.fit.endpoint.database.data.CustomerPojo;

import static org.mockito.Mockito.mock;

public class CustomerManagerTestNG {

    private IDBService dbService;
    private CustomerManager customerManager;

    private CustomerPojo createCustomerInput;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void before() {
        // create stubs for the test's class
        dbService = mock(IDBService.class);
        Logger logger = mock(Logger.class);

        // create the test's class
        customerManager = new CustomerManager(dbService, logger);
    }

    //FirstName
    @Test
    public void testCreateCustomerWithFirstNameSpace() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "Cl iff";
        createCustomerInput.lastName = "Booth";
        createCustomerInput.login = "cliffbooth@gmail.com";
        createCustomerInput.pass = "10031932cl";
        createCustomerInput.balance = 0;

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("FirstName have space.");
        customerManager.createCustomer(createCustomerInput);
    }

}
