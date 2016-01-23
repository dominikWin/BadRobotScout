package com.dchs.scout;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class SimplerTouch extends ApplicationAdapter implements InputProcessor {
    // we will use 32px/unit in world
    public final static float SCALE = 32f;
    public final static float INV_SCALE = 1.f/SCALE;
    // this is our "target" resolution, not that the window can be any size, it is not bound to this one
    public final static float VP_WIDTH = 1280 * INV_SCALE;
    public final static float VP_HEIGHT = 720 * INV_SCALE;
    
    private OrthographicCamera camera;
    private ExtendViewport viewport;        
    private ShapeRenderer shapes;
	//public static ArrayList<Vector2> touchPos = new ArrayList<Vector2>(); 
	public static ArrayList<Vector2> touchPosUp = new ArrayList<Vector2>(); 
	public static ArrayList<Vector2> touchPosDown = new ArrayList<Vector2>(); 
	public static ArrayList<ArrayList<Vector2>> touchPosXY = new ArrayList<ArrayList<Vector2>>();
    
    public SimplerTouch() {
        camera = new OrthographicCamera();
        // pick a viewport that suits your thing, ExtendViewport is a good start
        viewport = new ExtendViewport(VP_WIDTH, VP_HEIGHT, camera);
        // ShapeRenderer so we can see our touch point
        shapes = new ShapeRenderer();
        Gdx.input.setInputProcessor(this);
    }

    @Override public void render () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapes.setColor(Color.BLACK);
        shapes.rect(100,100,100,100);
       // shapes.setProjectionMatrix(camera.combined);
    }

    Vector3 tp = new Vector3();
    public static boolean dragging;
    
    @Override public boolean mouseMoved (int screenX, int screenY) {
        // we can also handle mouse movement without anything pressed
//      camera.unproject(tp.set(screenX, screenY, 0));
        return false;
    }

    @Override public boolean touchDown (int screenX1, int screenY1, int pointer, int button) {
        // ignore if its not left mouse button or first touch pointer
        if (button != Input.Buttons.LEFT || pointer > 0) return false;
        camera.unproject(tp.set(screenX1, screenY1, 0));
        dragging = true;
       // touchPosDown.add(new Vector2(screenX1, screenY1));
        Texture img = Scout.img;
        Circle x = new Circle(screenX1, Gdx.graphics.getHeight()-screenY1, 120);
        Circle y = new Circle(img.getWidth()*2+40, img.getHeight()-150, 60);
        Circle a = new Circle(img.getWidth()*2+75, img.getHeight()-220, 60);
        if (x.contains(y) || y.contains(x))
        {
        	shapes.setColor(Color.BLUE);
        	System.out.println("Blue");
        }
        else if (x.contains(a) || a.contains(x))
        {
        	shapes.setColor(Color.RED);
        	System.out.println("Red");
        }
     
        //System.out.println(Scout.circle.hasActions());
        
        return true;
    }
    
    public static ArrayList<Vector2> touchPos = new ArrayList<Vector2>();
    
    @Override public boolean touchDragged (int screenX1, int screenY1, int pointer) {
        if (!dragging) return false;
        if(screenX1 <= Scout.img.getWidth()*2)
        	touchPos.add(new Vector2(screenX1, Gdx.graphics.getHeight()-screenY1));
        camera.unproject(tp.set(screenX1, screenY1, 0));
        return true;
    }

    @Override public boolean touchUp (int screenX1, int screenY1, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) return false;
        camera.unproject(tp.set(screenX1, screenY1, 0));
        dragging = false;
        ArrayList<Vector2> touchPos2 = new ArrayList<Vector2>();
        touchPos2 = touchPos;
        touchPosXY.add(touchPos2);
		Scout.col.add(shapes.getColor());
		Scout.tes.add(new ShapeRenderer());

        //touchPosUp.add(new Vector2(screenX1, screenY1));
        return true;
    }

    @Override public void resize (int width, int height) {
        // viewport must be updated for it to work properly
        viewport.update(width, height, true);
    }

    @Override public void dispose () {
        // disposable stuff must be disposed
        shapes.dispose();
    }

    @Override public boolean keyDown (int keycode) {
        return false;
    }

    @Override public boolean keyUp (int keycode) {
        return false;
    }

    @Override public boolean keyTyped (char character) {
        return false;
    }

    @Override public boolean scrolled (int amount) {
        return false;
    }
}
