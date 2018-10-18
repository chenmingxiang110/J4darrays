## Introduction

Although this package is not camparable to numpy or tensorflow, but it is meant to make matrix manipulation and machine learning easier on Java. You can build ndArrays and caculations on them much easier than using the vanilla arrays.

## Quick Start

The following are some code snippets that you may be interested.

1. Math examples
```java
public static void main(String[] args) {
    Variable v1 = new Variable("v1", new int[]{10}, "random");
    Variable v2 = new Variable("v1", new int[]{10}, "random");
    System.out.println(v1);
    System.out.println(NdArrayMath.abs(v1));
    System.out.println(NdArrayMath.exp(v1));
    System.out.println(NdArrayMath.sin(v1));
    System.out.println(NdArrayMath.sqrt(v1));
    System.out.println(NdArrayMath.pow(v1,2));
    System.out.println("=============");
}
```
Output:
```
[0.2751375, 0.88426, 0.47232184, 0.055898003, 0.45938385, 0.4172129, 0.45583743, 0.9337849, 0.6923709, 0.28284898]
[0.2751375, 0.88426, 0.47232184, 0.055898003, 0.45938385, 0.4172129, 0.45583743, 0.9337849, 0.6923709, 0.28284898]
[1.3167118, 2.421192, 1.6037134, 1.0574899, 1.5830983, 1.5177256, 1.5774939, 2.5441203, 1.998448, 1.3269048]
[0.27167928, 0.77344614, 0.45495513, 0.055868898, 0.4433959, 0.405214, 0.4402144, 0.80387694, 0.63836396, 0.27909255]
[0.52453554, 0.940351, 0.68725675, 0.23642759, 0.6777786, 0.6459202, 0.6751573, 0.96632546, 0.8320883, 0.5318355]
[0.07570065, 0.7819157, 0.22308792, 0.0031245868, 0.21103352, 0.1740666, 0.20778777, 0.87195426, 0.47937745, 0.080003545]
=============
```

