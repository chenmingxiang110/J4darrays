package tensorMing_Fundation;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Random {

    private final static float EPSILON = (float)0.00000001;

    public Random() {}

    // Return a random integer given probabilities. The sum of the probs is not necessarily to be 1.
    public static int randInt(float[] probs) {
        if (probs.length<=0) throw new IllegalArgumentException("Probability size must be greater than zero.");
        float sum = (float)0.0;
        for (float f : probs) sum+=f;
        float[] accumulations = new float[probs.length];
        accumulations[0] = sum;
        for (int i = 0 ; i<(probs.length-1) ; i++) {
            sum-=probs[i];
            accumulations[i+1] = sum;
        }
        for (int i = 0 ; i<probs.length ; i++) {
            if (probs[i]<=EPSILON) continue;
            float f = (float) Math.random();
            if (f<(probs[i]/accumulations[i])) return i;
        }
        return probs.length-1;
    }
    // Optimized quick method only for private use.
    private static int randInt(float[] probs, float[] accumulations) {
        if (probs.length<=0) throw new IllegalArgumentException("Probability size must be greater than zero.");
        for (int i = 0 ; i<probs.length ; i++) {
            if (probs[i]<=EPSILON) continue;
            float f = (float) Math.random();
            if (f<(probs[i]/accumulations[i])) return i;
        }
        return probs.length-1;
    }

    // Maximum is exclusive. Add 1 to make it inclusive.
    public static int randInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static float random() {
        return (float) Math.random();
    }

    public static int[] sampling(int size, int maximum, boolean repeat) {
        if (maximum<=0) throw new IllegalArgumentException("Maximum must be greater than zero.");
        if (size<0) throw new IllegalArgumentException("Size must be greater than or equal zero.");
        int[] result = new int[size];
        if (size == 0) return result;
        if (repeat) {
            for (int i = 0 ; i<size ; i++) {
                result[i] = randInt(0, maximum);
            }
        } else {
            if (size>maximum) throw new IllegalArgumentException("Size is greater than the maximum number.");
            ArrayList<Integer> numbers = new ArrayList<Integer>();
            for (int i = 0 ; i<maximum ; i++) {
                numbers.add(i);
            }
            ArrayList<Integer> list = samplingHelperUnrepeat(size, numbers);
            for (int i = 0 ; i<size ; i++) {
                result[i] = list.get(i);
            }
        }
        return result;
    }
    private static ArrayList<Integer> samplingHelperUnrepeat(int size, ArrayList<Integer> stuff) {
        if (size>1) {
            int choice = randInt(0, stuff.size());
            int theStuff = stuff.get(choice);
            stuff.remove(choice);
            ArrayList<Integer> result = samplingHelperUnrepeat(size-1, stuff);
            result.add(theStuff);
            return result;
        } else {
            ArrayList<Integer> result = new ArrayList<Integer>();
            int choice = randInt(0, stuff.size());
            result.add(stuff.get(choice));
            return result;
        }
    }

    // Sampling from given probabilities. The sum of the probs is not necessarily to be 1.
    public static int[] sampling(int size, float[] probs, boolean repeat) {
        if (probs.length<=0) throw new IllegalArgumentException("Probability size must be greater than zero.");
        if (size<0) throw new IllegalArgumentException("Size must be greater than or equal zero.");
        int[] result = new int[size];
        if (size == 0) return result;
        if (repeat) {
            float sum = (float)0.0;
            for (float f : probs) sum+=f;
            float[] accumulations = new float[probs.length];
            accumulations[0] = sum;
            for (int i = 0 ; i<(probs.length-1) ; i++) {
                sum-=probs[i];
                accumulations[i+1] = sum;
            }
            for (int i = 0 ; i<size ; i++) {
                result[i] = randInt(probs, accumulations);
            }
        } else {
            float[] probsCopy = probs.clone();
            for (int i = 0 ; i<size ; i++) {
                int choice = randInt(probsCopy);
                probsCopy[choice] = 0;
                result[i] = choice;
            }
        }
        return result;
    }

}
