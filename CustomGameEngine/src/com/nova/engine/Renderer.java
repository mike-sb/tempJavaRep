package com.nova.engine;

import com.nova.engine.gfx.*;
import com.nova.game.Game;

import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Comparator;

public class Renderer {
    private int pWidth, pHeight;
    private int[] pixels;
    private int[] zBuffer;
    private int[] lightMap;
    private int[] lightBlock;


    private int ambientColor = 0xff232323;
    private int zDepth = 0;
    private boolean processing = false;


    private  ArrayList<LightRequest> lightRequests = new ArrayList<>();
    private ArrayList<ImageRequest> imageRequests = new ArrayList<>();

    private Font font = Font.STANDARD;

    public Renderer(Game game) {
        pWidth = game.getWidth();
        pHeight = game.getHeight();
        pixels = ((DataBufferInt) game.getWindow().getImage().getRaster().getDataBuffer()).getData();
        zBuffer = new int[pixels.length];
        lightBlock = new int[pixels.length];
        lightMap = new int[pixels.length];
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
            zBuffer[i] = 0;
            lightMap[i] = ambientColor;
            lightBlock[i] = 0;
        }
    }

    public void process() {
        processing = true;
        imageRequests.sort(new Comparator<ImageRequest>() {
            @Override
            public int compare(ImageRequest o1, ImageRequest o2) {
                if (o1.zDepth > o2.zDepth)
                    return 1;
                if (o1.zDepth < o2.zDepth)
                    return -1;
                return 0;
            }
        });

        for (int i = 0; i < imageRequests.size(); i++) {
            ImageRequest ir = imageRequests.get(i);
            setzDepth(ir.zDepth);
            drawImage(ir.image, ir.xOffset, ir.yOffset);
        }

        for (int i = 0; i < lightRequests.size(); i++) {
            LightRequest lightRequest = lightRequests.get(i);
            this.drawLightRequest(lightRequest.getLight(), lightRequest.getLocX(), lightRequest.getLocY());
        }

        for (int i = 0; i < pixels.length; i++) {
            float r = ((lightMap[i] >> 16) & 0xff) / 255f;
            float g = ((lightMap[i] >> 8) & 0xff) / 255f;
            float b = (lightMap[i] & 0xff) / 255f;
            pixels[i] = ((int) (((pixels[i] >> 16) & 0xff) * r) << 16 | (int) (((pixels[i] >> 8) & 0xff) * g) << 8 | (int) ((pixels[i] & 0xff) * b));
        }
        imageRequests.clear();
        lightRequests.clear();
        processing = false;
    }


    public void setLightMap(int x, int y, int value) {
        if ((x < 0 || x > pWidth - 1 || y < 0 || y > pHeight - 1)) {
            return;
        }
        int index = x + y * pWidth;
        int baseColor = lightMap[index];

        int maxRed = Math.max((baseColor >> 16) & 0xff, (value >> 16) & 0xff);
        int maxGreen = Math.max((baseColor >> 8) & 0xff, (value >> 8) & 0xff);
        int maxBlue = Math.max(baseColor & 0xff, value & 0xff);

        lightMap[index] = (maxRed << 16 | maxGreen << 8 | maxBlue);

    }
    public void setLightBlock(int x, int y, int value) {
        if ((x < 0 || x > pWidth - 1 || y < 0 || y > pHeight - 1)) {
            return;
        }
        int index = x + y * pWidth;

        if (zBuffer[index] > zDepth) return;

        lightBlock[index] =value;

    }

    public void drawLight(Light light, int xOffset, int yOffset)
    {
        lightRequests.add(new LightRequest(light,xOffset,yOffset));
    }

    private void drawLightRequest(Light light, int xOffset, int yOffset) {
        for (int i = 0; i < light.getDiameter(); i++) {
            drawLightLine(light, light.getRadius(), light.getRadius(), i, 0, xOffset, yOffset);
            drawLightLine(light, light.getRadius(), light.getRadius(), i, light.getDiameter(),xOffset, yOffset);
            drawLightLine(light, light.getRadius(), light.getRadius(), 0, i ,xOffset, yOffset);
            drawLightLine(light, light.getRadius(), light.getRadius(), light.getDiameter(), i, xOffset, yOffset);

        }
    }

    public void drawLightLine(Light light, int x0, int y0, int x1, int y1, int xOffset, int yOffset) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;

        int err = dx - dy;
        int err2;
        while (true) {
            int screenX = x0 - light.getRadius() + xOffset;
            int screenY =y0 - light.getRadius() + yOffset;
            if (screenX<0||screenX>=pWidth||screenY<0||screenY>=pHeight)
                return;

            int lightColor = light.getLight(x0,y0);
            if (lightColor==0)
                return;

            if (lightBlock[screenX+screenY*pWidth]==Light.FULL)
                return;

            setLightMap(screenX, screenY,lightColor);

            if (x0 == x1 && y0 == y1)
                break;

            err2 = 2 * err;
            if (err2 > -1 * dy) {
                err -= dy;
                x0 += sx;
            }

            if (err2 < dx) {
                err += dx;
                y0 += sy;
            }
        }
    }

    public void setPixels(int x, int y, int value) {
        int alpha = ((value >> 24) & 0xff);

        if ((x < 0 || x > pWidth - 1 || y < 0 || y > pHeight - 1) || alpha == 0) {
            return;
        }
        int index = x + y * pWidth;

        if (zBuffer[index] > zDepth) return;


        zBuffer[index] = zDepth;

        if (alpha == 255) {
            pixels[index] = value;
        } else {
            int pixel = pixels[index];
            int newRed = ((pixel >> 16) & 0xff) - (int) (((pixel >> 16) & 0xff - (value >> 16) & 0xff) * (alpha / 255f));//red
            int newGreen = ((pixel >> 8) & 0xff) - (int) (((pixel >> 8) & 0xff - (value >> 8) & 0xff) * (alpha / 255f));//green
            int newBlue = ((pixel) & 0xff) - (int) (((pixel) & 0xff) - (value & 0xff) * (alpha / 255f));//blue

            pixels[index] = (newRed << 16 | newGreen << 8 | newBlue);
        }

    }

    public void drawText(String text, int xOffset, int yOffset, int color) {
        text = text.toUpperCase();
        int offset = 0;
        for (int i = 0; i < text.length(); i++) {
            int unicode = text.codePointAt(i) - 32;

            for (int y = 0; y < font.getFontImage().getHeight(); y++) {
                for (int x = 0; x < font.getWidths()[unicode]; x++) {
                    if (font.getFontImage().getPixels()[(x + font.getOffsets()[unicode]) + y * font.getFontImage().getWidth()] == 0xffffffff) {
                        setPixels(x + xOffset + offset, y + yOffset, color);
                    }
                }
            }
            offset += font.getWidths()[unicode];
        }
    }

    public void drawImage(Image image, int xOffset, int yOffset) {
        if (image.isAlpha() && processing) {
            imageRequests.add(new ImageRequest(image, zDepth, xOffset, yOffset));
        }

        if (xOffset < -image.getWidth())
            return;
        if (yOffset < -image.getHeight())
            return;
        if (xOffset >= pWidth)
            return;
        if (yOffset >= pHeight)
            return;
        int newX = 0, newY = 0,
                newWidth = image.getWidth(),
                newHeight = image.getHeight();

        if (xOffset < 0) {
            newX -= xOffset;
        }
        if (yOffset < 0) {
            newY -= yOffset;
        }

        if (newWidth + xOffset > pWidth) {
            newWidth -= newWidth + xOffset - pWidth;
        }
        if (newHeight + yOffset > pHeight) {
            newHeight -= newHeight + yOffset - pHeight;
        }

        for (int y = newY; y < newHeight; y++) {
            for (int x = newX; x < newWidth; x++) {
                setPixels(x + xOffset, y + yOffset, image.getPixels()[x + y * image.getWidth()]);
                setLightBlock(x+xOffset, y+yOffset,image.getLightBlock());
            }
        }

    }

    public void drawImageTile(ImageTile image, int xOffset, int yOffset, int tileX, int tileY) {
        if (image.isAlpha() && processing) {
            imageRequests.add(new ImageRequest(image.getTileImage(tileX, tileY), zDepth, xOffset, yOffset));
        }
        if (xOffset < -image.getTileW())
            return;
        if (yOffset < -image.getTileH())
            return;
        if (xOffset >= pWidth)
            return;
        if (yOffset >= pHeight)
            return;

        int newX = 0, newY = 0,
                newWidth = image.getTileW(),
                newHeight = image.getTileH();

        if (xOffset < 0) {
            newX -= xOffset;
        }
        if (yOffset < 0) {
            newY -= yOffset;
        }

        if (newWidth + xOffset > pWidth) {
            newWidth -= newWidth + xOffset - pWidth;
        }
        if (newHeight + yOffset > pHeight) {
            newHeight -= newHeight + yOffset - pHeight;
        }

        for (int y = newY; y < newHeight; y++) {
            for (int x = newX; x < newWidth; x++) {
                setPixels(x + xOffset, y + yOffset, image.getPixels()[(x + tileX + image.getTileW()) + (y + tileY + image.getTileH()) * image.getWidth()]);
                 setLightBlock(x+xOffset, y+yOffset,image.getLightBlock());
            }
        }

    }

    public void drawRect(int xOffset, int yOffset, int width, int height, int color) {
        if (xOffset < -width)
            return;
        if (yOffset < -height)
            return;
        if (xOffset >= pWidth)
            return;
        if (yOffset >= pHeight)
            return;

        int newX = 0, newY = 0,
                newWidth = width,
                newHeight = height;

        if (xOffset < 0) {
            newX -= xOffset;
        }
        if (yOffset < 0) {
            newY -= yOffset;
        }

        if (newWidth + xOffset > pWidth) {
            newWidth -= newWidth + xOffset - pWidth;
        }
        if (newHeight + yOffset > pHeight) {
            newHeight -= newHeight + yOffset - pHeight;
        }


        for (int y = newY; y <= newHeight; y++) {
            setPixels(xOffset, y + yOffset, color);
            setPixels(xOffset + newWidth, y + yOffset, color);
        }
        for (int x = newX; x <= newWidth; x++) {
            setPixels(x + xOffset, yOffset, color);
            setPixels(x + xOffset, newHeight + yOffset, color);
        }
    }

    public void drawFillRect(int xOffset, int yOffset, int width, int height, int color) {

        if (xOffset < -width) return;
        if (yOffset < -height) return;
        if (xOffset >= pWidth) return;
        if (yOffset >= pHeight) return;

        int newX = 0, newY = 0, newWidth = width, newHeight = height;
        if (xOffset < 0) newX -= xOffset;
        if (yOffset < 0) newY -= yOffset;

        if (newWidth + xOffset > pWidth) newWidth -= newWidth + xOffset - pWidth;
        if (newHeight + yOffset > pHeight) newHeight -= newHeight + yOffset - pHeight;

        for (int y = newY; y <= newHeight; y++) {
            for (int x = newX; x <= newWidth; x++) {
                setPixels(x + xOffset, y + yOffset, color);
            }
        }

    }

    public int[] getzBuffer() {
        return zBuffer;
    }

    public void setzBuffer(int[] zBuffer) {
        this.zBuffer = zBuffer;
    }

    public int getzDepth() {
        return zDepth;
    }

    public void setzDepth(int zDepth) {
        this.zDepth = zDepth;
    }

    public int[] getLightMap() {
        return lightMap;
    }


    public int[] getLightBlock() {
        return lightBlock;
    }

    public void setLightBlock(int[] lightBlock) {
        this.lightBlock = lightBlock;
    }

    public int getAmbientColor() {
        return ambientColor;
    }

    public void setAmbientColor(int ambientColor) {
        this.ambientColor = ambientColor;
    }

    public boolean isProcessing() {
        return processing;
    }

    public void setProcessing(boolean processing) {
        this.processing = processing;
    }
}
