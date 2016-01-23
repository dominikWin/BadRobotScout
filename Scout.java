package com.dchs.scout;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Scout extends ApplicationAdapter {
	SpriteBatch batch, batch2, batch3, batch4, batch5;
	Drawable y;
	public static Texture img = null;
	Texture circ = null;
	SimplerTouch x;
	public static Button circle;
	
	@Override
	public void create () 
	{
		batch = new SpriteBatch();
		batch2 = new SpriteBatch();
		batch3 = new SpriteBatch();
		img = new Texture("../core/src/com/dchs/scout/Background.jpg");
		circ = new Texture("../core/src/com/dchs/scout/rsz_11untitled.png");
		ButtonStyle buttonStyle = new ButtonStyle();
		Skin skin = new Skin();
		//TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("buttons/buttons.pack"));
		Drawable circ2;
		skin.add("circ", img);
		//skin.add("circ2", circ2);
		buttonStyle.up = skin.getDrawable("circ");
		buttonStyle.down = skin.getDrawable("circ");
		buttonStyle.checked = skin.getDrawable("circ");
		SimplerTouch simplerTouch = new SimplerTouch();
		
		circle = new Button(buttonStyle);
		
		Gdx.input.setInputProcessor(simplerTouch);
		
		circle.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor)
			{
				shape2.setColor(Color.BLUE);
			}
		});
		
	}

	
	ShapeRenderer shape;
	public static ShapeRenderer shape2;
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.draw(img, img.getWidth(), 0);
		batch.end();
		
		batch2.begin();
		batch2.setColor(Color.BLUE);
		batch2.draw(circ, img.getWidth()*2 + 10, img.getHeight()-180,60,60);
		batch2.end();
		
		batch3.begin();
		batch3.setColor(Color.RED);
		batch3.draw(circ, img.getWidth()*2 + 45, img.getHeight()-250,60,60);
		batch3.end();
		
		
		shape = new ShapeRenderer();
		shape.begin(ShapeType.Filled);
		shape.setColor(com.badlogic.gdx.graphics.Color.BLUE);
		shape.circle(img.getWidth()*2+40, img.getHeight()-150, 30);
		shape.setColor(com.badlogic.gdx.graphics.Color.RED);
		shape.circle(img.getWidth()*2+75, img.getHeight()-220, 30);
		shape.setColor(com.badlogic.gdx.graphics.Color.GREEN);
		shape.circle(img.getWidth()*2+40, img.getHeight()-290, 30);
		shape.setColor(com.badlogic.gdx.graphics.Color.YELLOW);
		shape.circle(img.getWidth()*2+75, img.getHeight()-360, 30);
		shape.setColor(com.badlogic.gdx.graphics.Color.BROWN);
		shape.circle(img.getWidth()*2+40, img.getHeight()-430, 30);
		shape.setColor(com.badlogic.gdx.graphics.Color.VIOLET);
		shape.circle(img.getWidth()*2+75, img.getHeight()-500, 30);
		shape.end();

		
		
		shape2 = new ShapeRenderer();
		shape2.begin(ShapeType.Filled);
		
//		for(int i = 0; i < SimplerTouch.touchPosDown.size() && i < SimplerTouch.touchPosUp.size(); i++)
	//		shape.line(SimplerTouch.touchPosDown.get(i).x, Gdx.graphics.getHeight()-SimplerTouch.touchPosDown.get(i).y, SimplerTouch.touchPosUp.get(i).x, Gdx.graphics.getHeight()-SimplerTouch.touchPosUp.get(i).y);
		for(int i = 0; i < SimplerTouch.touchPosXY.size(); i++)
				for(int k = 0; k < SimplerTouch.touchPosXY.get(i).size(); k++)
					//if (k!=0){
						//shape.line(SimplerTouch.touchPosDown.get(k), SimplerTouch.touchPosXY.get(i).get(k));
				//	else
						//shape.line(SimplerTouch.touchPosXY.get(i).get(k-1), SimplerTouch.touchPosXY.get(i).get(k));
					{
					ShapeRenderer tes2 = tes.get(i);
					tes2.begin(ShapeType.Filled);
					tes2.setColor(col.get(i));
					//System.out.println(i);
				//	System.out.println(k);
					//System.out.println(col.get(i));

					tes2.rect(SimplerTouch.touchPosXY.get(i).get(k).x, SimplerTouch.touchPosXY.get(i).get(k).y,5,5,col.get(i),col.get(i),col.get(i),col.get(i));
					tes2.end();
					//tes2.dispose();
					}
					//}
		shape2.end();
//		System.out.println(SimplerTouch.touchPosXY.size());
		
		
		
	}

	public static ArrayList<Color> col = new ArrayList<Color>();
	public static ArrayList<ShapeRenderer> tes = new ArrayList<ShapeRenderer>();
	
	@Override
	public void dispose(){
		batch.dispose();
		img.dispose();
		shape.dispose();
	}
}