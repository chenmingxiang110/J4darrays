package tensorMing_Signal;

public class Window {

    public static float[] rectangle (int length) {
        if (length<=0) {
            throw new IllegalArgumentException("Window length must be positive.");
        }
        float[] result = new float[length];
        for (int i = 0 ; i<length ; i++) {
            result[i] = (float) 1;
        }
        return result;
    }

    public static float[] gaussian (int length, float sigma) {
        if (sigma>0.5) {
            throw new IllegalArgumentException("Sigma should be less than 0.5.");
        }

        if (sigma<0) {
            throw new IllegalArgumentException("Sigma should be positive.");
        }

        if (length<=0) {
            throw new IllegalArgumentException("Window length must be positive.");
        }

        float[] result = new float[length];
        for (int i = 0 ; i<length ; i++) {
            result[i] = (float) Math.exp(-0.5*Math.pow(((i-(length-1.0)/2.0)/(sigma*(length-1.0)/2.0)),2));
        }
        return result;
    }

    public static float[] bartlett (int length) {
        if (length<=0) {
            throw new IllegalArgumentException("Window length must be positive.");
        }
        float[] result = new float[length];
        for (int i = 0 ; i<length ; i++) {
            result[i] = (float) ((2.0/(length-1.0))*((length-1.0)/2.0-Math.abs(i-(length-1.0)/2.0)));
        }
        return result;
    }

    public static float[] triangle (int length) {
        if (length<=0) {
            throw new IllegalArgumentException("Window length must be positive.");
        }
        float[] result = new float[length];
        for (int i = 0 ; i<length ; i++) {
            result[i] = (float) ((2.0/length)*(length/2.0-Math.abs(i-(length-1.0)/2.0)));
        }
        return result;
    }

    public static float[] hamming (int length) {
        if (length<=0) {
            throw new IllegalArgumentException("Window length must be positive.");
        }
        float[] result = new float[length];
        for (int i = 0 ; i<length ; i++) {
            result[i] = (float) (0.53836-0.46164*Math.cos((2*Math.PI*i)/(length-1)));
        }
        return result;
    }

    public static float[] hanning (int length) {
        if (length<=0) {
            throw new IllegalArgumentException("Window length must be positive.");
        }
        float[] result = new float[length];
        for (int i = 0 ; i<length ; i++) {
            result[i] = (float) (0.5-0.5*Math.cos((2*Math.PI*i)/(length-1)));
        }
        return result;
    }

}
