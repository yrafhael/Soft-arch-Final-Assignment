import java.util.HashMap;
import java.util.Map;

public class RatGameManager {
    private static RatGameManager instance;
    private Map<String, Room> rooms;
    private Room currentRoom;
    private Rat rat;
    private int totalScore;

    private RatGameManager() {
        totalScore = 0;
    }

    public static RatGameManager getInstance() {
        if (instance == null) {
            instance = new RatGameManager();
        }
        return instance;
    }

    public void initializeGame() {
        rooms = new HashMap<>();
        RoomFactory roomFactory = new RoomFactory();

        // Create rooms
        Room livingRoom = roomFactory.createRoom("Living Room", "You are in the living room. You can stay (if you want)");
        Room kitchen = roomFactory.createRoom("Kitchen", "You are in the kitchen. You see the cheese on the counter!");
        Room bedroom = roomFactory.createRoom("Bedroom", "You are in the bedroom. Remember monster Inc? Watch out...");
        Room bathroom = roomFactory.createRoom("Bathroom", "You are in the bathroom.");
        Room laundryRoom = roomFactory.createRoom("Laundry Room", "You are in the laundry room. You are looking for cheese, not socks!");
        Room monsterRoom = roomFactory.createRoom("Monster Room", "Oh nooo!");

        // Link rooms
        roomFactory.linkRooms(livingRoom, "N", bedroom);
        roomFactory.linkRooms(livingRoom, "W", bathroom);
        roomFactory.linkRooms(livingRoom, "S", monsterRoom);
        roomFactory.linkRooms(bathroom, "S", livingRoom);
        roomFactory.linkRooms(bathroom, "N", laundryRoom);
        roomFactory.linkRooms(laundryRoom, "W", bedroom);
        roomFactory.linkRooms(laundryRoom, "N", monsterRoom);
        roomFactory.linkRooms(kitchen, "W", livingRoom);
        roomFactory.linkRooms(bedroom, "S", livingRoom);
        roomFactory.linkRooms(bedroom, "S", kitchen);
        roomFactory.linkRooms(bathroom, "E", monsterRoom);
        roomFactory.linkRooms(bedroom, "E", monsterRoom);

        rooms.put("Living Room", livingRoom);
        rooms.put("Kitchen", kitchen);
        rooms.put("Bedroom", bedroom);
        rooms.put("Bathroom", bathroom);
        rooms.put("Laundry Room", laundryRoom);
        rooms.put("Monster Room", monsterRoom);

        currentRoom = livingRoom; // I choose to start in the living room
        rat = new Rat();
    }

    public void playGame() {
        System.out.println(currentRoom.getDescription());

        while (!currentRoom.getName().equals("Kitchen") && !currentRoom.getName().equals("Monster Room")) {
            System.out.print("Enter your move (N/S/W/E): ");
            String move = getValidMove();

            Room nextRoom = currentRoom.getAdjacentRoom(move);

            if (nextRoom != null) {
                rat.move(); // Execute the rat's movement strategy
                currentRoom = nextRoom;
                System.out.println(currentRoom.getDescription());
            } else {
                System.out.println("You cannot go that way.");
            }
        }

        if (currentRoom.getName().equals("Kitchen")) {
            System.out.println("Congratulations! You found the cheese!");
            totalScore++;
        } else if (currentRoom.getName().equals("Monster Room")) {
            System.out.println("You encountered the monster. Game Over!");
        }
    }

    public Rat getRat() {
        return rat;
    }

    public int getTotalScore() {
        return totalScore;
    }

    private String getValidMove() {
        String move;
        while (true) {
            move = RatGame.getUserInput().trim().toUpperCase();
            if (move.equals("N") || move.equals("S") || move.equals("W") || move.equals("E")) {
                break;
            } else {
                System.out.print("Invalid move. Enter a valid move (N/S/W/E): ");
            }
        }
        return move;
    }
}