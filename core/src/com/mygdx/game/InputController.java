package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputController {
    private Car car;

    public InputController(Car car) {
        this.car = car;
    }

    public void moveCar(float dt) {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && car.getPositionX() > 0) {
            car.setPosition(car.getPositionX() - 300 * dt);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && car.getPositionX() < 650) {
            car.setPosition(car.getPositionX() + 300 * dt);
        }
    }
}
