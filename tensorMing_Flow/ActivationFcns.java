package tensorMing_Flow;

import tensorMing_Fundation.*;

import java.util.Arrays;

public class ActivationFcns {

    private void template() {
//        int[] shape = v.getShape();
//        if (shape.length == 1) {
//            float[] values = v.get1d();
//            for (int i = 0 ; i<shape[0] ; i++) {}
//        } else if (shape.length == 2) {
//            float[][] values = v.get2d();
//            for (int i = 0 ; i<shape[0] ; i++) {
//                for (int j = 0 ; j<shape[1] ; j++) {}
//            }
//        } else if (shape.length == 3) {
//            float[][][] values = v.get3d();
//            for (int i = 0 ; i<shape[0] ; i++) {
//                for (int j = 0 ; j<shape[1] ; j++) {
//                    for (int k = 0 ; k<shape[2] ; k++) {}
//                }
//            }
//        } else if (shape.length == 4) {
//            float[][][][] values = v.get4d();
//            for (int i = 0 ; i<shape[0] ; i++) {
//                for (int j = 0 ; j<shape[1] ; j++) {
//                    for (int k = 0 ; k<shape[2] ; k++) {
//                        for (int l = 0 ; l<shape[3] ; l++) {}
//                    }
//                }
//            }
//        } else {
//            throw new IllegalArgumentException("Illegal shape.");
//        }
    }

