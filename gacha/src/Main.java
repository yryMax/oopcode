import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static final String menu = """
        Please make your choice:
        1 – Show all balls currently in the machine.
        2 – Show current chance to win the legendary prize.
        3 – Draw a ball.
        4 – Write debug output to file.
        5 – Reset machine state.
        6 – Quit the application.
        """;

    private static Scanner user;
    public static Machine loadingData() throws FileNotFoundException{
        user = new Scanner(System.in);
        System.out.println("please provide the input file");
        String filepath = user.nextLine();
        Scanner sc = new Scanner(new File(filepath));
        return Machine.read(sc);
    }
    public static void main (String[] args) throws FileNotFoundException {
        Machine machine = loadingData();
        while (true) {
            System.out.println(menu);
            int choice = user.nextInt();
            user.nextLine();
            switch (choice) {
                case 1:
                    System.out.print(machine);
                    break;
                case 2:
                    int chance = machine.currentChance();
                    if (chance != -1) System.out.println(chance + "%");
                    else System.out.println("the legendary prize has already been drawn.");
                    break;
                case 3:
                    machine.draw();
                    break;
                case 4:
                    machine.log();
                    break;
                case 5:
                    machine.reset();
                    break;
                case 6:
                    return;
            }
        }
    }

}
