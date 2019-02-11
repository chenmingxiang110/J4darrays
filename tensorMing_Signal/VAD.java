package tensorMing_Signal;

import tensorMing_Fundation.Utils;

import java.util.Arrays;

public class VAD {

    public static float[] clip(float[] input, int rate) {
        if (input.length<=0) {
            return input;
        }
        return clip(input, rate, 0.2, 0.1, 0.5);
    }

    public static float[] clip(float[] input, int rate, double starting, double ending, double durance) {
        if (input.length<=0) {
            return input;
        }
        return clip(input, rate, 400, 160, starting, ending, durance);
    }

    public static float[] clip(float[] input, int rate, int frameLength, int frameStride, double starting, double ending, double durance) {
        if (input.length<=0) {
            return input;
        }
        double[] result = clip(Utils.fromFloatArray2Double(input), rate, frameLength, frameStride, starting, ending, durance);
        return Utils.fromDoubleArray2Float(result);
    }

    public static double[] clip(double[] input, int rate) {
        if (input.length<=0) {
            return input;
        }
        return clip(input, rate, 0.25, 0.2, 0.5);
    }

    public static double[] clip(double[] input, int rate, double starting, double ending, double durance) {
        if (input.length<=0) {
            return input;
        }
        return clip(input, rate, 400, 160, starting, ending, durance);
    }

    private static double getEnergy(double[] frame) {
        double energy = 0;
        for (double d : frame) {
            energy+=(d*d);
        }
        return energy;
    }

    public static double[] clip(double[] input, int rate, int frameLength, int frameStride, double starting, double ending, double durance) {
        if (input.length<=0) {
            return input;
        }
        int duranceFrames = Math.max((int)((durance*rate)/frameStride), 1);
        double startingT = starting*starting*frameLength;
        double endingT = ending*ending*frameLength;

        double[] inputNorm = Filters.signalNormalize(input);
        int i = 0;
        int startPoint = -1;
        int endPoint = -1;
        int consecutiveEndings = 0;
        while ((i+frameLength+(duranceFrames+1)*frameStride)<input.length) {
            double energy = getEnergy(Arrays.copyOfRange(input, i, i+frameLength));
            if (startPoint<0) {
                if (energy>=startingT) {
                    startPoint = Math.max(i-duranceFrames*frameStride/2, 0);
                }
            } else {
                if (energy<=endingT) {
                    consecutiveEndings+=1;
                    if (consecutiveEndings>=duranceFrames) {
                        endPoint = i;
                        break;
                    }
                } else {
                    consecutiveEndings = 0;
                }
            }
            i+=frameStride;
        }
        if (startPoint<0) {
            return new double[0];
        }
        if (endPoint<0) {
            return Arrays.copyOfRange(input, startPoint, input.length);
        }
        return Arrays.copyOfRange(input, startPoint, endPoint);
    }

}
