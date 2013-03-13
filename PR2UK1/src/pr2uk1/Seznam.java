/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr2uk1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author iliusvla
 */
public class Seznam {

    private List<Prvek> seznam = new ArrayList<Prvek>();

    public Seznam() {
    }

    public boolean add(Prvek obj) throws Exception {
        for (Prvek p : seznam) {
            if (p.getNumber() == obj.getNumber()) {
                throw new Exception("Cislo tel. v seznamu uz existuje!");
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
    
    public Prvek getByNum(int num){
        for (Prvek p : seznam){
            if (p.getNumber() == num){
                return p;
            }
        }
        return null;
    }
}
