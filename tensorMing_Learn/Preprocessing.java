package tensorMing_Learn;

import tensorMing_Fundation.*;

public class Preprocessing {

    public Preprocessing() {}

    public static float[] oneHotArray(int index, int size) {
        if (index<0 || size<=0) throw new IllegalArgumentException("Invalid index or size.");
        if (index>=size) throw new IllegalArgumentException("Index must be smaller than size.");
        float[] result = new float[size];
        result[index] = 1;
        return result;
    }

    public static Variable oneHot(int index, int size) {
        if (index<0 || size<=0) throw new IllegalArgumentException("Invalid index or size.");
        if (index>=size) throw new IllegalArgumentException("Index must be smaller than size.");
        float[] result = new float[size];
        result[index] = 1;
        return new Variable(result);
    }

    public static Variable oneHot(int index, int size, String resultName) {
        if (index<0 || size<=0) throw new IllegalArgumentException("Invalid index or size.");
        if (index>=size) throw new IllegalArgumentException("Index must be smaller than size.");
        float[] result = new float[size];
        result[index] = 1;
        return new Variable(resultName, result);
    }

    public static float[] nHotArray(int[] indices, int size) {
        float[] result = new float[size];
        for (int index : indices) {
            if (index<0 || size<=0) throw new IllegalArgumentException("Invalid index or size.");
            if (index>=size) throw new IllegalArgumentException("Index must be smaller than size.");
            result[index] = 1;
        }
        return result;
    }

    public static Variable nHot(int[] indices, int size) {
        float[] result = new float[size];
        for (int index : indices) {
            if (index<0 || size<=0) throw new IllegalArgumentException("Invalid index or size.");
            if (index>=size) throw new IllegalArgumentException("Index must be smaller than size.");
            result[index] = 1;
        }
        return new Variable(result);
    }

    public static Variable nHot(int[] indices, int size, String resultName) {
        float[] result = new float[size];
        for (int index : indices) {
            if (index<0 || size<=0) throw new IllegalArgumentException("Invalid index or size.");
            if (index>=size) throw new IllegalArgumentException("Index must be smaller than size.");
            result[index] = 1;
        }
        return new Variable(resultName, result);
    }

    public static Variable normalize(Variable v) {
        float mean = NdArrayUtils.mean(v);
        float std = NdArrayUtils.std(v);
        Variable variable = NdArrayUtils.elementWiseDivide(NdArrayUtils.elementWiseSub(v, mean), std);
        return variable;
    }

    public static Variable normalize(Variable v, String resultName) {
        float mean = NdArrayUtils.mean(v);
        float std = NdArrayUtils.std(v);
        Variable variable = NdArrayUtils.elementWiseDivide(NdArrayUtils.elementWiseSub(v, mean), std);
        variable.setName(resultName);
        return variable;
    }

    public static Variable normalize(Variable v, float scale, float bias) {
        float mean = NdArrayUtils.mean(v);
        float std = NdArrayUtils.std(v);
        Variable variable = NdArrayUtils.elementWiseAdd(NdArrayUtils.elementWiseDivide(NdArrayUtils.elementWiseSub(v, mean), scale*std), bias);
        return variable;
    }

    public static Variable normalize(Variable v, float scale, float bias, String resultName) {
        float mean = NdArrayUtils.mean(v);
        float std = NdArrayUtils.std(v);
        Variable variable = NdArrayUtils.elementWiseAdd(NdArrayUtils.elementWiseDivide(NdArrayUtils.elementWiseSub(v, mean), scale*std), bias);
        variable.setName(resultName);
        return variable;
    }

    public static Variable scale(Variable v) {
        Variable variable = NdArrayUtils.elementWiseSub(v, NdArrayUtils.min(v));
        variable = NdArrayUtils.elementWiseDivide(variable, NdArrayUtils.max(variable));
        return variable;
    }

    public static Variable scale(Variable v, String resultName) {
        Variable variable = NdArrayUtils.elementWiseSub(v, NdArrayUtils.min(v));
        variable = NdArrayUtils.elementWiseDivide(variable, NdArrayUtils.max(variable));
        variable.setName(resultName);
        return variable;
    }

    public static Variable scale(Variable v, float max) {
        Variable variable = NdArrayUtils.elementWiseSub(v, NdArrayUtils.min(v));
        variable = NdArrayUtils.elementWiseDivide(variable, NdArrayUtils.max(variable));
        variable = NdArrayUtils.elementWiseMultiply(variable, max);
        return variable;
    }

    public static Variable scale(Variable v, float max, String resultName) {
        Variable variable = NdArrayUtils.elementWiseSub(v, NdArrayUtils.min(v));
        variable = NdArrayUtils.elementWiseDivide(variable, NdArrayUtils.max(variable));
        variable = NdArrayUtils.elementWiseMultiply(variable, max);
        variable.setName(resultName);
        return variable;
    }

    public static Variable scale(Variable v, float min, float max) {
        Variable variable = NdArrayUtils.elementWiseSub(v, NdArrayUtils.min(v));
        variable = NdArrayUtils.elementWiseDivide(variable, NdArrayUtils.max(variable));
        variable = NdArrayUtils.elementWiseMultiply(variable, max-min);
        variable = NdArrayUtils.elementWiseAdd(variable, min);
        return variable;
    }

    public static Variable scale(Variable v, float min, float max, String resultName) {
        Variable variable = NdArrayUtils.elementWiseSub(v, NdArrayUtils.min(v));
        variable = NdArrayUtils.elementWiseDivide(variable, NdArrayUtils.max(variable));
        variable = NdArrayUtils.elementWiseMultiply(variable, max-min);
        variable = NdArrayUtils.elementWiseAdd(variable, min);
        variable.setName(resultName);
        return variable;
    }

}
