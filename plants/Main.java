import java.io.IOException;
import java.util.Scanner;
public class Main {
    private static Scanner userInput;
    private static final String menu = """
    Please make your choice:
    1 –Show plants that meet all criteria.
    2 –Filter plants by size.
    3 –Filter plants by flower colour.
    4 -Filter plants by edibility.
    5–Reset all criteria.
    6 –Simulate global warming.7–Quit the application.
    """;
    public static void main(String[] args) throws IOException {
        userInput = new Scanner(System.in);
        System.out.println("Please provide input file path");

        String file_path = userInput.nextLine();
        Plants plants = Plants.readFromFile(file_path);

        while(true){
            System.out.println(menu);
            int choice = userInput.nextInt();
            userInput.nextLine();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    return;
                default:
                    break;
            }
        }

    }
}
