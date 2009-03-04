
package bz.asd.autodb.data;

import bz.asd.autodb.logic.ModelSort;
import java.util.Collections;
import java.util.List;
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
    public static GroupTree create(List<Groupable> elements, int[] order, int groupLevel) {
        // sort elements
        Collections.sort(elements, new ModelSort(order));

        // create groups
        
        groupLevel++; // the root is an additional node in the array

        DefaultMutableTreeNode curNodes[] = new DefaultMutableTreeNode[groupLevel];

        for(int i = 0; i < groupLevel; i++) {
            curNodes[i] = new Group(elements.get(0), order, i-1);
        }
        curNodes[0].setUserObject(null);

        for(Groupable e : elements) {
            for(int level = 1; level < groupLevel; level++) {
                Groupable levelNode = (Groupable)curNodes[level].getUserObject();
                if(levelNode.compareTo(order[level-1], e) != 0) {
                    //addBranch(curNodes, level, e, order);
                    for(;level < groupLevel; level++) {
                        // create new branch and fill it to the yeald
                        addBranch(curNodes, level, e, order);
                    }
                    break;
                }
            }
            // add Data object as leaves to the tree
            curNodes[groupLevel-1].add(new DefaultMutableTreeNode(e));
        }
        
        return new GroupTree(curNodes[0]);
    }

    private static void addBranch(DefaultMutableTreeNode[] curNodes, int level, Groupable element, int[] order) {
        curNodes[level] = new Group(element, order, level-1);
        curNodes[level-1].add(curNodes[level]);
    }

}
