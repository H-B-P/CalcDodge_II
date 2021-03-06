package com.hbp.calcdodge;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Iterator;
import java.math.*;
//import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
//import com.badlogic.gdx.Input.Keys;
//import com.badlogic.gdx.Input.Buttons;
//import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
//import com.badlogic.gdx.utils.viewport.*;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.utils.Array;
//import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.Preferences;
import com.hbp.calcdodge.Kaboom;
import com.hbp.calcdodge.Dot;
import com.badlogic.gdx.math.MathUtils;

import com.hbp.calcdodge.CalcDodge;

public class GameScreen extends MetaScreen {
	
	final CalcDodge game;
	
	OrthographicCamera camera;
	 Texture pod_t;
	
	
	 Pod pod_main;
	
	 Pod pod_vert;
	 Pod pod_horz;
	 Pod pod_horzvert;
	
	 Texture statusbar_t;
	
	 Texture poncho_t;
	 Texture window_t;
	
	 Texture grid_t;
	
	Array<Pod> pods;
	 float pod_x;
	 float pod_y;
	 float pod_ydot;
	 float pod_xdot;
	 float pod_xdotdot;
	 float pod_ydotdot;
	 float pod_xdotdotdot;
	 float pod_ydotdotdot;
	
	 float pod_ydash;
	 float pod_ydashdash;
	 float pod_ydotdash;
	 float pod_ydashdot;
	
	 int GAMESPEED;
	 int GAMESPEED_ORI;
	
	String LEVEL;
	int shields;
	int secondlimit;
	
	 float input_x;
	 float input_y;
	
	 float effective_delta;
	
	 Array<Dot> dots;
	 Array<Kaboom> explosions;
	
	 Texture dot_t;
	
	 Texture explosion_t;
	
	 SpriteBatch batch;
	
	 float total_time;
	 int seconds;
	 int explosionseconds;
	
	 int UNIT_LENGTH_IN_PIXELS;
	
	 float SHIP_BOUNDARY_DIST;
	
	 boolean HAVE_WE_EXPLODED;
	
	 Rectangle realityBox;
	 Rectangle anyBox;
	 
	 String ypon;
	 BitmapFont font;
	
	 Texture shield_one_t;
	 Texture shield_two_t;
	 Texture shield_three_t;
	 Texture shield_four_t;
	
	 Rectangle cotestrec;
	
	 String level_ident_s;
	 
	Sound hitshield;
	
	Dot currently_active_dot;
	
	Texture pause_t;
	
	Texture S1_t=new Texture(Gdx.files.internal("CONGRATS.png"));
	Texture S2_t=new Texture(Gdx.files.internal("YOUWON.png"));
	Texture S3_t=new Texture(Gdx.files.internal("GOODJOB.png"));
	
	boolean CONGRATULATE=false;
	
