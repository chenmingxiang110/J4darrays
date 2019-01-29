package tensorMing_Evaluation;

import java.util.Comparator;

public class StringSimComparator implements Comparator<String> {

    private String mode;
    private String obj;
    private int nPrefix;

    public StringSimComparator(String word) {
        mode = "leve";
        obj = word;
        nPrefix = 0;
    }

    // mode can be leve, dame, jaro, wink
    public StringSimComparator(String word, String mode) {
        if (mode != "leve" && mode != "dame" && mode != "jaro" && mode != "wink") {
            throw new IllegalArgumentException("Invalid mode. Mode can be leve, dame, jaro, wink");
        }
        this.mode = mode;
        obj = word;
        nPrefix = 0;
    }

    public StringSimComparator(String word, String mode, int nPrefix) {
        if (mode != "leve" && mode != "dame" && mode != "jaro" && mode != "wink") {
            throw new IllegalArgumentException("Invalid mode. Mode can be leve, dame, jaro, wink");
        }
        this.mode = mode;
        obj = word;
        this.nPrefix = Math.min(nPrefix, word.length());
    }

    private float getSim(String a) {
        switch(mode){
            case "leve" :
                return StringDistance.getLevenshteinSim(obj, a);
            case "dame" :
                return StringDistance.getDamerauLevenshteinSim(obj, a);
            case "jaro" :
                return StringDistance.getJaroSim(obj, a);
            case "wink" :
                return StringDistance.getJaroWinklerSim(obj, a);
            default:
                return StringDistance.getLevenshteinSim(obj, a);
        }
    }

    @Override
    public int compare(String a, String b) {
        float sim_a;
        float sim_b;
        if (nPrefix>0) {
            if (a.length()>nPrefix && a.substring(0,nPrefix).equals(obj.substring(0,nPrefix))) {
                sim_a = getSim(a);
            } else {
                sim_a = -1;
            }
            if (b.length()>nPrefix && b.substring(0,nPrefix).equals(obj.substring(0,nPrefix))) {
                sim_b = getSim(b);
            } else {
                sim_b = -1;
            }
        } else {
            sim_a = getSim(a);
            sim_b = getSim(b);
        }
        if (sim_a>sim_b) return -1;
        if (sim_a<sim_b) return 1;
        return 0;
    }
}
