
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Enemy6 extends BaseActor{
	public Enemy6(float x, float y, Stage s) {
		super(x, y, s);
		String[] walkFileNames = {"assets/enemy6/bat_fly.png", "assets/enemy6/bat.png"};
		
		loadAnimationFromFiles(walkFileNames, 0.2f, true);
		
		setSpeed(MathUtils.random(90,150));
		setMotionAngle( MathUtils.random(0,360) );
	}
	public void act(float dt){
		super.act(dt);
		
		if ( MathUtils.random(1,120) == 1 )
		setMotionAngle( MathUtils.random(0,360) );
		applyPhysics(dt);
		boundToWorld();
	}
}
