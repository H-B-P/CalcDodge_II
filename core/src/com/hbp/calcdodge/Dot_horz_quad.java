package com.hbp.calcdodge;

public class Dot_horz_quad extends Dot{
	   float a;
	   float b;
	   float c;
	   
	   Dot_horz_quad(float xposn, float A, float B, float C, float start_time){
		   super(xposn, start_time);
		   a=A;
		   b=B;
		   c=C;
	   }
	   
	   @Override
	   
	   void locate_to_function(){
		   y=k;
		   x=a*t*t+b*t+c;
	   }
}