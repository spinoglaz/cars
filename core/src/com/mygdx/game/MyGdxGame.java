package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
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
	ArrayList<BotCar> botCar;
	InputController inputController;
	boolean gameOver;
	Texture gameOverTexture;
	float timeToSpawnObstacle;
	float timeToSpawnBotCar;
	int collisionCounter;
	Texture[] counterCarTextures;
	Road road;
	boolean pause;
	Sound soundOnRoad;
	Sound soundCrashObstacle;
	Sound soundCrashCar;

	@Override
	public void create () {
		batch = new SpriteBatch();
		road = new Road(140, 705, new Texture("road.png"), new Lane[]{
				new Lane(174, 282, false),
				new Lane(291, 414, false),
				new Lane(423, 548, true),
				new Lane(554, 670, true)});
		background = new Background(road);
		car = new Car(600f);
		obstacles = new ArrayList<>();
		botCar = new ArrayList<>();
		inputController = new InputController(car, road);
		gameOverTexture = new Texture("game_over.png");
		counterCarTextures = new Texture[]{
				new Texture("car2.png"),
				new Texture("car3.png"),
				new Texture("car4.png"),
				new Texture("car5.png"),
		};
		restart();
		soundOnRoad = Gdx.audio.newSound(Gdx.files.internal("road.mp3"));
		soundCrashObstacle = Gdx.audio.newSound(Gdx.files.internal("crashObs.wav"));
		soundCrashCar = Gdx.audio.newSound(Gdx.files.internal("crashCar.wav"));
		long soundOnRoadId = soundOnRoad.play();
		soundOnRoad.setLooping(soundOnRoadId, true);
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getRawDeltaTime();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		if(!pause) {
			if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
				pause = true;
			}
			if (!gameOver) {
				update(dt);
				background.render(batch);
				car.render(batch);
				for (int i = 0; i < obstacles.size(); i++) {
					obstacles.get(i).render(batch);
				}
				for (int i = 0; i < botCar.size(); i++) {
					botCar.get(i).render(batch);
				}
			} else {
				batch.draw(gameOverTexture, 0, 0);
				if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
					restart();
				}
			}
		} else {
			if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
				pause = false;
			}
		}
		batch.end();
	}

	public void update(float dt) {
		spawnObstacles(dt);
		spawnBotCars(dt);
		background.update(dt);
		inputController.moveCar(dt);
		for (int i = 0; i < obstacles.size() ; i++) {
			obstacles.get(i).update(dt);
		}
		for (int i = 0; i < botCar.size() ; i++) {
			botCar.get(i).update(dt);
		}
		car.getRectangle().setLocation((int)car.getPositionX(), (int)car.getPositionY());
		collisionOfObstacles();
		gameOver();
	}

	private void spawnObstacles(float dt) {
		timeToSpawnObstacle -= dt;
		if (timeToSpawnObstacle < 0) {
			obstacles.add (new Obstacle());
			timeToSpawnObstacle = (float) (1 + Math.random() * 8);
		}
	}

	private void spawnBotCars(float dt) {
		timeToSpawnBotCar -= dt;
		if(timeToSpawnBotCar < 0) {
			int textureIndex = ThreadLocalRandom.current().nextInt(counterCarTextures.length);
			Texture texture = counterCarTextures[textureIndex];
			int laneIndex = ThreadLocalRandom.current().nextInt(road.getLanes().length);
			Lane lane = road.getLanes()[laneIndex];
			int speed;
			float positionY = 1000;
			if (lane.isUpward()) {
				speed = -200;
			}
			else {
				speed = -700;
			}
			botCar.add(new BotCar(texture, speed, lane, positionY));
			timeToSpawnBotCar = (float) (1 + Math.random() * 1);
		}
	}

	public void gameOver() {
		for (int i = 0; i < obstacles.size(); i++) {
			if(car.getRectangle().intersects(obstacles.get(i).getRectangle())) {
				soundCrashObstacle.play(1f);
				collisionCounter++;
				obstacles.remove(i);
				i--;
			}
		}
		for (int i = 0; i < botCar.size(); i++) {
			if(car.getRectangle().intersects(botCar.get(i).getRectangle())) {
				soundCrashCar.play(0.3f);
				collisionCounter++;
				botCar.remove(i);
				i--;
			}
		}
		if(collisionCounter==3) {
			gameOver = true;
		}
	}

	public void collisionOfObstacles() {
		for (int i = 0; i < obstacles.size(); i++) {
			for (int j = 0; j < botCar.size(); j++) {
				if(obstacles.get(i).getRectangle().intersects(botCar.get(j).getRectangle())) {
					obstacles.remove(i);
					i--;
					break;
				}
			}
		}
	}

	public void restart() {
		car.setPosition(600f);
		gameOver = false;
		timeToSpawnObstacle = 3.0f;
		timeToSpawnBotCar = 1.0f;
		collisionCounter = 0;
		pause = false;

		obstacles.clear();
		botCar.clear();
	}

	@Override
	public void dispose () {
		batch.dispose();
		soundOnRoad.dispose();
		soundCrashObstacle.dispose();
		soundCrashCar.dispose();
	}
}
