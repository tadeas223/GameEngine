package gameObject;

import engine.Updatable;
import tools.Vector2;
import tools.Vector2Int;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * The name of this class is quite explanatory.
 */
public class GameObject implements Updatable, Comparable<GameObject> {
    private GameObject parent = null;

    private String name;
    private int layer = 1;
    private boolean active = true;

    private BufferedImage texture;
    private Vector2Int position = new Vector2Int(0, 0);

    private Vector2 scale = new Vector2(1, 1);

    private ArrayList<Module> modules = new ArrayList<>();
    private ArrayList<GameObject> children = new ArrayList<>();

    /**
     * Constructor for creating the object. Nothing special.
     */
    public GameObject() {
    }

    /**
     * Constructor for creating the object with a specific name.
     *
     * @param name of the object
     */
    public GameObject(String name) {
        this.name = name;
    }


    public Module findModule(Class<? extends Module> moduleClass) {
        for (Module m : modules) {
            if (m.getClass() == moduleClass) {
                return m;
            }
        }
        return null;
    }

    //region Get/Set/Add/Remove

    public GameObject getParent() {
        return parent;
    }

    public void setParent(GameObject parent) {
        this.parent = parent;
    }

    public void unSetParent() {
        parent = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public Vector2Int getPosition() {
        return position;
    }

    public void setPosition(Vector2Int position) {
        this.position = position;
    }

    public int getX() {
        return position.x;
    }

    public void setX(int x) {
        position.x = x;
    }

    public int getY() {
        return position.y;
    }

    public void setY(int y) {
        position.y = y;
    }

    public void setPosition(int x, int y) {
        position.x = x;
        position.y = y;
    }

    public void addPosition(int x, int y) {
        position.x += x;
        position.y += y;
    }

    public void addPosition(Vector2Int position) {
        this.position = Vector2Int.add(this.position, position);
    }

    public void addX(int x) {
        position.x += x;
    }

    public void addY(int y) {
        position.y += y;
    }

    public Vector2 getScale() {
        return scale;
    }

    public void setScale(Vector2 scale) {
        this.scale = scale;
    }

    public float getScaleX() {
        return scale.x;
    }

    public float getScaleY() {
        return scale.y;
    }

    public void setScale(float width, float height) {
        scale.x = width;
        scale.y = height;
    }

    public void addScale(float width, float heigth) {
        scale.x += width;
        scale.y += heigth;
    }

    public void addScale(Vector2 scale) {
        this.scale = Vector2.add(this.scale, scale);
    }

    public void addWidth(float width) {
        scale.x += width;
    }

    public void addHeigth(float heigth) {
        scale.y += heigth;
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
    }

    public void addModule(Module module) {
        module.source = this;
        modules.add(module);
        module.start();
    }

    public void removeModule(Module module) {
        modules.remove(module);
    }

    public void removeModule(Class<? extends Module> moduleClass) {
        modules.remove(findModule(moduleClass));
    }

    public ArrayList<GameObject> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<GameObject> children) {
        this.children = children;
    }

    public void addChild(GameObject child) {
        child.setParent(this);
        children.add(child);
    }

    public void removeChild(GameObject child) {
        children.remove(child);
    }

    public Vector2Int getSize() {
        return new Vector2Int(texture.getWidth(), texture.getHeight());
    }

    public int getWidth() {
        return texture.getWidth();
    }

    public int getHeight() {
        return texture.getHeight();
    }

    //endregion

    /**
     * This method forwards the game loop to its children and modules.
     *
     * @param time duration of the previous game frame.
     */
    @Override
    public void update(float time) {
        for (GameObject g : children) {
            if(g.isActive()){
                g.update(time);
            }
        }
        for (Module m : modules) {
            if(m.isActive()){
                m.update(time);
            }
        }
    }

    /**
     * Compares two objects by there layer numbers.
     * Is used for sorting the objects for drawing them in the correct order.
     *
     * @param o the object to be compared.
     */
    @Override
    public int compareTo(GameObject o) {
        return Integer.compare(layer, o.getLayer());
    }
}
