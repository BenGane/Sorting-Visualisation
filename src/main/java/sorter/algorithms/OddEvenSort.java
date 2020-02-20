package sorter.algorithms;

import sorter.model.Animation;
import sorter.model.Bar;

import java.util.ArrayList;
import java.util.List;

import static sorter.Constants.*;
import static sorter.model.Bar.*;

public class OddEvenSort implements SortingAlgorithm {

    @Override
    public List<Animation> sort(Bar[] bars) {

        List<Animation> animations = new ArrayList<>();

        boolean sorted = false;

        while (!sorted) {
            sorted = true;

            // even elements
            for (int i = 1; i <= bars.length - 2; i = i+2) {
                if (bars[i].getBarHeight() > bars[i+1].getBarHeight()) {
                    animations.add(new Animation(i + 1, i, true, false, -1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
                    swap(bars, i, i + 1);
                    animations.add(new Animation(i, i + 1, false, false, -1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
                    sorted = false;
                } else {
                    animations.add(new Animation(i, i + 1, false, false, -1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
                }
            }

            // odd elements
            for (int i = 0; i <= bars.length - 2; i = i+2) {
                if (bars[i].getBarHeight() > bars[i + 1].getBarHeight()) {
                    animations.add(new Animation(i + 1, i, true, false, -1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
                    swap(bars, i, i + 1);
                    animations.add(new Animation(i, i + 1, false, false, -1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
                    sorted = false;
                } else {
                    animations.add(new Animation(i, i + 1, false, false, -1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
                }
            }
        }

        return animations;
    }

}