
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Vida extends BaseActor{
	
    public Vida(float x, float y, Stage s){ 
        super(x+45,y+40,s);
        loadAnimationFromSheet("assets/items/heart2.png", 1, 2, 0.6f, true);
     }    
 }


