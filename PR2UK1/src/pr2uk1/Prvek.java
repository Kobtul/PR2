/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr2uk1;

/**
 *
 * @author iliusvla
 */
public class P {

    private String jmeno , prijmeni, email, ulice, cislo, mesto;
    private int tel, PSC;
    
    public P(String _jmeno,String _prijmeni){
        jmeno = _jmeno;
        prijmeni = _prijmeni;
        email = "";
        ulice = "";
        cislo = "";
        mesto = "";
        tel = 0;
        PSC = 0;
    }
    
    public P(String _jmeno,String _prijmeni, String _ulice, String _cislo, String _mesto, int _tel, int _PSC, String _email){
        jmeno = _jmeno;
        prijmeni = _prijmeni;
        ulice = _ulice;
        cislo = _cislo;
        mesto = _mesto;
        tel = _tel;
        PSC = _PSC;
        email = _email;
    }
    
    public void addStreet(String _ulice){
        ulice = _ulice;
    }
    
    public void addEmail(String _email){
        email = _email;
    }
    
    public void addHouseNumber(String _cislo){
        cislo = _cislo;
    }
    
    public void addCity(String _mesto){
        mesto = _mesto;
    }
    
    public void addTelNumber(int _tel){
        tel = _tel;
    }
    
    public void addPSC(int _PSC){
        PSC = _PSC;
    }
    
    @Override
    public String toString(){
        String result = "";
        result += "Jmeno: " + jmeno + " " + prijmeni + "\nAdresa: " + 
                mesto + ", " + ulice + ", "+cislo + ", " + PSC + "\nEmail: " + email + "\nTel.c.: "+tel;       
        return result;
    }
}
