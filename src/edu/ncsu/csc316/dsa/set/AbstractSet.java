package edu.ncsu.csc316.dsa.set;

/**
 * The abstract class for sets
 * @author colinscanlon
 *
 * @param <E> The generic type of the set
 */
public abstract class AbstractSet<E> implements Set<E> {

    @Override
    public void addAll(Set<E> other) {
        for(E element : other) {
            add(element);
        }
    }

    @Override
    public void retainAll(Set<E> other) {
        for(E element : this) {
            if(!other.contains(element)) {
                remove(element);
            }
        }
    }

    @Override
    public void removeAll(Set<E> other) {
        for(E element : other) {
            remove(element);
        }
    }
    
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
}
