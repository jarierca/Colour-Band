
import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreenNiveles extends BaseScreen{

	@Override
	public void initialize() {
		Toolkit t = Toolkit.getDefaultToolkit();
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	System.out.println("Tu resolución es de " + screenSize.width + "x" + screenSize.height);
    	
		BaseActor menu = new BaseActor(0, 0, mainStage);
		menu.loadTexture("assets/sample.png");
		menu.setSize(screenSize.width,screenSize.height);

		Skin style = new Skin(Gdx.files.internal("assets/ui/pixthulhu/pixthulhu-ui.json"));
		
		Label titulo = new Label("Selecciona Un Nivel",style);
		
		
		TextButton nivel1 = new TextButton("Nivel 1 - Redux", style);
		nivel1.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {				
				ColourBand.setActiveScreen( new LevelScreenRedux() );
				MenuScreen.musicOff();
				click.play(0.4f);
			}
		});
				
		TextButton nivel2 = new TextButton("Nivel 2 - Cavern", style);
		nivel2.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {			
				ColourBand.setActiveScreen( new LevelScreenCavern() );
				MenuScreen.musicOff();
				click.play(0.4f);
			}
		});
	
		TextButton nivel3 = new TextButton("Nivel 3 - IceLand", style);
		nivel3.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {		
				ColourBand.setActiveScreen( new LevelScreenIceland() );
				MenuScreen.musicOff();
				click.play(0.4f);
			}
		});
		
		TextButton nivel4 = new TextButton("Nivel 4 - CandyLand", style);
		nivel4.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {		
				ColourBand.setActiveScreen( new LevelScreenCandyland() );
				MenuScreen.musicOff();
				click.play(0.4f);
			}
		});
		
		TextButton volver = new TextButton("Volver", style);
		volver.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				ColourBand.setActiveScreen( new MenuScreenPersonajesNiveles() );
				click.play(0.4f);
			}
		});
		
		uiTable.add(titulo).center().width(550).height(120).pad(5);
		uiTable.row();
		uiTable.add(nivel1).center().width(550).height(120).pad(5);
		uiTable.row();
		uiTable.add(nivel2).center().width(550).height(120).pad(5);
		uiTable.row();
		uiTable.add(nivel3).center().width(550).height(120).pad(5);
		uiTable.row();
		uiTable.add(nivel4).center().width(550).height(120).pad(5);
		uiTable.row();
		uiTable.add().center().width(550).height(120).pad(5);
		uiTable.row();
		uiTable.add(volver).center().width(550).height(120).pad(5);
		uiTable.row();
		
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}

}
