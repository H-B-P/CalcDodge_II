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

public class SelectScreen extends MetaScreen {
	
	final CalcDodge game;
	
	private Rectangle L1_r;
	private Texture L1_t;
	
	private Rectangle L2_r;
	private Texture L2_t;
	
	private Rectangle L3_r;
	private Texture L3_t;
	
	private Rectangle L4_r;
	private Texture L4_t;
	
	private Rectangle L5_r;
	private Texture L5_t;
	
	private Rectangle L1a_r;
	private Texture L1a_t;
	
	private Rectangle L2a_r;
	private Texture L2a_t;
	
	private Rectangle L3a_r;
	private Texture L3a_t;
	
	private Rectangle L4a_r;
	private Texture L4a_t;
	
	private Rectangle L5a_r;
	private Texture L5a_t;
	
	private Texture TITLE_t;
	
	private Texture BANNER_CAMPAIGN_t;
	private Texture BANNER_BONUS_t;
	
	private Texture TRIM_t;
	
	private Texture locked_t;
	
	private BitmapFont font;
	
	private SpriteBatch batch;
	
	private int LEVELS_BEAT;
	
	public SelectScreen(final CalcDodge gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		LEVELS_BEAT=prefs.getInteger("LevelsBeat");
		
		System.out.print(camera);
		bgm=Gdx.audio.newMusic(Gdx.files.internal("Menu.mp3"));
		bgm.setLooping(true);
		bgm.setVolume(0.6f);
		bgm.play();
		
		TITLE_t= new Texture(Gdx.files.internal("title.png"));
		
		BANNER_CAMPAIGN_t = new Texture(Gdx.files.internal("banner_camp.png"));
		
		L1_r = new Rectangle();
		L1_r.x=30;
		L1_r.y=280;
		L1_r.height=50;
		L1_r.width=80;
		L1_t = new Texture(Gdx.files.internal("obutton_L1.png"));
		
		L2_r = new Rectangle();
		L2_r.x=120;
		L2_r.y=280;
		L2_r.height=50;
		L2_r.width=80;
		L2_t = new Texture(Gdx.files.internal("obutton_L2.png"));
		
		L3_r = new Rectangle();
		L3_r.x=210;
		L3_r.y=280;
		L3_r.height=50;
		L3_r.width=80;
		L3_t = new Texture(Gdx.files.internal("obutton_L3.png"));
		
		L4_r = new Rectangle();
		L4_r.x=75;
		L4_r.y=220;
		L4_r.height=50;
		L4_r.width=80;
		L4_t = new Texture(Gdx.files.internal("obutton_L4.png"));
		
		L5_r = new Rectangle();
		L5_r.x=165;
		L5_r.y=220;
		L5_r.height=50;
		L5_r.width=80;
		L5_t = new Texture(Gdx.files.internal("obutton_L5.png"));
		
		BANNER_BONUS_t = new Texture(Gdx.files.internal("banner_bonus.png"));
		
		L1a_r = new Rectangle();
		L1a_r.x=30;
		L1a_r.y=90;
		L1a_r.height=50;
		L1a_r.width=80;
		L1a_t = new Texture(Gdx.files.internal("obutton_L1a.png"));
		
		L2a_r = new Rectangle();
		L2a_r.x=120;
		L2a_r.y=90;
		L2a_r.height=50;
		L2a_r.width=80;
		L2a_t = new Texture(Gdx.files.internal("obutton_L2a.png"));
		
		L3a_r = new Rectangle();
		L3a_r.x=210;
		L3a_r.y=90;
		L3a_r.height=50;
		L3a_r.width=80;
		L3a_t = new Texture(Gdx.files.internal("obutton_L3a.png"));
		
		L4a_r = new Rectangle();
		L4a_r.x=75;
		L4a_r.y=30;
		L4a_r.height=50;
		L4a_r.width=80;
		L4a_t = new Texture(Gdx.files.internal("obutton_L4a.png"));
		
		L5a_r = new Rectangle();
		L5a_r.x=165;
		L5a_r.y=30;
		L5a_r.height=50;
		L5a_r.width=80;
		L5a_t = new Texture(Gdx.files.internal("obutton_L5a.png"));
		
		
		
		TRIM_t = new Texture(Gdx.files.internal("obutton_trim.png"));
		
		locked_t= new Texture(Gdx.files.internal("obutton_lock.png"));
		
		game = gam;
		
		batch= new SpriteBatch();
		
		font = new BitmapFont();
		
		
	}
	
