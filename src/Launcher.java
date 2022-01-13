
import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Launcher{
	
    public static void main (String[] args){    	
    	Toolkit t = Toolkit.getDefaultToolkit();
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	System.out.println("Tu resolución es de " + screenSize.width + "x" + screenSize.height);
    	
        Game myGame = new ColourBand(); 
        
     //   LwjglApplication launcher = new LwjglApplication( myGame, "Colour Band", screenSize.width, screenSize.height ); //800, 640
        
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Colour Band";
        cfg.width = screenSize.width;
        cfg.height = screenSize.height;
        cfg.addIcon("assets/icono.png", Files.FileType.Internal);
        
        new LwjglApplication(new ColourBand(), cfg);
       
    }
}