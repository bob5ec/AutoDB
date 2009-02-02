package bz.asd.autodb.data.db4o;

import java.util.Collection;
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
	
	public Db4oDatabase(String dbName) {
		this.dbName = dbName;
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
		models.addAll(query.execute());
	}

	@Override
	public Model createModel() throws Exception {
		Db4oModel m = new Db4oModel();
		models.add(m);
		return m;
	}

	@Override
	public void deleteModel(Model model) throws Exception {
		if(model instanceof Db4oModel && models.remove(model)) {
			deletedModels.add((Db4oModel)model);
		}
		
	}

	@Override
	public Collection<Model> getModels() {
		return (Collection)models;
	}

	@Override
	public void save() throws Exception {
		for(Db4oModel m : deletedModels) {
			db.delete(m);
		}
		
		for(Db4oModel m : models) {
			if(m.hasChanged()) {
				m.setHasChanged(false);
				db.set(m);
			}
		}
		
	}

}
