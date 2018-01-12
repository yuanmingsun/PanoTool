import utils.DirectionEnum;
import utils.PanoUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.*;

public class Main {
   public static int height=1024;
   public static double rate=3;
   public static double angle=70;

   public static class  PanoCallable implements Callable<String> {

        private String imagePath;
        private DirectionEnum dir;

        public PanoCallable(String imagePath,DirectionEnum dir)
        {
            this.imagePath=imagePath;
            this.dir=dir;
        }

        @Override
        public String call() throws Exception {
            BufferedImage image=PanoUtil.getImageFromPath(imagePath);
            BufferedImage panoImage=null;
            switch (dir)
            {
                case FRONT:
                    panoImage=PanoUtil.convertFrontImage(image,height,rate,angle);
                    ImageIO.write(panoImage, "png",new File("d://cube_front.png"));
                    break;
                case RIGHT:
                    panoImage=PanoUtil.convertRightImage(image,height,rate,angle);
                    ImageIO.write(panoImage, "png",new File("d://cube_right.png"));
                    break;
                case BACK:
                    panoImage=PanoUtil.convertBackImage(image,height,rate,angle);
                    ImageIO.write(panoImage, "png",new File("d://cube_back.png"));
                    break;
                case LEFT:
                    panoImage=PanoUtil.convertLeftImage(image,height,rate,angle);
                    ImageIO.write(panoImage, "png",new File("d://cube_left.png"));
                    break;
                case TOP:
                    panoImage=PanoUtil.convertTopImage(image,height,rate,angle);
                    ImageIO.write(panoImage, "png",new File("d://cube_top.png"));
                    break;
                case Bottom:
                    panoImage=PanoUtil.convertBottomImage(image,height,rate,angle);
                    ImageIO.write(panoImage, "png",new File("d://cube_bottom.png"));
                    break;

            }
            return dir.getName()+"全景图片生成完成";
        }
    }
    public static void savePanoImage(BufferedImage frontImage,BufferedImage rightImage,BufferedImage backImage,BufferedImage leftImage,BufferedImage topImage,BufferedImage bottomImage) throws IOException {
        BufferedImage bufferedImage=new BufferedImage((int)((rate*4+2)*height),height,BufferedImage.TYPE_INT_RGB);
        for(int i=0;i<frontImage.getWidth();i++)
        {
            for(int j=0;j<frontImage.getHeight();j++)
            {
                bufferedImage.setRGB(i,j,frontImage.getRGB(i,j));
            }
        }
        for(int i=0;i<rightImage.getWidth();i++)
        {
            for(int j=0;j<rightImage.getHeight();j++)
            {
                bufferedImage.setRGB(i+height,j,rightImage.getRGB(i,j));
            }
        }
        for(int i=0;i<backImage.getWidth();i++)
        {
            for(int j=0;j<backImage.getHeight();j++)
            {
                bufferedImage.setRGB((int)(i+height*(1+rate)),j,backImage.getRGB(i,j));
            }
        }
        for(int i=0;i<leftImage.getWidth();i++)
        {
            for(int j=0;j<leftImage.getHeight();j++)
            {
                bufferedImage.setRGB((int)(i+height*(2+rate)),j,leftImage.getRGB(i,j));
            }
        }
        for(int i=0;i<topImage.getWidth();i++)
        {
            for(int j=0;j<topImage.getHeight();j++)
            {
                bufferedImage.setRGB((int)(i+height*(2+rate*2)),j,topImage.getRGB(i,j));
            }
        }
        for(int i=0;i<bottomImage.getWidth();i++)
        {
            for(int j=0;j<bottomImage.getHeight();j++)
            {

                bufferedImage.setRGB((int)(i+height*(2+rate*3)),j,bottomImage.getRGB(i,j));
            }
        }
        ImageIO.write( bufferedImage,"png",new File("d://cube_pano3.jpg"));
    }
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newFixedThreadPool(6);

        for(DirectionEnum dirEnum :DirectionEnum.values())
        {
            Callable callable=new PanoCallable("d://sphere.jpg",dirEnum);
            FutureTask<String> futureTask=new FutureTask<>(callable);
            executorService.submit(futureTask);

        }

//        try {
//            BufferedImage image=PanoUtil.getImageFromPath("d://sphere.jpg");
//            BufferedImage frontImage=PanoUtil.convertFrontImage(image,height,rate,angle);
//            BufferedImage rightImage=PanoUtil.convertRightImage(image,height,rate,angle);
//            BufferedImage backImage=PanoUtil.convertBackImage(image,height,rate,angle);
//            BufferedImage leftImage=PanoUtil.convertLeftImage(image,height,rate,angle);
//            BufferedImage topImage=PanoUtil.convertTopImage(image,height,rate,angle);
//            BufferedImage bottomImage=PanoUtil.convertBottomImage(image,height,rate,angle);
//            ImageIO.write(frontImage, "png",new File("d://cube_front.jpg"));
//            ImageIO.write(rightImage,"png",new File("d://cube_right.jpg"));
//            ImageIO.write(backImage,"png",new File("d://cube_back.jpg"));
//            ImageIO.write(leftImage,"png",new File("d://cube_left.jpg"));
//            ImageIO.write( topImage,"png",new File("d://cube_top.jpg"));
//            ImageIO.write( bottomImage,"png",new File("d://cube_bottom.jpg"));
//
//            savePanoImage(frontImage,rightImage,backImage,leftImage,topImage,bottomImage);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        finally {
//            System.out.println("end");
//        }
    }
}
