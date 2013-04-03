package adresar;

import java.io.IOException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public  class ReadXMLFile extends DefaultHandler {

    public static Seznam<Prvek> seznam = new Seznam<Prvek>();
    private Prvek acct;
    private String temp;
    public static ArrayList<Prvek> accList = new ArrayList<Prvek>();

    /**
     * The main method sets things up for parsing
     */
    public static void main(String cesta) throws IOException, SAXException,
            ParserConfigurationException {

        //Create a "parser factory" for creating SAX parsers
        SAXParserFactory spfac = SAXParserFactory.newInstance();

        //Now use the parser factory to create a SAXParser object
        SAXParser sp = spfac.newSAXParser();

        //Create an instance of this class; it defines all the handler methods
        ReadXMLFile handler = new ReadXMLFile();

        //Finally, tell the parser to parse the input and notify the handler
        sp.parse(cesta, handler);

        handler.readList();

     /*   for (Object p :seznam.toArray()){
            System.out.println("Final: " + p.toString());
        }*/
    }


    /*
     * When the parser encounters plain text (not XML elements),
     * it calls(this method, which accumulates them in a string buffer
     */
    @Override
    public void characters(char[] buffer, int start, int length) {
        temp = new String(buffer, start, length);
    }


    /*
     * Every time the parser encounters the beginning of a new element,
     * it calls this method, which resets the string buffer
     */
    @Override
    public void startElement(String uri, String localName,
            String qName, Attributes attributes) throws SAXException {
        temp = "";
        if (qName.equals("Prvek")) {
     
            acct = new Prvek();
        }
    }

    /*
     * When the parser encounters the end of an element, it calls this method
     */
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {


        if (qName.equals("FirstName")) {
            acct.addJmeno(temp);
        } else if (qName.equals("LastName")) {
            acct.addPrijmeni((temp));
        } else if (qName.equals("Email")) {
            acct.addEmail((temp));
        } else if (qName.equals("Ulice")) {
            acct.addStreet((temp));
        } else if (qName.equals("Cislo")) {
            acct.addHouseNumber((temp));
        } else if (qName.equals("Mesto")) {
            acct.addCity((temp));
        } else if (qName.equals("Tel")) {
            acct.addTelNumber(Integer.parseInt(temp));
        } else if (qName.equals("PSC")) {
            acct.addPSC(Integer.parseInt(temp));
        } else if (qName.equals("Prvek")) {            
                    accList.add(acct);
        }

    }

    private void readList() {
        System.out.println(accList.size());
        for (Object a: accList){
             seznam.add((Prvek)a);
        }
        
    }
}
