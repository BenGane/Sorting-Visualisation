package sorter.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.util.Duration;

import static sorter.Constants.*;

public class GameEngine {

    private Visualisation visuals;
    private Timeline visualTimeline;

    public GameEngine(double width, double height) {
        this.visuals = new Visualisation(width - OPTIONS_OFFSET, height, this);
    }

    public Scene getScene() {
        return visuals.getScene();
    }

    public void run() {
        visualTimeline = new Timeline(new KeyFrame(Duration.millis(DEFAULT_FRAME_RATE), t -> this.visualise()));
        visualTimeline.setCycleCount(Timeline.INDEFINITE);
        visualTimeline.play();
        Timeline uiTimeline = new Timeline(new KeyFrame(Duration.millis(0.1), t -> this.interfaceTicks()));
        uiTimeline.setCycleCount(Timeline.INDEFINITE);
        uiTimeline.play();
    }

    public void setFrameRate(double frameRate) {
        visualTimeline.stop();
        visualTimeline.getKeyFrames().clear();
        visualTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(frameRate), t -> this.visualise()));
        visualTimeline.play();
    }

    private void visualise() {
        visuals.tick();
    }

    private void interfaceTicks() {
        visuals.uiTick();
    }
}