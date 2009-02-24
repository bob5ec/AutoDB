package bz.asd.autodb.data;

import bz.asd.autodb.logic.ModelSort;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author lars
 */
public class Group<T extends Groupable<? super T>> implements Groupable<Group<T>> {
    protected List<T> elements;
    protected List<Group<T>> subGroups;
    private int[] order;
    private int maxGroupLevel, groupLevel;
    private boolean useSubgroups;
    private T groupAttribute;

    public Group(int[] order, int maxGroupLevel, int groupLevel) {
        this.order = order;
        this.maxGroupLevel = maxGroupLevel;
        this.groupLevel = groupLevel;
        //do not open subroups if we are the last group
        useSubgroups = groupLevel < maxGroupLevel; // true -> elements contain Groups
        elements = new LinkedList<T>();
        subGroups = new LinkedList<Group<T>>();
    }

    public void addAll(Collection<T> newElements) {
        int compareIndex = order[groupLevel];
        
        if (groupAttribute == null) {
            Iterator<T> it = newElements.iterator();
            if(it.hasNext()) groupAttribute = it.next();
        }

        if(!useSubgroups) {
            // Sort leave elements in this Group
            elements.addAll(newElements);
            Collections.sort(elements, new ModelSort<T>(order));
        }
        else {
            // Sort elements into subgroups
            for(T newElement : newElements) {
                ListIterator<Group<T>> li = subGroups.listIterator();
                while(li.hasNext()) {
                    Group<T> subGroup = li.next();
                    int compareRes = subGroup.compareTo(compareIndex, newElement);

                    //TODO test order for correctnes
                    if(compareRes == 0) {
                        // found the subgroup for this element
                        subGroup.add(newElement);
                        break;
                    } else if(compareRes > 0) {
                        // did not found a subgroup for this element
                        // subGroups are sortet --> there is no fitting subgroup yet
                        Group<T> newSubGroup = new Group<T>(order, maxGroupLevel, groupLevel+1);
                        newSubGroup.add(newElement);
                        li.add(newSubGroup);
                        break;
                    }
                }
            }
            // Sort the subgroups
            //TODO sort + continue recursive
        }

        //TODO sort me
    }

    public void add(T element) {
        elements.add(element);
    }

    public int compareTo(int attribute, T o) {
        int res;
        if(groupAttribute == null && o == null) res = 0;
        else if(groupAttribute == null && o != null) res = -1;
        else if(groupAttribute != null && o == null) res = 1;
        else {
            res = this.groupAttribute.compareTo(attribute, o);
        }
        return res;
    }

    public int compareTo(int attribute, Group<T> o) {
        return compareTo(attribute, o.groupAttribute);
    }

    public int getAttributeCount() {
        return Model.ATTRIBUTE_COUNT;
    }

}
