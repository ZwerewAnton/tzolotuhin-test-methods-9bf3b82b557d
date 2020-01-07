package ru.nsu.fit.tests.api;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import ru.nsu.fit.services.log.Logger;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Timur Zolotuhin (tzolotuhin@gmail.com)
 */
public class BuildVerificationTest {
    ClientConfig clientConfig;
    HttpAuthenticationFeature feature;
    Invocation.Builder invocationBuilder;
    WebTarget webTarget;
    Client client;



    @BeforeClass
    public void beforeClass() {
        clientConfig = new ClientConfig();
        feature = HttpAuthenticationFeature.basic("admin", "setup");
        clientConfig.register( feature) ;
        clientConfig.register(JacksonFeature.class);
        client = ClientBuilder.newClient( clientConfig );
        webTarget = client.target("http://localhost:8081/endpoint/rest").path("customers");
        invocationBuilder =	webTarget.request(MediaType.APPLICATION_JSON);
    }

    @Test(description = "Create customer via API.")
    public void createCustomer() {
        Logger.debug("Try to make POST...");
        Reporter.log("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity("{\n" +
                "\t\"firstName\":\"Cliff\",\n" +
                "    \"lastName\":\"Booth\",\n" +
                "    \"login\":\"cliffbooth@gmail.com\",\n" +
                "    \"pass\":\"10031932cl\",\n" +
                "    \"balance\":\"0\"\n" +
                "}", MediaType.APPLICATION_JSON));
        Logger.info("Response: " + response.readEntity(String.class));
        Reporter.log("Response: " + response.readEntity(String.class));
    }

    @Test(description = "Check email of customer with wrong format.")
    public void checkCustomerEmail() {

        Logger.debug("Try to make POST...");
        Reporter.log("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity("{\n" +
                "\t\"firstName\":\"Cliff\",\n" +
                "    \"lastName\":\"Booth\",\n" +
                "    \"login\":\"cliffbooth\",\n" +
                "    \"pass\":\"10031932cl\",\n" +
                "    \"balance\":\"0\"\n" +
                "}", MediaType.APPLICATION_JSON));
        String res = response.readEntity(String.class);
        int end = res.indexOf('\n');
        boolean f = res.substring(0, end).equals("Email is not correct.");

        Logger.info(res.substring(0, end));
        Logger.info(f ? "Test Email is pass" : "Test  Email is fall");
        Reporter.log(f ? "Test Email is pass" : "Test  Email is fall");
        Assert.assertTrue(f);
    }


    @Test(description = "Check FirstName of customer with space.")
    public void checkCustomerFirstName() {
        Logger.debug("Try to make POST...");
        Reporter.log("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity("{\n" +
                "\t\"firstName\":\"Cl iff\",\n" +
                "    \"lastName\":\"Booth\",\n" +
                "    \"login\":\"cliffbooth@gmail.com\",\n" +
                "    \"pass\":\"10031932cl\",\n" +
                "    \"balance\":\"0\"\n" +
                "}", MediaType.APPLICATION_JSON));
        String res = response.readEntity(String.class);
        int end = res.indexOf('\n');
        boolean f = res.substring(0, end).equals("FirstName have space.");
        Logger.info(f ? "Test FirstName is pass" : "Test FirstName is fall");
        Reporter.log(f ? "Test FirstName is pass" : "Test FirstName is fall");
        Assert.assertTrue(f);
    }

    @Test(description = "Check LastName of customer with space.")
    public void checkCustomerLastName() {
        Logger.debug("Try to make POST...");
        Reporter.log("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity("{\n" +
                "\t\"firstName\":\"Cliff\",\n" +
                "    \"lastName\":\"Bo oth\",\n" +
                "    \"login\":\"cliffbooth@gmail.com\",\n" +
                "    \"pass\":\"10031932cl\",\n" +
                "    \"balance\":\"0\"\n" +
                "}", MediaType.APPLICATION_JSON));
        String res = response.readEntity(String.class);
        int end = res.indexOf('\n');
        boolean f = res.substring(0, end).equals("LastName have space.");
        Logger.info(f ? "Test LastName is pass" : "Test LastName is fall");
        Reporter.log(f ? "Test LastName is pass" : "Test LastName is fall");
        Assert.assertTrue(f);
    }


