import java.io.IOException;
import java.util.List;

enum Type {
    HERB,
    SHRUB,
    TREE;

    @Override
    public String toString() {
        if(this == HERB) return "Herb";
        else if(this == SHRUB)return "Shrub";
        else return "Tree";
    }

    public static Type read(String t) throws IOException {
        if(t.contains("HERB"))return HERB;
        if(t.contains("SHRUB"))return SHRUB;
        if(t.contains("TREE"))return TREE;
        throw  new IOException("invalid type name");
    }
}

public class Plant {

    public Plant(String name, String latin, double[] size, Type type, Flower flower, boolean edibility, String notes) {
        this.name = name;
        this.latin = latin;
        this.size = size;
        this.type = type;
        this.flower = flower;
        this.edibility = edibility;
        this.notes = notes;
    }

    public Plant(Plant that){
        this.name = that.name;
        this.latin = that.latin;
        this.size = that.size;
        this.type = that.type;
        this.flower = that.flower == null ? null: new Flower(that.flower);
        this.edibility = that.edibility;
        this.notes = that.notes;
    }

    public static Plant read(List<String> info) throws IOException{
        Type type = null;
        Double minSize = null;
        Double maxSize = null;
        Flower flower = null;
        String name = null, latin = null, note = null;
        boolean edibility = false;
        for(String cur: info){
            if(cur.startsWith("HERB:") || cur.startsWith("SHRUB:") || cur.startsWith("TREE:")){
                String[] s1 = cur.split(": ");
                type = Type.read(s1[0]);
                String[] s2 = s1[1].strip().split("; ");
                name = s2[0];
                latin = s2[1];
                String[] s3 = s2[2].substring(1, s2[2].length()-1).split(", ");
                minSize = Double.parseDouble(s3[0]);
                maxSize = Double.parseDouble(s3[1]);
            }
            else if(cur.contains("FLOWER DETAILS:")){
                String[] s1 = cur.replace("FLOWER DETAILS:", "").strip().split("; ");
                int num = Integer.parseInt(s1[2].replace("petals", "").strip());
                flower = new Flower(s1[1], s1[0], num);
            }
            else if(cur.contains("EDIBILITY")) {
                String[] s1 = cur.replace("EDIBILITY:", "").strip().split("; ");
                edibility = s1[0].contains("Yes");
                note = s1[1];
            }
        }
        return new Plant(name, latin, new double[]{minSize, maxSize}, type, flower, edibility, note);
    }

    @Override
    public String toString(){
        String s1 = String.format("%s named %s(latin: %s), typical size between %.2fcm to %.2fcm\n", type.toString(), name, latin, size[0], size[1]);
        String s2 = "This plant does not have flowers\n";
        String s3 = "";
        if(flower!=null){
            s2  = String.format("This plant has %s, %s flower with %d petals\n", flower.size, flower.color, flower.petals);
        }
        if(notes!=null){
            s3 = edibility ? "this plant is safe to eat with " : "this plant is not safe to eat with ";
            s3 += notes + "\n";
        }

        return s1 + s2 + s3;
    }

    static class Flower{
        String size;
        String color;
        int petals;

        public Flower(String size, String color, int petals) {
            this.size = size;
            this.color = color;
            this.petals = petals;
        }

        public Flower(Flower that){
            this.size = that.size;
            this.color = that.color;
            this.petals = that.petals;
        }

    }

    public Plant increaseSize(){
        this.size[0] = Math.floor(this.size[0]*1.1);
        this.size[1] = Math.floor(this.size[1]*1.1);
        return this;
    }

    String name;
    String latin;
    double[] size;
    Type type;
    Flower flower;
    boolean edibility;
    String notes;



}
