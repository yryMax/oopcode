import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Ball {
    protected String color;
    protected String type;
    protected String description;

    /**
     * create a new Ball
     * @param color color of the ball
     * @param type type of the ball
     * @param description amount of the ball
     */
    public Ball(String color, String type, String description) {
        this.color = color;
        this.type = type;
        this.description = description;
    }

    /**
     * read the ball from the string with the given pattern
     * @param str the given string which contains the infomation of the ball
     * @return a new ball
     */
    public static List<Ball> read(String str) {
        String[] infos = str.split(";");
        String numStr = infos[0].substring(infos[0].indexOf('[')+1,infos[0].indexOf(']'));
        int num = Integer.parseInt(numStr);
        List<Ball> tmp = new ArrayList<>();
        while(num-->0){
            if(str.startsWith("EMPTY"))tmp.add(new EmptyBall(infos[1],"EMPTY",infos[2]));
            else if(str.startsWith("EXTRA"))tmp.add(new ExtraBall(infos[1],"EXTRA",infos[2],Integer.parseInt(infos[3])));
            else tmp.add(PrizeBall.read(infos));
        }
        return tmp;
    }

    /**
     * get the color of the ball
     * @return the color of the ball
     */
    public String getColor() {
        return color;
    }

    /**
     * get the type of the ball
     * @return the type of the ball
     */
    public String getType() {
        return type;
    }

    /**
     * get the current amount of the ball
     * @return the current amount of the ball
     */
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ball)) return false;
        Ball ball = (Ball) o;
        return Objects.equals(color, ball.color) && Objects.equals(type, ball.type) && Objects.equals(description, ball.description);
    }
    public String toLog(){
        return description + " You get " + toString();
    }
}
