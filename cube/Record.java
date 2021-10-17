//OVERVIEW: le istanze della classe rappresentano un tipo record composto da due interi
public class Record {
    private int x;
    private int y;

    public Record(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override 
    public String toString() {
        return x + ", " + y;
    }
}
