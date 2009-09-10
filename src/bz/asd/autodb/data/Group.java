package bz.asd.autodb.data;

import bz.asd.autodb.logic.ModelSort;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author lars
 */
public class Group<T extends Groupable<? super T>> extends DefaultMutableTreeNode implements Groupable<Group<T>> {
    private int[] order;
    private int groupLevel;

    /**
     *
     * @param order the current order
     * @param groupLevel the level (index) of the GroupTree this Group is in.
     * 0 is the top most level a group can be in
     */
    public Group(T userObject, int[] order, int groupLevel) {
        this.userObject = userObject;
        this.order = order;
        this.groupLevel = groupLevel;
    }

    @Override
    public T getUserObject() {
        return (T) userObject;
    }

    @Override
    public String toString() {
        String s;
        if(userObject == null) s = "";
        else {
            //TODO BUG: sometimes existing groups are not used when inserting matiching elements
            // --> dublicate groups with same name
            // @see GroupTree
            Object obj = getUserObject().getValue(order[groupLevel]);
            if(obj == null) s = "";
            else {
                s=obj.toString();
            }
        }
        return s;
    }

    public int compareToElement(int attribute, T o) {
        int res;
        if(getUserObject() == null && o == null) res = 0;
        else if(getUserObject() == null && o != null) res = -1;
        else if(getUserObject() != null && o == null) res = 1;
        else {
            res = getUserObject().compareTo(attribute, o);
        }
        return res;
    }

    @Override
    public int compareTo(int attribute, Group<T> o) {
        return compareToElement(attribute, o.getUserObject());
    }

    public int getCommonPrefix(T o) {
        int res;

        for(res=0; res<=groupLevel; res++) {
            if(compareToElement(order[res], o) != 0) {
                res--; // last one was not common
                break;
            }
        }

        return res;
    }

    public int getAttributeCount() {
        return Model.ATTRIBUTE_COUNT;
    }

    public Object getValue(int attribute) {
        return getUserObject().getValue(attribute);
    }
}
