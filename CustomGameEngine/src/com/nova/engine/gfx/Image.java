package com.nova.engine.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Image {
    private int width, height;
    private int[]pixels;
    private boolean alpha = false;

    private int lightBlock = 0;

    public Image(String path)
    {

        BufferedImage image = null;
        try {
            image = ImageIO.read(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        width = image.getWidth();
        height = image.getHeight();
        pixels = image.getRGB(0,0,width,height,null,0, width);
    }

    public Image(int width, int height, int[] pixels) {
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int[] getPixels() {
        return pixels;
    }

    public boolean isAlpha() {
        return alpha;
    }

    public void setAlpha(boolean alpha) {
        this.alpha = alpha;
    }
    public int getLightBlock() {
        return lightBlock;
    }

    public void setLightBlock(int lightBlock) {
        this.lightBlock = lightBlock;
    }

}