2. Simple neural net:
The following code shows how to build a series of fully connected layers to fit y = x^2.
```java
public static void main(String[] args) {
    Variable x = new Variable("temp", new int[]{10}, "arange");
    x = NdArrayUtils.repeat(x, new int[]{1,10});
    x = NdArrayUtils.transpose(x);
    Variable y = NdArrayUtils.elementWiseMultiply(x,x);

    Variable m1 = new Variable("m1", new int[]{1,10}, "xavier");
    Variable b1 = new Variable("b1", new int[]{10}, "xavier");
    Variable m2 = new Variable("m2", new int[]{10,10}, "xavier");
    Variable b2 = new Variable("b2", new int[]{10}, "xavier");
    Variable m3 = new Variable("m3", new int[]{10,1}, "xavier");
    Variable b3 = new Variable("b3", new int[]{1}, "xavier");

    int NUM_STEP = 1000000;
    float LEARNING_RATE = (float) 0.00001;
    int PRINT_PERIOD = 100000;

    for (int i = 0 ; i<NUM_STEP ; i++) {
        Variable[] outputm1 = ActivationFcns.Relu(NdArrayUtils.matmul(x, m1));
        Variable output1 = NdArrayUtils.elementWiseAdd(outputm1[0], NdArrayUtils.repeat(b1, new int[]{10,10}));
        Variable[] outputm2 = ActivationFcns.Sigmoid(NdArrayUtils.matmul(output1, m2));
        Variable output2 = NdArrayUtils.elementWiseAdd(outputm2[0], NdArrayUtils.repeat(b2, new int[]{10,10}));
        Variable output3 = NdArrayUtils.elementWiseAdd(NdArrayUtils.matmul(output2, m3), NdArrayUtils.repeat(b3, new int[]{10,1}));

        Variable minus = NdArrayUtils.elementWiseSub(output3, y);
        Variable loss = NdArrayUtils.elementWiseMultiply(minus, minus);
        if (i/PRINT_PERIOD*PRINT_PERIOD==i) System.out.println(NdArrayUtils.mean(loss));

        Variable gh3 = NdArrayUtils.elementWiseSub(output3, y);
        Variable gb3 = NdArrayUtils.elementWiseMultiply(NdArrayUtils.meanDim1(gh3), LEARNING_RATE);
        Variable gm3 = NdArrayUtils.elementWiseMultiply(NdArrayUtils.matmul(NdArrayUtils.transpose(output2), gh3), LEARNING_RATE);

        Variable gh2 = NdArrayUtils.elementWiseMultiply(NdArrayUtils.matmul(gh3, NdArrayUtils.transpose(m3)), outputm2[1]);
        Variable gb2 = NdArrayUtils.elementWiseMultiply(NdArrayUtils.meanDim1(gh2), LEARNING_RATE);
        Variable gm2 = NdArrayUtils.elementWiseMultiply(NdArrayUtils.matmul(NdArrayUtils.transpose(output1), gh2), LEARNING_RATE);

        Variable gh1 = NdArrayUtils.elementWiseMultiply(NdArrayUtils.matmul(gh2, NdArrayUtils.transpose(m2)), outputm1[1]);
        Variable gb1 = NdArrayUtils.elementWiseMultiply(NdArrayUtils.meanDim1(gh1), LEARNING_RATE);
        Variable gm1 = NdArrayUtils.elementWiseMultiply(NdArrayUtils.matmul(NdArrayUtils.transpose(x), gh1), LEARNING_RATE);

        m1 = NdArrayUtils.elementWiseSub(m1, gm1);
        b1 = NdArrayUtils.elementWiseSub(b1, gb1);
        m2 = NdArrayUtils.elementWiseSub(m2, gm2);
        b2 = NdArrayUtils.elementWiseSub(b2, gb2);
        m3 = NdArrayUtils.elementWiseSub(m3, gm3);
        b3 = NdArrayUtils.elementWiseSub(b3, gb3);
    }
}
```
Output:
```
1472.5006
0.4221182
0.018670654
0.002476206
0.0015158897
0.001203638
0.0010536256
9.954824E-4
9.7468635E-4
9.589021E-4

Process finished with exit code 0
```

3. Save and load
```
public static void main(String[] args) {
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
```
Output
```
false
true
true
false
false
================
[ [0.51445496, 0.64803505, 0.81930506]
  [0.52867293, 0.3433866, 0.8686877]
  [0.31306472, 0.77175695, 0.8999964] ]
[ [0.51445496, 0.64803505, 0.81930506]
  [0.52867293, 0.3433866, 0.8686877]
  [0.31306472, 0.77175695, 0.8999964] ]
true
true
true
================

Process finished with exit code 0
```

