package com.hbp.calcdodge;

import com.badlogic.gdx.math.Rectangle;

public class Dot {
	   Rectangle rect;
	   float x;
	   float y;
	   float t;
	   float t0;
	   
	   float a;
	   float b;
	   float c;
	   
	   Dot(float xposn, float start_time, boolean up){
		   rect=new Rectangle(30,30,30,30);
		   a=xposn;
		   t0=start_time;
	   }
	   
	   void update_location(float current_time){
		   t=current_time-t0;
		   locate_to_function();
		   convert_to_realspace();
	   }
	   
	   private void locate_to_function(){
		   y=t-t0;
		   x=a;
	   }
	   
	   private void convert_to_realspace(){
		   rect.setCenter(x*80+160,y*80+200);
	   }
}