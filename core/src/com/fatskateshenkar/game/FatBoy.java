package com.fatskateshenkar.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import static com.fatskateshenkar.game.Game.gameHendler;

/**
 * Created by danielluzgarten on 26/12/2016.
 */

public class FatBoy {

    Texture [] sprite;
    Texture jumpImg;
    Texture doudgeImg;
    float yPosition = 0;
    float velocity = 0;
    float regularGravity = 25;
    float gravity= regularGravity;
    float superGarvity = (float) (regularGravity*2.5);
    float antiGravity = 25;
    int status =0;
    float width,hieght,dodgeW,dodgeH,jumpW,jumpH,runW,rectangleXpos;
    Animation<Texture> animation;
    Texture currentFrame;
    float stateTime;
    float dodgeTime = 0;
    boolean inMaxHiget = false;
    boolean jump = false;
    Rectangle rectangle = new Rectangle();
    float startingX;
    float startingY;


    public FatBoy() {
        sprite = new Texture[4];
        sprite[0] = new Texture("fn1.png");
        sprite[1] = new Texture("fn2.png");
        sprite[2] = new Texture("fn3.png");
        sprite[3] = new Texture("fn4.png");
        jumpImg = new Texture("fn5.png");
        doudgeImg = new Texture("fn6.png");

        width = Gdx.graphics.getWidth()/6;
        hieght = Gdx.graphics.getHeight()/2.5f;
        startingX = 70;
        rectangleXpos = startingX + width/2;
        startingY = 0;
        dodgeH = hieght/1.4f;
        dodgeW = width/3;
        jumpH  = hieght-yPosition;
        jumpW = width/3;
        runW = width/3;
        animation = new Animation<Texture>(0.1f, sprite);
        stateTime =0f;
        rectangle.set(startingX,yPosition,width,hieght);

    }

    public float getWidth() {

        return width;
    }
    public float getHieght() {
        return hieght;
    }

    public void jumpLogic(){
        if (jump){
            if (gameHendler.fatBoy.yPosition >=gameHendler.screenHight/2+gameHendler.fatBoy.getHieght()/2){
                inMaxHiget =true;
            }
            if (inMaxHiget){
                gameHendler.fatBoy.yPosition-=  gameHendler.fatBoy.gravity;
                if (gameHendler.fatBoy.yPosition<=0){
                    gameHendler.fatBoy.status = 0;
                    gameHendler.fatBoy.yPosition= 0;
                    jump =false;
                    inMaxHiget= false;
                    gameHendler.fatBoy.gravity = gameHendler.fatBoy.regularGravity;
                }
            }
            else {
                gameHendler.fatBoy.yPosition += gameHendler.fatBoy.antiGravity;
            }
        }
    }
    public void rectanglePicker(){
        if (status == 0) {
            rectangle.set(rectangleXpos,startingY,runW,hieght);
        }
        else if ( status==1){
             rectangle.set(rectangleXpos,yPosition,jumpW,jumpH);
        }
        else {
            rectangle.set(rectangleXpos,startingY,dodgeW,dodgeH);
        }
    }
    public void drow(){
        if (status == 0) {
            gameHendler.batch.draw(currentFrame,startingX,startingY,width,hieght);
        }
        else if ( status==1){
            gameHendler.batch.draw(jumpImg, startingX,yPosition,width,jumpH);
        }
        else {
            gameHendler.batch.draw(doudgeImg,startingX,startingY,width,dodgeH);
            dodgeTime++;
            if (dodgeTime >= 70){
                dodgeTime = 0;
                status =0;
            }
        }
    }
    public void spriteChanger(){
        gameHendler.fatBoy.stateTime += Gdx.graphics.getDeltaTime();
        gameHendler.fatBoy.currentFrame = gameHendler.fatBoy.animation.getKeyFrame(gameHendler.fatBoy.stateTime, true);
    }
}
