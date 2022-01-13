
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Pincho extends BaseActor{

	public Pincho(float x, float y, float width, float height, Stage s){
        super(x,y,s);
        setSize(width, height);
        setBoundaryRectangle();
    }

}
