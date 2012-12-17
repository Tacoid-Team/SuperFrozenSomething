package com.tacoid.superfrozensomething;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.AddAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ShipActor extends Actor {
	private Sprite sprite;
	private float moveX;
	private float moveY;
	private boolean moving;
	private final float speed = 300.f;
	private final float rotate_speed = 300.f; 
	
	public ShipActor(Sprite sprite) {
		this.sprite = sprite;
		setSize(64, 64);
		moveX = 0;
		moveY = 0;
		moving = false;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		Color color = getColor();
		sprite.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		sprite.setPosition(getX(), getY());
		sprite.setRotation(getRotation());
		sprite.draw(batch);
	}
	
	public void moveTo(float x, float y) {
		moveX = x;
		moveY = y;
		moving = true;
	}
	
	@Override
	public void act(float delta_time) {
		if(moving) {
			updateMove(delta_time);
		}
	}
	
	private void updateMove(float delta_time) {
		// Compute target
		float dirX = moveX - getX();
		float dirY = moveY - getY();
		float distance = (float)Math.sqrt(dirX*dirX + dirY*dirY);
		
		float target_angle;
		if(dirX > 0) {
			target_angle = 270 + (float)Math.toDegrees(Math.asin(dirY / distance));
		} else {
			target_angle = 90 + (float)-Math.toDegrees(Math.asin(dirY / distance)); 
		}
		// Rotate
		if(Math.abs(getRotation() - target_angle) > delta_time * rotate_speed) {
			rotate(Math.signum(target_angle - getRotation()) * delta_time * rotate_speed);
		} else {
			setRotation(target_angle);
		}
		
		// Thrust
		if(distance > delta_time * speed) {
			float angle = (float)Math.toRadians(getRotation() + 90);
			translate((float)(speed * delta_time * Math.cos(angle)), (float)(speed * delta_time * Math.sin(angle)));
		} else {
			setX(moveX);
			setY(moveY);
			moving = false;
		}
			
		/*
		MoveToAction action = new MoveToAction();
		action.setPosition(x, y);
		action.setDuration(distance / speed);
		addAction(action);
		*/
	}
}
