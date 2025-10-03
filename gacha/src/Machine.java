
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Machine {
    private List<Ball> balls;
    private static List<String>logs;
    private final List<Ball> backupBalls;

    private final Random rng;

    public Machine(List<Ball> balls) {
        this.balls = balls;
        this.backupBalls = List.copyOf(balls);
        this.rng = new Random(42);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Machine machine)) return false;
        return Objects.equals(balls, machine.balls) && Objects.equals(backupBalls, machine.backupBalls);
    }



    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();
        for(Ball ball:balls) ans.append(ball.toString());
        return ans.toString();
    }

    public int currentChance() {
        int legend = (int)balls.stream().filter(x->x.type.equals("LEGENDARY")).count();
        if(legend == 0)return -1;
        return (int)(legend*100.0/balls.size() + 0.5);
    }

    public void reset(){
        balls = new ArrayList<>();
        balls.addAll(backupBalls);
        logs.add("machine is reset");
    }

    public void log() throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter("log.txt")){
            logs.forEach(writer::println);
        }
    }

    public void draw(){
        if(balls.isEmpty()){
            System.out.println("machine is empty!");
            return;
        }
        int ch = rng.nextInt(balls.size());
        Ball ball = balls.remove(ch);
        System.out.println(ball.toLog());
        logs.add("You draw an " + ball);
        if (ball instanceof ExtraBall extraBall){
            for (int i = 0; i < extraBall.getNumber(); i++) draw();
        }
    }
    public static Machine read(Scanner sc){
        logs = new ArrayList<>();
        List<Ball>balls = new ArrayList<>();
        while(sc.hasNextLine()){
            String ans = sc.nextLine();
            ans += ";" + sc.nextLine() + ";" + sc.nextLine();
            if(!ans.startsWith("EMPTY"))ans += ";" + sc.nextLine();
            balls.addAll(Ball.read(ans));
        }
        return new Machine(balls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balls, backupBalls);
    }
}
