package tensorMing_Examples;

import org.omg.CORBA.portable.ValueInputStream;
import tensorMing_Flow.ActivationFcns;
import tensorMing_Fundation.*;

import java.util.Arrays;

public class Simpleyx2 {

    public static void main(String[] args) {
        Variable x = new Variable("temp", new int[]{10}, "arange");
        x = NdArrayUtils.repeat(x, new int[]{1,10});
        x = NdArrayUtils.transpose(x);
//        x = NdArrayUtils.elementWiseMultiply(x, (float)0.2);
        Variable y = NdArrayUtils.elementWiseMultiply(x,x);

//        System.out.println(x);
//        System.out.println(y);
//        System.out.println("(0.2*0.2)==0.04 "+((0.2*0.2)==0.04));
//        System.out.println("");
//
//        System.out.println(ActivationFcns.Sigmoid(x)[0]);
//        System.out.println(ActivationFcns.Sigmoid(x)[1]);
//        System.out.println("==========================");

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

//            System.out.println(output1);
//            System.out.println(output2);
//            System.out.println(output3);
//            System.out.println("-------------");
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

}
