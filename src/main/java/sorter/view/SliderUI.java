package sorter.view;

import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class SliderUI extends StackPane {

    private Slider slider;
    private Text text;

    public SliderUI(double width, double height, double min, double max, double value) {
        Rectangle bg = new Rectangle(width, height);
        bg.setArcHeight(10);
        bg.setArcWidth(10);
        bg.setStrokeWidth(1);
        bg.setStroke(Paint.valueOf("0xB5B5B5"));
        bg.setFill(Paint.valueOf("0xE8E8E8"));
        text = new Text();
        text.setStrokeWidth(1);
        text.setFill(Paint.valueOf("0x323232"));
        text.setTranslateY(-15);
        slider = new Slider(min, max, value);
        slider.setTranslateY(5);
        slider.setFocusTraversable(false);
        getChildren().addAll(bg, text, slider);
    }

    public void setTextString(String s) {
        text.setText(s);
    }

    public Slider getSlider() {
        return slider;
    }

    public double getValue() {
        return slider.getValue();
    }

    public void setMax(double max) {
        slider.setMax(max);
    }

    public void setMin(double min) {
        slider.setMin(min);
    }
}