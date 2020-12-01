package net.digiturtle.animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationSchematic {
	
	public static class Component {
		
		public int textureIndex;
		
		public int offsetX, offsetY, centerX, centerY;
		
	}
	
	public Component[] components;
	
	public TextureRegion[] textures;
	
}
