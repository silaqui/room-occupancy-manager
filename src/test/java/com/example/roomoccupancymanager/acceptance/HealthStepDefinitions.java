package com.example.roomoccupancymanager.acceptance;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealthStepDefinitions {

    @Autowired
    private MockMvc mockMvc;

    private String healthCheckResponse;

    @When("I want to @Get health")
    public void getAtHealth() throws Exception {
        healthCheckResponse = mockMvc
                .perform(MockMvcRequestBuilders.get("/health"))
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Then("I received OK response")
    public void responseOK() {
        assertEquals("OK", healthCheckResponse);
    }
}
