package bz.asd.autodb.data.db4o;

import bz.asd.autodb.data.CollectionChangeListener;
import bz.asd.autodb.data.CollectionState;
import java.util.LinkedList;
import java.util.List;

import bz.asd.autodb.data.Database;
import bz.asd.autodb.data.Model;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.query.Query;
import com.db4o.types.TransientClass;

public class Db4oDatabase implements Database, TransientClass {

	private String dbName;
	private ObjectContainer db;
	private List<Db4oModel> models, deletedModels;
    private CollectionState<Db4oModel> modelState;
	
	public Db4oDatabase(String dbName) {
		this.dbName = dbName;
        modelState = new CollectionState<Db4oModel>();
	}

	@Override
	public void close() throws Exception {
		db.close();
		models = null;
		deletedModels = null;
		db = null;
	}

	@Override
	public void open() throws Exception {
		if(db!=null) close();
		db=Db4o.openFile(dbName);
		
		Query query = db.query();
		query.constrain(Db4oModel.class);
		
		models = new LinkedList<Db4oModel>();
		deletedModels = new LinkedList<Db4oModel>();
		for(Object o : query.execute()) {
            Db4oModel model = (Db4oModel)o;
            models.add(model);
            model.setModelState(modelState);
        }
	}

	@Override
	public Model createModel() throws Exception {
		Db4oModel m = new Db4oModel();
		models.add(m);
        m.setModelState(modelState);
        m.insert();
		return m;
	}

	@Override
	public void deleteModel(Model model) throws Exception {
		if(model instanceof Db4oModel && models.remove(model)) {
            Db4oModel m = (Db4oModel)model;
			deletedModels.add(m);
            m.delete();
		}
		
	}

	@Override
	public List<Model> getModels() {
		return (List)models; //TODO typecast bug?
	}

	@Override
	public void save() throws Exception {
		for(Db4oModel m : deletedModels) {
			db.delete(m);
		}
		
		for(Db4oModel m : models) {
			if(m.hasChanged()) {
                // reset object
                // IMPROVE use Db4o stuff for this
                m.setModelState(null);
				m.setHasChanged(false);
                // save it
				db.set(m);
                // reinit model
                m.setModelState(modelState);
			}
		}
		
	}

    public boolean hasChanged() {
        boolean res = false;
        for(Db4oModel m : models) {
			res = m.hasChanged();
            if(res == true) break;
        }
        return res;
    }

    public void addDbContentListener(CollectionChangeListener listener) {
        modelState.addListener(listener);
    }

}
