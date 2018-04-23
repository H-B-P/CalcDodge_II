package com.hbp.calcdodge;

public class Dot_horz_quad extends Dot{
	   float a;
	   float b;
	   float c;
	   
	   Dot_horz_quad(float yposn, float A, float B, float C, int start_time){
		   super(yposn, start_time);
		   a=A;
		   b=B;
		   c=C;
	   }
	   
	   Dot_horz_quad(float yposn, float A, float C, int start_time){
		   this(yposn, A, 0, C, start_time);
	   }
	   
	   Dot_horz_quad(float yposn, float A, int start_time){
		   this(yposn, A, 0, 0, start_time);
	   }
	   
	   @Override
	   
	   void locate_to_function(){
		   y=k;
		   x=a*t*t+b*t+c;
	   }
	   
	   @Override
	   String return_x_line(){
		   if (b!=0){
			   return "x = " + double_formatted(a) + "t^2 + "+ double_formatted(b) + "t + " + double_formatted(c);
		   }
		   if (c==0){
			   return "x = " + double_formatted(a) + "t^2 = "+ double_formatted(x);
		   }
		   return "x = " + double_formatted(a) + "t^2 + "+double_formatted(c);
	   }
	   @Override
	   String return_y_line(){
		   return "y = " + double_formatted(k);
	   }
}