import com.badlogic.gdx.scenes.scene2d.Stage;

public class Abyss extends Solid {

    public Abyss(float x, float y, Stage s){
        super(x,y, 32,32, s);
        setEnabled(false);
    }
}
