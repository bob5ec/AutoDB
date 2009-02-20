package bz.asd.autodb.data;

/**
 * Abstract description of an Object that has attributes. Comparison is
 * possible in every attribute, which allows to find Groupables which are equal
 * in some attribute and differ in others. These Groupables form a Group.
 * @author lars
 */
public interface Groupable<T> {

    public int compareTo(int attribute, T o);
    public int getAttributeCount();

}
