package com.mygdx.game;

public class Lane {
    private int leftBound;
    private int rightBound;
    private boolean upward;

    public Lane(int leftBound, int rightBound, boolean upward) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.upward = upward;
    }

    public int getLeftBound() {
        return leftBound;
    }

    public int getRightBound() {
        return rightBound;
    }

    public boolean isUpward() {
        return upward;
    }

    public float getWidth() {
        return rightBound - leftBound;
    }
}