	public GameScreen(final CalcDodge gam, boolean play_the_sound, boolean start_music) {
		
		super(gam, play_the_sound);
		
		GAMESPEED_ORI=200;
		GAMESPEED=GAMESPEED_ORI;
		
		level_ident_s="level 0";
		
		pause_t=new Texture(Gdx.files.internal("pause_symbol.png"));
		
		if (start_music){
			set_up_music();
		}
		
		 this.game = gam;
		 
		hitshield=Gdx.audio.newSound(Gdx.files.internal("sfx_scronched/bang.mp3"));
				
		 
		 
		 explosionseconds=0;
		 dots=new Array<Dot>();
		 explosions = new Array<Kaboom>();
		 
		 cotestrec=new Rectangle();
		 
		 dot_t=new Texture(Gdx.files.internal("sniperdot.png"));
		 
		 explosion_t = new Texture(Gdx.files.internal("explosion.png"));
		 
		 ypon="";
		 
		 pod_x=0;
		 pod_y=0;
		 pod_xdot=0;
		 pod_ydot=0;
		 pod_xdotdot=0;
		 pod_ydotdot=0;
		 pod_xdotdotdot=0;
		 pod_ydotdotdot=0;
		 
		 pod_ydash=0;
		 pod_ydashdash=0;
		 pod_ydashdot=0;
		 
		 
		 statusbar_t=new Texture(Gdx.files.internal("statusbar.png"));
		poncho_t= new Texture(Gdx.files.internal("blackbar_poncho.png"));
		
		window_t=new Texture(Gdx.files.internal("window_normal.png"));
		
			pod_t= new Texture(Gdx.files.internal("cartesian_dodger.png"));
			grid_t= new Texture(Gdx.files.internal("grid_II.png"));
		
			shield_one_t=new Texture(Gdx.files.internal("shield_layer_one.png"));
			shield_two_t=new Texture(Gdx.files.internal("shield_layer_two.png"));
			shield_three_t=new Texture(Gdx.files.internal("shield_layer_three.png"));
			shield_four_t=new Texture(Gdx.files.internal("shield_layer_four.png"));
			
		
			pods= new Array<Pod>();
			
		pod_main= new Pod();
		pod_main.pod_r.x=pod_x*80+160-20;
		pod_main.pod_r.y=pod_y*80+320-20;
		
		pod_horz=new Pod();
		
			pod_horz= new Pod();
			pod_vert= new Pod();
			pod_horzvert= new Pod();
			
			pods.add(pod_main);
			pods.add(pod_horz);
			pods.add(pod_vert);
			pods.add(pod_horzvert);
		
		realityBox=new Rectangle();
		
		realityBox.width=240;
		realityBox.height=240;
		realityBox.setCenter(160,200);
		
		anyBox=new Rectangle();
		
		anyBox.width=320;
		anyBox.height=480;
		anyBox.setCenter(160,240);
		

		level_specific_setup();
		
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 320, 480);
	    batch = new SpriteBatch();
	    
	    total_time=0;
	    seconds=0;
	    
	    UNIT_LENGTH_IN_PIXELS=80;
	    
	    HAVE_WE_EXPLODED=false;
	    
	    SHIP_BOUNDARY_DIST=1.5f;
	    
