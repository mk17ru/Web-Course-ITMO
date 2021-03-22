package complex;

class Adder {
    static {
        init();
    }
    private static int sum;
    public static int getSum() {
        init();
        return sum;
    }
    private static boolean initialized = false;
    private static synchronized void init() {
        if (!initialized) {
            for (int i = 0; i < 10; i++) sum += i;
            initialized = true;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println(Adder.getSum());
    }
}