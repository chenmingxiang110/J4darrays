package ndArray;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NdCommonMathFunc {

    public NdCommonMathFunc() {}

    public static void softmax(Object array) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array");
        }
        int _length = Array.getLength(array);
        if (_length==0) {
            return;
        }
        double maximum = NdMath.max(array);
        NdMath.elementwiseSubtract(array, maximum);
        NdMath.ndExp(array);
        double summation = NdMath.sum(array);
        NdMath.elementwiseDivide(array, summation);
    }

    public static void sigmoid(Object array) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Invalid input. Not an array");
        }
        int _length = Array.getLength(array);
        if (_length==0) {
            return;
        }
        NdMath.elementwiseMultiply(array, -1);
        NdMath.ndExp(array);
        NdMath.elementwiseAdd(array, 1);
        NdMath.elementwiseDivide(1, array);
    }
}
