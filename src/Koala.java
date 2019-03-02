import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.Animation;

public class Koala extends BaseActor
{
    private Animation stand;
    private Animation walk;

    private float walkAcceleration;
    private float walkDeceleration;
    private float maxHorizontalSpeed;
    private float gravity;
    private float maxVerticalSpeed;

    public Koala(float x, float y, Stage s)
    {
        super(x,y,s);

        stand = loadTexture("assets/koala/stand.png");

        String[] walkFileNames =
                {"assets/koala/walk-1.png", "assets/koala/walk-2.png",
                        "assets/koala/walk-3.png", "assets/koala/walk-2.png"};

        walk = loadAnimationFromFiles(walkFileNames, 0.2f, true);

        maxHorizontalSpeed = 100;
        walkAcceleration   = 200;
        walkDeceleration   = 200;
        gravity            = 700;
        maxVerticalSpeed   = 1000;
    }

    public void act(float dt)
    {
        super.act(dt);

        // get keyboard input
        if (Gdx.input.isKeyPressed(Keys.LEFT))
            accelerationVec.add( -walkAcceleration, 0 );

        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            accelerationVec.add( walkAcceleration, 0 );

        // decelerate when not accelerating
        if ( !Gdx.input.isKeyPressed(Keys.RIGHT)
                && !Gdx.input.isKeyPressed(Keys.LEFT)  )
        {
            float decelerationAmount = walkDeceleration * dt;

            float walkDirection;

            if ( velocityVec.x > 0 )
                walkDirection = 1;
            else
                walkDirection = -1;

            float walkSpeed = Math.abs( velocityVec.x );

            walkSpeed -= decelerationAmount;

            if (walkSpeed < 0)
                walkSpeed = 0;

            velocityVec.x = walkSpeed * walkDirection;
        }

        // apply gravity
        accelerationVec.add(0, -gravity);

        velocityVec.add(accelerationVec.x * dt, accelerationVec.y * dt);

        velocityVec.x = MathUtils.clamp( velocityVec.x, -maxHorizontalSpeed, maxHorizontalSpeed );
        velocityVec.y = MathUtils.clamp(velocityVec.y * dt, -maxVerticalSpeed, maxVerticalSpeed);

        moveBy( velocityVec.x * dt, velocityVec.y * dt );

        // reset acceleration
        accelerationVec.set(0,0);

        // manage animations
        if(velocityVec.x == 0)
            setAnimation(stand);
        else setAnimation(walk);

        if(velocityVec.x > 0)
            setScaleX(1);
        if(velocityVec.x < 0)
            setScaleX(-1);

        alignCamera();
        boundToWorld();
    }
}
