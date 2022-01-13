
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Health extends BaseActor {
	public Health(float x, float y, Stage s){ 
	       super(x,y,s);
	       loadAnimationFromSheet("assets/items/heart_full.png.png", 1, 6, 0.1f, true);
	   }    
}

