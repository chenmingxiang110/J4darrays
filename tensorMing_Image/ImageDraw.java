package tensorMing_Image;

public class ImageDraw {

    public static int[][][] bool2Pic(boolean[][] edges) {
        int[][][] result = new int[edges.length][edges[0].length][3];
        for (int i = 0 ; i<result.length ; i++) {
            for (int j = 0 ; j<result[0].length ; j++) {
                if (!edges[i][j]) {
                    result[i][j] = new int[]{255,255,255};
                }
            }
        }
        return result;
    }

}
