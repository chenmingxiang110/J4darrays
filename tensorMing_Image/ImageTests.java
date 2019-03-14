package tensorMing_Image;

import java.util.Arrays;

public class ImageTests {

    public static void main(String[] args) {
//        String filename = "test_image.jpg";
//        int[][][] result = ImageFileIO.readImage(filename);
//        System.out.println(result.length);
//        System.out.println(result[0].length);
//        System.out.println(result[0][0].length);
//        System.out.println(Arrays.toString(result[300][300]));
//
//        filename = "test_image_yellow.jpg";
//        int[][][] data = new int[200][200][3];
//        for (int x = 0 ; x<200 ; x++) {
//            for (int y = 0 ; y<200 ; y++) {
//                if ((x+y)>200) {
//                    data[x][y][0] = 250;
//                    data[x][y][1] = 250;
//                    data[x][y][2] = 50;
//                } else {
//                    data[x][y][0] = 100;
//                    data[x][y][1] = 100;
//                    data[x][y][2] = 100;
//                }
//            }
//        }
//
//        ImageFileIO.writeImage(filename, data);

//        double[][] result = ImageFilter.gaussianMap(5);
//        for (int i = 0 ; i<result.length ; i++) {
//            System.out.println(Arrays.toString(result[i]));
//        }

//        String filename = "test_image.jpg";
//        int[][][] result = ImageFileIO.readImage(filename);
//        int[][][] data = ImageFilter.gaussianBlur(result, 15, 3);
//        ImageFileIO.writeImage("gaussian15_3.jpg", data);
//        data = ImageFilter.gaussianBlur(result, 15, 10);
//        ImageFileIO.writeImage("gaussian15_10.jpg", data);
//        data = ImageFilter.meanBlur(result, 25);
//        ImageFileIO.writeImage("mean25.jpg", data);
//        data = ImageFilter.medianBlur(result, 25);
//        ImageFileIO.writeImage("median25.jpg", data);

//        int[][][] result = ImageFileIO.readImage("test_image.jpg");
//        long startingTime = System.currentTimeMillis();
//        int[][][] data = ImageFilter.gaussianBlur_legacy(result, 15, 10);
//        System.out.println(System.currentTimeMillis()-startingTime);
//        ImageFileIO.writeImage("gaussianBlur_legacy.jpg", data);
//
//        startingTime = System.currentTimeMillis();
//        data = ImageFilter.gaussianBlur(result, 15, 10);
//        System.out.println(System.currentTimeMillis()-startingTime);
//        ImageFileIO.writeImage("gaussianBlur.jpg", data);

//        int[][][] result = ImageFileIO.readImage("test_image.jpg");
//
//        long startingTime = System.currentTimeMillis();
//        boolean[][] edge = ImageAnalysis.simpleEdge(result, 20);
//        int[][][] edgePic = ImageDraw.bool2Pic(edge);
//        System.out.println(System.currentTimeMillis()-startingTime);
//        ImageFileIO.writeImage("edge20.jpg", edgePic);
//
//        startingTime = System.currentTimeMillis();
//        edge = ImageAnalysis.simpleEdge(result, 50);
//        edgePic = ImageDraw.bool2Pic(edge);
//        System.out.println(System.currentTimeMillis()-startingTime);
//        ImageFileIO.writeImage("edge50.jpg", edgePic);
//
//        startingTime = System.currentTimeMillis();
//        edge = ImageAnalysis.simpleEdge(result, 100);
//        edgePic = ImageDraw.bool2Pic(edge);
//        System.out.println(System.currentTimeMillis()-startingTime);
//        ImageFileIO.writeImage("edge100.jpg", edgePic);

//        int[][][] result = ImageFileIO.readImage("test_image_2.jpg");
//
//        long startingTime = System.currentTimeMillis();
//        boolean[][] edge = ImageAnalysis.soberEdge(result, 100);
//        int[][][] edgePic = ImageDraw.bool2Pic(edge);
//        System.out.println(System.currentTimeMillis()-startingTime);
//        ImageFileIO.writeImage("soberEdge100.jpg", edgePic);
//
//        startingTime = System.currentTimeMillis();
//        edge = ImageAnalysis.soberEdge(result, 200);
//        edgePic = ImageDraw.bool2Pic(edge);
//        System.out.println(System.currentTimeMillis()-startingTime);
//        ImageFileIO.writeImage("soberEdge200.jpg", edgePic);

        int[][][] result = ImageFileIO.readImage("test_image_2.jpg");

        long startingTime = System.currentTimeMillis();
        boolean[][] edge = ImageAnalysis.cannyEdge(result, 100, 1600);
        int[][][] edgePic = ImageDraw.bool2Pic(edge);
        System.out.println(System.currentTimeMillis()-startingTime);
        ImageFileIO.writeImage("cannyEdge100.jpg", edgePic);
    }

}
