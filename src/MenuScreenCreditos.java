
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreenCreditos extends BaseScreen {

	@Override
	public void initialize() {
		Toolkit t = Toolkit.getDefaultToolkit();
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	System.out.println("Tu resolución es de " + screenSize.width + "x" + screenSize.height);
    	
		BaseActor menu = new BaseActor(0, 0, mainStage);
		menu.loadTexture("assets/sample.png");
		menu.setSize(screenSize.width,screenSize.height);
		
		Skin style = new Skin(Gdx.files.internal("assets/ui/pixthulhu/pixthulhu-ui.json"));
		
		Label titulo = new Label("Creditos",style);
		uiTable.add(titulo).center().width(250).height(150).pad(5);
		uiTable.row();
		
		TextButton creador = new TextButton("VideoJuego Creado por: \nJavier Aliaga",style);
		uiTable.add(creador).center().width(710).height(150).pad(5);
		uiTable.row();
		
		TextButton sprites = new TextButton("Sprites Creados por: \nKenney (www.kenney.nl)",style);
		sprites.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				try {

		            Desktop.getDesktop().browse(new URI("www.kenney.nl"));

		        } catch (URISyntaxException ex) {
		        	ex.printStackTrace();
		        } catch (IOException e) {
					e.printStackTrace();
				}
				click.play(0.4f);
			}
		});
		uiTable.add(sprites).center().width(710).height(150).pad(5);
		uiTable.row();
		
		TextButton musica = new TextButton("Música Creada por: \nKevin MacLeod \n(https://incompetech.com/) ",style);
		musica.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				try {

		            Desktop.getDesktop().browse(new URI("https://incompetech.com/"));

		        } catch (URISyntaxException ex) {
		        	ex.printStackTrace();
		        } catch (IOException e) {
					e.printStackTrace();
				}
				click.play(0.4f);
			}
		});
		uiTable.add(musica).center().width(710).height(200).pad(5);
		uiTable.row();
		
		TextButton volver = new TextButton("Volver", style);
		volver.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				
				ColourBand.setActiveScreen( new MenuScreen() );
				click.play(0.4f);
				MenuScreen.musicPause();
			}
		});
		uiTable.add().center().width(710).height(100).pad(5);
		uiTable.row();
		uiTable.add(volver).center().width(710).height(100).pad(5);
		uiTable.row();
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}

}
