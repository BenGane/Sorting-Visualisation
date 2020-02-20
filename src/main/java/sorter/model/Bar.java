package sorter.model;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import static sorter.Constants.*;

public class Bar extends StackPane {

    private Rectangle bar;

    public Bar(double width, double height) {
        bar = new Rectangle(width, height, DEFAULT_BAR_COLOUR);
        bar.setStroke(DEFAULT_STROKE_COLOUR);
        bar.setStrokeWidth(DEFAULT_STROKE_WIDTH);
        getChildren().add(bar);
    }

    public void setColour(Paint paint) {
        bar.setFill(paint);
    }

    public double getBarWidth() {
        return bar.getWidth();
    }

    public void setBarHeight(double height) {
        bar.setHeight(height);
    }

    public double getBarHeight() {
        return bar.getHeight();
    }

    public static boolean isSorted(Bar[] bars) {
        for (int i = 0; i < bars.length - 1; i++) {
            if (bars[i].getBarHeight() > bars[i+1].getBarHeight()) {
                return false;
            }
        }
        return true;
    }

    public static Bar[] createBarsCopy(Bar[] bars) {
        Bar[] copy = new Bar[bars.length];
        for (int i = 0; i < bars.length; i++) {
            Bar bar = bars[i];
            copy[i] = new Bar(bar.getBarWidth(), bar.getBarHeight());
        }
        return copy;
    }

    public static void swap(Bar[] bars, int i, int j, double sceneHeight) {
        double temp = bars[i].getBarHeight();
        bars[i].setBarHeight(bars[j].getBarHeight());
        bars[j].setBarHeight(temp);
        bars[i].setTranslateY(sceneHeight - bars[i].getBarHeight());
        bars[j].setTranslateY(sceneHeight- bars[j].getBarHeight());
    }

    public static void swap(Bar[] bars, int i, int j) {
        double temp = bars[i].getBarHeight();
        bars[i].setBarHeight(bars[j].getBarHeight());
        bars[j].setBarHeight(temp);
    }

    public static void resetBarColours(Bar[] bars) {
        for (Bar b: bars) {
            b.setColour(DEFAULT_BAR_COLOUR);
        }
    }
}