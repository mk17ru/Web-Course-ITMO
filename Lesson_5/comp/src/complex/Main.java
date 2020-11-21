package complex;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int[] num = new int[2];
        Scanner sc = new Scanner(System.in);
        num[0] = sc.nextInt();
        num[1] = sc.nextInt();
        Complex a = new Complex(num);
        Complex b = new Complex(new int[]{3, 4, 5});
        System.out.println(Complex.sum(a, b));
        System.out.println(Complex.subtract(a, b));
        System.out.println(Complex.multiply(a, b));

    }
}
