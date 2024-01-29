package tools;

public class Vector2Int{
    public int x;
    public int y;

    public Vector2Int(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2Int(Vector2Int vector){
        this.x = vector.x;
        this.y = vector.y;
    }

    public static Vector2Int add(Vector2Int v1,Vector2Int v2){
        return new Vector2Int(v1.x+v2.x,v1.y+ v2.y);
    }

    @Override
    public String toString() {
        return "Vector2Int{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
