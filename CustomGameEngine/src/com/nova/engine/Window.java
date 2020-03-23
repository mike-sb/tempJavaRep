package com.nova.engine;

import com.nova.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public class Window {
    private JFrame screen;
    private BufferedImage image;
    private Canvas canvas;
    private Graphics graphics;
    private BufferStrategy bufferStrategy;
    static GraphicsDevice device = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getScreenDevices()[0];


    public Window(Game game) {

        image = new BufferedImage(game.getWidth(),game.getHeight(), BufferedImage.TYPE_INT_RGB);
        canvas = new Canvas();
        canvas.setSize(new Dimension((int)(game.getWidth()*game.getScale()), (int)(game.getHeight()*game.getScale())));

        screen = new JFrame(game.getTitle());
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLayout(new BorderLayout());
        screen.add(canvas, BorderLayout.CENTER);
        screen.pack();
        screen.setLocationRelativeTo(null);
        screen.setResizable(true);
        screen.setVisible(true);
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        graphics = bufferStrategy.getDrawGraphics();

    }

    public void update()
    {
        graphics.drawImage(image,0,0,canvas.getWidth(),canvas.getHeight(), null);
        bufferStrategy.show();
    }

    public JFrame getScreen() {
        return screen;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setFullScreen(boolean isFullScreen) {
        if (isFullScreen)
        {
            device.setFullScreenWindow(screen);
        }
        else {
            device.setFullScreenWindow(null);
        }
    }
}
