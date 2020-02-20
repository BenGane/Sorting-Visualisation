package sorter.algorithms;

import sorter.model.Animation;
import sorter.model.Bar;

import java.util.ArrayList;
import java.util.List;

import static sorter.Constants.*;
import static sorter.model.Bar.*;

public class BubbleSort implements SortingAlgorithm {

    @Override
    public List<Animation> sort(Bar[] bars) {

        List<Animation> animations = new ArrayList<>();

        for (int i = 0; i < bars.length; i++) {

            boolean swapped = false;

            for (int j = 0; j < bars.length - 1; j++) {
                Bar a = bars[j];
                Bar b = bars[j + 1];
                if (a.getBarHeight() < b.getBarHeight()) {
                    animations.add(new Animation(j, j + 1, false, false, -1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
                } else {
                    animations.add(new Animation(j + 1, j, true, false, -1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
                    swap(bars, j, j + 1);
                    animations.add(new Animation(j, j + 1, false, false, - 1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }

        return animations;
    }
}