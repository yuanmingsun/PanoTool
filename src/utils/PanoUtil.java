package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * sphere图片来生成立方体的全景图
 */
public class PanoUtil {


    public static BufferedImage getImageFromPath(String path) throws IOException {
        File file = new File(path);
        return ImageIO.read(file);
    }


    /**
     * image 传入的原始图片
     * width 宽度
     * rate 长宽比
     * heading 长方体的朝向
     */
    public static BufferedImage convertFrontImage(BufferedImage image, int height, double rate, double angle) {
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();
        int tall = (int) Math.round(height * rate);
        BufferedImage frontImage = new BufferedImage(height, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < height; y++) {
                double heading = Math.atan((x - height / 2.0) / (tall / 2.0));
                double length = Math.sqrt((tall / 2.0) * (tall / 2.0) + (x - height / 2.0) * (x - height / 2.0));
                double pitch = Math.PI / 2 + Math.atan((y - height / 2.0) / length);

                heading += angle;
                if (heading < 0) {
                    heading += 2 * Math.PI;
                }
                if (heading > 2 * Math.PI) {
                    heading -= 2 * Math.PI;
                }
                int sourceX = (int) Math.round(sourceWidth * heading / (Math.PI * 2));
                int sourceY = (int) Math.round(sourceHeight * pitch / Math.PI);
                //System.out.println("sourceX:"+sourceX+"--sourceY:"+sourceY);
                int pixel = ImageUtil.getPixel(image, sourceX, sourceY);
                ImageUtil.setPixel(frontImage, x, y, pixel);
            }
        }
        return frontImage;

    }

    public static BufferedImage convertRightImage(BufferedImage image, int height, double rate, double angle) {
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();
        int width = (int) Math.round(height * rate);
        BufferedImage rightImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double heading = Math.PI / 2 + Math.atan((x - (width / 2.0)) / (height / 2.0));
                double length = Math.sqrt((height / 2.0) * (height / 2.0) + (x - width / 2.0) * (x - width / 2.0));
                double pitch = Math.PI / 2 + Math.atan((y - height / 2.0) / length);
                heading += angle;
                if (heading > 2 * Math.PI) {
                    heading -= 2 * Math.PI;
                }
                int sourceX = (int) Math.round(sourceWidth * heading / (Math.PI * 2));
                int sourceY = (int) Math.round(sourceHeight * pitch / Math.PI);
                int pixel = ImageUtil.getPixel(image, sourceX, sourceY);
                ImageUtil.setPixel(rightImage, x, y, pixel);
            }
        }
        return rightImage;
    }

    public static BufferedImage convertBackImage(BufferedImage image, int height, double rate, double angle) {
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();
        int tall = (int) Math.round(height * rate);
        BufferedImage backImage = new BufferedImage(height, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < height; y++) {
                double heading = Math.atan((x - height / 2.0) / (tall / 2.0)) + Math.PI;
                double length = Math.sqrt((tall / 2.0) * (tall / 2.0) + (x - height / 2.0) * (x - height / 2.0));
                double pitch = Math.PI / 2 + Math.atan((y - height / 2.0) / length);
                heading += angle;
                if (heading < 0) {
                    heading += 2 * Math.PI;
                }
                if (heading > 2 * Math.PI) {
                    heading -= 2 * Math.PI;
                }
                int sourceX = (int) Math.round(sourceWidth * heading / (Math.PI * 2));
                int sourceY = (int) Math.round(sourceHeight * pitch / Math.PI);
                int pixel = ImageUtil.getPixel(image, sourceX, sourceY);
                ImageUtil.setPixel(backImage, x, y, pixel);
            }
        }
        return backImage;
    }


    public static BufferedImage convertLeftImage(BufferedImage image, int height, double rate, double angle) {
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();
        int width = (int) Math.round(height * rate);
        BufferedImage leftImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double heading = Math.PI * 3 / 2 + Math.atan((x - (width / 2.0)) / (height / 2.0));
                double length = Math.sqrt((height / 2.0) * (height / 2.0) + (x - width / 2.0) * (x - width / 2.0));
                double pitch = Math.PI / 2 + Math.atan((y - height / 2.0) / length);
                heading += angle;
                if (heading >= 2 * Math.PI) {
                    heading -= 2 * Math.PI;
                }

                int sourceX = (int) Math.round(sourceWidth * heading / (Math.PI * 2));
                int sourceY = (int) Math.round(sourceHeight * pitch / Math.PI);

                int pixel = ImageUtil.getPixel(image, sourceX, sourceY);
                ImageUtil.setPixel(leftImage, x, y, pixel);
            }
        }
        return leftImage;
    }


    public static BufferedImage convertTopImage(BufferedImage image, int height, double rate, double angle) {
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();
        int width = (int) Math.round(height * rate);
        BufferedImage topImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double x1 = (x - width / 2.0);
                double y1 = (y - height / 2.0);
                double heading = Math.atan(Math.abs(y1) / Math.abs(x1));
                if (y1 < 0 && x1 < 0) {

                    heading = Math.PI * 2 - heading;
                } else if (y1 >= 0 && x1 < 0) {

                    heading = heading;
                } else if (y1 >= 0 && x1 >= 0) {

                    heading = Math.PI - heading;
                } else if (y1 < 0 && x1 >= 0) {
                    heading = Math.PI + heading;
                }

                if (heading >= 2 * Math.PI) {
                    heading -= 2 * Math.PI;
                }
                double r = Math.sqrt(x1 * x1 + y1 * y1);
                double pitch = Math.atan((height / 2) / r);

                pitch = Math.PI / 2 - pitch;

                int sourceX = (int) Math.round(sourceWidth * heading / (Math.PI * 2));
                int sourceY = (int) Math.round(sourceHeight * pitch / Math.PI);

                int pixel = ImageUtil.getPixel(image, sourceX, sourceY);
                ImageUtil.setPixel(topImage, x, y, pixel);
            }
        }
        return topImage;
    }


    public static BufferedImage convertBottomImage(BufferedImage image, int height, double rate, double angle) {
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();
        int width = (int) Math.round(height * rate);
        BufferedImage bottomImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double x1 = (x - width / 2.0);
                double y1 = (y - height / 2.0);
                double heading = Math.atan(Math.abs(y1) / Math.abs(x1));
                if (y1 < 0 && x1 < 0) {
                    heading = Math.PI * 2 - heading;
                } else if (y1 >= 0 && x1 < 0) {

                    heading = heading;
                } else if (y1 >= 0 && x1 >= 0) {

                    heading = Math.PI - heading;
                } else if (y1 < 0 && x1 >= 0) {
                    heading = Math.PI + heading;
                }
                if (heading >= 2 * Math.PI) {
                    heading -= 2 * Math.PI;
                }
                double r = Math.sqrt(x1 * x1 + y1 * y1);
                double pitch = Math.atan((height / 2) / r);
                pitch = Math.PI / 2 + pitch;
                int sourceX = (int) Math.round(sourceWidth * heading / (Math.PI * 2));
                int sourceY = (int) Math.round(sourceHeight * pitch / Math.PI);
                int pixel = ImageUtil.getPixel(image, sourceX, sourceY);
                ImageUtil.setPixel(bottomImage, x, y, pixel);
            }
        }
        return bottomImage;
    }


}
