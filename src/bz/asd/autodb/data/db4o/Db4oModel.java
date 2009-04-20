package bz.asd.autodb.data.db4o;

import bz.asd.autodb.data.CollectionState;
import bz.asd.autodb.data.DbSupport;
import bz.asd.autodb.data.DefaultModel;

public class Db4oModel extends DefaultModel implements DbSupport {
	
    private CollectionState<Db4oModel> modelState;

	public Db4oModel() {
		super();
	}

    protected void setModelState(CollectionState<Db4oModel> modelState) {
        this.modelState = modelState;
    }
	
    /*@Override
	protected void init() {
		super.init();
	}*/

    /**
     * Called from Database on deletion of the Model.
     */
	@Override
	public void delete() {
		// TODO Auto-generated method stub
        modelState.deleted(this);
	}

    /**
     * Called from Database on creation of the Model.
     */
	@Override
	public void insert() {
		// TODO Auto-generated method stub
        modelState.added(this);
	}

    /**
     * Called from notifier on change of the Model.
     */
	@Override
	public void update() {
		// TODO Auto-generated method stub
        modelState.changed(this);
	}

    protected void notifyChangeListener() {
        super.notifyChangeListener();
        update();
    }
}
