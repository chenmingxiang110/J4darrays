package tensorMing_Evaluation;

import java.util.Arrays;

public class EvalUnitTest {

    public static void main(String[] args) {
//        float sim = StringDistance.getJaroSim("jellyfish", "smellyfish");
//        System.out.println(sim);
//        sim = StringDistance.getJaroWinklerSim("jellyfish", "smellyfish", (float)0.1);
//        System.out.println(sim);
//        sim = StringDistance.getJaroSim("jellyfish", "jellyfishee");
//        System.out.println(sim);
//        sim = StringDistance.getJaroWinklerSim("jellyfish", "jellyfishee", (float)0.1);
//        System.out.println(sim);
//        sim = StringDistance.getJaroSim("jellyfish", "jellyfish");
//        System.out.println(sim);
//        sim = StringDistance.getJaroSim("", "a");
//        System.out.println(sim);
//        sim = StringDistance.getJaroSim("", "");
//        System.out.println(sim);
//        sim = StringDistance.getLevenshteinDistance("jellyfish", "smellyfishee");
//        System.out.println(sim);
//        sim = StringDistance.getLevenshteinDistance("jellyfish", "smellyfish");
//        System.out.println(sim);
//        sim = StringDistance.getLevenshteinDistance("jellyfish", "jellyfishee");
//        System.out.println(sim);
//        sim = StringDistance.getLevenshteinDistance("", "");
//        System.out.println(sim);
//        sim = StringDistance.getLevenshteinDistance("", "a");
//        System.out.println(sim);

        /////////////////////////////////////////////////////////////////////////////

        String word = "pencelboux";
        String[] wordBank = new String[]{"ruler", "pencil", "pencilbox", "pen_box", "poncelboux", "fishing"};
        String[] rec = StringDistance.getKNearstString(word, 3, "leve", wordBank);
        System.out.println(Arrays.toString(rec));
        rec = StringDistance.getKNearstString(word, 3, "leve", 3, wordBank);
        System.out.println(Arrays.toString(rec));
    }

}
