package tensorMing_Fundation;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/******************************************************************************
 *  Created by: chenmingxiang110
 *
 *  Just some utilities like float[] to double[], double[] to float[], arraylist
 *  to array, etc. I really do not like to build this class, but ... fuck this,
 *  it's my work. What can I do? In days like this I hate Java, extremely.
 *
 *  My dear friend, switch to python if you see this, please.
 ******************************************************************************/

public class ArrayUtils {

    public static byte[] floatToByte(float[] input) {
        byte[] ret = new byte[input.length*4];
        for (int x = 0; x < input.length; x++) {
            ByteBuffer.wrap(ret, x*4, 4).putFloat(input[x]);
        }
        return ret;
    }

    public static float[] byteToFloat(byte[] input) {
        float[] ret = new float[input.length/4];
        for (int x = 0; x < input.length; x+=4) {
            ret[x/4] = ByteBuffer.wrap(input, x, 4).getFloat();
        }
        return ret;
    }

    public static float[] byteToFloat(byte[] input, boolean reverse) {
        float[] ret = new float[input.length/4];
        for (int x = 0; x < input.length; x+=4) {
            if (reverse) {
                byte[] current = new byte[]{input[x+3],input[x+2],input[x+1],input[x]};
                ret[x/4] = ByteBuffer.wrap(current, 0, 4).getFloat();
            } else {
                ret[x/4] = ByteBuffer.wrap(input, x, 4).getFloat();
            }
        }
        return ret;
    }

    public static byte[] intToByte(int[] input) {
        byte[] ret = new byte[input.length*4];
        for (int x = 0; x < input.length; x++) {
            ByteBuffer.wrap(ret, x*4, 4).putInt(input[x]);
        }
        return ret;
    }

    public static int[] byteToInt(byte[] input) {
        int[] ret = new int[input.length/4];
        for (int x = 0; x < input.length; x+=4) {
            ret[x/4] = ByteBuffer.wrap(input, x, 4).getInt();
        }
        return ret;
    }

    public static int[] byteToInt(byte[] input, boolean reverse) {
        int[] ret = new int[input.length/4];
        for (int x = 0; x < input.length; x+=4) {
            if (reverse) {
                byte[] current = new byte[]{input[x+3],input[x+2],input[x+1],input[x]};
                ret[x/4] = ByteBuffer.wrap(current, 0, 4).getInt();
            } else {
                ret[x/4] = ByteBuffer.wrap(input, x, 4).getInt();
            }
        }
        return ret;
    }

    public static double[] fromFloatArray2Double(float[] input) {
        double[] result = new double[input.length];
        for (int i = 0 ; i<input.length ; i++) {
            result[i] = (double) (input[i]);
        }
        return result;
    }

    public static double[] fromIntArray2Double(int[] input) {
        double[] result = new double[input.length];
        for (int i = 0 ; i<input.length ; i++) {
            result[i] = (double) (input[i]);
        }
        return result;
    }

    public static float[] fromDoubleArray2Float(double[] input) {
        float[] result = new float[input.length];
        for (int i = 0 ; i<input.length ; i++) {
            result[i] = (float) (input[i]);
        }
        return result;
    }

    public static float[] fromIntArray2Float(int[] input) {
        float[] result = new float[input.length];
        for (int i = 0 ; i<input.length ; i++) {
            result[i] = (float) (input[i]);
        }
        return result;
    }

    public static int[] fromDoubleArray2Int(double[] input) {
        int[] result = new int[input.length];
        for (int i = 0 ; i<input.length ; i++) {
            result[i] = (int) (input[i]);
        }
        return result;
    }

    public static int[] fromFloatArray2Int(float[] input) {
        int[] result = new int[input.length];
        for (int i = 0 ; i<input.length ; i++) {
            result[i] = (int) (input[i]);
        }
        return result;
    }

    public static double[][] fromFloatArray2Double(float[][] input) {
        double[][] result = new double[input.length][input[0].length];
        for (int i = 0 ; i<input.length ; i++) {
            result[i] = fromFloatArray2Double(input[i]);
        }
        return result;
    }

    public static double[][] fromIntArray2Double(int[][] input) {
        double[][] result = new double[input.length][input[0].length];
        for (int i = 0 ; i<input.length ; i++) {
            result[i] = fromIntArray2Double(input[i]);
        }
        return result;
    }

    public static float[][] fromDoubleArray2Float(double[][] input) {
        float[][] result = new float[input.length][input[0].length];
        for (int i = 0 ; i<input.length ; i++) {
            result[i] = fromDoubleArray2Float(input[i]);
        }
        return result;
    }

    public static float[][] fromIntArray2Float(int[][] input) {
        float[][] result = new float[input.length][input[0].length];
        for (int i = 0 ; i<input.length ; i++) {
            result[i] = fromIntArray2Float(input[i]);
        }
        return result;
    }

    public static int[][] fromDoubleArray2Int(double[][] input) {
        int[][] result = new int[input.length][input[0].length];
        for (int i = 0 ; i<input.length ; i++) {
            result[i] = fromDoubleArray2Int(input[i]);
        }
        return result;
    }

    public static int[][] fromFloatArray2Int(float[][] input) {
        int[][] result = new int[input.length][input[0].length];
        for (int i = 0 ; i<input.length ; i++) {
            result[i] = fromFloatArray2Int(input[i]);
        }
        return result;
    }

    public static double[] arrayList2DoubleArray(ArrayList input) {
        double[] result = new double[input.size()];
        for (int i = 0 ; i<input.size() ; i++) {
            result[i] = (double) (input.get(i));
        }
        return result;
    }

    public static float[] arrayList2FloatArray(ArrayList input) {
        float[] result = new float[input.size()];
        for (int i = 0 ; i<input.size() ; i++) {
            result[i] = (float) (input.get(i));
        }
        return result;
    }

    public static int[] arrayList2IntegerArray(ArrayList input) {
        int[] result = new int[input.size()];
        for (int i = 0 ; i<input.size() ; i++) {
            result[i] = (int) (input.get(i));
        }
        return result;
    }

    public static boolean[] arrayList2BooleanArray(ArrayList input) {
        boolean[] result = new boolean[input.size()];
        for (int i = 0 ; i<input.size() ; i++) {
            result[i] = (boolean) (input.get(i));
        }
        return result;
    }

    public static char[] arrayList2CharacterArray(ArrayList input) {
        char[] result = new char[input.size()];
        for (int i = 0 ; i<input.size() ; i++) {
            result[i] = (char) (input.get(i));
        }
        return result;
    }

    public static String[] arrayList2StringArray(ArrayList<String> input) {
        String[] result = new String[input.size()];
        for (int i = 0 ; i<input.size() ; i++) {
            result[i] = input.get(i);
        }
        return result;
    }

}
