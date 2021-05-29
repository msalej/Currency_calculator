package parser;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class FileLoader {
    public NodeList readFile() {
        NodeList nList = null;
        try {
            File file = new File("exchange.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(file);
            document.getDocumentElement().normalize();

            nList = document.getElementsByTagName("Cube");

            for (int i=2; i < nList.getLength(); i++) {
                Node node = nList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("1 EUR = " + element.getAttribute("rate") + " " + element.getAttribute("currency"));
                }
            }
        } catch (Exception e) {
            System.out.println("\nexchange.xml file not found!\n");
            e.printStackTrace();
            System.exit(1);
        }

        return nList;
    }
}
