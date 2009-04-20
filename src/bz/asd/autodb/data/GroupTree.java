
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
     * @param groupLevel depth of the tree. 0 meens no grooping
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
        System.out.println("GroupTree: added "+element);

        // find group with prefix of the new element
        //    if not exists find yungest ancester
        Group ancester = getExistingMatchingAncester(element);
        // create branche to the leaf
        for(int i=ancester.getCommonPrefix(element); i<groupLevel; i++) {
            Group child = new Group(element, order, i);
            insertSorted(ancester, child);
            ancester = child;
        }
        insertSorted(ancester, new DefaultMutableTreeNode(element));
/*
        // find exact position of the new element in the leafs of the tree
        ModelSort sort = new ModelSort(order);
        DefaultMutableTreeNode lastMatch = null, firstLeaf = null;
        Groupable lastMatchModel = null;

        Enumeration<DefaultMutableTreeNode> nodes= root.depthFirstEnumeration();
        while(nodes.hasMoreElements()) {
            DefaultMutableTreeNode node = nodes.nextElement();
            if(!node.isLeaf()) continue;

            // for all leafs do:
            if(firstLeaf == null) firstLeaf = node;
            Groupable model = (Groupable)node.getUserObject();
            if(sort.compare(model, element) <= 0) {
                lastMatch = node;
                lastMatchModel = model;
            } else {
                break; // the tree is sorted --> no other match possible
            }
        }

        // find prefix that exists in the tree for this element
        int commonPrefixLevel = 0;
        for(int i = groupLevel; i>=0 && commonPrefixLevel == 0; i--) {
            
            if(comparePrefixTo(lastMatchModel, element, i) != 0) {
                commonPrefixLevel = i;
            }
        }

        // create empty branch from the point on where it's nessesery
        DefaultMutableTreeNode[] matchPath;

        if(lastMatch == null) {
            // special case if new element ist the first leaf in the new tree
            matchPath = (DefaultMutableTreeNode[])firstLeaf.getPath();

        } else {
            matchPath = (DefaultMutableTreeNode[])lastMatch.getPath();
        }

        
        
        for(int i=commonPrefixLevel; i<groupLevel; i++) {
            matchPath[i] = new Group(element, order, i);
            matchPath[i-1]
            matchPath[i-1].insert(matchPath[i], );
        }
        matchPath[groupLevel].add(new DefaultMutableTreeNode(element));*/
    }

    /**
     * find group with prefix of the element
     * if not exists find youngest ancester
     * @param element
     * @return
     */
    public Group getExistingMatchingAncester(Groupable element) {
        int maxMatch = 0;
        DefaultMutableTreeNode maxMatchNode = null;

        Enumeration<DefaultMutableTreeNode> nodes= getRoot().depthFirstEnumeration();
        while(nodes.hasMoreElements()) {
            DefaultMutableTreeNode node = nodes.nextElement();

            if(!node.isLeaf()) {
                int match = ((Group)node).getCommonPrefix(element);
                if(match >= maxMatch) {
                    maxMatch = match;
                    maxMatchNode = node;
                }
            }
        }

        return (Group)maxMatchNode;
    }

    public void insertSorted(DefaultMutableTreeNode parent, DefaultMutableTreeNode node) {
        Groupable element = (Groupable)node.getUserObject();
        ModelSort sort = new ModelSort(order);

        Enumeration<DefaultMutableTreeNode> children = parent.children();
        int i=0;
        while(children.hasMoreElements()) {
            DefaultMutableTreeNode child = children.nextElement();
            i++;

            if(sort.compare(element, (Groupable)child.getUserObject()) >= 0) {
                break;
            }
        }
        insertNodeInto(node, parent, i);
    }

    public void deleted(Groupable element) {
        //throw new UnsupportedOperationException("Not supported yet.");
        System.out.println("GroupTree: deleted "+element);
    }

    public void changed(Groupable element) {
        //throw new UnsupportedOperationException("Not supported yet.");
        System.out.println("GroupTree: changed "+element);
    }

    private int comparePrefixTo(Groupable g1, Groupable g2, int prefixLength) {
        int res = 0;
        for(int i = prefixLength; i>=0 && res == 0; i--) {
            res = g1.compareTo(order[i], g2);
        }
        return res;
    }

    public DefaultMutableTreeNode getRoot() {
        return (DefaultMutableTreeNode)super.getRoot();
    }

}
