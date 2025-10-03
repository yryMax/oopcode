import java.util.List;

public class EdibilityFilter implements FilterStrategy{
    @Override
    public List<Plant> filter(List<Plant> plants) {
        return plants.stream().filter(plant -> plant.edibility).toList();
    }
}
