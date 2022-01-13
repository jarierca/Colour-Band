

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Iceballitem extends BaseActor{
	
	public Iceballitem(float x, float y, Stage s){
		super(x,y,s);
		String[] anim = {"assets/iceball/iceball.png","assets/iceball/iceball2.png"};
		loadAnimationFromFiles(anim, 0.3f, true);
    }
}

