package sorter.algorithms;

import sorter.model.Animation;
import sorter.model.Bar;

import java.util.ArrayList;
import java.util.List;

import static sorter.Constants.*;
import static sorter.model.Bar.*;

public class CocktailSort implements SortingAlgorithm {

    @Override
    public List<Animation> sort(Bar[] bars) {

        List<Animation> animations = new ArrayList<>();

        int start = 0;
        int end = bars.length - 1;
        boolean swapped = false;

        while (!swapped) {

            // bubble sort from start to end
            for (int i = start; i < end; i++) {
                Bar a = bars[i];
                Bar b = bars[i + 1];
                if (a.getBarHeight() <= b.getBarHeight()) {
                    animations.add(new Animation(i, i + 1, false, false, -1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
                } else {
                    animations.add(new Animation(i + 1, i, true, false, -1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
                    swap(bars, i, i + 1);
                    animations.add(new Animation(i, i + 1, false, false, -1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }

            swapped = false;

            end--;

            for (int i = end; i > start; i--) {
                Bar a = bars[i - 1];
                Bar b = bars[i];
                if (a.getBarHeight() <= b.getBarHeight()) {
                    animations.add(new Animation(i - 1, i, false, false, -1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
                } else {
                    animations.add(new Animation(i, i - 1, true, false, -1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
                    swap(bars, i, i - 1);
                    animations.add(new Animation(i - 1, i, false, false, -1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }

            start++;

            swapped = false;
        }

        return animations;
    }

}