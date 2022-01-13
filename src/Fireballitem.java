
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Fireballitem extends BaseActor{
	
	public Fireballitem(float x, float y, Stage s){
		super(x,y,s);
		String[] anim = {"assets/items/fireballItem1.png","assets/items/fireballItem2.png"};
		loadAnimationFromFiles(anim, 0.3f, true);
    }
}
