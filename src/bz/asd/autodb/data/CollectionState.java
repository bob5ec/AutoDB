package bz.asd.autodb.data;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author lars
 */
public class CollectionState<T> {

    List<CollectionChangeListener<T>> listeners;

    public CollectionState() {
        listeners = new LinkedList<CollectionChangeListener<T>>();
    }

    public void addListener(CollectionChangeListener<T> listener) {
        listeners.add(listener);
    }

    public void added(T element) {
        for(CollectionChangeListener<T> l : listeners) {
            l.added(element);
        }
    }

    public void deleted(T element) {
        for(CollectionChangeListener<T> l : listeners) {
            l.deleted(element);
        }
    }

    public void changed(T element) {
        for(CollectionChangeListener<T> l : listeners) {
            l.changed(element);
        }
    }
}
