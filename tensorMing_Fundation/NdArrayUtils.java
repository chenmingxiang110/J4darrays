package tensorMing_Fundation;

import java.util.ArrayList;
import java.util.Arrays;

public class NdArrayUtils {

    public NdArrayUtils() {}

    public static boolean equals(Variable v1, Variable v2) {
        int[] vShape = v2.getShape();
        int[] mShape = v1.getShape();
        if (!(Arrays.equals(vShape, mShape))) {
            return false;
        }
        if (mShape.length == 1) {
            return Arrays.equals(v1.get1d(), v2.get1d());
        } else if (mShape.length == 2) {
            float[][] mValue = v1.get2d();
            float[][] vValue = v2.get2d();
            for (int i = 0 ; i<mShape[0] ; i++) {
                if (!Arrays.equals(mValue[i], vValue[i])) return false;
            }
            return true;
        } else if (mShape.length == 3) {
            float[][][] mValue = v1.get3d();
            float[][][] vValue = v2.get3d();
            for (int i = 0 ; i<mShape[0] ; i++) {
                for (int j = 0 ; j<mShape[1] ; j++) {
                    if (!Arrays.equals(mValue[i][j], vValue[i][j])) return false;
                }
            }
            return true;
        } else if (mShape.length == 4) {
            float[][][][] mValue = v1.get4d();
            float[][][][] vValue = v2.get4d();
            for (int i = 0 ; i<mShape[0] ; i++) {
                for (int j = 0 ; j<mShape[1] ; j++) {
                    for (int k = 0 ; k<mShape[2] ; k++) {
                        if (!Arrays.equals(mValue[i][j][k], vValue[i][j][k])) return false;
                    }
                }
            }
            return true;
        } else {
            throw new IllegalArgumentException("I have no idea what is going on.");
        }
    }

    public static float[] flatten(float[] inputValues) {
        return inputValues;
    }

    public static float[] flatten(float[][] inputValues) {
        float[] result = new float[inputValues.length*inputValues[0].length];
        for (int i = 0 ; i<inputValues.length ; i++) {
            System.arraycopy(inputValues[i], 0, result, i * inputValues[i].length, inputValues[i].length);
        }
        return result;
    }

    public static float[] flatten(float[][][] inputValues) {
        float[] result = new float[inputValues.length*inputValues[0].length*inputValues[0][0].length];
        for (int i = 0 ; i<inputValues.length ; i++) {
            float[] flattened = flatten(inputValues[i]);
            System.arraycopy(flattened, 0, result, i * flattened.length, flattened.length);
        }
        return result;
    }

    public static float[] flatten(float[][][][] inputValues) {
        float[] result = new float[inputValues.length*inputValues[0].length*inputValues[0][0].length*inputValues[0][0][0].length];
        for (int i = 0 ; i<inputValues.length ; i++) {
            float[] flattened = flatten(inputValues[i]);
            System.arraycopy(flattened, 0, result, i * flattened.length, flattened.length);
        }
        return result;
    }

    public static Variable flatten(Variable v) {
        int dim = v.getDimension();
        float[] value;
        if (dim == 1) {
            return v;
        } else if (dim == 2) {
            value = flatten(v.get2d());
        } else if (dim == 3) {
            value = flatten(v.get3d());
        } else if (dim == 4) {
            value = flatten(v.get4d());
        } else {
            throw new IllegalArgumentException("Invalid shape.");
        }
        Variable variable = new Variable("temp", new int[]{value.length}, "zeros");
        variable.importValues(value);
        return variable;
    }

    public static Variable flatten(Variable v, String resultName) {
        int dim = v.getDimension();
        float[] value;
        if (dim == 1) {
            v.setName(resultName);
            return v;
        } else if (dim == 2) {
            value = flatten(v.get2d());
        } else if (dim == 3) {
            value = flatten(v.get3d());
        } else if (dim == 4) {
            value = flatten(v.get4d());
        } else {
            throw new IllegalArgumentException("Invalid shape.");
        }
        Variable variable = new Variable(resultName, new int[]{value.length}, "zeros");
        variable.importValues(value);
        return variable;
    }

    // 计算加和，输入可以是 1d-4d array。
    public static float sum(Variable v) {
        float summation = (float) 0.0;
        float[] values = flatten(v).get1d();
        for (int i = 0 ; i<values.length ; i++) {
            summation+=values[i];
        }
        return summation;
    }

    // 计算连乘，输入可以是 1d-4d array。
    public static float multiplication(Variable v) {
        float mul = (float) 1.0;
        float[] values = flatten(v).get1d();
        for (int i = 0 ; i<values.length ; i++) {
            mul*=values[i];
        }
        return mul;
    }

    public static float mean(float[] inputValues) {
        float sum = (float) 0.0;
        for (float i : inputValues) sum+=i;
        return sum/inputValues.length;
    }

    public static float mean(float[][] inputValues) {
        float[] fInput = flatten(inputValues);
        float sum = (float) 0.0;
        for (float i : fInput) sum+=i;
        return sum/fInput.length;
    }

    public static float mean(float[][][] inputValues) {
        float[] fInput = flatten(inputValues);
        float sum = (float) 0.0;
        for (float i : fInput) sum+=i;
        return sum/fInput.length;
    }

    public static float mean(float[][][][] inputValues) {
        float[] fInput = flatten(inputValues);
        float sum = (float) 0.0;
        for (float i : fInput) sum+=i;
        return sum/fInput.length;
    }

