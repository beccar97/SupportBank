package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

class ProcessXML {
    private static final Logger LOGGER = LogManager.getLogger(ProcessXML.class);

    static void processXML(String fileName) throws IOException, SAXException, ParserConfigurationException {
        LOGGER.info(String.format("Attempting to process %s", fileName));
        ProcessXML.readXML(fileName);


    }

    private static void readXML(String fileName) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new File(fileName));

        document.getDocumentElement().normalize();

        Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());

        NodeList nList = document.getElementsByTagName("SupportTransaction");
        System.out.println("--------------");

        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            System.out.println("");

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element tElement = (Element) node;
                Element parties = (Element) tElement.getElementsByTagName("Parties").item(0);
                String date;
                String description;
                String amount;


                System.out.println("Date: " + tElement.getAttribute("Date"));
                System.out.println("Descrip: " + tElement.getElementsByTagName("Description").item(0).getTextContent());
                System.out.println("Value: " + tElement.getElementsByTagName("Value").item(0).getTextContent());
                System.out.println("From: " + parties.getElementsByTagName("From").item(0).getTextContent());
                System.out.println("To: " + parties.getElementsByTagName("To").item(0).getTextContent());
            }
        }

    }

}
