package ru.nsu.fit.tests.api;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import ru.nsu.fit.services.log.Logger;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Timur Zolotuhin (tzolotuhin@gmail.com)
 */
public class PlanManagerVerificationTest {
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
        Response response = invocationBuilder.post(Entity.entity("{\n" +
                "\t\"firstName\":\"Cliff\",\n" +
                "    \"lastName\":\"Booth\",\n" +
                "    \"login\":\"cliffbooth@gmail.com\",\n" +
                "    \"pass\":\"10031932cl\",\n" +
                "    \"balance\":\"0\"\n" +
                "}", MediaType.APPLICATION_JSON));
        Logger.info("Response: " + response.readEntity(String.class));
    }

}
