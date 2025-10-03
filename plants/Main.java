import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static Scanner userInput;
    private static final String menu = """
    Please make your choice:
    1 –Show plants that meet all criteria.
    2 –Filter plants by size.
    3 –Filter plants by flower colour.
    4 -Filter plants by edibility.
    5 –Reset all criteria.
    6 –Simulate global warming.
    7 – Quit the application.
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
                    if(plants.plantList.isEmpty()){
                        System.out.println("no plants");
                    }
                    else System.out.print(plants);
                    break;
                case 2:
                    System.out.println("Please input a target size");
                    double size = userInput.nextDouble();
                    userInput.nextLine();
                    System.out.print(plants.filter(new SizeFilter(size)));
                    break;
                case 3:
                    Set<String>colors = plants.getAllColors();
                    if(colors.isEmpty()){
                        System.out.println("no color to choose");
                    }
                    else {
                        String c = "[" + String.join(", ", colors) + "]";
                        System.out.println("Please choose from" + c);
                       String inputc = userInput.nextLine().strip();
                       if(colors.contains(inputc)){
                           System.out.print(plants.filter(new ColorFilter(inputc)));
                       }
                    }
                    break;
                case 4:
                    System.out.print(plants.filter(new EdibilityFilter()));
                    break;
                case 5:
                    plants.reset();
                    System.out.println("reset done!");
                    break;
                case 6:
                    plants.reset();
                    System.out.println(plants.increase());
                    break;
                case 7:
                    return;
                default:
                    break;
            }
        }

    }
}
