package com.example.roomoccupancymanager.acceptance;

import com.example.roomoccupancymanager.model.RoomOptimizeRequestDTO;
import com.example.roomoccupancymanager.model.RoomOptimizeResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomManagerStepDefinitions {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    private RoomOptimizeResponseDTO roomOptimizeResponseDTO;
    private Integer premiumRooms;
    private Integer economyRooms;
    private List<BigDecimal> offers;

    @Given("I have {int} premium rooms")
    public void iHavePRoomsPremiumRooms(Integer int1) {
        premiumRooms = int1;
    }

    @Given("I have {int} economy rooms")
    public void iHaveERoomsEconomyRooms(Integer int1) {
        economyRooms = int1;
    }

    @Given("Customers want to pay usual")
    public void customersWantToPayUsual() {
        offers = Stream.of(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0).map(BigDecimal::valueOf).toList();
    }

    @When("I request for result")
    public void iRequestForResult() throws Exception {
        RoomOptimizeRequestDTO input = new RoomOptimizeRequestDTO(offers, economyRooms, premiumRooms);
        String json = objectMapper.writeValueAsString(input);
        var response = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/rooms/optimize")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn()
                .getResponse()
                .getContentAsString();
        roomOptimizeResponseDTO = objectMapper.readValue(response, RoomOptimizeResponseDTO.class);
    }

    @Then("I have {int} premium rooms booked")
    public void iHavePBookedPremiumRoomsBooked(Integer int1) {
        assertEquals(int1, roomOptimizeResponseDTO.premiumOccupation());
    }

    @Then("I have {int} economy rooms booked")
    public void iHaveEBookedEconomyRoomsBooked(Integer int1) {
        assertEquals(int1, roomOptimizeResponseDTO.economyOccupation());
    }

    @Then("Premium profit is {bigdecimal}")
    public void premiumProfitIsPProfit(BigDecimal bigdecimal1) {
        assertEquals(bigdecimal1, roomOptimizeResponseDTO.premiumProfit());
    }

    @Then("Economy profit is {bigdecimal}")
    public void economyProfitIsEProfit(BigDecimal bigdecimal1) {
        assertEquals(bigdecimal1, roomOptimizeResponseDTO.economyProfit());
    }
}
