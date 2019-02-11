package tensorMing_Signal;

import tensorMing_Fundation.*;

import java.util.Arrays;

public class Filters {

    private static float[] _signalPad(float[] signal, int winlen, int winstep) {
        if (signal.length<winlen) {
            return NdArrayUtils.pad(signal, winlen);
        }
        if ((signal.length-winlen)%winstep == 0) {
            return signal;
        }
        int numPad = winstep-((signal.length-winlen)%winstep);
        return NdArrayUtils.pad(signal, signal.length+numPad);
    }

    // Frame a signal into overlapping frames.
    public static float[][] frameSeg(float[] signal, int frame_len, int frame_step, String windowFunc) {
        if (signal.length<1) {
            throw new IllegalArgumentException("Empty signal.");
        }
        if (frame_len<1) {
            throw new IllegalArgumentException("Frame length cannot be smaller than 1.");
        }
        if (frame_step<1) {
            throw new IllegalArgumentException("Frame step cannot be smaller than 1.");
        }
        if (signal.length<frame_len) {
            throw new IllegalArgumentException("Frame length is larger than the signal length.");
        }

        int nTimeStep = (signal.length-frame_len+frame_step)/frame_step;
        float[][] result = new float[nTimeStep][frame_len];
        for (int i = 0 ; i<nTimeStep ; i++) {
            if (windowFunc == "raw") {
                result[i] = Arrays.copyOfRange(signal, i*frame_step, i*frame_step+frame_len);
            } else if (windowFunc == "hamming") {
                result[i] = Arrays.copyOfRange(signal, i*frame_step, i*frame_step+frame_len);
                float[] window = Window.hamming(frame_len);
                for (int j = 0 ; j<frame_len ; j++) result[i][j]*=window[j];
            } else if (windowFunc == "hanning") {
                result[i] = Arrays.copyOfRange(signal, i*frame_step, i*frame_step+frame_len);
                float[] window = Window.hanning(frame_len);
                for (int j = 0 ; j<frame_len ; j++) result[i][j]*=window[j];
            } else {
                throw new IllegalArgumentException("Invalid window function: "+windowFunc);
            }
        }
        return result;
    }

    public static float[] zeroPad(float[] signal, int objLength) {
        float[] result = new float[objLength];
        for (int i = 0 ; i<signal.length ; i++) result[i] = signal[i];
        return result;
    }

    public static float[] signalNormalize(int[] signal) {
        float max = signal[0];
        for (int i : signal) {
            if (i>max) max = i;
        }
        float[] result = new float[signal.length];
        for (int i = 0 ; i<signal.length ; i++) {
            result[i] = ((float) signal[i])/(max);
        }
        return result;
    }

    public static float[] signalNormalize(float[] signal) {
        float max = signal[0];
        for (float i : signal) {
            if (i>max) max = i;
        }
        float[] result = new float[signal.length];
        for (int i = 0 ; i<signal.length ; i++) {
            result[i] = ((float) signal[i])/(max);
        }
        return result;
    }

    public static double[] signalNormalize(double[] signal) {
        double max = signal[0];
        for (double i : signal) {
            if (i>max) max = i;
        }
        double[] result = new double[signal.length];
        for (int i = 0 ; i<signal.length ; i++) {
            result[i] = signal[i]/max;
        }
        return result;
    }

    public static float[] preemphasis(float[] signal, float preemph) {
        if (signal.length<1) {
            throw new IllegalArgumentException("Empty signal.");
        }
        float[] result = new float[signal.length];
        result[0] = signal[0];
        for (int i = 1 ; i<signal.length ; i++) {
            result[i] = signal[i] - signal[i-1] * preemph;
        }
        return result;
    }