	    font = new BitmapFont();
	    font.setColor(Color.BLACK);
	   }
	   
	//---level_specific_whatever---
	
	void level_specific_setup(){
		LEVEL="xdotydot";
		shields=2;
		 secondlimit=120;
	}
	
	void level_specific_attack_pattern(){
		if (seconds==1){
			   dots.add(new Dot_vert(0,0.5f,8));
		   }
		   else{
			   if((seconds%6==0 && seconds>6 && seconds<60)||(seconds>=60 && seconds<110 && seconds%4==0)){
				   int a=0;
				   int b=0;
				   while (a==b){
					   a=MathUtils.random(-1,1);
					   b=MathUtils.random(-1,1);
				   }
				   if (MathUtils.random(0,1)>0){
					   dots.add(new Dot_vert(a,0.8f, seconds+4));
				   }
				   else{
					   dots.add(new Dot_horz(a,0.8f, seconds+4));   
				   }
				   if (MathUtils.random(0,1)>0){
					   dots.add(new Dot_vert(b,-0.8f, seconds+4));
				   }
				   else{
					   dots.add(new Dot_horz(b,-0.8f, seconds+4));
				   }
				   System.out.println("make a thing!");
			   }
		   }
	}
	
	void set_up_music(){
		bgm=Gdx.audio.newMusic(Gdx.files.internal("MCS_Phazer.mp3"));
		bgm.setLooping(true);
		bgm.setVolume(0.6f);
		bgm.play();
	}
	
	void level_specific_success(){
		game.setScreen(new GameScreen(game, true, true));
	}
	
	void level_specific_failure(){
		game.setScreen(new GameScreen(game, true, true));
	}
	
	   //---FUNCTIONS---
	   
	    int plusorminus(){
		   int coin=MathUtils.random(0,1);
		   return coin*2-1;
	   }
	   
	   //----
	   
	    void spawnExplosion(float X, float Y){
		   Kaboom boom = new Kaboom();
		   boom.rect= new Rectangle();
		   boom.birthtime=total_time;
		   boom.rect.x= X;
		   boom.rect.y= Y;
		   explosions.add(boom);
	   }
	   
	   
	    public String double_formatted(double doub){
			   double a=Math.round(doub*10.0)/10.0;
			   Float b=(Float)(float)a;
			   return b.toString();
		   }
	    
	    public String timely_double_formatted(double doub){
			   double a=Math.round(doub*10.0)/10.0;
			   double a1=Math.round(doub);
			   Float b=(Float)(float)a;
			   String c=b.toString();
			   if (a==a1){
				   return c+".0";
			   }
			   else{
				   return c;
			   }
		   }
	   
	   //---RENDER---
	   @Override
	   public void render(float delta) {
		   
		   meta_render();
		   
		   effective_delta=delta*((float)GAMESPEED)/100f;
		   
		   total_time+=effective_delta;
		   
		   Gdx.gl.glClearColor(0, 0, 0, 1);
		   Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		   
		   camera.update();
		   
		   batch.begin();
		   
		   batch.draw(grid_t, 0, -40);
		   
		   if (!HAVE_WE_EXPLODED){
			   
			   for (Pod pod: pods){
				   batch.draw(pod_t, pod.pod_r.x, pod.pod_r.y);
				   if (shields>=1){
					   batch.draw(shield_one_t, pod.shield_one_r.x, pod.shield_one_r.y);
				   }
				   if (shields>=2){
					   batch.draw(shield_two_t, pod.shield_two_r.x, pod.shield_two_r.y);
				   }
				   if (shields>=3){
					   batch.draw(shield_three_t, pod.shield_three_r.x, pod.shield_three_r.y);
				   }
				   if (shields>=4){
					   batch.draw(shield_four_t, pod.shield_four_r.x, pod.shield_four_r.y);
				   }
			   }
		   }
		   if (HAVE_WE_EXPLODED){
			   for(Kaboom boom: explosions) {
				   batch.draw(explosion_t, boom.rect.x-40, boom.rect.y-40);
			   }
		   }
		   
		   
		   batch.draw(poncho_t, -800+160, -1200+200);
		   batch.draw(window_t, 0, 0);
		   
		   for(Dot dot: dots) {
				   batch.draw(dot_t, dot.rect.x, dot.rect.y);
		   }
		   
		   for(Dot dot: dots) {
			   if (seconds>dot.t0 && !dot.rect.overlaps(anyBox)){
				   dots.removeValue(dot, true);
			   }
	   }
		   
		   if (!HAVE_WE_EXPLODED){
			   for(Kaboom boom: explosions) {
				   batch.draw(explosion_t, boom.rect.x-40, boom.rect.y-40);
			   }
		   }
		   
		   //batch.draw(region, x, y, originX, originY, width, height, scaleX, scaleY, rotation)
		   
		   batch.draw(statusbar_t, 0, 400);
		   
		   
		   if (!HAVE_WE_EXPLODED){
				   font.draw(batch, "x = " + double_formatted(pod_x), 15, 422);
				   font.draw(batch, "y = " + double_formatted(pod_y), 70, 422);
				   if(LEVEL.startsWith("xdot")){
					   font.draw(batch, "x = " + double_formatted(pod_xdot), 15, 447);
					   font.draw(batch, ".", 17, 457);
				   }
				   if(LEVEL.startsWith("xdotdot")){
					   font.draw(batch, "x = " + double_formatted(pod_xdotdot), 15, 472);
					   font.draw(batch, "..", 15, 482);
				   }
				   if(LEVEL.startsWith("2xdot")){
					   font.draw(batch, "x = " + double_formatted(2*pod_xdot), 15, 447);
					   font.draw(batch, "2", 7, 447);
				   }
				   if (LEVEL.split("y").length>1){
					   ypon=(LEVEL.split("y"))[1];
					   if(ypon.startsWith("dot")){
						   font.draw(batch, "y = "+double_formatted(pod_ydot), 70, 447);
						   font.draw(batch, ".", 72, 457);
					   }
					   if(ypon.startsWith("dash")){
						   font.draw(batch, "y = "+double_formatted(pod_ydash), 70, 447);
						   font.draw(batch, "'", 77, 447);
					   }
					   if(ypon.startsWith("dotdot")){
						   font.draw(batch, "y = "+double_formatted(pod_ydotdot), 70, 472);
						   font.draw(batch, "..", 70, 482);
					   }
					   if(ypon.startsWith("dashdash")){
						   font.draw(batch, "y = "+double_formatted(pod_ydashdash), 70, 472);
						   font.draw(batch, "''", 76, 474);
					   }
				   }
		   }
		   
		   
				   //font.draw(batch, "T = "+timely_double_formatted(total_time), 124, 457);
		   font.draw(batch, "- "+ level_ident_s+ " -", 124, 472);
		   font.draw(batch, "T = "+timely_double_formatted(total_time), 124, 447);
		   font.draw(batch, "goal: "+secondlimit, 124, 422);
		   
				   
				   
				   
				   
				   
				   
				   
				   boolean onlyone=true;
				   for (Dot dot: dots){
					   if (dot.rect.contains(tp_x,tp_y) && anyBox.overlaps(dot.rect) && onlyone){
						   font.draw(batch, dot.return_t_line(), 190, 472);
						   font.draw(batch, dot.return_x_line(), 190, 447);
						   font.draw(batch, dot.return_y_line(), 190, 422);
						   onlyone=false;
					   }
				   }
					   
				   
				   if (CONGRATULATE){
					   if (seconds>240){
					   //if (seconds>5){
						   batch.draw(S1_t, 160-70 ,320-12+65);
					   }
					   if (seconds>245){
					   //if (seconds>10){
						   batch.draw(S2_t, 160-70 ,320-12+40);
					   }
					   if (seconds>250){
					   //if (seconds>15){
						   batch.draw(S3_t, 160-70 ,320-12+15);
					   }
				   }
				   
				   if (GAMESPEED==0){
					   batch.draw(pause_t,0, 0);
				   }
				   
		   batch.end();
		   
		   if((seconds+1)<(total_time)){
			   System.out.println(seconds);
			   System.out.println(total_time);
			   seconds+=1;
			   if (HAVE_WE_EXPLODED){
				   explosionseconds+=1;
			   }
			   
			   
			   level_specific_attack_pattern();
			   
			   
		   }
		   
		   if (Gdx.input.isKeyJustPressed(Keys.SPACE)){
			   if (GAMESPEED==0){
				   GAMESPEED=GAMESPEED_ORI;
			   }
			   else{
				   GAMESPEED=0;
			   }
		   }
		   
		   
		   
		   if (Gdx.input.isKeyPressed(Keys.ESCAPE)){
			   bgm.stop();
			   bgm.dispose();
			   game.setScreen(new SelectScreen(game,true));
			   
			   dispose();
		   }
		   
		   if (explosionseconds>2){
			   level_specific_failure();
			   dispose();
		   }
		   
		   else{
			   if (seconds>=secondlimit && !HAVE_WE_EXPLODED){
				   level_specific_success();
				   dispose();
			   }
		   }
		   
		   if (Gdx.input.isKeyPressed(Keys.RIGHT) && !Gdx.input.isKeyPressed(Keys.LEFT)){
			   input_x=1;
		   }
		   else if (Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT)){
			   input_x=-1;
		   }
		   else{
			   input_x=0;
		   }
		   if (Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.DOWN)){
			   input_y=1;
		   }
		   else if (Gdx.input.isKeyPressed(Keys.DOWN) && !Gdx.input.isKeyPressed(Keys.UP)){
			   input_y=-1;
		   }
		   else{
			   input_y=0;
		   }
		   
		   
			   if (LEVEL.startsWith("xdotdot")){
				   pod_xdotdot=input_x;
			   }
			   else if (LEVEL.startsWith("xdot")){
				   pod_xdot=input_x;
			   }
			   else if (LEVEL.startsWith("2xdot")){
				   pod_xdot=input_x/2f;
			   }
			   else if (LEVEL.startsWith("x")){
				   pod_x=input_x;
			   }
			   
			   if (LEVEL.endsWith("ydotdot")){
				   pod_ydotdot=input_y;
			   }
			   else if (LEVEL.endsWith("ydot")){
				   pod_ydot=input_y;
			   }
			   else if (LEVEL.endsWith("y")){
				   pod_y=input_y;
			   }
			   else if (LEVEL.endsWith("ydash")){
				   pod_ydash=input_y;
			   }
			   else if (LEVEL.endsWith("ydashdash")){
				   pod_ydashdash=input_y;
			   }
			   
		   
		   if (pod_ydash>=4){
			   pod_ydash=4;
		   }
		   if (pod_ydash<=-4){
			   pod_ydash=-4;
		   }
		   
		   if (pod_xdot>=4){
			   pod_xdot=4;
		   }
		   if (pod_xdot<=-4){
			   pod_xdot=-4;
		   }
		   if (pod_ydot>=4){
			   pod_ydot=4;
		   }
		   if (pod_ydot<=-4){
			   pod_ydot=-4;
		   }
		   
		   
		   pod_xdotdot+=pod_xdotdotdot*effective_delta;
		   pod_ydotdot+=pod_ydotdotdot*effective_delta;
		   
		   
		   pod_xdot+=pod_xdotdot*effective_delta;
		   pod_ydot+=pod_ydotdot*effective_delta;
		   
		   pod_ydash+=pod_ydashdash*pod_xdot*effective_delta;
		   pod_ydot+=pod_ydotdash*pod_xdot*effective_delta;
		   pod_ydash+=pod_ydashdot*effective_delta;
		   pod_y+=pod_ydash*pod_xdot*effective_delta;
		   
		   
		   pod_x+=pod_xdot*effective_delta;
		   pod_y+=pod_ydot*effective_delta;
		   
		   //System.out.println(pod_x);
		   //System.out.println(pod_y);
		   
		   
			   if (pod_x>SHIP_BOUNDARY_DIST){
				   pod_x-=2*SHIP_BOUNDARY_DIST;
			   }
			   if (pod_x<-SHIP_BOUNDARY_DIST){
				   pod_x+=2*SHIP_BOUNDARY_DIST;
			   }
			   if (pod_y>SHIP_BOUNDARY_DIST){
				   pod_y-=2*SHIP_BOUNDARY_DIST;
			   }
			   if (pod_y<-SHIP_BOUNDARY_DIST){
				   pod_y+=2*SHIP_BOUNDARY_DIST;
			   }
			   
		   
		   pod_main.pod_r.setCenter(pod_x*UNIT_LENGTH_IN_PIXELS+160, pod_y*UNIT_LENGTH_IN_PIXELS+200);
		   
			   pod_horz.pod_r.y=pod_main.pod_r.y;
			   pod_vert.pod_r.x=pod_main.pod_r.x;
			   if (pod_x<0){
				   pod_horz.pod_r.x=pod_main.pod_r.x+SHIP_BOUNDARY_DIST*2*UNIT_LENGTH_IN_PIXELS;
				   pod_horzvert.pod_r.x=pod_main.pod_r.x+SHIP_BOUNDARY_DIST*2*UNIT_LENGTH_IN_PIXELS;
			   }
			   else{
				   pod_horz.pod_r.x=pod_main.pod_r.x-SHIP_BOUNDARY_DIST*2*UNIT_LENGTH_IN_PIXELS;
				   pod_horzvert.pod_r.x=pod_main.pod_r.x-SHIP_BOUNDARY_DIST*2*UNIT_LENGTH_IN_PIXELS;
			   }
			   if (pod_y<0){
				   pod_vert.pod_r.y=pod_main.pod_r.y+SHIP_BOUNDARY_DIST*2*UNIT_LENGTH_IN_PIXELS;
				   pod_horzvert.pod_r.y=pod_main.pod_r.y+SHIP_BOUNDARY_DIST*2*UNIT_LENGTH_IN_PIXELS;
			   }
			   else{
				   pod_vert.pod_r.y=pod_main.pod_r.y-SHIP_BOUNDARY_DIST*2*UNIT_LENGTH_IN_PIXELS;
				   pod_horzvert.pod_r.y=pod_main.pod_r.y-SHIP_BOUNDARY_DIST*2*UNIT_LENGTH_IN_PIXELS;
			   }
			   
			   pod_main.update_shields();
			   pod_horz.update_shields();
			   pod_vert.update_shields();
			   pod_horzvert.update_shields();
		   
		   
		   Iterator<Dot> iter = dots.iterator();
		   
		   Iterator<Kaboom> iterk = explosions.iterator();
		      while(iterk.hasNext()) {
		    	  Kaboom boom = iterk.next();
		    	  if(total_time - boom.birthtime > 0.25) iterk.remove();
		      }
		   
		  while(iter.hasNext()) {
		     Dot dot = iter.next();
		     dot.update_location(total_time);
		     //if((dot.rect.x+dot.rect.width/2)>360 || (dot.rect.x+dot.rect.width/2)<-40 || (dot.rect.y+dot.rect.height/2)>420 || (dot.rect.y+dot.rect.height/2)<60) iter.remove();
		     //if(Rectangle_collides_with_Polygon(dot.rect,pod_poly) && !HAVE_WE_EXPLODED && dot.rect.overlaps(realityBox)){
		     if(!HAVE_WE_EXPLODED && realityBox.overlaps(dot.rect)){
		    	 Intersector.intersectRectangles(realityBox, dot.rect, cotestrec);
		    	 if (shields>=4){
		    		 if ((pod_main.shield_four_r.overlaps(cotestrec)|| pod_horz.shield_four_r.overlaps(cotestrec) || pod_vert.shield_four_r.overlaps(cotestrec) ||pod_horzvert.shield_four_r.overlaps(cotestrec))){
		    			 spawnExplosion(dot.rect.x+dot.rect.width/2,dot.rect.y+dot.rect.height/2);
				    	 shields-=1;
				    	 hitshield.play(0.1f);
				    	 iter.remove();
		    		 }
		    	 }
		    	 else if (shields>=3){
		    		 if ((pod_main.shield_three_r.overlaps(cotestrec)|| pod_horz.shield_three_r.overlaps(cotestrec) || pod_vert.shield_three_r.overlaps(cotestrec) ||pod_horzvert.shield_three_r.overlaps(cotestrec))){
		    			 spawnExplosion(dot.rect.x+dot.rect.width/2,dot.rect.y+dot.rect.height/2);
				    	 shields-=1;
				    	 hitshield.play(0.1f);
				    	 iter.remove();
		    		 }
		    	 }
		    	 else if (shields>=2){
		    		 if ((pod_main.shield_two_r.overlaps(cotestrec)|| pod_horz.shield_two_r.overlaps(cotestrec) || pod_vert.shield_two_r.overlaps(cotestrec) ||pod_horzvert.shield_two_r.overlaps(cotestrec))){
		    			 spawnExplosion(dot.rect.x+dot.rect.width/2,dot.rect.y+dot.rect.height/2);
				    	 shields-=1;
				    	 hitshield.play(0.1f);
				    	 iter.remove();
		    		 }
		    	 }
		    	 else if (shields>=1){
		    		 if ((pod_main.shield_one_r.overlaps(cotestrec)|| pod_horz.shield_one_r.overlaps(cotestrec) || pod_vert.shield_one_r.overlaps(cotestrec) ||pod_horzvert.shield_one_r.overlaps(cotestrec))){
		    			 spawnExplosion(dot.rect.x+dot.rect.width/2,dot.rect.y+dot.rect.height/2);
				    	 shields-=1;
				    	 hitshield.play(0.1f);
				    	 iter.remove();
		    		 }
		    	 }
		    	 else if ((pod_main.pod_r.overlaps(cotestrec)|| pod_horz.pod_r.overlaps(cotestrec) || pod_vert.pod_r.overlaps(cotestrec) ||pod_horzvert.pod_r.overlaps(cotestrec))){
			    	 spawnExplosion(dot.rect.x+dot.rect.width/2,dot.rect.y+dot.rect.height/2);
			    	 //for (Pod pod: pods){
			    	//	 spawnExplosion(pod.pod_r.x+pod.pod_r.width/2,pod.pod_r.y+pod.pod_r.height/2);
			    	 //}
			    	 hitshield.play(0.1f);
			    	 iter.remove();
			    	 HAVE_WE_EXPLODED=true;
		    	 }
		     }
		  }
		  
		   
	   }
	   @Override
	   
	   //---END THE WORLD RESPONSIBLY---
	   
	   //(Still need to do this properly, but leaving most of the images etc
	   //running doesn't appear to be causing any problems yet.)
	   public void dispose() {
		   
	      // dispose of all the native resources
		   pod_t.dispose();

	   }

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		//viewport.update(width, height);
	    //camera.update();
		float scale=0f;
		if (width>=160 && height>=240){
			scale=0.5f;
		}
		if (width>=320 && height>=480){
			scale=1f;
		}
		while (width>=(320*(scale+1)) && height>=(480*(scale+1))){
			scale+=1f;
		}
		System.out.println("Target scale is: "+ scale);
		
		camera.setToOrtho(false, (float)width/(float)scale, (float)height/(float)scale);
		camera.translate(-((float)width/(float)scale-320)/2, -((float)height/(float)scale-480)/2);
		//camera.update();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	}