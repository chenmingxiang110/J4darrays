package tests;

import ndArray.NdMath;
import ndArray.NdUtils;

public class testFilters {

    public static void main(String[] args) {
//        ArrayList<double[][]> a = ndArray.Filters.sobel2d(2);
//        utils.Printer.print2dArray(a.get(0));
//        System.out.println();
//        utils.Printer.print2dArray(a.get(1));

        //////// 分割线 //////// 分割线 //////// 分割线 ////////

//        double[][] a = ndArray.Filters.laplacian2d3();
//        utils.Printer.print2dArray(a);

        //////// 分割线 //////// 分割线 //////// 分割线 ////////

//        double[][] a = ndArray.Filters.gaussian2d(2,1);
//        double[] b = ndArray.Filters.gaussian1d(2,1);
//        utils.Printer.print2dArray(a);
//        System.out.println();
//
//        double[] a2 = a[2];
//        double _sum = 0;
//        for (int i = 0; i < a2.length; i++) {
//            _sum += a2[i];
//        }
//        for (int i = 0; i < a2.length; i++) {
//            a2[i] /= _sum;
//        }
//        utils.Printer.print1dArray(b);
//        utils.Printer.print1dArray(a[2]);
//        utils.Printer.print1dArray(a2);
//
//        ////// 分割线 //////// 分割线 //////// 分割线 ////////
//
//        double[][] array = new double[][]{
//                new double[]{1.0, 2.0, 3.0, 4.0, 5.0},
//                new double[]{1.5, 2.5, 3.5, 4.5, 5.5},
//                new double[]{2.0, 3.0, 4.0, 5.0, 6.0},
//                new double[]{2.5, 3.5, 4.5, 5.5, 6.5},
//                new double[]{3.0, 4.0, 5.0, 6.0, 7.0},
//        };
//
//        double[][] filter = new double[][]{
//                new double[]{0.5, 1.5, 0.5},
//                new double[]{1.0, 2.0, 1.0},
//                new double[]{0.5, 1.5, 0.5},
//        };
//
//        double[][] result = NdMath.convolve(array, filter);
//        utils.Printer.print2dArray(result);
//
//        System.out.println();
//
//        result = NdMath.convolve(array, filter, true);
//        utils.Printer.print2dArray(result);
//
//        System.out.println();
//
//        array = new double[][]{
//                new double[]{1.0, 2.0, 3.0, 4.0},
//                new double[]{1.5, 2.5, 3.5, 4.5},
//                new double[]{2.0, 3.0, 4.0, 5.0},
//                new double[]{2.5, 3.5, 4.5, 5.5},
//        };
//
//        filter = new double[][]{
//                new double[]{0.5, 1.0, 1.0, 0.5},
//                new double[]{0.5, 1.0, 1.0, 0.5},
//        };
//
//        result = NdMath.convolve(array, filter);
//        utils.Printer.print2dArray(result);
//
//        System.out.println();
//
//        result = NdMath.convolve(array, filter, true);
//        utils.Printer.print2dArray(result);

        //////// 分割线 //////// 分割线 //////// 分割线 ////////

        double[][][][] aa = new double[3][2][5][4];
        aa[0][1][0][1] = 15;
        double[][][][] ba = NdUtils.transpose(aa, new int[]{0,2,1,3});
        System.out.println(aa[0][1][0][1]+","+ba[0][1][0][1]+","+ba[0][0][1][1]);
        System.out.println(ba.length+","+ba[0].length+","+ba[0][0].length+","+ba[0][0][0].length);
    }

}
