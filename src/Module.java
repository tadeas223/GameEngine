public abstract class Module implements Updatable{
    protected GameObject source;
    public Module(GameObject source){
        this.source = source;
    }

    public GameObject getSource() {
        return source;
    }
}
