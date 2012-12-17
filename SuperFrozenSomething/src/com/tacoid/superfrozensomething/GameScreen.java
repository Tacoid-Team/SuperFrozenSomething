package com.tacoid.superfrozensomething;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class GameScreen implements Screen {
	
	private Sprite shipSprite;
	
	private Stage stage;
	private ShipActor ship;
	
	public void init(AssetManager manager) {
		Texture shipImage = (Texture)manager.get("data/ship.png");
		Texture spaceImage = (Texture)manager.get("data/space.png");
		
		shipSprite= new Sprite(shipImage);
		
		stage = new Stage(800, 480, true);
		Gdx.input.setInputProcessor(stage);
		ship = new ShipActor(shipSprite);
		
		ship.setX(200);
		ship.setY(100);
		ship.setColor(0, 1, 0, 1);
		
		System.out.println("ready");
		stage.addActor(new Image(new TextureRegion(spaceImage, 800, 480)));
		stage.addActor(ship);
		
		ship.moveTo(500, 400);
		
		System.out.println("GameScreen init");
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			stage.getCamera().unproject(touchPos);
			ship.moveTo(touchPos.x - 64/2, touchPos.y - 64/2);
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
