package generic.collision;

import gameObject.GameObject;

public interface CollisionListener {
    void onCollisionEnter(GameObject g);
    void onCollisionExit(GameObject g);
}
