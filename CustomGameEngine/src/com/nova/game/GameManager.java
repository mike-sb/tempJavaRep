package com.nova.game;

import com.nova.engine.Renderer;
import com.nova.engine.gfx.Image;
import com.nova.engine.gfx.ImageTile;
import com.nova.engine.gfx.Light;
import com.nova.engine.sounds.SoundClip;
import com.nova.objects.GameObject;
import com.nova.objects.Player;

import java.util.ArrayList;

public class GameManager extends AbstractGame {

    public static final int TileSize = 16;

    private boolean[] collisions;
    private int levelW, levelH;
    private ArrayList<GameObject> objects = new ArrayList<>();

    private SoundClip sound;
    private Light light;


    public GameManager() {
        objects.add(new Player(2, 2, 16, 16));

        //sound = new SoundClip("res/audio/....wav");
    }

    @Override
    public void init(Game game) {
        game.getRenderer().setAmbientColor(-1);
    }

    @Override
    public void update(Game game, float dt) {
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).update(game, this,dt);
            if (objects.get(i).isDead()) {
                objects.remove(i);
                i--;
            }
        }

    }

    @Override
    public void render(Game game, Renderer renderer) {

        int xOffset = (int) (game.getInput().getMouseX() - game.getWindow().getScreen().getBounds().getX());
        int yOffset = (int) (game.getInput().getMouseY() - game.getWindow().getScreen().getBounds().getY());
        for (GameObject go : objects) {
            go.render(game, renderer);
        }


    }

    public void loadLevel(String path) {
        Image levelImage = new Image(path);

        levelW = levelImage.getWidth();
        levelH = levelImage.getHeight();

        collisions = new boolean[levelW * levelH];

        for (int y = 0; y < levelImage.getHeight(); y++) {
            for (int x = 0; x < levelImage.getWidth(); x++) {
                if (levelImage.getPixels()[x + y * levelW] == 0xff000000) {
                    collisions[x + y * levelW] = true;
                } else {
                    collisions[x + y * levelW] = false;
                }
            }

        }
    }

    public boolean getCollision(int x, int y)
    {
        if (x<0 ||x >=levelW||y<0||y>=levelH)
            return true;
        return collisions[x+y*levelW];
    }

    public static void main(String[] args) {
        Game game = new Game(new GameManager());
        game.setWidth(320);
        game.setHeight(240);
        game.setScale(3f);
        game.start();


    }
}
