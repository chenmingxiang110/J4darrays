package tensorMing_Fundation;

import tensorMing_Learn.*;

import java.lang.reflect.Array;
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
//        testMath2();
//        testDBScan();
//        testRandom();
//        testRandom2();
//        testKMeans();
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

    public static void testMath2() {
        Variable v3 = NdArrayUtils.elementWiseSub(new Variable("v3", new int[]{2,4,6}, "random"), (float)0.5);
        System.out.println(v3);
        System.out.println("----------------");
        System.out.println(NdArrayMath.sigmoid(v3));
        System.out.println("----------------");
        System.out.println(NdArrayMath.relu(v3));
        System.out.println("----------------");
        System.out.println(NdArrayMath.elu(v3));
    }

    public static void testDBScan() {
        ClusterDBScan cdb = new ClusterDBScan((float)1.5, 4);
        ArrayList<Variable> av = new ArrayList<Variable>();
        for (int i = 0 ; i<11 ; i++) {
            av.add(new Variable(new float[]{0, i}));
            av.add(new Variable(new float[]{1, i}));
        }
        av.add(new Variable(new float[]{9, 0}));
        av.add(new Variable(new float[]{9, 1}));
        av.add(new Variable(new float[]{10, 0}));
        av.add(new Variable(new float[]{10, 1}));
        av.add(new Variable(new float[]{10, 10}));

        cdb.fit(av);
        System.out.println(Arrays.toString(cdb.getLabels()));
    }

    public static void testRandom() {
        int[] theArray1 = Random.sampling(10, 20, false);
        int[] theArray2 = Random.sampling(10, 20, false);
        int[] theArray3 = Random.sampling(10, 20, false);
        System.out.println(Arrays.toString(theArray1));
        System.out.println(Arrays.toString(theArray2));
        System.out.println(Arrays.toString(theArray3));
        System.out.println("------------");
        theArray1 = Random.sampling(10, 20, true);
        theArray2 = Random.sampling(10, 20, true);
        theArray3 = Random.sampling(10, 20, true);
        System.out.println(Arrays.toString(theArray1));
        System.out.println(Arrays.toString(theArray2));
        System.out.println(Arrays.toString(theArray3));
        System.out.println("------------");
        theArray1 = Random.sampling(10, 10, false);
        theArray2 = Random.sampling(10, 10, false);
        theArray3 = Random.sampling(10, 10, false);
        System.out.println(Arrays.toString(theArray1));
        System.out.println(Arrays.toString(theArray2));
        System.out.println(Arrays.toString(theArray3));
    }

    public static void testRandom2() {
        float sum = 0;
        for (int i = 0 ; i<1000 ; i++) {
            int r = Random.randInt(new float[]{(float)0.1, (float)0.9});
            sum+=r;
        }
        System.out.println(sum);

        sum = 0;
        for (int i = 0 ; i<1000 ; i++) {
            int[] r = Random.sampling(1, new float[]{(float)0.1, (float)0.9}, true);
            sum+=r[0];
        }
        System.out.println(sum);

        sum = 0;
        for (int i = 0 ; i<1000 ; i++) {
            int[] r = Random.sampling(2, new float[]{(float)0.1, (float)0.9}, true);
            sum+=r[0];
            sum+=r[1];
        }
        System.out.println(sum);

        sum = 0;
        for (int i = 0 ; i<1000 ; i++) {
            int[] r = Random.sampling(1, new float[]{(float)0.1, (float)0.9}, false);
            sum+=r[0];
        }
        System.out.println(sum);

        sum = 0;
        for (int i = 0 ; i<1000 ; i++) {
            int[] r = Random.sampling(2, new float[]{(float)0.1, (float)0.9}, false);
            sum+=r[0];
            sum+=r[1];
        }
        System.out.println(sum);
    }

    public static void testKMeans() {
        ArrayList<Variable> av = new ArrayList<Variable>();
        av.add(new Variable(new float[]{9, 9}));
        av.add(new Variable(new float[]{0, 0}));
        av.add(new Variable(new float[]{0, 1}));
        av.add(new Variable(new float[]{1, 0}));
        av.add(new Variable(new float[]{1, 1}));
        av.add(new Variable(new float[]{9, 0}));

        ClusterKMeans ckm = new ClusterKMeans();

        System.out.println("Random Initialization");
        for (int i = 0 ; i<10 ; i++) {
            ckm.initCentroids(av, 3);
            System.out.println(ckm.getCentroids());
        }

        System.out.println("--------------------");

        System.out.println("Plus Initialization");
        for (int i = 0 ; i<10 ; i++) {
            ckm.initCentroidsPlus(av, 3);
            System.out.println(ckm.getCentroids());
        }

        av.add(new Variable(new float[]{2, 3}));
        av.add(new Variable(new float[]{4, 5}));
        av.add(new Variable(new float[]{3, 4}));
        av.add(new Variable(new float[]{3, 3}));
        av.add(new Variable(new float[]{6, 7}));
        av.add(new Variable(new float[]{1, 4}));
        av.add(new Variable(new float[]{(float)-2.2, 3}));
        av.add(new Variable(new float[]{4, (float)-2.5}));
        av.add(new Variable(new float[]{(float)2.3, -4}));
        av.add(new Variable(new float[]{(float)-2.3, 3}));
        av.add(new Variable(new float[]{-6, (float)2.7}));
        av.add(new Variable(new float[]{1, (float)-2.4}));
        av.add(new Variable(new float[]{-2, 3}));
        av.add(new Variable(new float[]{4, -5}));
        av.add(new Variable(new float[]{3, -4}));
        av.add(new Variable(new float[]{-3, 3}));
        av.add(new Variable(new float[]{-6, 7}));
        av.add(new Variable(new float[]{1, -4}));

        System.out.println("--------------------");

        System.out.println("Normal Fit");
        ckm.initCentroids(av, 5);
        for (int i = 0 ; i<10 ; i++) {
            System.out.println(Arrays.toString(ckm.getLabels()));
            ckm.fit();
        }
        System.out.println(Arrays.toString(ckm.getLabels()));

        System.out.println("Smart Init Fit");
        ckm.initCentroidsPlus(av, 4);
        for (int i = 0 ; i<10 ; i++) {
            System.out.println(Arrays.toString(ckm.getLabels()));
            ckm.fit();
        }
        System.out.println(Arrays.toString(ckm.getLabels()));
    }

}
