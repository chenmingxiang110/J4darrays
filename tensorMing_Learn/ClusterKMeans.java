package tensorMing_Learn;

import tensorMing_Fundation.NdArrayMath;
import tensorMing_Fundation.NdArrayUtils;
import tensorMing_Fundation.Random;
import tensorMing_Fundation.Variable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ClusterKMeans {

    private int nClusters;
    private int[] labels;
    private ArrayList<Variable> data;
    private ArrayList<Variable> centroids;

    public ClusterKMeans() {
        nClusters = 1;
        labels = new int[0];
    }

    public int[] getLabels() {
        return labels;
    }

    public ArrayList<Variable> getCentroids() {
        return centroids;
    }

    public void initCentroids(ArrayList<Variable> av, int nClusters) {
        if (nClusters < 1) throw new IllegalArgumentException("Invalid number of clusters.");
        this.nClusters = nClusters;

        if (av.size() == 0) {
            labels = new int[0];
            return;
        }

        if (av.size() == 1) {
            labels = new int[1];
            return;
        }

        int[] shape = av.get(0).getShape();
        for (Variable v : av) {
            if (v.getDimension() != 1 || !Arrays.equals(v.getShape(), shape)) throw new IllegalArgumentException(
                    "Invalid variables in the input. Variables must be 1d arrays with same shapes.");
        }

        labels = new int[av.size()];
        data = av;
        centroids = new ArrayList<>();
        int[] indices = Random.sampling(nClusters, av.size(), false);
        for (int i : indices) {
            centroids.add(av.get(i));
        }
        for (int i = 0 ; i<av.size() ; i++) {
            float minDist = NdArrayMath.distance(av.get(i), centroids.get(0));
            int minIndex = 0;
            for (int j = 1 ; j<centroids.size() ; j++) {
                float dist = NdArrayMath.distance(av.get(i), centroids.get(j));
                if (dist<minDist) {
                    minDist = dist;
                    minIndex = j;
                }
            }
            labels[i] = minIndex;
        }
    }

    public void initCentroidsPlus(ArrayList<Variable> av, int nClusters) {
        if (nClusters<1) throw new IllegalArgumentException("Invalid number of clusters.");
        this.nClusters = nClusters;

        if (av.size()==0) {
            labels = new int[0];
            return;
        }

        if (av.size()==1) {
            labels = new int[1];
            return;
        }

        int[] shape = av.get(0).getShape();
        for (Variable v : av) {
            if (v.getDimension()!=1 || !Arrays.equals(v.getShape(), shape)) throw new IllegalArgumentException(
                    "Invalid variables in the input. Variables must be 1d arrays with same shapes.");
        }

        labels = new int[av.size()];
        data = av;
        float[] probs = new float[av.size()];
        float[] dist2 = new float[av.size()];
        for (int i = 0 ; i<av.size() ; i++) {
            float minDist;
            if (i!=0) {
                minDist = NdArrayMath.distance(av.get(i), av.get(0));
            } else {
                minDist = NdArrayMath.distance(av.get(i), av.get(1));
            }
            for (int j = 1 ; j<av.size() ; j++) {
                if (j==i) continue;;
                float dist = NdArrayMath.distance(av.get(i), av.get(j));
                if (dist<minDist) {
                    minDist = dist;
                }
            }
            dist2[i] = (float)Math.pow(minDist, 2);
        }
        float total = 0;
        for (int i = 0 ; i<av.size() ; i++) {
            total += dist2[i];
        }
        for (int i = 0 ; i<av.size() ; i++) {
            probs[i] = dist2[i]/total;
        }

        centroids = new ArrayList<>();
        int[] indices = Random.sampling(nClusters, probs, false);
        for (int i : indices) {
            centroids.add(av.get(i));
        }
        for (int i = 0 ; i<av.size() ; i++) {
            float minDist = NdArrayMath.distance(av.get(i), centroids.get(0));
            int minIndex = 0;
            for (int j = 1 ; j<centroids.size() ; j++) {
                float dist = NdArrayMath.distance(av.get(i), centroids.get(j));
                if (dist<minDist) {
                    minDist = dist;
                    minIndex = j;
                }
            }
            labels[i] = minIndex;
        }
    }

    public void fit() {
        centroids = new ArrayList<>();
        HashMap<Integer, Variable> centSum = new HashMap<>();
        HashMap<Integer, Integer> centCount = new HashMap<>();
        for (int i = 0 ; i<nClusters ; i++) {
            centSum.put(i, new Variable("temp", data.get(0).getShape(), "zeros"));
            centCount.put(i,0);
        }
        for (int i = 0 ; i<data.size() ; i++) {
            int label = labels[i];
            Variable c = centSum.get(label);
            centSum.put(label, NdArrayUtils.elementWiseAdd(c, data.get(i)));
            centCount.put(label, centCount.get(label)+1);
        }
        for (int i = 0 ; i<nClusters ; i++) {
            Variable c = centSum.get(i);
            centroids.add(NdArrayUtils.elementWiseDivide(c, centCount.get(i)));
        }
        for (int i = 0 ; i<data.size() ; i++) {
            float minDist = NdArrayMath.distance(data.get(i), centroids.get(0));
            int minIndex = 0;
            for (int j = 1 ; j<centroids.size() ; j++) {
                float dist = NdArrayMath.distance(data.get(i), centroids.get(j));
                if (dist<minDist) {
                    minDist = dist;
                    minIndex = j;
                }
            }
            labels[i] = minIndex;
        }
    }

}
