import java.util.HashMap;
import java.util.Map;


public class Room {
    private String name;
    private String description;
    private Map<String, Room> adjacentRooms;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.adjacentRooms = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void addAdjacentRoom(String direction, Room adjacentRoom) {
        if (direction != null && adjacentRoom != null) {
            adjacentRooms.put(direction.toUpperCase(), adjacentRoom);
        }
    }

    public Room getAdjacentRoom(String direction) {
        return adjacentRooms.get(direction.toUpperCase());
    }
}
