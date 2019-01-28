package tensorMing_Signal;

import tensorMing_Fundation.Complex;

public class SignalUnitTests {

    public static void main(String[] args) {

        /***************************************************************************/
        // Complex number tests.

        Complex a = new Complex(5.0, 6.0);
        Complex b = new Complex(-3.0, 4.0);

        System.out.println("a            = " + a);
        System.out.println("b            = " + b);
        System.out.println("Re(a)        = " + a.re());
        System.out.println("Im(a)        = " + a.im());
        System.out.println("b + a        = " + b.plus(a));
        System.out.println("a - b        = " + a.minus(b));
        System.out.println("a * b        = " + a.times(b));
        System.out.println("b * a        = " + b.times(a));
        System.out.println("a / b        = " + a.divides(b));
        System.out.println("(a / b) * b  = " + a.divides(b).times(b));
        System.out.println("conj(a)      = " + a.conjugate());
        System.out.println("|a|          = " + a.abs());
        System.out.println("tan(a)       = " + a.tan());

        /***************************************************************************/
        // FFT tests.

        int n = 4;
        Complex[] x = new Complex[n];

        // original data
        for (int i = 0; i < n; i++) {
            x[i] = new Complex(i, 0);
            x[i] = new Complex(-2*Math.random() + 1, 0);
        }
        FFT.show(x, "x");

        // FFT of original data
        Complex[] y = FFT.fft(x);
        FFT.show(y, "y = fft(x)");

        // take inverse FFT
        Complex[] z = FFT.ifft(y);
        FFT.show(z, "z = ifft(y)");

        // circular convolution of x with itself
        Complex[] c = FFT.cconvolve(x, x);
        FFT.show(c, "c = cconvolve(x, x)");

        // linear convolution of x with itself
        Complex[] d = FFT.convolve(x, x);
        FFT.show(d, "d = convolve(x, x)");
    }

}
