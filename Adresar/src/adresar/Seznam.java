/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adresar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author iliusvla
 */
public class Seznam<T> {

    private List<Prvek> seznam = new ArrayList<Prvek>();

    public Seznam() {
    }

    public boolean add(Prvek obj) {
        for (Prvek p : seznam) {
            if (p.getNumber() == obj.getNumber()) {
                System.err.println("Cislo tel. v seznamu uz existuje!");
            }
        }
        seznam.add(obj);
        return true;
    }

    public Prvek get(int i) {

        return seznam.get(i);
    }

    public boolean contains(Prvek obj) {
        return seznam.contains(obj);
    }

    public String toString() {
        String result = "";
        for (Prvek a : seznam) {
            result += a.toString();
        }
        return result;
    }

    public Prvek getByNum(int num) {
        for (Prvek p : seznam) {
            if (p.getNumber() == num) {
                return p;
            }
        }
        return null;
    }

    public List<Prvek> compTel() {
        List<Prvek> seznam1 = new ArrayList<Prvek>();
        seznam1.addAll(seznam);
        Collections.sort(seznam1, new TelComparator());
        return seznam1;
    }

    public List<Prvek> compJmen() {
        List<Prvek> seznam1 = new ArrayList<Prvek>();
        seznam1.addAll(seznam);
        Collections.sort(seznam1, new JmenoComparator());
        return seznam1;
    }

    public void findSubString(String a) {
        //boolean contains = string.contains(substring);    
        for (Prvek p : seznam) {
            if (p.getJmeno().contains(a) || p.getPrijmeni().contains(a) || p.getEmail().contains(a)) {
                System.out.println("\n_____________________\n" + p.toString() + "\n_________________\n");
            }
        }
    }

    public Seznam findSubString1(String a) {
        Seznam subseznam = new Seznam();
        for (Prvek p : seznam) {
            if (p.getJmeno().contains(a) || p.getPrijmeni().contains(a) || p.getEmail().contains(a) || Integer.toString(p.getNumber()).contains(a)) {
                subseznam.add(p);
            }
        }
        return subseznam;
    }

    public Object[] toArray() {
        if (seznam.size()>0){
        Object[] result = new Object[seznam.size() + 1];

        int i = 0;
        for (Prvek p : seznam) {
            result[i] = p.getJmeno() + " ";
            result[i] += p.getPrijmeni() + " " + p.getNumber();
            i++;
        }
        return result;
        }
        else return null;
    }

    public int size() {
        return seznam.size();
    }

    public void remove(int i) {
        seznam.remove(i);
    }
     public void removeAll() {
        seznam.clear();
    }
}
