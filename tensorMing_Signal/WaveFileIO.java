package tensorMing_Signal;

import tensorMing_Fundation.Utils;

import java.io.*;


public class WaveFileIO {

    public static void write(float[] data, int rate, String path) {
        double[] dataTransformed = Utils.fromFloatArray2Double(data);
        write(dataTransformed, rate, path);
    }

    public static void write(float[][] data, int rate, String path) {
        double[][] dataTransformed = Utils.fromFloatArray2Double(data);
        write(dataTransformed, rate, path);
    }

    public static void write(double[] data, int rate, String path) {
        try {
            WavFile wavFile = WavFile.newWavFile(new File(path), 1, data.length, 16, rate);
            wavFile.writeFrames(data, data.length);
            wavFile.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void write(double[][] data, int rate, String path) {
        try {
            WavFile wavFile = WavFile.newWavFile(new File(path), data.length, data[0].length, 16, rate);
            wavFile.writeFrames(data, data[0].length);
            wavFile.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static double[][] read(String path) {
        double[][] result = new double[0][0];
        try {
            WavFile wavFile = WavFile.openWavFile(new File(path));
            int numChannels = wavFile.getNumChannels();
            if (wavFile.getNumFrames() > Integer.MAX_VALUE) {
                throw new WavFileException("Audio too long to read");
            }
            double[][] buffer = new double[numChannels][(int) wavFile.getNumFrames()];
            int framesRead = wavFile.readFrames(buffer, (int) wavFile.getNumFrames());
            result = buffer;
            wavFile.close();

        } catch (Exception e) {
            System.err.println(e);
        }
        return result;
    }

    public static float[][] readNorm(String path) {
        float[][] data = Utils.fromDoubleArray2Float(read(path));
        for (int i = 0 ; i<data.length ; i++) {
            data[i] = Filters.signalNormalize(data[i]);
        }
        return data;
    }

    // Info includes: rate, nChannels, dataLength, bitDepth
    public static long[] readInfo(String path) {
        long[] result = new long[4];
        try {
            WavFile wavFile = WavFile.openWavFile(new File(path));
            result[0] = wavFile.getSampleRate();
            result[1] = (long) wavFile.getNumChannels();
            result[2] = wavFile.getNumFrames();
            result[3] = (long) wavFile.getValidBits();
        } catch (Exception e) {
            System.err.println(e);
        }
        return result;
    }

}
