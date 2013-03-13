/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr2uk1;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author iliusvla
 */
public class PR2UK1 {

    /**
     * @param args the command line arguments
     */
    static int t;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

//////////////////////////////////////////////////
        Prvek c1 = new Prvek("John", "Dow");
        c1.addCity("Praha");
        c1.addEmail("asd@safd.sa");
        c1.addHouseNumber("nula");
        c1.addStreet("1st");
        c1.addPSC(19821);
        c1.addTelNumber(123456789);


        Prvek c2 = new Prvek("Mad", "Man", "2st", "1", "LA", 123456788, 5161615, "a;ksf@asd.csa");

        Seznam s = new Seznam();

        s.add(c1);
        s.add(c2);


        System.out.println(s.toString() + "\n___________________________________\n");
//////////////////////////////////////////////
        
        while (true) {
            System.out.println("1) Create new Prvek");
            System.out.println("2) Find Prvek in seznam");
            System.out.println("3) Exit");
            scan();
            if (t == 1) {
                String jmeno, prijmeni;
                int tel;
                System.out.println("Input first name.");
                try {
                    jmeno = sc.next();
                } catch (InputMismatchException e) {
                    throw e;
                }
                System.out.println("Input last name (or will)");
                try {
                    prijmeni = sc.next();
                } catch (InputMismatchException e) {
                    throw e;
                }

                Prvek temp = new Prvek(jmeno, prijmeni);
                System.out.println("Input tel.");
                try {
                    tel = sc.nextInt();
                } catch (InputMismatchException e) {
                    throw e;
                }
                temp.addTelNumber(tel);
                System.out.println("1) Smazat prvek? \n2) Pridat do seznamu\n3) Exit");
                scan();
                if (t == 1) {
                    temp = null;
                } else if (t == 2) {
                    s.add(temp);
                } else {
                    continue;
                }

            } else if (t == 2) {
                System.out.println("Input tel.");
                int tel;
                try {
                    tel = sc.nextInt();
                } catch (InputMismatchException e) {
                    throw e;
                }
                System.out.println(s.getByNum(tel).toString());
            } else {
                System.exit(0);
            }
        }
    }

    static void scan() throws InputMismatchException, Exception {
        try {
            t = sc.nextInt();
            if (t > 3 || t < 1) {
                throw new Exception("Cislo ma spatny format!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong input! Try Again!");
            scan();
        }

    }
}
