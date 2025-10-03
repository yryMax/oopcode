import java.util.*;

public class PrizeBall extends Ball{
    private final String prize;

    /**
     * create a new prize ball
     * @param color color of the ball
     * @param type type of the ball
     */
    public PrizeBall(String color, String type, String description, String prize) {
        super(color, type, description);
        this.prize = prize;
    }

    public static PrizeBall read(String[] infos) {
        String type = infos[0].substring(0,infos[0].indexOf(" "));
        String[] prizes = infos[3].substring(1,infos[3].indexOf('}')).split(", ");
        Random random = new Random();
        int index = random.nextInt(prizes.length);
        return new PrizeBall(infos[1],type,infos[2],prizes[index]);
    }


    /**
     * check if the other object is equal to this ball
     * @param o the other object
     * @return true off the other object is identical to this ball
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrizeBall)) return false;
        if (!super.equals(o)) return false;
        PrizeBall prizeBall = (PrizeBall) o;
        return Objects.equals(prize, prizeBall.prize);
    }

    /**
     * convert the ball to a string representation
     * @return the string representation of the ball
     */
    @Override
    public String toString() {
        return type + " PrizeBall (" + color + "), it contains " + prize + ".\n";
    }

}
