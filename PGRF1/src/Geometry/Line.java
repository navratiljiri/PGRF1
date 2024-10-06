package Geometry;

public class Line {
    private float x2;
    private float y2;
    private float x1;
    private float y1;

    /**
     * Inclination
     */
    private float k = 0;
    /**
     * Shift
     */
    private float q = 0;

    private int color = 0xffffff;

    public Line(float x1, float y1, float x2, float y2) {
        this.x2 = x2;
        this.y2 = y2;
        this.x1 = x1;
        this.y1 = y1;

        calculateInclination();
        calculateShift();
    }

    public Line(float x2, float y2, float x1, float y1, int color) {
        this.x2 = x2;
        this.y2 = y2;
        this.x1 = x1;
        this.y1 = y1;
        this.color = color;

        calculateInclination();
        calculateShift();
    }
    private void calculateInclination() {
        if(x2 - x1 != 0) {
            k = (y2 - y1) / (x2 - x1);
        }
    }
    private void calculateShift() {
        q = y1 - k * x1;
    }

    public int getColor() {
        return color;
    }

    public float getX2() {
        return x2;
    }
    public float getY2() {
        return y2;
    }

    public float getX1() {
        return x1;
    }

    public float getY1() {
        return y1;
    }

    public void setX2(float x2) {
        this.x2 = x2;
    }

    public void setY2(float y2) {
        this.y2 = y2;
    }

    public void setX1(float x1) {
        this.x1 = x1;
    }

    public void setY1(float y1) {
        this.y1 = y1;
    }

    public float getK() {
        return k;
    }

    public float getQ() {
        return q;
    }
}
