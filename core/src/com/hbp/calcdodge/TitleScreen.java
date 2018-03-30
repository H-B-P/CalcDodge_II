package com.hbp.calcdodge;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.calcdodge.CalcDodge;

public class TitleScreen extends MetaScreen {
	
	final CalcDodge game;
	
	private Rectangle CAMPAIGN_r;
	private Texture CAMPAIGN_t;
	
	private Rectangle BONUS_r;
	private Texture BONUS_t;
	
	private Rectangle OPTIONS_r;
	private Texture OPTIONS_t;
	
	private Texture PROBDEF_t;
	
	private Texture TRIM_t;

	
	private BitmapFont font;
	
	private SpriteBatch batch;
	
	public TitleScreen(final CalcDodge gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		System.out.print(camera);
		
		
		bgm=Gdx.audio.newMusic(Gdx.files.internal("Menu.mp3"));
		bgm.setLooping(true);
		bgm.setVolume(option_music_volume);
		bgm.play();
		
		
		
		
		
		CAMPAIGN_r = new Rectangle();
		CAMPAIGN_r.x=170;
		CAMPAIGN_r.y=300;
		CAMPAIGN_r.height=60;
		CAMPAIGN_r.width=140;
		CAMPAIGN_t = new Texture(Gdx.files.internal("abutton_campaign.png"));
		
		
		
		
		
		
		
		
		OPTIONS_r = new Rectangle();
		OPTIONS_r.x=10;
		OPTIONS_r.y=120;
		OPTIONS_r.height=60;
		OPTIONS_r.width=140;
		OPTIONS_t = new Texture(Gdx.files.internal("abutton_options.png"));
		
		
		if (hardcoded_opt_packagename.equals("Combination")){
			PROBDEF_t=new Texture(Gdx.files.internal("PROBDEF_Combination.png"));
		}
		else if (hardcoded_opt_packagename.equals("Bayesian")){
			PROBDEF_t=new Texture(Gdx.files.internal("PROBDEF_Bayesian.png"));
		}
		else{
			PROBDEF_t=new Texture(Gdx.files.internal("PROBDEF.png"));
		}
		
		
		
		TRIM_t = new Texture(Gdx.files.internal("abutton_trim_boring.png"));
		
		game = gam;
		
		batch= new SpriteBatch();
		
		font = new BitmapFont();
		
		
	}
	
	@Override
	public void render(float delta) {
		
		meta_render();
		
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		batch.setProjectionMatrix(camera.combined);
		
		
		
	    font.setColor(Color.BLACK);
		
	    batch.draw(PROBDEF_t, 40,405);
	    
		batch.draw(CAMPAIGN_t, CAMPAIGN_r.x, CAMPAIGN_r.y);
		
		batch.draw(OPTIONS_t, OPTIONS_r.x, OPTIONS_r.y);
		
		if (CAMPAIGN_r.contains(tp_x,tp_y)){
			batch.draw(TRIM_t, CAMPAIGN_r.x, CAMPAIGN_r.y);
			blackfont.draw(batch, "Play through a series of levels which build on the premise.\nYou should probably try this before Library or Arcade.", 10, 83, 300,1, true);
		}
		
		if (OPTIONS_r.contains(tp_x,tp_y)){
			batch.draw(TRIM_t, OPTIONS_r.x, OPTIONS_r.y);
			blackfont.draw(batch,"Adjust the display settings, the audio settings, the game speed, and the accessibility features.", 10, 75, 300,1, true);
		}
		
		
		
		
		batch.draw(poncho_t, -640, -960);
		
		batch.end();
		
		if (Gdx.input.justTouched()) {
				
				//if (LIBRARY_r.contains(tp_x,tp_y)){
				//	game.setScreen(new SelectScreen_Library(game, true));
		        //    dispose();
				//}
				
		}
		
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

	@Override
	public void dispose() {
		bgm.stop();
		bgm.dispose();
		

		
		
		
		PROBDEF_t.dispose();

		TRIM_t.dispose();
		batch.dispose();
	}
}