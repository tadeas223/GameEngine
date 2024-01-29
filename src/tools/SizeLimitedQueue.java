package tools;

import java.util.LinkedList;

//AI generated this whole code :)
public class SizeLimitedQueue<E> extends LinkedList<E> {
    private int maxSize;

    public SizeLimitedQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public boolean add(E element) {
        super.add(element);
        while (size() > maxSize) {
            super.remove();
        }
        return true;
    }
}