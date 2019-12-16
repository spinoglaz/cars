package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputController {
    private Car car;

    public InputController(Car car) {
        this.car = car;
    }

    public void moveCar() {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && car.getPositionX() > 0) {
            car.setPosition(car.getPositionX() - 20);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && car.getPositionX() < 750) {
            car.setPosition(car.getPositionX() + 20);
        }
    }
}
