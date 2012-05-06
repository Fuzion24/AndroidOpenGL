package com.fuzion24.opengltest;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class OpenGLRenderer implements Renderer {
	/** Pyramid instance */
	/** Cube instance */
	private Cube cube;
	
	/** Angle For The Pyramid */
	private float rtri; 	
	/** Angle For The Cube */
	private float rquad; 	
	
	/**
	 * Instance the Pyramid and Cube objects
	 */
	public OpenGLRenderer() {
	
		cube = new Cube();
	}

	/**
	 * The Surface is created/init()
	 */
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {		
		gl.glShadeModel(GL10.GL_SMOOTH); 			//Enable Smooth Shading
		gl.glClearColor(1.0f, 1.0f, 1.0f, 0.5f); 	//Black Background
		gl.glClearDepthf(1.0f); 					//Depth Buffer Setup
		gl.glEnable(GL10.GL_DEPTH_TEST); 			//Enables Depth Testing
		gl.glDepthFunc(GL10.GL_LEQUAL); 			//The Type Of Depth Testing To Do
		
		//Really Nice Perspective Calculations
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST); 
	}

	/**
	 * Here we do our drawing
	 */
	public void onDrawFrame(GL10 gl) {
		//Clear Screen And Depth Buffer
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);	
		gl.glLoadIdentity();					//Reset The Current Modelview Matrix
		
		//Drawing
		gl.glTranslatef(0.0f, 0f, -7.0f);	//Move down 1.0 Unit And Into The Screen 7.0
		//Minor change: Scale the Cube to 80 percent, otherwise it would be too large for the Emulator screen
		gl.glScalef(0.5f, 0.5f, 0.5f); 			
		gl.glRotatef(rquad, 1.0f, 1.0f, 1.0f);	//Rotate The Square On The X axis 
		cube.draw(gl);							//Draw the Cube
		
		
		//Rotation
		rtri += 0.2f; 							//Increase The Rotation Variable For The Pyramid 
		rquad -= 0.45f; 						//Decrease The Rotation Variable For The Cube
	}

	/**
	 * If the surface changes, reset the view
	 */
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		if(height == 0) { 						//Prevent A Divide By Zero By
			height = 1; 						//Making Height Equal One
		}

		gl.glViewport(0, 0, width, height); 	//Reset The Current Viewport
		gl.glMatrixMode(GL10.GL_PROJECTION); 	//Select The Projection Matrix
		gl.glLoadIdentity(); 					//Reset The Projection Matrix

		//Calculate The Aspect Ratio Of The Window
		GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);

		gl.glMatrixMode(GL10.GL_MODELVIEW); 	//Select The Modelview Matrix
		gl.glLoadIdentity(); 					//Reset The Modelview Matrix
	}

}
