package com.fatskateshenkar.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

import static com.fatskateshenkar.game.Game.gameHendler;

public class GameHendler {

    FatBoy fatBoy;
    Food food;
    SpriteBatch batch;
    Texture background;
    Texture background2;
    int gameState  = 0;
    Random randdomGenerator;
    public int level = 0;
    int score = 0;
    BitmapFont font;
    float screenHight = Gdx.graphics.getHeight();
    float screenWidth = Gdx.graphics.getWidth();
    boolean boom =false;
    float backgroundX = 0;
    float getBackgroundVelocity = 4;
    Rectangle fatboyRect;
    float timeStete =0f;

    public GameHendler() {
        batch = new SpriteBatch();
        background = new Texture("bg2.png");
        background2 = new Texture("bg2.png");
        randdomGenerator = new Random();
        fatBoy = new FatBoy();
        food = new Food();
        fatboyRect = new Rectangle(70+fatBoy.width/2,fatBoy.yPosition,fatBoy.width,fatBoy.hieght * 2.5f);
    }

    public SpriteBatch getBatch() {
        return batch;
    }


    public void inputChaking(){
        if (Gdx.input.getDeltaY() <0) {
            if (gameHendler.fatBoy.yPosition <= 0) {
                gameHendler.fatBoy.status = 1;
                gameHendler.fatBoy.jump = true;
            }
        }
        else if (Gdx.input.getDeltaY() > 0) {
            if (gameHendler.fatBoy.yPosition ==0){
                gameHendler.fatBoy.status = 2;
            }
            if (gameHendler.fatBoy.jump){
                gameHendler.fatBoy.gravity = gameHendler.fatBoy.superGarvity;
            }
        }
    }

    public void colison(){
        if (Intersector.overlaps(gameHendler.fatBoy.rectangle,gameHendler.food.rectangle)){
            food.xPos = screenWidth + 70 +fatBoy.getWidth();
            food.yPostion = food.randY();
            score-=5;
        }

    }
    public void initFont(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Arcon.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();

        params.size =150;
        params.color = Color.WHITE;
        font = generator.generateFont(params);
    }
    public void burnCal(){
        timeStete+=Gdx.graphics.getDeltaTime();
        if (timeStete>=1f){
            gameHendler.score++;
            timeStete = 0f;
        }
    }
    public void drowFont(){
        font.draw(gameHendler.batch,String.valueOf(gameHendler.score),gameHendler.screenWidth-(gameHendler.screenWidth*1/8),gameHendler.screenHight-(gameHendler.screenHight*1/8));
    }

}

