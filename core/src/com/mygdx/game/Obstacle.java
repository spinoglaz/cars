package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public class Obstacle {
    private Texture texture;
    private Vector2 position;
    private int speed;
    Rectangle rectangle;

    public Obstacle() {
        texture = new Texture("cow.png");
        position = new Vector2(100 + (float)Math.random() * 700, 1000);
        speed = -400;
        rectangle = new Rectangle((int)position.x, (int)position.y, texture.getWidth(), texture.getHeight());
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void update(float dt) {
        position.y += speed * dt;
        rectangle.setLocation((int)position.x, (int)position.y);
    }

    public float getPositionX() {
        return position.x;
    }

    public float getPositionY() {
        return position.y;
    }

    public float getWidthTexture() {
        return texture.getWidth();
    }

    public float getHeightTexture() {
        return texture.getHeight();
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
