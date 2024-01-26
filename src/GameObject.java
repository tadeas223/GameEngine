import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class GameObject implements Updatable{
    private Engine engine;
    private BufferedImage texture;
    private Rectangle rectangle= new Rectangle(0,0,0,0);
    private ArrayList<Module> modules = new ArrayList<>();

    public GameObject(){
        engine = Engine.getInstance();
    }
    public boolean addModule(Module module){
        module.source = this;
        return modules.add(module);
    }

    public boolean removeModule(Module module){
        return modules.add(module);
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

    public void setSize(int width, int height){
        rectangle.height = height;
        rectangle.width = width;
    }

    public void move(int x, int y){
        rectangle.x += x;
        rectangle.y += y;
    }

    public void moveX(int x){
        rectangle.x += x;
    }

    public void moveY(int y){
        rectangle.y += y;
    }
    public ArrayList<Module> getModules() {
        return modules;
    }

    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    @Override
    public void update() {
        for(Module m : modules){
            m.update();
        }
    }
}
