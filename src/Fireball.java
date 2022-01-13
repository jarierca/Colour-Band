
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Fireball extends BaseActor{
	
	public Fireball(float x, float y, Stage s){
		super(x,y,s);
		String[] fireball = {"assets/items/fireball2.png","assets/items/fireball4.png"};
		
		loadAnimationFromFiles(fireball, 0.3f,true);
        setSpeed(800);
    }
    
    public void act(float dt)
    {
        super.act(dt);
        applyPhysics(dt);
    }
	
}
