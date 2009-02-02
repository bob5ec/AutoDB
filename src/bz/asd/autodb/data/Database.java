package bz.asd.autodb.data;

import java.util.Collection;

/**
 * This class represents an open database (file).
 * The class implements a buffer for all changes to the objects. This buffer is synchronized
 * with the database on open, close and save calls.  
 * The application should be able to handle multiple databases at the same time.
 * --> No getInstance !
 * @author lars
 *
 */
public interface Database {
	public abstract void open() throws Exception;
	public abstract void close() throws Exception;
	public abstract void save() throws Exception;
	public abstract Collection<Model> getModels() throws Exception;
	public abstract Model createModel() throws Exception;
	public abstract void deleteModel(Model model) throws Exception;
}