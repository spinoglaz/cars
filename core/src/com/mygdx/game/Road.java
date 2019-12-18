package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Road {
    private int leftBound;
    private int rightBound;
    private Texture texture;

    public Road(int leftBound, int rightBound, Texture texture) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.texture = texture;
    }

    public int getLeftBound() {
        return leftBound;
    }

    public int getRightBound() {
        return rightBound;
    }

    public Texture getTexture() {
        return texture;
    }
}
