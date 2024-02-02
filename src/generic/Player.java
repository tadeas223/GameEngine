package generic;

import gameObject.GameObject;
import generic.anim.AnimationLoader;
import generic.anim.AnimationModule;
import generic.collision.CollisionModule;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Player extends GameObject {
    private MovementModule movementModule;
    private CollisionModule collisionModule;
    private AnimationModule animationModule;
    public Player(int speed){
        start(speed);
    }

    public Player (){
        start(0);
        movementModule.setActive(false);
    }

    public void start(int speed){
        try{
            setTexture(ImageIO.read(new File("data/player.png")));
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        movementModule = new MovementModule(speed);
        collisionModule = new CollisionModule(new Rectangle(27,139,64,23));
        animationModule = new AnimationModule(AnimationLoader.loadAnimationController("data/exampleAnimation"));

        addModule(movementModule);
        addModule(collisionModule);
        addModule(animationModule);

//        addModule(new GravityModule(10,1));


        collisionModule.showCollider(true);

        animationModule.startAnimation("idle");
    }

    public void allowMovement(boolean bool){
        movementModule.setActive(bool);
    }

    @Override
    public void update(float time) {
        super.update(time);

        if(movementModule.isActive()){
            if(movementModule.isLeft() || movementModule.isUp()){
                animationModule.startAnimation("walkleft");
            }
            else if(movementModule.isRight() || movementModule.isDown()){
                animationModule.startAnimation("walkright");
            } else{
                animationModule.startAnimation("idle");
            }
        }
    }
}
