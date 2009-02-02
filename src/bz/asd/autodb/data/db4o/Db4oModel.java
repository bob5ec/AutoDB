package bz.asd.autodb.data.db4o;

import bz.asd.autodb.data.DbSupport;
import bz.asd.autodb.data.Model;

public class Db4oModel extends Model implements DbSupport {
	
	private boolean hasChanged;

	public Db4oModel() {
		super();
	}
	
	protected void init() {
		super.init();
		hasChanged = false;
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setHasChanged(boolean hasChanged) {
		this.hasChanged = hasChanged;
	}
	
	protected boolean hasChanged() {
		return hasChanged;
	}

}
