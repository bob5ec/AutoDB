package bz.asd.autodb.logic;

import bz.asd.autodb.data.GroupTree;
import bz.asd.mvc.Controller;
import bz.asd.mvc.Model;
import bz.asd.mvc.View;
import bz.asd.autodb.gui.TreeView;
import java.awt.Frame;

/**
 *
 * @author lars
 */
public class TreeViewController extends Controller {

    @Override
    protected View createView() {
        return new TreeView();
    }

    @Override
    protected Model createModel() {
        return new GroupTree();
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
