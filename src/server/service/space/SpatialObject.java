package server.service.space;

public class SpatialObject<T extends Space> {
    public final T space;
    private int x;
    private int y;

    public SpatialObject(T space, int x, int y) {
        this.space = space;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected void update(long et) {

    }
}
