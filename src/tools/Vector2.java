package tools;

/**
 * This class is used for storing two flot values.
 */
public class Vector2 {
    public float x;
    public float y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2Int vector){
        this.x = vector.x;
        this.y = vector.y;
    }

    public static Vector2 add(Vector2 v1,Vector2 v2){
        return new Vector2(v1.x+v2.x,v1.y+ v2.y);
    }

    @Override
    public String toString() {
        return "Vector2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
