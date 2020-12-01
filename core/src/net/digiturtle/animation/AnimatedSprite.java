package net.digiturtle.animation;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

import net.digiturtle.animation.Animation.ComponentTransform;

public class AnimatedSprite {
	
	private AnimationSchematic schematic;
	private Animation animation;
	private float t;
	
	public AnimatedSprite (AnimationSchematic schematic) {
		this.schematic = schematic;
		reset();
	}
	
	public void reset () {
		t = 0;
	}
	
	public void animate (Animation animation) {
		reset();
		this.animation = animation;
	}
	
	public void act (float dt) {
		t += dt;
	}
	
	public void draw (Batch batch, int x, int y) {
		if (animation != null) {
			int lastFrame = (int) (t / animation.timePerFrame);
			if (lastFrame >= animation.frames.length) {
				t = 0;
				lastFrame = 0;
			}
			float f = (t - lastFrame * animation.timePerFrame) / animation.timePerFrame;
			Animation.Frame previous = animation.frames[lastFrame];
			Animation.Frame next;
			if (lastFrame < animation.frames.length - 1) {
				next = animation.frames[lastFrame + 1];
			} else {
				next = animation.frames[0];
			}
			for (int i = 0; i < schematic.textures.length; i++) {
				ComponentTransform previousTransform = getTransform(previous, i);
				ComponentTransform nextTransform = getTransform(next, i);
				float rotation = 0, originX = 0, originY = 0, tx = 0, ty = 0;
				if (previousTransform != null) {
					rotation = MathUtils.lerp(previousTransform.rotation, nextTransform.rotation, f);
					originX = schematic.components[i].centerX;
					originY = schematic.components[i].centerY;
					tx = MathUtils.lerp(previousTransform.translateX, nextTransform.translateX, f);
					ty = MathUtils.lerp(previousTransform.translateY, nextTransform.translateY, f);
				}
				batch.draw(schematic.textures[i], x + tx, y + ty, originX, originY, schematic.textures[i].getRegionWidth(), 
						schematic.textures[i].getRegionHeight(), 1, 1, rotation);
			}
		}
	}
	
	private ComponentTransform getTransform (Animation.Frame frame, int component) {
		for (ComponentTransform transform : frame.componentTransforms) {
			if (transform.componentIndex == component) {
				return transform;
			}
		}
		return null;
	}

}
