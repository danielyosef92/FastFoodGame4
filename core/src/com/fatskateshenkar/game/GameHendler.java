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
    Texture sky,road,skyLineClose,skyLineFar,trees ;
    int gameState  = 0;
    Random randdomGenerator;

    public int level = 0;
    int score = 0;
    BitmapFont font;
    float screenHight = Gdx.graphics.getHeight();
    float screenWidth = Gdx.graphics.getWidth();
    boolean boom =false;
    float skyX = 0,roadX = 0,treesX =0,closeX=0,farX=0;
    float skyV = 2,roadV = 4,treesV =3,closeV=2,farV=1f ;
    Rectangle fatboyRect;
    float timeStete =0f;
    int scoreHit = 5;
    public GameHendler() {
        batch = new SpriteBatch();
        background = new Texture("bg2.png");
        background2 = new Texture("bg2.png");
        sky = new Texture("sky.png");
        skyLineClose = new Texture("skyLineCloser.png");
        skyLineFar = new Texture("skyLineFar.png");
        road = new Texture("road.png");
        trees = new Texture("trees.png");
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
            food.xPos = screenWidth + fatBoy.startingX +fatBoy.getWidth();
            food.yPostion = food.randY();
            if (score - scoreHit <0){
                score = 0;
            }
            else {
                score-=scoreHit;
            }
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
    public void backgroundDrow(){
        gameHendler.getBatch().draw(sky, skyX, 0, gameHendler.screenWidth, gameHendler.screenHight );
        gameHendler.getBatch().draw(sky, gameHendler.skyX +gameHendler.screenWidth, 0, gameHendler.screenWidth, gameHendler.screenHight );

        gameHendler.getBatch().draw(skyLineFar, farX,screenHight/6, gameHendler.screenWidth, gameHendler.screenHight/2);
        gameHendler.getBatch().draw(skyLineFar, farX+gameHendler.screenWidth, screenHight/6, gameHendler.screenWidth, gameHendler.screenHight/2 );

        gameHendler.getBatch().draw(skyLineClose, closeX,screenHight/6, gameHendler.screenWidth, gameHendler.screenHight/2-screenHight/5 );
        gameHendler.getBatch().draw(skyLineClose, closeX +gameHendler.screenWidth, screenHight/6, gameHendler.screenWidth, gameHendler.screenHight/2-screenHight/5 );

        gameHendler.getBatch().draw(trees, treesX,screenHight/6, gameHendler.screenWidth, gameHendler.screenHight/10 );
        gameHendler.getBatch().draw(trees, treesX +gameHendler.screenWidth, screenHight/6, gameHendler.screenWidth, gameHendler.screenHight/10 );

        gameHendler.getBatch().draw(road, roadX, 0, gameHendler.screenWidth, gameHendler.screenHight/6);
        gameHendler.getBatch().draw(road, roadX +gameHendler.screenWidth, 0, gameHendler.screenWidth, gameHendler.screenHight/6);
    }
    public void backgroundCalcPostion(){
        if (gameHendler.skyX == -(gameHendler.screenWidth)){
            gameHendler.skyX = 0;
            roadX  = 0;
        }
        if (roadX == -(screenWidth)){
            roadX = 0;
        }
        if (treesX == -(screenWidth)){
            treesX = 0;
        }
        if (closeX == -(screenWidth)){
            closeX = 0;
        }
        if (farX == -(screenWidth)){
            farX = 0;
        }
    }
    public void gameRuner(){
        if (gameHendler.gameState != 0) {
            gameHendler.skyX -= gameHendler.skyV;
            roadX -= roadV;
            treesX -= treesV;
            closeX -= closeV;
            farX -= farV;
            gameHendler.burnCal();
            //input checking
            gameHendler.inputChaking();

            //food logic
            gameHendler.food.foodMovment();
            gameHendler.food.calcRectangle();
//            gameHendler.food.drow();
            //jump
            gameHendler.fatBoy.jumpLogic();

            //strat game by tuche
        } else {
            if (Gdx.input.justTouched()) {
                Gdx.app.log("Touched", "yep");
                gameHendler.gameState = 1;
            }
        }
    }
    public void speedGame(){
        float l1=food.speed,l2=food.speed*1.5f,l3=food.speed*2,l4=food.speed*3;
        if (score<=10){
            food.velocity = l1;
        }
        else if(score<=30){
            food.velocity = l2;
        }
        else if (score<=60){
            food.velocity = l3;
        }
        else {
            food.velocity=l4;
        }
    }

}