    public static Variable[] Relu(Variable v) {
        int[] shape = v.getShape();
        Variable variable = new Variable("temp", shape, "zeros");
        Variable variable2 = new Variable("temp", shape, "zeros");
        if (shape.length == 1) {
            float[] values = v.get1d();
            float[] grad = new float[shape[0]];
            for (int i = 0 ; i<shape[0] ; i++) {
                if (values[i]<0) {
                    values[i]=0;
                } else {
                    grad[i]=1;
                }
            }
            variable.importValues(values);
            variable2.importValues(grad);
            return new Variable[]{variable, variable2};
        } else if (shape.length == 2) {
            float[][] values = v.get2d();
            float[][] grad = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    if (values[i][j]<0) {
                        values[i][j]=0;
                    } else {
                        grad[i][j]=1;
                    }
                }
            }
            variable.importValues(values);
            variable2.importValues(grad);
            return new Variable[]{variable, variable2};
        } else if (shape.length == 3) {
            float[][][] values = v.get3d();
            float[][][] grad = new float[shape[0]][shape[1]][shape[2]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        if (values[i][j][k]<0) {
                            values[i][j][k]=0;
                        } else {
                            grad[i][j][k]=1;
                        }
                    }
                }
            }
            variable.importValues(values);
            variable2.importValues(grad);
            return new Variable[]{variable, variable2};
        } else if (shape.length == 4) {
            float[][][][] values = v.get4d();
            float[][][][] grad = new float[shape[0]][shape[1]][shape[2]][shape[3]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        for (int l = 0 ; l<shape[3] ; l++) {
                            if (values[i][j][k][l]<0) {
                                values[i][j][k][l]=0;
                            } else {
                                grad[i][j][k][l]=1;
                            }
                        }
                    }
                }
            }
            variable.importValues(values);
            variable2.importValues(grad);
            return new Variable[]{variable, variable2};
        } else {
            throw new IllegalArgumentException("Illegal shape.");
        }
    }

    public static Variable[] Relu2(Variable v, float shrinkConstant) {
        int[] shape = v.getShape();
        Variable variable = new Variable("temp", shape, "zeros");
        Variable variable2 = new Variable("temp", shape, "zeros");
        if (shape.length == 1) {
            float[] values = v.get1d();
            float[] grad = new float[shape[0]];
            for (int i = 0 ; i<shape[0] ; i++) {
                if (values[i]<0) {
                    values[i]*=shrinkConstant;
                    grad[i]=shrinkConstant;
                } else {
                    grad[i]=1;
                }
            }
            variable.importValues(values);
            variable2.importValues(grad);
            return new Variable[]{variable, variable2};
        } else if (shape.length == 2) {
            float[][] values = v.get2d();
            float[][] grad = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    if (values[i][j]<0) {
                        values[i][j]*=shrinkConstant;
                        grad[i][j]=shrinkConstant;
                    } else {
                        grad[i][j]=1;
                    }
                }
            }
            variable.importValues(values);
            variable2.importValues(grad);
            return new Variable[]{variable, variable2};
        } else if (shape.length == 3) {
            float[][][] values = v.get3d();
            float[][][] grad = new float[shape[0]][shape[1]][shape[2]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        if (values[i][j][k]<0) {
                            values[i][j][k]*=shrinkConstant;
                            grad[i][j][k]=shrinkConstant;
                        } else {
                            grad[i][j][k]=1;
                        }
                    }
                }
            }
            variable.importValues(values);
            variable2.importValues(grad);
            return new Variable[]{variable, variable2};
        } else if (shape.length == 4) {
            float[][][][] values = v.get4d();
            float[][][][] grad = new float[shape[0]][shape[1]][shape[2]][shape[3]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        for (int l = 0 ; l<shape[3] ; l++) {
                            if (values[i][j][k][l]<0) {
                                values[i][j][k][l]*=shrinkConstant;
                                grad[i][j][k][l]=shrinkConstant;
                            } else {
                                grad[i][j][k][l]=1;
                            }
                        }
                    }
                }
            }
            variable.importValues(values);
            variable2.importValues(grad);
            return new Variable[]{variable, variable2};
        } else {
            throw new IllegalArgumentException("Illegal shape.");
        }
    }

    public static Variable[] Sigmoid(Variable v) {
        int[] shape = v.getShape();
        Variable variable = new Variable("temp", shape, "zeros");
        Variable variable2 = new Variable("temp", shape, "zeros");
        if (shape.length == 1) {
            float[] values = v.get1d();
            float[] grad = new float[shape[0]];
            for (int i = 0 ; i<shape[0] ; i++) {
                values[i] = (float)(1.0/(1.0+Math.exp(-values[i])));
                grad[i] = values[i]*(1-values[i]);
            }
            variable.importValues(values);
            variable2.importValues(grad);
            return new Variable[]{variable, variable2};
        } else if (shape.length == 2) {
            float[][] values = v.get2d();
            float[][] grad = new float[shape[0]][shape[1]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    values[i][j] = (float)(1.0/(1.0+Math.exp(-values[i][j])));
                    grad[i][j] = values[i][j]*(1-values[i][j]);
                }
            }
            variable.importValues(values);
            variable2.importValues(grad);
            return new Variable[]{variable, variable2};
        } else if (shape.length == 3) {
            float[][][] values = v.get3d();
            float[][][] grad = new float[shape[0]][shape[1]][shape[2]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        values[i][j][k] = (float)(1.0/(1.0+Math.exp(-values[i][j][k])));
                        grad[i][j][k] = values[i][j][k]*(1-values[i][j][k]);
                    }
                }
            }
            variable.importValues(values);
            variable2.importValues(grad);
            return new Variable[]{variable, variable2};
        } else if (shape.length == 4) {
            float[][][][] values = v.get4d();
            float[][][][] grad = new float[shape[0]][shape[1]][shape[2]][shape[3]];
            for (int i = 0 ; i<shape[0] ; i++) {
                for (int j = 0 ; j<shape[1] ; j++) {
                    for (int k = 0 ; k<shape[2] ; k++) {
                        for (int l = 0 ; l<shape[3] ; l++) {
                            values[i][j][k][l] = (float)(1.0/(1.0+Math.exp(-values[i][j][k][l])));
                            grad[i][j][k][l] = values[i][j][k][l]*(1-values[i][j][k][l]);
                        }
                    }
                }
            }
            variable.importValues(values);
            variable2.importValues(grad);
            return new Variable[]{variable, variable2};
        } else {
            throw new IllegalArgumentException("Illegal shape.");
        }
    }

}
