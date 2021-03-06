package com.hbp.calcdodge;

/*~SUMMARY~
 * 
 * The most basic screen. Every other screen is an extension of this.
 * 
 * Its job is to handle the camerawork, take care of options, and play the sound that happens when you switch screens.
 * 
 * All the actual content is left for its children.
 * 
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Preferences;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.audio.Music;
import com.hbp.calcdodge.CalcDodge;

public class MetaScreen implements Screen { //Regarding implementing vs extending, I want all screens to be playable for debugging and education purposes.
	//In other words, if you want to see what MetaScreen or SpaceyScreen do, you can just load them up and see them in action.
	
	final CalcDodge game;
	OrthographicCamera camera;
	
	public Music bgm;
	
	
	private Sound hellosound;
	public Sound arrowsound;
	
	public float tp_x; //These two lines between them give the True Position of the mouse on the screen.
	public float tp_y; //This is as opposed to the position in whatever grid we're using in a level.
						//(Measured in (unscaled) pixels from the bottom left corner of the screen)
	
	public Texture poncho_t;
	
	public BitmapFont blackfont;
	public BitmapFont purplefont;
	public BitmapFont greenfont;
	
	public Preferences prefs;
	
	float option_sfx_volume;
	float option_music_volume;
	String option_screensize;
	boolean option_flicker;
	float option_gamespeed;
	String option_background;
	
	String hardcoded_opt_packagename;
	String hardcoded_opt_platform;
	
	
	
	public MetaScreen(final CalcDodge gam, boolean play_the_sound) {
		
		bgm=Gdx.audio.newMusic(Gdx.files.internal("Goodnightmare.mp3"));
		
		prefs = Gdx.app.getPreferences("dotdash");
		
		//System.out.println(prefs.get());
		
		if (!prefs.contains("LevelsBeat")){
			prefs.putInteger("LevelsBeat", 0);
			prefs.flush();
		}
		
		
		
		update_options();
		
		System.out.println(option_flicker);
		
		game=gam;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 320, 480);
		
		
		set_screensize();
		
		
		blackfont=new BitmapFont(Gdx.files.internal("regular_font/russo.fnt"));
		blackfont.setColor(new Color(0f, 0f, 0f, 1.0f));
		
		arrowsound=Gdx.audio.newSound(Gdx.files.internal("js_sfx/344510__jeremysykes__select03.wav"));
		
		hellosound=Gdx.audio.newSound(Gdx.files.internal("js_sfx/341250__jeremysykes__select01.wav"));
		if (play_the_sound){
			hellosound.play(option_sfx_volume);
		}
		
		poncho_t = new Texture(Gdx.files.internal("blackbar_poncho.png"));
		
	}
	
	void update_options(){
		
		boolean bother_adjusting=false;
		
		if (option_screensize!=null){
			if (!option_screensize.equals(prefs.getString("Screen Size"))){
				bother_adjusting=true;
			}
		}
		
		option_sfx_volume=prefs.getFloat("SFX Volume");
		option_music_volume=prefs.getFloat("Music Volume");
		option_screensize=prefs.getString("Screen Size");
		option_gamespeed=prefs.getFloat("Game Speed");
		option_background=prefs.getString("Background");
		if ((prefs.getString("Flickering")).equals("On")){
			option_flicker=true;
		}
		else{
			option_flicker=false;
		}
		if (bother_adjusting){
			set_screensize();
		}
	}
	
	void set_screensize(){
		if(option_screensize.equals("Small")){
			Gdx.graphics.setWindowedMode(160, 240);
		}
		else if (option_screensize.equals("Normal")){
			Gdx.graphics.setWindowedMode(320, 480);
		}
		else if (option_screensize.equals("Large")){
			Gdx.graphics.setWindowedMode(480, 720);
		}
		else if (option_screensize.equals("Huge")){
			Gdx.graphics.setWindowedMode(640, 960);
		}
		else if (option_screensize.equals("Giant")){
			Gdx.graphics.setWindowedMode(960, 1440);
		}
		else{
			Gdx.graphics.setWindowedMode(320, 480); //At 1:1 scale, the expected screen is 320px wide and 480px tall.
		}
	}
	
	//This function contains the things every screen has to do every step.
	
	public void meta_render() {
		
		camera.update();
		
		Vector3 scr_vec= new Vector3(Gdx.input.getX(), Gdx.input.getY(),0); //Get the position of the player's touch.
		Vector3 irl_vec=camera.unproject(scr_vec); // 'Unproject' the position (scale, translate, etc) to get the mouse position in the game world.
		tp_x=irl_vec.x; //extract the x component of mouse position (in pixels)
		tp_y=irl_vec.y; //extract the y component of mouse position (in pixels)
		
		
	}
	
	
	
	//All children override this render. It's so adorably pathetic.
	
	@Override
	public void render(float delta){
		meta_render();
	}
	
	/*The point of resize is to handle the possibility of a variable screensize.
	 * 
	 *Scaling up to fill an entire screen leads to games looking stretched and messed up.
	 *And scaling by non-integer factors mucks with the artwork.
	 *
	 *So we work out the largest integer scale factor we can use, then place an appropriately-sized screen at the centre of the device.
	 */
	@Override
	public void resize(int width, int height) {
		float scale=0f; //Harsh but fair: if their screen is less than 160px wide or less than 240px tall, give up.
		if (width>=160 && height>=240){
			scale=0.5f; //Special indulgence for scaling down by a factor of two. It actually doesn't look awful, and better impossibly small than literally invisible.
		}
		if (width>=320 && height>=480){
			scale=1f; //"If the screen is screen-sized, size the screen to be the size of the screen."
		}
		if (width>=480 && height>=720){
			scale=1.5f; //Another special indulgence for scaling by a factor of 1.5.
		}
		if (width>=640 && height>=960){
			scale=2f; //Gotta get the scale to an integer value before the next bit.
		}
		while (width>=(320*(scale+1)) && height>=(480*(scale+1))){
			scale+=1f; //For all n, we see if we could scale up by a factor of n and still fit on the device's screen.
		}
		
		System.out.println("Target scale is: "+ scale); //Useful for debugging.
		
		camera.setToOrtho(false, (float)width/(float)scale, (float)height/(float)scale); //Set the screen to the right size.
		camera.translate(-((float)width/(float)scale-320)/2f, -((float)height/(float)scale-480)/2f); //Center the screen.
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
	public void meta_dispose(){
		hellosound.dispose();
		arrowsound.dispose();
		poncho_t.dispose();
	}
	@Override
	public void dispose() {
		meta_dispose();
	}
}
