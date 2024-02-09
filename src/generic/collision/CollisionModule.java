package generic.collision;

import engine.Engine;
import gameObject.GameObject;
import gameObject.Module;
import tools.Vector2Int;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CollisionModule extends Module {
    ArrayList<CollisionListener> listeners = new ArrayList<>();
    private Rectangle collider;
    private boolean trigger = false;
    private ArrayList<CollisionModule> collisions = new ArrayList<>();
    private Vector2Int previousPos = null;
    private boolean showCollider = false;

    private GameObject colliderO;


    public CollisionModule(Rectangle collider) {
        this.collider = collider;
    }

    public CollisionModule(Rectangle collider, boolean trigger) {
        this.collider = collider;
        this.trigger = trigger;
    }

    @Override
    public void update(float time) {
        if(previousPos == null){
            previousPos = source.getPosition();
        }

        ArrayList<GameObject> objects = GameObject.getAllObjects(new ArrayList<>(), Engine.getInstance().getGameObjects());

        for (GameObject gameObject : objects) {

            if (gameObject != source) {

                Module[] modules = gameObject.findModule(this.getClass());

                ArrayList<CollisionModule> collidingModules = new ArrayList<>();

                for(Module m : modules){
                    if(m.isActive()) collidingModules.add((CollisionModule) m);
                }


                for (CollisionModule collisionModule : collidingModules) {
                    if(detectCollision(collisionModule)){
                        if (!collisions.contains(collisionModule)) {
                            collisions.add(collisionModule);
                            callListenerEnter(gameObject);
                        }

                        if (!collisionModule.isTrigger()) {
                            source.setPosition(previousPos);
                        }
                    } else {
                        collisions.remove(collisionModule);
                        callListenerExit(gameObject);
                    }

                }
            }
        }


        if (showCollider) {
            colliderO.setPosition(source.getPosition());
        }

        previousPos = new Vector2Int(source.getPosition());
    }
    public boolean detectCollision(CollisionModule collisionModule){
        if(!collisionModule.isActive()) return false;
        if (getRealRectangle().intersects(collisionModule.getRealRectangle())) {
            return true;
        }
        return false;
    }

    public Rectangle getRealRectangle() {
        return new Rectangle((int) (source.getX() + (collider.x * source.getScaleX())),
                (int) (source.getY() + (collider.y * source.getScaleY())),
                (int) (collider.width * source.getScaleX()),
                (int) (collider.height * source.getScaleY())
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

    public void showCollider(boolean show) {
        showCollider = show;
        if (show) {
            createColliderObject();
        } else {
            colliderO = null;
        }
    }

    private void createColliderObject() {
        colliderO = new GameObject("collider");

        BufferedImage texture = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = texture.createGraphics();
        Rectangle rectangle = getRealRectangle();
        g2.setColor(Color.red);
        g2.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        g2.dispose();

        colliderO.setTexture(texture);
        colliderO.setLayer(-2);

        source.addChild(colliderO);
    }

    @Override
    public void start() {
    }

    public void callListenerEnter(GameObject g) {
        for (CollisionListener listener : listeners) {
            listener.onCollisionEnter(g);
        }
    }

    public void callListenerExit(GameObject g) {
        for (CollisionListener listener : listeners) {
            listener.onCollisionExit(g);
        }
    }

    public void addCollisionListener(CollisionListener listener) {
        listeners.add(listener);
    }

    public void removeColisionListener(CollisionListener listener) {
        listeners.remove(listener);
    }
}
