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

import com.hbp.calcdodge.CalcDodge;

public class GameScreen extends MetaScreen {
	
	final CalcDodge game;
	
	OrthographicCamera camera;
	private Texture pod_t;
	private TextureRegion pod_tr;
	private Rectangle pod_r;
	
	private Rectangle pod_r_vert;
	private Rectangle pod_r_horz;
	private Rectangle pod_r_horzvert;
	
	private Texture statusbar_t;
	
	private Texture poncho_t;
	private Texture window_t;
	
	private Texture grid_t;
	
	private Array<Rectangle> pods_r;
	private Polygon pod_poly;
	private Array<Polygon> pods_poly;
	private float pod_x;
	private float pod_y;
	private float pod_ydot;
	private float pod_xdot;
	private float pod_xdotdot;
	private float pod_ydotdot;
	private float pod_xdotdotdot;
	private float pod_ydotdotdot;
	
	private float pod_ydash;
	private float pod_ydashdash;
	private float pod_ydotdash;
	private float pod_ydashdot;
	
	private FitViewport viewport;
	private int GAMESPEED;
	private int ORI_GAMESPEED;
	private String LEVEL;
	private String MODE;
	
	private float input_x;
	private float input_y;
	
	private float effective_delta;
	
	private Array<Dot> dots;
	private Array<Kaboom> explosions;
	
	private Texture dot_t;
	
	private Texture explosion_t;
	
	private int score;
	
	private SpriteBatch batch;
	
	private float total_time;
	private int seconds;
	private int explosionseconds;
	
	private float[] pp_input;
	
	private int UNIT_LENGTH_IN_PIXELS;
	
	private int SHIP_DISPLACEMENT_IN_PIXELS;
	
	private float SHIP_BOUNDARY_DIST;
	
	private boolean HAVE_WE_EXPLODED;
	
	private boolean ANDROID;
	
	private Rectangle realityBox;
	
	private String ypon;
	private BitmapFont font;
	
	private int hits;
	
	private int shields;
	private Rectangle shield_one_r;
	private Rectangle shield_two_r;
	private Rectangle shield_three_r;
	private Rectangle shield_four_r;
	
	private Texture shield_one_t;
	private Texture shield_two_t;
	private Texture shield_three_t;
	private Texture shield_four_t;
	
	private int secondlimit;
	
	private Rectangle cotestrec;
	
	private Preferences prefs;
	
	private int prefs_score;
	
