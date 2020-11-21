package complex;

import java.security.InvalidParameterException;

public class Complex {
    private int[] num;

    public Complex(int[] num) {
        if (num.length != 2) {
            throw new InvalidParameterException("wrong number of arguments " + num.length);
        }
        this.num = num;
    }

    public Complex(int re, int im) {
        this.num =  new int[]{re, im};
    }

    public int getR() {
        return num[0];
    }

    public int getIm() {
        return num[1];
    }

    public void setR(int R) {
        this.num[0] = R;
    }

    public void setIm(int Im) {
        this.num[1] = Im;
    }

    public static Complex sum(Complex c1, Complex c2) {
        return new Complex(new int[]{c1.getR() + c2.getR(), c1.getIm() + c2.getIm()});
    }

    public static Complex multiply(Complex c1, Complex c2) {
        return new Complex(new int[]{c1.getR() * c2.getR() - c1.getIm() * c2.getIm(),
                c1.getR() * c2.getIm() + c1.getIm() * c2.getR()});
    }

    public static Complex subtract(Complex c1, Complex c2) {
        return new Complex(new int[]{c1.getR() - c2.getR(), c1.getIm() - c2.getIm()});
    }

    @Override
    public String toString() {
        return "R: " + num[0] + ", " + "Im: " + num[1];
    }


}
