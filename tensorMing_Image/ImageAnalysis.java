package tensorMing_Image;

public class ImageAnalysis {

    private static int intArraySum(int[] input) {
        int result = 0;
        for (int i : input) {
            result+=i;
        }
        return result;
    }

    public static boolean[][] simpleEdge(int[][][] image, int threshold) {
        boolean[][] result = new boolean[image.length][image[0].length];
        for (int x = 0 ; x<image.length ; x++) {
            for (int y = 0 ; y<image[0].length ; y++) {
                double sum = 0;
                double num = 0;
                if (x>0) {
                    sum+=intArraySum(image[x][y])-intArraySum(image[x-1][y]);
                    num++;
                }
                if (x<(image.length-1)) {
                    sum+=intArraySum(image[x][y])-intArraySum(image[x+1][y]);
                    num++;
                }
                if (y>0) {
                    sum+=intArraySum(image[x][y])-intArraySum(image[x][y-1]);
                    num++;
                }
                if (y<(image[0].length-1)) {
                    sum+=intArraySum(image[x][y])-intArraySum(image[x][y+1]);
                    num++;
                }
                if ((sum/num)>=threshold) {
                    result[x][y] = true;
                }
            }
        }
        return result;
    }

    public static boolean[][] soberEdge(int[][][] image, double threshold) {
        double[][][] soberResult = ImageFilter.sober(image);
        double[][] gradDist = soberResult[2];
        boolean[][] result = new boolean[gradDist.length][gradDist[0].length];

        for (int x = 0 ; x<gradDist.length ; x++) {
            for (int y = 0 ; y<gradDist[0].length ; y++) {
                if (gradDist[x][y]>=threshold) {
                    result[x][y] = true;
                }
            }
        }
        return result;
    }

    public static boolean[][] cannyEdge(int[][][] image, double lowerBound, double upperBound) {
        int[][][] gaussian = ImageFilter.gaussianBlur(image, 5, 1.4f);
        double[][][] soberResult = ImageFilter.sober(gaussian);
        double[][] suppressionResult = ImageFilter.nonMaximumSuppression(soberResult);

        boolean[][] result = new boolean[suppressionResult.length][suppressionResult[0].length];

        for (int x = 0 ; x<suppressionResult.length ; x++) {
            for (int y = 0 ; y<suppressionResult[0].length ; y++) {
                if (suppressionResult[x][y]>=lowerBound && suppressionResult[x][y]<=upperBound) {
                    result[x][y] = true;
                }
            }
        }
        return result;
    }

}