    @Test(description = "Check balance of customer is not 0.")
    public void checkCustomerWNotNullBalance() {
        Logger.debug("Try to make POST...");
        Reporter.log("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity("{\n" +
                "\t\"firstName\":\"Cliff\",\n" +
                "    \"lastName\":\"Booth\",\n" +
                "    \"login\":\"cliffbooth@gmail.com\",\n" +
                "    \"pass\":\"10031932cl\",\n" +
                "    \"balance\":\"20\"\n" +
                "}", MediaType.APPLICATION_JSON));
        String res = response.readEntity(String.class);
        int end = res.indexOf('\n');
        boolean f = res.substring(0, end).equals("Balance is not null.");
        Logger.info(f ? "Test Balance is pass" : "Test Balance is fall");
        Reporter.log(f ? "Test Balance is pass" : "Test Balance is fall");
        Assert.assertTrue(f);
    }

    @Test(description = "Check Password of customer is easy.")
    public void checkCustomerShortPassword() {
        Logger.debug("Try to make POST...");
        Reporter.log("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity("{\n" +
                "\t\"firstName\":\"Cliff\",\n" +
                "    \"lastName\":\"Booth\",\n" +
                "    \"login\":\"cliffbooth@gmail.com\",\n" +
                "    \"pass\":\"123qwe\",\n" +
                "    \"balance\":\"0\"\n" +
                "}", MediaType.APPLICATION_JSON));
        String res = response.readEntity(String.class);
        int end = res.indexOf('\n');
        boolean f = res.substring(0, end).equals("Password is easy.");
        Logger.info(f ? "Test Password is pass" : "Test Password is fall");
        Reporter.log(f ? "Test Password is pass" : "Test Password is fall");
        Assert.assertTrue(f);
    }

    @Test(description = "Check FirstName's length.")
    public void checkCustomerWithLongFirstName() {
        Logger.debug("Try to make POST...");
        Reporter.log("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity("{\n" +
                "\t\"firstName\":\"Iamcliffbooth\",\n" +
                "    \"lastName\":\"Booth\",\n" +
                "    \"login\":\"cliffbooth@gmail.com\",\n" +
                "    \"pass\":\"10031932cl\",\n" +
                "    \"balance\":\"0\"\n" +
                "}", MediaType.APPLICATION_JSON));
        String res = response.readEntity(String.class);
        int end = res.indexOf('\n');
        boolean f = res.substring(0, end).equals("FirstName's length should be more or equal 6 symbols and less or equal 12 symbols.");
        Logger.info(f ? "Test FirstName's length is pass" : "Test FirstName's length is fall");
        Reporter.log(f ? "Test FirstName's length is pass" : "Test FirstName's length is fall");
        Assert.assertTrue(f);

    }

    @Test(description = "Check FirstName uppercase's.")
    public void checkCustomerWithManyUpperCaseInFirstName() {
        Logger.debug("Try to make POST...");
        Reporter.log("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity("{\n" +
                "\t\"firstName\":\"ClIfF\",\n" +
                "    \"lastName\":\"Booth\",\n" +
                "    \"login\":\"cliffbooth@gmail.com\",\n" +
                "    \"pass\":\"10031932cl\",\n" +
                "    \"balance\":\"0\"\n" +
                "}", MediaType.APPLICATION_JSON));
        String res = response.readEntity(String.class);
        int end = res.indexOf('\n');
        boolean f = res.substring(0, end).equals("Two or more characters FirstName is uppercase or not letter.");
        Logger.info(f ? "Test FirstName uppercase's is pass" : "Test FirstName uppercase's is fall");
        Reporter.log(f ? "Test FirstName uppercase's is pass" : "Test FirstName uppercase's is fall");
        Assert.assertTrue(f);
    }

    @Test(description = "Check FirstName of customer is uppercase or not letter.")
    public void checkCustomerWithWrongCharacterInFirsName() {
        Logger.debug("Try to make POST...");
        Reporter.log("Try to make POST...");
        Response response = invocationBuilder.post(Entity.entity("{\n" +
                "\t\"firstName\":\"Cliff1932#\",\n" +
                "    \"lastName\":\"Booth\",\n" +
                "    \"login\":\"cliffbooth@gmail.com\",\n" +
                "    \"pass\":\"10031932cl\",\n" +
                "    \"balance\":\"0\"\n" +
                "}", MediaType.APPLICATION_JSON));
        String res = response.readEntity(String.class);
        int end = res.indexOf('\n');
        boolean f = res.substring(0, end).equals("Two or more characters FirstName is uppercase or not letter.");
        Logger.info(f ? "Test Uppercase or not letter firstname is pass" : "Test Uppercase or not letter firstname is fall");
        Reporter.log(f ? "Test Uppercase or not letter firstname is pass" : "Test Uppercase or not letter firstname is fall");
        Assert.assertTrue(f);
    }
}
