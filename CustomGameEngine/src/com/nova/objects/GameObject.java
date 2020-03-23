package com.nova.objects;

import com.nova.engine.Renderer;
import com.nova.game.Game;
import com.nova.game.GameManager;

public abstract class GameObject {

    protected float posX, posY;
    protected int width, height;
    protected String tag;
    protected boolean dead = false;
    public abstract void update(Game game, GameManager gameManager, float dt);
    public abstract void render(Game game, Renderer renderer);


    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
