package com.hbp.calcdodge;

import com.badlogic.gdx.math.Rectangle;

public class Dot {
	   Rectangle rect;
	   float x;
	   float y;
	   float t;
	   int t0;
	   
	   float k;
	   
	   Dot(float posn, int start_time){
		   rect=new Rectangle(0,0,40,40);
		   k=posn;
		   t0=start_time;
	   }
	   
	   void update_location(float current_time){
		   t=current_time-t0;
		   locate_to_function();
		   convert_to_realspace();
	   }
	   
	   void locate_to_function(){
		   y=t;
		   x=k;
	   }
	   
	   void convert_to_realspace(){
		   rect.setCenter(x*80+160,y*80+200);
	   }
	   
	   String return_t_line(){
		   return "t = T - " + t0 + " = "+double_formatted(t);
	   }
	   
	   String return_x_line(){
		   return "";
	   }
	   String return_y_line(){
		   return "";
	   }
	   
	   public String double_formatted(double doub){
		   double a=Math.round(doub*10.0)/10.0;
		   Float b=(Float)(float)a;
		   return b.toString();
	   }
	   
}