# Room occupancy manager

---

### Start Application

Main application class is
`com.example.roomoccupancymanager.RoomOccupancyManagerApplication`

Application by default starts on `localhost:8080`

---

### API

### Health

`localhost:8080/health`
Provide server status

* Method GET
* Response `OK`

### Rooms

`localhost:8080/rooms/optimize`

* Method POST
* Header: `{"Content-Type":"application/json"}`
* Body:

```json
 {
  "offersForRooms": [23,45,155,374,22,99.99,100,101, 115, 209  ],
  "economyRoomCount": 3,
  "premiumRoomCount": 3
 }
 ```
* Response:   
```json
 {
  "economyOccupation": 3,
  "premiumOccupation": 3,
  "economyProfit": 167.99,
  "premiumProfit": 738.0
  }
 ```