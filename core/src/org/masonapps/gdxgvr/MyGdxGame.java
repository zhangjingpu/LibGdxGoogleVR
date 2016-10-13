package org.masonapps.gdxgvr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.masonapps.gdxgvr.vr.GVRApplicationListener;
import org.masonapps.gdxgvr.vr.GdxEye;
import org.masonapps.gdxgvr.vr.GdxHeadTransform;

public class MyGdxGame extends GVRApplicationListener {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render () {
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void onNewFrame(GdxHeadTransform gdxHeadTransform) {
	}

	@Override
	public void onDrawEye(GdxEye gdxEye) {
		final GdxEye.Viewport viewport = gdxEye.getViewport();
		Gdx.gl.glViewport(viewport.x, viewport.y, viewport.width, viewport.height);
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
