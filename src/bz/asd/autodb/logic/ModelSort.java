package bz.asd.autodb.logic;

import bz.asd.autodb.data.Groupable;
import java.util.Comparator;

/**
 *
 * @author lars
 */
public class ModelSort<T extends Groupable> implements Comparator<T> {

    private int[] order;

    public ModelSort(int[] order) {
        this.order = order;
    }

    public int compare(T o1, T o2) {
        int res = 0;
        for(int attribute : order) {
            res = o1.compareTo(attribute, o2);
            if(res != 0) break;
        }
        return res;
    }

}
