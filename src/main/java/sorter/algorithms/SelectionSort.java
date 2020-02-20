package sorter.algorithms;

import sorter.model.Animation;
import sorter.model.Bar;

import java.util.ArrayList;
import java.util.List;

import static sorter.Constants.*;
import static sorter.model.Bar.*;

public class SelectionSort implements SortingAlgorithm {

    @Override
    public List<Animation> sort(Bar[] bars) {

        List<Animation> animations = new ArrayList<>();

        for (int i = 0; i < bars.length; i++) {
            int minIdx = i;
            boolean sorted = true;
            for (int j = i + 1; j < bars.length; j++) {
                Bar a = bars[minIdx];
                Bar b = bars[j];
                if (a.getBarHeight() < b.getBarHeight()) {
                    animations.add(new Animation(minIdx, j, false, false, -1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
                } else {
                    animations.add(new Animation(j, minIdx, false, false, -1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
                    minIdx = j;
                }
                if (bars[j - 1].getBarHeight() > bars[j].getBarHeight()) {
                    sorted = false;
                }
            }
            if (i != minIdx) {
                animations.add(new Animation(minIdx, i, true, false, -1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
                swap(bars, i, minIdx);
                animations.add(new Animation(i, minIdx, false, false, -1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
            }
            if (sorted) {
                break;
            }
        }

        return animations;
    }
}