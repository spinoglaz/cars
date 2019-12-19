package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public class BotCar {

    private Texture texture;
    private Vector2 position;
    private int speed;
    Rectangle rectangle;
    Lane lane;

    public BotCar(Texture texture, int speed, Lane lane, float positionY) {
        this.texture = texture;
        float positionX = lane.getLeftBound() + (float)Math.random() * (lane.getWidth() - texture.getWidth());
        position = new Vector2(positionX, positionY);
        this.speed = speed;
        rectangle = new Rectangle((int)position.x, (int)position.y, texture.getWidth(), texture.getHeight());
        this.lane = lane;
    }

    public void render(SpriteBatch batch) {
        boolean flip = !lane.isUpward();
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
