package tensorMing_Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFileIO {

    public static int[][][] readImage(String path) {
        File f = new File(path);
        return readImage(f);
    }

    public static int[][][] readImage(File f) {
        try {
            BufferedImage image = ImageIO.read(f);
            int[][][] result = new int[image.getWidth()][image.getHeight()][3];

            for (int x = 0 ; x<image.getWidth() ; x++) {
                for (int y = 0 ; y<image.getHeight() ; y++) {
                    int color = image.getRGB(x, y);
                    int blue = color & 0xff;
                    int green = (color & 0xff00) >> 8;
                    int red = (color & 0xff0000) >> 16;
                    result[x][y][0] = red;
                    result[x][y][1] = green;
                    result[x][y][2] = blue;
                }
            }

            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeImage(String path, int[][][] data) {
        File f = new File(path);
        writeImage(f, data);
    }

    public static void writeImage(File f, int[][][] data) {
        BufferedImage image = new BufferedImage(data.length, data[0].length, BufferedImage.TYPE_INT_RGB);
        for (int x = 0 ; x<data.length ; x++) {
            for (int y = 0 ; y<data[0].length ; y++) {
                Color c = new Color(data[x][y][0], data[x][y][1], data[x][y][2]); // Color white
                int rgb = c.getRGB();
                image.setRGB(x, y, rgb);
            }
        }

        try {
            ImageIO.write(image, "jpg", f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
