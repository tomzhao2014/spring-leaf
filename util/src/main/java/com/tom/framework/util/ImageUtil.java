package com.tom.framework.util;

import com.mortennobel.imagescaling.ResampleOp;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/3/17 0017
 * Time: 19:09
 */
public class ImageUtil
{
    public static void zoom(InputStream inputStream, OutputStream outputStream, int limitWidth, int limitHeight)
    {
        try
        {
            BufferedImage sourceBufferedImage = ImageIO.read(inputStream);
            int width = sourceBufferedImage.getWidth();
            int height = sourceBufferedImage.getHeight();
            if (width > limitWidth) {
                double bl = limitWidth / width;
                width = (int)Math.round(width * bl);
                height = (int)Math.round(height * bl);
            }
            if (height > limitHeight) {
                double bl = limitHeight / height;
                width = (int)Math.round(width * bl);
                height = (int)Math.round(height * bl);
            }
            zoom(sourceBufferedImage, outputStream, Integer.valueOf(width), Integer.valueOf(height));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void zoom(InputStream inputStream, OutputStream outputStream, int zoomedSideLen)
    {
        try
        {
            BufferedImage sourceBufferedImage = ImageIO.read(inputStream);
            int width = sourceBufferedImage.getWidth();
            int height = sourceBufferedImage.getHeight();
            int maxSide = Math.max(width, height);
            if (maxSide > zoomedSideLen) {
                double bl = zoomedSideLen / maxSide;
                width = (int)Math.round(width * bl);
                height = (int)Math.round(height * bl);
            }
            zoom(sourceBufferedImage, outputStream, Integer.valueOf(width), Integer.valueOf(height));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void zoom(BufferedImage sourceBufferedImage, OutputStream outputStream, Integer limitWidth, Integer limitHeight)
    {
        try
        {
            int width = sourceBufferedImage.getWidth();
            int height = sourceBufferedImage.getHeight();
            if (limitWidth == null) {
                limitWidth = Integer.valueOf(width);
            }
            if (limitHeight == null) {
                limitHeight = Integer.valueOf(height);
            }
            ResampleOp resampleOp = new ResampleOp(limitWidth.intValue(), limitHeight.intValue());
            BufferedImage rescaledTomato = resampleOp.filter(sourceBufferedImage, null);

            ImageIO.write(rescaledTomato, "PNG", outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cut(InputStream inputStream, OutputStream outputStream, ImageArea avatar)
    {
        try {
            BufferedImage sourceBufferedImage = ImageIO.read(inputStream);

            ImageFilter cropFilter = new CropImageFilter(avatar.getX(), avatar.getY(), avatar.getWidth(), avatar.getHeight());
            Image cutedImg = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(sourceBufferedImage.getSource(), cropFilter));

            BufferedImage targetBufferedImage = new BufferedImage(avatar.getWidth(), avatar.getHeight(), 1);
            Graphics g = targetBufferedImage.getGraphics();
            g.drawImage(cutedImg, 0, 0, null);
            g.dispose();

            ImageIO.write(targetBufferedImage, "JPEG", outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
