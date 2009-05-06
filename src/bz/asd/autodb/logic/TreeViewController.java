package bz.asd.autodb.logic;

import bz.asd.autodb.data.CollectionChangeListener;
import bz.asd.autodb.data.Database;
import bz.asd.autodb.data.GroupTree;
import bz.asd.autodb.data.Groupable;
import bz.asd.autodb.data.Group;
import bz.asd.autodb.data.Settings;
import bz.asd.autodb.data.TreeViewSettings;
import bz.asd.mvc.Controller;
import bz.asd.mvc.View;
import bz.asd.autodb.gui.TreeView;
import bz.asd.autodb.data.Model;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 *
 * @author lars
 */
public class TreeViewController extends Controller implements ListController, CollectionChangeListener<Groupable> {

    private Database db;
    private TreeViewSettings tvs;
    private List<Groupable> models;
    private ModelViewController modelView;

    public TreeViewController(Database db, ModelViewController modelView) throws Exception {
        this.db = db;
        this.models = (List) db.getModels();
        this.modelView = modelView;
        tvs = Settings.getInstance().getTreeViewSettings();

        init();
    }

    public String getModelString(Object o) {
        String res = "";
        if(o != null) {
            res = o.toString();

            // invoke special toString only for Models in Leavs
            // isLeave() would be fine too
            if(o instanceof DefaultMutableTreeNode && ! (o instanceof Group)) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) o;
                Object userO = node.getUserObject();

                if(userO != null && userO instanceof Model) {
                    Model model = (Model) userO;
                    res = model.toString(tvs.getOrder(), tvs.getGroupLevel());
                }
            }
        }
        return res;
    }

    /**
     * Listener of TreeSelection changes.
     * @param leaf
     */
    public void treeSelectLeaf(DefaultMutableTreeNode leaf) {
        modelView.setModel((Model)leaf.getUserObject());
    }

    /**
     * Listener of TreeSelection changes.
     * @param node
     */
    public void treeSelectInnerNode(DefaultMutableTreeNode node) {
        modelView.setModel((Model)node.getUserObject());
    }

    private void selectNode(DefaultMutableTreeNode node) {
        getView().selectNode(node);
        modelView.setModel((Model)node.getUserObject());
    }

    @Override
    protected View createView() {
        return new TreeView(this);
    }

    @Override
    protected bz.asd.mvc.Model createModel() {
        GroupTree tree = GroupTree.create(models, tvs.getOrder(), tvs.getGroupLevel());
        db.addDbContentListener(this);
        return tree;
    }

    @Override
    protected TreeView getView() {
        return (TreeView)view;
    }

    @Override
    protected GroupTree getModel() {
        return (GroupTree)model;
    }

    /* ListController implementation */

    /**
     * Not called from tree, but from a list representation
     */
    public void selectNext() {
        DefaultMutableTreeNode curNode = (DefaultMutableTreeNode)getView().getSelectedNode();
        if(curNode == null) return; // no node selected
        curNode = getModel().getNextLeaf(curNode);
        if(curNode == null) return; // last node selected

        selectNode(curNode);
    }

    /**
     * Not called from tree, but from a list representation
     */
    public void selectPrev() {
        DefaultMutableTreeNode curNode = (DefaultMutableTreeNode)getView().getSelectedNode();
        curNode = getModel().getPrevLeaf(curNode);
        if(curNode == null) return; // last node selected

        selectNode(curNode);
    }

    /* CollectionChangeListener Proxy to get notified before model */
    public void added(Groupable element) {
        getModel().added(element);
        selectNode(getModel().getNodeRef(element));
    }

    public void deleted(Groupable element) {
        getModel().deleted(element);
    }

    public void changed(Groupable element) {
        getModel().changed(element);
        //IMPROVE check if we realy have to select the node again
        if(getView().getSelectedNode() == null) {
            selectNode(getModel().getNodeRef(element));
        }
    }

}
