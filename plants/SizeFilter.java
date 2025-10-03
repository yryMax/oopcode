import java.util.List;
import java.util.stream.Collectors;

public class SizeFilter implements FilterStrategy{
    double size;

    public SizeFilter(double size) {
        this.size = size;
    }

    @Override
    public List<Plant> filter(List<Plant> plants) {
        return plants.stream().filter(plant -> plant.size[0]<= size && plant.size[1]>=size).toList();
    }
}
