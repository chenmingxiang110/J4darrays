package tensorMing_Fundation;

public class NdArrayMath {

    public static Variable abs(Variable v1) {
        Variable variable = new Variable("temp", v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.abs(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.abs(value1[i][j]);
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
                        result[i][j][k] = (float)Math.abs(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.abs(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable exp(Variable v1) {
        Variable variable = new Variable("temp", v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.exp(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.exp(value1[i][j]);
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
                        result[i][j][k] = (float)Math.exp(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.exp(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable log(Variable v1) {
        Variable variable = new Variable("temp", v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.log(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.log(value1[i][j]);
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
                        result[i][j][k] = (float)Math.log(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.log(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable log10(Variable v1) {
        Variable variable = new Variable("temp", v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.log10(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.log10(value1[i][j]);
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
                        result[i][j][k] = (float)Math.log10(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.log10(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable sin(Variable v1) {
        Variable variable = new Variable("temp", v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.sin(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.sin(value1[i][j]);
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
                        result[i][j][k] = (float)Math.sin(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.sin(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable sinh(Variable v1) {
        Variable variable = new Variable("temp", v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.sinh(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.sinh(value1[i][j]);
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
                        result[i][j][k] = (float)Math.sinh(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.sinh(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable asin(Variable v1) {
        Variable variable = new Variable("temp", v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.asin(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.asin(value1[i][j]);
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
                        result[i][j][k] = (float)Math.asin(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.asin(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable cos(Variable v1) {
        Variable variable = new Variable("temp", v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.cos(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.cos(value1[i][j]);
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
                        result[i][j][k] = (float)Math.cos(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.cos(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable cosh(Variable v1) {
        Variable variable = new Variable("temp", v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.cosh(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.cosh(value1[i][j]);
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
                        result[i][j][k] = (float)Math.cosh(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.cosh(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable acos(Variable v1) {
        Variable variable = new Variable("temp", v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.acos(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.acos(value1[i][j]);
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
                        result[i][j][k] = (float)Math.acos(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.acos(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable tan(Variable v1) {
        Variable variable = new Variable("temp", v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.tan(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.tan(value1[i][j]);
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
                        result[i][j][k] = (float)Math.tan(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.tan(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable tanh(Variable v1) {
        Variable variable = new Variable("temp", v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.tanh(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.tanh(value1[i][j]);
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
                        result[i][j][k] = (float)Math.tanh(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.tanh(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable atan(Variable v1) {
        Variable variable = new Variable("temp", v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.atan(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.atan(value1[i][j]);
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
                        result[i][j][k] = (float)Math.atan(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.atan(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable sqrt(Variable v1) {
        Variable variable = new Variable("temp", v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.sqrt(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.sqrt(value1[i][j]);
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
                        result[i][j][k] = (float)Math.sqrt(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.sqrt(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable pow(Variable v1, float num) {
        Variable variable = new Variable("temp", v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.pow(value1[i], num);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.pow(value1[i][j], num);
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
                        result[i][j][k] = (float)Math.pow(value1[i][j][k], num);
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
                            result[i][j][k][l] = (float)Math.pow(value1[i][j][k][l], num);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable abs(Variable v1, String resultName) {
        Variable variable = new Variable(resultName, v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.abs(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.abs(value1[i][j]);
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
                        result[i][j][k] = (float)Math.abs(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.abs(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable exp(Variable v1, String resultName) {
        Variable variable = new Variable(resultName, v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.exp(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.exp(value1[i][j]);
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
                        result[i][j][k] = (float)Math.exp(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.exp(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable log(Variable v1, String resultName) {
        Variable variable = new Variable(resultName, v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.log(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.log(value1[i][j]);
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
                        result[i][j][k] = (float)Math.log(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.log(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable log10(Variable v1, String resultName) {
        Variable variable = new Variable(resultName, v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.log10(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.log10(value1[i][j]);
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
                        result[i][j][k] = (float)Math.log10(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.log10(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable sin(Variable v1, String resultName) {
        Variable variable = new Variable(resultName, v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.sin(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.sin(value1[i][j]);
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
                        result[i][j][k] = (float)Math.sin(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.sin(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable sinh(Variable v1, String resultName) {
        Variable variable = new Variable(resultName, v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.sinh(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.sinh(value1[i][j]);
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
                        result[i][j][k] = (float)Math.sinh(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.sinh(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable asin(Variable v1, String resultName) {
        Variable variable = new Variable(resultName, v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.asin(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.asin(value1[i][j]);
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
                        result[i][j][k] = (float)Math.asin(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.asin(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable cos(Variable v1, String resultName) {
        Variable variable = new Variable(resultName, v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.cos(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.cos(value1[i][j]);
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
                        result[i][j][k] = (float)Math.cos(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.cos(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable cosh(Variable v1, String resultName) {
        Variable variable = new Variable(resultName, v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.cosh(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.cosh(value1[i][j]);
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
                        result[i][j][k] = (float)Math.cosh(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.cosh(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable acos(Variable v1, String resultName) {
        Variable variable = new Variable(resultName, v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.acos(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.acos(value1[i][j]);
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
                        result[i][j][k] = (float)Math.acos(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.acos(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable tan(Variable v1, String resultName) {
        Variable variable = new Variable(resultName, v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.tan(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.tan(value1[i][j]);
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
                        result[i][j][k] = (float)Math.tan(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.tan(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable tanh(Variable v1, String resultName) {
        Variable variable = new Variable(resultName, v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.tanh(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.tanh(value1[i][j]);
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
                        result[i][j][k] = (float)Math.tanh(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.tanh(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable atan(Variable v1, String resultName) {
        Variable variable = new Variable(resultName, v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.atan(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.atan(value1[i][j]);
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
                        result[i][j][k] = (float)Math.atan(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.atan(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable sqrt(Variable v1, String resultName) {
        Variable variable = new Variable(resultName, v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.sqrt(value1[i]);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.sqrt(value1[i][j]);
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
                        result[i][j][k] = (float)Math.sqrt(value1[i][j][k]);
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
                            result[i][j][k][l] = (float)Math.sqrt(value1[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static Variable pow(Variable v1, float num, String resultName) {
        Variable variable = new Variable(resultName, v1.getShape(), "zeros");
        if (v1.getDimension() == 1) {
            float[] value1 = v1.get1d();
            float[] result = new float[v1.getShape()[0]];
            for (int i = 0 ; i<result.length ; i++) {
                result[i] = (float)Math.pow(value1[i], num);
            }
            variable.importValues(result);
        }
        if (v1.getDimension() == 2) {
            int[] shape = v1.getShape();
            float[][] value1 = v1.get2d();
            float[][] result = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    result[i][j] = (float)Math.pow(value1[i][j], num);
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
                        result[i][j][k] = (float)Math.pow(value1[i][j][k], num);
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
                            result[i][j][k][l] = (float)Math.pow(value1[i][j][k][l], num);
                        }
                    }
                }
            }
            variable.importValues(result);
        }
        return variable;
    }

    public static float modularL(Variable v) {
        if (v.getDimension()!=1) {
            throw new IllegalArgumentException("Modular length can only be measured on 1d arrays.");
        }
        int length = v.getShape()[0];
        float result = (float) 0.0;
        float[] value1 = v.get1d();
        for (int i = 0 ; i<length ; i++) {
            result += (float) Math.pow(value1[i], 2.0);
        }
        return (float) Math.sqrt(result);
    }

    public static float distance(Variable v1, Variable v2) {
        if (!(v1.getDimension()==1 && v2.getDimension()==1)) {
            throw new IllegalArgumentException("Distance can only be measured on 1d arrays.");
        }
        if (v1.getShape()[0]!=v2.getShape()[0]) {
            throw new IllegalArgumentException("The shapes of inputs does not match.");
        }
        int length = v1.getShape()[0];
        float result = (float) 0.0;
        float[] value1 = v1.get1d();
        float[] value2 = v2.get1d();
        for (int i = 0 ; i<length ; i++) {
            result += (float) Math.pow(value1[i]-value2[i], 2.0);
        }
        return (float) Math.sqrt(result);
    }

    public static float cosineSim(Variable v1, Variable v2) {
        if (!(v1.getDimension()==1 && v2.getDimension()==1)) {
            throw new IllegalArgumentException("Distance can only be measured on 1d arrays.");
        }
        if (v1.getShape()[0]!=v2.getShape()[0]) {
            throw new IllegalArgumentException("The shapes of inputs does not match.");
        }
        int length = v1.getShape()[0];
        float result = (float) 0.0;
        float[] value1 = v1.get1d();
        float[] value2 = v2.get1d();
        for (int i = 0 ; i<length ; i++) {
            result += value1[i]*value2[i];
        }
        float mod = modularL(v1)*modularL(v2);
        return result/mod;
    }

    public static float sigmoid(float f) {
        return (float)(1.0/(1.0+Math.exp(-f)));
    }

    public static Variable sigmoid(Variable v) {
        return pow(NdArrayUtils.elementWiseAdd(exp(NdArrayUtils.elementWiseMultiply(v, -1)), 1), -1);
    }

    public static Variable sigmoid(Variable v, String resultName) {
        Variable variable = pow(NdArrayUtils.elementWiseAdd(exp(NdArrayUtils.elementWiseMultiply(v, -1)), 1), -1);
        variable.setName(resultName);
        return variable;
    }

    public static float sigmoid(float f, float bias, float var) {
        float g = (f-bias)/var;
        return (float)(1.0/(1.0+Math.exp(-g)));
    }

    public static Variable sigmoid(Variable v, float bias, float var) {
        float[] values = NdArrayUtils.flatten(v).get1d();
        for (int i = 0 ; i<values.length ; i++) {
            values[i] = sigmoid(values[i], bias, var);
        }
        return NdArrayUtils.reshape(new Variable(values), v.getShape());
    }

    public static Variable sigmoid(Variable v, float bias, float var, String resultName) {
        float[] values = NdArrayUtils.flatten(v).get1d();
        for (int i = 0 ; i<values.length ; i++) {
            values[i] = sigmoid(values[i], bias, var);
        }
        return NdArrayUtils.reshape(new Variable(values), v.getShape(), resultName);
    }

    public static float elu(float f) {
        if (f<0) return (float)Math.exp(f);
        return f+1;
    }

    public static Variable elu(Variable v) {
        float[] values = NdArrayUtils.flatten(v).get1d();
        for (int i = 0 ; i<values.length ; i++) {
            values[i] = elu(values[i]);
        }
        return NdArrayUtils.reshape(new Variable(values), v.getShape());
    }

    public static Variable elu(Variable v, String resultName) {
        float[] values = NdArrayUtils.flatten(v).get1d();
        for (int i = 0 ; i<values.length ; i++) {
            values[i] = elu(values[i]);
        }
        return NdArrayUtils.reshape(new Variable(values), v.getShape(), resultName);
    }

    public static float relu(float f) {
        if (f<0) return 0;
        return f;
    }

    public static Variable relu(Variable v) {
        float[] values = NdArrayUtils.flatten(v).get1d();
        for (int i = 0 ; i<values.length ; i++) {
            values[i] = relu(values[i]);
        }
        return NdArrayUtils.reshape(new Variable(values), v.getShape());
    }

    public static Variable relu(Variable v, String resultName) {
        float[] values = NdArrayUtils.flatten(v).get1d();
        for (int i = 0 ; i<values.length ; i++) {
            values[i] = relu(values[i]);
        }
        return NdArrayUtils.reshape(new Variable(values), v.getShape(), resultName);
    }

}