	public GameScreen(final CalcDodge gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		GAMESPEED=100;
		   ORI_GAMESPEED=100;
		   LEVEL="xdotydot";
		   MODE="alt";
		   ANDROID=false;
		 this.game = gam;
	     
		 shields=4;
		 hits=0;
		 
		 if (MODE.equals("wall")){
			 secondlimit=100;
		 }
		 else if (MODE.equals("gen") || MODE.equals("alt")){
			 secondlimit=240;
		 }
		 else{
			 secondlimit=-1;
		 }
		 explosionseconds=0;
		 dots=new Array<Dot>();
		 explosions = new Array<Kaboom>();
		 
		 cotestrec=new Rectangle();
		 
		 dot_t=new Texture(Gdx.files.internal("sniperdot.png"));
		 
		 explosion_t = new Texture(Gdx.files.internal("explosion.png"));
		 
		 ypon="";
		 
		 prefs_score=0;
		 
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
		 pod_ydotdash=0;
		 
		 score=0;
		 
		 statusbar_t=new Texture(Gdx.files.internal("statusbar.png"));
		poncho_t= new Texture(Gdx.files.internal("blackbar_poncho.png"));
		
		window_t=new Texture(Gdx.files.internal("window_normal.png"));
		
			pod_t= new Texture(Gdx.files.internal("cartesian_dodger.png"));
			grid_t= new Texture(Gdx.files.internal("grid_II.png"));
		
			shield_one_t=new Texture(Gdx.files.internal("shield_layer_one.png"));
			shield_two_t=new Texture(Gdx.files.internal("shield_layer_two.png"));
			shield_three_t=new Texture(Gdx.files.internal("shield_layer_three.png"));
			shield_four_t=new Texture(Gdx.files.internal("shield_layer_four.png"));
			
		pod_tr= new TextureRegion(pod_t);
		
		pod_r= new Rectangle();
		pods_r= new Array<Rectangle>();
		pod_r.width=40;
		pod_r.height=40;
		pod_r.x=pod_x*80+160-20;
		pod_r.y=pod_y*80+320-20;

			pod_r_horz= new Rectangle();
			pod_r_vert= new Rectangle();
			pod_r_horzvert= new Rectangle();
			pod_r_horz.width=40;
			pod_r_vert.width=40;
			pod_r_horzvert.width=40;
			pod_r_horz.height=40;
			pod_r_vert.height=40;
			pod_r_horzvert.height=40;
			pod_r_horz.x= 160-20;
			pod_r_vert.x=160-20;
			pod_r_horzvert.x=160-20;
			pod_r_horz.y=320-20;
			pod_r_vert.y=320-20;
			pod_r_horzvert.y=320-20;
			
			pods_r.add(pod_r);
			pods_r.add(pod_r_horz);
			pods_r.add(pod_r_vert);
			pods_r.add(pod_r_horzvert);
		
		realityBox=new Rectangle();
		
		realityBox.width=240;
		realityBox.height=240;
		realityBox.setCenter(160,200);
		
		System.out.println("RB STATS");
		System.out.println(realityBox.x);
		System.out.println(realityBox.width);
		System.out.println(realityBox.height);
		System.out.println(realityBox.x);
		
		pp_input=new float[]{pod_r.x, pod_r.y, pod_r.x+pod_r.width, pod_r.y, pod_r.x+pod_r.width, pod_r.y+pod_r.height, pod_r.x, pod_r.y+pod_r.height};
		pod_poly= new Polygon(pp_input);
		
		pods_poly= new Array<Polygon>();
		
		pods_poly.add(pod_poly);
		
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 320, 480);
		viewport = new FitViewport(320, 480, camera);
	    batch = new SpriteBatch();
	    
	    total_time=0;
	    seconds=0;
	    
	    UNIT_LENGTH_IN_PIXELS=80;
	    
	    HAVE_WE_EXPLODED=false;
	    
	    SHIP_BOUNDARY_DIST=1.5f;
	    
