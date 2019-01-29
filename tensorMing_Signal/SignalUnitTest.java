package tensorMing_Signal;

import tensorMing_Fundation.Variable;

import java.util.Arrays;

public class SignalUnitTest {

    public static void main(String[] args) {
        WaveFileReader wfr = new WaveFileReader("audio_test.wav");
        float[] data = Filters.signalNormalize(wfr.getData()[0]);
        int rate = (int)wfr.getSampleRate();
        System.out.println(data.length);
        System.out.println(rate);

        float[][] fb = Filters.fbank(data, rate);
        System.out.print(fb.length);
        System.out.print(", ");
        System.out.println(fb[0].length);
        System.out.println(Arrays.toString(fb[333]));
    }

}
