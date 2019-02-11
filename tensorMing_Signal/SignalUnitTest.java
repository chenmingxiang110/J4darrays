package tensorMing_Signal;

import java.util.Arrays;

public class SignalUnitTest {

    public static void main(String[] args) {
//        WaveFileReader_legacy wfr = new WaveFileIO("audio_test.wav");
//        float[] data = Filters.signalNormalize(wfr.getData()[0]);
//        int rate = (int)wfr.getSampleRate();
//        System.out.println(data.length);
//        System.out.println(rate);
//
//        float[][] fb = Filters.fbank(data, rate);
//        System.out.print(fb.length);
//        System.out.print(", ");
//        System.out.println(fb[0].length);
//        System.out.println(Arrays.toString(fb[333]));

        long[] info = WaveFileIO.readInfo("audio_test_short.wav");
        System.out.println(Arrays.toString(info));
        double[][] data = WaveFileIO.read("audio_test_short.wav");
        float[][] dataNorm = WaveFileIO.readNorm("audio_test_short.wav");
        WaveFileIO.write(data, (int)info[0], "test_raw.wav");
        WaveFileIO.write(dataNorm, (int)info[0], "test_norm.wav");
        dataNorm[0] = VAD.clip(dataNorm[0], (int)info[0]);
        WaveFileIO.write(dataNorm, (int)info[0], "test_clip.wav");
    }

}
