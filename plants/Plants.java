import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Plants {

    List<Plant>plantList;

    List<Plant>originalPlants;


    public Plants(List<Plant> plantList) {
        this.plantList = plantList;
        this.originalPlants = plantList.stream().map(Plant::new).toList();
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
    @Override
    public String toString(){
        return plantList.stream().map(Plant::toString).collect(Collectors.joining(""));
    }

    public void reset(){
        this.plantList = this.originalPlants.stream().map(Plant::new).toList();
    }

    public String filter(FilterStrategy filter){
        this.plantList = filter.filter(plantList);
        if(plantList.isEmpty())return "No valid plants\n";
        else return toString();

    }
    public Set<String>getAllColors(){
        return plantList.stream().filter(plant -> plant.flower!=null).map(plant -> plant.flower.color).collect(Collectors.toSet());
    }

    public String increase(){
        plantList = new ArrayList<>(plantList);
        plantList.replaceAll(Plant::increaseSize);
        return toString();
    }


}
