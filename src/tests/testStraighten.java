package tests;

import ndArray.Lab;
import ndArray.NdImageIO;
import ndArray.NdImagePro;

public class testStraighten {

    public static void main(String[] args) {
        String path = "inclined.jpeg";
        int[][][] img = NdImageIO.readImage(path, "chw");
        double angle = Lab.straighten_legacy(img, "chw");
        System.out.println(angle);
        img = NdImagePro.rotate(img, angle, "chw");
        NdImageIO.writeImage("inclined_rotate.jpg", img, "chw");
    }
}
