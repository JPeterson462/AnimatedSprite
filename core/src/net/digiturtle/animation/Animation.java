package net.digiturtle.animation;

public class Animation {
	
	public static class ComponentTransform {
		
		public int componentIndex;
		
		public float rotation; // in radians
		
		public int translateX, translateY;
		
	}
	
	public static class Frame {
		
		public ComponentTransform[] componentTransforms;
		
	}
	
	public Frame[] frames;
	
	public float timePerFrame;
	
	public boolean looping;

}
