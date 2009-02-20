package bz.asd.autodb.data;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author lars
 */
public class Group<T extends Groupable<? super T>> implements Groupable<Group> {
    protected List<T> elements;
    private int[] order;
    private int maxGroupLevel, groupLevel;
    private T groupAttribute;

    public Group(int[] order, int maxGroupLevel, int groupLevel) {
        this.order = order;
        this.maxGroupLevel = maxGroupLevel;
        this.groupLevel = groupLevel;
        elements = new LinkedList<T>();
    }

    public void addAll(Collection<T> newElements) {
        int compareIndex = order[groupLevel];
        
        //do not open subroups if we are the last group
        boolean openSubgroups = groupLevel < maxGroupLevel;

        if (groupAttribute == null) {
            Iterator<T> it = newElements.iterator();
            if(it.hasNext()) groupAttribute = it.next();
        }


        if(!openSubgroups) elements.addAll(newElements);
        else {

            for(T e : elements) {
                //e.compareTo(compareIndex, groupAttribute);
            }
        }

        //TODO sort me
    }

    public int compareTo(int attribute, Group o) {
        int res;
        if(groupAttribute == null && o.groupAttribute == null) res = 0;
        else if(groupAttribute == null && o.groupAttribute != null) res = -1;
        else if(groupAttribute != null && o.groupAttribute == null) res = 1;
        else {
            groupAttribute.compareTo(attribute, o.groupAttribute);
        }
        return res;
    }

    public int getAttributeCount() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
