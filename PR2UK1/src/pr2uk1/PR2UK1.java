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


        Prvek c2 = new Prvek("Aad", "Man", "2st", "1", "LA", 123456788, 5161615, "a;ksf@asd.csa");
        Prvek c3 = new Prvek("Zad", "Man", "2st", "1", "LA", 100000000, 5161615, "a;ksf@asd.csa");
        Prvek c4 = new Prvek("Wad", "Man", "2st", "1", "LA", 999999999, 5161615, "a;ksf@asd.csa");
        Seznam s = new Seznam();

        s.add(c1);
        s.add(c2);
        s.add(c3);
        s.add(c4);

        System.out.println( "\n___________________________________\n");
       // s.findSubString("a");
        //s.findSubString("ad");
        //s.findSubString("csa");
        //System.out.println(s.toString() + "\n___________________________________\n");

        //System.out.println(s.compTel().toString() + "\n___________________________________\n");
       // System.out.println(s.compJmen().toString() + "\n___________________________________\n");
//////////////////////////////////////////////

        while (true) {
            System.out.println("1) Create new Prvek");
            System.out.println("2) Find Prvek in seznam");
            System.out.println("3) Vypsat seznam");
            System.out.println("4) Exit");
            scan();
            if (t == 1) {
                String jmeno, prijmeni;
                int tel = 0;
                System.out.println("Input first name.");
                while (true) {
                    try {
                        jmeno = sc.next();
                        break;
                    } catch (Exception e) {
                        System.out.println("Try again");
                    }
                }
                System.out.println("Input last name (or will)");
                while (true) {
                    try {
                        prijmeni = sc.next();
                        break;
                    } catch (Exception e) {
                        System.out.println("Try again");
                    }
                }
                Prvek temp = new Prvek(jmeno, prijmeni);
                System.out.println("Input tel.");

                try {
                    tel = sc.nextInt();

                } catch (NumberFormatException e) {
                    System.out.println("Try again");
                }

                while (!temp.addTelNumber(tel)) {
                    temp.addTelNumber(tel);
                }
                System.out.println("1) Smazat prvek? \n2) Pridat do seznamu\n3) Pridat a vypsat seznam\n4) Exit");
                scan();
                if (t == 1) {
                    temp = null;
                } else if (t == 2) {
                    s.add(temp);

                } else if (t == 3) {
                    s.add(temp);
                    System.out.println(s.toString());
                } else {
                    continue;
                }
            } else if (t == 2) {
                System.out.println("Input tel.");
                int tel = 0;
                while (true) {
                    try {
                        tel = sc.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Wrong! Try again!");
                        continue;
                    }
                }
                if (s.getByNum(tel) != null) {
                    System.out.println(s.getByNum(tel).toString());
                } else {
                    System.out.println("Number not founded!");
                }
            } else if (t == 3) {
                System.out.println(s.toString());

            } else {
                System.exit(0);
            }
        }
    }

    static void scan() throws InputMismatchException, Exception {
        try {
            t = sc.nextInt();
            if (t > 4 || t < 1) {
                throw new Exception("Cislo ma spatny format!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong input! Try Again!");
            scan();
        }

    }
}
