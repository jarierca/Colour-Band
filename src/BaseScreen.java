
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.sun.xml.internal.ws.org.objectweb.asm.Label;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;

public abstract class BaseScreen implements Screen, InputProcessor{
	static Music menuSound;
	Sound click;
    protected static Stage mainStage;
    protected Stage uiStage;
    protected Stage pausaStage;
    
    protected Table uiTable;
    protected Table pauseTable;
    
    boolean juegoEnMarchaDemo ;
    boolean juegoEnMarchaRedux ;
    boolean juegoEnMarchaCavern ;
    boolean juegoEnMarchaIceland ;
    boolean juegoEnMarchaCandy ;
    
    
    public BaseScreen(){

    	
        mainStage = new Stage();
        uiStage = new Stage();
        pausaStage = new Stage();

        uiTable = new Table();
        uiTable.setFillParent(true);
        uiStage.addActor(uiTable);
        
        pauseTable = new Table();
        pauseTable.setFillParent(true);
        pausaStage.addActor(pauseTable);
        
        juegoEnMarchaDemo = false;
        juegoEnMarchaRedux = false;
        juegoEnMarchaCavern = false;
        juegoEnMarchaIceland = false;
        juegoEnMarchaCandy = false;
        
        click = Gdx.audio.newSound(Gdx.files.internal("assets/click.wav"));
          
        initialize();
    }
    public enum State{
        Running, Paused
    }

    public abstract void initialize();

    public abstract void update(float dt);

    static State state = State.Running;
    public void render(float dt) {
    	
    	switch(state){
        case Running:

        	dt = Math.min(dt, 1/30f);
            
            uiStage.act(dt);
            mainStage.act(dt);

            update(dt);

            Gdx.gl.glClearColor(0,0,0,1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            mainStage.draw();
            uiStage.draw();
        	
            break;
        case Paused:     	
        	
        	dt = Math.min(dt, 1/30f);
            
            pausaStage.act(dt);
            
            update(dt);
            
            pausaStage.draw();
        	
            break;
    }
        
    }

    public void resize(int width, int height) {
    	uiStage.getViewport().update(width, height);
    }

    public void pause(){  }

    public void resume(){  }

    public void dispose(){  }

    public void show(){  
        InputMultiplexer im = (InputMultiplexer)Gdx.input.getInputProcessor();
        im.addProcessor(this);
        im.addProcessor(uiStage);
        im.addProcessor(mainStage);
    }

    public void hide() {  
        InputMultiplexer im = (InputMultiplexer)Gdx.input.getInputProcessor();
        im.removeProcessor(this);
        im.removeProcessor(uiStage);
        im.removeProcessor(mainStage);
    }

    public boolean isTouchDownEvent(Event e){
    	
        return (e instanceof InputEvent) && ((InputEvent)e).getType().equals(Type.touchDown);
    }

    public boolean keyDown(int keycode){  return false;  }

    public boolean keyUp(int keycode){  return false;  }

    public boolean keyTyped(char c){  return false;  }

    public boolean mouseMoved(int screenX, int screenY){  return false;  }

    public boolean scrolled(int amount){  return false;  }

    public boolean touchDown(int screenX, int screenY, int pointer, int button){  return false;  }

    public boolean touchDragged(int screenX, int screenY, int pointer){  return false;  }

    public boolean touchUp(int screenX, int screenY, int pointer, int button){  return false;  }
}