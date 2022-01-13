
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;

public class Player extends BaseActor {
	
    private Animation<TextureRegion> stand;
    private Animation<TextureRegion> walk;
    private Animation<TextureRegion> jump;
    
    String arriba;
    String abajo;
    String derecha;
    String izquierda;
    String disparar;
    String saltar;
    String correr;
    
    private float walkAcceleration;
    private float walkDeceleration;
    private float maxHorizontalSpeed;
    float gravity;
    private float maxVerticalSpeed;
    float facingAngle;
    int opcion = 0;
    
    protected float jumpSpeed;
    public BaseActor belowSensor;

    public Player(float x, float y, Stage s){
        super(x,y,s);
        
        if(MenuScreenPersonaje.getOpcion() != 0) {
        	opcion = MenuScreenPersonaje.getOpcion();
        }else if(MenuScreenPersonajesNiveles.getOpcion() != 0) {
        	opcion = MenuScreenPersonajesNiveles.getOpcion();
        }else {
        	opcion = MenuScreenPersonajesDemo.getOpcion();
        }
        
        stand = loadTexture( "assets/player"+opcion+"/"+opcion+"stand.png" );

        String[] walkFileNames = 
            {"assets/player"+opcion+"/"+opcion+"walk1.png", "assets/player"+opcion+"/"+opcion+"walk2.png"};

        walk = loadAnimationFromFiles(walkFileNames, 0.2f, true);

        maxHorizontalSpeed = 250;
        walkAcceleration = 450;
        walkDeceleration = 550;
        gravity = 650;
        maxVerticalSpeed = 1000;

        setBoundaryPolygon(8);

        jump = loadTexture( "assets/player"+opcion+"/"+opcion+"jump.png"  );        
        jumpSpeed = 650;

        belowSensor = new BaseActor(0,0, s);
        belowSensor.loadTexture("assets/white.png");
        belowSensor.setSize( this.getWidth() - 30, 8 );
        belowSensor.setBoundaryRectangle();
        belowSensor.setVisible(false);
    }

    public void act(float dt)  {
        super.act( dt );
        

        if (Gdx.input.isKeyPressed(Keys.A) || (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) && Gdx.input.isKeyPressed(Keys.A ))) {
            accelerationVec.add( -walkAcceleration, 1 );
            facingAngle = 180;
        }
        if (Gdx.input.isKeyPressed(Keys.D) || (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) && Gdx.input.isKeyPressed(Keys.D ))) {
            accelerationVec.add( walkAcceleration, 0 );
            facingAngle = 0;
    	}
        if(Gdx.input.isKeyPressed(Keys.S) || (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) && Gdx.input.isKeyPressed(Keys.S ))) {
        	facingAngle = 270;
        }
        if(Gdx.input.isKeyPressed(Keys.W) || (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) && Gdx.input.isKeyPressed(Keys.W ))) {
        	facingAngle = 90;
        }

        if ( !Gdx.input.isKeyPressed(Keys.D)
        && !Gdx.input.isKeyPressed(Keys.A)  ) {
        	
            float decelerationAmount = walkDeceleration * dt;

            float walkDirection;

            if ( velocityVec.x > 0 ) {
                walkDirection = 1;
            }
            else {
                walkDirection = -1;
            }
            float walkSpeed = Math.abs( velocityVec.x );

            walkSpeed -= decelerationAmount;

            if (walkSpeed < 0)
                walkSpeed = 0;

            velocityVec.x = walkSpeed * walkDirection;
        }
        if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
        	maxHorizontalSpeed = 350;
        }
        if(!Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
        	maxHorizontalSpeed = 250;
        }

        accelerationVec.add(0, -gravity);

        velocityVec.add( accelerationVec.x * dt, accelerationVec.y * dt );

        velocityVec.x = MathUtils.clamp( velocityVec.x, 
            -maxHorizontalSpeed, maxHorizontalSpeed );

        moveBy( velocityVec.x * dt, velocityVec.y * dt );

        accelerationVec.set(0,0);

        belowSensor.setPosition( getX() +15, getY() - 8 );

        if ( this.isOnSolid() ) {
            belowSensor.setColor( Color.GREEN );
            if ( velocityVec.x == 0 )
                setAnimation(stand);
            else
                setAnimation(walk);
        }
        else{
            belowSensor.setColor( Color.RED );
            setAnimation(jump);
        }

        if ( velocityVec.x > 0 )
            setScaleX(1);

        if ( velocityVec.x < 0 ) 
            setScaleX(-1);

        alignCamera();
        boundToWorld();
    }

    public boolean belowOverlaps(BaseActor actor) {
        return belowSensor.overlaps(actor);
    }

    public boolean isOnSolid()  {
        for (BaseActor actor : BaseActor.getList( getStage(), "Solid" )) {
        	
            Solid solid = (Solid)actor;
            if ( belowOverlaps(solid) && solid.isEnabled() )
                return true;
        }   

        return false;
    }

    public void jump(){
    	
        velocityVec.y = jumpSpeed;
    }

    public boolean isFalling(){
    	
        return (velocityVec.y < 0);
    }

    public void spring(){
    	
        velocityVec.y = 1.5f * jumpSpeed;
    }    

    public void JumpBoostOn() {
    	jumpSpeed = 1000;
    }
    
    public void JumpBoostOff() {
    	jumpSpeed = 650;
    }

	public boolean isJumping() {
		
        return (velocityVec.y > 0); 
    }
	public float getFacingAngle(){
        return facingAngle;
    }

}