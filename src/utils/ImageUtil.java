package utils;

import java.awt.image.BufferedImage;

public class ImageUtil {
    /**
     * 取除图片某个像素点的颜色值
     * @param image 原始图片
     * @param x  x方向像素
     * @param y  y方向像素
     * @return   返回int类型的颜色值
     */
    public static int getPixel(BufferedImage image,int x,int y)
    {
        if(x<0)
        {
            x=0;
        }
        if(y<0)
        {
            y=0;
        }
        if(x>=image.getWidth())
        {
            x=image.getWidth()-1;
        }
        if(y>=image.getHeight())
        {
            y=image.getHeight()-1;
        }
        int imageWidth=image.getWidth();
        int imageHeight=image.getHeight();
        if(x>imageWidth)
        {
            x=imageWidth;
        }
        if(y>imageHeight)
        {
            y=imageHeight;
        }
        return image.getRGB(x,y);

    }

    /**
     * 设置某个像素点坐标值
     * @param image
     * @param x
     * @param y
     * @param color
     */
    public static void setPixel(BufferedImage image,int x,int y,int color)
    {
        image.setRGB(x,y,color);
    }
    public static double angleToRadian(double angle)
    {
        return angle*Math.PI/180;
    }
    public static double angleBetweenVector(double x1,double y1,double x2,double y2) {
        double downpart = (Math.sqrt(x1 * x1 + y1 * y1) * Math.sqrt(x2 * x2 + y2 * y2));
        if(downpart == 0) {
            return 0;
        }
        double a = Math.acos((x1 * x2 + y1 * y2) / downpart);
        return a;
    }
}
