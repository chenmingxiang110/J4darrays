package tensorMing_Fundation;

/******************************************************************************
 *  From: https://introcs.cs.princeton.edu/java/32class/Complex.java.html
 *  Modified by: chenmingxiang110
 *
 *  Compilation:  javac Complex.java
 *  Execution:    java Complex
 *
 *  Data type for complex numbers.
 ******************************************************************************/

import java.util.Objects;

public class Complex {

    private final double re;   // the real part
    private final double im;   // the imaginary part

    // create a new object with the given real and imaginary parts
    public Complex(double real, double imag) {
        re = real;
        im = imag;
    }

    // return a string representation of the invoking Complex object
    public String toString() {
        if (im == 0) return re + "";
        if (re == 0) return im + "i";
        if (im <  0) return re + " - " + (-im) + "i";
        return re + " + " + im + "i";
    }

    // return abs/modulus/magnitude
    public double abs() {
        return Math.hypot(re, im);
    }

    // return angle/phase/argument, normalized to be between -pi and pi
    public double phase() {
        return Math.atan2(im, re);
    }

    // return a new Complex object whose value is (this + b)
    public Complex plus(Complex b) {
        Complex a = this;             // invoking object
        double real = a.re + b.re;
        double imag = a.im + b.im;
        return new Complex(real, imag);
    }

    // return a new Complex object whose value is (this - b)
    public Complex minus(Complex b) {
        Complex a = this;
        double real = a.re - b.re;
        double imag = a.im - b.im;
        return new Complex(real, imag);
    }

    // return a new Complex object whose value is (this * b)
    public Complex times(Complex b) {
        Complex a = this;
        double real = a.re * b.re - a.im * b.im;
        double imag = a.re * b.im + a.im * b.re;
        return new Complex(real, imag);
    }

    // return a / b
    public Complex divides(Complex b) {
        Complex a = this;
        return a.times(b.reciprocal());
    }

    // a static version of plus
    public static Complex plus(Complex a, Complex b) {
        double real = a.re + b.re;
        double imag = a.im + b.im;
        Complex sum = new Complex(real, imag);
        return sum;
    }

    // a static version of minus
    public static Complex minus(Complex a, Complex b) {
        double real = a.re - b.re;
        double imag = a.im - b.im;
        return new Complex(real, imag);
    }

    // a static version of times
    public static Complex times(Complex a, Complex b) {
        double real = a.re * b.re - a.im * b.im;
        double imag = a.re * b.im + a.im * b.re;
        return new Complex(real, imag);
    }

    // a static version of divides
    public static Complex divides(Complex a, Complex b) {
        return a.times(b.reciprocal());
    }

    // return a new object whose value is (this * alpha)
    public Complex scale(double alpha) {
        return new Complex(alpha * re, alpha * im);
    }

    // return a new Complex object whose value is the conjugate of this
    public Complex conjugate() {
        return new Complex(re, -im);
    }

    // return a new Complex object whose value is the reciprocal of this
    public Complex reciprocal() {
        double scale = re*re + im*im;
        return new Complex(re / scale, -im / scale);
    }

    // return the real or imaginary part
    public double re() { return re; }
    public double im() { return im; }

    // return a new Complex object whose value is the complex exponential of this
    public Complex exp() {
        return new Complex(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
    }

    // return a new Complex object whose value is the complex sine of this
    public Complex sin() {
        return new Complex(Math.sin(re) * Math.cosh(im), Math.cos(re) * Math.sinh(im));
    }

    // return a new Complex object whose value is the complex cosine of this
    public Complex cos() {
        return new Complex(Math.cos(re) * Math.cosh(im), -Math.sin(re) * Math.sinh(im));
    }

    // return a new Complex object whose value is the complex tangent of this
    public Complex tan() {
        return sin().divides(cos());
    }

    // real^2 + imag^2
    public static double squareSum(Complex a) {
        return Math.pow(a.re, 2)+Math.pow(a.im, 2);
    }

    // sqrt(real^2 + imag^2)
    public static double modularL(Complex a) {
        return Math.sqrt(squareSum(a));
    }

    public static double[] realArray(Complex[] a) {
        double[] result = new double[a.length];
        for (int i = 0 ; i<a.length ; i++) {
            result[i] = a[i].re;
        }
        return result;
    }

    public static double[] imagArray(Complex[] a) {
        double[] result = new double[a.length];
        for (int i = 0 ; i<a.length ; i++) {
            result[i] = a[i].im;
        }
        return result;
    }

    public static double[] squareSumArray(Complex[] a) {
        double[] result = new double[a.length];
        for (int i = 0 ; i<a.length ; i++) {
            result[i] = squareSum(a[i]);
        }
        return result;
    }

    public static double[] modularLengthArray(Complex[] a) {
        double[] result = new double[a.length];
        for (int i = 0 ; i<a.length ; i++) {
            result[i] = modularL(a[i]);
        }
        return result;
    }

    // Construct an array of complex numbers from an integer array.
    public static Complex[] fromArray(int[] a) {
        Complex[] result = new Complex[a.length];
        for (int i = 0 ; i<a.length ; i++) {
            result[i] = new Complex(a[i], 0);
        }
        return result;
    }

    // Construct an array of complex numbers from an float array.
    public static Complex[] fromArray(float[] a) {
        Complex[] result = new Complex[a.length];
        for (int i = 0 ; i<a.length ; i++) {
            result[i] = new Complex(a[i], 0);
        }
        return result;
    }

    // Construct an array of complex numbers from an double array.
    public static Complex[] fromArray(double[] a) {
        Complex[] result = new Complex[a.length];
        for (int i = 0 ; i<a.length ; i++) {
            result[i] = new Complex(a[i], 0);
        }
        return result;
    }

    public boolean equals(Object x) {
        if (x == null) return false;
        if (this.getClass() != x.getClass()) return false;
        Complex that = (Complex) x;
        return (this.re == that.re) && (this.im == that.im);
    }

    public int hashCode() {
        return Objects.hash(re, im);
    }

}