4. Random and sampling
```
public static void main(String[] args) {
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
```
Output
```
[13, 14, 8, 4, 15, 11, 5, 17, 12, 10]
[13, 1, 4, 9, 17, 5, 2, 19, 0, 8]
[18, 19, 3, 6, 7, 11, 14, 13, 2, 1]
------------
[8, 8, 3, 4, 15, 7, 16, 5, 16, 8]
[3, 3, 19, 18, 13, 10, 13, 11, 1, 14]
[5, 13, 14, 6, 6, 4, 6, 9, 4, 13]
------------
[6, 9, 0, 5, 3, 8, 7, 1, 4, 2]
[0, 5, 6, 3, 7, 4, 9, 8, 2, 1]
[8, 5, 7, 2, 3, 1, 6, 4, 9, 0]
904.0
902.0
1797.0
887.0
1000.0

Process finished with exit code 0
```
5. DBScan
```
public static void main(String[] args) {
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
```
Output
```
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 0]
```
6. KMeans
```
public static void main(String[] args) {
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
```
Output
```
Random Initialization
[[9.0, 0.0], [0.0, 0.0], [1.0, 1.0]]
[[1.0, 1.0], [0.0, 0.0], [0.0, 1.0]]
[[9.0, 9.0], [1.0, 1.0], [0.0, 1.0]]
[[0.0, 0.0], [1.0, 0.0], [1.0, 1.0]]
[[0.0, 0.0], [9.0, 0.0], [9.0, 9.0]]
[[1.0, 0.0], [0.0, 1.0], [9.0, 0.0]]
[[0.0, 1.0], [9.0, 9.0], [1.0, 0.0]]
[[1.0, 1.0], [0.0, 1.0], [9.0, 9.0]]
[[0.0, 0.0], [1.0, 0.0], [0.0, 1.0]]
[[0.0, 1.0], [9.0, 0.0], [9.0, 9.0]]
--------------------
Plus Initialization
[[9.0, 9.0], [9.0, 0.0], [0.0, 0.0]]
[[9.0, 0.0], [9.0, 9.0], [0.0, 0.0]]
[[9.0, 0.0], [9.0, 9.0], [0.0, 0.0]]
[[9.0, 9.0], [9.0, 0.0], [1.0, 1.0]]
[[9.0, 9.0], [9.0, 0.0], [1.0, 1.0]]
[[9.0, 9.0], [9.0, 0.0], [0.0, 0.0]]
[[9.0, 9.0], [1.0, 0.0], [9.0, 0.0]]
[[9.0, 9.0], [1.0, 1.0], [9.0, 0.0]]
[[9.0, 9.0], [9.0, 0.0], [1.0, 0.0]]
[[9.0, 9.0], [9.0, 0.0], [1.0, 0.0]]
--------------------
Normal Fit
[1, 0, 0, 2, 0, 4, 4, 4, 4, 4, 1, 4, 3, 2, 2, 3, 3, 2, 3, 2, 2, 3, 3, 2]
[1, 0, 0, 0, 0, 4, 4, 4, 4, 4, 1, 4, 3, 2, 2, 3, 3, 2, 3, 2, 2, 3, 3, 2]
[1, 0, 0, 0, 0, 4, 4, 4, 4, 4, 1, 4, 3, 2, 2, 3, 3, 2, 3, 2, 2, 3, 3, 2]
[1, 0, 0, 0, 0, 4, 4, 4, 4, 4, 1, 4, 3, 2, 2, 3, 3, 2, 3, 2, 2, 3, 3, 2]
[1, 0, 0, 0, 0, 4, 4, 4, 4, 4, 1, 4, 3, 2, 2, 3, 3, 2, 3, 2, 2, 3, 3, 2]
[1, 0, 0, 0, 0, 4, 4, 4, 4, 4, 1, 4, 3, 2, 2, 3, 3, 2, 3, 2, 2, 3, 3, 2]
[1, 0, 0, 0, 0, 4, 4, 4, 4, 4, 1, 4, 3, 2, 2, 3, 3, 2, 3, 2, 2, 3, 3, 2]
[1, 0, 0, 0, 0, 4, 4, 4, 4, 4, 1, 4, 3, 2, 2, 3, 3, 2, 3, 2, 2, 3, 3, 2]
[1, 0, 0, 0, 0, 4, 4, 4, 4, 4, 1, 4, 3, 2, 2, 3, 3, 2, 3, 2, 2, 3, 3, 2]
[1, 0, 0, 0, 0, 4, 4, 4, 4, 4, 1, 4, 3, 2, 2, 3, 3, 2, 3, 2, 2, 3, 3, 2]
[1, 0, 0, 0, 0, 4, 4, 4, 4, 4, 1, 4, 3, 2, 2, 3, 3, 2, 3, 2, 2, 3, 3, 2]
Smart Init Fit
[0, 1, 1, 1, 1, 3, 1, 0, 1, 1, 0, 1, 2, 1, 1, 2, 2, 1, 2, 1, 1, 2, 2, 1]
[0, 1, 1, 1, 1, 3, 1, 0, 0, 1, 0, 1, 2, 1, 1, 2, 2, 1, 2, 1, 1, 2, 2, 1]
[0, 1, 1, 1, 1, 3, 1, 0, 0, 1, 0, 2, 2, 1, 1, 2, 2, 1, 2, 1, 1, 2, 2, 1]
[0, 1, 1, 1, 1, 3, 1, 0, 0, 0, 0, 2, 2, 1, 1, 2, 2, 1, 2, 1, 1, 2, 2, 1]
[0, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 2, 2, 1, 1, 2, 2, 1, 2, 1, 1, 2, 2, 1]
[0, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 2, 1, 1, 2, 2, 1, 2, 1, 1, 2, 2, 1]
[0, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 2, 1, 1, 2, 2, 1, 2, 1, 1, 2, 2, 1]
[0, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 2, 1, 1, 2, 2, 1, 2, 1, 1, 2, 2, 1]
[0, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 2, 1, 1, 2, 2, 1, 2, 1, 1, 2, 2, 1]
[0, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 2, 1, 1, 2, 2, 1, 2, 1, 1, 2, 2, 1]
[0, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 2, 1, 1, 2, 2, 1, 2, 1, 1, 2, 2, 1]
```

