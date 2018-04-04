package com.hbp.calcdodge;

public class Dot_vert_quad extends Dot{
	   float a;
	   float b;
	   float c;
	   
	   Dot_vert_quad(float yposn, float A, float B, float C, float start_time){
		   super(yposn, start_time);
		   a=A;
		   b=B;
		   c=C;
	   }
	   
	   @Override
	   
	   void locate_to_function(){
		   y=a*t*t+b*t+c;
		   x=k;
	   }
}