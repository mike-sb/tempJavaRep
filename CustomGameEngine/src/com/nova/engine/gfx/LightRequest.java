package com.nova.engine.gfx;

public class LightRequest {
    private Light light;
    private int locX, locY;

    public LightRequest(Light light, int locX, int locY) {
        this.light = light;
        this.locX = locX;
        this.locY = locY;
    }

    public Light getLight() {
        return light;
    }

    public void setLight(Light light) {
        this.light = light;
    }

    public int getLocX() {
        return locX;
    }

    public void setLocX(int locX) {
        this.locX = locX;
    }

    public int getLocY() {
        return locY;
    }

    public void setLocY(int locY) {
        this.locY = locY;
    }
}
