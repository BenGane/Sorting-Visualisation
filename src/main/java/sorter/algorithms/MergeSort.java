package sorter.algorithms;

import sorter.model.Animation;
import sorter.model.Bar;

import java.util.ArrayList;
import java.util.List;

import static sorter.Constants.*;

public class MergeSort implements SortingAlgorithm {

    @Override
    public List<Animation> sort(Bar[] bars) {
        return mergeSort(bars, 0, bars.length - 1);
    }

    private List<Animation> mergeSort(Bar[] array, int low, int high) {
        List<Animation> animations = new ArrayList<>();
        if (high <= low) {
            return animations;
        }
        int mid = (low+high)/2;
        animations.addAll(mergeSort(array, low, mid));
        animations.addAll(mergeSort(array, mid+1, high));
        animations.addAll(merge(array, low, mid, high));
        return animations;
    }

    private List<Animation> merge(Bar[] array, int low, int mid, int high) {
        List<Animation> animations = new ArrayList<>();

        Bar[] leftArray = new Bar[mid - low + 1];
        Bar[] rightArray = new Bar[high - mid];

        for (int i = 0; i < leftArray.length; i++)
            leftArray[i] = array[low + i];
        for (int i = 0; i < rightArray.length; i++)
            rightArray[i] = array[mid + i + 1];

        int leftIndex = 0;
        int rightIndex = 0;

        List<Double> values = new ArrayList<>();

        for (int i = low; i < high + 1; i++) {

            if (leftIndex < leftArray.length && rightIndex < rightArray.length) {
                if (leftArray[leftIndex].getBarHeight() < rightArray[rightIndex].getBarHeight()) {
                    array[i] = leftArray[leftIndex];
                    values.add(array[i].getBarHeight());
                    animations.add(new Animation(low + leftIndex, mid + 1 + rightIndex,
                            false, false, -1, COMP_BAR_COLOUR, COMP_BAR_COLOUR));
                    leftIndex++;
                } else {
                    array[i] = rightArray[rightIndex];
                    values.add(array[i].getBarHeight());
                    animations.add(new Animation(mid + 1 + rightIndex, low + leftIndex,
                            false, false, -1, COMP_BAR_COLOUR, COMP_BAR_COLOUR));
                    rightIndex++;
                }
            } else if (leftIndex < leftArray.length) {
                array[i] = leftArray[leftIndex];
                values.add(array[i].getBarHeight());
                animations.add(new Animation(low + leftIndex, low + leftIndex,
                       false, false, -1, EXTRA_BAR_COLOUR, EXTRA_BAR_COLOUR));
                leftIndex++;
            } else if (rightIndex < rightArray.length) {
                array[i] = rightArray[rightIndex];
                values.add(array[i].getBarHeight());
                animations.add(new Animation(mid + 1 + rightIndex, mid + 1 + rightIndex,
                        false, false, -1, EXTRA_BAR_COLOUR, EXTRA_BAR_COLOUR));
                rightIndex++;
            }
        }
        for (int i = 0; i < values.size(); i++) {
            double value = values.get(i);
            animations.add(new Animation(low + i, low + i,
                    false, true, value, OVERRIDE_BAR_COLOUR, OVERRIDE_BAR_COLOUR));
        }

        return animations;
    }
}