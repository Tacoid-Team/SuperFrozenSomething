package com.tacoid.superfrozensomething;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class SuperFrozenSomething extends Game {

	public static final int VIRTUAL_WIDTH = 800;
	public static final int VIRTUAL_HEIGHT = 480;
	
	public AssetManager manager;
	private GameScreen gameScreen;
	private LoadingScreen loadingScreen;
	
	@Override
	public void create() {
		manager = new AssetManager();
		loadAssets();
		
		loadingScreen = new LoadingScreen();
	}

	@Override
	public void dispose() {
		/*
		stage.dispose(); 
		
		shipImage.dispose();
		spaceImage.dispose();
		lazerImage.dispose();
		*/
	}

	@Override
	public void render() {	
		if(manager.update()) {
			if(gameScreen == null) {
				gameScreen = new GameScreen();
				gameScreen.init(manager);
				setScreen(gameScreen);
			}
			getScreen().show();
			super.render();
		} else {
			loadingScreen.render(Gdx.graphics.getDeltaTime());
		}
		
	}

	@Override
	public void resize(int width, int height) {
		//stage.setViewport(width, height, true);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
	private void loadAssets() {
		manager.load("data/space.png", Texture.class);
		manager.load("data/ship.png", Texture.class);
		manager.load("data/lazer.png", Texture.class);
	}
}
