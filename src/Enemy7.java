
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Enemy7 extends BaseActor{
	public BaseActor belowSensorL;
	public BaseActor belowSensorR;
	
	public Enemy7(float x, float y, Stage s) {
		super(x, y, s);
		String[] walkFileNames = {"assets/enemy7/spider.png", "assets/enemy7/spider_walk1.png", "assets/enemy7/spider_walk2.png"};
		
		loadAnimationFromFiles(walkFileNames, 0.2f, true);
		
		velocityVec.x = (MathUtils.random(60,100));
		
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
		belowSensorR.setPosition( getX() + 100, getY() - 8 );
		
		
		if ( this.isOnSolidL() ) {
			belowSensorL.setColor( Color.GREEN );
        }
        else{
        	belowSensorL.setColor( Color.RED );
            if(velocityVec.x > 0) {
        		velocityVec.x = -(MathUtils.random(60,100));
        		setScaleX(1);
            		
            }else {
            		velocityVec.x = (MathUtils.random(60,100));
            		setScaleX(-1);
            }
        }
		
		if ( this.isOnSolidR() ) {
			belowSensorR.setColor( Color.BLUE );
        }
        else{
        	belowSensorR.setColor( Color.ORANGE );
            if(velocityVec.x > 0) {
        		velocityVec.x = -(MathUtils.random(60,100));
        		setScaleX(1);
            		
            }else {
            		velocityVec.x = (MathUtils.random(60,100));
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
	        if ( belowOverlapsL(solid) && solid.isEnabled() ) {
	        	preventOverlap(solid);
	            return true;
	        }
	    }   
	
	    return false;
	}
	public boolean belowOverlapsR(BaseActor actor) {
	    return belowSensorR.overlaps(actor);
	}
	
	public boolean isOnSolidR()  {
	for (BaseActor actor : BaseActor.getList( getStage(), "Solid" )) {
		
	    Solid solid = (Solid)actor;
	    if ( belowOverlapsR(solid) && solid.isEnabled() ) {
	    	preventOverlap(solid);
	    	return true;
	    }
	}   
	
	return false;
	}
}