## API: tensorMing_Fundation

### Variable
|Function Name |Inputs |Type |Return |Description |
|---           |---    |---  |---    |---         |
|Variable|String str, int[] dimens, String initialization|Constructor|None|Initialize the variable with the variable name, dimension, and initializer. The initializer can be "zeros", "random" or "xavier" so far.|
|Variable|String str, float[] values|Constructor|None|Initialize the variable with a name and values.|
|Variable|String str, float[][] values|Constructor|None|Initialize the variable with a name and values.|
|Variable|String str, float[][][] values|Constructor|None|Initialize the variable with a name and values.|
|Variable|String str, float[][][][] values|Constructor|None|Initialize the variable with a name and values.|
|Variable|float[] values|Constructor|None|Initialize the variable with values. The name is set as "temp".|
|Variable|float[][] values|Constructor|None|Initialize the variable with values. The name is set as "temp".|
|Variable|float[][][] values|Constructor|None|Initialize the variable with values. The name is set as "temp".|
|Variable|float[][][][] values|Constructor|None|Initialize the variable with values. The name is set as "temp".|
|Variable|String str, String filePath|Constructor|None|Initialize the variable by loading from a file.|
|save|String filePath|Method|Boolean|Save the variable to a the filepath. Usually a txt file. Return true if the file is successfully saved.|
|load|String filePath|Method|Boolean|Load the variable from a the filepath. Usually a txt file. Return true if the file is successfully loaded.|
|equals|Variable v|Method|Boolean|Return true if the value and the shape of the given variable v is equal to the current one.|
|getName|None|Method|String|Return the name of the current variable.|
|setName|String name|Method|None|Set the name of the current variable as the input.|
|getShape|None|Method|int[]|Return an array which is the shape of the current variable. For example, the return of a 3 by 4 matrix is {3,4}|
|getDimension|None|Method|int|Return the dimension of the current variable. For example, the return of a 3 by 4 matrix is 2.|
|setValue|float[] value|Method|None|Set the value and change the dimension & shape.|
|setValue|float[][] value|Method|None|Set the value and change the dimension & shape.|
|setValue|float[][][] value|Method|None|Set the value and change the dimension & shape.|
|setValue|float[][][][] value|Method|None|Set the value and change the dimension & shape.|
|importValues|float[] value|Method|None|Set the value with the input. If the shape and dimension of the input and the current variable does not match, throw a NumberFormatException.|
|importValues|float[][] value|Method|None|Set the value with the input. If the shape and dimension of the input and the current variable does not match, throw a NumberFormatException.|
|importValues|float[][][] value|Method|None|Set the value with the input. If the shape and dimension of the input and the current variable does not match, throw a NumberFormatException.|
|importValues|float[][][][] value|Method|None|Set the value with the input. If the shape and dimension of the input and the current variable does not match, throw a NumberFormatException.|
|get1d|None|Method|float[]|Return a float array if the dimension is 1, or it will return null.|
|get2d|None|Method|float[][]|Return a float array if the dimension is 2, or it will return null.|
|get3d|None|Method|float[][][]|Return a float array if the dimension is 3, or it will return null.|
|get4d|None|Method|float[][][][]|Return a float array if the dimension is 4, or it will return null.|
|toString|None|Method|String|Override the toString method.|

