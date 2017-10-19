/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imodel;

import java.util.List;

/**
 *
 * @author vivek
 */
public interface Tree <N, I> {
    public N getRoot();
    public void addTransaction(List<I> l);
    public boolean isEmpty();
}
