/*This class takes the responsibility of drawing
 * it draws nodes level by level ... after that it draws line between
 * parent and child.... and subsequently name each node
 */
package com.habib.bfs.app;

import java.util.ArrayList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.SurfaceHolder;

public class DrawGrap {
	
	int[] level;
	int[] num_of_nodes_inlevel;
	int height_grap;
	int[] par;
	ArrayList<Integer> bfsgrap;
	ArrayList<Point> drawn_node;
	
	private int columMarjin;
	private int rowMarjin;
	
	SurfaceHolder holder;
	Canvas canvas;
	
	public DrawGrap(SurfaceHolder holder,int columMarjin,int rowMarjin) {
			this.holder = holder;
			canvas = holder.lockCanvas();
			this.columMarjin = columMarjin;
			this.rowMarjin = rowMarjin;
	}
	
	public void setdata(int[] level, int[] num_of_nodes_inlevel, int[] par,
			int height_grap, ArrayList<Integer> bfsgrap) {
		this.height_grap = height_grap;
		this.level = level;
		this.par = par;
		this.num_of_nodes_inlevel = num_of_nodes_inlevel;
		this.bfsgrap = bfsgrap;
			
		drawn_node = new ArrayList<Point>();
		for(int i = 0; i < MainActivity.gSize; i++){
			drawn_node.add(new Point(0, 0));
		}
	}
	
	public void draw() {
		if (canvas != null) {
			Paint textpaint = new Paint();
			textpaint.setColor(Color.BLACK); 
			textpaint.setTextSize(38);
			Paint paint = new Paint();	
			paint.setColor(Color.GREEN);
			paint.setStrokeWidth(5.0f);			
			canvas.drawColor(Color.WHITE);
			
			int cx = rowMarjin;
			int cy = columMarjin;
			
			columMarjin = columMarjin / (height_grap + 1);
			cy =columMarjin;
			cx = rowMarjin / 2;
			
			int node_till_now = 0;
			int u = bfsgrap.get(node_till_now++).intValue();
			canvas.drawCircle(cx,cy, 40, paint);
			canvas.drawText("" + u, cx - 15, cy + 15, textpaint);
			
			drawn_node.get(u).set(cx, cy);
			
			for(int i = 2; i <= height_grap; i++){
				
				cy = cy + columMarjin;
				cx = 0;
				int dx =  rowMarjin/ (num_of_nodes_inlevel[i] + 1);
				
				for(int j = 0; j < num_of_nodes_inlevel[i]; j++){
					u = bfsgrap.get(node_till_now++).intValue();
					cx = cx + dx;
					canvas.drawCircle(cx, cy, 40, paint);
				
					drawn_node.get(u).set(cx, cy);
					
					// setting start and end point for line :
					int startX = cx;
					int startY = cy;
					int stopX = drawn_node.get(par[u]).x;
					int stopY = drawn_node.get(par[u]).y;
					canvas.drawLine(startX, startY, stopX, stopY, paint);
					canvas.drawText("" + u, cx - 15, cy + 15, textpaint);
					
				}
			}
		}
	}
	
	public void stop() {
		holder.unlockCanvasAndPost(canvas);
	}
}