### Initializer
|Function Name |Inputs |Type |Return |Description |
|---           |---    |---  |---    |---         |
|arange|int shape|Static Method|float[]|Return a float[] where the first element is 0, and the last element is shape-1. The size of the array equals to the input integer.|
|zeros1d|int shape|Static Method|float[]|Return an all zero array. The size of the array equals to the input integer.|
|zeros1d|int[] shape|Static Method|float[]|Return an all zero array. The shape of the array equals to the input array.|
|zeros2d|int[][] shape|Static Method|float[][]|Return an all zero array. The shape of the array equals to the input array.|
|zeros3d|int[][][] shape|Static Method|float[][][]|Return an all zero array. The shape of the array equals to the input array.|
|zeros4d|int[][][][] shape|Static Method|float[][][][]|Return an all zero array. The shape of the array equals to the input array.|
|random1d|int shape|Static Method|float[]|Return an array, where the values are picked randomly from the range between 0 and 1. The size of the array equals to the input integer.|
|random1d|int[] shape|Static Method|float[]|Return an array, where the values are picked randomly from the range between 0 and 1. The shape of the array equals to the input array.|
|random2d|int[] shape|Static Method|float[][]|Return an array, where the values are picked randomly from the range between 0 and 1. The shape of the array equals to the input array.|
|random3d|int[] shape|Static Method|float[][][]|Return an array, where the values are picked randomly from the range between 0 and 1. The shape of the array equals to the input array.|
|random4d|int[] shape|Static Method|float[][][][]|Return an array, where the values are picked randomly from the range between 0 and 1. The shape of the array equals to the input array.|
|xavier1d|int shape|Static Method|float[]|Return an array, where the values are initialized using xavier initialization method. The size of the array equals to the input integer.|
|xavier1d|int[] shape|Static Method|float[]|Return an array, where the values are initialized using xavier initialization method. The shape of the array equals to the input array.|
|xavier2d|int[] shape|Static Method|float[][]|Return an array, where the values are initialized using xavier initialization method. The shape of the array equals to the input array.|
|xavier3d|int[] shape|Static Method|float[][][]|Return an array, where the values are initialized using xavier initialization method. The shape of the array equals to the input array.|
|xavier4d|int[] shape|Static Method|float[][][][]|Return an array, where the values are initialized using xavier initialization method. The shape of the array equals to the input array.|

