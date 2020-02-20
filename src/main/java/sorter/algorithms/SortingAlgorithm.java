package sorter.algorithms;

import sorter.model.Animation;
import sorter.model.Bar;

import java.util.List;

public interface SortingAlgorithm {

    List<Animation> sort(Bar[] bars);

}