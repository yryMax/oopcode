import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {
    private static final String menu = """
            Please make your choice:
            "1 – Show all subscriptions and prepaid formulas
            "2 – Filter subscriptions and prepaid formulas
            "3 – Write to file
            "4 – Stop the program
            """;
    private static Scanner user;
    private static PlanData loadingData() throws FileNotFoundException {
        user = new Scanner(System.in);
        List<Plan>tmp = new ArrayList<>();
        tmp.addAll(PlanData.read(new Scanner(new File("subscriptions.txt"))));
        tmp.addAll(PlanData.read(new Scanner(new File("prepaid.txt"))));
        return new PlanData(tmp);
    }


    public static void main(String[] args) throws IOException {
        PlanData data = loadingData();
        while(true){
            System.out.println(menu);
            int choice = user.nextInt();
            user.nextLine();
            switch (choice){
                case 1:
                    System.out.println(data);
                    break;
                case 2:
                    System.out.println("please enter which type you want to see, you can only type in 'subscription','prepaid' or 'all'");
                    String type = user.nextLine();
                    System.out.println("are you a student? [y/n]");
                    String stu = user.nextLine();
                    System.out.println("you can either require a maximum price or require a minimum amount of calling minutes, texts and gigabytes "
                                +   "if you want to require a price, just enter the price number " +
                                    "if you want to require on the plan, please enter a minimum amount of calling minutes, texts and gigabytes that you want"
                                +   "and seperated with a space");
                    String req = user.nextLine();
                    data.filter(type,stu.contains("y"),req);
                    break;
                case 3:
                    System.out.println("please provide the file name");
                    String fr = user.nextLine();
                    data.writeToFile(fr);
                    break;
                default:
                    return;
            }
        }
    }
}
