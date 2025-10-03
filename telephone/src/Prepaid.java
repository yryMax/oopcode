

public class Prepaid extends Plan{
    private final boolean[] carriedOver;

    public Prepaid(boolean student, double discount, String provider, String name, int price, String[] amount, boolean[] carriedOver) {
        super(student, discount, provider, name, price, amount);
        this.carriedOver = carriedOver;
    }

    public static Prepaid read(boolean student, double discount, String[] amount, String[] infos) {
        boolean[] c = {infos[6].equals("true"),infos[7].equals("true"),infos[8].equals("true")};
        return new Prepaid(student,discount,infos[0],infos[1],Integer.parseInt(infos[2]),amount,c);
    }


    public String getCarriedStr(boolean x){
        return !x ?"no":"yes";
    }
    @Override
    public String toString() {
        String head = "Prepaid plan";
        if(student)head+=" for students";
        return head + super.toString()
                + "       calling minutes carry over: " + getCarriedStr(carriedOver[0])
                + "\n       texts carry over: " + getCarriedStr(carriedOver[1])
                + "\n       internet data carry over: " + getCarriedStr(carriedOver[2])
                + "\n";
    }
}
