
import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreenGameOver extends BaseScreen{
	Music music;
	
	@Override
	public void initialize() {
		music = Gdx.audio.newMusic(Gdx.files.internal("assets/gameover.mp3"));
        music.setLooping(false);
        music.setVolume(0.4f);
        music.play();
		
        Toolkit t = Toolkit.getDefaultToolkit();
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	System.out.println("Tu resolución es de " + screenSize.width + "x" + screenSize.height);
    	
		BaseActor menu = new BaseActor(0, 0, mainStage);
		menu.loadTexture("assets/GameOver.png");
		menu.setSize(screenSize.width,screenSize.height);
		
		Skin style = new Skin(Gdx.files.internal("assets/ui/pixthulhu/pixthulhu-ui.json"));
	
		
		TextButton Menu = new TextButton("Menu", style);
		Menu.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				ColourBand.setActiveScreen( new MenuScreen() );
				click.play(0.4f);
			}
		});
		
		TextButton Salir = new TextButton("Salir",style);
		Salir.addListener(new ClickListener() {
			public void clicked(InputEvent event,float x, float y) {
				Gdx.app.exit();
				click.play(0.4f);
			}
		});
		uiTable.row();
		uiTable.add(Menu).center().width(200).height(100).pad(5);
		uiTable.row();
		uiTable.add(Salir).center().width(200).height(100).pad(5);
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}

}
