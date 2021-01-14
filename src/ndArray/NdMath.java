package ndArray;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NdMath {

    public NdMath() {}

    public static double[] convolve(double[] array, double[] filter, boolean isFilterSymmetrical) {
        if (filter.length>array.length) {
            throw new IllegalArgumentException("Filter size: "+filter.length+
                    " is larger than the size of the array: "+array.length+".");
        }
        double[] result = new double[array.length-filter.length+1];

        int n_filters = filter.length;
        // 如果滤波器是对称的
        if (isFilterSymmetrical) {
            // 判断滤波器尺寸是否是双数
            if (filter.length/2*2 == filter.length) {
                n_filters = n_filters/2;
            } else {
                n_filters = n_filters/2+1;
            }
        }

        // 是的，计算卷积我应该用FFT的，但我懒了
        double[][] result_of_filters = new double[n_filters][array.length];
        for (int i = 0; i < n_filters; i++) {
            double[] tmp = new double[array.length];
            for (int j = 0; j < array.length; j++) {
                tmp[j] = array[j]*filter[i];
            }
            result_of_filters[i] = tmp;
        }
        for (int i = 0; i < filter.length; i++) {
            int filter_index = i;
            if (i>=n_filters) {
                if (filter.length/2*2 == filter.length) {
                    filter_index = 2*n_filters-1-i;
                } else {
                    filter_index = 2*n_filters-2-i;
                }
            }
            double[] result_of_filter = result_of_filters[filter_index];
            int start = i;
            int end = i+array.length-filter.length+1;

            for (int j = start; j < end; j++) {
                result[j-start] += result_of_filter[j];
            }
        }
        return result;
    }

    public static double[] convolve(double[] array, double[] filter) {
        return convolve(array, filter, false);
    }

    public static double[][] convolve(double[][] array, double[][] filter, boolean[] isFilterSymmetrical) {
        if (filter.length>array.length || filter[0].length>array[0].length) {
            throw new IllegalArgumentException("Filter size: "+
                    filter.length+", "+filter[0].length+
                    " is larger than the size of the array: "+
                    array.length+", "+array[0].length+".");
        }
        if (isFilterSymmetrical.length != (filter.length+1)) {
            throw new IllegalArgumentException("The length of isFilterSymmetrical should be equal to (filter.length+1).");
        }

        double[][] result = new double[array.length-filter.length+1][array[0].length-filter[0].length+1];

        int n_filters = filter.length;
        // 如果滤波器是对称的
        if (isFilterSymmetrical[0]) {
            // 判断滤波器尺寸是否是双数
            if (filter.length/2*2 == filter.length) {
                n_filters = n_filters/2;
            } else {
                n_filters = n_filters/2+1;
            }
        }

        double[][][] result_of_filters = new double[n_filters][array.length][];
        for (int i = 0; i < n_filters; i++) {
            double[][] tmp = new double[array.length][];
            for (int j = 0; j < array.length; j++) {
                tmp[j] = convolve(array[j], filter[i], isFilterSymmetrical[i+1]);
            }
            result_of_filters[i] = tmp;
        }
        for (int i = 0; i < filter.length; i++) {
            int filter_index = i;
            if (i>=n_filters) {
                if (filter.length/2*2 == filter.length) {
                    filter_index = 2*n_filters-1-i;
                } else {
                    filter_index = 2*n_filters-2-i;
                }
            }
            double[][] result_of_filter = result_of_filters[filter_index];
            int start = i;
            int end = i+array.length-filter.length+1;

            for (int j = start; j < end; j++) {
                for (int k = 0; k < result[j - start].length; k++) {
                    result[j-start][k] += result_of_filter[j][k];
                }
            }
        }
        return result;
    }

    public static double[][] convolve(double[][] array, double[][] filter, boolean isFilterSymmetrical) {
        boolean[] isFilterSymmetricals = new boolean[filter.length+1]; // 初始化默认是false
        if (isFilterSymmetrical) {
            for (int i = 0; i < isFilterSymmetricals.length; i++) {
                isFilterSymmetricals[i] = isFilterSymmetrical;
            }
        }
        return convolve(array, filter, isFilterSymmetricals);
    }

    public static double[][] convolve(double[][] array, double[][] filter) {
        boolean[] isFilterSymmetricals = new boolean[filter.length+1]; // 初始化默认是false
        return convolve(array, filter, isFilterSymmetricals);
    }

    public static double min(ArrayList<Double> array) {
        if (array.size()==0) {
            throw new IllegalArgumentException("Empty Array.");
        }
        double result = array.get(0);
        for (double d : array) {
            if (d<result) result = d;
        }
        return result;
    }

    public static double min(Object array) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array");
        }
        ArrayList<Double> _array = NdUtils.flatten(array);
        return min(_array);
    }

    public static double max(ArrayList<Double> array) {
        if (array.size()==0) {
            throw new IllegalArgumentException("Empty Array.");
        }
        double result = array.get(0);
        for (double d : array) {
            if (d>result) result = d;
        }
        return result;
    }

    public static double max(Object array) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array");
        }
        ArrayList<Double> _array = NdUtils.flatten(array);
        return max(_array);
    }

    public static double sum(ArrayList<Double> array) {
        if (array.size()==0) {
            throw new IllegalArgumentException("Empty Array.");
        }
        double result = 0;
        for (double d : array) {
            result+=d;
        }
        return result;
    }

    public static double sum(Object array) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array");
        }
        ArrayList<Double> _array = NdUtils.flatten(array);
        return sum(_array);
    }

    public static double mean(ArrayList<Double> array) {
        double _sum = sum(array);
        int count = array.size();
        return _sum/count;
    }

    public static double mean(Object array) {
        ArrayList<Double> _array = NdUtils.flatten(array);
        double _sum = sum(_array);
        int count = _array.size();
        return _sum/count;
    }

    // averaging along the first dimension
    public static double reduce(double[] img) {
        return mean(img);
    }

    // averaging along the first dimension
    public static double[] reduce(double[][] img) {
        double[]result = new double[img[0].length];
        for (int i = 0; i < img.length; i++) {
            NdMath.elementwiseAdd(result, img[i]);
        }
        NdMath.elementwiseDivide(result, img.length);
        return result;
    }

    // averaging along the first dimension
    public static double[][] reduce(double[][][] img) {
        double[][] result = new double[img[0].length][img[0][0].length];
        for (int i = 0; i < img.length; i++) {
            NdMath.elementwiseAdd(result, img[i]);
        }
        NdMath.elementwiseDivide(result, img.length);
        return result;
    }

    // averaging along the first dimension
    public static double[][][] reduce(double[][][][] img) {
        double[][][] result = new double[img[0].length][img[0][0].length][img[0][0][0].length];
        for (int i = 0; i < img.length; i++) {
            NdMath.elementwiseAdd(result, img[i]);
        }
        NdMath.elementwiseDivide(result, img.length);
        return result;
    }

    // averaging along the first dimension
    public static double[][][][] reduce(double[][][][][] img) {
        double[][][][] result = new double[img[0].length][img[0][0].length][img[0][0][0].length][img[0][0][0][0].length];
        for (int i = 0; i < img.length; i++) {
            NdMath.elementwiseAdd(result, img[i]);
        }
        NdMath.elementwiseDivide(result, img.length);
        return result;
    }

    public static void ndPow(Object array, double num) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array");
        }
        int _length = Array.getLength(array);
        if (_length==0) {
            return;
        }
        if (array.getClass().getComponentType().isArray()) {
            for (int i = 0; i < _length; i++) {
                ndPow(Array.get(array, i), num);
            }
        } else {
            for (int i = 0; i < _length; i++) {
                Array.set(array, i, Math.pow(Array.getDouble(array, i), num));
            }
        }
    }

    public static void ndPow(Object array, Object array_) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array.");
        }
        int _length = Array.getLength(array);
        if (_length==0) {
            return;
        }
        if (_length != Array.getLength(array_)) {
            throw new IllegalArgumentException("Inconsistent shape.");
        }
        if (array.getClass().getComponentType().isArray()) {
            for (int i = 0; i < _length; i++) {
                ndPow(Array.get(array, i), Array.get(array_, i));
            }
        } else {
            for (int i = 0; i < _length; i++) {
                Array.set(array_, i, Math.pow(Array.getDouble(array, i), Array.getDouble(array_, i)));
            }
        }
    }

    public static void ndSqrt(Object array) {
        ndPow(array, 0.5);
    }

    public static void ndExp(Object array) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array");
        }
        int _length = Array.getLength(array);
        if (_length==0) {
            return;
        }
        if (array.getClass().getComponentType().isArray()) {
            for (int i = 0; i < _length; i++) {
                ndExp(Array.get(array, i));
            }
        } else {
            for (int i = 0; i < _length; i++) {
                Array.set(array, i, Math.exp(Array.getDouble(array, i)));
            }
        }
    }

    public static void ndAbs(Object array) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array");
        }
        int _length = Array.getLength(array);
        if (_length==0) {
            return;
        }
        if (array.getClass().getComponentType().isArray()) {
            for (int i = 0; i < _length; i++) {
                ndAbs(Array.get(array, i));
            }
        } else {
            for (int i = 0; i < _length; i++) {
                Array.set(array, i, Math.abs(Array.getDouble(array, i)));
            }
        }
    }

    public static void ndSin(Object array) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array");
        }
        int _length = Array.getLength(array);
        if (_length==0) {
            return;
        }
        if (array.getClass().getComponentType().isArray()) {
            for (int i = 0; i < _length; i++) {
                ndSin(Array.get(array, i));
            }
        } else {
            for (int i = 0; i < _length; i++) {
                Array.set(array, i, Math.sin(Array.getDouble(array, i)));
            }
        }
    }

    public static void ndCos(Object array) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array");
        }
        int _length = Array.getLength(array);
        if (_length==0) {
            return;
        }
        if (array.getClass().getComponentType().isArray()) {
            for (int i = 0; i < _length; i++) {
                ndCos(Array.get(array, i));
            }
        } else {
            for (int i = 0; i < _length; i++) {
                Array.set(array, i, Math.cos(Array.getDouble(array, i)));
            }
        }
    }

    public static void ndTan(Object array) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array");
        }
        int _length = Array.getLength(array);
        if (_length==0) {
            return;
        }
        if (array.getClass().getComponentType().isArray()) {
            for (int i = 0; i < _length; i++) {
                ndTan(Array.get(array, i));
            }
        } else {
            for (int i = 0; i < _length; i++) {
                Array.set(array, i, Math.tan(Array.getDouble(array, i)));
            }
        }
    }

    public static void ndSinh(Object array) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array");
        }
        int _length = Array.getLength(array);
        if (_length==0) {
            return;
        }
        if (array.getClass().getComponentType().isArray()) {
            for (int i = 0; i < _length; i++) {
                ndSinh(Array.get(array, i));
            }
        } else {
            for (int i = 0; i < _length; i++) {
                Array.set(array, i, Math.sinh(Array.getDouble(array, i)));
            }
        }
    }

    public static void ndCosh(Object array) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array");
        }
        int _length = Array.getLength(array);
        if (_length==0) {
            return;
        }
        if (array.getClass().getComponentType().isArray()) {
            for (int i = 0; i < _length; i++) {
                ndCosh(Array.get(array, i));
            }
        } else {
            for (int i = 0; i < _length; i++) {
                Array.set(array, i, Math.cosh(Array.getDouble(array, i)));
            }
        }
    }

    public static void ndTanh(Object array) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array");
        }
        int _length = Array.getLength(array);
        if (_length==0) {
            return;
        }
        if (array.getClass().getComponentType().isArray()) {
            for (int i = 0; i < _length; i++) {
                ndTanh(Array.get(array, i));
            }
        } else {
            for (int i = 0; i < _length; i++) {
                Array.set(array, i, Math.tanh(Array.getDouble(array, i)));
            }
        }
    }

    public static void ndAsin(Object array) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array");
        }
        int _length = Array.getLength(array);
        if (_length==0) {
            return;
        }
        if (array.getClass().getComponentType().isArray()) {
            for (int i = 0; i < _length; i++) {
                ndAsin(Array.get(array, i));
            }
        } else {
            for (int i = 0; i < _length; i++) {
                Array.set(array, i, Math.asin(Array.getDouble(array, i)));
            }
        }
    }

    public static void ndAcos(Object array) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array");
        }
        int _length = Array.getLength(array);
        if (_length==0) {
            return;
        }
        if (array.getClass().getComponentType().isArray()) {
            for (int i = 0; i < _length; i++) {
                ndAcos(Array.get(array, i));
            }
        } else {
            for (int i = 0; i < _length; i++) {
                Array.set(array, i, Math.acos(Array.getDouble(array, i)));
            }
        }
    }

    public static void ndAtan(Object array) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array");
        }
        int _length = Array.getLength(array);
        if (_length==0) {
            return;
        }
        if (array.getClass().getComponentType().isArray()) {
            for (int i = 0; i < _length; i++) {
                ndAtan(Array.get(array, i));
            }
        } else {
            for (int i = 0; i < _length; i++) {
                Array.set(array, i, Math.atan(Array.getDouble(array, i)));
            }
        }
    }

    public static void ndLog(Object array) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array");
        }
        int _length = Array.getLength(array);
        if (_length==0) {
            return;
        }
        if (array.getClass().getComponentType().isArray()) {
            for (int i = 0; i < _length; i++) {
                ndLog(Array.get(array, i));
            }
        } else {
            for (int i = 0; i < _length; i++) {
                Array.set(array, i, Math.log(Array.getDouble(array, i)));
            }
        }
    }

    public static void ndLog10(Object array) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array");
        }
        int _length = Array.getLength(array);
        if (_length==0) {
            return;
        }
        if (array.getClass().getComponentType().isArray()) {
            for (int i = 0; i < _length; i++) {
                ndLog10(Array.get(array, i));
            }
        } else {
            for (int i = 0; i < _length; i++) {
                Array.set(array, i, Math.log10(Array.getDouble(array, i)));
            }
        }
    }

    public static double dot(double[] array, double[] array_) {
        if (array.length!=array_.length) {
            throw new IllegalArgumentException("Invalid input. Inconsistent vector length.");
        }
        double result = 0;
        for (int i = 0; i < array.length; i++) {
            result+=array[i]*array_[i];
        }
        return result;
    }

    public static double[][] dot(double[][] array, double[][] array_) {
        double[][] result = new double[array.length][array_[0].length];
        for (int i = 0 ; i<array.length ; i++) {
            for (int j = 0 ; j<array_[0].length ; j++) {
                double[] a = array[i];
                double[] b = new double[a.length];
                for (int k = 0 ; k<a.length ; k++) {
                    b[k] = array_[k][j];
                }
                result[i][j] = dot(a,b);
            }
        }
        return result;
    }

    public static void elementwiseAdd(Object array, double adder) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array");
        }
        int _length = Array.getLength(array);
        if (_length==0) {
            return;
        }
        if (array.getClass().getComponentType().isArray()) {
            for (int i = 0; i < _length; i++) {
                elementwiseAdd(Array.get(array, i), adder);
            }
        } else {
            for (int i = 0; i < _length; i++) {
                Array.set(array, i, Array.getDouble(array, i)+adder);
            }
        }
    }

    public static void elementwiseAdd(Object array, Object array_) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array.");
        }
        int _length = Array.getLength(array);
        if (_length==0) {
            return;
        }
        if (_length != Array.getLength(array_)) {
            throw new IllegalArgumentException("Inconsistent shape.");
        }
        if (array.getClass().getComponentType().isArray()) {
            for (int i = 0; i < _length; i++) {
                elementwiseAdd(Array.get(array, i), Array.get(array_, i));
            }
        } else {
            for (int i = 0; i < _length; i++) {
                Array.set(array, i, Array.getDouble(array, i)+Array.getDouble(array_, i));
            }
        }
    }

    public static void elementwiseSubtract(Object array, double subtracter) {
        elementwiseAdd(array, -subtracter);
    }

    public static void elementwiseSubtract(double subtracter, Object array) {
        elementwiseAdd(array, -subtracter);
        elementwiseMultiply(array, -1);
    }

    public static void elementwiseSubtract(Object array, Object array_) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array.");
        }
        int _length = Array.getLength(array);
        if (_length==0) {
            return;
        }
        if (_length != Array.getLength(array_)) {
            throw new IllegalArgumentException("Inconsistent shape.");
        }
        if (array.getClass().getComponentType().isArray()) {
            for (int i = 0; i < _length; i++) {
                elementwiseSubtract(Array.get(array, i), Array.get(array_, i));
            }
        } else {
            for (int i = 0; i < _length; i++) {
                Array.set(array, i, Array.getDouble(array, i)-Array.getDouble(array_, i));
            }
        }
    }

    public static void elementwiseMultiply(Object array, double multiplier) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array");
        }
        int _length = Array.getLength(array);
        if (_length==0) {
            return;
        }
        if (array.getClass().getComponentType().isArray()) {
            for (int i = 0; i < _length; i++) {
                elementwiseMultiply(Array.get(array, i), multiplier);
            }
        } else {
            for (int i = 0; i < _length; i++) {
                Array.set(array, i, Array.getDouble(array, i)*multiplier);
            }
        }
    }

    public static void elementwiseMultiply(Object array, Object array_) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array.");
        }
        int _length = Array.getLength(array);
        if (_length==0) {
            return;
        }
        if (_length != Array.getLength(array_)) {
            throw new IllegalArgumentException("Inconsistent shape.");
        }
        if (array.getClass().getComponentType().isArray()) {
            for (int i = 0; i < _length; i++) {
                elementwiseMultiply(Array.get(array, i), Array.get(array_, i));
            }
        } else {
            for (int i = 0; i < _length; i++) {
                Array.set(array, i, Array.getDouble(array, i)*Array.getDouble(array_, i));
            }
        }
    }

    public static void elementwiseDivide(Object array, double divider) {
        elementwiseMultiply(array, 1.0/divider);
    }

    public static void elementwiseDivide(double divider, Object array) {
        elementwiseMultiply(array, 1.0/divider);
        ndPow(array, -1);
    }

    public static void elementwiseDivide(Object array, Object array_) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array.");
        }
        int _length = Array.getLength(array);
        if (_length==0) {
            return;
        }
        if (_length != Array.getLength(array_)) {
            throw new IllegalArgumentException("Inconsistent shape.");
        }
        if (array.getClass().getComponentType().isArray()) {
            for (int i = 0; i < _length; i++) {
                elementwiseDivide(Array.get(array, i), Array.get(array_, i));
            }
        } else {
            for (int i = 0; i < _length; i++) {
                Array.set(array, i, Array.getDouble(array, i)/Array.getDouble(array_, i));
            }
        }
    }

    public static void replaceNanWith(Object array, double newValue) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array");
        }
        int _length = Array.getLength(array);
        if (_length==0) {
            return;
        }
        if (array.getClass().getComponentType().isArray()) {
            for (int i = 0; i < _length; i++) {
                replaceNanWith(Array.get(array, i), newValue);
            }
        } else {
            for (int i = 0; i < _length; i++) {
                if (Double.isNaN((Double) Array.get(array, i))) {
                    Array.set(array, i, newValue);
                }
            }
        }
    }

}
