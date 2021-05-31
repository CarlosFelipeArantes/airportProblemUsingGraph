package com.carlosarantes.grafos.config;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageConfig {

    private BufferedImage bufferedImage;
    private Graphics graphics;
    private int height, width;

    public ImageConfig() throws IOException {
        bufferedImage = ImageIO.read(new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Mercator_projection_Square.JPG/1200px-Mercator_projection_Square.JPG"));
        this.height = bufferedImage.getHeight();
        this.width = bufferedImage.getWidth();
        graphics = bufferedImage.getGraphics();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public Graphics getGraphics() {
        return graphics;
    }
}
