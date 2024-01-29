package generic;

import gameObject.Module;

public class GravityModule extends Module {
    private float mass;
    private float gravity;

    public GravityModule(float mass, float gravity) {
        this.mass = mass;
        this.gravity = gravity;
    }

    @Override
    public void update(float time) {
        source.addY((int) (-mass*gravity));
    }

    @Override
    public void start() {

    }
}
