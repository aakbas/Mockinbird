package com.aakbas.mockinbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

import java.util.Random;

public class MockinBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture Background;
	Texture Hero;
	Texture Enemy1;
	Texture Enemy2;
	Texture Enemy3;
    float herox=0;
    float heroy=0;
    int GameState=0;
    float velocity=0;
    float gravity=0.3f;
    float enemyVelocity=4;
	int numberOfEnemies=4;
	float [] enemyX=new float[numberOfEnemies];
	float distance=0;
	float [] enemyOffSet1=new float[numberOfEnemies];
	float [] enemyOffSet2=new float[numberOfEnemies];
	float [] enemyOffSet3=new float[numberOfEnemies];
	Random aakbas;
	Circle heroCircle;
	Circle[] enemyCircle1;
	Circle[] enemyCircle2;
	Circle[] enemyCircle3;
	int score=0;
	int scoredEnemy=0;
	BitmapFont font;
	BitmapFont font2;

	
	@Override
	public void create () {
       batch =new SpriteBatch();
       Background=new Texture("Background.png");
       Hero=new Texture("hero.png");
       Enemy1=new Texture("enemy.png");
       Enemy2=new Texture("enemy.png");
       Enemy3=new Texture("enemy.png");
       herox=Gdx.graphics.getWidth()/3-Hero.getHeight()/2;
       heroy=Gdx.graphics.getHeight()/3;
       heroCircle=new Circle();
       enemyCircle1=new Circle[numberOfEnemies];
       enemyCircle2=new Circle[numberOfEnemies];
       enemyCircle3=new Circle[numberOfEnemies];
       font=new BitmapFont();
       font.setColor(Color.WHITE);
       font.getData().setScale(4);
		font2=new BitmapFont();
		font2.setColor(Color.WHITE);
		font2.getData().setScale(6);






       distance=Gdx.graphics.getWidth()/2;
       aakbas=new Random();
       for(int i=0;i<numberOfEnemies;i++){

		   enemyOffSet1[i]=(aakbas.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
		   enemyOffSet2[i]=(aakbas.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
		   enemyOffSet3[i]=(aakbas.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);

       	enemyX[i]=Gdx.graphics.getWidth()/2-Enemy1.getWidth()/2+i*distance;

       	   enemyCircle1[i]=new Circle();
		   enemyCircle2[i]=new Circle();
		   enemyCircle3[i]=new Circle();

	   }

	}

	@Override
	public void render () {

		batch.begin();
		batch.draw(Background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		if(GameState==1){

			if (enemyX[scoredEnemy]<Gdx.graphics.getWidth()/3-Hero.getHeight()/2){
				score++;
				if(scoredEnemy<numberOfEnemies-1){
					scoredEnemy++;
				}
				else{
					scoredEnemy=0;
				}
			}



			if(Gdx.input.justTouched()){
				velocity=-7;
			}

               for(int i=0;i<numberOfEnemies;i++) {

				   if(enemyX[i]<Gdx.graphics.getWidth() / 15){
					   enemyX[i]=enemyX[i]+numberOfEnemies*distance;
					   enemyOffSet1[i]=(aakbas.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
					   enemyOffSet2[i]=(aakbas.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
					   enemyOffSet3[i]=(aakbas.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
				   }
				   else{
					   enemyX[i]=enemyX[i]-enemyVelocity;
				   }
				   enemyX[i]=enemyX[i]-7;
				   batch.draw(Enemy1, enemyX[i], Gdx.graphics.getHeight()/2+enemyOffSet1[i], Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
				   batch.draw(Enemy2, enemyX[i], Gdx.graphics.getHeight()/2+enemyOffSet2[i], Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
				   batch.draw(Enemy3, enemyX[i], Gdx.graphics.getHeight()/2+enemyOffSet3[i], Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
				   enemyCircle1[i]=new Circle( enemyX[i]+Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/2+enemyOffSet1[i]+Gdx.graphics.getHeight() / 20,Gdx.graphics.getWidth()/30);
				   enemyCircle2[i]=new Circle( enemyX[i]+Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/2+enemyOffSet2[i]+Gdx.graphics.getHeight() / 20,Gdx.graphics.getWidth()/30);
				   enemyCircle3[i]=new Circle( enemyX[i]+Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/2+enemyOffSet3[i]+Gdx.graphics.getHeight() / 20,Gdx.graphics.getWidth()/30);

			   }



			if(heroy>0){
				velocity=velocity+gravity;
				heroy=heroy-velocity;
			}
			else{
				GameState=2;


			}

		}
		else if(GameState==0){
			if(Gdx.input.justTouched()){
				GameState=1;
			}
		}
		else if(GameState==2){

			font2.draw(batch,"Kaybettin! Tekrar oynamak icin tikla",100,Gdx.graphics.getHeight()/2);


			if(Gdx.input.justTouched()){
				GameState=1;
				heroy=Gdx.graphics.getHeight()/3;

				for(int i=0;i<numberOfEnemies;i++){

					enemyOffSet1[i]=(aakbas.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
					enemyOffSet2[i]=(aakbas.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
					enemyOffSet3[i]=(aakbas.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);

					enemyX[i]=Gdx.graphics.getWidth()/2-Enemy1.getWidth()/2+i*distance;

					enemyCircle1[i]=new Circle();
					enemyCircle2[i]=new Circle();
					enemyCircle3[i]=new Circle();

				}
                  velocity=0;
				score=0;
				scoredEnemy=0;

			}

		}





        batch.draw(Hero,herox,heroy,Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);
		font.draw(batch,String.valueOf(score),100,200);

		batch.end();

		heroCircle.set(herox+Gdx.graphics.getWidth()/30,heroy+Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/30);



		for(int i=0;i<numberOfEnemies;i++){
         if(Intersector.overlaps(heroCircle,enemyCircle1[i])||Intersector.overlaps(heroCircle,enemyCircle2[i])||Intersector.overlaps(heroCircle,enemyCircle3[i])){
            GameState=2;
		 }

		}


	}
	
	@Override
	public void dispose () {

	}
}
