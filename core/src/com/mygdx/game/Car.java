package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public class Car {

    private Texture texture;
    private Vector2 position;
    Rectangle rectangle;

    public Car() {
        texture = new Texture("car.png");
        position = new Vector2(700, 0);
        rectangle = new Rectangle((int)position.x, (int)position.y, texture.getWidth(), texture.getHeight());
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
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

    public void setPosition(float updatePosition) {
        position.x = updatePosition;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
