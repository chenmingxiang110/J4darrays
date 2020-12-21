package ndArray;

/*
Lab class 的主要作用是存放一些实验中的功能。有一些功能未必有 paper 的支持，因此使用应当谨慎。
 */

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Lab {

    public Lab() {}

    public static double sharpness(int[][][] img, double T1, double T2, String mode) {
        mode = mode.toLowerCase();
        if (!( mode.equals("chw1") || mode.equals("cwh1") || mode.equals("hwc1") || mode.equals("whc1") ||
                mode.equals("chw255") || mode.equals("cwh255") || mode.equals("hwc255") || mode.equals("whc255") )) {
            throw new IllegalArgumentException("Illegal Mode. Please choose the mode from: chw1, cwh1, hwc1, whc1, " +
                    "chw255, cwh255, hwc255, whc255.");
        }

        double[][][] img_d = NdUtils.cast(img); // 改变格式到 double
        if (!mode.substring(0,1).equals("c")) {
            img_d = NdUtils.transpose(img_d, new int[]{2, 0, 1}); // 转置
        }
        if (mode.substring(3,4).equals("2")) {
            NdMath.elementwiseDivide(img_d, 255); // 归一化
        }
        return sharpness(img_d, T1, T2, "chw1");
    }

    // 推荐 T1 = 0.2, T2 = 0.1
    public static double sharpness(double[][][] img_d, double T1, double T2, String mode) {
        mode = mode.toLowerCase();
        if (!( mode.equals("chw1") || mode.equals("cwh1") || mode.equals("hwc1") || mode.equals("whc1") ||
                mode.equals("chw255") || mode.equals("cwh255") || mode.equals("hwc255") || mode.equals("whc255") )) {
            throw new IllegalArgumentException("Illegal Mode. Please choose the mode from: chw1, cwh1, hwc1, whc1, " +
                    "chw255, cwh255, hwc255, whc255.");
        }
        if (!mode.substring(0,1).equals("c")) {
            img_d = NdUtils.transpose(img_d, new int[]{2, 0, 1}); // 转置
        }
        if (mode.substring(3,4).equals("2")) {
            NdMath.elementwiseDivide(img_d, 255); // 归一化
        }

        // 使用 Laplacian 做卷积
        double[][][] lap_result = new double[][][]{
                NdMath.convolve(img_d[0], Filters.laplacian2d3_nine()),
                NdMath.convolve(img_d[1], Filters.laplacian2d3_nine()),
                NdMath.convolve(img_d[2], Filters.laplacian2d3_nine()),
        };
        NdMath.ndAbs(lap_result); // 绝对值
        double[][] lap_result_ = NdMath.reduce(lap_result); // 各 channel 的平均

        ArrayList<Double> lap_values = NdUtils.flatten(lap_result_);

        double[] thresholds = new double[]{T1, T2};
        double[] percentages = new double[2];
        for (int i = 0; i < 2; i++) {
            double percentage = 0;
            for (double d : lap_values) {
                if (d>thresholds[i]) percentage+=1;
            }
            percentage /= lap_values.size();
            percentages[i] = percentage;
        }

        return percentages[0]/percentages[1];
    }

    private static double aligness_legacy(double[][] img_) {
        ArrayList<double[][]> sobels = Filters.sobel2d(3);
        double[][] sobelx = NdMath.convolve(img_, sobels.get(0));
        NdMath.ndAbs(sobelx);
        double[][] sobely = NdMath.convolve(img_, sobels.get(1));
        NdMath.ndAbs(sobely);
        double result = 0;
        for (int i = 0; i < sobelx.length; i++) {
            for (int j = 0; j < sobelx[0].length; j++) {
                result += Math.max(sobelx[i][j], sobely[i][j]);
            }
        }
        return result;
    }

    public static double straighten_legacy(int[][][] img_, String mode) {
        int w = 0;
        int h = 0;
        switch (mode) {
            case "chw":
                w = img_[0][0].length;
                h = img_[0].length;
                break;
            case "cwh":
                w = img_[0].length;
                h = img_[0][0].length;
                break;
            case "hwc":
                w = img_[0].length;
                h = img_.length;
                break;
            case "whc":
                w = img_.length;
                h = img_[0].length;
                break;
            default:
                throw new IllegalArgumentException("Illegal Mode. Please choose the mode from: chw, cwh, hwc, whc.");
        }

        double side_length = 128;
        double ratio = 2*side_length/(w+h);
        int newWidth = Math.max(3, (int)(w*ratio)); // 因为 sobel filter 边长是 3
        int newHeight = Math.max(3, (int)(h*ratio));

        BufferedImage bi = NdImageIO.convert(img_, mode);
        bi = NdImagePro.resize(bi, newWidth, newHeight);
        int[][][] img = NdImageIO.convert(bi, "cwh");
        double[][] img_bw = NdMath.reduce(NdUtils.cast(img));

        double[] gaps = new double[]{30,10,3.3,1.1};
        double[] best_angle = new double[]{0, aligness_legacy(img_bw)};
        for (double gap : gaps) {
            double base_angle = best_angle[0];
            for (double delta : new double[]{-gap, gap}) {
                double angle = base_angle+delta;
                BufferedImage bi_ = NdImagePro.rotate(bi, angle);
                img = NdImageIO.convert(bi_, "cwh");
                img_bw = NdMath.reduce(NdUtils.cast(img));
                double _aligness = aligness_legacy(img_bw);
                if (_aligness>best_angle[1]) {
                    best_angle = new double[]{angle, _aligness};
                }
            }
        }
        return best_angle[0];
    }

}
