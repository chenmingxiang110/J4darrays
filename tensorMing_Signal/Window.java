package tensorMing_Signal;

public class Window {

    public static float[] hamming (int length) {
        float[] result = new float[length];
        for (int i = 0 ; i<length ; i++) {
            result[i] = (float) (0.54-0.46*Math.cos((2*Math.PI*i)/(length-1)));
        }
        return result;
    }

    public static float[] hanning (int length) {
        float[] result = new float[length];
        for (int i = 0 ; i<length ; i++) {
            result[i] = (float) (0.5-0.5*Math.cos((2*Math.PI*i)/(length-1)));
        }
        return result;
    }

}
