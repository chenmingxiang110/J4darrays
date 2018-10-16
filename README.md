## Introduction

Although this package is not camparable to numpy or tensorflow, but it is meant to make matrix manipulation and machine learning easier on Java. You can build ndArrays and caculations on them much easier than using the vanilla arrays like float[] or int[].

## Quick Start

The following are some code snippets that you may be interested.

Code:
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
```java
[0.2751375, 0.88426, 0.47232184, 0.055898003, 0.45938385, 0.4172129, 0.45583743, 0.9337849, 0.6923709, 0.28284898]
[0.2751375, 0.88426, 0.47232184, 0.055898003, 0.45938385, 0.4172129, 0.45583743, 0.9337849, 0.6923709, 0.28284898]
[1.3167118, 2.421192, 1.6037134, 1.0574899, 1.5830983, 1.5177256, 1.5774939, 2.5441203, 1.998448, 1.3269048]
[0.27167928, 0.77344614, 0.45495513, 0.055868898, 0.4433959, 0.405214, 0.4402144, 0.80387694, 0.63836396, 0.27909255]
[0.52453554, 0.940351, 0.68725675, 0.23642759, 0.6777786, 0.6459202, 0.6751573, 0.96632546, 0.8320883, 0.5318355]
[0.07570065, 0.7819157, 0.22308792, 0.0031245868, 0.21103352, 0.1740666, 0.20778777, 0.87195426, 0.47937745, 0.080003545]
=============
```

## API

### Variable
|Function Name |Type |Return |
|---           |---  |---    |

### NdArrayUtils


### NdArrayMath

