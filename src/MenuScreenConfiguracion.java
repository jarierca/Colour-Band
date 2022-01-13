
import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreenConfiguracion extends BaseScreen{

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
		
		Label titulo = new Label("Configuracion",style);
		
		TextButton jugar = new TextButton("Musica", style);
		jugar.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				ColourBand.setActiveScreen( new MenuScreenMusica() );
				click.play(0.4f);
			}
		});
		
		TextButton graficos = new TextButton("Graficos",style);
		graficos.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				ColourBand.setActiveScreen( new MenuScreenCamara() );
				click.play(0.4f);
			}
		});
		
		TextButton reiniciarMedallas = new TextButton("Reiniciar Medallas",style);
		reiniciarMedallas.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				ColourBand.setActiveScreen( new MenuScreenRestablecerMedallas() );
				click.play(0.4f);
			}
		});
		
		TextButton volver = new TextButton("Volver",style);
		volver.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				ColourBand.setActiveScreen( new MenuScreen() );
				MenuScreen.musicPause();
				click.play(0.4f);
			}
		});
	
		
		uiTable.add(titulo).center().width(350).height(120).pad(5);
		uiTable.row();
		uiTable.row();
		uiTable.add(jugar).center().width(500).height(100).pad(5);
		uiTable.row();
		uiTable.add(graficos).center().width(500).height(100).pad(5);
		uiTable.row();
		uiTable.add(reiniciarMedallas).center().width(500).height(100).pad(5);
		uiTable.row();
		uiTable.add().center().width(400).height(100).pad(5);
		uiTable.row();
		uiTable.add(volver).center().width(500).height(100).pad(5);
		
	}

	@Override
	public void update(float dt) {

	}

}
