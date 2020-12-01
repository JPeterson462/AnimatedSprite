package net.digiturtle.animation;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import net.digiturtle.animation.Animation.ComponentTransform;
import net.digiturtle.animation.Animation.Frame;
import net.digiturtle.animation.AnimationSchematic.Component;

public class AnimationBuilder {
	
	public static class SchematicBuilder {
		TextureRegion[] textures;
		ArrayList<Component> components = new ArrayList<>();

		public SchematicBuilder newComponent (int textureIndex, int centerX, int centerY) {
			return newComponent(textureIndex, 0, 0, centerX, centerY);
		}
		public SchematicBuilder newComponent (int textureIndex, int offsetX, int offsetY, int centerX, int centerY) {
			Component c = new Component();
			c.textureIndex = textureIndex;
			c.offsetX = offsetX;
			c.offsetY = offsetY;
			c.centerX = centerX;
			c.centerY = centerY;
			components.add(c);
			return this;
		}
		
		public AnimationSchematic build () {
			AnimationSchematic schematic = new AnimationSchematic();
			schematic.components = components.toArray(new Component[components.size()]);
			schematic.textures = textures;
			return schematic;
		}
		
	}
	
	public static class FrameBuilder {
		public AnimatedSequenceBuilder builder;
		public ArrayList<ComponentTransform> transforms = new ArrayList<>();
		
		public FrameBuilder newTransform (int componentIndex, float angle, int translateX, int translateY) {
			ComponentTransform t = new ComponentTransform();
			t.componentIndex = componentIndex;
			t.rotation = angle;
			t.translateX = translateX;
			t.translateY = translateY;
			transforms.add(t);
			return this;
		}
		
		public AnimatedSequenceBuilder endFrame () {
			Frame frame = new Frame();
			frame.componentTransforms = transforms.toArray(new ComponentTransform[transforms.size()]);
			builder.frames.add(frame);
			return builder;
		}
		
	}
	public static class AnimatedSequenceBuilder {
		public float timePerFrame;
		public boolean looping;
		public ArrayList<Frame> frames = new ArrayList<>();
		
		public FrameBuilder beginFrame () {
			FrameBuilder builder = new FrameBuilder();
			builder.builder = this;
			return builder;
		}
		
		public Animation build () {
			Animation animation = new Animation();
			animation.timePerFrame = timePerFrame;
			animation.looping = looping;
			animation.frames = frames.toArray(new Frame[frames.size()]);
			return animation;
		}
		
	}
	
	public static SchematicBuilder newSchematic (TextureRegion... textures) {
		SchematicBuilder builder = new SchematicBuilder();
		builder.textures = textures;
		return builder;
	}
	
	public static AnimatedSequenceBuilder newAnimation (float timePerFrame, boolean looping) {
		AnimatedSequenceBuilder builder = new AnimatedSequenceBuilder();
		builder.timePerFrame = timePerFrame;
		builder.looping = looping;
		return builder;
	}

}
