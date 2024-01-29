package tools;

public class Vector2Int{
    public int x;
    public int y;

    public Vector2Int(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2Int add(Vector2Int v1,Vector2Int v2){
        return new Vector2Int(v1.x+v2.x,v1.y+ v2.y);
    }
}
