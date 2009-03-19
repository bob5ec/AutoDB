package bz.asd.autodb.logic;

import bz.asd.autodb.data.GroupTree;
import bz.asd.autodb.data.Groupable;
import bz.asd.autodb.data.Group;
import bz.asd.autodb.data.Settings;
import bz.asd.autodb.data.TreeViewSettings;
import bz.asd.mvc.Controller;
import bz.asd.mvc.View;
import bz.asd.autodb.gui.TreeView;
import bz.asd.autodb.data.Model;
import java.awt.Container;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author lars
 */
public class TreeViewController extends Controller {

    private TreeViewSettings tvs;
    private List<Groupable> models;
    private ModelViewController modelView;

    public TreeViewController(List<Model> models, ModelViewController modelView) throws Exception {
        this.models = (List)models;
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

    public void treeSelectLeaf(DefaultMutableTreeNode leaf) {
        selectModel((Model)leaf.getUserObject());
    }

    private void selectModel(Model model) {
        modelView.setModel(model);
    }

    @Override
    protected View createView() {
        return new TreeView(this);
    }

    @Override
    protected bz.asd.mvc.Model createModel() {
        return GroupTree.create(models, tvs.getOrder(), tvs.getGroupLevel());
    }

    @Override
    protected TreeView getView() {
        return (TreeView)view;
    }

    @Override
    protected GroupTree getModel() {
        return (GroupTree)model;
    }

}
