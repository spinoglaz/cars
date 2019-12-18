package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public class CounterCar {

    private Texture texture;
    private Vector2 position;
    private int speed;
    Rectangle rectangle;

    public CounterCar(Texture texture) {
        this.texture = texture;
        position = new Vector2(100 + (float)Math.random() * 700, 1000);
        speed = -700;
        rectangle = new Rectangle((int)position.x, (int)position.y, texture.getWidth(), texture.getHeight());
    }

    public void render(SpriteBatch batch) {
        boolean flip = speed < 0;
        batch.draw(texture, position.x, position.y, getWidthTexture(), getHeightTexture(), 0, 0, getWidthTexture(), getHeightTexture(), false, flip);
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

    public int getWidthTexture() {
        return texture.getWidth();
    }

    public int getHeightTexture() {
        return texture.getHeight();
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
