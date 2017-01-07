package com.fatskateshenkar.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import static com.badlogic.gdx.math.Intersector.overlaps;


public class Game extends ApplicationAdapter {
	static GameHendler gameHendler;
	Rectangle scoreRect, foodRec, fatboyRect,scoreFoodRect;

//	ShapeRenderer boyShape,foodShape,passShap;
//	float  w,h,dw,dh,jw,jh,rh,rw;
//	float  xp,syp,ypos;
//	float fw,fh,fx,fy;
	@Override
	public void create() {
		gameHendler = new GameHendler();
		gameHendler.initFont();
		scoreRect = new Rectangle(0,0,10,gameHendler.screenHight);
		foodRec = new Rectangle(100,100,100,100);
		fatboyRect = new Rectangle(100,100,100,100);
		scoreFoodRect = new Rectangle(0,0,0,0);
//		boyShape  = new ShapeRenderer() ;
//		foodShape = new ShapeRenderer();
//		passShap  = new ShapeRenderer();
//
//		//startingX,yPosition,jumpW,jumpH
//		w = gameHendler.fatBoy.width;		h=gameHendler.fatBoy.hieght;
//
//		dw = gameHendler.fatBoy.dodgeW;		dh=gameHendler.fatBoy.dodgeH;
//
//		jw = gameHendler.fatBoy.jumpW;		jh=gameHendler.fatBoy.jumpH;
//
//		xp = gameHendler.fatBoy.rectangleXpos;	syp=gameHendler.fatBoy.startingY;
//
//		rw = gameHendler.fatBoy.runW;
//
//		fw = gameHendler.food.rectW;		fh=gameHendler.food.rectH;

	}

	@Override
	public void render() {

		gameHendler.getBatch().begin();
		gameHendler.getBatch().draw(gameHendler.background, gameHendler.backgroundX, 0, gameHendler.screenWidth, gameHendler.screenHight );
		gameHendler.getBatch().draw(gameHendler.background2, gameHendler.backgroundX+gameHendler.screenWidth, 0, gameHendler.screenWidth, gameHendler.screenHight );
		if (gameHendler.backgroundX == -(gameHendler.screenWidth)){
			gameHendler.backgroundX = 0;
		}
//		boyShape.begin(ShapeRenderer.ShapeType.Filled);
//		foodShape.begin(ShapeRenderer.ShapeType.Filled);
//		passShap.begin(ShapeRenderer.ShapeType.Filled);
//
//		boyShape.setColor(Color.BLUE);
//		foodShape.setColor(Color.GREEN);
//		passShap.setColor(Color.RED);
//		passShap.rect(0,0,10, gameHendler.screenHight);


		//game///////////////////////////////////////////////////////////////
		if (gameHendler.gameState != 0) {
			gameHendler.backgroundX -= gameHendler.getBackgroundVelocity;
			gameHendler.burnCal();
			//input checking
			gameHendler.inputChaking();
			//food logic

			//fx= gameHendler.food.xPos;			fy = gameHendler.food.yPostion;

			gameHendler.food.foodMovment();
			gameHendler.food.calcRectangle();
			gameHendler.food.drow();
			//foodShape.rect(gameHendler.food.xPos,gameHendler.food.yPostion,fw,fh);

			scoreFoodRect.set(gameHendler.food.xPos,gameHendler.food.yPostion,10, gameHendler.food.height - 100);
			//jump
			gameHendler.fatBoy.jumpLogic();
		//////////////////////////////////////////////////////////////////////

		//strat game by tuche
		} else {
			if (Gdx.input.justTouched()) {
				Gdx.app.log("Touched", "yep");
				gameHendler.gameState = 1;
			}
		}
		///////////////////////////////////////////////////////////////////////


		//boy animation;
//		gameHendler.fatBoy.stateTime += Gdx.graphics.getDeltaTime();
//		gameHendler.fatBoy.currentFrame = gameHendler.fatBoy.animation.getKeyFrame(gameHendler.fatBoy.stateTime, true);
		gameHendler.fatBoy.spriteChanger();
		//drwoing boy
		gameHendler.fatBoy.rectanglePicker();
		gameHendler.fatBoy.drow();
//		if (gameHendler.fatBoy.status == 0) {
//			boyShape.rect(xp,syp,rw,h);
//		}
//		else if (gameHendler.fatBoy.status==1) {
//			ypos = gameHendler.fatBoy.yPosition;
//			boyShape.rect(xp, ypos, rw, h);
//		}
//		else {
//			boyShape.rect(xp,syp,rw,dh);
//		}

		gameHendler.colison();
		gameHendler.drowFont();
		gameHendler.batch.end();
//		boyShape.end();
//		foodShape.end();
//		passShap.end();
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