    // Compute the magnitude spectrum of each frame in frames. If frames is an NxD matrix, output will be Nx(NFFT/2+1).
    public static float[][] magspec(float[][] frames, int nfft) {
        float[][] result = new float[frames.length][nfft/2+1];
        for (int i = 0 ; i<frames.length ; i++) {
            float[] input;
            if (nfft>frames[i].length) {
                input = zeroPad(frames[i], nfft);
            } else {
                input = Arrays.copyOfRange(frames[i],0,nfft);
            }
            Complex[] inputComplex = Complex.fromArray(input);
            Complex[] fftResult = FFT.fft(inputComplex);
            result[i] = Utils.fromDoubleArray2Float(Complex.modularLengthArray(fftResult));
        }
        return result;
    }

    // Compute the power spectrum of each frame in frames. If frames is an NxD matrix, output will be Nx(NFFT/2+1).
    public static float[][] powspec(float[][] frames, int nfft) {
        float[][] result = new float[frames.length][nfft/2+1];
        for (int i = 0 ; i<frames.length ; i++) {
            float[] input;
            if (nfft>frames[i].length) {
                input = zeroPad(frames[i], nfft);
            } else {
                input = Arrays.copyOfRange(frames[i],0,nfft);
            }
            Complex[] inputComplex = Complex.fromArray(input);
            Complex[] fftResult = FFT.rfft(inputComplex);
            result[i] = Utils.fromDoubleArray2Float(Complex.squareSumArray(fftResult));
        }

        for (int i = 0 ; i<result.length ; i++) {
            for (int j = 0 ; j<result[0].length ; j++) {
                result[i][j] = (float) (1.0 / nfft * result[i][j]);
            }
        }
        return result;
    }

    // Convert a value in Hertz to Mels.
    public static float hz2mel(float hz){
        return (float) (2595.0 * Math.log10(1.0+hz/700.0));
    }

    // Convert an array in Hertz to Mels.
    public static float[] hz2mel(float[] hzs){
        float[] result = new float[hzs.length];
        for (int i = 0 ; i<hzs.length ; i++) {
            result[i] = (float) (2595.0 * Math.log10(1.0+hzs[i]/700.0));
        }
        return result;
    }

    // Convert a value in Mels to Hertz.
    public static float mel2hz(float mel){
        return (float) (700.0*Math.pow(10.0, (mel/2595.0)-1.0));
    }

    // Convert an array in Mels to Hertz.
    public static float[] mel2hz(float[] mels){
        float[] result = new float[mels.length];
        for (int i = 0 ; i<mels.length ; i++) {
            result[i] = (float) (700.0*(Math.pow(10.0, (mels[i]/2595.0))-1.0));
        }
        return result;
    }

    public static float[][] get_filterbanks(int nfilt, int nfft, int samplerate, int lowfreq, int highfreq) {
        if (highfreq>(samplerate/2)) {
            throw new IllegalArgumentException("High frequency higher than sample rate / 2.");
        }
        if (lowfreq>=highfreq) {
            throw new IllegalArgumentException("Low frequency cannot be higher than (or equal to) high frequency.");
        }
        if (lowfreq>(samplerate/2)) {
            throw new IllegalArgumentException("Low frequency higher than sample rate / 2.");
        }
        if (lowfreq<0 || highfreq<0) {
            throw new IllegalArgumentException("Frequency cannot be lower than zero.");
        }
        float lowmel = hz2mel(lowfreq);
        float highmel = hz2mel(highfreq);
        float[] melpoints = mel2hz(NdArrayUtils.linspace(lowmel, highmel, nfilt+2));
        float[] bins = new float[melpoints.length];
        for (int i = 0 ; i<melpoints.length ; i++) {
            bins[i] = (float)Math.floor((nfft+1)*melpoints[i]/(float) samplerate);
        }

        float[][] fbank = new float[nfilt][nfft/2+1];
        for (int i = 0 ; i<nfilt ; i++) {
            for (int j = (int)bins[i] ; j<(int)bins[i+1] ; j++) {
                fbank[i][j] = (j-bins[i])/(bins[i+1]-bins[i]);
            }
            for (int j = (int)bins[i+1] ; j<(int)bins[i+2] ; j++) {
                fbank[i][j] = (bins[i+2]-j)/(bins[i+2]-bins[i+1]);
            }
        }
        return fbank;
    }

