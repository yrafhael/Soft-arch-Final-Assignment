public class RoomFactory {
    public Room createRoom(String name, String description) {
        return new Room(name, description);
    }

    public void linkRooms(Room room1, String direction, Room room2) {
        room1.addAdjacentRoom(direction, room2);
    }
}