	    font = new BitmapFont();
	    font.setColor(Color.BLACK);
	   }
	   
	   //---FUNCTIONS---
	   
	   private int plusorminus(){
		   int coin=MathUtils.random(0,1);
		   return coin*2-1;
	   }
	   
	   private int tri(){
		   int coin=MathUtils.random(0,2);
		   return coin-1;
	   }
	   
	   private float pent(){
		   int coin=MathUtils.random(0,4);
		   return ((float)coin-2)/2;
	   }
	   
	   private float sept(){
		   int coin=MathUtils.random(0,6);
		   return ((float)coin-3)/2;
	   }
	   

	   
	   //----
	   
	   private void spawnExplosion(float X, float Y){
		   Kaboom boom = new Kaboom();
		   boom.rect= new Rectangle();
		   boom.birthtime=total_time;
		   boom.rect.x= X;
		   boom.rect.y= Y;
		   explosions.add(boom);
	   }
	   
	   //----
	   
	   
	   
	   ///--  --
	   private String double_formatted(double doub){
		   double a=Math.round(doub*10.0)/10.0;
		   Float b=(Float)(float)a;
		   return b.toString();
	   }
	   
	   //---RENDER---
	   @Override
	   public void render(float delta) {
		   
		   
		   
		   effective_delta=delta*((float)GAMESPEED)/100f;
		   
		   total_time+=effective_delta;
		   
		   Gdx.gl.glClearColor(0, 0, 0, 1);
		   Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		   
		   camera.update();
		   
		   batch.begin();
		   
		   if (!HAVE_WE_EXPLODED){
			   batch.draw(pod_tr, pod_r.x-10, pod_r.y-10);
			   if (shields>=1){
				   batch.draw(shield_one_t, pod_r.x-6, pod_r.y-6);
			   }
			   if (shields>=2){
				   batch.draw(shield_two_t, pod_r.x-11, pod_r.y-11);
			   }
			   if (shields>=3){
				   batch.draw(shield_three_t, pod_r.x-16, pod_r.y-16);
			   }
			   if (shields>=4){
				   batch.draw(shield_four_t, pod_r.x-21, pod_r.y-21);
			   }
			   //batch.draw(pod_tr, pod_r.x-10, pod_r.y-10, 30, 30, 60, 60, 1, 1, 0);
			   batch.draw(pod_tr, pod_r_horz.x-10, pod_r_horz.y-10, 30, 30, 60, 60, 1, 1, 0);
			   batch.draw(pod_tr, pod_r_vert.x-10, pod_r_vert.y-10, 30, 30, 60, 60, 1, 1, 0);
			   batch.draw(pod_tr, pod_r_horzvert.x-10, pod_r_horzvert.y-10, 30, 30, 60, 60, 1, 1, 0);
			   
		   }
		   if (HAVE_WE_EXPLODED){
			   for(Kaboom boom: explosions) {
				   batch.draw(explosion_t, boom.rect.x-40, boom.rect.y-40);
			   }
		   }
		   
		   
		   batch.draw(poncho_t, -800+160, -1200+240);
		   batch.draw(window_t, 0, 0);
		   
		   for(Dot dot: dots) {
				   batch.draw(dot_t, dot.rect.x, dot.rect.y);
		   }
		   
		   if (!HAVE_WE_EXPLODED){
			   for(Kaboom boom: explosions) {
				   batch.draw(explosion_t, boom.rect.x-40, boom.rect.y-40);
			   }
		   }
		   
		   //batch.draw(region, x, y, originX, originY, width, height, scaleX, scaleY, rotation)
		   
		   batch.draw(statusbar_t, 0, 400);
		   if (!HAVE_WE_EXPLODED){
				   if (LEVEL.startsWith("xdotdotdot")){
					   font.draw(batch, double_formatted(pod_xdotdotdot), 30, 470);
					   font.draw(batch, double_formatted(pod_xdotdot), 30, 445);
					   font.draw(batch, double_formatted(pod_xdot), 30, 420);
					   if (LEVEL.equals("xdotdotdotydotdotdot")){
						   font.draw(batch, double_formatted(pod_ydotdotdot), 90, 470);
						   font.draw(batch, double_formatted(pod_ydotdot), 90, 445);
						   font.draw(batch, double_formatted(pod_ydot), 90, 420);
					   }
					   else{
						   font.draw(batch, double_formatted(pod_y), 90, 420);
					   }
				   }
				   else{
					   font.draw(batch, double_formatted(pod_x), 30, 420);
					   font.draw(batch, double_formatted(pod_y), 90, 420);
					   if(LEVEL.startsWith("xdot")){
						   font.draw(batch, double_formatted(pod_xdot), 30, 445);
					   }
					   if(LEVEL.startsWith("xdotdot")){
						   font.draw(batch, double_formatted(pod_xdotdot), 30, 470);
					   }
					   if (LEVEL.split("y").length>1){
						   ypon=(LEVEL.split("y"))[1];
						   if(ypon.startsWith("dot")){
							   font.draw(batch, double_formatted(pod_ydot), 90, 445);
						   }
						   if(ypon.startsWith("dash")){
							   font.draw(batch, double_formatted(pod_ydash), 90, 445);
						   }
						   if(ypon.startsWith("dotdot")){
							   font.draw(batch, double_formatted(pod_ydotdot), 90, 470);
						   }
						   if(ypon.startsWith("dashdot")){
							   font.draw(batch, double_formatted(pod_ydashdot), 90, 470);
						   }
						   if(ypon.startsWith("dotdash")){
							   font.draw(batch, double_formatted(pod_ydotdash), 90, 470);
						   }
						   if(ypon.startsWith("dashdash")){
							   font.draw(batch, double_formatted(pod_ydashdash), 90, 470);
						   }
					   }
				   }
		   }
		   
		   font.draw(batch, "Score: "+score, 150, 425);
		   font.draw(batch, "Time: "+(secondlimit-seconds), 150, 445);
		   font.draw(batch, "Hits: "+hits, 150, 465);
		   
		   batch.end();
		   
		   if((seconds+1)<(total_time)){
			   System.out.println(seconds);
			   seconds+=1;
			   if (HAVE_WE_EXPLODED){
				   explosionseconds+=1;
			   }
			   
			   if (seconds==3){
				   dots.add(new Dot(1,5,true));
			   }
			   
			   
		   }
		   if (Gdx.input.isKeyPressed(Keys.ESCAPE) || seconds==secondlimit || explosionseconds>2){
			   
			   while (GAMESPEED>20){
				   GAMESPEED-=10;
				   System.out.println("GAMESPEED IS "+GAMESPEED);
			   }
			   game.setScreen(new TitleScreen(game, true));
			   

			   
			   dispose();
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
		   
		   
			   if (LEVEL=="xdotydot"){
				   pod_xdot=input_x;
				   pod_ydot=input_y;
			   }
			   if (LEVEL=="xy"){
				   pod_x=input_x;
				   pod_y=input_y;
			   }
			   if (LEVEL=="xdotdotydotdot"){
				   pod_xdotdot=input_x;
				   pod_ydotdot=input_y;
			   }
			   if (LEVEL=="xdoty"){
				   pod_xdot=input_x;
				   pod_y=input_y;
			   }
			   if (LEVEL=="xdotdoty"){
				   pod_xdotdot=input_x;
				   pod_y=input_y;
			   }
			   if (LEVEL=="xdotdotydot"){
				   pod_xdotdot=input_x;
				   pod_ydot=input_y;
			   }
			   if (LEVEL=="xdotdotdoty"){
				   pod_xdotdotdot=input_x;
				   pod_y=input_y;
			   }
			   if (LEVEL=="xdotdotdotydotdotdot"){
				   pod_xdotdotdot=input_x;
				   pod_ydotdotdot=input_y;
			   }
			   if (LEVEL=="xdotydash"){
				   pod_xdot=input_x;
				   pod_ydash=input_y;
			   }
			   if (LEVEL=="xdotydashdash"){
				   pod_xdot=input_x;
				   pod_ydashdash=input_y;
			   }
			   if (LEVEL=="xdotydashdot"){
				   pod_xdot=input_x;
				   pod_ydashdot=input_y;
			   }
			   if (LEVEL=="xdotydotdash"){
				   pod_xdot=input_x;
				   pod_ydotdash=input_y;
			   }
		   
		   if (pod_xdotdot>=1){
			   pod_xdotdot=1;
		   }
		   if (pod_xdotdot<=-1){
			   pod_xdotdot=-1;
		   }
		   if (pod_ydotdot>=1){
			   pod_ydotdot=1;
		   }
		   if (pod_ydotdot<=-1){
			   pod_ydotdot=-1;
		   }
		   
		   if (pod_ydash>=1){
			   pod_ydash=1;
		   }
		   if (pod_ydash<=-1){
			   pod_ydash=-1;
		   }
		   
		   if (pod_xdot>=1){
			   pod_xdot=1;
		   }
		   if (pod_xdot<=-1){
			   pod_xdot=-1;
		   }
		   if (pod_ydot>=1){
			   pod_ydot=1;
		   }
		   if (pod_ydot<=-1){
			   pod_ydot=-1;
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
			   
		   
		   pod_r.setCenter(pod_x*UNIT_LENGTH_IN_PIXELS+160, pod_y*UNIT_LENGTH_IN_PIXELS+240);
		   
			   pod_r_horz.y=pod_r.y;
			   pod_r_vert.x=pod_r.x;
			   if (pod_x<0){
				   pod_r_horz.x=pod_r.x+SHIP_BOUNDARY_DIST*2*UNIT_LENGTH_IN_PIXELS;
				   pod_r_horzvert.x=pod_r.x+SHIP_BOUNDARY_DIST*2*UNIT_LENGTH_IN_PIXELS;
			   }
			   else{
				   pod_r_horz.x=pod_r.x-SHIP_BOUNDARY_DIST*2*UNIT_LENGTH_IN_PIXELS;
				   pod_r_horzvert.x=pod_r.x-SHIP_BOUNDARY_DIST*2*UNIT_LENGTH_IN_PIXELS;
			   }
			   if (pod_y<0){
				   pod_r_vert.y=pod_r.y+SHIP_BOUNDARY_DIST*2*UNIT_LENGTH_IN_PIXELS;
				   pod_r_horzvert.y=pod_r.y+SHIP_BOUNDARY_DIST*2*UNIT_LENGTH_IN_PIXELS;
			   }
			   else{
				   pod_r_vert.y=pod_r.y-SHIP_BOUNDARY_DIST*2*UNIT_LENGTH_IN_PIXELS;
				   pod_r_horzvert.y=pod_r.y-SHIP_BOUNDARY_DIST*2*UNIT_LENGTH_IN_PIXELS;
			   }
		   
		   
		   //pp_input=new float[]{-pod_r.width/2, -pod_r.height/2, pod_r.width/2, -pod_r.height/2, pod_r.width/2, pod_r.height/2, -pod_r.width/2, pod_r.height/2};
		   pp_input=new float[]{pod_r.x, pod_r.y, pod_r.x+pod_r.width, pod_r.y, pod_r.x+pod_r.width, pod_r.y+pod_r.height, pod_r.x, pod_r.y+pod_r.height};
		   pod_poly= new Polygon(pp_input);
		   pod_poly.setOrigin(pod_r.x+20,pod_r.y+20);
		   pod_poly.setRotation(0);
		   
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
		    	 if ((pod_r.overlaps(cotestrec)|| pod_r_horz.overlaps(cotestrec) || pod_r_vert.overlaps(cotestrec) ||pod_r_horzvert.overlaps(cotestrec))){
			    	 System.out.println("YES");
			    	 System.out.println(dot.rect.x);
			    	 System.out.println(dot.rect.y);
			    	 spawnExplosion(dot.rect.x+dot.rect.width/2,dot.rect.y+dot.rect.height/2);
			    	 hits+=1;
			    	 iter.remove();
			    	 
			    	//HAVE_WE_EXPLODED=true;
			    	 //Iterator<Rectangle> iterdie = pods_r.iterator();
			    	 //while (iterdie.hasNext()){
			    	//	 Rectangle a_pod=iterdie.next();
			    	//	 spawnExplosion(a_pod.x, a_pod.y);
			    	// }
		    	 }
		     }
		     else{
		    	 //System.out.println("NO");
		     }
		     //System.out.println(dot.rect.x + ", " + dot.rect.y);
		     //System.out.println(pod_poly.getTransformedVertices()[0]+", "+pod_poly.getTransformedVertices()[1]);
		     //System.out.println(pod_poly.getTransformedVertices()[2]+", "+pod_poly.getTransformedVertices()[3]);
		     //System.out.println(pod_poly.getTransformedVertices()[4]+", "+pod_poly.getTransformedVertices()[5]);
		     //System.out.println(pod_poly.getTransformedVertices()[6]+", "+pod_poly.getTransformedVertices()[7]);
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