import java.io.File;
import java.io.IOException;
import java.util.*;
public class Plants {

    List<Plant>plantList;

    public Plants(List<Plant> plantList) {
        this.plantList = plantList;
    }


    public static Plants readFromFile(String filepath) throws IOException {
        try (Scanner sc = new Scanner(new File(filepath))) {
            List<String> cache = new ArrayList<>();
            List<Plant> plantList = new ArrayList<>();
            while (sc.hasNextLine()) {
                String cur = sc.nextLine();
                if (cur.startsWith("HERB:") || cur.startsWith("SHRUB:") || cur.startsWith("TREE:")) {
                    if(!cache.isEmpty())plantList.add(Plant.read(cache));
                    cache.clear();
                }
                cache.add(cur);
            }
            plantList.add(Plant.read(cache));
            return new Plants(plantList);
        }
    }
}
