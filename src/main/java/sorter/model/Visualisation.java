package sorter.model;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import sorter.algorithms.*;
import sorter.view.UserInterface;

import java.util.ArrayList;
import java.util.List;

import static sorter.Constants.*;
import static sorter.model.Bar.*;

public class Visualisation {

    private Scene scene;
    private Pane root;
    private Bar[] bars;
    private List<Animation> animations;
    private List<Animation> sortedScan;
    private SortingAlgorithm sorter;
    private boolean sorting;
    private boolean updateFrames;
    private boolean updateBarWidths;
    private int animationIndex;
    private GameEngine gameEngine;
    private double barWidth;

    private UserInterface ui;

    public Visualisation(double width, double height, GameEngine gameEngine) {
        root = new Pane();
        scene = new Scene(root, width, height);
        animations = new ArrayList<>();
        sorting = false;
        updateFrames = false;
        ui = new UserInterface(OPTIONS_OFFSET - 1, scene.getHeight(), this);
        sorter = ui.getSortingAlgorithm();
        root.getChildren().add(ui);
        root.getChildren().addAll(ui.getChildren());
        barWidth = DEFAULT_BAR_WIDTH;
        this.gameEngine = gameEngine;
        this.createArray();
    }

    public Scene getScene() {
        return scene;
    }

    public void handleBarWidthPress(MouseEvent mouseEvent) {
        updateBarWidths = true;
        resetBarColours(bars);
    }

    public void handleBarWidthRelease(MouseEvent mouseEvent) {
        updateBarWidths = false;
        sorting = false;
    }

    public void handleFrameRatePress(MouseEvent mouseEvent) {
        updateFrames = true;
    }

    public void handleFrameRateRelease(MouseEvent mouseEvent) {
        updateFrames = false;
        gameEngine.setFrameRate(ui.getDelayUI().getValue());

    }

    public void algorithmSelection(ActionEvent actionEvent) {
        sorter = ui.getSortingAlgorithm();
        sorting = false;
        resetBarColours(bars);
    }

    public void sort(MouseEvent mouseEvent) {
        if (sorting) {
            return;
        }
        if (sortedScan == null) {
            sortedScan = scanThroughAnimation(bars);
        }
        if (isSorted(bars)) {
            animations.clear();
            animations.addAll(sortedScan);
            animationIndex = 0;
        } else if (!sorting) {
            animations = sorter.sort(createBarsCopy(bars));
            animations.addAll(sortedScan);
            animationIndex = 0;
        }
        sorting = true;
    }

    public void createArray() {
        int n = (int) ((scene.getWidth() - OPTIONS_OFFSET)/ barWidth);
        bars = new Bar[n];
        double space = (scene.getWidth() - OPTIONS_OFFSET - bars.length * barWidth) / 2;
        for (int i = 0; i < bars.length; i++) {
            bars[i] = new Bar(barWidth, MIN_BAR_HEIGHT +
                    Math.random() * (scene.getHeight() - MIN_BAR_HEIGHT));
            bars[i].setTranslateX(i * barWidth + OPTIONS_OFFSET + space);
            bars[i].setTranslateY(scene.getHeight() - bars[i].getBarHeight());
            root.getChildren().add(bars[i]);
        }
    }

    public void randomiseArray(MouseEvent event) {
        for (int i = 0; i < bars.length; i++) {
            bars[i].setBarHeight(MIN_BAR_HEIGHT +
                    Math.random() * (scene.getHeight() - MIN_BAR_HEIGHT));
            bars[i].setTranslateY(scene.getHeight() - bars[i].getBarHeight());
            bars[i].setColour(DEFAULT_BAR_COLOUR);
        }
        sorting = false;
    }

    public void tick() {
        if (sorting) {
            if (animationIndex > 0) {
                int prevMinIdx = animations.get(animationIndex - 1).getMin();
                int prevMaxIdx = animations.get(animationIndex - 1).getMax();
                bars[prevMinIdx].setColour(DEFAULT_BAR_COLOUR);
                bars[prevMaxIdx].setColour(DEFAULT_BAR_COLOUR);
                if (animations.get(animationIndex - 1).needsSwapping()) {
                    swap(bars, prevMinIdx, prevMaxIdx, scene.getHeight());
                }
            }
            if (animationIndex < animations.size()) {
                int minIdx = animations.get(animationIndex).getMin();
                int maxIdx = animations.get(animationIndex).getMax();
                if (animations.get(animationIndex).needsOverriding()) {
                    bars[minIdx].setBarHeight(animations.get(animationIndex).getOverrideValue());
                    bars[minIdx].setTranslateY(scene.getHeight() - bars[minIdx].getBarHeight());
                }
                bars[minIdx].setColour(animations.get(animationIndex).getMinColour());
                bars[maxIdx].setColour(animations.get(animationIndex).getMaxColour());
            }
            animationIndex++;
            if (animationIndex == animations.size() + 1) {
                sorting = false;
            }
        }
    }

    public void uiTick() {
        if (updateFrames) {
            ui.getDelayUI().setTextString("Delay: " + String.format("%.2f ms", ui.getDelayUI().getValue()));
        }
        if (updateBarWidths) {
            if (barWidth == ui.getBarWidthUI().getValue()) {
                resetBarColours(bars);
                return;
            }
            ui.getBarWidthUI().setTextString("Bar width: " + String.format("%.2f pixels", ui.getBarWidthUI().getValue()));
            barWidth = ui.getBarWidthUI().getValue();
            for (Bar bar: bars) {
                root.getChildren().remove(bar);
            }
            ui.getDelayUI().setMax(MAX_DELAY_SCALE * barWidth);
            ui.getDelayUI().setMin(Math.max(0.1, barWidth / MIN_DELAY_SCALE));
            ui.getDelayUI().setTextString("Delay: " + String.format("%.2f ms", ui.getDelayUI().getValue()));
            createArray();
            animations.clear();
            animationIndex = 0;
            sortedScan = scanThroughAnimation(bars);
        }
    }

    private List<Animation> scanThroughAnimation(Bar[] bars) {
        List<Animation> runThrough = new ArrayList<>();
        for (int i = 0; i < bars.length; i++) {
            runThrough.add(new Animation(i, i, false, false,
                    -1, SCAN_BAR_COLOUR, SCAN_BAR_COLOUR));
        }
        return runThrough;
    }
}