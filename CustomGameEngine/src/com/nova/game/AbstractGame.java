package com.nova.game;

import com.nova.engine.Renderer;
import com.nova.game.Game;

public  abstract  class AbstractGame {

    public abstract void init(Game game);
    public abstract void update(Game game, float dt);

    public abstract void render(Game game, Renderer renderer);
}
