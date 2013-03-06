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
public class  Seznam <T>{
    private List seznam = new ArrayList();
    
    public   Seznam(){
         
    }
    public void add(Object obj){
        seznam.add(obj);
    }
    public Object get(int i){
        return seznam.get(i);
    }
    public boolean contains(Object obj){
        return seznam.contains(obj);
    }
    public String toString(){
        String result = "";
        for (Object a: seznam){
            result += a.toString() + "\n_____________________\n";
        }        
        return result;
    }
}
