package gameObject;

import Tools.Vector2;
import engine.Engine;
import engine.Updatable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;

public class GameObject implements Updatable,Comparable<GameObject>{
    private Engine engine;
    private GameObject parent = null;
    private String name;
    private int layer = 1;
    private boolean active = true;
    private BufferedImage texture;
    private Rectangle rectangle= new Rectangle(0,0,0,0);
    private Vector2 scale = new Vector2(1,1);
    private HashSet<Module> modules = new HashSet<>();
    private ArrayList<GameObject> children = new ArrayList<>();

    public GameObject(){
        engine = Engine.getInstance();
    }
    public GameObject(String name){
        this.name = name;
    }


    public boolean addModule(Module module){
        module.source = this;
        return modules.add(module);
    }

    public boolean removeModule(Module module){
        return modules.add(module);
    }

    public void addChild(GameObject gameObject){
        setParent(this);
        children.add(gameObject);
    }

    public void removeChild(GameObject gameObject){
        setParent(this);
        children.add(gameObject);
    }

    public void move(int x, int y){
        rectangle.x += x;
        rectangle.y += y;
    }

    public void clampSizeToTexture(){
        rectangle.width = texture.getWidth();
        rectangle.height = texture.getHeight();
    }

    //region Get&Set

    public void moveX(int x){
        rectangle.x += x;
    }

    public void moveY(int y){
        rectangle.y += y;
    }
    public HashSet<Module> getModules() {
        return modules;
    }

    public void setModules(HashSet<Module> modules) {
        this.modules = modules;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public void setScaleX(float x){
        scale.x = x;
    }
    public void setScaleY(float y){
        scale.y = y;
    }

    public void setSize(int width, int height){
        rectangle.height = height;
        rectangle.width = width;
    }

    public Vector2 getScale() {
        return scale;
    }

    public void setScale(Vector2 scale) {
        this.scale = scale;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void setRectangle(int x, int y, int width, int height){
        rectangle = new Rectangle(x,y,width,height);
    }

    public void setPosition(int x, int y ){
        rectangle.x = x;
        rectangle.y = y;
    }

    public void setSize(Dimension d){
        rectangle.width = d.width;
        rectangle.height = d.height;

    }

    public ArrayList<GameObject> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<GameObject> children) {
        this.children = children;
    }

    public GameObject getParent() {
        return parent;
    }

    public void setParent(GameObject parent) {
        this.parent = parent;
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

    //endregion

    @Override
    public void update(float time) {
        for(Updatable u : children){
            u.update(time);
        }
        for(Module m : modules){
            m.update(time);
        }
    }

    @Override
    public int compareTo(GameObject o) {
        return Integer.compare(layer,o.getLayer());
    }
}
