public abstract class Plan {
    protected String provider;
    protected String name;
    protected int price;
    protected int[] amount;
    protected boolean student;
    protected double discount;

    public boolean isStudent() {
        return student;
    }

    public int getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public int[] getAmount() {
        return amount;
    }

    protected Plan(boolean student, double discount, String provider, String name, int price, String[] amount) {
        this.provider = provider;
        this.name = name;
        this.price = price;
        this.student = student;
        this.discount = discount;
        this.amount = new int[3];
        for(int i=0;i<3;i++){
            if(amount[i].contains("unlimited"))this.amount[i] = Integer.MAX_VALUE;
            else this.amount[i] = Integer.parseInt(amount[i]);
        }
    }

    public static Plan read(String info) {
        String[] infos = info.split(", ");
        infos[2] = infos[2].replace(" euro","");
        int n = infos.length;
        boolean student = false;
        double discount = 0;
        if(infos[n-1].contains("%")){
            student = true;
            discount = Double.parseDouble(infos[n-1].substring(0, infos[n-1].length()-1))/100.0;
        }
        String[] amount = {infos[3],infos[4],infos[5]};
        if(n>8)return Prepaid.read(student,discount,amount,infos);
        else return new Subscription(student,discount,infos[0],infos[1],Integer.parseInt(infos[2]),amount);
    }
    public String getAmountStr(int x){
        return x == Integer.MAX_VALUE? "unlimited": String.valueOf(x);
    }
    @Override
    public String toString() {
        return ": " + provider + " " + name + ", costs " +price*(1.0-discount) +  " euro\n       includes "
                + getAmountStr(amount[0]) + " calling minutes, "
                + getAmountStr(amount[1]) + " texts and "
                + getAmountStr(amount[2]) + "GB of data.\n";
    }
}
