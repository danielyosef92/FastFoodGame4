package com.fatskateshenkar.game;

import com.badlogic.gdx.ApplicationAdapter;

import static com.fatskateshenkar.game.Game.gameHendler;


public class Game extends ApplicationAdapter {
	static GameHendler gameHendler;
	UiHendler uiEndler;
	LogicHendler logicHendler;

	@Override
	public void create() {
		gameHendler = new GameHendler();
		uiEndler = new UiHendler();
		logicHendler = new LogicHendler();
		gameHendler.initFont();

	}
	@Override
	public void render() {
		gameHendler.getBatch().begin();
		uiEndler.run();
		logicHendler.run();
		gameHendler.batch.end();
	}

	@Override
	public void dispose() {
		gameHendler.batch.dispose();
		gameHendler.background.dispose();
		gameHendler.font.dispose();
	}
	@Override
	public void pause() {
		super.pause();
	}

}
class UiHendler implements Runnable{

	@Override
	public void run() {
		gameHendler.backgroundDrow();
		gameHendler.food.drow();
		gameHendler.fatBoy.spriteChanger();
		gameHendler.fatBoy.drow();
		gameHendler.drowFont();
	}
}
class LogicHendler implements Runnable{

	@Override
	public void run() {
		gameHendler.backgroundCalcPostion();
		gameHendler.gameRuner();
		gameHendler.fatBoy.rectanglePicker();
		gameHendler.colison();
		gameHendler.speedGame();
	}
}
