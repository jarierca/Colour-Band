
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Enemy1Solid extends BaseActor{
	
	public Enemy1Solid(float x, float y, float width, float height, Stage s){
        super(x,y,s);
        setSize(width, height);
        setBoundaryRectangle();
    }
}
