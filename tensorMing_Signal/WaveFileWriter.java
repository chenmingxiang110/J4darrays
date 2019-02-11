package tensorMing_Signal;

import java.io.File;

public class WaveFileWriter {

    public static void write(double[] data, int rate, String path) {
        try {
            WavFile wavFile = WavFile.newWavFile(new File(path), 2, data.length, 16, rate);
            wavFile.writeFrames(data, data.length);
            wavFile.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