### NdArrayUtils
|Function Name |Inputs |Type |Return |Description |
|---           |---    |---  |---    |---         |
|equals|Variable v1, Variable v2|Static Method|Boolean|Return true if the values of the two variables are the same.|
|flatten|float[] inputValues|Static Method|float[]|Reshape the input array into an 1d-array.|
|flatten|float[][] inputValues|Static Method|float[]|Reshape the input array into an 1d-array.|
|flatten|float[][][] inputValues|Static Method|float[]|Reshape the input array into an 1d-array.|
|flatten|float[][][][] inputValues|Static Method|float[]|Reshape the input array into an 1d-array.|
|flatten|Variable v|Static Method|Variable|Reshape the input variable. The shape of the output equals to int[]{N}, where N equals to the number of elements in the input variable. The name of the output variable is "temp".|
|flatten|Variable v, String resultName|Static Method|Variable|Reshape the input variable. The shape of the output equals to int[]{N}, where N equals to the number of elements in the input variable. The name of the output variable is the second input.|
|sum|Variable v|Static Method|float|Return the sum of all the elements in the input variable.|
|multiplication|Variable v|Static Method|float|Return the result of multiplying all the elements in the input variable.|
|mean|float[] inputValues|Static Method|float|Return the mean of all elements in the input array.|
|mean|float[][] inputValues|Static Method|float|Return the mean of all elements in the input array.|
|mean|float[][][] inputValues|Static Method|float|Return the mean of all elements in the input array.|
|mean|float[][][][] inputValues|Static Method|float|Return the mean of all elements in the input array.|
|mean|Variable v|Static Method|float|Return the mean of all elements in the input variable.|
|meanDim1|Variable v|Static Method|Variable|Return the mean of all elements in the input variable along the first axis. For example, let 'a' be a variable with values of [[1,2],[3,4]], then the output would be a variable with values of [1.5, 3.5]|
|mean|Variable v, int axis|Static Method|Variable|Return the mean of all elements in the input variable along any axis. For example, let 'a' be a variable with values of [[1,2],[3,4]] and the axis is 1, then the output would be a variable with values of [2, 3]|
|std|float[] inputValues|Static Method|float|Return the standard deviation of all elements in the input array.|
|std|float[][] inputValues|Static Method|float|Return the standard deviation of all elements in the input array.|
|std|float[][][] inputValues|Static Method|float|Return the standard deviation of all elements in the input array.|
|std|float[][][][] inputValues|Static Method|float|Return the standard deviation of all elements in the input array.|
|std|Variable v|Static Method|float|Return the standard deviation of all elements in the input variable.|
|addDim1d|Variable v|Static Method|Variable|Add one dimension to the beginning of the shape. For example, the input variable is a 2d-array with a shape of [2,3], then in the output variable, the values are not changed, but the shape becomes [1,2,3]|
|matmul|Variable v1, Variable v2|Static Method|Variable|Do matrix multiplication to the inputs. The inputs should be matrix (2d-arrays). The name of the output is "temp".|
|matmul|Variable v1, Variable v2, String resultName|Static Method|Variable|Do matrix multiplication to the inputs. The inputs should be matrix (2d-arrays). The name of the output is the second input.|
|transpose|Variable v|Static Method|Variable|Transpose the input matrix. The inputs should be matrix (2d-arrays). The name of the output is "temp".|
|transpose|Variable v, String resultName|Static Method|Variable|Transpose the input matrix. The inputs should be matrix (2d-arrays). The name of the output is the second input.|
|transpose|Variable v, int[] t|Static Method|Variable|Transpose the input array by indicating the sequence of the axis. The inputs do not need to be a matrix (2d-arrays). The name of the output is "temp". For example, if the input variable has a shape of [2,4,5], and the second input is [1,0,2], then the output would be a variable with a shape of [4,2,5].|
|transpose|Variable v, int[] t, String resultName|Static Method|Variable|Transpose the input array by indicating the sequence of the axis. The inputs do not need to be a matrix (2d-arrays). The name of the output is the third input. For example, if the input variable has a shape of [2,4,5], and the second input is [1,0,2], then the output would be a variable with a shape of [4,2,5].|
|reshape|Variable v, int[] s|Static Method|Variable|Reshape the variable into the target shape. The name of the output is "temp".|
|reshape|Variable v, int[] s, String resultName|Static Method|Variable|Reshape the variable into the target shape. The name of the output is the second input.|
|repeat|Variable v, int objTime|Static Method|Variable|Repeat the variable n times. For example, let 'a' be a variable with a shape of [4,5], then the output of repeat(a, 3) is a variable with a shape of [3,4,5]. The name of the output is "temp".|
|repeat|Variable v, int objTime, String resultName|Static Method|Variable|Repeat the variable n times. For example, let 'a' be a variable with a shape of [4,5], then the output of repeat(a, 3) is a variable with a shape of [3,4,5]. The name of the output is the third input.|
|concatenate|ArrayList<Variable> av|Static Method|Variable|Concatenate the list of variables. The name of the output is "temp".|
|concatenate|ArrayList<Variable> av|Static Method|Variable|Concatenate the list of variables. The name of the output is "temp".|
|min|Variable v|Static Method|float|Return the minimum of all elements in the input variable.|
|max|Variable v|Static Method|float|Return the maximum of all elements in the input variable.|
|split|Variable array1d, int length, int stride|Static Method|Variable|Split by frames.|
|split|Variable array1d, int length, int stride, String resultName|Static Method|Variable|Split by frames.|
|doubleSplit|Variable array1d, int frameLength, int frameStride, int windowLength, int windowStride|Static Method|Variable|Split by frames and windows.|
|doubleSplit|Variable array1d, int frameLength, int frameStride, int windowLength, int windowStride, String resultName|Static Method|Variable|Split by frames and windows.|
|elementWiseAdd|Variable v1, Variable v2|Static Method|Variable|elementWiseAdd|
|elementWiseAdd|Variable v1, float num|Static Method|Variable|elementWiseAdd|
|elementWiseAdd|Variable v1, Variable v2, String resultName|Static Method|Variable|elementWiseAdd|
|elementWiseAdd|Variable v1, float num, String resultName|Static Method|Variable|elementWiseAdd|
|elementWiseSub|Variable v1, Variable v2|Static Method|Variable|elementWiseSub|
|elementWiseSub|Variable v1, float num|Static Method|Variable|elementWiseSub|
|elementWiseSub|Variable v1, Variable v2, String resultName|Static Method|Variable|elementWiseSub|
|elementWiseSub|Variable v1, float num, String resultName|Static Method|Variable|elementWiseSub|
|elementWiseMultiply|Variable v1, Variable v2|Static Method|Variable|elementWiseMultiply|
|elementWiseMultiply|Variable v1, float num|Static Method|Variable|elementWiseMultiply|
|elementWiseMultiply|Variable v1, Variable v2, String resultName|Static Method|Variable|elementWiseMultiply|
|elementWiseMultiply|Variable v1, float num, String resultName|Static Method|Variable|elementWiseMultiply|
|elementWiseDivide|Variable v1, Variable v2|Static Method|Variable|elementWiseDivide|
|elementWiseDivide|Variable v1, float num|Static Method|Variable|elementWiseDivide|
|elementWiseDivide|Variable v1, Variable v2, String resultName|Static Method|Variable|elementWiseDivide|
|elementWiseDivide|Variable v1, float num, String resultName|Static Method|Variable|elementWiseDivide|

