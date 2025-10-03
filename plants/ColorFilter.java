import java.util.List;
import java.util.stream.Collectors;

public class ColorFilter implements FilterStrategy{

    String color;
    @Override
    public List<Plant> filter(List<Plant> plants) {
        return plants.stream()
                .filter(plant -> (plant.flower!=null && plant.flower.color.contains(color)))
                .collect(Collectors.toList());
    }

    public ColorFilter(String color){
        this.color = color;
    }
}
