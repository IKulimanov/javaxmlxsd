import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class exportXML {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        // Получение фабрики, чтобы после получить билдер документов.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        SchemaFactory factorySchem = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        File schemaFile = new File("src/main/resources/Univer.xsd");
        Schema xsdScheme = factorySchem.newSchema(schemaFile);

        Validator validator = xsdScheme.newValidator();

        // Получили из фабрики билдер, который парсит XML, создает структуру Document в виде иерархического дерева.
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Запарсили XML, создав структуру Document. Теперь у нас есть доступ ко всем элементам, каким нам нужно.
        Document document = builder.parse(new File("src/main/resources/rgatu.xml"));
        Element root = document.getDocumentElement();
        System.out.println(root.getTagName());
        NodeList nList = root.getChildNodes();

        System.out.println(nList.getLength());
        Element addElement = document.createElement("nameuniver");
        addElement.setTextContent("РГАТУ");

        root.appendChild(addElement);

        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node node = nList.item(temp);
            //System.out.println("");    //Just a separator
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                //Print each employee's detail
                Element eElement = (Element) node;
                //String textElement = eElement.getTextContent();
                if(eElement.getNodeName().equalsIgnoreCase("text")){
                    eElement.removeChild(eElement);
                }
                    System.out.println(" " + eElement.getTextContent());
                System.out.println(" " + eElement.getNodeName());


                //System.out.println("First Name : "  + eElement.getElementsByTagName("first").item(0).getFirstChild());
               // System.out.println("Last Name : "   + eElement.getElementsByTagName("lastname").item(0).getNodeValue());

            }
        }
        Transformer t= TransformerFactory.newInstance().newTransformer();
        t.transform(new DOMSource(document), new StreamResult(new FileOutputStream("rgatus.xml")));
        SAXSource source = new SAXSource(
                new NamespaceFilter(XMLReaderFactory.createXMLReader()),
                new InputSource(new FileInputStream("rgatus.xml")));

        validator.validate(source,null);
    }



}
