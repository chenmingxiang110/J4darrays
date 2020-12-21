package tests;

import ndArray.NdImageIO;
import ndArray.NdImagePro;
import ndArray.NdMath;
import ndArray.NdUtils;

import java.util.ArrayList;

public class testIO {

    public static void main(String[] args) {
//        int[][] _input = new int[][]{new int[]{1,2,3}, new int[]{1,2}, new int[]{1,2,3}};
//        ArrayList<Double> _output = ndArray.NdUtils.flatten(_input);
//        for (int i = 0; i < _output.size(); i++) {
//            System.out.println(_output.get(i));
//        }
//
//        System.out.println(NdMath.mean(_output));

        //////// 分割线 //////// 分割线 //////// 分割线 ////////

//        double[] a = new double[]{1,2,3};
//        System.out.println(a.getClass().getComponentType());
//        System.out.println(a.getClass().getComponentType().equals(double.class));

        //////// 分割线 //////// 分割线 //////// 分割线 ////////

//        double[][] _input = new double[][]{new double[]{1,2,3,4}, new double[]{1,2,3,4}, new double[]{1,2,3,4}};
//        double[][][] _output = new double[2][3][2];
//        NdUtils.reshape(_input, _output);
//        NdUtils.printNdArray(NdUtils.shape(_output));
//        NdUtils.printNdArray(_output);
//        System.out.println();
//        double[][][] _output2 = new double[2][3][2];
//        NdUtils.printNdArray(_output2);
//        System.out.println();
//        NdUtils.copyArray(_output, _output2);
//        NdUtils.printNdArray(_output2);

        //////// 分割线 //////// 分割线 //////// 分割线 ////////

//        String path = "lily.jpeg";
//        int[][][] img = NdImageIO.readImage(path, "chw");
//        img = NdImagePro.resize(img, 256, 256, "chw");
//        NdImageIO.writeImage("lily_resize.jpg", img, "chw");
//        double[][][] img_d = NdUtils.cast(img);
//        NdUtils.printNdArray(ndArray.NdUtils.shape(img_d));
//        System.out.println(NdMath.max(img_d));
//        NdUtils.printNdArray(img_d);
//
//        double[][][] lap_result = new double[][][]{
//                ndArray.NdMath.convolve(img_d[0], ndArray.Filters.laplacian2d3_nine()),
//                ndArray.NdMath.convolve(img_d[1], ndArray.Filters.laplacian2d3_nine()),
//                ndArray.NdMath.convolve(img_d[2], ndArray.Filters.laplacian2d3_nine()),
//        };
//
//        double[][][] _abs = new double[lap_result.length][lap_result[0].length][lap_result[0][0].length];
//        NdUtils.copyArray(lap_result, _abs);
//        NdMath.ndAbs(_abs);
//        double _max = NdMath.max(_abs);
//
//        // 归一化
//        System.out.println(NdMath.min(lap_result)+","+NdMath.max(lap_result));
//        NdMath.elementwiseDivide(lap_result, _max);
//        NdMath.ndAbs(lap_result);
//        System.out.println(NdMath.min(lap_result)+","+NdMath.max(lap_result));
//        NdUtils.printNdArray(ndArray.NdUtils.shape(lap_result));
//
//        // 变成一个 channel
//        double[][] lap_result_reduce = NdMath.reduce(lap_result);
//        System.out.println(NdMath.min(lap_result_reduce)+","+NdMath.max(lap_result_reduce));
//        NdUtils.printNdArray(ndArray.NdUtils.shape(lap_result_reduce));
//
//        // 映射: 0.0-1.0 至 0-255
//        NdMath.elementwiseMultiply(lap_result_reduce, 255);
//        System.out.println(NdMath.min(lap_result_reduce)+","+NdMath.max(lap_result_reduce));
//        NdImageIO.writeImage("lily_p.jpg", lap_result_reduce, "hw");

        //////// 分割线 //////// 分割线 //////// 分割线 ////////

        String path = "lily.jpeg";
        int[][][] img = NdImageIO.readImage(path, "chw");
        img = NdImagePro.rotate(img, 30, "chw");
        NdImageIO.writeImage("lily_rotate.jpg", img, "chw");
    }

}
