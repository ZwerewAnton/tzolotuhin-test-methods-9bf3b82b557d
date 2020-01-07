package ru.nsu.fit.endpoint.manager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import ru.nsu.fit.endpoint.database.IDBService;
import ru.nsu.fit.endpoint.database.data.CustomerPojo;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class CustomerManagerTest {
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

    @Test
    public void testCreateCustomerWithFirstNameLowercase() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "cliff";
        createCustomerInput.lastName = "Booth";
        createCustomerInput.login = "cliffbooth@gmail.com";
        createCustomerInput.pass = "10031932cl";
        createCustomerInput.balance = 0;

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("The first character FirstName is lowercase.");
        customerManager.createCustomer(createCustomerInput);
    }

    @Test
    public void testCreateCustomerWithShortFirstName() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "C";
        createCustomerInput.lastName = "Booth";
        createCustomerInput.login = "cliffbooth@gmail.com";
        createCustomerInput.pass = "10031932cl";
        createCustomerInput.balance = 0;

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("FirstName's length should be more or equal 6 symbols and less or equal 12 symbols.");
        customerManager.createCustomer(createCustomerInput);
    }

    @Test
    public void testCreateCustomerWithLongFirstName() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "Iamcliffbooth";
        createCustomerInput.lastName = "Booth";
        createCustomerInput.login = "cliffbooth@gmail.com";
        createCustomerInput.pass = "10031932cl";
        createCustomerInput.balance = 0;

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("FirstName's length should be more or equal 6 symbols and less or equal 12 symbols.");
        customerManager.createCustomer(createCustomerInput);
    }

    @Test
    public void testCreateCustomerWithManyUpperCaseInFirstName() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "ClIfF";
        createCustomerInput.lastName = "Booth";
        createCustomerInput.login = "cliffbooth@gmail.com";
        createCustomerInput.pass = "10031932cl";
        createCustomerInput.balance = 0;

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Two or more characters FirstName is uppercase or not letter.");
        customerManager.createCustomer(createCustomerInput);
    }

    @Test
    public void testCreateCustomerWithWrongCharacterInFirsName() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "Cliff1932#";
        createCustomerInput.lastName = "Booth";
        createCustomerInput.login = "cliffbooth@gmail.com";
        createCustomerInput.pass = "10031932cl";
        createCustomerInput.balance = 0;

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Two or more characters FirstName is uppercase or not letter.");
        customerManager.createCustomer(createCustomerInput);
    }

    //LastName
    @Test
    public void testCreateCustomerWithLastNameSpace() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "Cliff";
        createCustomerInput.lastName = "Bo oth";
        createCustomerInput.login = "cliffbooth@gmail.com";
        createCustomerInput.pass = "10031932cl";
        createCustomerInput.balance = 0;

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("LastName have space.");
        customerManager.createCustomer(createCustomerInput);
    }

    @Test
    public void testCreateCustomerWithLastNameLowercase() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "Cliff";
        createCustomerInput.lastName = "booth";
        createCustomerInput.login = "cliffbooth@gmail.com";
        createCustomerInput.pass = "10031932cl";
        createCustomerInput.balance = 0;

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("The first character LastName is lowercase.");
        customerManager.createCustomer(createCustomerInput);
    }

    @Test
    public void testCreateCustomerWithShortLastName() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "Cliff";
        createCustomerInput.lastName = "B";
        createCustomerInput.login = "cliffbooth@gmail.com";
        createCustomerInput.pass = "10031932cl";
        createCustomerInput.balance = 0;

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("LastName's length should be more or equal 6 symbols and less or equal 12 symbols.");
        customerManager.createCustomer(createCustomerInput);
    }

    @Test
    public void testCreateCustomerWithLongLastName() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "Cliff";
        createCustomerInput.lastName = "Iamcliffbooth";
        createCustomerInput.login = "cliffbooth@gmail.com";
        createCustomerInput.pass = "10031932cl";
        createCustomerInput.balance = 0;

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("LastName's length should be more or equal 6 symbols and less or equal 12 symbols.");
        customerManager.createCustomer(createCustomerInput);
    }

    @Test
    public void testCreateCustomerWithManyUpperCaseInLastName() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "Cliff";
        createCustomerInput.lastName = "BoOtH";
        createCustomerInput.login = "cliffbooth@gmail.com";
        createCustomerInput.pass = "10031932cl";
        createCustomerInput.balance = 0;

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Two or more characters LastName is uppercase or not letter.");
        customerManager.createCustomer(createCustomerInput);
    }

    @Test
    public void testCreateCustomerWithWrongCharacterInLastName() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "Cliff";
        createCustomerInput.lastName = "Booth1932#";
        createCustomerInput.login = "cliffbooth@gmail.com";
        createCustomerInput.pass = "10031932cl";
        createCustomerInput.balance = 0;

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Two or more characters LastName is uppercase or not letter.");
        customerManager.createCustomer(createCustomerInput);
    }

    //Email
    @Test
    public void testCreateCustomerWithWrongEmail() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "Cliff";
        createCustomerInput.lastName = "Booth";
        createCustomerInput.login = "cliffboothgmailcom";
        createCustomerInput.pass = "10031932cl";
        createCustomerInput.balance = 0;

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Email is not correct.");
        customerManager.createCustomer(createCustomerInput);
    }

    //Password
    @Test
    public void testCreateCustomerWithShortPassword() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "Cliff";
        createCustomerInput.lastName = "Booth";
        createCustomerInput.login = "cliffbooth@gmail.com";
        createCustomerInput.pass = "123qwe";
        createCustomerInput.balance = 0;

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Password is easy.");
        customerManager.createCustomer(createCustomerInput);
    }

    @Test
    public void testCreateCustomerWithPasswordWithPartOfFirstName() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "Cliff";
        createCustomerInput.lastName = "Booth";
        createCustomerInput.login = "cliffbooth@gmail.com";
        createCustomerInput.pass = "1003Cliff";
        createCustomerInput.balance = 0;

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Password contains part of the FirstName.");
        customerManager.createCustomer(createCustomerInput);
    }

    @Test
    public void testCreateCustomerWithPasswordWithPartOfLastName() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "Cliff";
        createCustomerInput.lastName = "Booth";
        createCustomerInput.login = "cliffbooth@gmail.com";
        createCustomerInput.pass = "1003Booth";
        createCustomerInput.balance = 0;

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Password contains part of the LastName.");
        customerManager.createCustomer(createCustomerInput);
    }

    @Test
    public void testCreateCustomerWithPasswordWithPartOfEmail() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "Cliff";
        createCustomerInput.lastName = "Booth";
        createCustomerInput.login = "c@gmail.com";
        createCustomerInput.pass = "c@gmail.com";
        createCustomerInput.balance = 0;

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Password contains part of the Email.");
        customerManager.createCustomer(createCustomerInput);
    }

    //Balance
    @Test
    public void testCreateCustomerWithNotNullBalance() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "Cliff";
        createCustomerInput.lastName = "Booth";
        createCustomerInput.login = "cliffbooth@gmail.com";
        createCustomerInput.pass = "10031932cl";
        createCustomerInput.balance = 20;

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Balance is not null.");
        customerManager.createCustomer(createCustomerInput);
    }
}
