## Introduction

Although this package is not camparable to numpy or tensorflow, but it is meant to make matrix manipulation and machine learning easier on Java. You can build ndArrays and caculations on them much easier than using the vanilla arrays like float[] or int[].

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

## API: tensorMing_Fundation

### Variable
|Function Name |Inputs |Type |Return |Description |
|---           |---    |---  |---    |---         |
|Variable|String str, int[] dimens, String initialization|Initialization|None|Initialize the variable with the variable name, dimension, and initializer. The initializer can be "zeros", "random" or "xavier" so far.|
|Variable|String str, float[] values|Initialization|None|Initialize the variable with a name and values.|
|Variable|String str, float[][] values|Initialization|None|Initialize the variable with a name and values.|
|Variable|String str, float[][][] values|Initialization|None|Initialize the variable with a name and values.|
|Variable|String str, float[][][][] values|Initialization|None|Initialize the variable with a name and values.|
|Variable|float[] values|Initialization|None|Initialize the variable with values. The name is set as "temp".|
|Variable|float[][] values|Initialization|None|Initialize the variable with values. The name is set as "temp".|
|Variable|float[][][] values|Initialization|None|Initialize the variable with values. The name is set as "temp".|
|Variable|float[][][][] values|Initialization|None|Initialize the variable with values. The name is set as "temp".|
|Variable|String str, String filePath|Initialization|None|Initialize the variable by loading from a file.|
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

### Random
|Function Name |Inputs |Type |Return |Description |
|---           |---    |---  |---    |---         |

## API: tensorMing_Learn

### Preprocessing
|Function Name |Inputs |Type |Return |Description |
|---           |---    |---  |---    |---         |

### ClusterDBScan
|Function Name |Inputs |Type |Return |Description |
|---           |---    |---  |---    |---         |

### ClusterKMeans
|Function Name |Inputs |Type |Return |Description |
|---           |---    |---  |---    |---         |
