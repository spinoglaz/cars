package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class ReserveOfLives {

    private Texture texture;
    private Vector2 position;

    public ReserveOfLives() {
        texture = new Texture("heart.png");
        position = new Vector2(30, 30);
    }

    public void render (SpriteBatch batch, int life) {
        for (int i = 0; i < life; i++) {
            batch.draw(texture, position.x + (texture.getWidth() + 5) * i, position.y);
        }
    }
}
