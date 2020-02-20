package sorter.algorithms;

import sorter.model.Animation;
import sorter.model.Bar;

import java.util.ArrayList;
import java.util.List;

import static sorter.Constants.*;
import static sorter.model.Bar.*;

public class InsertionSort implements SortingAlgorithm {

    @Override
    public List<Animation> sort(Bar[] bars) {

        List<Animation> animations = new ArrayList<>();

        for (int i = 0; i < bars.length; i++) {
            double temp = bars[i].getBarHeight();
            int j = i - 1;
            while (j >= 0) {
                if (bars[j].getBarHeight() > temp) {
                    animations.add(new Animation(j + 1, j, true, false, -1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
                    swap(bars, j, j + 1);
                    animations.add(new Animation(j, j + 1, false, false, -1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
                } else {
                    animations.add(new Animation(j, j + 1, false, false, -1, MIN_BAR_COLOUR, MAX_BAR_COLOUR));
                    break;
                }
                j--;
            }
            bars[j + 1].setBarHeight(temp);
        }

        return animations;
    }
}