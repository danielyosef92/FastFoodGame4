package com.fatskateshenkar.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.fatskateshenkar.game.GameHendler;

import java.util.Random;

import static com.fatskateshenkar.game.Game.gameHendler;

/**
 * Created by danielluzgarten on 26/12/2016.
 */


public class Food {
    Texture sprite;
    float speed = 14;
    float velocity = speed;
    float yPostion;
    float width;
    float height;
    float xPos;
    Rectangle rectangle;

    float rectW,rectH,rectX;

    Texture carcter = new Texture("fn1.png");
    public Food() {
        sprite = new Texture("badFood1.png");
        width = sprite.getWidth()/2;
        height = sprite.getHeight()/2;
        xPos =Gdx.graphics.getWidth();
        yPostion = randY();
        rectangle = new Rectangle(10,10,10,10);
       // gameHendler.food.xPos,gameHendler.food.yPostion,gameHendler.food.width*1/2, gameHendler.food.height*1/2;
        rectW = width/10;
        rectH = height;
        rectX = xPos;
    }


    public float randY(){
        float minY = 0;
        float maxY = Gdx.graphics.getHeight()/2 - (carcter.getHeight()/2);
        Random rand = new Random();
        float finalX = rand.nextFloat() * (maxY- minY) + minY;
        return  finalX;
    }
    public void speedUp(){
        if (velocity>70){
            velocity = 70f;
        }
        else {
            velocity = velocity+7f;

        }
    }
    public void foodMovment() {
        if (gameHendler.food.xPos < -(gameHendler.screenWidth)){
            if (gameHendler.boom){
                gameHendler.boom = false;
                gameHendler.level++;
                if (gameHendler.level == 5){
                    gameHendler.food.speedUp();
                    gameHendler.level =0;
                }
            }
            gameHendler.food.xPos = gameHendler.screenWidth;
            gameHendler.food.yPostion = gameHendler.food.randY();

        }
        else {
            gameHendler.food.xPos -= gameHendler.food.velocity;
        }
    }
    public void drow(){
        gameHendler.batch.draw(gameHendler.food.sprite,xPos,yPostion,width,height);
    }
    public void calcRectangle(){
        rectangle.set(gameHendler.food.xPos,gameHendler.food.yPostion,rectW,rectH);
    }
}
