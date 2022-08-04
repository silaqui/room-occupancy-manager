Feature: Room Occupancy Manager

  Background:
    Given Customers want to pay usual

  Scenario Outline: I want to optimize room booking
    And I have <pRooms> premium rooms
    And I have <eRooms> economy rooms
    When I request for result
    Then I have <pBooked> premium rooms booked
    And I have <eBooked> economy rooms booked
    And Premium profit is <pProfit>
    And Economy profit is <eProfit>

    Examples:
      | pRooms | eRooms | pBooked | eBooked | pProfit | eProfit |
      | 3      | 3      | 3       | 3       | 738.0   | 167.99  |
      | 7      | 5      | 6       | 4       | 1054.0  | 189.99  |
      | 2      | 7      | 2       | 4       | 583.0   | 189.99  |
      | 7      | 1      | 7       | 1       | 1153.99  | 45.0   |
