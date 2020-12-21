package ndArray;

import java.lang.reflect.Array;
import java.util.ArrayList;

/*
 * 为了统一标准：
 * 1. 所有有返回值的函数，必须不是 inplace 操作。
 * 2. 所有返回值为空的函数，都是 inplace 操作。
 */

public class NdUtils {

    public NdUtils() {
//        if (!array.getClass().isArray()) {
//            throw new IllegalArgumentException("Invalid input. Not an array");
//        }
//        int _length = Array.getLength(array);
//        if (_length==0) {
//            return;
//        }
//        if (array.getClass().getComponentType().isArray()) {
//            for (int i = 0; i < _length; i++) {}
//        } else {}
    }

    public static void printNdArray(Object array) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array");
        }
        int _length = Array.getLength(array);
        if (_length==0) {
            System.out.println("[]");
            return;
        }
        if (array.getClass().getComponentType().isArray()) {
            if (_length<10) {
                for (int i = 0; i < _length; i++) {
                    printNdArray(Array.get(array, i));
                }
            } else {
                printNdArray(Array.get(array, 0));
                printNdArray(Array.get(array, 1));
                printNdArray(Array.get(array, 2));
                System.out.println(" ...");
                printNdArray(Array.get(array, _length-3));
                printNdArray(Array.get(array, _length-2));
                printNdArray(Array.get(array, _length-1));
                System.out.println();
            }
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("[");

            if (_length<10) {
                for (int i = 0; i < _length; i++) {
                    sb.append(Array.get(array, i));
                    if (i<(_length-1)) sb.append(", ");
                }
            } else {
                sb.append(Array.get(array, 0)+", ");
                sb.append(Array.get(array, 1)+", ");
                sb.append(Array.get(array, 2)+", ..., ");
                sb.append(Array.get(array, _length-3)+", ");
                sb.append(Array.get(array, _length-2)+", ");
                sb.append(Array.get(array, _length-1));
            }

            sb.append("]");
            System.out.println(sb.toString());
        }
    }

    public static ArrayList<Double> flatten(Object array) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array");
        }

        ArrayList<Double> result = new ArrayList<Double>();
        int _length = Array.getLength(array);
        if (_length==0) {
            return result;
        }
        if (array.getClass().getComponentType().isArray()) {
            for (int i = 0; i < _length; i++) {
                ArrayList<Double> subresult = flatten(Array.get(array, i));
                for (double d : subresult) result.add(d);
            }
        } else {
            for (int i = 0; i < _length; i++) {
                result.add(Array.getDouble(array,i));
            }
        }
        return result;
    }

    public static void copyArray(Object array, Object target) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array.");
        }
        int _length = Array.getLength(array);
        if (_length==0) {
            return;
        }
        if (_length != Array.getLength(target)) {
            throw new IllegalArgumentException("Inconsistent shape.");
        }
        if (array.getClass().getComponentType().isArray()) {
            for (int i = 0; i < _length; i++) {
                copyArray(Array.get(array, i), Array.get(target, i));
            }
        } else {
            for (int i = 0; i < _length; i++) {
                Array.set(target, i, Array.get(array, i));
            }
        }
    }

    public static int[] shape(Object array) {
        if (array.getClass().getComponentType().isArray()) {
            int length0 = Array.getLength(array);
            if (length0==0) return new int[0];

            int[] subshape = shape(Array.get(array, 0));
            int[] result = new int[subshape.length+1];
            result[0] = length0;
            for (int i = 0; i < subshape.length; i++) {
                result[i+1] = subshape[i];
            }
            return result;
        } else if (array.getClass().isArray()) {
            return new int[]{Array.getLength(array)};
        } else {
            throw new IllegalArgumentException("Invalid input array.");
        }
    }

    public static void reshape(ArrayList<Double> array, Object target) {
        int[] shape = shape(target);
        int _sum = 1;
        for (int i : shape) _sum*=i;
        if (_sum!=array.size()) {
            throw new IllegalArgumentException("Inconsistent number of elements.");
        }

        if (!target.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid target. Not an array");
        }
        int _length = Array.getLength(target);
        if (_length==0) {
            return;
        }
        if (target.getClass().getComponentType().isArray()) {
            int gap = array.size()/Array.getLength(target);
            for (int i = 0; i < _length; i++) {
                reshape(new ArrayList(array.subList(i*gap, (i+1)*gap)), Array.get(target, i));
            }
        } else {
            if (target.getClass().getComponentType().equals(double.class)) {
                for (int i = 0; i < _length; i++) {
                    Array.set(target, i, array.get(i));
                }
            } else {
                throw new IllegalArgumentException("The target should be an array of doubles.");
            }
        }
    }

    public static void reshape(Object array, Object target) {
        ArrayList<Double> data = flatten(array);
        reshape(data, target);
    }

    public static double[] cast(int[] array) {
        double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = (double) array[i];
        }
        return result;
    }

    public static double[][] cast(int[][] array) {
        double[][] result = new double[array.length][];
        for (int i = 0; i < array.length; i++) {
            result[i] = cast(array[i]);
        }
        return result;
    }

    public static double[][][] cast(int[][][] array) {
        double[][][] result = new double[array.length][][];
        for (int i = 0; i < array.length; i++) {
            result[i] = cast(array[i]);
        }
        return result;
    }

    public static double[][][][] cast(int[][][][] array) {
        double[][][][] result = new double[array.length][][][];
        for (int i = 0; i < array.length; i++) {
            result[i] = cast(array[i]);
        }
        return result;
    }

    public static double[][][][][] cast(int[][][][][] array) {
        double[][][][][] result = new double[array.length][][][][];
        for (int i = 0; i < array.length; i++) {
            result[i] = cast(array[i]);
        }
        return result;
    }

    // The values are rounded before casting.
    public static int[] cast(double[] array) {
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = (int) Math.round(array[i]);
        }
        return result;
    }

    public static int[][] cast(double[][] array) {
        int[][] result = new int[array.length][];
        for (int i = 0; i < array.length; i++) {
            result[i] = cast(array[i]);
        }
        return result;
    }

    public static int[][][] cast(double[][][] array) {
        int[][][] result = new int[array.length][][];
        for (int i = 0; i < array.length; i++) {
            result[i] = cast(array[i]);
        }
        return result;
    }

    public static int[][][][] cast(double[][][][] array) {
        int[][][][] result = new int[array.length][][][];
        for (int i = 0; i < array.length; i++) {
            result[i] = cast(array[i]);
        }
        return result;
    }

    public static int[][][][][] cast(double[][][][][] array) {
        int[][][][][] result = new int[array.length][][][][];
        for (int i = 0; i < array.length; i++) {
            result[i] = cast(array[i]);
        }
        return result;
    }

    public static double[][] transpose(double[][] array, int[] dims) {
        if (dims.length!=2) {
            throw new IllegalArgumentException("The dims given are not consistant with the array's dimensions.");
        }

        int[] sizes = new int[]{array.length, array[0].length};
        double[][] result = new double[sizes[dims[0]]][sizes[dims[1]]];
        for (int i = 0; i < sizes[0]; i++) {
            for (int j = 0; j < sizes[1]; j++) {
                int[] dim_indices = new int[]{i,j};
                result[dim_indices[dims[0]]][dim_indices[dims[1]]] = array[i][j];
            }
        }
        return result;
    }

    public static double[][] transpose(double[][] array) {
        return transpose(array, new int[]{1,0});
    }

    public static double[][][] transpose(double[][][] array, int[] dims) {
        if (dims.length!=3) {
            throw new IllegalArgumentException("The dims given are not consistant with the array's dimensions.");
        }

        int[] sizes = new int[]{array.length, array[0].length, array[0][0].length};
        double[][][] result = new double[sizes[dims[0]]][sizes[dims[1]]][sizes[dims[2]]];
        for (int i = 0; i < sizes[0]; i++) {
            for (int j = 0; j < sizes[1]; j++) {
                for (int k = 0; k < sizes[2]; k++) {
                    int[] dim_indices = new int[]{i,j,k};
                    result[dim_indices[dims[0]]][dim_indices[dims[1]]][dim_indices[dims[2]]] = array[i][j][k];
                }
            }
        }
        return result;
    }

    public static double[][][][] transpose(double[][][][] array, int[] dims) {
        if (dims.length!=4) {
            throw new IllegalArgumentException("The dims given are not consistant with the array's dimensions.");
        }

        int[] sizes = new int[]{array.length, array[0].length, array[0][0].length, array[0][0][0].length};
        double[][][][] result = new double[sizes[dims[0]]][sizes[dims[1]]][sizes[dims[2]]][sizes[dims[3]]];
        for (int i = 0; i < sizes[0]; i++) {
            for (int j = 0; j < sizes[1]; j++) {
                for (int k = 0; k < sizes[2]; k++) {
                    for (int l = 0; l < sizes[3]; l++) {
                        int[] dim_indices = new int[]{i,j,k,l};
                        result[dim_indices[dims[0]]][dim_indices[dims[1]]][dim_indices[dims[2]]]
                                [dim_indices[dims[3]]] = array[i][j][k][l];
                    }
                }
            }
        }

        return result;
    }

    public static double[][][][][] transpose(double[][][][][] array, int[] dims) {
        if (dims.length!=5) {
            throw new IllegalArgumentException("The dims given are not consistant with the array's dimensions.");
        }

        int[] sizes = new int[]{array.length, array[0].length, array[0][0].length, array[0][0][0].length, array[0][0][0][0].length};
        double[][][][][] result = new double[sizes[dims[0]]][sizes[dims[1]]][sizes[dims[2]]][sizes[dims[3]]][sizes[dims[4]]];
        for (int i = 0; i < sizes[0]; i++) {
            for (int j = 0; j < sizes[1]; j++) {
                for (int k = 0; k < sizes[2]; k++) {
                    for (int l = 0; l < sizes[3]; l++) {
                        for (int m = 0; m < sizes[4]; m++) {
                            int[] dim_indices = new int[]{i,j,k,l,m};
                            result[dim_indices[dims[0]]][dim_indices[dims[1]]][dim_indices[dims[2]]]
                                    [dim_indices[dims[3]]][dim_indices[dims[4]]] = array[i][j][k][l][m];
                        }
                    }
                }
            }
        }

        return result;
    }

    public static int[][] transpose(int[][] array, int[] dims) {
        if (dims.length!=2) {
            throw new IllegalArgumentException("The dims given are not consistant with the array's dimensions.");
        }

        int[] sizes = new int[]{array.length, array[0].length};
        int[][] result = new int[sizes[dims[0]]][sizes[dims[1]]];
        for (int i = 0; i < sizes[0]; i++) {
            for (int j = 0; j < sizes[1]; j++) {
                int[] dim_indices = new int[]{i,j};
                result[dim_indices[dims[0]]][dim_indices[dims[1]]] = array[i][j];
            }
        }
        return result;
    }

    public static int[][] transpose(int[][] array) {
        return transpose(array, new int[]{1,0});
    }

    public static int[][][] transpose(int[][][] array, int[] dims) {
        if (dims.length!=3) {
            throw new IllegalArgumentException("The dims given are not consistant with the array's dimensions.");
        }

        int[] sizes = new int[]{array.length, array[0].length, array[0][0].length};
        int[][][] result = new int[sizes[dims[0]]][sizes[dims[1]]][sizes[dims[2]]];
        for (int i = 0; i < sizes[0]; i++) {
            for (int j = 0; j < sizes[1]; j++) {
                for (int k = 0; k < sizes[2]; k++) {
                    int[] dim_indices = new int[]{i,j,k};
                    result[dim_indices[dims[0]]][dim_indices[dims[1]]][dim_indices[dims[2]]] = array[i][j][k];
                }
            }
        }
        return result;
    }

    public static int[][][][] transpose(int[][][][] array, int[] dims) {
        if (dims.length!=4) {
            throw new IllegalArgumentException("The dims given are not consistant with the array's dimensions.");
        }

        int[] sizes = new int[]{array.length, array[0].length, array[0][0].length, array[0][0][0].length};
        int[][][][] result = new int[sizes[dims[0]]][sizes[dims[1]]][sizes[dims[2]]][sizes[dims[3]]];
        for (int i = 0; i < sizes[0]; i++) {
            for (int j = 0; j < sizes[1]; j++) {
                for (int k = 0; k < sizes[2]; k++) {
                    for (int l = 0; l < sizes[3]; l++) {
                        int[] dim_indices = new int[]{i,j,k,l};
                        result[dim_indices[dims[0]]][dim_indices[dims[1]]][dim_indices[dims[2]]]
                                [dim_indices[dims[3]]] = array[i][j][k][l];
                    }
                }
            }
        }

        return result;
    }

    public static int[][][][][] transpose(int[][][][][] array, int[] dims) {
        if (dims.length!=5) {
            throw new IllegalArgumentException("The dims given are not consistant with the array's dimensions.");
        }

        int[] sizes = new int[]{array.length, array[0].length, array[0][0].length, array[0][0][0].length, array[0][0][0][0].length};
        int[][][][][] result = new int[sizes[dims[0]]][sizes[dims[1]]][sizes[dims[2]]][sizes[dims[3]]][sizes[dims[4]]];
        for (int i = 0; i < sizes[0]; i++) {
            for (int j = 0; j < sizes[1]; j++) {
                for (int k = 0; k < sizes[2]; k++) {
                    for (int l = 0; l < sizes[3]; l++) {
                        for (int m = 0; m < sizes[4]; m++) {
                            int[] dim_indices = new int[]{i,j,k,l,m};
                            result[dim_indices[dims[0]]][dim_indices[dims[1]]][dim_indices[dims[2]]]
                                    [dim_indices[dims[3]]][dim_indices[dims[4]]] = array[i][j][k][l][m];
                        }
                    }
                }
            }
        }

        return result;
    }
}
