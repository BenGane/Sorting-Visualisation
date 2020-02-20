package sorter.view;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import sorter.Constants;
import sorter.model.Visualisation;
import sorter.algorithms.*;

import java.util.HashMap;
import java.util.Map;

import static sorter.Constants.*;

public class UserInterface extends StackPane {

    private ChoiceBox<String> algorithmSelection;
    private Map<String, SortingAlgorithm> algorithms;
    private SliderUI delayUI;
    private SliderUI barWidthUI;

    public UserInterface(double width, double height, Visualisation visualisation) {
        Rectangle background = new Rectangle(width, height);
        background.setTranslateX(1);
        background.setFill(Paint.valueOf("lightgrey"));
        background.setStrokeWidth(1);
        background.setStroke(Paint.valueOf("darkgrey"));

        double yOffset = (height - 215) / 2;

        Button sortButton = new Button("Sort!");
        sortButton.setFocusTraversable(false);
        sortButton.setStyle("-fx-shadow-highlight-color: transparent;");
        sortButton.setOnMouseClicked(visualisation::sort);
        sortButton.setPrefWidth(60);
        sortButton.setTranslateX((OPTIONS_OFFSET - sortButton.getPrefWidth()) / 2);
        sortButton.setTranslateY(yOffset);

        Button arrayGenerator = new Button("Generate new array!");
        arrayGenerator.setFocusTraversable(false);
        arrayGenerator.setStyle("-fx-shadow-highlight-color: transparent;");
        arrayGenerator.setOnMouseClicked(visualisation::randomiseArray);
        arrayGenerator.setPrefWidth(155);
        arrayGenerator.setTranslateX((OPTIONS_OFFSET - arrayGenerator.getPrefWidth()) / 2);
        arrayGenerator.setTranslateY(yOffset + 30);

        algorithmSelection = new ChoiceBox<>();
        algorithmSelection.setFocusTraversable(false);
        algorithmSelection.setStyle("-fx-shadow-highlight-color: transparent;");
        algorithmSelection.getItems().addAll("Bubble sort", "Selection sort", "Cocktail sort", "Insertion sort",
                "Odd Even sort", "Quick sort", "Merge sort");
        algorithmSelection.setValue("Bubble sort");
        algorithmSelection.setOnAction(visualisation::algorithmSelection);
        algorithmSelection.setPrefWidth(125);
        algorithmSelection.setTranslateX((OPTIONS_OFFSET - algorithmSelection.getPrefWidth()) / 2);
        algorithmSelection.setTranslateY(yOffset + 60);

        delayUI = new SliderUI(160, 60, DEFAULT_BAR_WIDTH / MIN_DELAY_SCALE,
                MAX_DELAY_SCALE * DEFAULT_BAR_WIDTH, DEFAULT_FRAME_RATE);
        delayUI.getSlider().setOnMousePressed(visualisation::handleFrameRatePress);
        delayUI.getSlider().setOnMouseReleased(visualisation::handleFrameRateRelease);
        delayUI.setTextString("Delay: " + String.format("%.2f ms", DEFAULT_FRAME_RATE));
        delayUI.setPrefWidth(160);
        delayUI.setTranslateX((Constants.OPTIONS_OFFSET - delayUI.getPrefWidth()) / 2);
        delayUI.setTranslateY(yOffset + 90);

        barWidthUI = new SliderUI(160, 60, MIN_BAR_WIDTH,  MAX_BAR_WIDTH, DEFAULT_BAR_WIDTH);
        barWidthUI.getSlider().setOnMousePressed(visualisation::handleBarWidthPress);
        barWidthUI.getSlider().setOnMouseReleased(visualisation::handleBarWidthRelease);
        barWidthUI.setTextString("Bar width: " + String.format("%.2f pixels", DEFAULT_BAR_WIDTH));
        barWidthUI.setPrefWidth(160);
        barWidthUI.setTranslateX((OPTIONS_OFFSET - barWidthUI.getPrefWidth()) / 2);
        barWidthUI.setTranslateY(yOffset + 155);

        getChildren().addAll(background, sortButton, arrayGenerator, algorithmSelection, delayUI, barWidthUI);

        initialiseSortingAlgorithms();
    }

    private void initialiseSortingAlgorithms() {
        algorithms = new HashMap<>();
        algorithms.put("Bubble sort", new BubbleSort());
        algorithms.put("Selection sort", new SelectionSort());
        algorithms.put("Cocktail sort", new CocktailSort());
        algorithms.put("Insertion sort", new InsertionSort());
        algorithms.put("Odd Even sort", new OddEvenSort());
        algorithms.put("Quick sort", new QuickSort());
        algorithms.put("Merge sort", new MergeSort());
    }

    public SortingAlgorithm getSortingAlgorithm() {
        return algorithms.get(algorithmSelection.getValue());
    }

    public SliderUI getDelayUI() {
        return delayUI;
    }

    public SliderUI getBarWidthUI() {
        return barWidthUI;
    }
}