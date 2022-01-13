
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;

public class Enemy4 extends BaseActor {
	
    private float maxHorizontalSpeed;
    private float gravity;


    public Enemy4(float x, float y, Stage s){
        super(x,y,s);
                
        loadTexture( "assets/enemy4/saw.png" );

        maxHorizontalSpeed = 300;
        gravity = 650;

        setBoundaryPolygon(8);
  

    }

    public void act(float dt)  {
        super.act( dt );

        accelerationVec.add(0, -gravity);

        velocityVec.add( accelerationVec.x * dt, accelerationVec.y * dt );

        velocityVec.x = MathUtils.clamp( velocityVec.x, 
            -maxHorizontalSpeed, maxHorizontalSpeed );

        moveBy( velocityVec.x * dt, velocityVec.y * dt );

        accelerationVec.set(0,0);
    
        boundToWorld();
    }


}