import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class labXML {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        f.setValidating(false);
        f.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = f.newDocumentBuilder();
        Document doc = builder.parse(new File("src/main/resources/rgatu2.xml"));

        printDom("",doc.getFirstChild());
        addNewStudent(doc);
    }
    /**Вывод полученного дома*/
    private static void printDom(String prefix, Node node) {

        String text = node.getNodeValue();
        if (text != null && !text.isEmpty() && !text.contains("\n")) {
            System.out.println(prefix+":"+text + "\"");
        }


        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            printDom(prefix + node.getNodeName() + ":", children.item(i));
        }
    }
    /**Функция добавления элементов*/
    private static void addNewStudent(Document document) throws TransformerFactoryConfigurationError, DOMException {
        // Получаем корневой элемент
        NodeList students = document.getElementsByTagName("napravlenie");
       Node studentPar = students.item(0);

        // Создаем элемент для нового студента
        Element student = document.createElement("student");
        //добавляем элемент для поля Имя и заполняем
        Element firstname = document.createElement("firstname");
        firstname.setTextContent("Кот");
        // добавляем элемент для поля Отчество и заполняем
        Element midlename = document.createElement("midlename");
        midlename.setTextContent("Котеевич");
        //добавляем элемент для поля Фамилия и заполняем
        Element lastname = document.createElement("lastname");
        lastname.setTextContent("Котейкин");

        // Добавляем новые заполненные элементы в элемент студент
        student.appendChild(firstname);
        student.appendChild(midlename);
        student.appendChild(lastname);

        // Добавляем элемент студент
        studentPar.appendChild(student);
        //валидируем с xsd
        boolean answer = validateXMLSchema("src/main/resources/Univer2.xsd", "src/main/resources/rgatu2.xml");
        System.out.println("Result:" + answer);

        // Записываем XML в файл
        writeDocument(document);
    }
    /**Функция записи данных в xml*/
    private static void writeDocument(Document document) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream("other.xml");
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }
    /**Валидация xml по xsd*/
    public static boolean validateXMLSchema(String xsdPath, String xmlPath)
    {
        try {
            // Получить фабрику для схемы
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            // Загрузить схему из XSD
            Schema schema = factory.newSchema(new File(xsdPath));
            // Создать валидатор (проверялбщик)
            Validator validator = schema.newValidator();
            // Запусить проверку - если будет исключение, значит есть ошибки.
            // Если нет - все заполнено правильно
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
        return true;
    }
}
