package ndArray;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NdImagePro {

    public NdImagePro() {}

    public static BufferedImage resize(BufferedImage bi, int newWidth, int newHeight) {
        Image newImage = bi.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
        BufferedImage bimage = new BufferedImage(
                newImage.getWidth(null),
                newImage.getHeight(null),
                bi.getType()
        );

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(newImage, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    public static int[][][] resize(int[][][] _data, int newWidth, int newHeight, String mode) {
        BufferedImage bi = NdImageIO.convert(_data, mode);
        bi = resize(bi, newWidth, newHeight);
        int[][][] image = NdImageIO.convert(bi, mode);
        return image;
    }

//    public static int[][] resize(int[][] _data, int newWidth, int newHeight, String mode) {}

    public static BufferedImage rotate(BufferedImage bi, double angle, boolean padded) {
        int w = bi.getWidth();
        int h = bi.getHeight();
        double toRad = Math.toRadians(angle);
        if (padded) {
            int hPrime = (int) (w * Math.abs(Math.sin(toRad)) + h * Math.abs(Math.cos(toRad)));
            int wPrime = (int) (h * Math.abs(Math.sin(toRad)) + w * Math.abs(Math.cos(toRad)));

            BufferedImage rotated = new BufferedImage(wPrime, hPrime, bi.getType());
            Graphics2D g = rotated.createGraphics();
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, wPrime, hPrime);  // fill entire area
            g.translate(wPrime/2, hPrime/2);
            g.rotate(toRad);
            g.translate(-w/2, -h/2);
            g.drawImage(bi, 0, 0, null);
            g.dispose();  // release used resources before g is garbage-collected
            return rotated;
        } else {
            BufferedImage rotated = new BufferedImage(w, h, bi.getType());
            Graphics2D g = rotated.createGraphics();
            g.rotate(toRad, w/2, h/2);
            g.drawImage(bi, null, 0, 0);
            g.dispose();
            return rotated;
        }
    }

    public static BufferedImage rotate(BufferedImage bi, double angle) {
        return rotate(bi, angle, true);
    }

    public static int[][][] rotate(int[][][] _data, double angle, String mode) {
        BufferedImage bi = NdImageIO.convert(_data, mode);
        bi = rotate(bi, angle);
        int[][][] image = NdImageIO.convert(bi, mode);
        return image;
    }

//    public static int[][] rotate(int[][] _data, double angle) {}

}
