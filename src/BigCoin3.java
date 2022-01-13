
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BigCoin3 extends BaseActor{
	public BigCoin3(float x, float y, Stage s){ 
	       super(x+20,y,s);
	       loadAnimationFromSheet("assets/particleYellow_3.png", 1, 1, 0.1f, true);
	    }    
	}