    public static float mean(Variable v) {
        int[] shape = v.getShape();
        if (shape.length == 1) {
            float[] value = v.get1d();
            float mean = 0;
            for (int i = 0 ; i<shape[0] ; i++) {
                mean += value[i];
            }
            return mean/shape[0];
        } else if (shape.length == 2) {
            float[][] value = v.get2d();
            float mean = 0;
            float total = 0;
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    mean += value[i][j];
                    total += 1;
                }
            }
            return mean/total;
        } else if (shape.length == 3) {
            float[][][] value = v.get3d();
            float mean = 0;
            float total = 0;
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        mean += value[i][j][k];
                        total += 1;
                    }
                }
            }
            return mean/total;
        } else if (shape.length == 4) {
            float[][][][] value = v.get4d();
            float mean = 0;
            float total = 0;
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        for (int l = 0 ; l<shape[3] ; l++) {
                            mean += value[i][j][k][l];
                            total += 1;
                        }
                    }
                }
            }
            return mean/total;
        } else {
            throw new IllegalArgumentException("Illegal shape.");
        }
    }

    // 沿着第一维计算均值。例如输入的 array 其 shape 为 [3,4,5]，输出则为 [4,5]。计算其他维度需先 transpose 再计算。
    public static Variable meanDim1(Variable v) {
        int[] shape = v.getShape();
        if (shape.length<2) throw new IllegalArgumentException("Please use mean() instead.");
        Variable variable = new Variable("temp", Arrays.copyOfRange(shape, 1, shape.length), "zeros");
        if (shape.length == 2) {
            float[][] values = v.get2d();
            float[] result = new float[shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[j]+=(values[i][j]/shape[0]);
                }
            }
            variable.importValues(result);
            return variable;
        } else if (shape.length == 3) {
            float[][][] values = v.get3d();
            float[][] result = new float[shape[1]][shape[2]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        result[j][k]+=(values[i][j][k]/shape[0]);
                    }
                }
            }
            variable.importValues(result);
            return variable;
        } else if (shape.length == 4) {
            float[][][][] values = v.get4d();
            float[][][] result = new float[shape[1]][shape[2]][shape[3]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        for (int l = 0 ; l<shape[3] ; l++) {
                            result[j][k][l]+=(values[i][j][k][l]/shape[0]);
                        }
                    }
                }
            }
            variable.importValues(result);
            return variable;
        } else {
            throw new IllegalArgumentException("Illegal shape.");
        }
    }

    // input = 2,3, output = [2,0,1]
    // input = 4,7, output = [4,0,1,2,3,5,6]
    private static int[] axis2transposition(int axis, int shape) {
        if (axis>=shape || axis<0) throw new IllegalArgumentException("Invalid axis.");
        if (shape<=0) throw new IllegalArgumentException("Invalid shape.");
        int[] transposition = new int[shape];
        transposition[0] = axis;
        int pointer = 1;
        for (int i = 0 ; i<shape ; i++) {
            if (i!=axis) {
                transposition[pointer] = i;
                pointer++;
            }
        }
        return transposition;
    }

    public static Variable mean(Variable v, int axis) {
        int[] transposition = axis2transposition(axis, v.getDimension());
        Variable variable = meanDim1(transpose(v, transposition));
        return variable;
    }

    public static float std(float[] inputValues) {
        if (inputValues.length == 1) return 0;
        float mean = mean(inputValues);
        float sum = (float) 0.0;
        for (float i : inputValues) sum+=(Math.pow((i-mean),2));
        sum/=(inputValues.length-1);
        return (float) Math.sqrt(sum);
    }

    public static float std(float[][] inputValues) {
        float[] fInput = flatten(inputValues);
        if (fInput.length == 1) return 0;
        float mean = mean(fInput);
        float sum = (float) 0.0;
        for (float i : fInput) sum+=(Math.pow((i-mean),2));
        sum/=(fInput.length-1);
        return (float) Math.sqrt(sum);
    }

    public static float std(float[][][] inputValues) {
        float[] fInput = flatten(inputValues);
        if (fInput.length == 1) return 0;
        float mean = mean(fInput);
        float sum = (float) 0.0;
        for (float i : fInput) sum+=(Math.pow((i-mean),2));
        sum/=(fInput.length-1);
        return (float) Math.sqrt(sum);
    }

    public static float std(float[][][][] inputValues) {
        float[] fInput = flatten(inputValues);
        if (fInput.length == 1) return 0;
        float mean = mean(fInput);
        float sum = (float) 0.0;
        for (float i : fInput) sum+=(Math.pow((i-mean),2));
        sum/=(fInput.length-1);
        return (float) Math.sqrt(sum);
    }

    public static float std(Variable v) {
        int dim = v.getDimension();
        float[] values;
        if (dim == 1) {
            values = flatten(v.get1d());
        } else if (dim == 2) {
            values = flatten(v.get2d());
        } else if (dim == 3) {
            values = flatten(v.get3d());
        } else if (dim == 4) {
            values = flatten(v.get4d());
        } else {
            throw new IllegalArgumentException("Invalid variable shape.");
        }
        if (values.length == 1) return 0;
        float mean = mean(values);
        float sum = (float) 0.0;
        for (float i : values) sum+=(Math.pow((i-mean),2));
        sum/=(values.length-1);
        return (float) Math.sqrt(sum);
    }

    public static void addDim1d(Variable v) {
        if (v.getDimension() == 1) {
            float[] values = v.get1d();
            float[][] obj = new float[1][values.length];
            obj[0] = values;
            v.setValue(obj);
        }
        if (v.getDimension() == 2) {
            float[][] values = v.get2d();
            float[][][] obj = new float[1][values.length][values[0].length];
            obj[0] = values;
            v.setValue(obj);
        }
        if (v.getDimension() == 3) {
            float[][][] values = v.get3d();
            float[][][][] obj = new float[1][values.length][values[0].length][values[0][0].length];
            obj[0] = values;
            v.setValue(obj);
        }
    }

    private static float multiplyAndAdd(float[] a, float[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("The shape of a and b are not equal, cannot do multiplyAndAdd.");
        }
        float result = (float) 0.0;
        for (int i = 0 ; i<a.length ; i++) {
            result+=a[i]*b[i];
        }
        return result;
    }

    public static Variable matmul(Variable v1, Variable v2) {
        if (!(v1.getDimension() == 2 && v2.getDimension() == 2 && v1.getShape()[1] == v2.getShape()[0])) {
            throw new IllegalArgumentException("Cannot do matrix multiplication on "+v1.getName()+" and "+v2.getName()+".");
        }
        float[][] result = new float[v1.getShape()[0]][v2.getShape()[1]];
        float[][] value1 = v1.get2d();
        float[][] value2 = v2.get2d();
        for (int i = 0 ; i<v1.getShape()[0] ; i++) {
            for (int j = 0 ; j<v2.getShape()[1] ; j++) {
                float[] a = value1[i];
                float[] b = new float[a.length];
                for (int k = 0 ; k<a.length ; k++) {
                    b[k] = value2[k][j];
                }
                result[i][j] = multiplyAndAdd(a, b);
            }
        }
        Variable variable = new Variable("temp", new int[]{result.length,result[0].length}, "zeros");
        variable.importValues(result);
        return variable;
    }

    public static Variable matmul(Variable v1, Variable v2, String resultName) {
        if (!(v1.getDimension() == 2 && v2.getDimension() == 2 && v1.getShape()[1] == v2.getShape()[0])) {
            throw new IllegalArgumentException("Cannot do matrix multiplication on "+v1.getName()+" and "+v2.getName()+".");
        }
        float[][] result = new float[v1.getShape()[0]][v2.getShape()[1]];
        float[][] value1 = v1.get2d();
        float[][] value2 = v2.get2d();
        for (int i = 0 ; i<v1.getShape()[0] ; i++) {
            for (int j = 0 ; j<v2.getShape()[1] ; j++) {
                float[] a = value1[i];
                float[] b = new float[a.length];
                for (int k = 0 ; k<a.length ; k++) {
                    b[k] = value2[k][j];
                }
                result[i][j] = multiplyAndAdd(a, b);
            }
        }
        Variable variable = new Variable(resultName, new int[]{result.length,result[0].length}, "zeros");
        variable.importValues(result);
        return variable;
    }

    public static Variable transpose(Variable v) {
        if (!(v.getDimension() == 2)) {
            throw new IllegalArgumentException("Cannot do matrix transpose on "+v.getName()+".");
        }
        int[] shape = v.getShape();
//        System.out.println(Arrays.toString(shape));
        float[][] value = v.get2d();
        float[][] result = new float[shape[1]][shape[0]];
        Variable variable = new Variable("temp", new int[]{shape[1], shape[0]}, "zeros");
        for (int i = 0 ; i<shape[0] ; i++) {
            for (int j = 0 ; j<shape[1] ; j++) {
                result[j][i] = value[i][j];
            }
        }
        variable.importValues(result);
        return variable;
    }

    public static Variable transpose(Variable v, String resultName) {
        if (!(v.getDimension() == 2)) {
            throw new IllegalArgumentException("Cannot do matrix transpose on "+v.getName()+".");
        }
        int[] shape = v.getShape();
        float[][] value = v.get2d();
        float[][] result = new float[shape[0]][shape[1]];
        Variable variable = new Variable(resultName, shape, "zeros");
        for (int i = 0 ; i<shape[0] ; i++) {
            for (int j = 0 ; j<shape[1] ; j++) {
                result[i][j] = value[j][i];
            }
        }
        variable.importValues(result);
        return variable;
    }

    public static Variable transpose(Variable v, int[] t) {
        int[] sortedT = new int[t.length];
        if (t.length == 0 || t.length>4) throw new IllegalArgumentException("Invalid transposition.");
        for (int i : t) {
            if (i>=sortedT.length || i<0 || sortedT[i]==1) throw new IllegalArgumentException("Invalid transposition.");
            sortedT[i] = 1;
        }
        int[] shape = v.getShape();
        if (t.length==1) {
            return v;
        } else if (t.length==2) {
            if (Arrays.equals(t, new int[]{0,1})) {
                return v;
            } else {
                float[][] value = v.get2d();
                float[][] newValue = new float[shape[t[0]]][shape[t[1]]];
                for (int i = 0 ; i<shape[0] ; i++) {
                    for (int j = 0 ; j<shape[1] ; j++) {
                        newValue[j][i] = value[i][j];
                    }
                }
                return new Variable(newValue);
            }
        } else if (t.length==3) {
            float[][][] value = v.get3d();
            float[][][] newValue = new float[shape[t[0]]][shape[t[1]]][shape[t[2]]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        if (Arrays.equals(t, new int[]{0,2,1})) {
                            newValue[i][k][j] = value[i][j][k];
                        } else if (Arrays.equals(t, new int[]{1,0,2})) {
                            newValue[j][i][k] = value[i][j][k];
                        } else if (Arrays.equals(t, new int[]{1,2,0})) {
                            newValue[j][k][i] = value[i][j][k];
                        } else if (Arrays.equals(t, new int[]{2,0,1})) {
                            newValue[k][i][j] = value[i][j][k];
                        } else if (Arrays.equals(t, new int[]{2,1,0})) {
                            newValue[k][j][i] = value[i][j][k];
                        } else {
                            return v;
                        }
                    }
                }
            }
            return new Variable(newValue);
        } else {
            float[][][][] value = v.get4d();
            float[][][][] newValue = new float[shape[t[0]]][shape[t[1]]][shape[t[2]]][shape[t[3]]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        for (int l = 0 ; l<shape[3] ; l++) {
                            if (Arrays.equals(t, new int[]{0,1,3,2})) {
                                newValue[i][j][l][k] = value[i][j][k][l];
                            } else if (Arrays.equals(t, new int[]{0,2,1,3})) {
                                newValue[i][k][j][l] = value[i][j][k][l];
                            } else if (Arrays.equals(t, new int[]{0,2,3,1})) {
                                newValue[i][k][l][j] = value[i][j][k][l];
                            } else if (Arrays.equals(t, new int[]{0,3,1,2})) {
                                newValue[i][l][j][k] = value[i][j][k][l];
                            } else if (Arrays.equals(t, new int[]{0,3,2,1})) {
                                newValue[i][l][k][j] = value[i][j][k][l];
                            } else if (Arrays.equals(t, new int[]{1,0,2,3})) {
                                newValue[j][i][k][l] = value[i][j][k][l];
                            } else if (Arrays.equals(t, new int[]{1,0,3,2})) {
                                newValue[j][i][l][k] = value[i][j][k][l];
                            } else if (Arrays.equals(t, new int[]{1,2,0,3})) {
                                newValue[j][k][i][l] = value[i][j][k][l];
                            } else if (Arrays.equals(t, new int[]{1,2,3,0})) {
                                newValue[j][k][l][i] = value[i][j][k][l];
                            } else if (Arrays.equals(t, new int[]{1,3,0,2})) {
                                newValue[j][l][i][k] = value[i][j][k][l];
                            } else if (Arrays.equals(t, new int[]{1,3,2,0})) {
                                newValue[j][l][k][i] = value[i][j][k][l];
                            } else if (Arrays.equals(t, new int[]{2,0,1,3})) {
                                newValue[k][i][j][l] = value[i][j][k][l];
                            } else if (Arrays.equals(t, new int[]{2,0,3,1})) {
                                newValue[k][i][l][j] = value[i][j][k][l];
                            } else if (Arrays.equals(t, new int[]{2,1,0,3})) {
                                newValue[k][j][i][l] = value[i][j][k][l];
                            } else if (Arrays.equals(t, new int[]{2,1,3,0})) {
                                newValue[k][j][l][i] = value[i][j][k][l];
                            } else if (Arrays.equals(t, new int[]{2,3,0,1})) {
                                newValue[k][l][i][j] = value[i][j][k][l];
                            } else if (Arrays.equals(t, new int[]{2,3,1,0})) {
                                newValue[k][l][j][i] = value[i][j][k][l];
                            } else if (Arrays.equals(t, new int[]{3,0,1,2})) {
                                newValue[l][i][j][k] = value[i][j][k][l];
                            } else if (Arrays.equals(t, new int[]{3,0,2,1})) {
                                newValue[l][i][k][j] = value[i][j][k][l];
                            } else if (Arrays.equals(t, new int[]{3,1,0,2})) {
                                newValue[l][j][i][k] = value[i][j][k][l];
                            } else if (Arrays.equals(t, new int[]{3,1,2,0})) {
                                newValue[l][j][k][i] = value[i][j][k][l];
                            } else if (Arrays.equals(t, new int[]{3,2,0,1})) {
                                newValue[l][k][i][j] = value[i][j][k][l];
                            } else if (Arrays.equals(t, new int[]{3,2,1,0})) {
                                newValue[l][k][j][i] = value[i][j][k][l];
                            } else {
                                return v;
                            }
                        }
                    }
                }
            }
            return new Variable(newValue);
        }
    }

    public static Variable transpose(Variable v, int[] t, String resultName) {
        int[] sortedT = new int[t.length];
        if (t.length == 0 || t.length>4) throw new IllegalArgumentException("Invalid transposition.");
        for (int i : t) {
            if (i>=sortedT.length || i<0 || sortedT[i]==1) throw new IllegalArgumentException("Invalid transposition.");
            sortedT[i] = 1;
        }
        int[] shape = v.getShape();
        if (t.length==1) {
            v.setName(resultName);
            return v;
        } else if (t.length==2) {
            if (t.equals(new int[]{0,1})) {
                v.setName(resultName);
                return v;
            } else {
                float[][] value = v.get2d();
                float[][] newValue = new float[shape[t[0]]][shape[t[1]]];
                for (int i = 0 ; i<shape[0] ; i++) {
                    for (int j = 0 ; j<shape[1] ; j++) {
                        newValue[j][i] = value[i][j];
                    }
                }
                return new Variable(resultName, newValue);
            }
        } else if (t.length==3) {
            float[][][] value = v.get3d();
            float[][][] newValue = new float[shape[t[0]]][shape[t[1]]][shape[t[2]]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        if (t.equals(new int[]{0,2,1})) {
                            newValue[i][k][j] = value[i][j][k];
                        } else if (t.equals(new int[]{1,0,2})) {
                            newValue[j][i][k] = value[i][j][k];
                        } else if (t.equals(new int[]{1,2,0})) {
                            newValue[j][k][i] = value[i][j][k];
                        } else if (t.equals(new int[]{2,0,1})) {
                            newValue[k][i][j] = value[i][j][k];
                        } else if (t.equals(new int[]{2,1,0})) {
                            newValue[k][j][i] = value[i][j][k];
                        } else {
                            v.setName(resultName);
                            return v;
                        }
                    }
                }
            }
            return new Variable(resultName, newValue);
        } else {
            float[][][][] value = v.get4d();
            float[][][][] newValue = new float[shape[t[0]]][shape[t[1]]][shape[t[2]]][shape[t[3]]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        for (int l = 0 ; l<shape[3] ; l++) {
                            if (t.equals(new int[]{0,1,3,2})) {
                                newValue[i][j][l][k] = value[i][j][k][l];
                            } else if (t.equals(new int[]{0,2,1,3})) {
                                newValue[i][k][j][l] = value[i][j][k][l];
                            } else if (t.equals(new int[]{0,2,3,1})) {
                                newValue[i][k][l][j] = value[i][j][k][l];
                            } else if (t.equals(new int[]{0,3,1,2})) {
                                newValue[i][l][j][k] = value[i][j][k][l];
                            } else if (t.equals(new int[]{0,3,2,1})) {
                                newValue[i][l][k][j] = value[i][j][k][l];
                            } else if (t.equals(new int[]{1,0,2,3})) {
                                newValue[j][i][k][l] = value[i][j][k][l];
                            } else if (t.equals(new int[]{1,0,3,2})) {
                                newValue[j][i][l][k] = value[i][j][k][l];
                            } else if (t.equals(new int[]{1,2,0,3})) {
                                newValue[j][k][i][l] = value[i][j][k][l];
                            } else if (t.equals(new int[]{1,2,3,0})) {
                                newValue[j][k][l][i] = value[i][j][k][l];
                            } else if (t.equals(new int[]{1,3,0,2})) {
                                newValue[j][l][i][k] = value[i][j][k][l];
                            } else if (t.equals(new int[]{1,3,2,0})) {
                                newValue[j][l][k][i] = value[i][j][k][l];
                            } else if (t.equals(new int[]{2,0,1,3})) {
                                newValue[k][i][j][l] = value[i][j][k][l];
                            } else if (t.equals(new int[]{2,0,3,1})) {
                                newValue[k][i][l][j] = value[i][j][k][l];
                            } else if (t.equals(new int[]{2,1,0,3})) {
                                newValue[k][j][i][l] = value[i][j][k][l];
                            } else if (t.equals(new int[]{2,1,3,0})) {
                                newValue[k][j][l][i] = value[i][j][k][l];
                            } else if (t.equals(new int[]{2,3,0,1})) {
                                newValue[k][l][i][j] = value[i][j][k][l];
                            } else if (t.equals(new int[]{2,3,1,0})) {
                                newValue[k][l][j][i] = value[i][j][k][l];
                            } else if (t.equals(new int[]{3,0,1,2})) {
                                newValue[l][i][j][k] = value[i][j][k][l];
                            } else if (t.equals(new int[]{3,0,2,1})) {
                                newValue[l][i][k][j] = value[i][j][k][l];
                            } else if (t.equals(new int[]{3,1,0,2})) {
                                newValue[l][j][i][k] = value[i][j][k][l];
                            } else if (t.equals(new int[]{3,1,2,0})) {
                                newValue[l][j][k][i] = value[i][j][k][l];
                            } else if (t.equals(new int[]{3,2,0,1})) {
                                newValue[l][k][i][j] = value[i][j][k][l];
                            } else if (t.equals(new int[]{3,2,1,0})) {
                                newValue[l][k][j][i] = value[i][j][k][l];
                            } else {
                                v.setName(resultName);
                                return v;
                            }
                        }
                    }
                }
            }
            return new Variable(resultName, newValue);
        }
    }

    public static Variable reshape(Variable v, int[] s) {
        int[] shape = v.getShape();
        if (shape.length==0 || s.length==0) {
            throw new IllegalArgumentException("Cannot reshape the variable with shape "+Arrays.toString(shape)+" to "+Arrays.toString(s)+".");
        }
        int multiplication = 1;
        for (int i = 0 ; i<shape.length ; i++) multiplication*=shape[i];
        int multiplicationS = 1;
        for (int i = 0 ; i<s.length ; i++) multiplicationS*=s[i];
        if (multiplication!=multiplicationS) {
            throw new IllegalArgumentException("Cannot reshape the variable with shape "+Arrays.toString(shape)+" to "+Arrays.toString(s)+".");
        }

        float[] values = flatten(v).get1d();
        int index = 0;
        if (s.length == 1) {
            return new Variable(values);
        } else if (s.length == 2) {
            float[][] result = new float[s[0]][s[1]];
            for (int i = 0 ; i<s[0] ; i++) {
                for (int j = 0 ; j<s[1] ; j++) {
                    result[i][j] = values[index];
                    index++;
                }
            }
            return new Variable(result);
        } else if (s.length == 3) {
            float[][][] result = new float[s[0]][s[1]][s[2]];
            for (int i = 0 ; i<s[0] ; i++) {
                for (int j = 0 ; j<s[1] ; j++) {
                    for (int k = 0 ; k<s[2] ; k++) {
                        result[i][j][k] = values[index];
                        index++;
                    }
                }
            }
            return new Variable(result);
        } else if (s.length == 4) {
            float[][][][] result = new float[s[0]][s[1]][s[2]][s[3]];
            for (int i = 0 ; i<s[0] ; i++) {
                for (int j = 0 ; j<s[1] ; j++) {
                    for (int k = 0 ; k<s[2] ; k++) {
                        for (int l = 0 ; l<s[3] ; l++) {
                            result[i][j][k][l] = values[index];
                            index++;
                        }
                    }
                }
            }
            return new Variable(result);
        } else {
            throw new IllegalArgumentException("Cannot deal with 5darrays or above.");
        }
    }

    public static Variable reshape(Variable v, int[] s, String resultName) {
        int[] shape = v.getShape();
        if (shape.length==0 || s.length==0) {
            throw new IllegalArgumentException("Cannot reshape the variable with shape "+Arrays.toString(shape)+" to "+Arrays.toString(s)+".");
        }
        int multiplication = 1;
        for (int i = 0 ; i<shape.length ; i++) multiplication*=shape[i];
        int multiplicationS = 1;
        for (int i = 0 ; i<s.length ; i++) multiplicationS*=s[i];
        if (multiplication!=multiplicationS) {
            throw new IllegalArgumentException("Cannot reshape the variable with shape "+Arrays.toString(shape)+" to "+Arrays.toString(s)+".");
        }

        float[] values = flatten(v).get1d();
        int index = 0;
        if (s.length == 1) {
            return new Variable(resultName, values);
        } else if (s.length == 2) {
            float[][] result = new float[s[0]][s[1]];
            for (int i = 0 ; i<s[0] ; i++) {
                for (int j = 0 ; j<s[1] ; j++) {
                    result[i][j] = values[index];
                    index++;
                }
            }
            return new Variable(resultName, result);
        } else if (s.length == 3) {
            float[][][] result = new float[s[0]][s[1]][s[2]];
            for (int i = 0 ; i<s[0] ; i++) {
                for (int j = 0 ; j<s[1] ; j++) {
                    for (int k = 0 ; k<s[2] ; k++) {
                        result[i][j][k] = values[index];
                        index++;
                    }
                }
            }
            return new Variable(resultName, result);
        } else if (s.length == 4) {
            float[][][][] result = new float[s[0]][s[1]][s[2]][s[3]];
            for (int i = 0 ; i<s[0] ; i++) {
                for (int j = 0 ; j<s[1] ; j++) {
                    for (int k = 0 ; k<s[2] ; k++) {
                        for (int l = 0 ; l<s[3] ; l++) {
                            result[i][j][k][l] = values[index];
                            index++;
                        }
                    }
                }
            }
            return new Variable(resultName, result);
        } else {
            throw new IllegalArgumentException("Cannot deal with 5darrays or above.");
        }
    }

    public static Variable repeat(Variable v, int objTime) {
        if (objTime<=0) throw new IllegalArgumentException("Cannot repeat n times if n is smaller than 1.");
        int[] shape = v.getShape();
        int[] objShape = new int[shape.length+1];
        objShape[0] = objTime;
        for (int i = 0 ; i<shape.length ; i++) {
            objShape[i+1] = shape[i];
        }
        if (!(objShape.length <= 4 && shape.length == (objShape.length-1) && Arrays.equals(shape, Arrays.copyOfRange(objShape, 1, objShape.length)))) {
            throw new IllegalArgumentException("Shape does not match when doing repeat on "+v.getName()+
                    ".\nTarget shape is "+ Arrays.toString(objShape)+", current shape is "+Arrays.toString(shape)+".");
        }
        Variable variable = new Variable("temp", objShape, "zeros");
        if (objShape.length == 2) {
            float[] value = v.get1d();
            float[][] result = new float[objShape[0]][objShape[1]];
            for (int i = 0 ; i<objShape[0] ; i++) {
                result[i] = value;
            }
            variable.importValues(result);
        } else if (objShape.length == 3) {
            float[][] value = v.get2d();
            float[][][] result = new float[objShape[0]][objShape[1]][objShape[2]];
            for (int i = 0 ; i<objShape[0] ; i++) {
                result[i] = value;
            }
            variable.importValues(result);
        } else if (objShape.length == 4) {
            float[][][] value = v.get3d();
            float[][][][] result = new float[objShape[0]][objShape[1]][objShape[2]][objShape[3]];
            for (int i = 0 ; i<objShape[0] ; i++) {
                result[i] = value;
            }
            variable.importValues(result);
        } else{
            throw new IllegalArgumentException("Illegal target shape.");
        }
        return variable;
    }

    public static Variable repeat(Variable v, int objTime, String resultName) {
        if (objTime<=0) throw new IllegalArgumentException("Cannot repeat n times if n is smaller than 1.");
        int[] shape = v.getShape();
        int[] objShape = new int[shape.length+1];
        objShape[0] = objTime;
        if (!(objShape.length <= 4 && shape.length == (objShape.length-1) && Arrays.equals(shape, Arrays.copyOfRange(objShape, 1, objShape.length)))) {
            throw new IllegalArgumentException("Shape does not match when doing repeat on "+v.getName()+
                    ".\nTarget shape is "+ Arrays.toString(objShape)+", current shape is "+Arrays.toString(shape)+".");
        }
        Variable variable = new Variable(resultName, objShape, "zeros");
        if (objShape.length == 2) {
            float[] value = v.get1d();
            float[][] result = new float[objShape[0]][objShape[1]];
            for (int i = 0 ; i<objShape[0] ; i++) {
                result[i] = value;
            }
            variable.importValues(result);
        } else if (objShape.length == 3) {
            float[][] value = v.get2d();
            float[][][] result = new float[objShape[0]][objShape[1]][objShape[2]];
            for (int i = 0 ; i<objShape[0] ; i++) {
                result[i] = value;
            }
            variable.importValues(result);
        } else if (objShape.length == 4) {
            float[][][] value = v.get3d();
            float[][][][] result = new float[objShape[0]][objShape[1]][objShape[2]][objShape[3]];
            for (int i = 0 ; i<objShape[0] ; i++) {
                result[i] = value;
            }
            variable.importValues(result);
        } else{
            throw new IllegalArgumentException("Illegal target shape.");
        }
        return variable;
    }

    public static Variable concatenate(ArrayList<Variable> av) {
        Variable v = av.get(0);
        int[] shape = v.getShape();
        if (shape.length>=4) {
            throw new IllegalArgumentException("Sorry I am not able to deal with 5-d array or higher.");
        }
        if (shape.length == 1) {
            Variable variable = new Variable("temp", new int[]{av.size(), shape[0]}, "zeros");
            float[][] result = new float[av.size()][shape[0]];
            for (int i = 0 ; i<av.size() ; i++) {
                result[i] = av.get(i).get1d();
            }
            variable.importValues(result);
            return variable;
        } else if (shape.length == 2) {
            Variable variable = new Variable("temp", new int[]{av.size(), shape[0], shape[1]}, "zeros");
            float[][][] result = new float[av.size()][shape[0]][shape[1]];
            for (int i = 0 ; i<av.size() ; i++) {
                result[i] = av.get(i).get2d();
            }
            variable.importValues(result);
            return variable;
        } else if (shape.length == 3) {
            Variable variable = new Variable("temp", new int[]{av.size(), shape[0], shape[1], shape[2]}, "zeros");
            float[][][][] result = new float[av.size()][shape[0]][shape[1]][shape[2]];
            for (int i = 0 ; i<av.size() ; i++) {
                result[i] = av.get(i).get3d();
            }
            variable.importValues(result);
            return variable;
        } else {
            throw new IllegalArgumentException("Illegal shape.");
        }
    }

    public static Variable concatenate(ArrayList<Variable> av, String resultName) {
        Variable v = av.get(0);
        int[] shape = v.getShape();
        if (shape.length>=4) {
            throw new IllegalArgumentException("Sorry I am not able to deal with 5-d array or higher.");
        }
        if (shape.length == 1) {
            Variable variable = new Variable(resultName, new int[]{av.size(), shape[0]}, "zeros");
            float[][] result = new float[av.size()][shape[0]];
            for (int i = 0 ; i<av.size() ; i++) {
                result[i] = av.get(i).get1d();
            }
            variable.importValues(result);
            return variable;
        } else if (shape.length == 2) {
            Variable variable = new Variable(resultName, new int[]{av.size(), shape[0], shape[1]}, "zeros");
            float[][][] result = new float[av.size()][shape[0]][shape[1]];
            for (int i = 0 ; i<av.size() ; i++) {
                result[i] = av.get(i).get2d();
            }
            variable.importValues(result);
            return variable;
        } else if (shape.length == 3) {
            Variable variable = new Variable(resultName, new int[]{av.size(), shape[0], shape[1], shape[2]}, "zeros");
            float[][][][] result = new float[av.size()][shape[0]][shape[1]][shape[2]];
            for (int i = 0 ; i<av.size() ; i++) {
                result[i] = av.get(i).get3d();
            }
            variable.importValues(result);
            return variable;
        } else {
            throw new IllegalArgumentException("Illegal shape.");
        }
    }

    public static float min(float[] v) {
        int index = argmin(v);
        return v[index];
    }

    public static float max(float[] v) {
        int index = argmax(v);
        return v[index];
    }

    public static int argmin(float[] v) {
        if (v.length == 0) {
            throw new IllegalArgumentException("Empty input.");
        }
        if (v.length == 1) return 0;
        int index = 0;
        float value = v[index];
        for (int i = 1 ; i<v.length ; i++) {
            if (v[i]<value) {
                index = i;
                value = v[i];
            }
        }
        return index;
    }

    public static int argmax(float[] v) {
        if (v.length == 0) {
            throw new IllegalArgumentException("Empty input.");
        }
        if (v.length == 1) return 0;
        int index = 0;
        float value = v[index];
        for (int i = 1 ; i<v.length ; i++) {
            if (v[i]>value) {
                index = i;
                value = v[i];
            }
        }
        return index;
    }

    public static float min(Variable v) {
        int[] shape = v.getShape();
        if (shape.length == 1) {
            float[] value = v.get1d();
            float minimum = value[0];
            for (int i = 0 ; i<shape[0] ; i++) {
                if (value[i] < minimum) minimum = value[i];
            }
            return minimum;
        } else if (shape.length == 2) {
            float[][] value = v.get2d();
            float minimum = value[0][0];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    if (value[i][j] < minimum) minimum = value[i][j];
                }
            }
            return minimum;
        } else if (shape.length == 3) {
            float[][][] value = v.get3d();
            float minimum = value[0][0][0];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        if (value[i][j][k] < minimum) minimum = value[i][j][k];
                    }
                }
            }
            return minimum;
        } else if (shape.length == 4) {
            float[][][][] value = v.get4d();
            float minimum = value[0][0][0][0];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        for (int l = 0 ; l<shape[3] ; l++) {
                            if (value[i][j][k][l] < minimum) minimum = value[i][j][k][l];
                        }
                    }
                }
            }
            return minimum;
        } else {
            throw new IllegalArgumentException("Illegal shape.");
        }
    }

    public static float max(Variable v) {
        int[] shape = v.getShape();
        if (shape.length == 1) {
            float[] value = v.get1d();
            float maximum = value[0];
            for (int i = 0 ; i<shape[0] ; i++) {
                if (value[i] > maximum) maximum = value[i];
            }
            return maximum;
        } else if (shape.length == 2) {
            float[][] value = v.get2d();
            float maximum = value[0][0];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    if (value[i][j] > maximum) maximum = value[i][j];
                }
            }
            return maximum;
        } else if (shape.length == 3) {
            float[][][] value = v.get3d();
            float maximum = value[0][0][0];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        if (value[i][j][k] > maximum) maximum = value[i][j][k];
                    }
                }
            }
            return maximum;
        } else if (shape.length == 4) {
            float[][][][] value = v.get4d();
            float maximum = value[0][0][0][0];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        for (int l = 0 ; l<shape[3] ; l++) {
                            if (value[i][j][k][l] > maximum) maximum = value[i][j][k][l];
                        }
                    }
                }
            }
            return maximum;
        } else {
            throw new IllegalArgumentException("Illegal shape.");
        }
    }

    public static Variable split(Variable array1d, int length, int stride) {
        if (array1d.getShape()[0]<length) throw new IllegalArgumentException("The frame length is larger than array length.");
        int dim = (array1d.getShape()[0]-length)/stride+1;
        float[] values = array1d.get1d();
        float[][] result = new float[dim][length];
        for (int i = 0 ; i<dim ; i++) {
            result[i] = Arrays.copyOfRange(values, stride*i, stride*i+length);
        }
        Variable variable = new Variable("temp", new int[]{dim, length}, "zeros");
        variable.importValues(result);
        return variable;
    }

    public static Variable split(Variable array1d, int length, int stride, String resultName) {
        if (array1d.getShape()[0]<length) throw new IllegalArgumentException("The frame length is larger than array length.");
        int dim = (array1d.getShape()[0]-length)/stride+1;
        float[] values = array1d.get1d();
        float[][] result = new float[dim][length];
        for (int i = 0 ; i<dim ; i++) {
            result[i] = Arrays.copyOfRange(values, stride*i, stride*i+length);
        }
        Variable variable = new Variable(resultName, new int[]{dim, length}, "zeros");
        variable.importValues(result);
        return variable;
    }

    public static Variable doubleSplit(Variable array1d, int frameLength, int frameStride, int windowLength, int windowStride) {
        if (array1d.getShape()[0]<frameLength) throw new IllegalArgumentException("The frame length is larger than array length.");
        int nFrames = (array1d.getShape()[0]-frameLength)/frameStride+1;
        if (nFrames<windowLength) throw new IllegalArgumentException("The window length is larger than number of frames.");
        int nWindows = (nFrames-windowLength)/windowStride+1;
        float[] values = array1d.get1d();
        float[][] result = new float[nFrames][frameLength];
        for (int i = 0 ; i<nFrames ; i++) {
            result[i] = Arrays.copyOfRange(values, frameStride*i, frameStride*i+frameLength);
        }
        float[][][] result3d = new float[nWindows][windowLength][frameLength];
        for (int i = 0 ; i<nWindows ; i++) {
            result3d[i] = Arrays.copyOfRange(result, windowStride*i, windowStride*i+windowLength);
        }
        Variable variable = new Variable("temp", new int[]{nWindows, windowLength, frameLength}, "zeros");
        variable.importValues(result3d);
        return variable;
    }

    public static Variable doubleSplit(Variable array1d, int frameLength, int frameStride, int windowLength, int windowStride, String resultName) {
        if (array1d.getShape()[0]<frameLength) throw new IllegalArgumentException("The frame length is larger than array length.");
        int nFrames = (array1d.getShape()[0]-frameLength)/frameStride+1;
        if (nFrames<windowLength) throw new IllegalArgumentException("The window length is larger than number of frames.");
        int nWindows = (nFrames-windowLength)/windowStride+1;
        float[] values = array1d.get1d();
        float[][] result = new float[nFrames][frameLength];
        for (int i = 0 ; i<nFrames ; i++) {
            result[i] = Arrays.copyOfRange(values, frameStride*i, frameStride*i+frameLength);
        }
        float[][][] result3d = new float[nWindows][windowLength][frameLength];
        for (int i = 0 ; i<nWindows ; i++) {
            result3d[i] = Arrays.copyOfRange(result, windowStride*i, windowStride*i+windowLength);
        }
        Variable variable = new Variable(resultName, new int[]{nWindows, windowLength, frameLength}, "zeros");
        variable.importValues(result3d);
        return variable;
    }

    public static Variable elementWiseAdd(Variable v1, Variable v2) {
        if (!Arrays.equals(v1.getShape(), v2.getShape())) {
//            System.out.println(Arrays.toString(v1.getShape()));
//            System.out.println(Arrays.toString(v2.getShape()));
            throw new IllegalArgumentException("The shape of "+v1.getName()+" and "+v2.getName()+" are not equal, cannot do element wise adding.");
        }
        Variable variable = new Variable("temp", v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] value2 = v2.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = value1[i]+value2[i];
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] value2 = v2.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = value1[i][j]+value2[i][j];
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 3) {
            int[] shape = v1.getShape();
            float[][][] value1 = v1.get3d();
            float[][][] value2 = v2.get3d();
            float[][][] result = new float[shape[0]][shape[1]][shape[2]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        result[i][j][k] = value1[i][j][k]+value2[i][j][k];
                    }
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 4) {
            int[] shape = v1.getShape();
            float[][][][] value1 = v1.get4d();
            float[][][][] value2 = v2.get4d();
            float[][][][] result = new float[shape[0]][shape[1]][shape[2]][shape[3]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        for (int l = 0 ; l<shape[3] ; l++) {
                            result[i][j][k][l] = value1[i][j][k][l]+value2[i][j][k][l];
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable elementWiseAdd(Variable v1, float num) {
        Variable variable = new Variable("temp", v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = value1[i]+num;
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = value1[i][j]+num;
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 3) {
            int[] shape = v1.getShape();
            float[][][] value1 = v1.get3d();
            float[][][] result = new float[shape[0]][shape[1]][shape[2]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        result[i][j][k] = value1[i][j][k]+num;
                    }
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 4) {
            int[] shape = v1.getShape();
            float[][][][] value1 = v1.get4d();
            float[][][][] result = new float[shape[0]][shape[1]][shape[2]][shape[3]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        for (int l = 0 ; l<shape[3] ; l++) {
                            result[i][j][k][l] = value1[i][j][k][l]+num;
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable elementWiseAdd(Variable v1, Variable v2, String resultName) {
        if (v1.getShape() == v2.getShape()) {
            throw new IllegalArgumentException("The shape of "+v1.getName()+" and "+v2.getName()+"are not equal, cannot do element wise adding.");
        }
        Variable variable = new Variable(resultName, v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] value2 = v2.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = value1[i]+value2[i];
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] value2 = v2.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = value1[i][j]+value2[i][j];
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 3) {
            int[] shape = v1.getShape();
            float[][][] value1 = v1.get3d();
            float[][][] value2 = v2.get3d();
            float[][][] result = new float[shape[0]][shape[1]][shape[2]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        result[i][j][k] = value1[i][j][k]+value2[i][j][k];
                    }
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 4) {
            int[] shape = v1.getShape();
            float[][][][] value1 = v1.get4d();
            float[][][][] value2 = v2.get4d();
            float[][][][] result = new float[shape[0]][shape[1]][shape[2]][shape[3]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        for (int l = 0 ; l<shape[3] ; l++) {
                            result[i][j][k][l] = value1[i][j][k][l]+value2[i][j][k][l];
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable elementWiseAdd(Variable v1, float num, String resultName) {
        Variable variable = new Variable(resultName, v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = value1[i]+num;
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = value1[i][j]+num;
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 3) {
            int[] shape = v1.getShape();
            float[][][] value1 = v1.get3d();
            float[][][] result = new float[shape[0]][shape[1]][shape[2]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        result[i][j][k] = value1[i][j][k]+num;
                    }
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 4) {
            int[] shape = v1.getShape();
            float[][][][] value1 = v1.get4d();
            float[][][][] result = new float[shape[0]][shape[1]][shape[2]][shape[3]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        for (int l = 0 ; l<shape[3] ; l++) {
                            result[i][j][k][l] = value1[i][j][k][l]+num;
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable elementWiseSub(Variable v1, Variable v2) {
        if (v1.getShape() == v2.getShape()) {
            throw new IllegalArgumentException("The shape of "+v1.getName()+" and "+v2.getName()+"are not equal, cannot do element wise subtraction.");
        }
        Variable variable = new Variable("temp", v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] value2 = v2.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = value1[i]-value2[i];
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] value2 = v2.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = value1[i][j]-value2[i][j];
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 3) {
            int[] shape = v1.getShape();
            float[][][] value1 = v1.get3d();
            float[][][] value2 = v2.get3d();
            float[][][] result = new float[shape[0]][shape[1]][shape[2]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        result[i][j][k] = value1[i][j][k]-value2[i][j][k];
                    }
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 4) {
            int[] shape = v1.getShape();
            float[][][][] value1 = v1.get4d();
            float[][][][] value2 = v2.get4d();
            float[][][][] result = new float[shape[0]][shape[1]][shape[2]][shape[3]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        for (int l = 0 ; l<shape[3] ; l++) {
                            result[i][j][k][l] = value1[i][j][k][l]-value2[i][j][k][l];
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable elementWiseSub(Variable v1, float num) {
        Variable variable = new Variable("temp", v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = value1[i]-num;
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = value1[i][j]-num;
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 3) {
            int[] shape = v1.getShape();
            float[][][] value1 = v1.get3d();
            float[][][] result = new float[shape[0]][shape[1]][shape[2]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        result[i][j][k] = value1[i][j][k]-num;
                    }
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 4) {
            int[] shape = v1.getShape();
            float[][][][] value1 = v1.get4d();
            float[][][][] result = new float[shape[0]][shape[1]][shape[2]][shape[3]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        for (int l = 0 ; l<shape[3] ; l++) {
                            result[i][j][k][l] = value1[i][j][k][l]-num;
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable elementWiseSub(Variable v1, Variable v2, String resultName) {
        if (v1.getShape() == v2.getShape()) {
            throw new IllegalArgumentException("The shape of "+v1.getName()+" and "+v2.getName()+"are not equal, cannot do element wise subtraction.");
        }
        Variable variable = new Variable(resultName, v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] value2 = v2.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = value1[i]-value2[i];
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] value2 = v2.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = value1[i][j]-value2[i][j];
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 3) {
            int[] shape = v1.getShape();
            float[][][] value1 = v1.get3d();
            float[][][] value2 = v2.get3d();
            float[][][] result = new float[shape[0]][shape[1]][shape[2]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        result[i][j][k] = value1[i][j][k]-value2[i][j][k];
                    }
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 4) {
            int[] shape = v1.getShape();
            float[][][][] value1 = v1.get4d();
            float[][][][] value2 = v2.get4d();
            float[][][][] result = new float[shape[0]][shape[1]][shape[2]][shape[3]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        for (int l = 0 ; l<shape[3] ; l++) {
                            result[i][j][k][l] = value1[i][j][k][l]-value2[i][j][k][l];
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable elementWiseSub(Variable v1, float num, String resultName) {
        Variable variable = new Variable(resultName, v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = value1[i]-num;
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = value1[i][j]-num;
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 3) {
            int[] shape = v1.getShape();
            float[][][] value1 = v1.get3d();
            float[][][] result = new float[shape[0]][shape[1]][shape[2]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        result[i][j][k] = value1[i][j][k]-num;
                    }
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 4) {
            int[] shape = v1.getShape();
            float[][][][] value1 = v1.get4d();
            float[][][][] result = new float[shape[0]][shape[1]][shape[2]][shape[3]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        for (int l = 0 ; l<shape[3] ; l++) {
                            result[i][j][k][l] = value1[i][j][k][l]-num;
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable elementWiseMultiply(Variable v1, Variable v2) {
        if (v1.getShape() == v2.getShape()) {
            throw new IllegalArgumentException("The shape of "+v1.getName()+" and "+v2.getName()+"are not equal, cannot do element wise multiplication.");
        }
        Variable variable = new Variable("temp", v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] value2 = v2.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = value1[i]*value2[i];
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] value2 = v2.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = value1[i][j]*value2[i][j];
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 3) {
            int[] shape = v1.getShape();
            float[][][] value1 = v1.get3d();
            float[][][] value2 = v2.get3d();
            float[][][] result = new float[shape[0]][shape[1]][shape[2]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        result[i][j][k] = value1[i][j][k]*value2[i][j][k];
                    }
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 4) {
            int[] shape = v1.getShape();
            float[][][][] value1 = v1.get4d();
            float[][][][] value2 = v2.get4d();
            float[][][][] result = new float[shape[0]][shape[1]][shape[2]][shape[3]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        for (int l = 0 ; l<shape[3] ; l++) {
                            result[i][j][k][l] = value1[i][j][k][l]*value2[i][j][k][l];
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable elementWiseMultiply(Variable v1, float num) {
        Variable variable = new Variable("temp", v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = value1[i]*num;
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = value1[i][j]*num;
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 3) {
            int[] shape = v1.getShape();
            float[][][] value1 = v1.get3d();
            float[][][] result = new float[shape[0]][shape[1]][shape[2]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        result[i][j][k] = value1[i][j][k]*num;
                    }
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 4) {
            int[] shape = v1.getShape();
            float[][][][] value1 = v1.get4d();
            float[][][][] result = new float[shape[0]][shape[1]][shape[2]][shape[3]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        for (int l = 0 ; l<shape[3] ; l++) {
                            result[i][j][k][l] = value1[i][j][k][l]*num;
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable elementWiseMultiply(Variable v1, Variable v2, String resultName) {
        if (v1.getShape() == v2.getShape()) {
            throw new IllegalArgumentException("The shape of "+v1.getName()+" and "+v2.getName()+"are not equal, cannot do element wise multiplication.");
        }
        Variable variable = new Variable(resultName, v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] value2 = v2.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = value1[i]*value2[i];
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] value2 = v2.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = value1[i][j]*value2[i][j];
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 3) {
            int[] shape = v1.getShape();
            float[][][] value1 = v1.get3d();
            float[][][] value2 = v2.get3d();
            float[][][] result = new float[shape[0]][shape[1]][shape[2]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        result[i][j][k] = value1[i][j][k]*value2[i][j][k];
                    }
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 4) {
            int[] shape = v1.getShape();
            float[][][][] value1 = v1.get4d();
            float[][][][] value2 = v2.get4d();
            float[][][][] result = new float[shape[0]][shape[1]][shape[2]][shape[3]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        for (int l = 0 ; l<shape[3] ; l++) {
                            result[i][j][k][l] = value1[i][j][k][l]*value2[i][j][k][l];
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable elementWiseMultiply(Variable v1, float num, String resultName) {
        Variable variable = new Variable(resultName, v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = value1[i]*num;
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = value1[i][j]*num;
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 3) {
            int[] shape = v1.getShape();
            float[][][] value1 = v1.get3d();
            float[][][] result = new float[shape[0]][shape[1]][shape[2]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        result[i][j][k] = value1[i][j][k]*num;
                    }
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 4) {
            int[] shape = v1.getShape();
            float[][][][] value1 = v1.get4d();
            float[][][][] result = new float[shape[0]][shape[1]][shape[2]][shape[3]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        for (int l = 0 ; l<shape[3] ; l++) {
                            result[i][j][k][l] = value1[i][j][k][l]*num;
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable elementWiseDivide(Variable v1, Variable v2) {
        if (v1.getShape() == v2.getShape()) {
            throw new IllegalArgumentException("The shape of "+v1.getName()+" and "+v2.getName()+"are not equal, cannot do element wise dividing.");
        }
        Variable variable = new Variable("temp", v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] value2 = v2.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = value1[i]/value2[i];
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] value2 = v2.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = value1[i][j]/value2[i][j];
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 3) {
            int[] shape = v1.getShape();
            float[][][] value1 = v1.get3d();
            float[][][] value2 = v2.get3d();
            float[][][] result = new float[shape[0]][shape[1]][shape[2]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        result[i][j][k] = value1[i][j][k]/value2[i][j][k];
                    }
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 4) {
            int[] shape = v1.getShape();
            float[][][][] value1 = v1.get4d();
            float[][][][] value2 = v2.get4d();
            float[][][][] result = new float[shape[0]][shape[1]][shape[2]][shape[3]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        for (int l = 0 ; l<shape[3] ; l++) {
                            result[i][j][k][l] = value1[i][j][k][l]/value2[i][j][k][l];
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable elementWiseDivide(Variable v1, float num) {
        Variable variable = new Variable("temp", v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = value1[i]/num;
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = value1[i][j]/num;
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 3) {
            int[] shape = v1.getShape();
            float[][][] value1 = v1.get3d();
            float[][][] result = new float[shape[0]][shape[1]][shape[2]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        result[i][j][k] = value1[i][j][k]/num;
                    }
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 4) {
            int[] shape = v1.getShape();
            float[][][][] value1 = v1.get4d();
            float[][][][] result = new float[shape[0]][shape[1]][shape[2]][shape[3]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        for (int l = 0 ; l<shape[3] ; l++) {
                            result[i][j][k][l] = value1[i][j][k][l]/num;
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable elementWiseDivide(Variable v1, Variable v2, String resultName) {
        if (v1.getShape() == v2.getShape()) {
            throw new IllegalArgumentException("The shape of "+v1.getName()+" and "+v2.getName()+"are not equal, cannot do element wise dividing.");
        }
        Variable variable = new Variable(resultName, v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] value2 = v2.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = value1[i]/value2[i];
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] value2 = v2.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = value1[i][j]/value2[i][j];
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 3) {
            int[] shape = v1.getShape();
            float[][][] value1 = v1.get3d();
            float[][][] value2 = v2.get3d();
            float[][][] result = new float[shape[0]][shape[1]][shape[2]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        result[i][j][k] = value1[i][j][k]/value2[i][j][k];
                    }
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 4) {
            int[] shape = v1.getShape();
            float[][][][] value1 = v1.get4d();
            float[][][][] value2 = v2.get4d();
            float[][][][] result = new float[shape[0]][shape[1]][shape[2]][shape[3]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        for (int l = 0 ; l<shape[3] ; l++) {
                            result[i][j][k][l] = value1[i][j][k][l]/value2[i][j][k][l];
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable elementWiseDivide(Variable v1, float num, String resultName) {
        Variable variable = new Variable(resultName, v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = value1[i]/num;
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = value1[i][j]/num;
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 3) {
            int[] shape = v1.getShape();
            float[][][] value1 = v1.get3d();
            float[][][] result = new float[shape[0]][shape[1]][shape[2]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        result[i][j][k] = value1[i][j][k]/num;
                    }
                }
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 4) {
            int[] shape = v1.getShape();
            float[][][][] value1 = v1.get4d();
            float[][][][] result = new float[shape[0]][shape[1]][shape[2]][shape[3]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        for (int l = 0 ; l<shape[3] ; l++) {
                            result[i][j][k][l] = value1[i][j][k][l]/num;
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }
}
