package tensorMing_Learn;

import tensorMing_Fundation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ClusterDBScan {
    private int[] labels;
    private ArrayList<Variable> data;
    private float eps;
    private int min_samples;

    public ClusterDBScan(float eps, int min_samples) {
        this.eps = eps;
        this.min_samples = min_samples;
    }

    // Cluster starts from 1. If the label is 0, then it means the current point is a noise.
    public int[] getLabels() {
        return labels;
    }

    public void fit(ArrayList<Variable> av) {
        if (av.size()==0) {
            labels = new int[0];
            return;
        }
        int[] shape = av.get(0).getShape();
        for (Variable v : av) {
            if (v.getDimension()!=1 || !Arrays.equals(v.getShape(), shape)) throw new IllegalArgumentException(
                    "Invalid variables in the input. Variables must be 1d arrays with same shapes.");
        }

        labels = new int[av.size()];
        data = av;
        int label = 0;
        for (int i = 0 ; i<data.size() ; i++) {
            if (labels[i] == 0) {
                ArrayList<Integer> neighbours = regionQuery(i);
                if (neighbours.size()>=eps) {
                    label++;
                    expandCluster(i, label, neighbours);
                }
            }
        }
    }

    private void expandCluster(int index, int label, ArrayList<Integer> neighbours) {
        labels[index] = label;
        Set<Integer> neighboursSet = new HashSet<Integer>(neighbours);
        int i = 0;
        while (i<neighbours.size()) {
            int neighbour = neighbours.get(i);
            if (labels[neighbour] == 0) {
                labels[neighbour] = label;
                ArrayList<Integer> newNeighbours = regionQuery(neighbour);
                if (newNeighbours.size()>=min_samples) {
                    for (int j : newNeighbours) {
                        if (!neighboursSet.contains(j)) {
                            neighboursSet.add(j);
                            neighbours.add(j);
                        }
                    }
                }
            }
            i++;
        }
    }

    private ArrayList<Integer> regionQuery(int index) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0 ; i<data.size() ; i++) {
            if (i==index) {
                result.add(i);
                continue;
            }
            float distance = NdArrayMath.distance(data.get(index), data.get(i));
            if (distance<=eps) result.add(i);
        }
        return result;
    }
}
