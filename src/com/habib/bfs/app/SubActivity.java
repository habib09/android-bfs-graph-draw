/*This class handles the intent received from InputActivity... 
 * then pass all received resources to DrawGrap class...
 * future plan ----> animating the drawing...
 */
package com.habib.bfs.app;


import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SubActivity extends Activity {
	
	int[] level;
	int[] num_of_nodes_inlevel;
	int height_grap;
	int[] par;
	ArrayList<Integer> bfsgrap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		level = getIntent().getExtras().getIntArray("level");
		num_of_nodes_inlevel = getIntent().getExtras().getIntArray("num_of_nodes_inlevel");
		height_grap = getIntent().getExtras().getInt("height_grap");
		par = getIntent().getExtras().getIntArray("par");
		bfsgrap = getIntent().getExtras().getIntegerArrayList("bfsgrap");

		DrawView drawView = new DrawView(this);
		drawView.set(level, num_of_nodes_inlevel, height_grap, par, bfsgrap);
		
		setContentView(drawView);
		//setContentView(R.layout.subactivity);
		
	}

}
