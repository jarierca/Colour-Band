
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Lava extends BaseActor{

	public Lava(float x, float y, Stage s) {
		super(x, y, s);
		String[] fileNames = {"assets/lava1.png","assets/lava2.png"};
		loadAnimationFromFiles(fileNames, 0.4f, true);
	}

}
