package tensorMing_Image;

import tensorMing_Fundation.ArrayUtils;
import tensorMing_Fundation.Initializer;
import tensorMing_Fundation.NdArrayMath;
import tensorMing_Fundation.Variable;

import java.util.ArrayList;
import java.util.Arrays;

public class ImageFilter {

    public static int[][][] meanBlur(int[][][] image) {
        return meanBlur(image, 5);
    }

    // Can be extremely optimized in the future.
    public static int[][][] meanBlur(int[][][] image, int filterSize) {
        if (filterSize==1) return image;
        if (filterSize%2==0 || filterSize<=0) {
            throw new IllegalArgumentException("filterSize should be an positive odd number. This will be changed in the future");
        }

        int filterDistance = (filterSize-1)/2;
        int[][][] result = new int[image.length][image[0].length][image[0][0].length];

        for (int x = 0 ; x<image.length ; x++) {
            for (int y = 0 ; y<image[0].length ; y++) {
                for (int z = 0 ; z<image[0][0].length ; z++) {
                    int sum = 0;
                    int num = 0;
                    for (int i = Math.max(0, x-filterDistance) ; i<Math.min(image.length, x+filterDistance) ; i++) {
                        for (int j = Math.max(0, y-filterDistance) ; j<Math.min(image[0].length, y+filterDistance) ; j++) {
                            num++;
                            sum+=image[i][j][z];
                        }
                    }
                    result[x][y][z] = sum/num;
                }
            }
        }

        return result;
    }

    public static int[][][] medianBlur(int[][][] image) {
        return medianBlur(image, 5);
    }

    // Can be extremely optimized in the future.
    public static int[][][] medianBlur(int[][][] image, int filterSize) {
        if (filterSize==1) return image;
        if (filterSize%2==0 || filterSize<=0) {
            throw new IllegalArgumentException("filterSize should be an positive odd number. This will be changed in the future");
        }

        int filterDistance = (filterSize-1)/2;
        int[][][] result = new int[image.length][image[0].length][image[0][0].length];

        for (int x = 0 ; x<image.length ; x++) {
            for (int y = 0 ; y<image[0].length ; y++) {
                for (int z = 0 ; z<image[0][0].length ; z++) {
                    ArrayList<Integer> data = new ArrayList<>();
                    for (int i = Math.max(0, x-filterDistance) ; i<Math.min(image.length, x+filterDistance) ; i++) {
                        for (int j = Math.max(0, y-filterDistance) ; j<Math.min(image[0].length, y+filterDistance) ; j++) {
                            data.add(image[i][j][z]);
                        }
                    }
                    int[] dataArray = ArrayUtils.arrayList2IntegerArray(data);
                    Arrays.sort(dataArray);
                    if (dataArray.length%2==0) {
                        result[x][y][z] = (dataArray[dataArray.length/2]+dataArray[dataArray.length/2-1])/2;
                    } else {
                        result[x][y][z] = dataArray[dataArray.length/2];
                    }
                }
            }
        }

        return result;
    }

    public static double[][] gaussianMap(int filterSize) {
        return gaussianMap(filterSize, filterSize, 1.0f);
    }

    public static double[][] gaussianMap(int filterSizeX, int filterSizeY) {
        return gaussianMap(filterSizeX,filterSizeY, 1.0f);
    }

    public static double[][] gaussianMap(int filterSizeX, int filterSizeY, float sigma) {
        float[] xRange = Initializer.arange(filterSizeX, -((float)filterSizeX-1.0f)/2.0f, ((float)filterSizeX-1.0f)/2.0f);
        float[] yRange = Initializer.arange(filterSizeY, -((float)filterSizeY-1.0f)/2.0f, ((float)filterSizeY-1.0f)/2.0f);
        float[][][] gridxy = Initializer.meshgrid(xRange, yRange);
        Variable h1 = new Variable(gridxy[0]);
        Variable h2 = new Variable(gridxy[1]);

        // OH YEAH!!! FUCK ME!!!
        Variable hg = NdArrayMath.exp(
                NdArrayMath.elementWiseDivide(
                        NdArrayMath.elementWiseMultiply(
                                NdArrayMath.elementWiseAdd(
                                        NdArrayMath.pow(h1, 2),
                                        NdArrayMath.pow(h2, 2)),
                                -1.0f),
                        2*((float) Math.pow(sigma, 2))));
        hg = NdArrayMath.elementWiseDivide(hg, NdArrayMath.sum(hg));

        return ArrayUtils.fromFloatArray2Double(hg.get2d());
    }

    public static int[][][] gaussianBlur(int[][][] image) {
        return gaussianBlur(image, 5);
    }

    public static int[][][] gaussianBlur(int[][][] image, int filterSize) {
        return gaussianBlur(image, filterSize, 1);
    }

    // Can be extremely optimized in the future. Check the following link for optimization:
    // http://blog.ivank.net/fastest-gaussian-blur.html
    public static int[][][] gaussianBlur(int[][][] image, int filterSize, float std) {
        if (filterSize==1) return image;
        if (filterSize%2==0 || filterSize<=0) {
            throw new IllegalArgumentException("filterSize should be an positive odd number. This will be changed in the future");
        }

        int filterDistance = (filterSize-1)/2;
        int[][][] result = new int[image.length][image[0].length][image[0][0].length];
        double[][] index = gaussianMap(filterSize, filterSize, std);

        for (int x = 0 ; x<image.length ; x++) {
            for (int y = 0 ; y<image[0].length ; y++) {
                for (int z = 0 ; z<image[0][0].length ; z++) {

                    double sum = 0;
                    double factor = 0;
                    for (int i = Math.max(0, x-filterDistance) ; i<Math.min(image.length, x+filterDistance) ; i++) {
                        for (int j = Math.max(0, y-filterDistance) ; j<Math.min(image[0].length, y+filterDistance) ; j++) {
                            sum+=image[i][j][z]*index[i-x+filterDistance][j-y+filterDistance];
                            factor+=index[i-x+filterDistance][j-y+filterDistance];
                        }
                    }
                    result[x][y][z] = (int)(sum/factor);

                }
            }
        }

        return result;
    }

}
