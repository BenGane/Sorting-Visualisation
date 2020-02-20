package sorter;

import javafx.scene.paint.Paint;

public class Constants {

    public static final Paint MIN_BAR_COLOUR = Paint.valueOf("lightgreen");
    public static final Paint MAX_BAR_COLOUR = Paint.valueOf("red");
    public static final Paint COMP_BAR_COLOUR = Paint.valueOf("red");
    public static final Paint EXTRA_BAR_COLOUR = Paint.valueOf("red");
    public static final Paint SCAN_BAR_COLOUR = Paint.valueOf("aqua");
    public static final Paint OVERRIDE_BAR_COLOUR = Paint.valueOf("lightgreen");
    public static final Paint DEFAULT_BAR_COLOUR = Paint.valueOf("grey");
    public static final Paint DEFAULT_STROKE_COLOUR = Paint.valueOf("black");
    public static final double DEFAULT_STROKE_WIDTH = 0.5;
    public static final double DEFAULT_BAR_WIDTH = 20;
    public static final double DEFAULT_FRAME_RATE = DEFAULT_BAR_WIDTH;
    public static final double MIN_BAR_HEIGHT = 2;
    public static final double MIN_BAR_WIDTH = 2;
    public static final double MAX_BAR_WIDTH = 50;
    public static final double OPTIONS_OFFSET = 170;

    /**
     * Increasing the min delay scale increases the minimum delay between each animation
     * Increasing the max delay scale increases the maximum delay between each animation
     */
    public static final double MIN_DELAY_SCALE = 20;
    public static final double MAX_DELAY_SCALE = 6;

    public static final double WINDOW_WIDTH = 800;
    public static final double WINDOW_HEIGHT = 600;
}