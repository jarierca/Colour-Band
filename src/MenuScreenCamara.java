import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

	
public class MenuScreenCamara extends BaseScreen implements InputProcessor{
	static int resolucionX = 1240;
	static int resolucionY = 720;
	@Override
	public void initialize() {
		Toolkit t = Toolkit.getDefaultToolkit();
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	System.out.println("Tu resolución es de " + screenSize.width + "x" + screenSize.height);
    	
		BaseActor menu = new BaseActor(0, 0, mainStage);
		menu.loadTexture("assets/sample.png");
		menu.setSize(screenSize.width,screenSize.height);
		/*	Graphics.DisplayMode mode = Gdx.graphics.getDisplayMode();
		Gdx.graphics.setFullscreenMode(mode);*/
		
		Skin style = new Skin(Gdx.files.internal("assets/ui/pixthulhu/pixthulhu-ui.json"));
		
		Label titulo = new Label("Graficos",style);
		
		TextButton pantalla1 = new TextButton("Camara 1 (Zoom Normal)", style);
		pantalla1.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				resolucionX = 1240;
		    	resolucionY = 720;
		    	
				click.play(0.4f);
			}
		});
		
		TextButton pantalla2 = new TextButton("Camara 2 (Zoom x2)",style);
		pantalla2.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				resolucionX = 800;
		    	resolucionY = 460;
		    	
				click.play(0.4f);
			}
		});
		
		TextButton pantalla3 = new TextButton("Camara 3 (Zoom x3)",style);
		pantalla3.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				resolucionX = 640;
		    	resolucionY = 480;
		    	
				click.play(0.4f);
			}
		});
		
		TextButton fullScreen = new TextButton("Full Screen",style);
		fullScreen.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				Graphics.DisplayMode mode = Gdx.graphics.getDisplayMode();
				Gdx.graphics.setFullscreenMode(mode);
				MenuScreen.musicPause();
				ColourBand.setActiveScreen( new MenuScreen() );
				click.play(0.4f);
			}
		});
		
		TextButton modoVentana = new TextButton("Modo Ventana",style);
		modoVentana.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				Graphics.DisplayMode mode = Gdx.graphics.getDisplayMode();
				Gdx.graphics.setWindowedMode(screenSize.width,screenSize.height);
				MenuScreen.musicPause();
				ColourBand.setActiveScreen( new MenuScreen() );
				click.play(0.4f);
			}
		});
		
		
		TextButton volver = new TextButton("Volver",style);
		volver.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				ColourBand.setActiveScreen( new MenuScreenConfiguracion() );
				click.play(0.4f);
			}
		});
	
		
		uiTable.add(titulo).center().width(200).height(120).pad(5);
		uiTable.row();
		uiTable.add(pantalla1).center().width(650).height(100).pad(5);
		uiTable.row();
		uiTable.add(pantalla2).center().width(650).height(100).pad(5);
		uiTable.row();
		uiTable.add(pantalla3).center().width(650).height(100).pad(5);
		uiTable.row();
		uiTable.add(fullScreen).center().width(650).height(100).pad(5);
		uiTable.row();
		uiTable.add(modoVentana).center().width(650).height(100).pad(5);
		uiTable.row();
		uiTable.add().center().width(650).height(100).pad(5);
		uiTable.row();
		uiTable.add(volver).center().width(650).height(100).pad(5);
		
	}
	@Override
	public boolean keyTyped(char character) {
		Gdx.input.setInputProcessor(this);
		return false;
	}
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}
	public static int getResolucionX() {
		return resolucionX;
	}
	public static int getResolucionY() {
		return resolucionY;
	}
	

}
