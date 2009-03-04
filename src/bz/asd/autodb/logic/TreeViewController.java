package bz.asd.autodb.logic;

import bz.asd.autodb.data.GroupTree;
import bz.asd.autodb.data.Groupable;
import bz.asd.autodb.data.Settings;
import bz.asd.autodb.data.TreeViewSettings;
import bz.asd.mvc.Controller;
import bz.asd.mvc.View;
import bz.asd.autodb.gui.TreeView;
import bz.asd.autodb.data.Model;
import java.util.List;

/**
 *
 * @author lars
 */
public class TreeViewController extends Controller {

    List<Groupable> models;
    public TreeViewController(List<Model> models) {
        this.models = (List)models;
    }

    @Override
    protected View createView() {
        return new TreeView();
    }

    @Override
    protected bz.asd.mvc.Model createModel() {
        TreeViewSettings tvs = Settings.getInstance().getTreeViewSettings();
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
