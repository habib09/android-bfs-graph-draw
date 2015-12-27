package com.habib.bfs.app;

import java.util.ArrayList;
import java.util.LinkedList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity{
	
	public static ArrayList<ArrayList<Integer>> grap;
	public static int gSize;
	private int heigth_grap;
	private ArrayList<Integer> bfsgrap = new ArrayList<Integer>();
	Context context;
	private int[] par;
	private int[] taken;
	private int[] level;
	private int[] num_of_nodes_inlevel;
	private DrawGrapView drawGrapView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		context = MainActivity.this;
		drawGrapView = new DrawGrapView(this);
		setContentView(drawGrapView);
	}
	
	public void mainAlgo(int src){
		
		taken = new int[gSize];
		par = new int[gSize];
		level = new int[gSize];
		num_of_nodes_inlevel = new int[gSize + 1];
		bfsgrap.clear();

		for (int i = 0; i < gSize; i++) {
			taken[i] = 0;
		}
		LinkedList<Integer> queue = new LinkedList<Integer>();
		taken[src] = 1;
		par[src] = src;
		level[src] = 1;
		heigth_grap = 1;
		num_of_nodes_inlevel[1] = 1;
		bfsgrap.add(Integer.valueOf(src));
		queue.add(Integer.valueOf(src));
		while (!queue.isEmpty()) {
			int u = queue.poll().intValue();
			for (int i = 0; i < grap.get(u).size(); i++) {
				int v = grap.get(u).get(i).intValue();
				if (taken[v] == 0) {
					taken[v] = 1;
					bfsgrap.add(Integer.valueOf(v));
					queue.add(Integer.valueOf(v));
					par[v] = u;
					level[v] = level[u] + 1;
					num_of_nodes_inlevel[level[v]]++;
					if (level[v] > heigth_grap) {
						heigth_grap = level[v];
					}
				}
			}
		}
		
		drawBFS();
	}

	private void drawBFS() {
		Intent intent = new Intent(this,SubActivity.class);
		intent.putExtra("num_of_nodes_inlevel", num_of_nodes_inlevel);
		intent.putExtra("height_grap", heigth_grap);
		intent.putExtra("par", par);
		intent.putIntegerArrayListExtra("bfsgrap", bfsgrap);
		startActivity(intent);
	}

}
