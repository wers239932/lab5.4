package objectSpace;

import objectSpace.objectExceptions.CoordinatesException;

/**
 * класс координат в формате (x,y)
 */

public class Coordinates {
    private float x;
    private long y;
    public static float parseXCoord(String x) throws CoordinatesException {
        float y;
        try
        {
            y = Float.parseFloat(x);
        }
        catch (Exception e)
        {
            throw new CoordinatesException("не удалось преобразовать из строки в float");
        }
        return y;
    }
    public static long parseYCoord(String x) throws CoordinatesException {
        long y;
        try {
            y = Long.parseLong(x);
        } catch (Exception e) {
            throw new CoordinatesException("не удалось преобразовать из строки в long");
        }
        return y;
    }

    public Coordinates(float x, long y)
    {
        this.x=x;
        this.y=y;
    }

    public long getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    @Override
    public String toString()
    {
        return x + "," + y;
    }
}
