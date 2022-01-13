
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

public class MenuScreenMusica extends BaseScreen {
	static float nivelMusica = 0.4f; 
	static float nivelEfectos = 0.6f; 
	
	static Slider sliderMusica;
	static Slider sliderEfectos;
	
	Label lbl1;
	Label lbl2;
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
		
		Label titulo = new Label("Musica",style);
		
		Label titulo2 = new Label("Musica de Fondo",style);
		
		
		sliderMusica = new Slider(0, 10, 1,false, style);
		sliderMusica.setValue(nivelMusica * 10);
		lbl1 = new Label(String.valueOf(sliderMusica.getValue()*10+" %"),style);
		sliderMusica.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent arg0, Actor arg1) {
				Gdx.app.log("MUSICA", "slider cambia a: " + sliderMusica.getValue());
				if(sliderMusica.getValue() == 0) {
					nivelMusica = 0.0f;
					sliderMusica.setValue(0);
				}else if(sliderMusica.getValue() == 1) {
					nivelMusica = 0.1f;
					sliderMusica.setValue(1);
				}else if(sliderMusica.getValue() == 2) {
					nivelMusica = 0.2f;
					sliderMusica.setValue(2);
				}else if(sliderMusica.getValue() == 3) {
					nivelMusica = 0.3f;
					sliderMusica.setValue(3);
				}else if(sliderMusica.getValue() == 4) {
					nivelMusica = 0.4f;
					sliderMusica.setValue(4);
				}else if(sliderMusica.getValue() == 5) {
					nivelMusica = 0.5f;
					sliderMusica.setValue(5);
				}else if(sliderMusica.getValue() == 6) {
					nivelMusica = 0.6f;
					sliderMusica.setValue(6);
				}else if(sliderMusica.getValue() == 7) {
					nivelMusica = 0.7f;
					sliderMusica.setValue(7);
				}else if(sliderMusica.getValue() == 8) {
					nivelMusica = 0.8f;
					sliderMusica.setValue(8);
				}else if(sliderMusica.getValue() == 9) {
					nivelMusica = 0.9f;
					sliderMusica.setValue(9);
				}else if(sliderMusica.getValue() == 10) {
					nivelMusica = 1f;
					sliderMusica.setValue(10);
				}
				lbl1.setText(String.valueOf(sliderMusica.getValue()*10+" %"));
				
			}
		});
		
		Label titulo3 = new Label("Efectos de Sonido",style);
		
		
		sliderEfectos = new Slider(0, 10, 1,false, style);
		sliderEfectos.setValue(nivelEfectos * 10);
		lbl2 = new Label(String.valueOf(sliderEfectos.getValue()*10+" %"),style);
		sliderEfectos.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent arg0, Actor arg1) {
				Gdx.app.log("EFECTOS", "slider cambia a: " + sliderEfectos.getValue());
				
				if(sliderEfectos.getValue() == 0) {
					nivelEfectos = 0;
					sliderEfectos.setValue(0);	
				}else if(sliderEfectos.getValue() == 1) {
					nivelEfectos = 0.1f;
					sliderEfectos.setValue(1);
				}else if(sliderEfectos.getValue() == 2) {
					nivelEfectos = 0.2f;
					sliderEfectos.setValue(2);
				}else if(sliderEfectos.getValue() == 3) {
					nivelEfectos = 0.3f;
					sliderEfectos.setValue(3);
				}else if(sliderEfectos.getValue() == 4) {
					nivelEfectos = 0.4f;
					sliderEfectos.setValue(4);
				}else if(sliderEfectos.getValue() == 5) {
					nivelEfectos = 0.5f;
					sliderEfectos.setValue(5);
				}else if(sliderEfectos.getValue() == 6) {
					nivelEfectos = 0.6f;
					sliderEfectos.setValue(6);
				}else if(sliderEfectos.getValue() == 7) {
					nivelEfectos = 0.7f;
					sliderEfectos.setValue(7);
				}else if(sliderEfectos.getValue() == 8) {
					nivelEfectos = 0.8f;
					sliderEfectos.setValue(8);
				}else if(sliderEfectos.getValue() == 9) {
					nivelEfectos = 0.9f;
					sliderEfectos.setValue(9);
				}else if(sliderEfectos.getValue() == 10) {
					nivelEfectos = 1f;
					sliderEfectos.setValue(10);
				}
				lbl2.setText(String.valueOf(sliderEfectos.getValue()*10+" %"));
				
			}
		});
		
		TextButton resetear = new TextButton("Resetear Valores",style);
		resetear.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				ColourBand.setActiveScreen( new MenuScreenRestablecerMusica() );
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
		uiTable.add(titulo).center().width(230).height(100).pad(5);
		uiTable.row();
		uiTable.add(titulo2).center().width(460).height(30).pad(5);
		uiTable.row();
		uiTable.add(sliderMusica).center().width(520).height(50).pad(5);
		uiTable.row();
		uiTable.add(lbl1).center().width(460).height(50).pad(5);
		uiTable.row();
		uiTable.add().center().width(460).height(20).pad(5);
		uiTable.row();
		uiTable.add(titulo3).center().width(460).height(30).pad(5);
		uiTable.row();
		uiTable.add(sliderEfectos).center().width(520).height(50).pad(5);
		uiTable.row();
		uiTable.add(lbl2).center().width(460).height(50).pad(5);
		uiTable.row();
		uiTable.add().center().width(460).height(20).pad(5);
		uiTable.row();
		uiTable.add(resetear).center().width(460).height(100).pad(5);
		uiTable.row();
		uiTable.add().center().width(460).height(50).pad(5);
		uiTable.row();
		uiTable.add(volver).center().width(460).height(100).pad(5);
		uiTable.row();

	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub

	}

	public static float getNivelMusica() {
		float volumenMusica = nivelMusica;
		return volumenMusica;
	}

	public static float getNivelEfectos() {
		float volumenEfectos = nivelEfectos;
		return volumenEfectos;
	}
	
}
