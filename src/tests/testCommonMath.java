package tests;

import ndArray.NdCommonMathFunc;
import ndArray.NdMath;
import ndArray.NdUtils;

import java.util.Arrays;

public class testCommonMath {

    public static void main(String[] args) {
        double[] a = new double[]{0.25, 0.5, 2};
        NdCommonMathFunc.softmax(a);
        NdUtils.printNdArray(a);

        a = new double[]{0.25, 0.5, -2000000};
        NdCommonMathFunc.softmax(a);
        NdUtils.printNdArray(a);

        a = new double[]{0.25, 0.5, 2000000};
        NdCommonMathFunc.softmax(a);
        NdUtils.printNdArray(a);

        a = new double[]{0.25, 0.5, Double.NaN};
        NdUtils.printNdArray(a);
        NdMath.replaceNanWith(a, 1);
        NdUtils.printNdArray(a);

        a = new double[]{0.25, 0.5, 0.3};
        ndCommon.DoubleArrayIndexComparator comparator = new ndCommon.DoubleArrayIndexComparator(a);
        Integer[] indices = comparator.createIndexArray();
        Arrays.sort(indices, comparator);
        for (int i : indices) {
            System.out.println(i);
        }

        System.out.println("---------");

        comparator = new ndCommon.DoubleArrayIndexComparator(a, true);
        indices = comparator.createIndexArray();
        Arrays.sort(indices, comparator);
        for (int i : indices) {
            System.out.println(i);
        }

        System.out.println("---------");

        String[] sa = new String[]{"mother", "faker", "fucker", "idiot"};
        ndCommon.StringArrayIndexComparator scomparator = new ndCommon.StringArrayIndexComparator(sa);
        indices = scomparator.createIndexArray();
        Arrays.sort(indices, scomparator);
        for (int i : indices) {
            System.out.println(i);
        }

        System.out.println("---------");

        scomparator = new ndCommon.StringArrayIndexComparator(sa, true);
        indices = scomparator.createIndexArray();
        Arrays.sort(indices, scomparator);
        for (int i : indices) {
            System.out.println(i);
        }

        System.out.println("---------");

        System.out.println(ndCommon.CommonMisc.round(200.3456, 2));
    }
}
