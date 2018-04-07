package com.hbp.calcdodge;

public class Dot_vert_cubic extends Dot{
	   float a;
	   float b;
	   float c;
	   float d;
	   
	   Dot_vert_cubic(float xposn, float A, float B, float C, float D, float start_time){
		   super(xposn, start_time);
		   a=A;
		   b=B;
		   c=C;
		   d=D;
	   }
	   
	   Dot_vert_cubic(float xposn, float A, float C, float D, float start_time){
		   this(xposn, A, 0, C, D, start_time);
	   }
	   
	   Dot_vert_cubic(float xposn, float A, float C, float start_time){
		   this(xposn, A, 0, C, 0, start_time);
	   }
	   
	   Dot_vert_cubic(float xposn, float A, float start_time){
		   this(xposn, A, 0, 0, 0, start_time);
	   }
	   
	   @Override
	   
	   void locate_to_function(){
		   y=a*t*t*t+b*t*t+c*t+d;
		   x=k;
	   }
}