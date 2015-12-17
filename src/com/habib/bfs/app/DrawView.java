/*This class is used to draw the custom view..By extending SarfaceView class
 * and implementing Callback.... 
 */
package com.habib.bfs.app;

import java.util.ArrayList;
import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;


public class DrawView extends SurfaceView implements Callback{
	
	int[] level;
	int[] num_of_nodes_inlevel;
	int height_grap;
	int[] par;
	ArrayList<Integer> bfsgrap;

	SurfaceHolder holder;
	Context context;
	
	public DrawView(Context context) {
		super(context);
		this.context = context;
		holder = getHolder();
		holder.addCallback(this);
	}
	
	
	public void set(int[] level, int[] num_of_nodes_inlevel, int height_grap, int[] par,
			ArrayList<Integer> bfsgrap) {
		this.level = level;
		this.num_of_nodes_inlevel = num_of_nodes_inlevel;
		this.height_grap = height_grap;
		this.par = par;
		this.bfsgrap = bfsgrap;
	}


	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		DrawGrap drawGrap = new DrawGrap(holder,getHeight(),getWidth());
		drawGrap.setdata(level, num_of_nodes_inlevel, par, height_grap, bfsgrap);
		
		drawGrap.draw();
		drawGrap.stop();
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
}
