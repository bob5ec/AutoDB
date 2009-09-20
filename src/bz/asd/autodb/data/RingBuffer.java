package bz.asd.autodb.data;

import java.util.LinkedList;

public class RingBuffer {

    private LinkedList buffer;
    private int size;

    public RingBuffer() {
        buffer = new LinkedList();
    }

    public void add(Object o) {
        if(!buffer.contains(o)) buffer.add(o);
        if (buffer.size() > size) {
            buffer.poll();
        }
    }

    public Object getLast() {
        Object res = null;
        if(buffer.size() != 0) res = buffer.getLast();
        return res;
    }

    public Object[] getBuffer() {
        return buffer.toArray();
    }

    public void setBuffer(Object[] buffer) {
        for(Object o : buffer) {
            this.buffer.add(o);
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
