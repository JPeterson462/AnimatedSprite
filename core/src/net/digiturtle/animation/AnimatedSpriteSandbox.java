package net.digiturtle.animation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimatedSpriteSandbox extends ApplicationAdapter {
	SpriteBatch batch;
	AnimatedSprite sprite1;
	Animation animation1;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		AnimationSchematic schematic1 = AnimationBuilder.newSchematic(
				new TextureRegion(new Texture("MadScientist_SIDE_FOOT.png")),
				new TextureRegion(new Texture("MadScientist_SIDE_FOOT.png")),
				new TextureRegion(new Texture("MadScientist_SIDE_HEAD.png")),
				new TextureRegion(new Texture("MadScientist_SIDE_TORSO.png")), 
				new TextureRegion(new Texture("MadScientist_SIDE_ARM.png"))
			)
				.newComponent(0, 174/2, 202/2)
				.newComponent(1, 174/2, 202/2)
				.newComponent(2, 174/2, 202/2)
				.newComponent(3, 174/2, 202/2)
				.newComponent(4, 174/2, 202/2 - 20)
				.build();
		sprite1 = new AnimatedSprite(schematic1);
		animation1 = AnimationBuilder.newAnimation(.25f, true)
				.beginFrame()
					.newTransform(0, 0, 0, 0)
					.newTransform(1, 0, 0, 0)
					.newTransform(4, 0, 0, 0)
					.endFrame()
				.beginFrame()
					.newTransform(0, 0, -10, 0)
					.newTransform(1, 0, 10, 0)
					.newTransform(4, 30, 0, 0)
					.endFrame()
				.beginFrame()
					.newTransform(0, 0, 0, 0)
					.newTransform(1, 0, 0, 0)
					.newTransform(4, 0, 0, 0)
					.endFrame()
				.beginFrame()
					.newTransform(0, 0, 10, 0)
					.newTransform(1, 0, -10, 0)
					.newTransform(4, -20, 0, 0)
					.endFrame()
				.build();
		sprite1.animate(animation1);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sprite1.act(Gdx.graphics.getDeltaTime());
		batch.begin();
		sprite1.draw(batch, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
