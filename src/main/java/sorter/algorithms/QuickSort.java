package sorter.algorithms;

import sorter.model.Animation;
import sorter.model.Bar;

import java.util.ArrayList;
import java.util.List;

import static sorter.Constants.*;
import static sorter.model.Bar.*;

public class QuickSort implements SortingAlgorithm {

    @Override
    public List<Animation> sort(Bar[] bars) {
        return quickSort(bars, 0, bars.length - 1);
    }

    private List<Animation> quickSort(Bar[] bars, int low, int high) {
        List<Animation> animations = new ArrayList<>();
        if (low < high) {
            int pi = partition(bars, low, high, animations);
            animations.addAll(quickSort(bars, low, pi - 1));  // Before pi
            animations.addAll(quickSort(bars, pi + 1, high)); // After pi
        }
        return animations;
    }

    private int partition (Bar[] bars, int low, int high, List<Animation> animations) {

        double pivot = bars[high].getBarHeight();

        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (bars[j].getBarHeight() < pivot) {
                i++;
                if (i != j) {
                    animations.add(new Animation(j, i, true, false, -1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
                    swap(bars, i, j);
                }
                animations.add(new Animation(i, j, false, false, -1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
            } else {
                animations.add(new Animation(j, j, false, false, -1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
            }
        }
        if (bars[high].getBarHeight() < bars[i + 1].getBarHeight()) {
            animations.add(new Animation(high, i + 1, true, false, -1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
            swap(bars, i + 1, high);
            animations.add(new Animation(i + 1, high, false, false, -1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
        } else {
            animations.add(new Animation(i + 1, high, false, false, -1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
        }
        return (i + 1);
    }

}