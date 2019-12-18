package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputController {
    private Car car;
    private Road road;

    public InputController(Car car, Road road) {
        this.car = car;
        this.road = road;
    }

    public void moveCar(float dt) {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            float newPosition = car.getPositionX();
            newPosition -= 400 * dt;
            newPosition = Math.max(newPosition, road.getLeftBound());
            car.setPosition(newPosition);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            float newPosition = car.getPositionX();
            newPosition += 400 * dt;
            newPosition = Math.min(newPosition, road.getRightBound() - car.getWidthTexture());
            car.setPosition(newPosition);
        }
    }
}
