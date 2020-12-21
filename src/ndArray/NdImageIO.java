package ndArray;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class NdImageIO {

    public NdImageIO() {}

    public static BufferedImage convert(int[][][] _data, String mode) {
        mode = mode.toLowerCase();
        int[][][] data;
        switch (mode) {
            case "chw":
                data = NdUtils.transpose(_data, new int[]{2,1,0});
                break;
            case "cwh":
                data = NdUtils.transpose(_data, new int[]{1,2,0});
                break;
            case "hwc":
                data = NdUtils.transpose(_data, new int[]{1,0,2});
                break;
            case "whc":
                data = _data;
            default:
                throw new IllegalArgumentException("Illegal Mode. Please choose the mode from: chw, cwh, hwc, whc.");
        }

        BufferedImage image = new BufferedImage(data.length, data[0].length, 1);

        for(int x = 0; x < data.length; ++x) {
            for(int y = 0; y < data[0].length; ++y) {
                int alpha = data[x][y].length>3?data[x][y][3]:255;
                Color c = new Color(data[x][y][0], data[x][y][1], data[x][y][2], alpha);
                int rgb = c.getRGB();
                image.setRGB(x, y, rgb);
            }
        }

        return image;
    }

    public static int[][][] convert(BufferedImage bi, String mode) {
        mode = mode.toLowerCase();
        if (mode.equals("chw") && mode.equals("cwh") && mode.equals("hwc") && mode.equals("whc")) {
            throw new IllegalArgumentException("Illegal Mode. Please choose the mode from: chw, cwh, hwc, whc.");
        }
        int[][][] result = new int[0][][];
        if (mode.equals("chw")) {
            result = new int[3][bi.getHeight()][bi.getWidth()];
        } else if (mode.equals("cwh")) {
            result = new int[3][bi.getWidth()][bi.getHeight()];
        } else if (mode.equals("hwc")) {
            result = new int[bi.getHeight()][bi.getWidth()][3];
        } else if (mode.equals("whc")) {
            result = new int[bi.getWidth()][bi.getHeight()][3];
        }

        for(int x = 0; x < bi.getWidth(); ++x) {
            for(int y = 0; y < bi.getHeight(); ++y) {
                int color = bi.getRGB(x, y);
                int blue = color & 255;
                int green = (color & '\uff00') >> 8;
                int red = (color & 16711680) >> 16;
                if (mode.equals("chw")) {
                    result[0][y][x] = red;
                    result[1][y][x] = green;
                    result[2][y][x] = blue;
                } else if (mode.equals("cwh")) {
                    result[0][x][y] = red;
                    result[1][x][y] = green;
                    result[2][x][y] = blue;
                } else if (mode.equals("hwc")) {
                    result[y][x][0] = red;
                    result[y][x][1] = green;
                    result[y][x][2] = blue;
                } else if (mode.equals("whc")) {
                    result[x][y][0] = red;
                    result[x][y][1] = green;
                    result[x][y][2] = blue;
                }

            }
        }

        return result;
    }

    public static int[][][] readImage(String path, String mode) {
        File f = new File(path);
        return readImage(f, mode);
    }

    public static int[][][] readImage(File f, String mode) {
        mode = mode.toLowerCase();
        if (mode.equals("chw") && mode.equals("cwh") && mode.equals("hwc") && mode.equals("whc")) {
            throw new IllegalArgumentException("Illegal Mode. Please choose the mode from: chw, cwh, hwc, whc.");
        }
        try {
            BufferedImage image = ImageIO.read(f);
            return convert(image, mode);
        } catch (IOException var9) {
            var9.printStackTrace();
            return (int[][][])null;
        }
    }

    public static void writeImage(String path, double[][] _data, String mode) {
        mode = mode.toLowerCase();
        if (!(mode.equals("wh") || mode.equals("hw"))) {
            throw new IllegalArgumentException("Illegal Mode. Please choose the mode from: wh, hw.");
        }
        double[][][] data = new double[][][]{_data, _data, _data};
        writeImage(path, data, "c"+mode);
    }

    public static void writeImage(File f, double[][] _data, String mode) {
        mode = mode.toLowerCase();
        if (!(mode.equals("wh") || mode.equals("hw"))) {
            throw new IllegalArgumentException("Illegal Mode. Please choose the mode from: wh, hw.");
        }
        double[][][] data = new double[][][]{_data, _data, _data};
        writeImage(f, data, "c"+mode);
    }

    public static void writeImage(String path, int[][] _data, String mode) {
        mode = mode.toLowerCase();
        if (!(mode.equals("wh") || mode.equals("hw"))) {
            throw new IllegalArgumentException("Illegal Mode. Please choose the mode from: wh, hw.");
        }
        int[][][] data = new int[][][]{_data, _data, _data};
        writeImage(path, data, "c"+mode);
    }

    public static void writeImage(File f, int[][] _data, String mode) {
        mode = mode.toLowerCase();
        if (!(mode.equals("wh") || mode.equals("hw"))) {
            throw new IllegalArgumentException("Illegal Mode. Please choose the mode from: wh, hw.");
        }
        int[][][] data = new int[][][]{_data, _data, _data};
        writeImage(f, data, "c"+mode);
    }

    public static void writeImage(String path, double[][][] _data, String mode) {
        writeImage(path, NdUtils.cast(_data),mode);
    }

    public static void writeImage(File f, double[][][] _data, String mode) {
        writeImage(f, NdUtils.cast(_data),mode);
    }

    public static void writeImage(String path, int[][][] _data, String mode) {
        File f = new File(path);
        writeImage(f, _data, mode);
    }

    public static void writeImage(File f, int[][][] _data, String mode) {
        BufferedImage image = convert(_data, mode);
        try {
            ImageIO.write(image, "jpg", f);
        } catch (IOException var7) {
            var7.printStackTrace();
        }

    }

}