### NdArrayMath
|Function Name |Inputs |Type |Return |Description |
|---           |---    |---  |---    |---         |
|abs|Variable v|Static Method|Variable|-|
|exp|Variable v|Static Method|Variable|-|
|log|Variable v|Static Method|Variable|-|
|log10|Variable v|Static Method|Variable|-|
|sin|Variable v|Static Method|Variable|-|
|sinh|Variable v|Static Method|Variable|-|
|asin|Variable v|Static Method|Variable|-|
|cos|Variable v|Static Method|Variable|-|
|cosh|Variable v|Static Method|Variable|-|
|acos|Variable v|Static Method|Variable|-|
|tan|Variable v|Static Method|Variable|-|
|tanh|Variable v|Static Method|Variable|-|
|atan|Variable v|Static Method|Variable|-|
|sqrt|Variable v|Static Method|Variable|-|
|pow|Variable v, float num|Static Method|Variable|-|
|abs|Variable v, String resultName|Static Method|Variable|-|
|exp|Variable v, String resultName|Static Method|Variable|-|
|log|Variable v, String resultName|Static Method|Variable|-|
|log10|Variable v, String resultName|Static Method|Variable|-|
|sin|Variable v, String resultName|Static Method|Variable|-|
|sinh|Variable v, String resultName|Static Method|Variable|-|
|asin|Variable v, String resultName|Static Method|Variable|-|
|cos|Variable v, String resultName|Static Method|Variable|-|
|cosh|Variable v, String resultName|Static Method|Variable|-|
|acos|Variable v, String resultName|Static Method|Variable|-|
|tan|Variable v, String resultName|Static Method|Variable|-|
|tanh|Variable v, String resultName|Static Method|Variable|-|
|atan|Variable v, String resultName|Static Method|Variable|-|
|sqrt|Variable v, String resultName|Static Method|Variable|-|
|pow|Variable v, float num, String resultName|Static Method|Variable|-|
|modularL|Variable v|Static Method|float|Modular length.|
|distance|Variable v1, Variable v2|Static Method|float|-|
|cosineSim|Variable v1, Variable v2|Static Method|float|Cosine simlarity.|

