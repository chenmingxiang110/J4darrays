package tensorMing_Evaluation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class StringDistance {

    // insertions, deletions, and substitution
    public static int getLevenshteinDistance(String a, String b) {
        if (a.length()==0) return b.length();
        if (b.length()==0) return a.length();
        if (a.substring(0,1).equals(b.substring(0,1))) {
            return getLevenshteinDistance(a.substring(1), b.substring(1));
        }
        return 1+Math.min(getLevenshteinDistance(a.substring(1), b.substring(1)),
                          Math.min(getLevenshteinDistance(a, b.substring(1)),
                                   getLevenshteinDistance(a.substring(1), b)));
    }

    // insertions, deletions, substitution, and transposition
    public static int getDamerauLevenshteinDistance(String a, String b) {
        if (a.length()==0) return b.length();
        if (b.length()==0) return a.length();
        if (a.substring(0,1).equals(b.substring(0,1))) {
            return getLevenshteinDistance(a.substring(1), b.substring(1));
        }
        if (a.length()>=2 && b.length()>=2 && a.substring(0,1).equals(b.substring(1,2)) && a.substring(1,2).equals(b.substring(0,1))) {
            return 1+getLevenshteinDistance(a.substring(2), b.substring(2));
        }
        return 1+Math.min(getLevenshteinDistance(a.substring(1), b.substring(1)),
                Math.min(getLevenshteinDistance(a, b.substring(1)),
                        getLevenshteinDistance(a.substring(1), b)));
    }

    public static float getLevenshteinSim(String a, String b) {
        int d = getLevenshteinDistance(a, b);
        return 1 - ((float) d) / ((float) Math.max(a.length(), b.length()));
    }

    public static float getDamerauLevenshteinSim(String a, String b) {
        int d = getDamerauLevenshteinDistance(a, b);
        return 1 - ((float) d) / ((float) Math.max(a.length(), b.length()));
    }

    public static float getJaroSim(String s, String t) {
        int s_len = s.length();
        int t_len = t.length();

        if (s_len == 0 && t_len == 0) return 1;

        int match_distance = Integer.max(s_len, t_len) / 2 - 1;

        boolean[] s_matches = new boolean[s_len];
        boolean[] t_matches = new boolean[t_len];

        int matches = 0;
        int transpositions = 0;

        for (int i = 0; i < s_len; i++) {
            int start = Integer.max(0, i-match_distance);
            int end = Integer.min(i+match_distance+1, t_len);

            for (int j = start; j < end; j++) {
                if (t_matches[j]) continue;
                if (s.charAt(i) != t.charAt(j)) continue;
                s_matches[i] = true;
                t_matches[j] = true;
                matches++;
                break;
            }
        }

        if (matches == 0) return 0;

        int k = 0;
        for (int i = 0; i < s_len; i++) {
            if (!s_matches[i]) continue;
            while (!t_matches[k]) k++;
            if (s.charAt(i) != t.charAt(k)) transpositions++;
            k++;
        }

        return (float)((((double)matches / s_len) + ((double)matches / t_len) +
               (((double)matches - transpositions/2.0) / matches)) / 3.0);
    }

    public static float getJaroWinklerSim(String s, String t, float scale) {
        float sim = getJaroSim(s, t);
        int l = 0;
        for (int i = 0 ; i < Math.min(Math.min(s.length(), t.length()),4) ; i++) {
            if (s.substring(i,i+1).equals(t.substring(i,i+1))) {
                l++;
            } else {
                break;
            }
        }
        float result = sim+((float) l)*scale*((float)1.0-sim);
        return result;
    }

    public static float getJaroWinklerSim(String s, String t) {
        return getJaroWinklerSim(s, t, (float) 0.1);
    }

    public static int getHammingDistance(String a, String b) {
        if (a.length() != b.length()) throw new IllegalArgumentException("Hamming distance can only measure the distance between two strings of equal length.");
        int err = 0;
        for (int i = 0 ; i < a.length() ; i++) {
            if (!(a.substring(i,i+1).equals(b.substring(i,i+1)))) {
                err+=1;
            }
        }
        return err;
    }

    public static float getWER(String a, String b) {
        return 1-getLevenshteinSim(a, b);
    }

    public static String getNearstString(String a, ArrayList<String> sl) {
        String word = sl.get(0);
        float maxSim = getLevenshteinSim(a, sl.get(0));
        for (String s : sl) {
            float currSim = getLevenshteinSim(a, s);
            if (currSim>maxSim) {
                maxSim = currSim;
                word = s;
            }
        }
        return word;
    }

    public static String getNearstString(String a, String[] sl) {
        String word = sl[0];
        float maxSim = getLevenshteinSim(a, sl[0]);
        for (String s : sl) {
            float currSim = getLevenshteinSim(a, s);
            if (currSim>maxSim) {
                maxSim = currSim;
                word = s;
            }
        }
        return word;
    }

    public static String[] getKNearstString(String a, int k, String mode, ArrayList<String> sl) {
        Comparator<String> comparator = new StringSimComparator(a, mode);
        PriorityQueue<String> queue = new PriorityQueue<>(8, comparator);
        for (String s : sl) {
            queue.add(s);
        }
        String[] result = new String[Math.min(k, sl.size())];
        for (int i = 0 ; i<k ; i++) {
            result[i] = queue.remove();
        }
        return result;
    }

    public static String[] getKNearstString(String a, int k, String mode, String[] sl) {
        Comparator<String> comparator = new StringSimComparator(a, mode);
        PriorityQueue<String> queue = new PriorityQueue<>(8, comparator);
        for (String s : sl) {
            queue.add(s);
        }
        String[] result = new String[Math.min(k, sl.length)];
        for (int i = 0 ; i<k ; i++) {
            result[i] = queue.remove();
        }
        return result;
    }

    public static String[] getKNearstString(String a, int k, String mode, int prefix, ArrayList<String> sl) {
        Comparator<String> comparator = new StringSimComparator(a, mode, prefix);
        PriorityQueue<String> queue = new PriorityQueue<>(8, comparator);
        for (String s : sl) {
            queue.add(s);
        }
        String[] result = new String[Math.min(k, sl.size())];
        for (int i = 0 ; i<k ; i++) {
            result[i] = queue.remove();
        }
        return result;
    }

    public static String[] getKNearstString(String a, int k, String mode, int prefix, String[] sl) {
        Comparator<String> comparator = new StringSimComparator(a, mode, prefix);
        PriorityQueue<String> queue = new PriorityQueue<>(8, comparator);
        for (String s : sl) {
            queue.add(s);
        }
        String[] result = new String[Math.min(k, sl.length)];
        for (int i = 0 ; i<k ; i++) {
            result[i] = queue.remove();
        }
        return result;
    }

}
