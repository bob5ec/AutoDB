package bz.asd.autodb.logic;

import bz.asd.autodb.data.Group;
import java.util.Comparator;
/**
 *
 * @author lars
 */
public class GroupSort implements Comparator<Group> {

    private int[] order;
    private int groupLevel;

    /**
     *
     * @param order
     * @param groupLevel the level of the Groups which should be sorted
     */
    public GroupSort(int[] order, int groupLevel) {
        this.order = order;
        this.groupLevel = groupLevel;
    }

    public int compare(Group o1, Group o2) {
        int res = 0;
        for(int z=0; z<groupLevel && z<order.length; z++) {
            int attribute = order[z];
            res = o1.compareTo(attribute, o2);
            if(res != 0) break;
        }
        return res;
    }

}