### Random
|Function Name |Inputs |Type |Return |Description |
|---           |---    |---  |---    |---         |
|randInt|float[] probs|Static Method|int|Return a random integer with probabilities. The range is between 0 and the size of the input (exclusive).|
|randInt|int min, int max|Static Method|int|Return a random integer. The range is between min and max (exclusive).|
|random|None|Static Method|float|Return a random float between 0 and 1.|
|sampling|int size, int maximum, boolean repeat|Static Method|int[]|Return a series of index randomly sampled between 0 and max (exclusive). The size of the output equals to the first input. If repeat is false, then the elements in the result will not be repeated (once sampled, never sampled again).|
|sampling|int size, int maximum, boolean repeat|Static Method|int[]|Return a series of index sampled with probabilities between 0 and the size of the probabilities (exclusive). The size of the output equals to the first input. If repeat is false, then the elements in the result will not be repeated (once sampled, never sampled again).|

## API: tensorMing_Learn

### Preprocessing
|Function Name |Inputs |Type |Return |Description |
|---           |---    |---  |---    |---         |
|oneHotArray|int index, int size|Static Method|float[]|Return an array where the result[index] is one, and the rest are zeros.|
|oneHot|int index, int size|Static Method|Variable|Return a variable, and the value of which is an array where the result[index] is one, and the rest are zeros.The name of the variable is "temp".|
|oneHot|int index, int size, String resultName|Static Method|Variable|Return a variable, and the value of which is an array where the result[index] is one, and the rest are zeros. The name of the variable is the third input.|
|nHotArray|int[] indices, int size|Static Method|float[]|-|
|nHot|int[] indices, int size|Static Method|Variable|-|
|nHot|int[] indices, int size, String resultName|Static Method|Variable|-|
|normalize|Variable v|Static Method|Variable|-|
|normalize|Variable v, String resultName|Static Method|Variable|-|
|normalize|Variable v, float scale, float bias|Static Method|Variable|-|
|normalize|Variable v, float scale, float bias, String resultName|Static Method|Variable|-|
|scale|Variable v|Static Method|Variable|-|
|scale|Variable v, String resultName|Static Method|Variable|-|
|scale|Variable v, float max|Static Method|Variable|-|
|scale|Variable v, float max, String resultName|Static Method|Variable|-|
|scale|Variable v, float min, float max|Static Method|Variable|-|
|scale|Variable v, float min, float max, String resultName|Static Method|Variable|-|

### ClusterDBScan
|Function Name |Inputs |Type |Return |Description |
|---           |---    |---  |---    |---         |
|ClusterDBScan|float eps, int min_samples|Constructor|None|Initialize the class with eps and minimum number of samples.|
|getLabels|None|Method|int[]|Return the labels.|
|fit|ArrayList<Variable> av|Method|None|Fit (train) the data.|

### ClusterKMeans
|Function Name |Inputs |Type |Return |Description |
|---           |---    |---  |---    |---         |
|ClusterKMeans|None|Constructor|None|-|
|getLabels|None|Method|int[]|Return the labels.|
|getCentroids|None|Method|ArrayList<Variable>|Return the centroids.|
|initCentroids|ArrayList<Variable> av, int nClusters|Method|None|Initialize the centroids.|
|initCentroidsPlus|ArrayList<Variable> av, int nClusters|Method|None|Initialize the centroids using KMeans++ method.|
