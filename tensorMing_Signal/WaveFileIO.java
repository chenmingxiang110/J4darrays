package tensorMing_Signal;

import tensorMing_Fundation.ArrayUtils;

import java.io.*;
import java.nio.ByteBuffer;


public class WaveFileIO {

    public static int[][] readBuffer16(byte[] input, int nChannels) {
        int[][] ret = new int[nChannels][input.length/(2*nChannels)];
        for (int x = 0; x < input.length; x+=(2*nChannels)) {
            for (int j = 0 ; j<nChannels ; j++) {
                ret[j][x/(2*nChannels)] = input[x+j*2+1]<<8+input[x+j*2];
            }
        }
        return ret;
    }

    public static void write(float[] data, int rate, String path) {
        double[] dataTransformed = ArrayUtils.fromFloatArray2Double(data);
        write(dataTransformed, rate, path);
    }

    public static void write(float[][] data, int rate, String path) {
        double[][] dataTransformed = ArrayUtils.fromFloatArray2Double(data);
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
        float[][] data = ArrayUtils.fromDoubleArray2Float(read(path));
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
