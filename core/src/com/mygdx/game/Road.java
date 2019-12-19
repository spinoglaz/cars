package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Road {
    private int leftBound;
    private int rightBound;
    private Texture texture;
    private Lane[] lanes;

    public Road(int leftBound, int rightBound, Texture texture, Lane[] lanes) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.texture = texture;
        this.lanes = lanes;
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

    public Lane[] getLanes() {
        return lanes;
    }
}
