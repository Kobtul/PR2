/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adresar;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteXMLFile {

    public static void main(String adress, Seznam s) {

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Seznam");
            doc.appendChild(rootElement);

            // staff elements
            System.out.println(s.size());
             for (int i = 0; i < s.size(); i++) {
            Prvek p = s.get(i);
            
            System.out.println(p.toString());

            Element staff = doc.createElement("Prvek");
            rootElement.appendChild(staff);


            Element firstname = doc.createElement("FirstName");
            firstname.appendChild(doc.createTextNode(p.getJmeno()));
            staff.appendChild(firstname);

            // lastname elements
            Element lastname = doc.createElement("LastName");
            lastname.appendChild(doc.createTextNode(p.getPrijmeni()));
            staff.appendChild(lastname);

            // nickname elements
            Element email = doc.createElement("Email");
            email.appendChild(doc.createTextNode(p.getEmail()));
            staff.appendChild(email);

            // salary elements
            Element ulice = doc.createElement("Ulice");
            ulice.appendChild(doc.createTextNode(p.getUlice()));
            staff.appendChild(ulice);

            Element cislo = doc.createElement("Cislo");
            cislo.appendChild(doc.createTextNode(p.getHouseNumber()));
            staff.appendChild(cislo);

            Element mesto = doc.createElement("Mesto");
            mesto.appendChild(doc.createTextNode(p.getMesto()));
            staff.appendChild(mesto);

            Element tel = doc.createElement("Tel");
            tel.appendChild(doc.createTextNode(Integer.toString(p.getNumber())));
            staff.appendChild(tel);

            Element psc = doc.createElement("PSC");
            psc.appendChild(doc.createTextNode(Integer.toString(p.getPSC())));
            staff.appendChild(psc);

             }


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(adress));

            // Output to console for testing
            //StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}