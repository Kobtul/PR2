package com.example.scrolltest;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author iliusvla
 */
public class  Seznam <T>{
    private List<P> seznam = new ArrayList<P>();
    
    public   Seznam(){
         
    }
    public void add(P obj){
        seznam.add(obj);
    }
    public P get(int i){
        return seznam.get(i);
    }
    public boolean contains(P obj){
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
