
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen extends BaseScreen{
	Properties configuracion = new Properties();
	
	static String es11 = null;static String es12 = null;static String es13 = null;	
	static String es21 = null;static String es22 = null;static String es23 = null;		
	static String es31 = null;static String es32 = null;static String es33 = null;	
	static String es41 = null;static String es42 = null;static String es43 = null;	
	static String esD1 = null;static String esD2 = null;static String esD3 = null;
	
	private Animation<TextureRegion> medallaDRegion;
    private Animation<TextureRegion> medalla1Region;
    private Animation<TextureRegion> medalla2Region;
    private Animation<TextureRegion> medalla3Region;
    private Animation<TextureRegion> medalla4Region; 
	
	BaseActor medallaD;
	BaseActor medalla1;
	BaseActor medalla2;
	BaseActor medalla3;
	BaseActor medalla4;
	
	
		
	@Override
	public void initialize() {
    	menuSound = Gdx.audio.newMusic(Gdx.files.internal("assets/menu.wav"));
		menuSound.setLooping(true);
		menuSound.setVolume(0.4f);
		
		Toolkit t = Toolkit.getDefaultToolkit();
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	System.out.println("Tu resolución es de " + screenSize.width + "x" + screenSize.height);
    	
		BaseActor menu = new BaseActor(0, 0, mainStage);
		menu.loadTexture("assets/sample.png");
		menu.setSize(screenSize.width,screenSize.height);
		
		medallaD = new BaseActor(100, 100, mainStage);
		medallaD.loadTexture("assets/medal/medalD_Fondo1.png");
		medallaDRegion = medallaD.loadTexture("assets/medal/medalD.png");
		
		medalla1 = new BaseActor(150, 100, mainStage);
		medalla1.loadTexture("assets/medal/medal1_Fondo1.png");
		medalla1Region = medalla1.loadTexture("assets/medal/medal1.png");
		
		medalla2 = new BaseActor(250, 100, mainStage);
		medalla2.loadTexture("assets/medal/medal2_Fondo1.png");
		medalla2Region = medalla2.loadTexture("assets/medal/medal2.png");
		
		medalla3 = new BaseActor(200, 100, mainStage);
		medalla3.loadTexture("assets/medal/medal3_Fondo1.png");	
		medalla3Region = medalla3.loadTexture("assets/medal/medal3.png");
		
		medalla4 = new BaseActor(300, 100, mainStage);
		medalla4.loadTexture("assets/medal/medal4_Fondo1.png");
		medalla4Region = medalla4.loadTexture("assets/medal/medal4.png");
				
	//	menuSound.play();
				
		properties();
			
		
		Skin style = new Skin(Gdx.files.internal("assets/ui/pixthulhu/pixthulhu-ui.json"));
	
		Label titulo = new Label("Colour Band",style);
		
		
		TextButton jugar = new TextButton("Jugar", style);
		jugar.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				ColourBand.setActiveScreen(new MenuScreenPersonaje());
				click.play(0.4f);
			}
		});
		
		TextButton niveles = new TextButton("Niveles",style);
		niveles.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				ColourBand.setActiveScreen(new MenuScreenPersonajesNiveles());
				click.play(0.4f);
			}
		});
		
		TextButton demo = new TextButton("Demo",style);
		demo.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				ColourBand.setActiveScreen(new MenuScreenPersonajesDemo());
				click.play(0.4f);
			}
		});
		
		TextButton configuracion = new TextButton("Configuracion",style);
		configuracion.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				ColourBand.setActiveScreen( new MenuScreenConfiguracion() );
				click.play(0.4f);
			}
		});
	
		TextButton creditos = new TextButton("Creditos",style);
		creditos.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				ColourBand.setActiveScreen(new MenuScreenCreditos());
				click.play(0.4f);
			}
		});
		
		TextButton salir = new TextButton("Salir",style);
		salir.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				click.play(0.4f);
				Gdx.app.exit();
			}
		});
		
		
		uiTable.add(titulo).center().width(300).height(120).pad(5);
		uiTable.row();
		uiTable.add(jugar).center().width(400).height(100).pad(5);
		uiTable.row();
		uiTable.add(niveles).center().width(400).height(100).pad(5);
		uiTable.row();
		uiTable.add(demo).center().width(400).height(100).pad(5);
		uiTable.row();
		uiTable.add(configuracion).center().width(400).height(100).pad(5);
		uiTable.row();
		uiTable.add(creditos).center().width(400).height(100).pad(5);
		uiTable.row();
		uiTable.add(salir).center().width(400).height(100).pad(5);
		
	}
	public void properties() {
		leerProperties();
		comprobarEstrellas();
		comprobarPropertiesPintar();
		leerProperties();
		comprobarEstrellas();
	}
	public void comprobarEstrellas() {
	
		if(esD1.equalsIgnoreCase("si") && esD2.equalsIgnoreCase("si") && esD3.equalsIgnoreCase("si")){
			medallaD.setAnimation(medallaDRegion);
		}
		
		if(es11.equalsIgnoreCase("si") && es12.equalsIgnoreCase("si") && es13.equalsIgnoreCase("si")){
			medalla1.setAnimation(medalla1Region);
		}
		
		if(es21.equalsIgnoreCase("si") && es22.equalsIgnoreCase("si") && es23.equalsIgnoreCase("si")){
			medalla2.setAnimation(medalla2Region);
		}
		
		if(es31.equalsIgnoreCase("si") && es32.equalsIgnoreCase("si") && es33.equalsIgnoreCase("si")){
			medalla3.setAnimation(medalla3Region);
		}
		
		if(es41.equalsIgnoreCase("si") && es42.equalsIgnoreCase("si") && es43.equalsIgnoreCase("si")){
			medalla4.setAnimation(medalla4Region);
			
		}
			
	}
	
	
	public static void comprobarPropertiesPintar() {
		Properties configuracion = new Properties();
		
		
		
		if(esD1.equalsIgnoreCase("si") && esD2.equalsIgnoreCase("si") && esD3.equalsIgnoreCase("si")) {
			configuracion.setProperty("Estrella.D.1", "si");	
			configuracion.setProperty("Estrella.D.2", "si");
			configuracion.setProperty("Estrella.D.3", "si");
		}


		if(es11.equalsIgnoreCase("si") && es12.equalsIgnoreCase("si") && es13.equalsIgnoreCase("si")) {
			configuracion.setProperty("Estrella.1.1", "si");
			configuracion.setProperty("Estrella.1.2", "si");
			configuracion.setProperty("Estrella.1.3", "si");
		}
		
		
		if(es21.equalsIgnoreCase("si") && es22.equalsIgnoreCase("si") && es23.equalsIgnoreCase("si")) {
			 configuracion.setProperty("Estrella.2.1", "si");	
			 configuracion.setProperty("Estrella.2.2", "si");
			 configuracion.setProperty("Estrella.2.3", "si");	
		}
		
		
		if(es31.equalsIgnoreCase("si") && es32.equalsIgnoreCase("si") && es33.equalsIgnoreCase("si")) {
			 configuracion.setProperty("Estrella.3.1", "si");
			configuracion.setProperty("Estrella.3.2", "si");
			configuracion.setProperty("Estrella.3.3", "si");
	    }

		if(es41.equalsIgnoreCase("si") && es42.equalsIgnoreCase("si") && es43.equalsIgnoreCase("si")){
		
			 configuracion.setProperty("Estrella.4.1", "si");
			 configuracion.setProperty("Estrella.4.2", "si");
			 configuracion.setProperty("Estrella.4.3", "si");
		}
		  	  
		if(esD1.equalsIgnoreCase("no") || esD2.equalsIgnoreCase("no") || esD3.equalsIgnoreCase("no")) {
			esD1 = LevelScreenDemo.isTieneBigCoin1Demo();
			esD2 = LevelScreenDemo.isTieneBigCoin2Demo();
			esD3 = LevelScreenDemo.isTieneBigCoin3Demo();
			  configuracion.setProperty("Estrella.D.1", esD1);	
			  configuracion.setProperty("Estrella.D.2", esD2);	
			  configuracion.setProperty("Estrella.D.3", esD3);
		}
	     
    	
		if(es11.equalsIgnoreCase("no") || es12.equalsIgnoreCase("no") || es13.equalsIgnoreCase("no")) {
			es11 = LevelScreenRedux.isTieneBigCoin1Demo();
			es12 = LevelScreenRedux.isTieneBigCoin2Demo();
			es13 = LevelScreenRedux.isTieneBigCoin3Demo();
			configuracion.setProperty("Estrella.1.1", es11);	
			configuracion.setProperty("Estrella.1.2", es12);	
			configuracion.setProperty("Estrella.1.3", es13);	
		}
		
		if(es21.equalsIgnoreCase("no") || es22.equalsIgnoreCase("no") || es23.equalsIgnoreCase("no")) {
			es21 = LevelScreenCavern.isTieneBigCoin1Demo();
			es22 = LevelScreenCavern.isTieneBigCoin2Demo();
			es23 = LevelScreenCavern.isTieneBigCoin3Demo();
			  configuracion.setProperty("Estrella.2.1", es21);	
			  configuracion.setProperty("Estrella.2.2", es22);
			  configuracion.setProperty("Estrella.2.3", es23);
		}
		
		if(es31.equalsIgnoreCase("no") || es32.equalsIgnoreCase("no") || es33.equalsIgnoreCase("no")) {
			es31 = LevelScreenIceland.isTieneBigCoin1Demo();
			es32 = LevelScreenIceland.isTieneBigCoin2Demo();
			es33 = LevelScreenIceland.isTieneBigCoin3Demo();
			  configuracion.setProperty("Estrella.3.1", es31);
			  configuracion.setProperty("Estrella.3.2", es32);
			  configuracion.setProperty("Estrella.3.3", es33);
		}
		
		if(es41.equalsIgnoreCase("no") || es42.equalsIgnoreCase("no") || es43.equalsIgnoreCase("no")) {
			es41 = LevelScreenCandyland.isTieneBigCoin1Demo();
			es42 = LevelScreenCandyland.isTieneBigCoin2Demo();
			es43 = LevelScreenCandyland.isTieneBigCoin3Demo();
			  configuracion.setProperty("Estrella.4.1", es41);
			  configuracion.setProperty("Estrella.4.2", es42);
			  configuracion.setProperty("Estrella.4.3", es43);
			  
		}
		
	
		try {
		  configuracion.store(new FileOutputStream("estrellas.conf"),"Fichero de estrellas");
		} catch (FileNotFoundException fnfe ) { 
		  fnfe.printStackTrace(); 
		} catch (IOException ioe) { 
		  ioe.printStackTrace();
		}
	}
	public static void crearProperties() {
		Properties configuracion = new Properties();
		
		es11 = LevelScreenRedux.isTieneBigCoin1Demo();
		es12 = LevelScreenRedux.isTieneBigCoin2Demo();
		es13 = LevelScreenRedux.isTieneBigCoin3Demo();
		
		es21 = LevelScreenCavern.isTieneBigCoin1Demo();
		es22 = LevelScreenCavern.isTieneBigCoin2Demo();
		es23 = LevelScreenCavern.isTieneBigCoin3Demo();
		
		es31 = LevelScreenIceland.isTieneBigCoin1Demo();
		es32 = LevelScreenIceland.isTieneBigCoin2Demo();
		es33 = LevelScreenIceland.isTieneBigCoin3Demo();
		
		es41 = LevelScreenCandyland.isTieneBigCoin1Demo();
		es42 = LevelScreenCandyland.isTieneBigCoin2Demo();
		es43 = LevelScreenCandyland.isTieneBigCoin3Demo();
		
		esD1 = LevelScreenDemo.isTieneBigCoin1Demo();
		esD2 = LevelScreenDemo.isTieneBigCoin2Demo();
		esD3 = LevelScreenDemo.isTieneBigCoin3Demo();
		
		
		configuracion.setProperty("Estrella.D.1", esD1);			
		configuracion.setProperty("Estrella.D.2", esD2);			
		configuracion.setProperty("Estrella.D.3", esD3);
		configuracion.setProperty("Estrella.1.1", es11);	
		configuracion.setProperty("Estrella.1.2", es12);	
		configuracion.setProperty("Estrella.1.3", es13);		
		configuracion.setProperty("Estrella.2.1", es21);	
		configuracion.setProperty("Estrella.2.2", es22);	
		configuracion.setProperty("Estrella.2.3", es23);		
		configuracion.setProperty("Estrella.3.1", es31);		
		configuracion.setProperty("Estrella.3.2", es32);		
		configuracion.setProperty("Estrella.3.3", es33);
		configuracion.setProperty("Estrella.4.1", es41);		
		configuracion.setProperty("Estrella.4.2", es42);		
		configuracion.setProperty("Estrella.4.3", es43);
		
		try {
		  configuracion.store(new FileOutputStream("estrellas.conf"),"Fichero de estrellas");
		} catch (FileNotFoundException fnfe ) { 
		  fnfe.printStackTrace(); 
		} catch (IOException ioe) { 
		  ioe.printStackTrace();
		}
	}
	
	public void leerProperties() {
		Properties configuracion = new Properties();
		try {
		  configuracion.load(new FileInputStream("estrellas.conf"));
		  
		  esD1 = configuracion.getProperty("Estrella.D.1");			
		  esD2 = configuracion.getProperty("Estrella.D.2");			
		  esD3 = configuracion.getProperty("Estrella.D.3");
		  es11 = configuracion.getProperty("Estrella.1.1");	
		  es12 = configuracion.getProperty("Estrella.1.2");	
		  es13 = configuracion.getProperty("Estrella.1.3");		
		  es21 = configuracion.getProperty("Estrella.2.1");	
		  es22 = configuracion.getProperty("Estrella.2.2");	
		  es23 = configuracion.getProperty("Estrella.2.3");		
		  es31 = configuracion.getProperty("Estrella.3.1");		
		  es32 = configuracion.getProperty("Estrella.3.2");		
		  es33 = configuracion.getProperty("Estrella.3.3");
		  es41 = configuracion.getProperty("Estrella.4.1");		
		  es42 = configuracion.getProperty("Estrella.4.2");		
		  es43 = configuracion.getProperty("Estrella.4.3");
		  
		} catch (FileNotFoundException fnfe ) { 
			crearProperties();
		} catch (IOException ioe) { 
		  ioe.printStackTrace();
		}
	}
	public static void musicOff() {
		menuSound.stop();
	}
	public static void musicPause() {
		menuSound.pause();
	}

	@Override
	public void update(float dt) {
	}

}
