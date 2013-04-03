/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adresar;

import java.util.Comparator;

/**
 *
 * @author iliusvla
 */
public class TelComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Prvek a1 = (Prvek) o1;
        Prvek a2 = (Prvek) o2;
        if (a1.getNumber() > a2.getNumber()) {
            return 1;
        }
        return -1;

    }
}
