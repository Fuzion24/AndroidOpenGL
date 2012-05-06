package com.fuzion24.opengltest;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class OpenGLTestActivity extends Activity {

	/** The OpenGL View */
	private GLSurfaceView glSurface;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        glSurface = new GLSurfaceView(this);
        glSurface.setRenderer(new OpenGLRenderer());
        setContentView(glSurface);
    }
	/**
	 * Remember to resume the glSurface
	 */
	@Override
	protected void onResume() {
		super.onResume();
		glSurface.onResume();
	}

	/**
	 * Also pause the glSurface
	 */
	@Override
	protected void onPause() {
		super.onPause();
		glSurface.onPause();
	}
}