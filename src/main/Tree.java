package main;

import java.util.List;

/**
 * Interface for FPTree
 *
 * @author vivek
 * @param <N>
 * @param <I>
 */
public interface Tree<N extends Node, I> {

    public N getRoot();

    public void addTransaction(List<I> l);

    public boolean isEmpty();
}
