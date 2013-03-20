/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr2uk1;

import java.util.Comparator;

/**
 *
 * @author iliusvla
 */
public class JmenoComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Prvek a1 = (Prvek) o1;
        Prvek a2 = (Prvek) o2;
        return (a1.getJmeno().compareTo(a2.getJmeno()));

    }
}
