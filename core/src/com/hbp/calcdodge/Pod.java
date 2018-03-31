package com.hbp.calcdodge;

import com.badlogic.gdx.math.Rectangle;

public class Pod {
	   Rectangle pod_r;
	   Rectangle shield_one_r;
	   Rectangle shield_two_r;
	   Rectangle shield_three_r;
	   Rectangle shield_four_r;
	  
	   
	   Pod(){
		  pod_r = new Rectangle(0,0,40,40);
		  shield_one_r = new Rectangle(0,0,50,50);
		  shield_two_r = new Rectangle(0,0,60,60);
		  shield_three_r = new Rectangle(0,0,70,70);
		  shield_four_r = new Rectangle(0,0,80,80);
	   }
	   void update_shields(){
		   shield_one_r.setCenter(pod_r.x+pod_r.width/2, pod_r.y+pod_r.height/2);
		   shield_two_r.setCenter(pod_r.x+pod_r.width/2, pod_r.y+pod_r.height/2);
		   shield_three_r.setCenter(pod_r.x+pod_r.width/2, pod_r.y+pod_r.height/2);
		   shield_four_r.setCenter(pod_r.x+pod_r.width/2, pod_r.y+pod_r.height/2);
	   }
		   
}