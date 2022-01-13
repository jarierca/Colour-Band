
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Iceball extends BaseActor{
	
	public Iceball(float x, float y, Stage s){
		super(x,y,s);
		String[] iceball = {"assets/iceball/iceball3.png","assets/iceball/iceball4.png"};
		
		loadAnimationFromFiles(iceball, 0.3f,true);
        setSpeed(800);
    }
    
    public void act(float dt)
    {
        super.act(dt);
        applyPhysics(dt);
    }
	
}
