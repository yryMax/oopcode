import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
public class PlanData {
    List<Plan>plans;

    public PlanData(List<Plan> plans) {
        this.plans = plans;
    }

    public static List<Plan> read(Scanner sc) {
        List<Plan>tmp = new ArrayList<>();
        while(sc.hasNextLine()){
            tmp.add(Plan.read(sc.nextLine()));
        }
        return tmp;
    }

    @Override
    public String toString() {
        String ans = "";
        for (Plan x : plans) {
            ans += x.toString();
        }
        return ans;
    }

    public void filter(String type, boolean isStudent, String req) {
        List<Plan>tmp = plans;
        if(type.toLowerCase().contains("subscription"))tmp = tmp.stream().filter(x->x instanceof Subscription).toList();
        if(type.toLowerCase().contains("prepaid"))tmp = tmp.stream().filter(x->x instanceof Prepaid).toList();
        if(!isStudent)tmp = tmp.stream().filter(x->!x.isStudent()).toList();
        if(!req.contains(" ")){
            double maxPrice = Double.parseDouble(req);
            tmp = tmp.stream().filter(x->x.getDiscount()*x.getPrice()<=maxPrice).toList();
        }
        else{
            String[] infos = req.strip().split(" ");
            int[] num = {Integer.parseInt(infos[0]),Integer.parseInt(infos[1]),Integer.parseInt(infos[2])};
            tmp = tmp.stream().filter(x->x.getAmount()[0]>=num[0] && x.getAmount()[1]>=num[1] && x.getAmount()[2]>=num[2]).toList();
        }
        if(tmp.isEmpty()) System.out.println("sorry, no such plan!");
        else tmp.forEach(System.out::print);
    }

    public void writeToFile(String fr) throws FileNotFoundException {
        Comparator<Plan>myComparator = Comparator.comparingInt(Plan::getPrice);
        try (PrintWriter writer = new PrintWriter(fr)) {
            plans.stream().sorted(myComparator).forEach(x->writer.write(x.toString()));
        }

    }
}
