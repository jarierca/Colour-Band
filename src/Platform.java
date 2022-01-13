
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Platform extends Solid{
	
    public Platform(float x, float y, Stage s){ 
        super(x,y,128,128,s);
        loadTexture("assets/items/platform.png");
    }
}