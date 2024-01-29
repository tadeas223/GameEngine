package generic.collision;

import engine.Engine;
import gameObject.GameObject;
import gameObject.Module;
import tools.SizeLimitedQueue;
import tools.Vector2Int;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CollisionModule extends Module {
    private Rectangle collider;
    private boolean trigger;

    private Vector2Int previousPos;
    private boolean showCollider = false;

    private GameObject colliderO;


    public CollisionModule(Rectangle collider){
        this.collider = collider;
    }

    public CollisionModule(Rectangle collider, boolean trigger) {
        this.collider = collider;
        this.trigger = trigger;
    }

    @Override
    public void update(float time) {

        for(GameObject parent : Engine.getInstance().getGameObjects()){
            for(GameObject child : parent.getChildren()){

                if(child != source){
                    if(detectCollision(child)){

                        if(!trigger){
                            source.setPosition(previousPos);
                        }
                    }
                }

            }

            if(parent != source){
                if(detectCollision(parent)){
                    if(!trigger){
                        source.setPosition(previousPos);
                    }
                }
            }

        }

        if(showCollider){
            colliderO.setPosition(source.getPosition());
        }

        previousPos = new Vector2Int(source.getPosition());
    }

    public boolean detectCollision(GameObject g){
        CollisionModule collisionModule = (CollisionModule) g.findModule(this.getClass());

        if(collisionModule != null){
            return (getRealRectangle().intersects(collisionModule.getRealRectangle()));
        }
        return false;
    }

    public Rectangle getRealRectangle(){
        return new Rectangle((int) (source.getX()+(collider.x*source.getScaleX())),
                (int) (source.getY()+(collider.y*source.getScaleY())),
                (int) (collider.width * source.getScaleX()),
                (int) (collider.height*source.getScaleY())
        );
    }

    public Rectangle getCollider() {
        return collider;
    }

    public void setCollider(Rectangle collider) {
        this.collider = collider;
    }

    public boolean isTrigger() {
        return trigger;
    }

    public void setTrigger(boolean trigger) {
        this.trigger = trigger;
    }

    public void showCollider(boolean show){
        showCollider = show;
        if(show){
            createColliderObject();
        }
        else{
            colliderO = null;
        }
    }

    private void createColliderObject(){
        colliderO = new GameObject("collider");

        BufferedImage texture = new BufferedImage(source.getWidth(),source.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = texture.createGraphics();
        Rectangle rectangle = getRealRectangle();
        g2.setColor(Color.red);
        g2.fillRect(rectangle.x,rectangle.y,rectangle.width,rectangle.height);
        g2.dispose();

        colliderO.setTexture(texture);

        source.addChild(colliderO);
    }

    @Override
    public void start() {
        previousPos = new Vector2Int(source.getPosition());
    }
}
