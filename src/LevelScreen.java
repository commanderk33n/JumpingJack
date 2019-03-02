import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;

public class LevelScreen extends BaseScreen
{
    Koala jack;

    public void initialize()
    {
        TilemapActor tma = new TilemapActor("assets/map.tmx", mainStage);

        for(MapObject obj : tma.getRectangleList("Solid"))
        {
            MapProperties props = obj.getProperties();
            new Solid((float)props.get("x"), (float)props.get("y"),
                    (float)props.get("width"), (float)props.get("height"), mainStage);
        }

        MapObject startPoint = tma.getRectangleList("start").get(0);
        MapProperties startProps = startPoint.getProperties();
        jack = new Koala( (float)startProps.get("x"), (float)startProps.get("y"), mainStage);

    }

    public void update(float dt)
    {

    }
}