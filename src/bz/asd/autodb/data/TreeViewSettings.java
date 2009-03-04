package bz.asd.autodb.data;

/**
 *
 * @author lars
 */
public class TreeViewSettings {

    private int[] order;
    private int groupLevel;

    public TreeViewSettings() {

    }

    public int getGroupLevel() {
        return groupLevel;
    }

    public void setGroupLevel(int groupLevel) {
        this.groupLevel = groupLevel;
    }

    public int[] getOrder() {
        return order;
    }

    public void setOrder(int[] order) {
        this.order = order;
    }

}
