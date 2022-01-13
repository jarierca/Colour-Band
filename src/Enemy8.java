
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Enemy8 extends BaseActor{
    public BaseActor belowSensorL;
	public BaseActor belowSensorR;
	
	public Enemy8(float x, float y, Stage s) {
		super(x, y, s);
		String[] walkFileNames = {"assets/enemy8/snake.png", "assets/enemy8/snake_walk.png"};
		
		loadAnimationFromFiles(walkFileNames, 0.4f, true);
		
		velocityVec.x = -(MathUtils.random(20,35));
		
		belowSensorL = new BaseActor(0,0, s);
		belowSensorL.loadTexture("assets/white.png");
		belowSensorL.setSize( 10, 8 );
		belowSensorL.setBoundaryRectangle();
		belowSensorL.setVisible(false);
		
		
		belowSensorR = new BaseActor(0,0, s);
		belowSensorR.loadTexture("assets/white.png");
		belowSensorR.setSize( 10, 8 );
		belowSensorR.setBoundaryRectangle();
		belowSensorR.setVisible(false);
		
	}
	public void act(float dt) {
		super.act(dt);
		
		belowSensorL.setPosition( getX() -4, getY() - 8 );
		belowSensorR.setPosition( getX() + 90, getY() - 8 );
		
		
		if ( this.isOnSolidL() ) {
			belowSensorL.setColor( Color.GREEN );
        }
        else{
        	belowSensorL.setColor( Color.RED );
            if(velocityVec.x > 0) {
        		velocityVec.x = -(MathUtils.random(20,35));
        		setScaleX(1);
            		
            }else {
            		velocityVec.x = (MathUtils.random(20,35));
            		setScaleX(-1);
            }
        }
		
		if ( this.isOnSolidR() ) {
			belowSensorR.setColor( Color.BLUE );
        }
        else{
        	belowSensorR.setColor( Color.ORANGE );
            if(velocityVec.x > 0) {
        		velocityVec.x = -(MathUtils.random(20,35));
        		setScaleX(1);
            		
            }else {
            		velocityVec.x = (MathUtils.random(20,35));
            		setScaleX(-1);
            }
        }
		
		applyPhysics(dt);
		boundToWorld();
	}
	public boolean belowOverlapsL(BaseActor actor) {
        return belowSensorL.overlaps(actor);
    }

	public boolean isOnSolidL()  {
	    for (BaseActor actor : BaseActor.getList( getStage(), "Solid" )) {
	    	
	        Solid solid = (Solid)actor;
	        if ( belowOverlapsL(solid) && solid.isEnabled() )
	            return true;
	    }   
	
	    return false;
	}
	public boolean belowOverlapsR(BaseActor actor) {
	    return belowSensorR.overlaps(actor);
	}
	
	public boolean isOnSolidR()  {
	for (BaseActor actor : BaseActor.getList( getStage(), "Solid" )) {
		
	    Solid solid = (Solid)actor;
	    if ( belowOverlapsR(solid) && solid.isEnabled() )
	        return true;
	}   
	
	return false;
	}

}
