public class EmptyBall extends Ball{
    /**
     * create an empty ball
     * @param color color of the ball
     * @param type type of the ball
     * @param description amount of the ball
     */
    public EmptyBall(String color, String type, String description) {
        super(color, type, description);
    }

    /**
     * convert the ball to a string representation
     * @return the string representation of the ball
     */
    @Override
    public String toString() {
        return "EmptyBall (" + color + "), it contains... nothing.\n";
    }
}
