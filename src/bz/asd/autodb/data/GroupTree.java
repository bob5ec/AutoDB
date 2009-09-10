
package bz.asd.autodb.data;

import bz.asd.autodb.logic.ModelSort;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

/**
 *
 * @author lars
 */
public class GroupTree extends DefaultTreeModel implements bz.asd.mvc.Model, CollectionChangeListener<Groupable> {

    //private DefaultMutableTreeNode root;
    private int[] order;
    private int groupLevel;
    

    private GroupTree(TreeNode root, int[] order, int groupLevel) {
        super(root);
        this.order = order;
        this.groupLevel = groupLevel;
    }

    /**
     *
     * @param elements The data items to be in the tree
     * @param order the order, which determines which attribute is ordered first and which afterwards
     * @param groupLevel depth of the tree. 0 meens no grouping
     * @return
     */
    public static GroupTree create(List<Groupable> elements, int[] order, int groupLevel) {
        if(elements.size() == 0) {
            return new GroupTree(new DefaultMutableTreeNode(), order, groupLevel);
        }
        // sort elements
        Collections.sort(elements, new ModelSort(order));

        // create groups
        
        groupLevel++; // the root is an additional node in the array

        DefaultMutableTreeNode curNodes[] = new DefaultMutableTreeNode[groupLevel];

        for(int i = 0; i < groupLevel; i++) {
            curNodes[i] = new Group(elements.get(0), order, i-1);
            if(i>0) curNodes[i-1].add(curNodes[i]);
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
        
        return new GroupTree(curNodes[0], order, groupLevel-1);
    }

    private static void addBranch(DefaultMutableTreeNode[] curNodes, int level, Groupable element, int[] order) {
        curNodes[level] = new Group(element, order, level-1);
        curNodes[level-1].add(curNodes[level]);
    }

    // implementatio of CollectionChangeListener<Groupable>

    public void added(Groupable element) {
        //TODO BUG: sometimes existing groups are not used when inserting matiching elements
        // --> dublicate groups with same name
        System.out.println("GroupTree: added "+element);

        // find group with prefix of the new element
        //    if not exists find yungest ancester
        DefaultMutableTreeNode ancester = getExistingMatchingAncester(element);

        int i = 0; // check for existing matching groups
        if(ancester instanceof Group) {
            i=((Group) ancester).getCommonPrefix(element);
        }

        // create branche to the leaf
        for(; i<groupLevel; i++) {
            Group child = new Group(element, order, i);
            insertSorted(ancester, child);
            ancester = child;
        }
        insertSorted(ancester, new DefaultMutableTreeNode(element));
    }

    /**
     * find group with prefix of the element
     * if not exists find youngest ancester
     * @param element
     * @return
     */
    public DefaultMutableTreeNode getExistingMatchingAncester(Groupable element) {
        int maxMatch = 0;
        DefaultMutableTreeNode maxMatchNode = getRoot();

        Enumeration<DefaultMutableTreeNode> nodes= getRoot().depthFirstEnumeration();
        while(nodes.hasMoreElements()) {
            DefaultMutableTreeNode node = nodes.nextElement();

            if(!node.isLeaf()) {
                int match = 0;
                if(node instanceof Group) match = ((Group)node).getCommonPrefix(element);
                if(match >= maxMatch) {
                    maxMatch = match;
                    maxMatchNode = node;
                }
            }
        }

        return maxMatchNode;
    }

    public void insertSorted(DefaultMutableTreeNode parent, DefaultMutableTreeNode node) {
        Groupable element = (Groupable)node.getUserObject();
        ModelSort sort = new ModelSort(order);

        Enumeration<DefaultMutableTreeNode> children = parent.children();
        int i=0;
        while(children.hasMoreElements()) {
            DefaultMutableTreeNode child = children.nextElement();
            
            if(sort.compare(element, (Groupable)child.getUserObject()) <= 0) {
                break;
            } else i++;
        }
        if(i==0) parent.setUserObject(element); // groups always have the first element as userObject
        insertNodeInto(node, parent, i);
    }
/*TODO BUG: after deleting the vorletzte element from the db the beans binding crashes because groupLevel is -1
 * wird vielleicht die root ausgewählt?
*/
    public void deleted(Groupable element) {
        System.out.println("GroupTree: deleted "+element);
        // find node
        DefaultMutableTreeNode node = getNodeRef(element);
        // remove all ancesters which have no child after removal
        TreeNode[] path = node.getPath();

        removeNodeFromParent((DefaultMutableTreeNode)path[path.length-1]);
        // remove empty inner nodes, not leaf and root
        for(int i=path.length-2; i>0; i--) {
            if(path[i].getChildCount() != 0) {
                // set other child userobject as userobj of the group
                DefaultMutableTreeNode parent = (DefaultMutableTreeNode)path[i];
                DefaultMutableTreeNode child = (DefaultMutableTreeNode)path[i].getChildAt(0);
                parent.setUserObject(child.getUserObject());
                // and stop searching
                break;
            }
            removeNodeFromParent((DefaultMutableTreeNode)path[i]);
        }

    }

    public void changed(Groupable element) {
        System.out.println("GroupTree: changed "+element);
        // find oldElement in tree (via ==)
        DefaultMutableTreeNode oldNode = getNodeRef(element);
        // check if element has the same path as oldElement
        DefaultMutableTreeNode node = getExistingMatchingAncester(element);

        Object[] pathNode = node.getUserObjectPath();
        Object[] pathOldNode = oldNode.getUserObjectPath();
        boolean samePath = pathNode.length == pathOldNode.length;
        for(int i=0; i<pathNode.length && samePath; i++) {
            samePath = pathNode[i] == pathOldNode[i];
        }
        if(samePath) {
            // path is the same, we don't have to change the tree
            System.out.println("tree does not change");
            return;
        }

        // remove oldElement
        deleted(element);
        // insert element
        added(element);
    }

    private int comparePrefixTo(Groupable g1, Groupable g2, int prefixLength) {
        int res = 0;
        for(int i = prefixLength; i>=0 && res == 0; i--) {
            res = g1.compareTo(order[i], g2);
        }
        return res;
    }

    /**
     * Find the node which has a reference to the element as userObject.
     * @param element
     * @return
     */
    public DefaultMutableTreeNode getNodeRef(Groupable element) {
        //IMPROVE binary search using ordered Tree
        DefaultMutableTreeNode res = null;
        Enumeration<DefaultMutableTreeNode> nodes= getRoot().depthFirstEnumeration();
        while(nodes.hasMoreElements()) {
            DefaultMutableTreeNode node = nodes.nextElement();

            if(node.isLeaf()) {
                if(element == node.getUserObject()) res = node;
            }
        }
        return res;
    }

    @Override
    public DefaultMutableTreeNode getRoot() {
        return (DefaultMutableTreeNode)super.getRoot();
    }

    public DefaultMutableTreeNode getNextLeaf(DefaultMutableTreeNode node) {
        // IMPROVE inefficient implementation used
        DefaultMutableTreeNode nextNode = getRoot();

        while(nextNode != node) {
            nextNode = nextNode.getNextNode();
        }
        while(nextNode != null && !nextNode.isLeaf()) {
            nextNode = nextNode.getNextNode();
        }
        // leaf of selected node found

        // get next leaf
        nextNode = nextNode.getNextNode();

        while (nextNode != null && !nextNode.isLeaf()) {
            // go to leaf
            nextNode = nextNode.getNextNode();
        }

        return nextNode;
    }

    public DefaultMutableTreeNode getPrevLeaf(DefaultMutableTreeNode node) {
        // IMPROVE inefficient implementation used
        DefaultMutableTreeNode prevNode = getRoot();

        while(prevNode != node) {
            prevNode = prevNode.getNextNode();
        }
        while(prevNode != null && !prevNode.isLeaf()) {
            prevNode = prevNode.getNextNode();
        }
        // leaf of selected node found

        // get prev leaf
        prevNode = prevNode.getPreviousNode();

        while (prevNode != null && !prevNode.isLeaf()) {
            // go to leaf
            prevNode = prevNode.getPreviousNode();
        }

        return prevNode;
    }
}
