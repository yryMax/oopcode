

public class ExtraBall extends Ball{
    private int number;

    /**
     * create a new extraball
     * @param color color of the ball
     * @param type type of the ball
     * @param number the extra chances given by the ball
     */
    public ExtraBall(String color, String type, String description, int number) {
        super(color, type, description);
        this.number = number;
    }


    public int getNumber() {
        return number;
    }
    /**
     * check if the other object is equal to this ball
     * @param o the other object
     * @return true off the other object is identical to this ball
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExtraBall)) return false;
        if (!super.equals(o)) return false;
        ExtraBall extraBall = (ExtraBall) o;
        return number == extraBall.number;
    }


    /**
     * convert the ball to a string representation
     * @return the string representation of the ball
     */
    @Override
    public String toString() {
        return "ExtraBall (" + color +"), it gives " + number + " extra draws\n";
    }
}
