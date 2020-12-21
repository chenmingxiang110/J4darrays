package tests;

import ndArray.NdMath;
import ndArray.NdUtils;

public class testMatrix {

    public static void main(String[] args) {
        double[][] a = new double[][]{new double[]{3,2,1},new double[]{3,2,1},};
        double[][] b = new double[][]{new double[]{2,1},new double[]{1,0},new double[]{-1,-2},};
        double[][] c = NdMath.matmul(a,b);
        NdUtils.printNdArray(a);
        System.out.println();
        NdUtils.printNdArray(b);
        System.out.println();
        NdUtils.printNdArray(c);
        System.out.println();
    }

}
