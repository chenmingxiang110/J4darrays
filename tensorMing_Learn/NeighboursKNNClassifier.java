package tensorMing_Learn;

import tensorMing_Fundation.NdArrayMath;
import tensorMing_Fundation.NdArrayUtils;
import tensorMing_Fundation.Variable;

import java.util.ArrayList;
import java.util.Arrays;

public class NeighboursKNNClassifier {

    private boolean isCosine;
    private int k;

    public NeighboursKNNClassifier(int kValue, boolean mode) {
        k = kValue-1;
        isCosine = mode;
    }

    private float findKthValue(ArrayList<Float> list, int k) {
        assert list.size()>=1;
        assert k >= 0;
        assert k < list.size();

        if (list.size()==1) {
            return list.get(0);
        }

        ArrayList<Float> left = new ArrayList<>();
        ArrayList<Float> right = new ArrayList<>();

        for (int i = 1 ; i<list.size() ; i++) {
            if (list.get(i)<list.get(0)) {
                left.add(list.get(i));
            } else if (list.get(i)>list.get(0)){
                right.add(list.get(i));
            }
        }

        if (left.size()==k) return list.get(0);
        if (left.size()>k) return findKthValue(left, k);
        if ((list.size()-right.size())>k) return list.get(0);
        return findKthValue(right, k-list.size()+right.size());
    }

    public float[] predict(Variable input, ArrayList<Variable> xs, ArrayList<Integer> ys, int maxLabel) {
        assert xs.size() == ys.size();
        ArrayList<Float> dists = new ArrayList<>();
        float[] counts = new float[maxLabel];
        if (k>=ys.size()) {
            for (int i : ys) {
                counts[i] += 1;
            }
            float sum = (float)0.0;
            for (float i : counts) {
                sum += i;
            }
            for (int i = 0 ; i<maxLabel ; i++) {
                counts[i]/=(float)(sum+0.00000001);
            }
        } else {
            for (int i = 0 ; i<ys.size() ; i++) {
                if (isCosine) {
                    dists.add((float)1.0-NdArrayMath.cosineSim(xs.get(i), input));
                } else {
                    dists.add(NdArrayMath.distance(xs.get(i), input));
                }
            }

            float threshold = findKthValue(dists, k);

            for (int i = 0 ; i<ys.size() ; i++) {
                if (dists.get(i)<=threshold) {
                    counts[ys.get(i)] += 1;
                }
            }
//            System.out.println(Arrays.toString(dists.toArray()));
//            System.out.println(threshold);
//            System.out.println(Arrays.toString(counts));
            float sum = (float)0.0;
            for (float i : counts) {
                sum += i;
            }
            for (int i = 0 ; i<maxLabel ; i++) {
                counts[i]/=(float)(sum+0.00000001);
            }
        }

        return counts;
    }
}
