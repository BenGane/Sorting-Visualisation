package sorter.model;

import javafx.scene.paint.Paint;

public class Animation {

    private int min;
    private int max;
    private boolean swap;
    private boolean override;
    private double overrideValue;
    private Paint minColour;
    private Paint maxColour;

    public Animation(int min, int max, boolean swap, boolean override, double overrideValue, Paint minColour, Paint maxColour) {
        this.min = min;
        this.max = max;
        this.swap = swap;
        this.override = override;
        this.overrideValue = overrideValue;
        this.minColour = minColour;
        this.maxColour = maxColour;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public boolean needsSwapping() {
        return swap;
    }

    public boolean needsOverriding() { return override; }

    public double getOverrideValue() {
        return overrideValue;
    }

    public Paint getMaxColour() {
        return maxColour;
    }

    public Paint getMinColour() {
        return minColour;
    }
}