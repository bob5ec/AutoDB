package bz.asd.autodb.logic;

import bz.asd.autodb.data.Model;
import java.util.Comparator;

/**
 *
 * @author lars
 */
public class ModelSort implements Comparator<Model> {

    private int[] order;

    public ModelSort(int[] order) {
        this.order = order;
    }

    public int compare(Model o1, Model o2) {
        int res = 0;
        for(int attribute : order) {
            res = o1.compareTo(attribute, o2);
            if(res != 0) break;
        }
        return res;
    }

}
