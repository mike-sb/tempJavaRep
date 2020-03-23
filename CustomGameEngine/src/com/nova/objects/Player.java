package com.nova.objects;

import com.nova.engine.Renderer;
import com.nova.game.Game;
import com.nova.game.GameManager;

import java.awt.event.KeyEvent;

public class Player extends GameObject {
    private float speed = 100;
    private float fallSpeed = 10;
    private float jumpSpeed = -10;
    private float fallDistance = 0;
    private int tileX, tileY;
    private float xOffset, yOffset;
    private boolean ground = false;


    public Player(float posX, float posY, int width, int height) {
        this.tag = "player";
        this.posX = posX * 16;
        this.tileX = (int) posX;
        this.posY = posY * 16;
        this.tileY = (int) posY;
        xOffset = yOffset = 0;
        this.width = width;
        this.height = height;
    }

    @Override
    public void update(Game game, GameManager gameManager, float dt) {

        //Left+right
        if (game.getInput().isKey(KeyEvent.VK_D)) {
            if (gameManager.getCollision(tileX + 1, tileY)||gameManager.getCollision(tileX + 1, tileY+(int)Math.signum((int)yOffset))) {
                if (xOffset < 0) {
                    xOffset += dt * speed;
                    if (xOffset> 0)
                    {
                        xOffset = 0;
                    }
                } else {
                    xOffset = 0;
                }
            } else {
                xOffset += dt * speed;
            }

        }


        if (game.getInput().isKey(KeyEvent.VK_A)) {
            if (gameManager.getCollision(tileX - 1, tileY)||gameManager.getCollision(tileX - 1, tileY+(int)Math.signum((int)yOffset))) {
                if (xOffset > 0) {
                    xOffset -= dt * speed;
                    if (xOffset< 0)
                    {
                        xOffset = 0;
                    }
                } else {
                    xOffset = 0;
                }
            } else {
                xOffset -= dt * speed;
            }

        }

//Jump + Gravity
        fallDistance += dt * fallSpeed;

        if (game.getInput().isKey(KeyEvent.VK_W)) {
            fallDistance = jumpSpeed;
            ground = false;
        }

        yOffset += fallDistance;

        if(fallDistance<0) {
            if ((gameManager.getCollision(tileX, tileY - 1) || gameManager.getCollision(tileX + (int) Math.signum((int) xOffset), tileY - 1)) && yOffset < 0) {
                fallDistance = 0;
                yOffset = 0;
            }
        }

        if(fallDistance>0) {
            if ((gameManager.getCollision(tileX, tileY + 1) || gameManager.getCollision(tileX + (int) Math.signum((int) xOffset), tileY + 1)) && yOffset >= 0) {
                fallDistance = 0;
                yOffset = 0;
                ground = true;
            }
        }

        //end jumping+ gravity

        //final position
        if (yOffset > GameManager.TileSize / 2) {
            tileY++;
            yOffset -= GameManager.TileSize;
        }
        if (yOffset < -GameManager.TileSize / 2) {
            tileY--;
            yOffset += GameManager.TileSize;
        }
        if (xOffset > GameManager.TileSize / 2) {
            tileX++;
            xOffset -= GameManager.TileSize;
        }
        if (xOffset < -GameManager.TileSize / 2) {
            tileX--;
            xOffset += GameManager.TileSize;
        }


        posX = tileX * GameManager.TileSize + xOffset;
        posY = tileY * GameManager.TileSize + yOffset;
    }

    @Override
    public void render(Game game, Renderer renderer) {
        renderer.drawFillRect((int) posX, (int) posY, width, height, 0xff00ff00);
    }
}
