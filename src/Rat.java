public class Rat {
    private MovementStrategy movementStrategy;

    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public void move() {
        if (movementStrategy != null) {
            movementStrategy.move();
        } else {
            System.out.println("No movement strategy set for the rat.");
        }
    }
}