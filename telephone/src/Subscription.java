public class Subscription extends Plan{
    public Subscription(boolean student, double discount, String provider, String name, int price, String[] amount) {
        super(student, discount, provider, name, price, amount);
    }

    @Override
    public String toString() {
        String head = "Subscription plan";
        if(student)head+=" for students";
        return head + super.toString();
    }
}
