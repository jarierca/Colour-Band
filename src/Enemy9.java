
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Enemy9 extends BaseActor{

	
	public Enemy9(float x, float y, Stage s) {
		super(x, y, s);
		String[] walkFileNames = {"assets/enemy9/snakeSlime.png", "assets/enemy9/snakeSlime1.png"};
		
		loadAnimationFromFiles(walkFileNames, 0.5f, true);			

	}
	public void act(float dt) {
		super.act(dt);
		
		applyPhysics(dt);
		boundToWorld();
	}

}
