package ndArray;

import java.util.ArrayList;

public class Filters {
    public Filters() {}

    public static double[][] averaging2d(int size) {
        double[][] result = new double[size][size];
        double value = 1.0/(size*size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = value;
            }
        }
        return result;
    }

    public static double[] gaussian1d(int size, double sigma) {
        double[] y = new double[size*2+1];
        double _sum = 0;
        for (int i = 0; i < y.length; i++) {
            double x = i-size;
            y[i] = Math.exp(- x * x / (2 * sigma * sigma));
            _sum += y[i];
        }
        for (int i = 0; i < y.length; i++) {
            y[i] /= _sum;
        }
        return y;
    }

    public static double[][] gaussian2d(int size, double sigma) {
        double[][] y = new double[size*2+1][size*2+1];
        double _sum = 0;
        for (int i = 0; i < y.length; i++) {
            for (int j = 0; j < y[0].length; j++) {
                double x0 = i-size;
                double x1 = j-size;
                y[i][j] = Math.exp(-(x0 * x0 + x1 * x1) / (2 * sigma * sigma)) / (2 * Math.PI * sigma * sigma);
                _sum += y[i][j];
            }
        }
        for (int i = 0; i < y.length; i++) {
            for (int j = 0; j < y[0].length; j++) {
                y[i][j] /= _sum;
            }
        }
        return y;
    }

    public static ArrayList<double[][]> sobel2d(int size) {
        if (size<1) {
            throw new IllegalArgumentException("Invalid size.");
        }
        double[][] gx = new double[size*2+1][size*2+1];
        double[][] gy = new double[size*2+1][size*2+1];

        for (int row = 0 ; row<(size*2+1) ; row++) {
            for (int col = 0 ; col<(size*2+1) ; col++) {
                int i = row-size;
                int j = col-size;
                gx[row][col] = j / Math.max((i*i+j*j), 1e-6);
                gy[row][col] = i / Math.max((i*i+j*j), 1e-6);
            }
        }

        ArrayList<double[][]> result = new ArrayList<double[][]>();
        result.add(gx);
        result.add(gy);

        return result;
    }

    public static double[] laplacian1d3() {
        double[] result = new double[]{1,-2,1};
        return result;
    }

    public static double[][] laplacian2d3_five() {
        double[][] result = new double[][]{
                new double[]{0, 1,0},
                new double[]{1,-4,1},
                new double[]{0, 1,0}
        };
        return result;
    }

    public static double[][] laplacian2d3_nine() {
        double[][] result = new double[][]{
                new double[]{0.25,0.5,0.25},
                new double[]{ 0.5, -3, 0.5},
                new double[]{0.25,0.5,0.25}
        };
        return result;
    }
}
