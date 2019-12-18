package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Background background;
	Car car;
	ArrayList<Obstacle> obstacles;
	ArrayList<CounterCar> counterCar;
	InputController inputController;
	boolean gameOver;
	Texture gameOverTexture;
	float timeToSpawnObstacle;
	float timeToSpawnCounterCar;
	int collisionCounter;
	Texture[] counterCarTextures;

	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Background();
		car = new Car();
		obstacles = new ArrayList<>();
		counterCar = new ArrayList<>();
		inputController = new InputController(car);
		gameOver = false;
		gameOverTexture = new Texture("game_over.png");
		timeToSpawnObstacle = 3.0f;
		timeToSpawnCounterCar = 1.0f;
		collisionCounter = 0;
		counterCarTextures = new Texture[]{
				new Texture("CounterCar.png"),
				new Texture("CounterCar1.png"),
				new Texture("CounterCar2.png"),
				new Texture("CounterCar3.png"),
		};
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getRawDeltaTime();
		update(dt);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		if(!gameOver) {
			background.render(batch);
			car.render(batch);
			for (int i = 0; i < obstacles.size() ; i++) {
				obstacles.get(i).render(batch);
			}
			for (int i = 0; i < counterCar.size() ; i++) {
				counterCar.get(i).render(batch);
			}
		} else {
			batch.draw(gameOverTexture, 0, 0);
		}
		batch.end();
	}

	public void update(float dt) {
		timeToSpawnObstacle -= dt;
		if (timeToSpawnObstacle < 0) {
			obstacles.add (new Obstacle());
			timeToSpawnObstacle = (float) (1 + Math.random() * 8);
		}
		timeToSpawnCounterCar -=dt;
		if(timeToSpawnCounterCar < 0) {
			int textureIndex = ThreadLocalRandom.current().nextInt(counterCarTextures.length);;
			counterCar.add(new CounterCar(counterCarTextures[textureIndex]));
			timeToSpawnCounterCar = (float) (1 + Math.random() * 1);
		}
		background.update(dt);
		inputController.moveCar(dt);
		for (int i = 0; i < obstacles.size() ; i++) {
			obstacles.get(i).update(dt);
		}
		for (int i = 0; i < counterCar.size() ; i++) {
			counterCar.get(i).update(dt);
		}
		car.getRectangle().setLocation((int)car.getPositionX(), (int)car.getPositionY());
		collisionOfObstacles();
		gameOver();
		restart();
	}

	public void gameOver() {
		for (int i = 0; i < obstacles.size(); i++) {
			if(car.getRectangle().intersects(obstacles.get(i).getRectangle())) {
				collisionCounter++;
				obstacles.remove(i);
				i--;
			}
		}
		for (int i = 0; i < counterCar.size(); i++) {
			if(car.getRectangle().intersects(counterCar.get(i).getRectangle())) {
				collisionCounter++;
				counterCar.remove(i);
				i--;
			}
		}
		if(collisionCounter==3) {
			gameOver = true;
		}
	}

	public void collisionOfObstacles() {
		for (int i = 0; i < obstacles.size(); i++) {
			for (int j = 0; j < counterCar.size(); j++) {
				if(obstacles.get(i).getRectangle().intersects(counterCar.get(j).getRectangle())) {
					obstacles.remove(i);
					i--;
					break;
				}
			}
		}
	}

	public void restart() {
		if(gameOver) {
			if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)){
				create();
			}
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
