
import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

public class MenuScreenRestablecerMusica extends BaseScreen {
	static float nivelMusica = 0.44f; 
	static float nivelEfectos = 0.66f; 
	@Override
	public void initialize() {
		Toolkit t = Toolkit.getDefaultToolkit();
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	System.out.println("Tu resolución es de " + screenSize.width + "x" + screenSize.height);
    	
		BaseActor menu = new BaseActor(0, 0, mainStage);
		menu.loadTexture("assets/sample.png");
		menu.setSize(screenSize.width,screenSize.height);;
		/*	Graphics.DisplayMode mode = Gdx.graphics.getDisplayMode();
		Gdx.graphics.setFullscreenMode(mode);*/
		
		Skin style = new Skin(Gdx.files.internal("assets/ui/pixthulhu/pixthulhu-ui.json"));
		
		Label titulo = new Label("¿Deseas restablecer los valores \nde musica y efectos?",style);
	
		
		TextButton si = new TextButton("Si",style);
		si.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				MenuScreenMusica.sliderMusica.setValue(4);
				MenuScreenMusica.sliderEfectos.setValue(6);
				ColourBand.setActiveScreen( new MenuScreenMusica() );
				click.play(0.4f);
			}
		});
		
		TextButton no = new TextButton("No",style);
		no.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				ColourBand.setActiveScreen( new MenuScreenMusica() );
				click.play(0.4f);
			}
		});
		uiTable.add(titulo).center().width(700).height(220).pad(5);
		uiTable.row();
		uiTable.add(si).center().width(400).height(100).pad(5);
		uiTable.row();
		uiTable.add(no).center().width(400).height(100).pad(5);
		uiTable.row();

	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
	}	
}
