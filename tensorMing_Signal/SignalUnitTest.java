package tensorMing_Signal;

public class SignalUnitTest {

    public static void main(String[] args) {
        WaveFileReader wfr = new WaveFileReader("audio_test.wav");
        int[] data = wfr.getData()[0];
        int samplerate = (int)wfr.getSampleRate();
        System.out.println(data.length);
        System.out.println(samplerate);
    }

}