    public static float[] getEnergy(float[] signal, int winlen, int winstep, int nfft, float preemph, String windowFunc) {
        if (signal.length<1) {
            throw new IllegalArgumentException("Empty signal.");
        }
        float eps = (float) 1e-32;
        float[] signalEmp = preemphasis(signal, preemph);
        float[][] frames = frameSeg(signalEmp, winlen, winstep, windowFunc);
        float[][] pspec = powspec(frames, nfft);
        float[] energy = NdArrayUtils.sum(new Variable(pspec), 1).get1d();
        for (int i = 0 ; i<energy.length ; i++) {
            if (energy[i]<eps) energy[i] = eps;
        }
        return energy;
    }

    public static float[] getEnergy(float[] signal) {
        return getEnergy(signal, 400, 160, 512, (float)0.97, "raw");
    }

    public static float[][] fbank(float[] signal, int samplerate, int winlen, int winstep, int nfilt, int nfft, int lowfreq, int highfreq, float preemph, String windowFunc) {
        if (signal.length<1) {
            throw new IllegalArgumentException("Empty signal.");
        }
        float eps = (float) 1e-32;
        float[] signalEmp = preemphasis(signal, preemph);
        float[] signal_padded = _signalPad(signalEmp, winlen, winstep);
        float[][] frames = frameSeg(signal_padded, winlen, winstep, windowFunc);
        float[][] pspec = powspec(frames, nfft);
        float[] energy = NdArrayUtils.sum(new Variable(pspec), 1).get1d();
        for (int i = 0 ; i<energy.length ; i++) {
            if (energy[i]<eps) energy[i] = eps;
        }
        float[][] fb = get_filterbanks(nfilt, nfft, samplerate, lowfreq, highfreq);
        float[][] feats = NdArrayUtils.matmul(new Variable(pspec), NdArrayUtils.transpose(new Variable(fb))).get2d();
        for (int i = 0 ; i<feats.length ; i++) {
            for (int j = 0 ; j<feats[0].length ; j++) {
                if (feats[i][j]<eps) feats[i][j] = eps;
            }
        }
        return feats;
    }

    public static float[][] fbank(float[] signal, int samplerate) {
        if (signal.length<1) {
            throw new IllegalArgumentException("Empty signal.");
        }
        return fbank(signal, samplerate, 400, 160, 40, 512, 0, samplerate/2, (float)0.97, "raw");
    }

    public static float[][] logfbank(float[] signal, int samplerate, int winlen, int winstep, int nfilt, int nfft, int lowfreq, int highfreq, float preemph, String windowFunc) {
        if (signal.length<1) {
            throw new IllegalArgumentException("Empty signal.");
        }
        float[][] fbResult = fbank(signal, samplerate, winlen, winstep, nfilt, nfft, lowfreq, highfreq, preemph, windowFunc);
        for (int i = 0 ; i<fbResult.length ; i++) {
            for (int j = 0 ; j<fbResult[0].length ; j++) {
                fbResult[i][j] = (float) Math.log10(fbResult[i][j]);
            }
        }
        return fbResult;
    }

    public static float[][] logfbank(float[] signal, int samplerate) {
        if (signal.length<1) {
            throw new IllegalArgumentException("Empty signal.");
        }
        float[][] fbResult = fbank(signal, samplerate, 400, 160, 40, 512, 0, samplerate/2, (float)0.97, "raw");
        for (int i = 0 ; i<fbResult.length ; i++) {
            for (int j = 0 ; j<fbResult[0].length ; j++) {
                fbResult[i][j] = (float) Math.log10(fbResult[i][j]);
            }
        }
        return fbResult;
    }

}
