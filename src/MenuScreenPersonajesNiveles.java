
import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreenPersonajesNiveles extends BaseScreen{

	static int opcionColor = 0;
	@Override
	public void initialize() {
		Toolkit t = Toolkit.getDefaultToolkit();
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	System.out.println("Tu resolución es de " + screenSize.width + "x" + screenSize.height);
    	
		BaseActor menu = new BaseActor(0, 0, mainStage);
		menu.loadTexture("assets/sample.png");
		menu.setSize(screenSize.width,screenSize.height);
		
		Skin style = new Skin(Gdx.files.internal("assets/ui/pixthulhu/pixthulhu-ui.json"));
		
		Label titulo = new Label("Selecciona Un Personaje",style);
		uiTable.add(titulo).center().width(250).height(120).pad(5);
		uiTable.row();
		
		TextButton button1 = new TextButton("Verde", style);
		button1.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				opcionColor = 1;
				ColourBand.setActiveScreen( new MenuScreenNiveles());
				click.play(0.4f);
			}
		});
		
		
		
		TextButton boton2 = new TextButton("Azul",style);
		boton2.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				opcionColor = 2;		
				ColourBand.setActiveScreen( new MenuScreenNiveles());
				click.play(0.4f);
			}
		});
		
		
		
		TextButton button3 = new TextButton("Beige", style);
		button3.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				opcionColor = 3;
				ColourBand.setActiveScreen( new MenuScreenNiveles());
				click.play(0.4f);
			}
		});
		
		
		
		TextButton boton4 = new TextButton("Rosa",style);
		boton4.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				opcionColor = 4;
				ColourBand.setActiveScreen( new MenuScreenNiveles());
				click.play(0.4f);
			}
		});
		
		
		TextButton boton5 = new TextButton("Amarillo", style);
		boton5.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				opcionColor = 5;
				ColourBand.setActiveScreen( new MenuScreenNiveles() );
				click.play(0.4f);
			}
		});
		
		
		TextButton volver = new TextButton("Volver", style);
		volver.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				ColourBand.setActiveScreen( new MenuScreen() );
				click.play(0.4f);
				MenuScreen.musicPause();
			}
		});
		
		uiTable.add(button1).center().width(250).height(120).pad(5);
		uiTable.add(boton2).center().width(250).height(120).pad(5);
		uiTable.row();
		uiTable.add(button3).center().width(250).height(120).pad(5);
		uiTable.add(boton4).center().width(250).height(120).pad(5);
		uiTable.row();
		uiTable.add(boton5).center().width(250).height(120).pad(5);
		uiTable.row();
		uiTable.add().center().width(250).height(120).pad(5);
		uiTable.row();
		uiTable.add(volver).center().width(250).height(120).pad(5);
		
	}

	public static int getOpcion() {
		int opcion = opcionColor;
		return opcion;
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}

}
