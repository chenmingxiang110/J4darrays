package tensorMing_Fundation;

import java.util.ArrayList;
import java.util.Arrays;

public class UnitTests {

    public static void main(String[] args) {
//        testUtils1();
//        testUtils2();
//        testSaveLoad();
//        testMath1();
//        testUtils3();
//        testPrint();
        System.out.println("");
        System.out.println("The test is very limited, please feel free to add more tests.");
    }

    public static void testUtils1() {
        System.out.println("Hello dumb.");
        Variable v1 = new Variable("v1", new int[]{3}, "zeros");
        Variable v2 = new Variable("v2", new int[]{3,3}, "zeros");
        Variable v3 = new Variable("v3", new int[]{3,6,9}, "zeros");
        Variable v4 = new Variable("v4", new int[]{3,6,9,12}, "zeros");
        Variable v2r = new Variable("v2r", new int[]{3,3}, "random");
        Variable v2x = new Variable("v2x", new int[]{3,3}, "xavier");

        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v2r);
        System.out.println(v2x);

        System.out.println(v3.getDimension());
        System.out.println(v3.getShape().length);
        System.out.println(v3.getShape()[0]+","+v3.getShape()[1]+","+v3.getShape()[2]);

        System.out.println(v4.getDimension());
        System.out.println(v4.getShape().length);
        System.out.println(v4.getShape()[0]+","+v4.getShape()[1]+","+v4.getShape()[2]+","+v4.getShape()[3]);

        Variable v2_200_1 = new Variable("v221", new int[]{200,200}, "xavier");
        Variable v2_200_2 = new Variable("v222", new int[]{200,200}, "xavier");
        Variable v2_200_12_mm = NdArrayUtils.matmul(v2_200_1, v2_200_2);
        Variable v2_200_12_a = NdArrayUtils.elementWiseAdd(v2_200_1, v2_200_2);
        Variable v2_200_12_s = NdArrayUtils.elementWiseSub(v2_200_1, v2_200_2);
        Variable v2_200_12_m = NdArrayUtils.elementWiseMultiply(v2_200_1, v2_200_2);
        Variable v2_200_12_d = NdArrayUtils.elementWiseDivide(v2_200_1, v2_200_2);

        System.out.println("v2_200_1");
        System.out.println(v2_200_1);
        System.out.println("v2_200_2");
        System.out.println(v2_200_2);
        System.out.println("v2_200_12_mm");
        System.out.println(v2_200_12_mm);
        System.out.println("v2_200_12_a");
        System.out.println(v2_200_12_a);
        System.out.println("v2_200_12_s");
        System.out.println(v2_200_12_s);
        System.out.println("v2_200_12_m");
        System.out.println(v2_200_12_m);
        System.out.println("v2_200_12_d");
        System.out.println(v2_200_12_d);
    }

    public static void testUtils2() {
        System.out.println("Hello dumb.");
        Variable v1s = new Variable("v1s", new int[]{10}, "xavier");
        Variable v1s2 = new Variable("v1s2", new int[]{10}, "xavier");
        Variable v1s3 = new Variable("v1s3", new int[]{10}, "xavier");
        Variable v1l = new Variable("v1l", new int[]{32}, "xavier");
        Variable v2 = new Variable("v2", new int[]{3,3}, "zeros");
        Variable v2r = new Variable("v2r", new int[]{3,3}, "random");
        Variable v2x = new Variable("v2x", new int[]{3,3}, "xavier");
        Variable v3 = new Variable("v3", new int[]{3,6,9}, "xavier");
        Variable v4 = new Variable("v4", new int[]{3,6,9,12}, "xavier");

        System.out.println(v2x);
        System.out.println(NdArrayUtils.transpose(v2x));
        System.out.println("=============");

        System.out.println(v1s);
        System.out.println(NdArrayUtils.repeat(v1s, new int[]{3,10}));
        System.out.println("=============");

        ArrayList<Variable> v1ss = new ArrayList<>();
        v1ss.add(v1s);
        v1ss.add(v1s2);
        v1ss.add(v1s3);
        System.out.println(v1s);
        System.out.println(v1s2);
        System.out.println(v1s3);
        System.out.println(NdArrayUtils.concatenate(v1ss));
        System.out.println("=============");

        System.out.println(v2r);
        System.out.println(NdArrayUtils.min(v2r));
        System.out.println(NdArrayUtils.max(v2r));
        System.out.println("=============");

        System.out.println(v1l);
        System.out.println(NdArrayUtils.split(v1l, 10, 5));
        System.out.println(NdArrayUtils.doubleSplit(v1l, 10, 5, 3, 2).serialize());
        System.out.println("=============");
    }

    public static void testSaveLoad() {
        System.out.println("Hello dumb.");
        Variable v21 = new Variable("v21", new int[]{3,3}, "zeros");
        Variable v22 = NdArrayUtils.elementWiseAdd(v21, 1);
        Variable v23 = NdArrayUtils.elementWiseAdd(v21, 1);
        Variable v2r = new Variable("v2r", new int[]{3,3}, "random");
        Variable v4 = new Variable("v4", new int[]{3,6,9,12}, "xavier");

        System.out.println(v21.equals(v22));
        System.out.println(v22.equals(v22));
        System.out.println(v23.equals(v22));
        System.out.println(v23.equals(v2r));
        System.out.println(v23.equals(v4));
        System.out.println("================");

        v2r.save("tempFile.txt");
        Variable v2rLoad = new Variable("temp", "tempFile.txt");
        System.out.println(v2r);
        System.out.println(v2rLoad);
        System.out.println(v2r.equals(v2rLoad));

        v2r.save("tempFile.txt");
        v2rLoad = new Variable("temp", "tempFile.txt");
        System.out.println(v2r.equals(v2rLoad));

        v4.save("tempFile.txt");
        Variable v4Load = new Variable("temp", "tempFile.txt");
        System.out.println(v4.equals(v4Load));
        System.out.println("================");
    }

    public static void testMath1() {
        System.out.println("Hello dumb.");
        Variable v1 = new Variable("v1", new int[]{10}, "random");
        Variable v2 = new Variable("v1", new int[]{10}, "random");

        System.out.println(v1);
        System.out.println(NdArrayMath.abs(v1));
        System.out.println(NdArrayMath.exp(v1));
        System.out.println(NdArrayMath.sin(v1));
        System.out.println(NdArrayMath.sqrt(v1));
        System.out.println(NdArrayMath.pow(v1,2));
        System.out.println("=============");

        System.out.println(v1);
        System.out.println(v2);
        System.out.println(NdArrayMath.modularL(v1));
        System.out.println(NdArrayMath.distance(v1, v2));
        System.out.println(NdArrayMath.cosineSim(v1, v2));
        System.out.println("=============");
    }

    public static void testUtils3() {
        System.out.println("Hello dumb.");
        Variable v3 = new Variable("v3", new int[]{3,6,9}, "xavier");

        System.out.println(v3);
        System.out.println(NdArrayUtils.sum(v3));
        System.out.println(NdArrayUtils.mean(v3, 1));
        System.out.println(Arrays.toString(NdArrayUtils.transpose(v3, new int[]{2,0,1}).getShape()));
        System.out.println(NdArrayUtils.reshape(v3, new int[]{18,9}));
    }

    public static void testPrint() {
        Variable v4 = new Variable("v4", new int[]{3,6,9,12}, "xavier");
        System.out.println(v4);
    }

}
