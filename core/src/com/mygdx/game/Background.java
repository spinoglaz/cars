package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Background {

    private Texture texture;
    private Vector2 position;
    private int speed;

    public Background(Road road) {
        texture = road.getTexture();
        position = new Vector2(0, 0);
        speed = 400;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
        batch.draw(texture, position.x + texture.getWidth(), position.y);
        batch.draw(texture, position.x, position.y + texture.getHeight());
        batch.draw(texture, position.x + texture.getWidth(), position.y + texture.getHeight());
    }

    public void update(float dt) {
        position.y -= speed * dt;
        if (position.y < -texture.getHeight()) {
            position.y = 0;
        }
        if (position.x < -texture.getWidth()) {
            position.x = 0;
        }
    }
}
