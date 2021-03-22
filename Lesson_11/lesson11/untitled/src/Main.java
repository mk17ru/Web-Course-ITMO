import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int t = sc.nextInt();
        int[] a = new int[n];
        int st = 101;
        int fi = 0;
        for (int i = 0; i < n; ++i) {
            a[i] = sc.nextInt();
            fi = Math.max(fi, a[i]);
            st = Math.min(st, a[i]);
        }
        int num = sc.nextInt();
        int floor  = a[num - 1];
        int mi = Math.min(fi - floor, floor - st);
        int ma = Math.max(fi - floor, floor - st);
        if (mi > t) {
            System.out.println(mi * 2 + ma);
        } else {
            System.out.println(mi + ma);
        }
    }
}
