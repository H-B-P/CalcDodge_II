package com.hbp.calcdodge;

import com.badlogic.gdx.math.Rectangle;

public class Dot {
	   Rectangle rect;
	   float x;
	   float y;
	   float t;
	   float t0;
	   
	   float c;
	   
	   Dot(float posn, float start_time){
		   rect=new Rectangle(0,0,40,40);
		   c=posn;
		   t0=start_time;
	   }
	   
	   void update_location(float current_time){
		   t=current_time-t0;
		   locate_to_function();
		   convert_to_realspace();
	   }
	   
	   void locate_to_function(){
		   y=t;
		   x=c;
	   }
	   
	   void convert_to_realspace(){
		   rect.setCenter(x*80+160,y*80+200);
	   }
}