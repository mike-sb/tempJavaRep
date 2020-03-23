package com.nova.game;

import com.nova.engine.Input;
import com.nova.engine.Renderer;
import com.nova.engine.Window;

public class Game implements Runnable {

    private Thread thread;
    private com.nova.engine.Window window;
    private Input input;
private AbstractGame abstractGame;

    private boolean running = false;
    private final double UPDATE_TICK = 1.0 / 60.0;
    private int width , height;
private float scale = 3;
private String title = "";
    private Renderer renderer;

    double frameTime = 0;
    int frames = 0;
    int fps = 0;

    public Game(AbstractGame game) {
     //   Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
        width = 800;//(int) sSize.getWidth();
        height = 500;//(int) sSize.getHeight();
        abstractGame = game;
    }

    public void start() {

        window = new com.nova.engine.Window(this);
        window.setFullScreen(false);//
        input = new Input(this);
        renderer = new Renderer(this);
        thread = new Thread(this);
        thread.run();
    }

    public void stop() {
        running = false;

    }

    @Override
    public void run() {
        running = true;
        boolean render = false;
        double fTime = 0;
        double lTime = System.nanoTime() / 1000000000.0;
        double passedTime = 0;
        double unprocessedTime = 0;
abstractGame.init(this);
        while (running) {
            render = false;
            fTime = System.nanoTime() / 1000000000.0;
            frameTime +=passedTime;
            passedTime = fTime - lTime;
            lTime = fTime;
            unprocessedTime += passedTime;
            while (unprocessedTime >= UPDATE_TICK) {
                unprocessedTime -= UPDATE_TICK;
                render = true;
                //TODO: Update game
                abstractGame.update(this, (float) UPDATE_TICK);

                input.update();


                if (frameTime>=1.0)
                {
                    frameTime = 0;
                    fps = frames;
                    frames= 0;
                }
            }

            if (render) {
                renderer.clear();
                abstractGame.render(this, renderer);
                renderer.process();
                renderer.drawText("FPS: "+fps,0,0,0xff00ffff);
                window.update();
                frames++;
            } else {

            }
        }
        stop();
    }

    private void dispose() {

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

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public com.nova.engine.Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public Input getInput() {
        return input;
    }

    public Renderer getRenderer() {
        return renderer;
    }
}


