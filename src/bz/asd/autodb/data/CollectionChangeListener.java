package bz.asd.autodb.data;

/**
 *
 * @author lars
 */
public interface CollectionChangeListener<T> {

    public void added(T element);
    public void deleted(T element);
    public void changed(T element);

}
