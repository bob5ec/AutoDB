package bz.asd.autodb.data.db4o;

import bz.asd.autodb.data.CollectionState;
import bz.asd.autodb.data.DbSupport;
import bz.asd.autodb.data.DefaultModel;
import java.beans.PropertyChangeSupport;

public class Db4oModel extends DefaultModel implements DbSupport {

    private CollectionState<Db4oModel> modelState;

    public Db4oModel() {
        super();
    }

    // if data structure is changed, use this method to convert "old" objects to
    // "new" ones. This method is called after db4o has filled in the data from
    // the db and before the object is given to the
    /*public void objectOnActivate(ObjectContainer container) {

    }*/

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

    @Override
    protected void notifyChangeListener() {
        super.notifyChangeListener();
        update();
    }

    /**
     * Called before the Object is serialized.
     * Use this to clenup state which should not go to database.
     */
    public void prepareSleep() {
        propertySupport = null;
    }

    /**
     * Called after the Object is read from it's serialized state.
     * Use this to restore state.
     */
    public void resumeFromSleep() {
        propertySupport = new PropertyChangeSupport(this);
    }
}
