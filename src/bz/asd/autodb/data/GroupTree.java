
package bz.asd.autodb.data;

import java.util.Collection;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

/**
 *
 * @author lars
 */
public class GroupTree extends DefaultTreeModel implements bz.asd.mvc.Model {

    private DefaultMutableTreeNode root;
    

    private GroupTree(TreeNode root) {
        super(root);
    }

    /**
     *
     * @param elements The data items to be in the tree
     * @param order the order, which determines which attribute is ordered first and which afterwards
     * @param groupLevel depth of the tree. 0 meens no grooping
     * @return
     */
    public static GroupTree create(Collection<Groupable> elements, int[] order, int groupLevel) {
        Group<Groupable<Groupable>> root = new Group<Groupable<Groupable>>(order, groupLevel, 0);
        root.add(elements);
        //DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        //return new GroupTree(root);
    }

}
