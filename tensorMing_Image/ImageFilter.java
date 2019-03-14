package tensorMing_Image;

import tensorMing_Fundation.ArrayUtils;
import tensorMing_Fundation.Initializer;
import tensorMing_Fundation.NdArrayMath;
import tensorMing_Fundation.Variable;

import java.util.ArrayList;
import java.util.Arrays;

public class ImageFilter {

    private static int intArraySum(int[] input) {
        int result = 0;
        for (int i : input) {
            result+=i;
        }
        return result;
    }

    private static double[][] gaussianMap(int filterSize) {
        return gaussianMap(filterSize, filterSize, 1.0f);
    }

    private static double[][] gaussianMap(int filterSizeX, int filterSizeY) {
        return gaussianMap(filterSizeX,filterSizeY, 1.0f);
    }

    private static double[][] gaussianMap(int filterSizeX, int filterSizeY, float sigma) {
        float[] xRange = Initializer.arange(filterSizeX, -((float)filterSizeX-1.0f)/2.0f, ((float)filterSizeX-1.0f)/2.0f);
        float[] yRange = Initializer.arange(filterSizeY, -((float)filterSizeY-1.0f)/2.0f, ((float)filterSizeY-1.0f)/2.0f);
        float[][][] gridxy = Initializer.meshgrid(xRange, yRange);
        Variable h1 = new Variable(gridxy[0]);
        Variable h2 = new Variable(gridxy[1]);

        // FUCK ME!!!
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

    private static double[] gaussianMap1d(int filterSize) {
        return gaussianMap1d(filterSize, 1.0f);
    }

    private static double[] gaussianMap1d(int filterSize, float sigma) {
        float[] xRange = Initializer.arange(filterSize, -((float)filterSize-1.0f)/2.0f, ((float)filterSize-1.0f)/2.0f);
        Variable h1 = new Variable(xRange);

        // FUCK ME!!!
        Variable hg = NdArrayMath.exp(
                NdArrayMath.elementWiseDivide(
                        NdArrayMath.elementWiseMultiply(
                                h1, -1.0f),
                        2*((float) Math.pow(sigma, 2))));
        hg = NdArrayMath.elementWiseDivide(hg, NdArrayMath.sum(hg));

        return ArrayUtils.fromFloatArray2Double(hg.get1d());
    }

    private static int[][][] convolve1d(int[][][] image, double[] window, boolean isX) {

        int filterSize = window.length;
        if (filterSize%2==0 || filterSize<=0) {
            throw new IllegalArgumentException("filterSize should be an positive odd number. This will be changed in the future");
        }

        int filterDistance = (filterSize-1)/2;

        int[][][] result = new int[image.length][image[0].length][image[0][0].length];

        for (int x = 0 ; x<image.length ; x++) {
            for (int y = 0 ; y<image[0].length ; y++) {
                for (int z = 0 ; z<image[0][0].length ; z++) {

                    double sum = 0;
                    double factor = 0;

                    if (isX) {
                        for (int i = Math.max(0, x-filterDistance) ; i<Math.min(image.length, x+filterDistance+1) ; i++) {
                            sum+=image[i][y][z]*window[i-x+filterDistance];
                            factor+=window[i-x+filterDistance];
                        }
                        result[x][y][z] = (int)(sum/factor);
                    } else {
                        for (int i = Math.max(0, y-filterDistance) ; i<Math.min(image[1].length, y+filterDistance+1) ; i++) {
                            sum+=image[x][i][z]*window[i-y+filterDistance];
                            factor+=window[i-y+filterDistance];
                        }
                        result[x][y][z] = (int)(sum/factor);
                    }

                }
            }
        }

        return result;

    }

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

    // result[0] is gradX, result[1] = gradY, result[2] = sqrt(gradX**2+gradY**2)
    public static double[][][] sober(int[][][] image) {
        double[][][] result = new double[3][image.length][image[0].length];
        for (int x = 0 ; x<image.length ; x++) {
            for (int y = 0 ; y<image[0].length ; y++) {
                if (x==0 && y==0) {
                    result[0][x][y] = 8*(intArraySum(image[x+1][y])-intArraySum(image[x][y]));
                    result[1][x][y] = 8*(intArraySum(image[x][y+1])-intArraySum(image[x][y]));
                } else if (x==0 && y==(image[0].length-1)) {
                    result[0][x][y] = 8*(intArraySum(image[x+1][y])-intArraySum(image[x][y]));
                    result[1][x][y] = 8*(intArraySum(image[x][y])-intArraySum(image[x][y-1]));
                } else if (x==(image.length-1) && y==0) {
                    result[0][x][y] = 8*(intArraySum(image[x][y])-intArraySum(image[x-1][y]));
                    result[1][x][y] = 8*(intArraySum(image[x][y+1])-intArraySum(image[x][y]));
                } else if (x==(image.length-1) && y==(image[0].length-1)) {
                    result[0][x][y] = 8*(intArraySum(image[x][y])-intArraySum(image[x-1][y]));
                    result[1][x][y] = 8*(intArraySum(image[x][y])-intArraySum(image[x][y-1]));
                } else if (x==0) {
                    result[0][x][y] = 4*(intArraySum(image[x+1][y])-intArraySum(image[x][y]))+
                            2*(intArraySum(image[x+1][y-1])-intArraySum(image[x][y-1]))+
                            2*(intArraySum(image[x+1][y+1])-intArraySum(image[x][y+1]));
                    result[1][x][y] = 4*(intArraySum(image[x][y+1])-intArraySum(image[x][y-1]));
                } else if (x==(image.length-1)) {
                    result[0][x][y] = 4*(intArraySum(image[x][y])-intArraySum(image[x-1][y]))+
                            2*(intArraySum(image[x][y-1])-intArraySum(image[x-1][y-1]))+
                            2*(intArraySum(image[x][y+1])-intArraySum(image[x-1][y+1]));
                    result[1][x][y] = 4*(intArraySum(image[x][y+1])-intArraySum(image[x][y-1]));
                } else if (y==0) {
                    result[0][x][y] = 4*(intArraySum(image[x+1][y])-intArraySum(image[x-1][y]));
                    result[1][x][y] = 4*(intArraySum(image[x][y+1])-intArraySum(image[x][y]))+
                            2*(intArraySum(image[x+1][y+1])-intArraySum(image[x+1][y]))+
                            2*(intArraySum(image[x-1][y+1])-intArraySum(image[x-1][y]));
                } else if (y==(image[0].length-1)) {
                    result[0][x][y] = 4*(intArraySum(image[x+1][y])-intArraySum(image[x-1][y]));
                    result[1][x][y] = 4*(intArraySum(image[x][y])-intArraySum(image[x][y-1]))+
                            2*(intArraySum(image[x+1][y])-intArraySum(image[x+1][y-1]))+
                            2*(intArraySum(image[x-1][y])-intArraySum(image[x-1][y-1]));
                } else {
                    result[0][x][y] = 2*(intArraySum(image[x+1][y])-intArraySum(image[x-1][y]))+
                            (intArraySum(image[x+1][y-1])-intArraySum(image[x-1][y-1]))+
                            (intArraySum(image[x+1][y+1])-intArraySum(image[x-1][y+1]));
                    result[1][x][y] = 2*(intArraySum(image[x][y+1])-intArraySum(image[x][y-1]))+
                            (intArraySum(image[x+1][y+1])-intArraySum(image[x+1][y-1]))+
                            (intArraySum(image[x-1][y+1])-intArraySum(image[x-1][y-1]));
                }
            }
        }
        for (int x = 0 ; x<image.length ; x++) {
            for (int y = 0 ; y<image[0].length ; y++) {
                result[2][x][y] = Math.sqrt(result[0][x][y]*result[0][x][y]+result[1][x][y]*result[1][x][y]);
            }
        }
        return result;
    }

    public static int[][][] gaussianBlur(int[][][] image) {
        return gaussianBlur(image, 5);
    }

    public static int[][][] gaussianBlur(int[][][] image, int filterSize) {
        return gaussianBlur(image, filterSize, 1);
    }

    public static int[][][] gaussianBlur_legacy(int[][][] image, int filterSize, float std) {
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
                    for (int i = Math.max(0, x-filterDistance) ; i<Math.min(image.length, x+filterDistance+1) ; i++) {
                        for (int j = Math.max(0, y-filterDistance) ; j<Math.min(image[0].length, y+filterDistance+1) ; j++) {
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

    public static int[][][] gaussianBlur(int[][][] image, int filterSize, float std) {
        if (filterSize==1) return image;
        if (filterSize%2==0 || filterSize<=0) {
            throw new IllegalArgumentException("filterSize should be an positive odd number. This will be changed in the future");
        }

        int[][][] result;
        double[] index = gaussianMap1d(filterSize, std);

        result = convolve1d(image, index, true);
        result = convolve1d(result, index, false);

        return result;
    }

//    Implementing the box approximation
//    public static int[][][] gaussianBlur_approximate(int[][][] image, int filterSize, float std) {
//        return gaussianBlur_approximate(image, filterSize, std, 3);
//    }
//
//    public static int[][][] gaussianBlur_approximate(int[][][] image, int filterSize, float std, int nbox) {
//        if (filterSize==1) return image;
//        if (filterSize%2==0 || filterSize<=0) {
//            throw new IllegalArgumentException("filterSize should be an positive odd number. This will be changed in the future");
//        }
//
//        if (filterSize<=5) return gaussianBlur(image, filterSize, std);
//    }

    public static double[][] nonMaximumSuppression(double[][][] soberResult) {
        if (soberResult.length!=3) {
            throw new IllegalArgumentException("The input is not a valid sober result.");
        }

        // soberResult[0] is gradX, soberResult[1] = gradY, soberResult[2] = sqrt(gradX**2+gradY**2)
        double[][] gradX = soberResult[0];
        double[][] gradY = soberResult[1];
        boolean[][] isRemain = new boolean[gradX.length][gradX[0].length];
        double[][] result = new double[gradX.length][gradX[0].length];

        for (int x = 0 ; x<gradX.length ; x++) {
            for (int y = 0 ; y<gradX[0].length ; y++) {
                if (x==0) {
                    if (gradX[0][y]>gradX[1][y]) {
                        isRemain[x][y] = true;
                        continue;
                    }
                } else if (x==gradX.length-1) {
                    if (gradX[x][y]>gradX[x-1][y]) {
                        isRemain[x][y] = true;
                        continue;
                    }
                } else {
                    if ((gradX[x][y]>gradX[x-1][y]) && (gradX[x][y]>gradX[x+1][y])) {
                        isRemain[x][y] = true;
                        continue;
                    }
                }

                if (y==0) {
                    if (gradX[x][0]>gradX[x][1]) {
                        isRemain[x][y] = true;
                        continue;
                    }
                } else if (y==gradX[0].length-1) {
                    if (gradX[x][y]>gradX[x][y-1]) {
                        isRemain[x][y] = true;
                        continue;
                    }
                } else {
                    if ((gradX[x][y]>gradX[x][y-1]) && (gradX[x][y]>gradX[x][y+1])) {
                        isRemain[x][y] = true;
                        continue;
                    }
                }
            }
        }

        for (int x = 0 ; x<gradX.length ; x++) {
            for (int y = 0; y < gradX[0].length; y++) {
                if (isRemain[x][y]) result[x][y] = soberResult[2][x][y];
            }
        }

        return result;
    }

}
