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
public class Prvek {

    private String jmeno, prijmeni, email, ulice, cislo, mesto;
    private int tel, PSC;

    public Prvek() {
        jmeno = "null";
        prijmeni = "null";
        email = "";
        ulice = "";
        cislo = "";
        mesto = "";
        tel = 0;
        PSC = 0;
    }

    public Prvek(String _jmeno, String _prijmeni) {
        jmeno = _jmeno;
        prijmeni = _prijmeni;
        email = "";
        ulice = "";
        cislo = "";
        mesto = "";
        tel = 0;
        PSC = 0;
    }

    public Prvek(String _jmeno, String _prijmeni, String _ulice, String _cislo, String _mesto, int _tel, int _PSC, String _email) throws Exception {
        jmeno = _jmeno;
        prijmeni = _prijmeni;
        ulice = _ulice;
        cislo = _cislo;
        if (numPlace(_tel) == 9) {
            tel = _tel;
        } else {
            throw new Exception("Cislo ma spatny format!");
        }
        mesto = _mesto;
        tel = _tel;
        PSC = _PSC;
        email = _email;
    }

    public void addJmeno(String _jmeno) {
        jmeno = _jmeno;
    }

    public void addPrijmeni(String _prijmeni) {
        prijmeni = _prijmeni;
    }

    public void addStreet(String _ulice) {
        ulice = _ulice;
    }

    public void addEmail(String _email) {
        email = _email;
    }

    public void addHouseNumber(String _cislo) {
        cislo = _cislo;
    }

    public void addCity(String _mesto) {
        mesto = _mesto;
    }

    public boolean addTelNumber(int _tel) {
        if (numPlace(_tel) == 9) {
            tel = _tel;
            return true;
        } else {
            System.out.println("Cislo ma spatny format");
            return false;
        }
    }

    public void addPSC(int _PSC) {
        PSC = _PSC;
    }

    @Override
    public String toString() {
        String result = "";


        result += "Jmeno: " + jmeno + " " + prijmeni;

        result += "\nAdresa: ";
        if (mesto != "") {
            result += mesto;
        }
        if (ulice != "") {
            result += ", " + ulice;
        }
        if (cislo != "") {
            result += ", " + cislo;
        }
        if (PSC != 0) {
            result += ", " + PSC;
        }
        if (email != "") {
            result += "\nEmail: " + email;
        }
        if (tel != 0) {
            result += "\nTel.c.: " + tel;
        }
        result += "\n______________________________\n";
        return result;
    }

    int getNumber() {
        return tel;
    }

    static int numPlace(int num) {
        int cnt = 0;
        while (num > 0) {
            int r = num % 10;
            cnt++;
            num = num / 10;
        }
        return cnt;
    }

    String getJmeno() {
        return this.jmeno;
    }

    String getPrijmeni() {
        return this.prijmeni;
    }

    String getEmail() {
        return this.email;
    }

    String getUlice() {
        return this.ulice;
    }

    String getHouseNumber() {
        return this.cislo;
    }

    String getMesto() {
        return this.mesto;
    }

    int getPSC() {
        return this.PSC;
    }
}