	@Override
	public void render(float delta) {
		
		meta_render();
		
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		batch.setProjectionMatrix(camera.combined);
		
		
		
	    font.setColor(Color.BLACK);
	    
	    batch.draw(TITLE_t, 20, 420);
	    
	    batch.draw(BANNER_CAMPAIGN_t, 90, 340);
	    
		batch.draw(L1_t, L1_r.x, L1_r.y);
		batch.draw(L2_t, L2_r.x, L2_r.y);
		batch.draw(L3_t, L3_r.x, L3_r.y);
		batch.draw(L4_t, L4_r.x, L4_r.y);
		batch.draw(L5_t, L5_r.x, L5_r.y);
	    
		batch.draw(BANNER_BONUS_t, 90, 150);
		
		batch.draw(L1a_t, L1a_r.x, L1a_r.y);
		batch.draw(L2a_t, L2a_r.x, L2a_r.y);
		batch.draw(L3a_t, L3a_r.x, L3a_r.y);
		batch.draw(L4a_t, L4a_r.x, L4a_r.y);
		batch.draw(L5a_t, L5a_r.x, L5a_r.y);
		
		if (L1_r.contains(tp_x,tp_y)){
			batch.draw(TRIM_t, L1_r.x, L1_r.y);
		}
		if (L2_r.contains(tp_x,tp_y)){
			batch.draw(TRIM_t, L2_r.x, L2_r.y);
		}
		if (L3_r.contains(tp_x,tp_y)){
			batch.draw(TRIM_t, L3_r.x, L3_r.y);
		}
		if (L4_r.contains(tp_x,tp_y)){
			batch.draw(TRIM_t, L4_r.x, L4_r.y);
		}
		if (L5_r.contains(tp_x,tp_y)){
			batch.draw(TRIM_t, L5_r.x, L5_r.y);
		}
		
		if (L1a_r.contains(tp_x,tp_y)){
			batch.draw(TRIM_t, L1a_r.x, L1a_r.y);
		}
		if (L2a_r.contains(tp_x,tp_y)){
			batch.draw(TRIM_t, L2a_r.x, L2a_r.y);
		}
		if (L3a_r.contains(tp_x,tp_y)){
			batch.draw(TRIM_t, L3a_r.x, L3a_r.y);
		}
		if (L4a_r.contains(tp_x,tp_y)){
			batch.draw(TRIM_t, L4a_r.x, L4a_r.y);
		}
		if (L5a_r.contains(tp_x,tp_y)){
			batch.draw(TRIM_t, L5a_r.x, L5a_r.y);
		}
//		
//		if (OPTIONS_r.contains(tp_x,tp_y)){
//			batch.draw(TRIM_t, OPTIONS_r.x, OPTIONS_r.y);
//			blackfont.draw(batch,"Adjust the display settings, the audio settings, the game speed, and the accessibility features.", 10, 75, 300,1, true);
//		}
		
		if (LEVELS_BEAT<1){
			batch.draw(locked_t, L2_r.x, L2_r.y);
		}
		if (LEVELS_BEAT<2){
			batch.draw(locked_t, L3_r.x, L3_r.y);
		}
		if (LEVELS_BEAT<3){
			batch.draw(locked_t, L4_r.x, L4_r.y);
		}
		if (LEVELS_BEAT<4){
			batch.draw(locked_t, L5_r.x, L5_r.y);
		}
		if (LEVELS_BEAT<5){
			batch.draw(locked_t, L1a_r.x, L1a_r.y);
			batch.draw(locked_t, L2a_r.x, L2a_r.y);
			batch.draw(locked_t, L3a_r.x, L3a_r.y);
			batch.draw(locked_t, L4a_r.x, L4a_r.y);
			batch.draw(locked_t, L5a_r.x, L5a_r.y);
		}
		
		batch.draw(poncho_t, -640, -960);
		
		batch.end();
		
		if (Gdx.input.justTouched()) {
				
			if (L1_r.contains(tp_x,tp_y)){
				game.setScreen(new Level1(game,true,true));
				dispose();
			}
			if (LEVELS_BEAT>=1 && L2_r.contains(tp_x,tp_y)){
				game.setScreen(new Level2(game,true,true));
				dispose();
			}
			if (LEVELS_BEAT>=2 && L3_r.contains(tp_x,tp_y)){
				game.setScreen(new Level3(game,true,true));
				dispose();
			}
			if (LEVELS_BEAT>=3 && L4_r.contains(tp_x,tp_y)){
				game.setScreen(new Level4(game,true,true));
				dispose();
			}
			if (LEVELS_BEAT>=4 && L5_r.contains(tp_x,tp_y)){
				game.setScreen(new Level5(game,true,true));
				dispose();
			}
			if (LEVELS_BEAT>=1){
				if (L1a_r.contains(tp_x,tp_y)){
					game.setScreen(new Level1a(game,true,true));
					dispose();
				}
				if (L2a_r.contains(tp_x,tp_y)){
					game.setScreen(new Level2a(game,true,true));
					dispose();
				}
				if (L3a_r.contains(tp_x,tp_y)){
					game.setScreen(new Level3a(game,true,true));
					dispose();
				}
				if (L4a_r.contains(tp_x,tp_y)){
					game.setScreen(new Level4a(game,true,true));
					dispose();
				}
				if (L5a_r.contains(tp_x,tp_y)){
					game.setScreen(new Level5a(game,true,true));
					dispose();
				}
			}
				
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

		TRIM_t.dispose();
		batch.dispose();
	}
}